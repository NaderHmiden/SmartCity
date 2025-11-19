/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Hotels.Models;

/**
 *
 * @author manso
 */
import hotel.model.StayPlace;

@FunctionalInterface
public interface HotelFilter {
    boolean apply(StayPlace hotel);

    public boolean apply(T t);
}
