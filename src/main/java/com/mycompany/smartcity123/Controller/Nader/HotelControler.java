package com.mycompany.smartcity123.Controller.Nader;

import com.mycompany.smartcity123.Models.Nader.model.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class HotelControler {

    @FXML private TableView<StayPlace> hotelTable;
    @FXML private TableColumn<StayPlace, String> colName;
    @FXML private TableColumn<StayPlace, String> colCategory;
    @FXML private TableColumn<StayPlace, String> colPrice;
    @FXML private TableColumn<StayPlace, String> colCity;

    @FXML private TextField searchField;
    @FXML private ComboBox<HotelCategory> categoryFilter;

    @FXML private Button btnAdd;
    @FXML private Button btnEdit;
    @FXML private Button btnDelete;
    @FXML private Button btnDetails;

    private final HotelService service = HotelService.getInstance();

    @FXML
    public void initialize() {
        colName.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getName()));
        colCategory.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getCategory().name()));
        colPrice.setCellValueFactory(c -> new SimpleStringProperty(
                c.getValue().getPricePlan().minPrice() + " - " +
                c.getValue().getPricePlan().maxPrice()
        ));
        colCity.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getAddress()));

        hotelTable.setItems(FXCollections.observableArrayList(service.getAll()));

        categoryFilter.getItems().setAll(HotelCategory.values());
    }

    @FXML
    private void filter(ActionEvent event) {
        String keyword = searchField.getText().trim();
        HotelCategory selected = categoryFilter.getValue();

        List<StayPlace> filtered = service.getAll().stream()
                .filter(h -> keyword.isEmpty() || h.getName().toLowerCase().contains(keyword.toLowerCase()))
                .filter(h -> selected == null || h.getCategory() == selected)
                .collect(Collectors.toList());

        hotelTable.setItems(FXCollections.observableArrayList(filtered));
    }

    @FXML
    private void addHotel(ActionEvent event) {
        openView(event, "/fxml/hotel-add.fxml", "Ajouter Hôtel");
    }

    @FXML
    private void editHotel(ActionEvent event) {
        StayPlace selected = hotelTable.getSelectionModel().getSelectedItem();
        if (selected == null) return;
        // Tu peux passer l’hôtel sélectionné au controller d’édition via FXMLLoader
        openView(event, "/fxml/hotel-edit.fxml", "Modifier Hôtel");
    }

    @FXML
    private void deleteHotel(ActionEvent event) {
        StayPlace selected = hotelTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            service.removeHotel(selected.getName());
            hotelTable.setItems(FXCollections.observableArrayList(service.getAll()));
        }
    }

    @FXML
    private void showDetails(ActionEvent event) {
        StayPlace selected = hotelTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            openView(event, "/fxml/hotel-details.fxml", "Détails Hôtel");
        }
    }

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
