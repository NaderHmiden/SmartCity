/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Hotels.Models;

/**
 *
 * @author manso
 */


public final class Resort extends StayPlace {

    private final AmenitiesPackage amenities;

    public Resort(String name, ContactDetails contact, PricePlan price, AmenitiesPackage amenities) {
        super(name, HotelCategory.RESORT, contact, price);
        this.amenities = amenities;
    }

    @Override
    public void showInfo() {
        System.out.println("Resort: " + getName() +
                " | Amenities: " + amenities.features());
    }
}
