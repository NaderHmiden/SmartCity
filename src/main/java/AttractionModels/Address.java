/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AttractionModels;

/**
 *
 * @author manso
 */


import java.util.Objects;
import java.util.stream.Stream;

/**
 * Immutable value object for address using Java record.
 */
public record Address(String street, String city, String postalCode, String country) {
    public Address {
        Objects.requireNonNull(street, "street");
        Objects.requireNonNull(city, "city");
        Objects.requireNonNull(postalCode, "postalCode");
        Objects.requireNonNull(country, "country");
    }

    @Override
    public String toString() {
        return street + ", " + postalCode + " " + city + ", " + country;
    }

}

