package com.mycompany.smartcity123.Models.Nader.model;

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

    // Ajouter ce getter manquant
    public AmenitiesPackage getAmenities() {
        return amenities;
    }
   
}