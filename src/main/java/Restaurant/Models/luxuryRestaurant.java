/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Restaurant.Models;

/**
 *
 * @author manso
 */

import java.util.ArrayList;
import java.util.List;

/**
 * Restaurant haut de gamme (classe finale).
 */
public final class LuxuryRestaurant extends FoodPlace {
    private final List<String> sommeliers = new ArrayList<>();
    private final boolean hasMichelinStar;

    public LuxuryRestaurant(int id, String name, CuisineType cuisine,
                            Address address, ContactInfo contact, PriceRange priceRange,
                            boolean hasMichelinStar) {
        super(id, name, cuisine, address, contact, priceRange);
        this.hasMichelinStar = hasMichelinStar;
    }

    public void addSommelier(String name) { if (name != null && !name.isBlank()) sommeliers.add(name); }
    public List<String> sommeliers() { return List.copyOf(sommeliers); }
    public boolean hasMichelinStar() { return hasMichelinStar; }

    @Override
    public String getType() { return "LuxuryRestaurant"; };
    @Override
    public String toString() {
       return super.toString() + 
           ", sommeliers=" + sommeliers + 
           ", MichelinStar=" + hasMichelinStar;
}

}

