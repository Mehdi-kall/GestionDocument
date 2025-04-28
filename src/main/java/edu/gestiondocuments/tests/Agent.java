package edu.gestiondocuments.tests;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.io.File;

public class Agent extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            // Test de chargement des ressources
            URL imageUrl = getClass().getResource("/images/");
            if (imageUrl != null) {
                File imageDir = new File(imageUrl.toURI());
                System.out.println("Images trouvées :");
                for (File file : imageDir.listFiles()) {
                    System.out.println(" - " + file.getName());
                }
            } else {
                System.out.println("Dossier images non trouvé");
            }

            // Charger le fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Agent.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/styles/agent.css").toExternalForm());

            primaryStage.setTitle("Interface Agent");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Erreur lors du chargement de l'interface: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}