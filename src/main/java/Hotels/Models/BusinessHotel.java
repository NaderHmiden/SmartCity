/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Hotels.Models;

/**
 *
 * @author manso
 */


public final class BusinessHotel extends StayPlace {

    private final RoomInfo roomInfo;

    public BusinessHotel(String name, ContactDetails contact, PricePlan price, RoomInfo roomInfo) {
        super(name, Hotelcategory.BUSINESS, contact, price);
        this.roomInfo = roomInfo;
    }

    @Override
    public void showInfo() {
        System.out.println("Business Hotel: " + getName() +
                " | Rooms: " + roomInfo.numberOfRooms());
    }
}

