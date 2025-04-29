package edu.gestiondocuments.controller;

import edu.gestiondocuments.entities.Documents;
import edu.gestiondocuments.services.ServiceDocuments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class AgentMenuController implements Initializable {
    private final ServiceDocuments serviceDocuments = new ServiceDocuments();
    private Documents documentEnEdition = null;

    @FXML private TableView<Documents> documentsTable;
    @FXML private TableColumn<Documents, Integer> idColumn;
    @FXML private TableColumn<Documents, String> titreColumn;
    @FXML private TableColumn<Documents, String> descriptionColumn;
    @FXML private TableColumn<Documents, LocalDateTime> dateCreationColumn;
    @FXML private TableColumn<Documents, LocalDateTime> dateModificationColumn;
    @FXML private TableColumn<Documents, String> urlColumn;
    @FXML private TableColumn<Documents, List<String>> tagsColumn;
    
    @FXML private TextField searchField;
    @FXML private ComboBox<String> searchTypeCombo;
    @FXML private VBox formPane;
    @FXML private TextField titreField;
    @FXML private TextArea descriptionField;
    @FXML private TextField urlField;
    @FXML private TextField tagsField;
    @FXML private AnchorPane drawerPane;
    @FXML private AnchorPane opacityPane;
    @FXML private AnchorPane paneDocuments;
    @FXML private AnchorPane paneRDV;
    @FXML private AnchorPane paneAffectation;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Configuration des colonnes
        idColumn.setCellValueFactory(new PropertyValueFactory<>("idDocument"));
        titreColumn.setCellValueFactory(new PropertyValueFactory<>("titreDocument"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("descriptionDocument"));
        dateCreationColumn.setCellValueFactory(new PropertyValueFactory<>("dateCreationDocument"));
        dateModificationColumn.setCellValueFactory(new PropertyValueFactory<>("dateModificationDocument"));
        urlColumn.setCellValueFactory(new PropertyValueFactory<>("urlDocument"));
        tagsColumn.setCellValueFactory(new PropertyValueFactory<>("tagsDocuments"));

        // Configuration du ComboBox de recherche
        searchTypeCombo.setItems(FXCollections.observableArrayList(
            "Titre/Description", "Tags"
        ));
        searchTypeCombo.getSelectionModel().selectFirst();

        // Chargement initial des documents
        refreshDocuments();

        // Configuration de la sélection
        documentsTable.getSelectionModel().selectedItemProperty().addListener(
            (obs, oldSelection, newSelection) -> {
                if (newSelection != null) {
                    showDocumentDetails(newSelection);
                }
            }
        );
    }

    @FXML
    private void handleNouveauDocument() {
        documentEnEdition = null;
        clearForm();
        formPane.setVisible(true);
    }

    @FXML
    private void handleRechercher() {
        String searchText = searchField.getText().trim();
        String searchType = searchTypeCombo.getValue();

        List<Documents> results;
        if (searchType.equals("Tags")) {
            results = serviceDocuments.rechercherDocumentParTag(searchText);
        } else {
            results = serviceDocuments.rechercherDocument(searchText);
        }

        documentsTable.setItems(FXCollections.observableArrayList(results));
    }

    @FXML
    private void handleSave() {
        if (!validateForm()) {
            return;
        }

        Documents document = documentEnEdition != null ? 
            documentEnEdition : new Documents();

        document.setTitreDocument(titreField.getText());
        document.setDescriptionDocument(descriptionField.getText());
        document.setUrlDocument(urlField.getText());
        document.setTagsDocuments(Arrays.asList(tagsField.getText().split(",")));

        if (documentEnEdition == null) {
            document.setDateCreationDocument(LocalDateTime.now());
            serviceDocuments.ajouterDocument(document);
        } else {
            document.setDateModificationDocument(LocalDateTime.now());
            serviceDocuments.modifierDocument(document);
        }

        refreshDocuments();
        clearForm();
        formPane.setVisible(false);
    }

    @FXML
    private void handleCancel() {
        clearForm();
        formPane.setVisible(false);
    }

    private void showDocumentDetails(Documents document) {
        documentEnEdition = document;
        titreField.setText(document.getTitreDocument());
        descriptionField.setText(document.getDescriptionDocument());
        urlField.setText(document.getUrlDocument());
        tagsField.setText(String.join(",", document.getTagsDocuments()));
        formPane.setVisible(true);
    }

    private void clearForm() {
        documentEnEdition = null;
        titreField.clear();
        descriptionField.clear();
        urlField.clear();
        tagsField.clear();
    }

    private boolean validateForm() {
        String titre = titreField.getText().trim();
        if (titre.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Erreur de validation", 
                     "Le titre est obligatoire.");
            return false;
        }
        return true;
    }

    private void refreshDocuments() {
        List<Documents> documents = serviceDocuments.getAllDocuments();
        documentsTable.setItems(FXCollections.observableArrayList(documents));
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
    public void BurgerClicked(MouseEvent mouseEvent) {
        if (!drawerPane.isVisible()) {
            drawerPane.setVisible(true);
            opacityPane.setVisible(true);
        } else {
            drawerPane.setVisible(false);
            opacityPane.setVisible(false);
        }
    }

    @FXML
    private void MouseEntred() {
        drawerPane.setVisible(true);
        opacityPane.setVisible(true);
    }

    @FXML
    private void MouseExited() {
        drawerPane.setVisible(false);
        opacityPane.setVisible(false);
    }

    @FXML
    private void DocumentsClicked() {
        paneDocuments.setVisible(true);
        paneRDV.setVisible(false);
        paneAffectation.setVisible(false);
        drawerPane.setVisible(false);
        opacityPane.setVisible(false);
    }

    @FXML
    private void AffectationClicked() {
        paneDocuments.setVisible(false);
        paneRDV.setVisible(false);
        paneAffectation.setVisible(true);
        drawerPane.setVisible(false);
        opacityPane.setVisible(false);
    }

    @FXML
    private void Render_Vous() {
        paneDocuments.setVisible(false);
        paneRDV.setVisible(true);
        paneAffectation.setVisible(false);
        drawerPane.setVisible(false);
        opacityPane.setVisible(false);
    }

    @FXML
    private void GestionDemande() {
        // À implémenter selon les besoins
        drawerPane.setVisible(false);
        opacityPane.setVisible(false);
    }

    @FXML
    private void Reclamation() {
        // À implémenter selon les besoins
        drawerPane.setVisible(false);
        opacityPane.setVisible(false);
    }

    @FXML
    private void Logout() {
//        // Implémenter la logique de déconnexion
//        Stage stage = (Stage) drawerPane.getScene().getWindow();
//        stage.close();
    }

    @FXML
    private void handleSupprimer() {
        Documents selectedDocument = documentsTable.getSelectionModel().getSelectedItem();
        if (selectedDocument == null) {
            showAlert(Alert.AlertType.WARNING, "Attention", 
                     "Veuillez sélectionner un document à supprimer.");
            return;
        }

        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Confirmation de suppression");
        confirmation.setHeaderText(null);
        confirmation.setContentText("Êtes-vous sûr de vouloir supprimer le document \"" + 
                                  selectedDocument.getTitreDocument() + "\" ?");

        confirmation.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {
                    serviceDocuments.supprimerDocument(selectedDocument.getIdDocument());
                    refreshDocuments();
                    showAlert(Alert.AlertType.INFORMATION, "Succès", 
                             "Le document a été supprimé avec succès.");
                } catch (Exception e) {
                    showAlert(Alert.AlertType.ERROR, "Erreur", 
                             "Une erreur est survenue lors de la suppression : " + e.getMessage());
                }
            }
        });
    }


}

