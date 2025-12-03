/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smartcity123.Models.Mazen.model;

/**
 *
 * @author manso
 */



public sealed abstract class FoodPlace permits FastFood ,luxuryRestaurant {
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

    public abstract String getType();

    @Override
    public String toString() {
        return "%s (%s) - %s".formatted(name, getType(), cuisine);
    }
}
