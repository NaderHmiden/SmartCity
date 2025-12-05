package com.mycompany.smartcity123.Controller;

import com.mycompany.smartcity123.Models.Mazen.model.FoodPlace;
import com.mycompany.smartcity123.Models.Mazen.model.RestaurantService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.beans.property.SimpleStringProperty;

public class RestauantController {

    @FXML private TableView<FoodPlace> restaurantTable;
    @FXML private TableColumn<FoodPlace, String> colName;
    @FXML private TableColumn<FoodPlace, String> colType;
    @FXML private TableColumn<FoodPlace, String> colCuisine;

    private RestaurantService service;
    private final ObservableList<FoodPlace> data = FXCollections.observableArrayList();

    public void setService(RestaurantService service) {
        this.service = service;
        loadRestaurants();
    }

    @FXML
    public void initialize() {
        colName.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getName()));
        colType.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getType()));
        colCuisine.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getCuisine().toString()));
        restaurantTable.setItems(data);
    }

    private void loadRestaurants() {
        if (service != null) {
            data.setAll(service.getAll());
        }
    }
}
