package edu.gestiondocuments.tests;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class AgentMenu extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            System.out.println("Démarrage de l'application AgentMenu...");

            System.out.println("Chargement du fichier FXML: /AgentMenu.fxml");
            URL fxmlUrl = getClass().getResource("/AgentMenu.fxml");
            if (fxmlUrl == null) {
                throw new IOException("Impossible de trouver le fichier FXML: /AgentMenu.fxml");
            }
            System.out.println("URL du fichier FXML: " + fxmlUrl);

            FXMLLoader loader = new FXMLLoader(fxmlUrl);
            System.out.println("Chargement du contenu FXML...");
            Parent root = loader.load();
            System.out.println("Contenu FXML chargé avec succès.");

            Scene scene = new Scene(root);

            URL cssUrl = getClass().getResource("/styles/agent.css");
            if (cssUrl == null) {
                System.out.println("Attention: Impossible de trouver la feuille de style: /styles/agent.css");
            } else {
                System.out.println("Chargement de la feuille de style: " + cssUrl);
               scene.getStylesheets().add(cssUrl.toExternalForm());
            }


            primaryStage.setTitle("Menu Agent");
            primaryStage.getIcons().add(new javafx.scene.image.Image(getClass().getResource("/images/logo.jpg").toExternalForm()));
            primaryStage.setScene(scene);

            // Définir les dimensions de la fenêtre
            primaryStage.setWidth(1000);
            primaryStage.setHeight(700);


            primaryStage.setResizable(false);


            System.out.println("Affichage de la fenêtre principale...");
            primaryStage.show();
            System.out.println("Application démarrée avec succès.");

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Erreur lors du chargement de l'interface: " + e.getMessage());

            showErrorAlert("Erreur de l'application", "Impossible de démarrer l'application", e.getMessage());
            Platform.exit();
        }
    }


    private void showErrorAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        try {
            System.out.println("Lancement de l'application AgentMenu...");
            launch(args);
        } catch (Exception e) {
            System.err.println("Erreur de lancement: " + e.getMessage());
            e.printStackTrace();
        }
    }
}