/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smartcity123.Models.Mazen.model;

/**
 *
 * @author manso
 */
import java.time.LocalDate;

/**
 * Review immutable et finale.
 */
public final class Review {
    private final String user;
    private final int rating; 
    private final String comment;
    private final LocalDate date;

    public Review(String user, int rating, String comment) {
        if (rating < 1 || rating > 5) throw new IllegalArgumentException("rating must be 1..5");
        this.user = user;
        this.rating = rating;
        this.comment = comment == null ? "" : comment;
        this.date = LocalDate.now();
    }

    public String user() { return user; }
    public int rating() { return rating; }
    public String comment() { return comment; }
    public LocalDate date() { return date; }

    @Override
    public String toString() {
        return "%s — %d/5 — %s (%s)".formatted(user, rating, comment, date);
    }
}


