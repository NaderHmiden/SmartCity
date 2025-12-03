package com.mycompany.smartcity123.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class AttractionController {
    
    @FXML
    private Label messageLabel;
    
    @FXML
    private TextField nomField;
    
    @FXML
    private TextField typeField;
    
    @FXML
    private TextField localisationField;
    
    @FXML
    private ComboBox<String> categorieComboBox;
    
    @FXML
    private TableView<Attraction> attractionsTable;
    
    @FXML
    private TableColumn<Attraction, String> nomColumn;
    
    @FXML
    private TableColumn<Attraction, String> typeColumn;
    
    @FXML
    private TableColumn<Attraction, String> localisationColumn;
    
    @FXML
    private TableColumn<Attraction, String> categorieColumn;
    
    @FXML
    private Button ajouterButton;
    
    @FXML
    private Button supprimerButton;
    
    @FXML
    private Button modifierButton;
    
    private ObservableList<Attraction> attractionsList = FXCollections.observableArrayList();
    
    @FXML
    public void initialize() {
        System.out.println("Initialisation du contrôleur Attraction...");
        
        // Initialiser la ComboBox
        if (categorieComboBox != null) {
            categorieComboBox.getItems().addAll(
                "Touristique",
                "Culturelle",
                "Historique",
                "Divertissement",
                "Naturelle"
            );
        }
        
        // Configurer les colonnes de la TableView
        if (nomColumn != null && typeColumn != null && localisationColumn != null && categorieColumn != null) {
            nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
            typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
            localisationColumn.setCellValueFactory(new PropertyValueFactory<>("localisation"));
            categorieColumn.setCellValueFactory(new PropertyValueFactory<>("categorie"));
            
            attractionsTable.setItems(attractionsList);
        }
        
        // Ajouter quelques données de test
        attractionsList.add(new Attraction("Tour Eiffel", "Monument", "Paris", "Touristique"));
        attractionsList.add(new Attraction("Musée du Louvre", "Musée", "Paris", "Culturelle"));
        attractionsList.add(new Attraction("Parc des Buttes-Chaumont", "Parc", "Paris", "Naturelle"));
    }
    
    @FXML
    private void handleAjouterAttraction(ActionEvent event) {
        System.out.println("Ajout d'une attraction...");
        
        if (nomField == null || typeField == null || localisationField == null || categorieComboBox == null) {
            if (messageLabel != null) {
                messageLabel.setText("Erreur: Composants non initialisés");
            }
            return;
        }
        
        String nom = nomField.getText();
        String type = typeField.getText();
        String localisation = localisationField.getText();
        String categorie = categorieComboBox.getValue();
        
        if (nom.isEmpty() || type.isEmpty() || localisation.isEmpty() || categorie == null) {
            if (messageLabel != null) {
                messageLabel.setText("Veuillez remplir tous les champs !");
            }
            return;
        }
        
        // Ajouter à la liste
        attractionsList.add(new Attraction(nom, type, localisation, categorie));
        
        // Effacer les champs
        nomField.clear();
        typeField.clear();
        localisationField.clear();
        categorieComboBox.setValue(null);
        
        if (messageLabel != null) {
            messageLabel.setText("Attraction ajoutée avec succès !");
        }
    }
    
    @FXML
    private void handleSupprimerAttraction(ActionEvent event) {
        System.out.println("Suppression d'une attraction...");
        
        if (attractionsTable == null) {
            if (messageLabel != null) {
                messageLabel.setText("Erreur: Table non initialisée");
            }
            return;
        }
        
        Attraction selected = attractionsTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            attractionsList.remove(selected);
            if (messageLabel != null) {
                messageLabel.setText("Attraction supprimée !");
            }
        } else {
            if (messageLabel != null) {
                messageLabel.setText("Veuillez sélectionner une attraction !");
            }
        }
    }
    
    @FXML
    private void handleModifierAttraction(ActionEvent event) {
        System.out.println("Modification d'une attraction...");
        
        if (attractionsTable == null) {
            if (messageLabel != null) {
                messageLabel.setText("Erreur: Table non initialisée");
            }
            return;
        }
        
        Attraction selected = attractionsTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            if (nomField != null && !nomField.getText().isEmpty()) {
                selected.setNom(nomField.getText());
            }
            if (typeField != null && !typeField.getText().isEmpty()) {
                selected.setType(typeField.getText());
            }
            if (localisationField != null && !localisationField.getText().isEmpty()) {
                selected.setLocalisation(localisationField.getText());
            }
            if (categorieComboBox != null && categorieComboBox.getValue() != null) {
                selected.setCategorie(categorieComboBox.getValue());
            }
            
            // Rafraîchir la table
            attractionsTable.refresh();
            
            if (messageLabel != null) {
                messageLabel.setText("Attraction modifiée !");
            }
        } else {
            if (messageLabel != null) {
                messageLabel.setText("Veuillez sélectionner une attraction !");
            }
        }
    }
    
    @FXML
    private void handleSelectionTable() {
        if (attractionsTable == null) return;
        
        Attraction selected = attractionsTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            if (nomField != null) nomField.setText(selected.getNom());
            if (typeField != null) typeField.setText(selected.getType());
            if (localisationField != null) localisationField.setText(selected.getLocalisation());
            if (categorieComboBox != null) categorieComboBox.setValue(selected.getCategorie());
        }
    }
    
    // Classe interne pour représenter une attraction
    public static class Attraction {
        private String nom;
        private String type;
        private String localisation;
        private String categorie;
        
        public Attraction() {
            this("", "", "", "");
        }
        
        public Attraction(String nom, String type, String localisation, String categorie) {
            this.nom = nom;
            this.type = type;
            this.localisation = localisation;
            this.categorie = categorie;
        }
        
        // Getters
        public String getNom() { return nom; }
        public String getType() { return type; }
        public String getLocalisation() { return localisation; }
        public String getCategorie() { return categorie; }
        
        // Setters
        public void setNom(String nom) { this.nom = nom; }
        public void setType(String type) { this.type = type; }
        public void setLocalisation(String localisation) { this.localisation = localisation; }
        public void setCategorie(String categorie) { this.categorie = categorie; }
    }
}