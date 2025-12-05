package com.mycompany.smartcity123.Models.Nader.model;

public final class BusinessHotel extends StayPlace {

    private final RoomInfo roomInfo;

    public BusinessHotel(String name, ContactDetails contact, PricePlan price, RoomInfo roomInfo) {
        super(name, HotelCategory.BUSINESS, contact, price);
        this.roomInfo = roomInfo;
    }

    @Override
    public void showInfo() {
        System.out.println("Business Hotel: " + getName() +
                " | Rooms: " + roomInfo.numberOfRooms());
    }

    // Ajouter ce getter manquant
    public RoomInfo getRoomInfo() {
        return roomInfo;
    }
}