package com.mycompany.smartcity123.Models.Mazen.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

public class RestaurantService {

    // Liste observable pour JavaFX
    private final ObservableList<FoodPlace> restaurants = FXCollections.observableArrayList();

    // ====== SINGLETON ======
    private static final RestaurantService instance = new RestaurantService();
    public static RestaurantService getInstance() { return instance; }

    private RestaurantService() {}  // privé

    // ====== AJOUT ======
    public void addRestaurant(FoodPlace r) {
        Objects.requireNonNull(r, "Restaurant cannot be null");

        if (existsById(r.getId()))
            throw new IllegalStateException("Restaurant with ID already exists: " + r.getId());

        if (existsByName(r.getName()))
            throw new IllegalStateException("Restaurant with name already exists: " + r.getName());

        restaurants.add(r);
    }

    // ====== SUPPRESSION ======
    public boolean removeRestaurant(int id) {
        return restaurants.removeIf(r -> r.getId() == id);
    }

    // ====== RECHERCHE ======
    public Optional<FoodPlace> findById(int id) {
        return restaurants.stream()
                .filter(r -> r.getId() == id)
                .findFirst();
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

    // ====== FILTRES / TRI ======
    public List<FoodPlace> filterByCuisine(CuisineType type) {
        return restaurants.stream()
                .filter(r -> r.getCuisine() == type)
                .toList();
    }

    public List<FoodPlace> filter(Predicate<FoodPlace> rule) {
        return restaurants.stream().filter(rule).toList();
    }

    public List<FoodPlace> sortByAveragePrice() {
        return restaurants.stream()
                .sorted((a, b) -> Double.compare(avg(a.getPriceRange()), avg(b.getPriceRange())))
                .toList();
    }

    // ====== UTILS ======
    private boolean existsById(int id) {
        return restaurants.stream().anyMatch(r -> r.getId() == id);
    }

    private boolean existsByName(String name) {
        return restaurants.stream().anyMatch(r -> r.getName().equalsIgnoreCase(name));
    }

    private double avg(PriceRange range) {
        return (range.minPrice() + range.maxPrice()) / 2.0;
    }

    // ====== GETTER ======
    public ObservableList<FoodPlace> getAll() {
        return restaurants;
    }
}
