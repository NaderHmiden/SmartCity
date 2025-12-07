package com.mycompany.smartcity123.Models.Nader.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class HotelService {

    // Liste observable pour JavaFX
    private final ObservableList<StayPlace> hotels = FXCollections.observableArrayList();

    // ====== SINGLETON ======
    private static final HotelService instance = new HotelService();
    private HotelService() {} // privé
    public static HotelService getInstance() { return instance; }

    // ====== AJOUT ======
    public void addHotel(StayPlace hotel) {
        Objects.requireNonNull(hotel, "Hotel cannot be null");

        if (exists(hotel.getName()))
            throw new IllegalStateException("Hotel already exists: " + hotel.getName());

        hotels.add(hotel);
    }

    // ====== SUPPRESSION ======
    public boolean removeHotel(String name) {
        return hotels.removeIf(h -> h.getName().equalsIgnoreCase(name));
    }

    // ====== RECHERCHE ======
    public Optional<StayPlace> findByName(String name) {
        return hotels.stream()
                .filter(h -> h.getName().equalsIgnoreCase(name))
                .findFirst();
    }

    public List<StayPlace> searchByName(String keyword) {
        if (keyword == null || keyword.isBlank()) return List.of();
        return hotels.stream()
                .filter(h -> h.getName().toLowerCase().contains(keyword.toLowerCase()))
                .toList();
    }

    // ====== FILTRES / TRI ======
    public List<StayPlace> filterByCategory(HotelCategory category) {
        return hotels.stream()
                .filter(h -> h.getCategory() == category)
                .toList();
    }

    public List<StayPlace> sortByAveragePrice() {
        return hotels.stream()
                .sorted((a, b) -> Double.compare(avg(a.getPricePlan()), avg(b.getPricePlan())))
                .toList();
    }

    // ====== UTILS ======
    private boolean exists(String name) {
        return hotels.stream().anyMatch(h -> h.getName().equalsIgnoreCase(name));
    }

    private double avg(PricePlan price) {
        return (price.minPrice() + price.maxPrice()) / 2.0;
    }

    // ====== GETTER ======
    public ObservableList<StayPlace> getAll() {
        return hotels;
    }
}
