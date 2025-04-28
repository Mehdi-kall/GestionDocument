package edu.gestiondocuments.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class AgentController {
    @FXML
    private VBox contentArea;
    
    @FXML
    private VBox accueilSection;

    @FXML
    private void handleNouveauDocument() {
        // Implémenter la création d'un nouveau document
    }

    @FXML
    private void handleQuitter() {
        System.exit(0);
    }

    @FXML
    private void afficherAccueil() {
        accueilSection.setVisible(true);
        // Cacher les autres sections si nécessaire
    }

    @FXML
    private void afficherRendezVous() {
        // Implémenter l'affichage des rendez-vous
    }

    @FXML
    private void handleGererDocuments() {
        // Implémenter la gestion des documents
    }
}