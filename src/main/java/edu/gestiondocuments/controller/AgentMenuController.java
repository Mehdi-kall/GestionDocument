package edu.gestiondocuments.controller;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AgentMenuController implements Initializable {
    private boolean drawerOuvert = false;
    @FXML
    private AnchorPane opacityPane, drawerPane, paneDocuments, paneAffectation;

    @FXML
    private Label drawerImage, LabelLateral, LabelFermiture;

    @FXML
    private ImageView exit;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        opacityPane.setVisible(false);


        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), opacityPane);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();

        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.5), drawerPane);
        translateTransition.setByX(-600);
        translateTransition.play();


        drawerOuvert = false;


        drawerImage.setOnMouseClicked(event -> {
            if (!drawerOuvert) {
                ouvrirMenu();
            } else {
                fermerMenu();
            }
        });


        opacityPane.setOnMouseClicked(event -> {
            fermerMenu();
        });
    }


    private void ouvrirMenu() {
        opacityPane.setVisible(true);


        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), opacityPane);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(0.15);
        fadeTransition.play();


        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.5), drawerPane);
        translateTransition.setToX(0); // Aller à 0 (ouvrir le menu)
        translateTransition.play();


        drawerOuvert = true;
    }


    private void fermerMenu() {
        // Animation de fade pour opacityPane
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), opacityPane);
        fadeTransition.setFromValue(0.15);
        fadeTransition.setToValue(0);
        fadeTransition.play();


        fadeTransition.setOnFinished(event -> {
            opacityPane.setVisible(false);
        });

        // Animation de translation pour drawerPane
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.5), drawerPane);
        translateTransition.setToX(-600);
        translateTransition.play();


        drawerOuvert = false;
    }

    public void DocumentsClicked(ActionEvent actionEvent) {
        paneDocuments.setVisible(true);
        paneAffectation.setVisible(false);
        fermerMenu();
    }

    public void AffectationClicked(ActionEvent actionEvent) {
        paneDocuments.setVisible(false);
        paneAffectation.setVisible(true);
        fermerMenu();
    }

    public void MouseEntred(MouseEvent mouseEvent) {
        if (!drawerOuvert) {
            ouvrirMenu();
        }
    }

    public void MouseExited(MouseEvent mouseEvent) {
        if(drawerOuvert){
            fermerMenu();
        }
    }
}

