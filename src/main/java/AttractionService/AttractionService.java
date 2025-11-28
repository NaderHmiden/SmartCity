/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AttractionService;

import AttractionException.AttractionNotFoundException;
import AttractionModels.Attraction;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AttractionService {

    private final List<Attraction> attractions = new ArrayList<>();

    // ---------------------------------------------------------
    // 1) ADD
    // ---------------------------------------------------------
    public void addAttraction(Attraction attraction) {
        if (attraction == null) {
            throw new IllegalArgumentException("Attraction cannot be null");
        }
        attractions.add(attraction);
    }

    // ---------------------------------------------------------
    // 2) REMOVE BY ID
    // ---------------------------------------------------------
    public void removeAttractionById(int id) {
        boolean removed = attractions.removeIf(a -> a.getId() == id);

        if (!removed) {
            throw new AttractionNotFoundException(
                    "No attraction found with id: " + id
            );
        }
    }

    // ---------------------------------------------------------
    // 3) FIND BY ID
    // ---------------------------------------------------------
    public Attraction findById(int id) {
        return attractions.stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElseThrow(() ->
                        new AttractionNotFoundException(
                                "No attraction found with id: " + id)
                );
    }

    // ---------------------------------------------------------
    // 4) FILTER BY CITY
    // ---------------------------------------------------------
    public List<Attraction> findByCity(String city) {
        return attractions.stream()
                .filter(a -> a.getCity().equalsIgnoreCase(city))
                .collect(Collectors.toList());
    }

    // ---------------------------------------------------------
    // 5) FILTER BY PRICE RANGE
    // ---------------------------------------------------------
    public List<Attraction> filterByPrice(double min, double max) {
        return attractions.stream()
                .filter(a -> a.getPrice() >= min && a.getPrice() <= max)
                .collect(Collectors.toList());
    }

    // ---------------------------------------------------------
    // 6) GET ALL
    // ---------------------------------------------------------
    public List<Attraction> getAllAttractions() {
        return new ArrayList<>(attractions); // copy to avoid modification
    }
}
