package com.mycompany.smartcity123.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.Node;

import java.io.IOException;

public class MainController {

    @FXML
    private void goToHotel(ActionEvent event) {
        openView(event, "/com/mycompany/smartcity123/View/Hotel/Hotel-list.fxml", "Hotels");
    }

    @FXML
    private void goToAttraction(ActionEvent event) {
        openView(event, "/fxml/attraction-list.fxml", "Attractions");
    }

    @FXML
    private void goToRestaurant(ActionEvent event) {
        openView(event, "/com/mycompany/smartcity123/View/Restaurant/restaurant-list.fxml", "Restaurants");
    }

    /**
     * Remplace la scène actuelle par le nouveau FXML
     * @param event L'événement déclenché par le bouton
     * @param fxmlPath Chemin du fichier FXML
     * @param title Titre de la fenêtre
     */
    private void openView(ActionEvent event, String fxmlPath, String title) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));

            // Récupère la fenêtre actuelle
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.setTitle(title);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
