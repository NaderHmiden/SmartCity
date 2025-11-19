/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Restaurant.exception;

/**
 *
 * @author manso
 */
public class InvalidRestaurantInputException extends RuntimeException {
    public InvalidRestaurantInputException(String message) {
        super(message);
    }
}
