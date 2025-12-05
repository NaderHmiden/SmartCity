package com.mycompany.smartcity123.Models.Aziz.model;

import java.util.List;
import java.util.Objects;

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
        return getName() + " (" + category + ") - Price: " + price;
    }
}
