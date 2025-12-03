/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smartcity123.Controller;

/**
 *
 * @author manso
 */
public class RestaurantController {

    private final RestaurantService service;
    private final RestaurantListView listView;
    private final RestaurantDetailsView detailsView;

    public RestaurantController() {
        this.service = new RestaurantService();
        this.listView = new RestaurantListView();
        this.detailsView = new RestaurantDetailsView();
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

    public void create(Restaurant r) {
        service.add(r);
        System.out.println("Restaurant ajouté !");
    }

    public void delete(int id) {
        service.remove(id);
        System.out.println("Restaurant supprimé !");
    }
}
