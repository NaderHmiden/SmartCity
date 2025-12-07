package com.mycompany.smartcity123.Controller.Mazen;

import com.mycompany.smartcity123.Models.Mazen.model.*;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.io.IOException;
import javafx.beans.property.SimpleStringProperty;

public class RestaurantController {

    @FXML private TableView<FoodPlace> restaurantTable;
    @FXML private TableColumn<FoodPlace, String> colName;
    @FXML private TableColumn<FoodPlace, String> colCuisine;
    @FXML private TableColumn<FoodPlace, String> colPrice;

    @FXML private TextField searchField;
    @FXML private ComboBox<CuisineType> cuisineFilter;

    @FXML private Button btnAdd;
    @FXML private Button btnEdit;
    @FXML private Button btnDelete;
    @FXML private Button btnDetails;

    private final RestaurantService service = RestaurantService.getInstance();
    private FilteredList<FoodPlace> filteredList;

    @FXML
    public void initialize() {
        // Initialiser colonnes TableView
        colName.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getName()));
        colCuisine.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getCuisine().name()));
        colPrice.setCellValueFactory(c -> new SimpleStringProperty(
                c.getValue().getPriceRange().minPrice() + " - " +
                c.getValue().getPriceRange().maxPrice() + " " +
                c.getValue().getPriceRange().currency()
        ));

        // Lier TableView à la liste observable du service
        filteredList = new FilteredList<>(service.getAll(), p -> true);
        restaurantTable.setItems(filteredList);

        // Initialiser ComboBox des cuisines
        cuisineFilter.getItems().setAll(CuisineType.values());

        // Mettre à jour le filtre à chaque changement de texte ou sélection de cuisine
        searchField.textProperty().addListener((obs, oldV, newV) -> applyFilter());
        cuisineFilter.valueProperty().addListener((obs, oldV, newV) -> applyFilter());
    }

    private void applyFilter() {
        String keyword = searchField.getText().trim().toLowerCase();
        CuisineType selectedCuisine = cuisineFilter.getValue();

        filteredList.setPredicate(r ->
                (keyword.isEmpty() || r.getName().toLowerCase().contains(keyword)) &&
                (selectedCuisine == null || r.getCuisine() == selectedCuisine)
        );
    }

    @FXML
    private void addRestaurant(ActionEvent event) {
        openView(event, "/fxml/restaurant-add.fxml", "Ajouter Restaurant");
    }

     @FXML
    private void deleteRestaurant(ActionEvent event) {
        FoodPlace selected = restaurantTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            service.removeRestaurant(selected.getId());
        }
    }

   /* @FXML
    private void editRestaurant(ActionEvent event) {
        FoodPlace selected = restaurantTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            openView(event, "/fxml/restaurant-edit.fxml", "Modifier Restaurant");
        }
    }

    @FXML
    private void showDetails(ActionEvent event) {
        FoodPlace selected = restaurantTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            openView(event, "/fxml/restaurant-details.fxml", "Détails Restaurant");
        }
    }*/

    private void openView(ActionEvent event, String fxmlPath, String title) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle(title);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
