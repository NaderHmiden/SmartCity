package com.mycompany.smartcity123.Controller.Nader;

import com.mycompany.smartcity123.Models.Nader.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class HotelEditController {

    @FXML private TextField txtName;
    @FXML private TextField txtPhone;
    @FXML private TextField txtEmail;
    @FXML private TextField txtCity;
    @FXML private TextField txtMinPrice;
    @FXML private TextField txtMaxPrice;
    @FXML private TextField txtFeatures;
    @FXML private CheckBox chkPool;
    @FXML private CheckBox chkSpa;
    @FXML private TextField txtRooms;
    @FXML private CheckBox chkMeetingRoom;

    private StayPlace hotel;
    private HotelService hotelService;

    /** Injection de l’instance existante du service hôtel */
    public void setHotelService(HotelService service) {
        this.hotelService = service;
    }

    /** Injection de l’hôtel à éditer */
    public void setHotel(StayPlace hotel) {
        this.hotel = hotel;
        populateFields();
    }

    /** Remplit les champs selon le type d’hôtel */
    private void populateFields() {
        if (hotel == null) return;

        txtName.setText(hotel.getName());
        txtPhone.setText(hotel.getContact().phone());
        txtEmail.setText(hotel.getContact().email());
        txtCity.setText(hotel.getContact().city());
        txtMinPrice.setText(String.valueOf(hotel.getPricePlan().minPrice()));
        txtMaxPrice.setText(String.valueOf(hotel.getPricePlan().maxPrice()));

        if (hotel instanceof Resort resort) {
            txtFeatures.setText(resort.getAmenities().features());
            chkPool.setSelected(resort.getAmenities().hasPool());
            chkSpa.setSelected(resort.getAmenities().hasSpa());
        }

        if (hotel instanceof BusinessHotel business) {
            txtRooms.setText(String.valueOf(business.getRoomInfo().numberOfRooms()));
            chkMeetingRoom.setSelected(business.getRoomInfo().meetingRoom());
        }
    }

    /** Sauvegarde les changements */
    @FXML
    public void saveChanges() {
        try {
            String name = txtName.getText();
            ContactDetails contact = new ContactDetails(
                    txtPhone.getText(),
                    txtEmail.getText(),
                    txtCity.getText()
            );
            PricePlan price = new PricePlan(
                    Double.parseDouble(txtMinPrice.getText()),
                    Double.parseDouble(txtMaxPrice.getText())
            );

            StayPlace updatedHotel = null;

            if (hotel instanceof Resort) {
                AmenitiesPackage amenities = new AmenitiesPackage(
                        txtFeatures.getText(),
                        chkPool.isSelected(),
                        chkSpa.isSelected()
                );
                updatedHotel = new Resort(name, contact, price, amenities);
            } else if (hotel instanceof BusinessHotel) {
                RoomInfo roomInfo = new RoomInfo(
                        Integer.parseInt(txtRooms.getText()),
                        chkMeetingRoom.isSelected()
                );
                updatedHotel = new BusinessHotel(name, contact, price, roomInfo);
            }

            if (updatedHotel != null && hotelService != null) {
                hotelService.removeHotel(hotel.getName());
                hotelService.addHotel(updatedHotel);
                this.hotel = updatedHotel; // mise à jour de la référence locale
            }

            closeWindow();

        } catch (NumberFormatException e) {
            showAlert("Erreur", "Veuillez entrer des nombres valides pour les prix et le nombre de chambres.");
        } catch (Exception e) {
            showAlert("Erreur", e.getMessage());
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void closeWindow() {
        Stage stage = (Stage) txtName.getScene().getWindow();
        stage.close();
    }
}
