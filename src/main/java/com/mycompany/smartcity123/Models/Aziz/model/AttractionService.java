package com.mycompany.smartcity123.Models.Aziz.model;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Service de gestion des attractions touristiques.
 * Gère : ajout, recherche, filtrage, suppression et statistiques.
 */
public class AttractionService {

    private final List<Attraction> attractions = new ArrayList<>();

    /**
     * Ajouter une attraction (avec validation d'unicité ID)
     */
    public void addAttraction(Attraction attraction) {
        Objects.requireNonNull(attraction, "Attraction cannot be null");

        if (exists(attraction.getId()))
            throw new IllegalStateException("Attraction already exists: " + attraction.getId());

        attractions.add(attraction);
    }

    /**
     * Supprimer une attraction par id
     * @return true si une suppression a eu lieu
     */
    public boolean removeAttraction(String id) {
        return attractions.removeIf(a -> a.getId().equalsIgnoreCase(id));
    }

    /**
     * Recherche par ID (Optional pour éviter null)
     */
    public Optional<Attraction> findById(String id) {
        return attractions.stream()
                .filter(a -> a.getId().equalsIgnoreCase(id))
                .findFirst();
    }

    /**
     * Recherche par nom OU partie du nom
     */
    public List<Attraction> findByName(String keyword) {
        if (keyword == null || keyword.isBlank()) return List.of();
        return attractions.stream()
                .filter(a -> a.getName().toLowerCase().contains(keyword.toLowerCase()))
                .toList();
    }

    /**
     * Filtrer par une ou plusieurs catégories
     */
    public List<Attraction> findByCategories(AttractionCategory... categories) {
        Set<AttractionCategory> set = Set.of(categories);
        return attractions.stream()
                .filter(a -> set.contains(a.getCategory()))
                .toList();
    }

    /**
     * Méthode générique de filtrage (lambda)
     */
    public List<Attraction> filter(Predicate<Attraction> rule) {
        return attractions.stream().filter(rule).toList();
    }

    

    public List<Attraction> getAllAttractions() {
        return List.copyOf(attractions);
    }

    
    public boolean exists(String id) {
        return attractions.stream()
                .anyMatch(a -> a.getId().equalsIgnoreCase(id));
    }

    
}
