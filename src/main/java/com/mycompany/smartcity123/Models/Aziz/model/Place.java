package com.mycompany.smartcity123.Models.Aziz.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public abstract sealed class Place implements Identifiable
        permits Attraction {

    private final String id;
    private String name;
    private String description;
    private final Address address;
    private final Contact contact;
    private final Coordinate coordinates;
    private final List<Review> reviews = new ArrayList<>();

    protected Place(String id, String name, String description,
                    Address address, Contact contact, Coordinate coordinates) {
        this.id = Objects.requireNonNull(id, "id");
        this.name = Objects.requireNonNull(name, "name");
        this.description = description == null ? "" : description;
        this.address = Objects.requireNonNull(address, "address");
        this.contact = Objects.requireNonNull(contact, "contact");
        this.coordinates = Objects.requireNonNull(coordinates, "coordinates");
    }

    @Override
    public String getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = Objects.requireNonNull(name); }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description == null ? "" : description; }

    public Address getAddress() { return address; }
    public Contact getContact() { return contact; }
    public Coordinate getCoordinates() { return coordinates; }

    public List<Review> getReviews() { return Collections.unmodifiableList(reviews); }

    public void addReview(Review review) { 
        Objects.requireNonNull(review, "review");
        reviews.add(review); 
    }

    public double averageRating() {
        return reviews.stream().mapToInt(Review::rating).average().orElse(0.0);
    }
}
