package edu.gestiondocuments.services;

import edu.gestiondocuments.entities.Documents;
import edu.gestiondocuments.interfaces.IServiceDocument;
import edu.gestiondocuments.tools.MyConnection;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ServiceDocuments implements IServiceDocument {
    private Connection connection;

    public ServiceDocuments() {
        this.connection = MyConnection.getInstance().getConnection();
    }

    @Override
    public void ajouterDocument(Documents document) {
        String sql = "INSERT INTO documents (titre_document, description_document, date_creation_document, " +
                "date_modification_document, url_document, tags_documents) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setString(1, document.getTitreDocument());
            pst.setString(2, document.getDescriptionDocument());
            pst.setTimestamp(3, Timestamp.valueOf(document.getDateCreationDocument()));
            
            Timestamp dateModif = null;
            if (document.getDateModificationDocument() != null) {
                dateModif = Timestamp.valueOf(document.getDateModificationDocument());
            }
            pst.setTimestamp(4, dateModif);
            
            pst.setString(5, document.getUrlDocument());
            
            String tagsStr = null;
            if (document.getTagsDocuments() != null && !document.getTagsDocuments().isEmpty()) {
                tagsStr = String.join(",", document.getTagsDocuments());
            }
            pst.setString(6, tagsStr);
            
            pst.executeUpdate();
            System.out.println("Document ajouté avec succès!");
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout du document: " + e.getMessage());
        }
    }

    @Override
    public void modifierDocument(Documents document) {
        List<Documents> existingDocs = rechercherDocumentParId(document.getIdDocument());
        if (existingDocs.isEmpty()) {
            System.out.println("Document non trouvé.");
            return;
        }
        Documents existingDoc = existingDocs.get(0);

        String sql = "UPDATE documents SET titre_document=?, description_document=?, " +
                "date_modification_document=?, url_document=?, tags_documents=? WHERE id_document=?";
        
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setString(1, document.getTitreDocument() != null ?
                document.getTitreDocument() : existingDoc.getTitreDocument());
            pst.setString(2, document.getDescriptionDocument() != null ? 
                document.getDescriptionDocument() : existingDoc.getDescriptionDocument());
            pst.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            pst.setString(4, document.getUrlDocument() != null ? 
                document.getUrlDocument() : existingDoc.getUrlDocument());
            
            String tagsStr = null;
            if (document.getTagsDocuments() != null && !document.getTagsDocuments().isEmpty()) {
                tagsStr = String.join(",", document.getTagsDocuments());
            } else if (existingDoc.getTagsDocuments() != null && !existingDoc.getTagsDocuments().isEmpty()) {
                tagsStr = String.join(",", existingDoc.getTagsDocuments());
            }
            pst.setString(5, tagsStr);
            
            pst.setInt(6, document.getIdDocument());
            
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Document modifié avec succès!");
            } else {
                System.out.println("Aucun document trouvé avec cet ID.");
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la modification du document: " + e.getMessage());
        }
    }

    @Override
    public void supprimerDocument(int idDocument) {
        String sql = "DELETE FROM documents WHERE id_document=?";
        
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setInt(1, idDocument);
            
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Document supprimé avec succès!");
            } else {
                System.out.println("Aucun document trouvé avec cet ID.");
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression du document: " + e.getMessage());
        }
    }

    @Override
    public List<Documents> rechercherDocument(String critere) {
        List<Documents> documents = new ArrayList<>();
        String sql = "SELECT * FROM documents WHERE titre_document LIKE ? OR description_document LIKE ? OR tags_documents LIKE ?";
        
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            String searchPattern = "%" + critere + "%";
            pst.setString(1, searchPattern);
            pst.setString(2, searchPattern);
            pst.setString(3, searchPattern);
            
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                documents.add(extractDocumentFromResultSet(rs));
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la recherche de documents: " + e.getMessage());
        }
        
        return documents;
    }

    @Override
    public List<Documents> rechercherDocumentParId(int id) {
        List<Documents> documents = new ArrayList<>();
        String sql = "SELECT * FROM documents WHERE id_document = ?";
        
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setInt(1, id);
            
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                documents.add(extractDocumentFromResultSet(rs));
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la recherche du document par ID: " + e.getMessage());
        }
        
        return documents;
    }

    @Override
    public List<Documents> rechercherDocumentParTag(String tag) {
        List<Documents> documents = new ArrayList<>();
        String sql = "SELECT * FROM documents WHERE tags_documents LIKE ?";
        
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setString(1, "%" + tag + "%");
            
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                documents.add(extractDocumentFromResultSet(rs));
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la recherche du document par tag: " + e.getMessage());
        }
        
        return documents;
    }

    private Documents extractDocumentFromResultSet(ResultSet rs) throws SQLException {
        Documents doc = new Documents();
        doc.setIdDocument(rs.getInt("id_document"));
        doc.setTitreDocument(rs.getString("titre_document"));
        doc.setDescriptionDocument(rs.getString("description_document"));
        doc.setDateCreationDocument(rs.getTimestamp("date_creation_document").toLocalDateTime());
        
        Timestamp dateModif = rs.getTimestamp("date_modification_document");
        if (dateModif != null) {
            doc.setDateModificationDocument(dateModif.toLocalDateTime());
        }
        
        doc.setUrlDocument(rs.getString("url_document"));
        
        String tags = rs.getString("tags_documents");
        if (tags != null && !tags.isEmpty()) {
            doc.setTagsDocuments(Arrays.asList(tags.split(",")));
        } else {
            doc.setTagsDocuments(new ArrayList<>());
        }
        
        return doc;
    }

    // Méthode utilitaire pour récupérer tous les documents
    public List<Documents> getAllDocuments() {
        return rechercherDocument("");
    }
}