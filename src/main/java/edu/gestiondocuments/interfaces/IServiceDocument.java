package edu.gestiondocuments.interfaces;

import edu.gestiondocuments.entities.Documents;
import java.util.List;

public interface IServiceDocument {
    void ajouterDocument(Documents document);
    void modifierDocument(Documents document);
    void supprimerDocument(int idDocument);
    List<Documents> rechercherDocument(String critere);
    List<Documents> rechercherDocumentParId(int id);
    List<Documents> rechercherDocumentParTag(String tag);
}
