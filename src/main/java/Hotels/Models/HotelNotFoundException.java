/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Hotels.Models;

/**
 *
 * @author manso
 */
public class HotelNotFoundException extends RuntimeException {
    public HotelNotFoundException(String msg) {
        super(msg);
    }
}
