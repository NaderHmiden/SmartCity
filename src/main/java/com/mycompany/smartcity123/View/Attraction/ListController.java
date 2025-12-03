package com.mycompany.smartcity123.View.Attraction;

import com.mycompany.smartcity123.Models.Aziz.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class ListController {

    @FXML
    private TableView<Attraction> tableAttractions;

    @FXML
    private TableColumn<Attraction, String> colName, colCity, colType;

    @FXML
    private TableColumn<Attraction, Double> colPrice;

    @FXML
    private Button btnAdd, btnEdit, btnDelete, btnDetails;

    private final AttractionService service = new AttractionService();
    private final ObservableList<Attraction> data = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        setupColumns();
        loadData();
        setupButtons();
    }

    private void setupColumns() {
        colName.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getName()));
        colCity.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getAddress().city()));
        colType.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getCategory().name()));
        colPrice.setCellValueFactory(c -> new javafx.beans.property.SimpleObjectProperty<>(c.getValue().getPrice()));
    }

    private void loadData() {
        List<Attraction> list = service.getAllAttractions();
        data.setAll(list);
        tableAttractions.setItems(data);
    }

    private void setupButtons() {
        btnAdd.setOnAction(e -> openAddWindow());
        btnEdit.setOnAction(e -> openEditWindow());
        btnDetails.setOnAction(e -> openDetailsWindow());
        btnDelete.setOnAction(e -> deleteAttraction());
    }

    // --------------- ACTIONS ---------------- //

    private void openAddWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/smartcity123/View/Attraction/AddAttraction.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Ajouter Attraction");
            stage.showAndWait();
            loadData(); // refresh table
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void openEditWindow() {
        Attraction selected = tableAttractions.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/smartcity123/View/Attraction/EditAttraction.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            EditAttractionController controller = loader.getController();
            controller.setAttraction(selected);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Modifier Attraction");
            stage.showAndWait();
            loadData(); // refresh table
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void openDetailsWindow() {
        Attraction selected = tableAttractions.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/smartcity123/View/Attraction/DetailsAttraction.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            DetailsAttractionController controller = loader.getController();
            controller.setAttraction(selected);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Détails Attraction");
            stage.showAndWait();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void deleteAttraction() {
        Attraction selected = tableAttractions.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Supprimer " + selected.getName() + " ?", ButtonType.YES, ButtonType.NO);
        confirm.showAndWait().ifPresent(button -> {
            if (button == ButtonType.YES) {
                service.removeAttraction(selected.getId());
                data.remove(selected);
            }
        });
    }
}
