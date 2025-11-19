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
 * FastFood (classe finale).
 */
public final class FastFood extends FoodPlace {
    private final boolean driveThrough;
    private final boolean delivery;

    public FastFood(int id, String name, CuisineType cuisine,
                    Address address, ContactInfo contact, PriceRange priceRange,
                    boolean driveThrough, boolean delivery) {
        super(id, name, cuisine, address, contact, priceRange);
        this.driveThrough = driveThrough;
        this.delivery = delivery;
    }

    public boolean hasDriveThrough() { return driveThrough; }
    public boolean hasDelivery() { return delivery; }

    @Override
    public String getType() { return "FastFood"; }
}

