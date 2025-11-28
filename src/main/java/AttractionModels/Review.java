/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AttractionModels;

/**
 *
 * @author manso
 */


import java.time.Instant;
import java.util.Objects;

/**
 * Final class representing a user review.
 */
public final class Review {

    static int rating(T value) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    private final String id;
    private final String author;
    private final int rating; // 0..5
    private final String comment;
    private final Instant createdAt;

    public Review(String id, String author, int rating, String comment, Instant createdAt) {
        this.id = Objects.requireNonNull(id, "id");
        this.author = Objects.requireNonNull(author, "author");
        if (rating < 0 || rating > 5) {
            throw new IllegalArgumentException("rating must be between 0 and 5");
        }
        this.rating = rating;
        this.comment = comment == null ? "" : comment;
        this.createdAt = createdAt == null ? Instant.now() : createdAt;
    }

    public String id() { return id; }
    public String author() { return author; }
    public int rating() { return rating; }
    public String comment() { return comment; }
    public Instant createdAt() { return createdAt; }

    @Override
    public String toString() {
        return "Review{" + id + " by " + author + " (" + rating + ")}";
    }
}

