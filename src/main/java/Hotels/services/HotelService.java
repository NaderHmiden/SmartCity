package Hotels.services;

import Hotels.Models.HotelFilter;
import Hotels.Models.StayPlace;
import Hotels.Models.HotelNotFoundException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class HotelService {

    private final List<StayPlace> hotels = new ArrayList<>();

    /**
     * Ajoute un nouvel hôtel (avec contrôle si déjà existant)
     */
    public void addHotel(StayPlace hotel) {
        if (hotel == null)
            throw new IllegalArgumentException("Hotel cannot be null");

        boolean exists = hotels.stream()
                .anyMatch(h -> h.getName().equalsIgnoreCase(hotel.getName()));

        if (exists)
            throw new IllegalStateException("Hotel already exists: " + hotel.getName());

        hotels.add(hotel);
    }

    /**
     * Recherche par nom en retournant Optional (meilleure pratique)
     */
    public Optional<StayPlace> findByName(String name) {
        return hotels.stream()
                .filter(h -> h.getName().equalsIgnoreCase(name))
                .findFirst();
    }

    /**
     * Même méthode mais version qui lance exception
     */
    public StayPlace findOrThrow(String name) {
        return findByName(name)
                .orElseThrow(() -> new HotelNotFoundException("Hotel not found: " + name));
    }

    /**
     * Filtre dynamique avec HotelFilter
     */
    public List<StayPlace> filter(HotelFilter filter) {
        if (filter == null) return List.of(); // sécurité
        return hotels.stream()
                .filter(filter::apply)
                .toList(); // Java 16+, remplace Collectors.toList()
    }

    /**
     * Liste immuable → empêche modification externe
     */
    public List<StayPlace> getAll() {
        return Collections.unmodifiableList(hotels);
    }

    /**
     * Suppression d’un hôtel par nom
     */
    public boolean remove(String name) {
        return hotels.removeIf(h -> h.getName().equalsIgnoreCase(name));
    }

    /**
     * Moyenne des prix journaliers (bonus utile)
     */
    public double averagePrice() {
        return hotels.stream()
                .mapToDouble(h -> h.getPricePlan().basePrice())
                .average()
                .orElse(0.0);
    }
}
