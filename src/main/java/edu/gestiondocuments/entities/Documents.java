package edu.gestiondocuments.entities;

import javax.swing.text.html.HTML;
import java.time.LocalDateTime;
import java.util.List;

public class Documents {
    private int idDocument; // Clé primaire auto-incrémentée
    private String titreDocument; //Obligatoire
    private String descriptionDocument; //pas obligatoire
    private LocalDateTime dateCreationDocument; //obligatoire generer au moment de la creation
    private LocalDateTime dateModificationDocument; //pas obligatoire generer au moment de la modification
    private String urlDocument; // pas obligatoire
    private List<String> tagsDocuments; //pas obligatoire
    public Documents() {
    }
    //constructeur complet
    public Documents( String titreDocument, String descriptionDocument, LocalDateTime dateCreationDocument, LocalDateTime dateModificationDocument, String urlDocument, List<String> tagsDocuments) {
        this.titreDocument = titreDocument;
        this.descriptionDocument = descriptionDocument;
        this.dateCreationDocument = dateCreationDocument;
        this.dateModificationDocument = dateModificationDocument;
        this.urlDocument = urlDocument;
        this.tagsDocuments = tagsDocuments;
    }

    public int getIdDocument() {
        return idDocument;
    }

    public void setIdDocument(int idDocument) {
        this.idDocument = idDocument;
    }

    public String getTitreDocument() {
        return titreDocument;
    }

    public void setTitreDocument(String titreDocument) {
        this.titreDocument = titreDocument;
    }

    public String getDescriptionDocument() {
        return descriptionDocument;
    }

    public void setDescriptionDocument(String descriptionDocument) {
        this.descriptionDocument = descriptionDocument;
    }

    public LocalDateTime getDateCreationDocument() {
        return dateCreationDocument;
    }

    public void setDateCreationDocument(LocalDateTime dateCreationDocument) {
        this.dateCreationDocument = dateCreationDocument;
    }

    public LocalDateTime getDateModificationDocument() {
        return dateModificationDocument;
    }

    public void setDateModificationDocument(LocalDateTime dateModificationDocument) {
        this.dateModificationDocument = dateModificationDocument;
    }

    public String getUrlDocument() {
        return urlDocument;
    }

    public void setUrlDocument(String urlDocument) {
        this.urlDocument = urlDocument;
    }

    public List<String> getTagsDocuments() {
        return tagsDocuments;
    }

    public void setTagsDocuments(List<String> tagsDocuments) {
        this.tagsDocuments = tagsDocuments;
    }
}
