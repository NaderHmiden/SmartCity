package com.mycompany.smartcity123.Models.Aziz.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

public class AttractionService {

    private final ObservableList<Attraction> attractions = FXCollections.observableArrayList();

    // Singleton
    private static final AttractionService instance = new AttractionService();
    public static AttractionService getInstance() { return instance; }

    private AttractionService() {} // privé pour singleton

    public void addAttraction(Attraction attraction) {
        Objects.requireNonNull(attraction, "Attraction cannot be null");
        if (exists(attraction.getId()))
            throw new IllegalStateException("Attraction already exists: " + attraction.getId());
        attractions.add(attraction);
    }

    public boolean removeAttraction(String id) {
        return attractions.removeIf(a -> a.getId().equalsIgnoreCase(id));
    }

    public Optional<Attraction> findById(String id) {
        return attractions.stream().filter(a -> a.getId().equalsIgnoreCase(id)).findFirst();
    }

    public List<Attraction> findByName(String keyword) {
        if (keyword == null || keyword.isBlank()) return List.of();
        return attractions.stream()
                .filter(a -> a.getName().toLowerCase().contains(keyword.toLowerCase()))
                .toList();
    }

    public List<Attraction> findByCategories(AttractionCategory... categories) {
        Set<AttractionCategory> set = Set.of(categories);
        return attractions.stream()
                .filter(a -> set.contains(a.getCategory()))
                .toList();
    }

    public List<Attraction> filter(Predicate<Attraction> rule) {
        return attractions.stream().filter(rule).toList();
    }

    public ObservableList<Attraction> getAllAttractions() {
        return attractions;
    }

    public boolean exists(String id) {
        return attractions.stream().anyMatch(a -> a.getId().equalsIgnoreCase(id));
    }
}
