/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Restaurant.Models;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.OptionalDouble;


/**
 *
 * @author manso
 */
public sealed abstract class FoodPlace permits FastFood, LuxuryRestaurant {

    protected final int id;
    protected final String name;
    protected final CuisineType cuisine;
    protected final Address address;
    protected final ContactInfo contact;
    protected PriceRange priceRange;
    protected final List<MenuItem> menu = new ArrayList<>();
    protected final List<Review> reviews = new ArrayList<>();

    protected FoodPlace(int id, String name, CuisineType cuisine,
                        Address address, ContactInfo contact, PriceRange priceRange) {
        this.id = id;
        this.name = name;
        this.cuisine = cuisine;
        this.address = address;
        this.contact = contact;
        this.priceRange = priceRange;
    }

    public int id() { return id; }
    public String name() { return name; }
    public CuisineType cuisine() { return cuisine; }
    public Address address() { return address; }
    public ContactInfo contact() { return contact; }
    public PriceRange priceRange() { return priceRange; }
    public List<MenuItem> menu() { return Collections.unmodifiableList(menu); }
    public List<Review> reviews() { return Collections.unmodifiableList(reviews); }

    public void addMenuItem(MenuItem item) { if (item != null) menu.add(item); }
    public void addReview(Review review) { if (review != null) reviews.add(review); }
    public void setPriceRange(PriceRange p) { if (p != null) this.priceRange = p; }

    public double averageRating() {
        OptionalDouble avg = reviews.stream().mapToInt(Review::rating).average();
        return avg.orElse(0.0);
    }

    public List<MenuItem> findMenuItemsByName(String q) {
        if (q == null || q.isBlank()) return List.of();
        String low = q.toLowerCase();
        return menu.stream()
                .filter(mi -> mi.name().toLowerCase().contains(low))
                .toList();
    }

    public abstract String getType();

    @Override
    public String toString() {
        return "%s [%d] %s — %s — avg=%.2f".formatted(getType(), id, name, cuisine, averageRating());
    }
}
