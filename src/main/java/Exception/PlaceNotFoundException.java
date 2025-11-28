/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Exception;

/**
 *
 * @author manso
 */


/**
 * Thrown when a place is not found.
 */
public class PlaceNotFoundException extends RuntimeException {
    public PlaceNotFoundException() { super(); }
    public PlaceNotFoundException(String message) { super(message); }
    public PlaceNotFoundException(String message, Throwable cause) { super(message, cause); }
}

