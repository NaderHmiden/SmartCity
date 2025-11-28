/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AttractionModels;

/**
 *
 * @author manso
 */


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Sealed base class for any Place in the domain.
 * Only permitted subclasses must be declared with {@code permits}.
 */
public sealed abstract class Place implements Identifiable
        permits Attraction {

    private final String id;
    private String name;
    private String description;
    private final Address address;
    private final Contact contact;
    private final Coordinates coordinates;
    private final List<Review> reviews = new ArrayList<>();

    protected Place(String id, String name, String description,
                    Address address, Contact contact, Coordinates coordinates) {
        this.id = Objects.requireNonNull(id, "id");
        this.name = Objects.requireNonNull(name, "name");
        this.description = description == null ? "" : description;
        this.address = Objects.requireNonNull(address, "address");
        this.contact = Objects.requireNonNull(contact, "contact");
        this.coordinates = Objects.requireNonNull(coordinates, "coordinates");
    }

    @Override
    public String getId() {
        return id;
    }

    public String getName() { return name; }

    public void setName(String name) {
        this.name = Objects.requireNonNull(name, "name");
    }

    public String getDescription() { return description; }

    public void setDescription(String description) {
        this.description = description == null ? "" : description;
    }

    public Address getAddress() { return address; }

    public Contact getContact() { return contact; }

    public Coordinates getCoordinates() { return coordinates; }

    /**
     * Retourne une vue non modifiable des reviews.
     * @return 
     */
    public List<Review> getReviews() {
        return Collections.unmodifiableList(reviews);
    }

    /**
     * Ajoute un avis au lieu.
     *
     * @param review Review to add
     */
    public void addReview(Review review) {
        Objects.requireNonNull(review, "review");
        reviews.add(review);
    }

    /**
     * Calcul d'une note moyenne simple (0..5).
     * @return 
     */
    public double averageRating() {
        return reviews.stream()
                .mapToInt(Review::rating)
                .average()
                .orElse(0.0);
    }

    @Override
    public String toString() {
        return "Place{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", averageRating=" + averageRating() +
                '}';
    }

    public int getId() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

