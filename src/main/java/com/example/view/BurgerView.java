package com.example.view;

import com.example.entity.Burger;
import com.example.service.BurgerService;
import com.example.service.ImageService;

import java.io.IOException;   

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;



public class BurgerView {

    private BurgerService service;
    private ImageService imageService;  
    private Scanner scanner;

    public BurgerView(BurgerService service) {
        this.service = service;
        this.imageService = new ImageService(); 
        this.scanner = new java.util.Scanner(System.in);
    }

    public void afficherMenu() throws SQLException {
        while (true) {
            System.out.println("\n=== MENU BURGER ===");
            System.out.println("1. Lister tous les burgers");
            System.out.println("2. Créer un burger");
            System.out.println("3. Retour");
            System.out.print("Choix : ");
            
            String input = scanner.nextLine();
            if (input.isEmpty()) continue;
            
            try {
                int choix = Integer.parseInt(input);
                switch (choix) {
                    case 1 -> listerBurgers();
                    case 2 -> creerBurger();
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

    private void listerBurgers() throws SQLException {
        List<Burger> burgers = service.listerTous();
        System.out.println("\n--- Liste des burgers ---");
        for (Burger b : burgers) {
            System.out.println(b);
        }
    }

    private void creerBurger() throws SQLException {
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
 
            urlImage = imageService.uploadImage(localFilePath, "brasil_burger/burgers");
            System.out.println("✅ Image uploadée avec succès. URL : " + urlImage);
        } catch (IOException e) {
            System.out.println("❌ Erreur lors de l'upload de l'image : " + e.getMessage());
            System.out.println("Annulation de la création du burger.");
            return; 
        }
 
        
        System.out.print("Description : ");
        String desc = scanner.nextLine();

 
        Burger burger = new Burger(nom, prix, urlImage, desc);
        service.creer(burger);
        System.out.println("✅ Burger créé avec succès !");
    }

     
}