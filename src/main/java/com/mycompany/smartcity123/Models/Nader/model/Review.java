/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smartcity123.Models.Nader.model;

/**
 *
 * @author manso
 */


public final class Review {

    private final String username;
    private final int rating;

    public Review(String username, int rating) {
        this.username = username;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return username + " rated: " + rating;
    }
}
