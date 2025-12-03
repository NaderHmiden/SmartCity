package com.mycompany.smartcity123.Models.Mazen.model;

import com.mycompany.smartcity123.Models.Mazen.Exception.RestaurantNotFoundException;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class RestaurantService {

    private final List<FoodPlace> restaurants = new ArrayList<>();

  

    public void addRestaurant(FoodPlace r) {
        Objects.requireNonNull(r, "Restaurant cannot be null");

        if (existsById(r.getId()))
            throw new IllegalStateException("ID already used: " + r.getId());

        if (existsByName(r.getName()))
            throw new IllegalStateException("Name already exists: " + r.getName());

        restaurants.add(r);
    }

    

    public Optional<FoodPlace> findByName(String name) {
        return restaurants.stream()
                .filter(r -> r.getName().equalsIgnoreCase(name))
                .findFirst();
    }

    
    public List<FoodPlace> searchByName(String keyword) {
        if (keyword == null || keyword.isBlank()) return List.of();
        return restaurants.stream()
                .filter(r -> r.getName().toLowerCase().contains(keyword.toLowerCase()))
                .toList();
    }

    public FoodPlace findOrThrow(String name) {
        return findByName(name)
                .orElseThrow(() -> new RestaurantNotFoundException("Restaurant not found: " + name));
    }

   

    public List<FoodPlace> filter(CuisineType type) {
        return restaurants.stream()
                .filter(r -> r.getCuisine() == type)
                .toList();
    }

    
    public List<FoodPlace> filter(RestaurantFilter filter) {
        return restaurants.stream().filter(filter::apply).toList();
    }

    public List<FoodPlace> filter(Predicate<FoodPlace> condition) {
        return restaurants.stream().filter(condition).toList();
    }


    public List<FoodPlace> sortByAveragePrice() {
        return restaurants.stream()
                .sorted(Comparator.comparingDouble(r -> avg(r.getPriceRange())))
                .toList();
    }

  

    public boolean remove(int id) {
        return restaurants.removeIf(r -> r.getId() == id);
    }

   
  
    public double averagePrice() {
        return restaurants.stream()
                .mapToDouble(r -> avg(r.getPriceRange()))
                .average()
                .orElse(0.0);
    }

   

    private boolean existsById(int id) {
        return restaurants.stream().anyMatch(r -> r.getId() == id);
    }

    private boolean existsByName(String name) {
        return restaurants.stream().anyMatch(r -> r.getName().equalsIgnoreCase(name));
    }

    private double avg(PriceRange p) {
        return (p.minPrice() + p.maxPrice()) / 2.0;
    }

    public List<FoodPlace> getAll() {
        return List.copyOf(restaurants);
    }
}
