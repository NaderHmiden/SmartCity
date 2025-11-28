/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AttractionException;

/**
 *
 * @author manso
 */


/**
 * Exception levée lorsqu'une attraction demandée n'existe pas.
 */
public class AttractionNotFoundException extends RuntimeException {

    public AttractionNotFoundException(String message) {
        super(message);
    }
}
