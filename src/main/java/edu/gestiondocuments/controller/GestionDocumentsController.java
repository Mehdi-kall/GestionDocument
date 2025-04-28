package edu.gestiondocuments.controller;

import edu.gestiondocuments.entities.Documents;
import edu.gestiondocuments.services.ServiceDocuments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class GestionDocumentsController {
    private final ServiceDocuments service = new ServiceDocuments();
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
    @FXML private VBox formPane;
    @FXML private TextField titreField;
    @FXML private TextArea descriptionField;
    @FXML private TextField urlField;
    @FXML private TextField tagsField;

    @FXML
    public void initialize() {
        setupTableColumns();
        chargerDocuments();
        setupTableSelection();
    }

    private void setupTableColumns() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("idDocument"));
        titreColumn.setCellValueFactory(new PropertyValueFactory<>("titreDocument"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("descriptionDocument"));
        dateCreationColumn.setCellValueFactory(new PropertyValueFactory<>("dateCreationDocument"));
        dateModificationColumn.setCellValueFactory(new PropertyValueFactory<>("dateModificationDocument"));
        urlColumn.setCellValueFactory(new PropertyValueFactory<>("urlDocument"));
        tagsColumn.setCellValueFactory(new PropertyValueFactory<>("tagsDocuments"));
    }

    private void setupTableSelection() {
        documentsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                documentEnEdition = newSelection;
                afficherDocument(newSelection);
            }
        });
    }

    private void chargerDocuments() {
        List<Documents> documents = service.getAllDocuments();
        ObservableList<Documents> observableDocuments = FXCollections.observableArrayList(documents);
        documentsTable.setItems(observableDocuments);
    }

    @FXML
    private void handleAjouter() {
        documentEnEdition = null;
        viderFormulaire();
        formPane.setVisible(true);
    }

    @FXML
    private void handleModifier() {
        Documents selected = documentsTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Erreur", "Veuillez sélectionner un document à modifier");
            return;
        }
        documentEnEdition = selected;
        afficherDocument(selected);
        formPane.setVisible(true);
    }

    @FXML
    private void handleSupprimer() {
        Documents selected = documentsTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Erreur", "Veuillez sélectionner un document à supprimer");
            return;
        }

        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Confirmation de suppression");
        confirmation.setContentText("Voulez-vous vraiment supprimer ce document ?");
        
        confirmation.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                service.supprimerDocument(selected.getIdDocument());
                chargerDocuments();
            }
        });
    }

    @FXML
    private void handleRechercher() {
        String critere = searchField.getText();
        List<Documents> resultats = service.rechercherDocument(critere);
        documentsTable.setItems(FXCollections.observableArrayList(resultats));
    }

    @FXML
    private void handleSave() {
        if (!validateForm()) {
            return;
        }

        Documents doc = new Documents();
        if (documentEnEdition != null) {
            doc.setIdDocument(documentEnEdition.getIdDocument());
            doc.setDateCreationDocument(documentEnEdition.getDateCreationDocument());
        } else {
            doc.setDateCreationDocument(LocalDateTime.now());
        }

        doc.setTitreDocument(titreField.getText());
        doc.setDescriptionDocument(descriptionField.getText());
        doc.setUrlDocument(urlField.getText());
        doc.setDateModificationDocument(LocalDateTime.now());
        
        String tagsText = tagsField.getText();
        if (!tagsText.isEmpty()) {
            doc.setTagsDocuments(Arrays.asList(tagsText.split(",")));
        }

        if (documentEnEdition != null) {
            service.modifierDocument(doc);
        } else {
            service.ajouterDocument(doc);
        }

        chargerDocuments();
        formPane.setVisible(false);
        viderFormulaire();
    }

    @FXML
    private void handleCancel() {
        formPane.setVisible(false);
        viderFormulaire();
    }

    private void afficherDocument(Documents doc) {
        titreField.setText(doc.getTitreDocument());
        descriptionField.setText(doc.getDescriptionDocument());
        urlField.setText(doc.getUrlDocument());
        tagsField.setText(String.join(",", doc.getTagsDocuments()));
        formPane.setVisible(true);
    }

    private void viderFormulaire() {
        titreField.clear();
        descriptionField.clear();
        urlField.clear();
        tagsField.clear();
    }

    private boolean validateForm() {
        if (titreField.getText().trim().isEmpty()) {
            showAlert("Erreur", "Le titre est obligatoire");
            return false;
        }
        return true;
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
