/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AttractionModels;

/**
 *
 * @author manso
 */



/**
 * Immutable contact record.
 */
public record Contact(String phone, String email, String website) {
   
    @Override
    public String toString() {
        return "Contact{phone=" + phone + ", email=" + email + ", website=" + website + "}";
    }
}

