package com.example.view;

import com.example.entity.Complement;
import com.example.service.ComplementService;
import com.example.service.ImageService;

import java.io.IOException;    
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ComplementView {

    private ComplementService service;
    private ImageService imageService;  
    private Scanner scanner;

    public ComplementView(ComplementService service) {
        this.service = service;
        this.imageService = new ImageService(); 
        this.scanner = new java.util.Scanner(System.in);
    }

    public void afficherMenu() throws SQLException {
        while (true) {
            System.out.println("\n=== MENU COMPLEMENT ===");
            System.out.println("1. Lister tous les compléments");
            System.out.println("2. Créer un complément");
            System.out.println("3. Retour");
            System.out.print("Choix : ");
            
            String input = scanner.nextLine();
            if (input.isEmpty()) continue;

            try {
                int choix = Integer.parseInt(input);

                switch (choix) {
                    case 1 -> listerComplements();
                    case 2 -> creerComplement();
                    case 3 -> { return; }
                    default -> System.out.println("Choix invalide !");
                }
            } catch (NumberFormatException e) {
                System.out.println("Saisie invalide. Veuillez entrer un numéro.");
            } catch (SQLException e) {
                System.out.println("Erreur SQL: " + e.getMessage());
            }
        }
    }

    private void listerComplements() throws SQLException {
        List<Complement> list = service.listerTous();
        System.out.println("\n--- Liste des compléments ---");
        for (Complement c : list) {
            System.out.println(c);
        }
    }

    private void creerComplement() throws SQLException {
        System.out.print("Nom : ");
        String nom = scanner.nextLine();
        
        System.out.print("Prix : ");
        double prix = 0;
        try {
            prix = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("❌ Prix invalide.");
            return;
        }

        String urlImage = "";
        System.out.print("Chemin COMPLET du fichier image local (ex: /Users/votre_nom/image.jpg) : ");
        String localFilePath = scanner.nextLine();
        
        try {

            urlImage = imageService.uploadImage(localFilePath, "brasil_burger/complements");
            System.out.println("✅ Image uploadée avec succès. URL : " + urlImage);
        } catch (IOException e) {
            System.out.println("❌ Erreur lors de l'upload de l'image : " + e.getMessage());
            System.out.println("Annulation de la création du complément.");
            return;
        }

        Complement complement = new Complement(nom, prix, urlImage);
        service.creer(complement);
        System.out.println("✅ Complément créé !");
    }

 
}