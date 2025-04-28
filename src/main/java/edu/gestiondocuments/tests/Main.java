package edu.gestiondocuments.tests;

import edu.gestiondocuments.entities.Documents;
import edu.gestiondocuments.services.ServiceDocuments;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ServiceDocuments service = new ServiceDocuments();

    public static void main(String[] args) {
        boolean continuer = true;
        while (continuer) {
            afficherMenu();
            int choix = scanner.nextInt();
            scanner.nextLine(); // Vider le buffer

            switch (choix) {
                case 1 -> ajouterDocument();
                case 2 -> modifierDocument();
                case 3 -> supprimerDocument();
                case 4 -> rechercherDocument();
                case 5 -> afficherTousLesDocuments();
                case 0 -> continuer = false;
                default -> System.out.println("Choix invalide!");
            }
        }
        System.out.println("Au revoir!");
        scanner.close();
    }

    private static void afficherMenu() {
        System.out.println("\n=== Gestion des Documents ===");
        System.out.println("1. Ajouter un document");
        System.out.println("2. Modifier un document");
        System.out.println("3. Supprimer un document");
        System.out.println("4. Rechercher des documents");
        System.out.println("5. Afficher tous les documents");
        System.out.println("0. Quitter");
        System.out.print("Votre choix : ");
    }

    private static void ajouterDocument() {
        System.out.println("\n=== Ajout d'un nouveau document ===");

        System.out.print("Titre : ");
        String titre = scanner.nextLine();

        System.out.print("Description : ");
        String description = scanner.nextLine();

        System.out.print("URL : ");
        String url = scanner.nextLine();

        System.out.print("Tags (séparés par des virgules) : ");
        String tagsInput = scanner.nextLine();
        List<String> tags = new ArrayList<>();
        if (!tagsInput.trim().isEmpty()) {
            tags = Arrays.asList(tagsInput.split(","));
        }

        Documents doc = new Documents(
            titre,
            description,
            LocalDateTime.now(),
            null,
            url,
            tags
        );

        service.ajouterDocument(doc);
    }

    private static void modifierDocument() {
        System.out.println("\n=== Modification d'un document ===");
        System.out.println("Choisir le critère de recherche du document à modifier :");
        System.out.println("1. Par ID");
        System.out.println("2. Par Titre");
        System.out.println("3. Par Tag");
        System.out.print("Votre choix : ");

        int choixRecherche = scanner.nextInt();
        scanner.nextLine(); // Vider le buffer

        Documents documentAModifier = null;

        switch (choixRecherche) {
            case 1 -> {
                System.out.print("Entrez l'ID du document : ");
                int id = scanner.nextInt();
                scanner.nextLine(); // Vider le buffer
                List<Documents> docs = service.rechercherDocumentParId(id);
                if (!docs.isEmpty()) {
                    documentAModifier = docs.get(0);
                }
            }
            case 2 -> {
                System.out.print("Entrez le titre du document : ");
                String titre = scanner.nextLine();
                List<Documents> docs = service.rechercherDocument(titre);
                documentAModifier = selectionnerDocument(docs);
            }
            case 3 -> {
                System.out.print("Entrez le tag : ");
                String tag = scanner.nextLine();
                List<Documents> docs = service.rechercherDocumentParTag(tag);
                documentAModifier = selectionnerDocument(docs);
            }
            default -> {
                System.out.println("Choix invalide!");
                return;
            }
        }

        if (documentAModifier == null) {
            System.out.println("Aucun document trouvé.");
            return;
        }

        // Afficher le document avant modification
        System.out.println("\nDocument à modifier :");
        afficherDocument(documentAModifier);

        // Demander les nouvelles informations
        System.out.print("\nNouveau titre : ");
        String titre = scanner.nextLine();

        System.out.print("Nouvelle description : ");
        String description = scanner.nextLine();

        System.out.print("Nouvelle URL : ");
        String url = scanner.nextLine();

        System.out.print("Nouveaux tags (séparés par des virgules) : ");
        String tagsInput = scanner.nextLine();
        List<String> tags = new ArrayList<>();
        if (!tagsInput.trim().isEmpty()) {
            tags = Arrays.asList(tagsInput.split(","));
        }

        Documents doc = new Documents(
            titre,
            description,
            documentAModifier.getDateCreationDocument(), // Garder la date de création originale
            LocalDateTime.now(), // Nouvelle date de modification
            url,
            tags
        );
        doc.setIdDocument(documentAModifier.getIdDocument());

        service.modifierDocument(doc);
    }

    private static Documents selectionnerDocument(List<Documents> documents) {
        if (documents.isEmpty()) {
            return null;
        }

        if (documents.size() == 1) {
            return documents.get(0);
        }

        System.out.println("\nPlusieurs documents trouvés :");
        for (int i = 0; i < documents.size(); i++) {
            System.out.println((i + 1) + ". ");
            afficherDocument(documents.get(i));
        }

        System.out.print("\nSélectionnez le numéro du document à modifier (1-" + documents.size() + ") : ");
        int choix = scanner.nextInt();
        scanner.nextLine(); // Vider le buffer

        if (choix >= 1 && choix <= documents.size()) {
            return documents.get(choix - 1);
        }

        return null;
    }

    private static void afficherDocument(Documents doc) {
        System.out.println("ID: " + doc.getIdDocument());
        System.out.println("Titre: " + doc.getTitreDocument());
        System.out.println("Description: " + doc.getDescriptionDocument());
        System.out.println("Date création: " + doc.getDateCreationDocument());
        System.out.println("Date modification: " + doc.getDateModificationDocument());
        System.out.println("URL: " + doc.getUrlDocument());
        System.out.println("Tags: " + String.join(", ", doc.getTagsDocuments()));
        System.out.println("----------------------------------------");
    }

    private static void supprimerDocument() {
        System.out.println("\n=== Suppression d'un document ===");
        System.out.print("ID du document à supprimer : ");
        int id = scanner.nextInt();
        service.supprimerDocument(id);
    }

    private static void rechercherDocument() {
        System.out.println("\n=== Recherche de documents ===");
        System.out.print("Entrez votre critère de recherche : ");
        String critere = scanner.nextLine();

        List<Documents> resultats = service.rechercherDocument(critere);
        afficherDocuments(resultats);
    }

    private static void afficherTousLesDocuments() {
        System.out.println("\n=== Liste de tous les documents ===");
        List<Documents> documents = service.getAllDocuments();
        afficherDocuments(documents);
    }

    private static void afficherDocuments(List<Documents> documents) {
        if (documents.isEmpty()) {
            System.out.println("Aucun document trouvé.");
            return;
        }

        for (Documents doc : documents) {
            System.out.println("\nID: " + doc.getIdDocument());
            System.out.println("Titre: " + doc.getTitreDocument());
            System.out.println("Description: " + doc.getDescriptionDocument());
            System.out.println("Date création: " + doc.getDateCreationDocument());
            System.out.println("Date modification: " + doc.getDateModificationDocument());
            System.out.println("URL: " + doc.getUrlDocument());
            System.out.println("Tags: " + String.join(", ", doc.getTagsDocuments()));
            System.out.println("----------------------------------------");
        }
    }
}