package com.mycompany.smartcity123.Controller.Mazen;

import com.mycompany.smartcity123.Models.Mazen.model.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class RestaurantAddController {

    @FXML private TextField idField, nameField, streetField, cityField, postalField, phoneField, emailField;
    @FXML private ComboBox<CuisineType> cuisineField;
    @FXML private TextField minPriceField, maxPriceField;
    @FXML private ComboBox<String> typeField;

    @FXML private CheckBox driveThroughBox, deliveryBox; // FastFood
    @FXML private CheckBox michelinBox;                  // Luxury
    @FXML private TextArea sommeliersArea;

    private final RestaurantService service = RestaurantService.getInstance();

    @FXML
    public void initialize() {
        cuisineField.setItems(FXCollections.observableArrayList(CuisineType.values()));
        typeField.setItems(FXCollections.observableArrayList("FastFood", "LuxuryRestaurant"));

        // Masquer les sous-champs au démarrage
        handleTypeChange();

        // Changement de type
        typeField.setOnAction(e -> handleTypeChange());
    }

    @FXML
    private void handleTypeChange() {
        boolean isFastFood = "FastFood".equals(typeField.getValue());
        boolean isLuxury = "LuxuryRestaurant".equals(typeField.getValue());

        // FastFood champs
        driveThroughBox.setDisable(!isFastFood);
        deliveryBox.setDisable(!isFastFood);

        // Luxury champs
        michelinBox.setDisable(!isLuxury);
        sommeliersArea.setDisable(!isLuxury);
    }

    @FXML
    private void addRestaurant(ActionEvent event) {
        try {
            int id = Integer.parseInt(idField.getText().trim());
            String name = nameField.getText().trim();
            CuisineType cuisine = cuisineField.getValue();

            Address address = new Address(
                    streetField.getText().trim(),
                    cityField.getText().trim(),
                    postalField.getText().trim()
            );

            ContactInfo contact = new ContactInfo(
                    phoneField.getText().trim(),
                    emailField.getText().trim()
            );

            PriceRange price = new PriceRange(
                    Double.parseDouble(minPriceField.getText().trim()),
                    Double.parseDouble(maxPriceField.getText().trim()),
                    "€"
            );

            FoodPlace restaurant;

            if ("FastFood".equals(typeField.getValue())) {
                restaurant = new FastFood(
                        id, name, cuisine, address, contact, price,
                        driveThroughBox.isSelected(), deliveryBox.isSelected()
                );
            } else {
                luxuryRestaurant lr = new luxuryRestaurant(
                        id, name, cuisine, address, contact, price,
                        michelinBox.isSelected()
                );

                for (String s : sommeliersArea.getText().split("\n")) {
                    if (!s.isBlank()) lr.addSommelier(s.trim());
                }

                restaurant = lr;
            }

            service.addRestaurant(restaurant);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/restaurant-list.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Liste des Restaurants");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Impossible d'ajouter le restaurant");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    private void close() {
        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.close();
    }
}
