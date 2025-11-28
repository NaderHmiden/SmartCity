/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Hotels.Models;

/**
 *
 * @author manso
 */


public sealed abstract class StayPlace permits Resort, BusinessHotel {

    private final String name;
    private final HotelCategory category;
    private final ContactDetails contact;
    private final PricePlan price;

    public StayPlace(String name, HotelCategory category, ContactDetails contact, PricePlan price) {
        this.name = name;
        this.category = category;
        this.contact = contact;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public HotelCategory getCategory() {
        return category;
    }

    public ContactDetails getContact() {
        return contact;
    }

    public PricePlan getPrice() {
        return price;
    }
    ///////////////ABSTRACT CLASS//////////////////////////////////////
    public abstract void showInfo();

    @Override
    public String toString() {
        return name + " (" + category + ")";
    }
}

