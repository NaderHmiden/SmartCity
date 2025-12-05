package com.mycompany.smartcity123.Models.Mazen.model;

public abstract sealed class FoodPlace permits FastFood, luxuryRestaurant {
    private final int id;
    private final String name;
    private final CuisineType cuisine;
    private final Address address;
    private final ContactInfo contact;
    private final PriceRange priceRange;

    protected FoodPlace(int id, String name, CuisineType cuisine,
                        Address address, ContactInfo contact, PriceRange priceRange) {
        this.id = id;
        this.name = name;
        this.cuisine = cuisine;
        this.address = address;
        this.contact = contact;
        this.priceRange = priceRange;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public CuisineType getCuisine() { return cuisine; }
    public Address getAddress() { return address; }
    public ContactInfo getContact() { return contact; }
    public PriceRange getPriceRange() { return priceRange; }

    /** Pour le TableColumn colType */
    public String getCuisineType() {
        return cuisine.name(); // retourne ITALIAN, FRENCH, etc.
    }

    public abstract String getType();

    @Override
    public String toString() {
        return "%s (%s) - %s".formatted(name, getType(), cuisine);
    }
}
