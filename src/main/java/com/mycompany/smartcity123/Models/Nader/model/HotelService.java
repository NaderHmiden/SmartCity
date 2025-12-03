package com.mycompany.smartcity123.Models.Nader.model;

import com.mycompany.smartcity123.Models.Nader.Exception.HotelNotFoundException;

import java.util.*;
import java.util.stream.Collectors;


public class HotelService {

    private final List<StayPlace> hotels = new ArrayList<>();

   
    public void addHotel(StayPlace hotel) {
        Objects.requireNonNull(hotel, "Hotel cannot be null");

        if (exists(hotel.getName()))
            throw new IllegalStateException("Hotel already exists: " + hotel.getName());

        hotels.add(hotel);
    }

    
    public Optional<StayPlace> findByName(String name) {
        return hotels.stream()
                .filter(h -> h.getName().equalsIgnoreCase(name))
                .findFirst();
    }

    
    public StayPlace findOrThrow(String name) {
        return findByName(name)
                .orElseThrow(() -> new HotelNotFoundException("Hotel not found: " + name));
    }

   
    public List<StayPlace> search(String keyword) {
        if (keyword == null || keyword.isBlank()) return List.of();
        return hotels.stream()
                .filter(h -> h.getName().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

   
    public List<StayPlace> getAll() {
        return List.copyOf(hotels);
    }

    
    public boolean remove(String name) {
        return hotels.removeIf(h -> h.getName().equalsIgnoreCase(name));
    }

    
    public List<StayPlace> filter(HotelFilter filter) {
        if (filter == null) return List.of();
        return hotels.stream().filter(filter::apply).toList();
    }

    
    public double averagePrice() {
        return hotels.stream()
                .mapToDouble(h -> (h.getPricePlan().minPrice() + h.getPricePlan().maxPrice()) / 2)
                .average()
                .orElse(0.0);
    }

    
    public List<StayPlace> sortByPrice() {
        return hotels.stream()
                .sorted(Comparator.comparingDouble(h -> (h.getPricePlan().minPrice() + h.getPricePlan().maxPrice()) / 2))
                .toList();
    }

    
    public boolean exists(String name) {
        return name != null &&
                hotels.stream().anyMatch(h -> h.getName().equalsIgnoreCase(name));
    }
}
