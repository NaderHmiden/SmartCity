package com.mycompany.smartcity123.Controller.Aziz;

import com.mycompany.smartcity123.Models.Aziz.model.Attraction;
import com.mycompany.smartcity123.Models.Aziz.model.AttractionService;
import com.mycompany.smartcity123.Models.Aziz.model.Place;
import com.mycompany.smartcity123.Models.Mazen.model.RestaurantService;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.Node;

import java.io.IOException;

public class AttractionController {

    @FXML private TableView<Attraction> attractionTable;
    @FXML private TableColumn<Attraction, String> colName;
    @FXML private TableColumn<Attraction, String> colCategory;
    @FXML private TableColumn<Attraction, Double> colPrice;
private final AttractionService service = AttractionService.getInstance();
    @FXML
    public void initialize() {
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCategory.setCellValueFactory(cell ->
                new javafx.beans.property.SimpleStringProperty(cell.getValue().getCategory().toString()));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        // Utilisation du singleton avec ObservableList
        attractionTable.setItems(AttractionService.getInstance().getAllAttractions());
    }

    @FXML
    private void goToAttractionAdd(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/attraction-add.fxml"));

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Ajouter Attraction");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    };
    @FXML
    private void deleteAttraction(ActionEvent event) {
        Place selected = attractionTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            service.removeAttraction(selected.getId());
        }
    }

    
}
