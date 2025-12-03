/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smartcity123.Models.Aziz.model;

/**
 *
 * @author manso
 */




import java.util.List;
import java.util.Objects;
import java.util.stream.DoubleStream;

/**
 * Classe finale représentant une attraction touristique.
 */
public final class Attraction extends Place {

    private final AttractionCategory category;
    private final List<OpeningHours> openingHours;
    private final double price;

    public Attraction(
            String id,
            String name,
            String description,
            Address address,
            Contact contact,
            Coordinate coordinates,
            AttractionCategory category,
            List<OpeningHours> openingHours,
            double price
    ) {
        super(id, name, description, address, contact, coordinates);
        this.category = Objects.requireNonNull(category, "category");
        this.openingHours = openingHours == null ? List.of() : List.copyOf(openingHours);
        if (price < 0) throw new IllegalArgumentException("price must be >= 0");
        this.price = price;
    }

    public AttractionCategory getCategory() { return category; }
    public List<OpeningHours> getOpeningHours() { return openingHours; }
    public double getPrice() { return price; }

    @Override
    public String toString() {
        return "Attraction{" + getId() + " - " + getName() + " (" + category + ") Price: " + price + "}";
    }

    DoubleStream getTicketPrice() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    DoubleStream getVisitorsCount() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
