package com.mycompany.smartcity123.Controller.Aziz;

import com.mycompany.smartcity123.Models.Aziz.model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.Node;

import java.io.IOException;
import java.util.List;

public class AttractionAddController {

    @FXML
    private TextField idField, nameField, descriptionField, addressField, latitudeField, longitudeField, priceField, phoneField;

    @FXML
    private ComboBox<AttractionCategory> categoryField;

    @FXML
    private Button btnAdd, btnCancel;

    private final AttractionService attractionService = AttractionService.getInstance();

    @FXML
    private void initialize() {
        // Initialisation de la ComboBox avec les catégories disponibles
        categoryField.getItems().setAll(AttractionCategory.values());
    }

    @FXML
    private void addAttraction(ActionEvent event) {
        try {
            // Récupérer les valeurs du formulaire
            String id = idField.getText();
            String name = nameField.getText();
            String description = descriptionField.getText();
            String street = addressField.getText();
            String city = ""; // optionnel
            String postalCode = ""; // optionnel
            String country = ""; // optionnel
            String phone = phoneField.getText();
            String email = ""; // optionnel
            double price = Double.parseDouble(priceField.getText());
            AttractionCategory category = categoryField.getValue();
            double latitude = Double.parseDouble(latitudeField.getText());
            double longitude = Double.parseDouble(longitudeField.getText());

            // Créer les objets nécessaires
            Address address = new Address(street, city, postalCode, country);
            Contact contact = new Contact(phone, email);
            Coordinate coordinates = new Coordinate(latitude, longitude);

            // Créer l'attraction
            Attraction attraction = new Attraction(id, name, description, address, contact, coordinates, category, List.of(), price);

            // Ajouter l'attraction au service
            attractionService.addAttraction(attraction);

            // Retour à la liste
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/attraction-list.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Liste des Attractions");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void cancel(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/attraction-list.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Liste des Attractions");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
