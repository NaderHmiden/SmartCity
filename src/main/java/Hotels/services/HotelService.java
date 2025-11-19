/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Hotels.services;

/**
 *
 * @author manso
 */
import Hotels.Models.HotelFilter;
import Hotels.Models.StayPlace;
import Hotels.Models.HotelNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HotelService {

    private final List<StayPlace> hotels = new ArrayList<>();

    public void addHotel(StayPlace hotel) {
        hotels.add(hotel);
    }

    public StayPlace findByName(String name) {
        return hotels.stream()
                .filter(h -> h.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new HotelNotFoundException("Hotel not found: " + name));
    }

    public List<StayPlace> filter(HotelFilter filter) {
        return hotels.stream()
                .filter(filter::apply)
                .collect(Collectors.toList());
    }

    public List<StayPlace> getAll() {
        return hotels;
    }
}