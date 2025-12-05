package com.mycompany.smartcity123.Controller;

import com.mycompany.smartcity123.Models.Nader.model.HotelService;
import com.mycompany.smartcity123.Models.Nader.model.StayPlace;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.beans.property.SimpleStringProperty;

public class HotelController {

    @FXML private TableView<StayPlace> hotelTable;
    @FXML private TableColumn<StayPlace, String> colName;
    @FXML private TableColumn<StayPlace, String> colCategory;
    @FXML private TableColumn<StayPlace, String> colContact;

    private HotelService service;
    private final ObservableList<StayPlace> data = FXCollections.observableArrayList();

    public void setService(HotelService service) {
        this.service = service;
        loadHotels();
    }

    @FXML
    public void initialize() {
        colName.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getName()));
        colCategory.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getCategory().toString()));
        colContact.setCellValueFactory(c -> new SimpleStringProperty(
                c.getValue().getContact().phone() + " / " + c.getValue().getContact().email()
        ));
        hotelTable.setItems(data);
    }

    private void loadHotels() {
        if (service != null) {
            data.setAll(service.getAll());
        }
    }
}
