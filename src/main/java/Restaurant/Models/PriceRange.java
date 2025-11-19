/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Restaurant.Models;

/**
 *
 * @author manso
 */


/**
 * Fourchette de prix moyenne du restaurant.
 */
public record PriceRange(double minPrice, double maxPrice, String currency) { }
