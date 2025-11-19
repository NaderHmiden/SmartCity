/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Restaurant.Models;

/**
 *
 * @author manso
 */
import citymanager.restaurant.model.FoodPlace;

@FunctionalInterface
public interface RestaurantFilter {
    boolean apply(FoodPlace r);
}
