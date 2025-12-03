/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smartcity123.Controller;

/**
 *
 * @author manso
 */
public class HotelController {

    private final HotelService service;
    private final HotelListView listView;
    private final HotelDetailsView detailsView;

    public HotelController() {
        this.service = new HotelService();
        this.listView = new HotelListView();
        this.detailsView = new HotelDetailsView();
    }

    public void showAll() {
        listView.render(service.getAll());
    }

    public void showDetails(int id) {
        try {
            detailsView.render(service.getById(id));
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    public void create(Hotel h) {
        service.add(h);
        System.out.println("Hotel ajouté !");
    }

    public void delete(int id) {
        service.remove(id);
        System.out.println("Hotel supprimé !");
    }
}

