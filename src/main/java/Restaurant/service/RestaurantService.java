/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Restaurant.service;

/**
 *
 * @author manso
 */


import Restaurant.exception.RestaurantNotFoundExceptio;
import Restaurant.Models.RestaurantFilter;
import Restaurant.Models.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Service métier pour gérer les FoodPlace.
 * Utilise Map, List et Streams.
 */
public class RestaurantService {

    private final Map<Integer, FoodPlace> restaurants = new HashMap<>();
    private final AtomicInteger idGen = new AtomicInteger(1);

    /**
     * Ajoute un restaurant. Si r.id() <= 0, un id est généré automatiquement.
     * Retourne l'id utilisé.
     */
    public int addRestaurant(FoodPlace r) {
        if (r == null) throw new IllegalArgumentException("restaurant is null");
        int id = r.id() <= 0 ? idGen.getAndIncrement() : r.id();
        restaurants.put(id, r);
        return id;
    }

    public void removeRestaurant(int id) {
        restaurants.remove(id);
    }

    public FoodPlace getById(int id) {
        if (!restaurants.containsKey(id)) throw new RestaurantNotFoundExceptio("Restaurant " + id + " not found");
        return restaurants.get(id);
    }

    public List<FoodPlace> listAll() {
        return restaurants.values().stream()
                .toList();
    }

    public List<FoodPlace> findByCity(String city) {
        if (city == null) return List.of();
        return restaurants.values().stream()
                .filter(r -> r.address().city().equalsIgnoreCase(city))
                .toList();
    }

    public List<FoodPlace> filter(RestaurantFilter f) {
        if (f == null) return listAll();
        return restaurants.values().stream()
                .filter(f::apply)
                .toList();
    }

    public List<FoodPlace> topRated(int n) {
        if (n <= 0) return List.of();
        return restaurants.values().stream()
                .sorted(Comparator.comparingDouble(FoodPlace::averageRating).reversed())
                .limit(n)
                .toList();
    }

    public Map<CuisineType, Long> countByCuisine() {
        return restaurants.values().stream()
                .collect(Collectors.groupingBy(FoodPlace::cuisine, Collectors.counting()));
    }

    public double averageMenuPrice() {
        return restaurants.values().stream()
                .flatMap(r -> r.menu().stream())
                .mapToDouble(MenuItem::price)
                .average().orElse(0.0);
    }

    public List<MenuItem> searchMenuItems(String query) {
        if (query == null || query.isBlank()) return List.of();
        String q = query.toLowerCase();
        return restaurants.values().stream()
                .flatMap(r -> r.menu().stream())
                .filter(mi -> mi.name().toLowerCase().contains(q))
                .toList();
    }

    public Map<Integer, List<MenuItem>> menuByRestaurant() {
        return restaurants.values().stream()
                .collect(Collectors.toMap(FoodPlace::id, FoodPlace::menu));
    }
}
