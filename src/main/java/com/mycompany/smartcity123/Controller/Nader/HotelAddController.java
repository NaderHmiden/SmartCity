package com.mycompany.smartcity123.Controller.Nader;

import com.mycompany.smartcity123.Models.Nader.model.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class HotelAddController {

    @FXML private TextField nameField, phoneField, emailField, cityField, minPriceField, maxPriceField;
    @FXML private ComboBox<HotelCategory> categoryField;
    @FXML private TextArea amenitiesArea; // Pour Resort
    @FXML private CheckBox poolBox, spaBox; // Pour Resort
    @FXML private TextField roomsField; // Pour BusinessHotel
    @FXML private CheckBox meetingRoomBox; // Pour BusinessHotel

    private final HotelService service = HotelService.getInstance();

    @FXML
    public void initialize() {
        categoryField.setItems(FXCollections.observableArrayList(HotelCategory.values()));
        handleCategoryChange();
        categoryField.setOnAction(e -> handleCategoryChange());
    }

    private void handleCategoryChange() {
        boolean isResort = categoryField.getValue() == HotelCategory.RESORT;
        boolean isBusiness = categoryField.getValue() == HotelCategory.BUSINESS;

        amenitiesArea.setDisable(!isResort);
        poolBox.setDisable(!isResort);
        spaBox.setDisable(!isResort);

        roomsField.setDisable(!isBusiness);
        meetingRoomBox.setDisable(!isBusiness);
    }

    @FXML
    private void addHotel(ActionEvent event) {
        try {
            String name = nameField.getText().trim();
            ContactDetails contact = new ContactDetails(
                    phoneField.getText().trim(),
                    emailField.getText().trim(),
                    cityField.getText().trim()
            );
            PricePlan price = new PricePlan(
                    Double.parseDouble(minPriceField.getText().trim()),
                    Double.parseDouble(maxPriceField.getText().trim())
            );

            StayPlace hotel;

            if (categoryField.getValue() == HotelCategory.RESORT) {
                AmenitiesPackage amenities = new AmenitiesPackage(
                        amenitiesArea.getText().trim(),
                        poolBox.isSelected(),
                        spaBox.isSelected()
                );
                hotel = new Resort(name, contact, price, amenities);
            } else { // BUSINESS
                RoomInfo roomInfo = new RoomInfo(
                        Integer.parseInt(roomsField.getText().trim()),
                        meetingRoomBox.isSelected()
                );
                hotel = new BusinessHotel(name, contact, price, roomInfo);
            }

            service.addHotel(hotel);
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/hotel-list.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Liste des Hotels");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Impossible d'ajouter l'hôtel");
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
