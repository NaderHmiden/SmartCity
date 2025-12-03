package com.mycompany.smartcity123;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        try {
            // Charger le fichier FXML principal
            Parent root = FXMLLoader.load(getClass().getResource("/com/mycompany/smartcity123/fxml/main.fxml"));
            
            Scene scene = new Scene(root);
            
            // Optionnel: ajouter une feuille de style CSS
            // scene.getStylesheets().add(getClass().getResource("/com/mycompany/smartcity123/styles.css").toExternalForm());
            
            primaryStage.setTitle("Smart City Management System");
            primaryStage.setScene(scene);
            primaryStage.setMaximized(true); // Optionnel: fenêtre maximisée
            primaryStage.show();
            
        } catch (IOException e) {
            System.err.println("Erreur lors du chargement du fichier FXML: " + e.getMessage());
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.err.println("Fichier FXML introuvable. Vérifiez le chemin.");
            e.printStackTrace();
        }
    }
    
    @Override
    public void init() {
        // Code d'initialisation (optionnel)
        System.out.println("Initialisation de l'application SmartCity...");
    }
    
    @Override
    public void stop() {
        // Code de nettoyage (optionnel)
        System.out.println("Arrêt de l'application SmartCity...");
    }

    public static void main(String[] args) {
        System.out.println("Démarrage de l'application SmartCity...");
        
        // Vérifier que JavaFX est disponible
        try {
            Class.forName("javafx.application.Application");
            launch(args);
        } catch (ClassNotFoundException e) {
            System.err.println("Erreur: JavaFX n'est pas disponible dans le classpath.");
            System.err.println("Assurez-vous d'utiliser un JDK qui inclut JavaFX (comme JDK 11+ avec JavaFX)");
            System.exit(1);
        }
    }
}