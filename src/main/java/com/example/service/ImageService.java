package com.example.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.config.CloudinaryConfig;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class ImageService {

    private final Cloudinary cloudinary;

    public ImageService() {
        this.cloudinary = CloudinaryConfig.getInstance();
    }

    /**
     * Upload un fichier image local vers Cloudinary et retourne l'URL sécurisée.
     * 
     * @param localFilePath Chemin absolu du fichier sur le disque de l'utilisateur.
     * @param folderName Dossier dans Cloudinary (ex: "brasil_burger/burgers").
     * @return L'URL sécurisée (HTTPS) de l'image hébergée.
     * @throws IOException Si le fichier n'est pas trouvé ou s'il y a une erreur d'upload.
     */
    public String uploadImage(String localFilePath, String folderName) throws IOException {
        File file = new File(localFilePath);
        
        // Vérification de l'existence du fichier
        if (!file.exists()) {
            throw new IOException("❌ Fichier non trouvé : " + localFilePath);
        }
        
        if (file.isDirectory()) {
            throw new IOException("❌ Le chemin spécifié est un dossier, pas un fichier : " + localFilePath);
        }
        
        if (!file.canRead()) {
            throw new IOException("❌ Impossible de lire le fichier : " + localFilePath);
        }
        
        System.out.println("📤 Upload en cours de : " + file.getName() + "...");
        
        try {
            // Envoi du fichier à Cloudinary avec options
            Map<String, Object> options = ObjectUtils.asMap(
                "folder", folderName,
                "resource_type", "auto",  // Détection automatique du type
                "use_filename", true,     // Conserver le nom du fichier
                "unique_filename", true   // Ajouter un identifiant unique
            );
            
            Map uploadResult = cloudinary.uploader().upload(file, options);
            
            String secureUrl = (String) uploadResult.get("secure_url");
            System.out.println("✅ Upload réussi ! URL: " + secureUrl);
            
            return secureUrl;
            
        } catch (IOException e) {
            System.err.println("❌ Erreur lors de l'upload: " + e.getMessage());
            throw e;
        }
    }
    
    /**
     * Overload pour un upload simple sans spécifier de dossier
     */
    public String uploadImage(String localFilePath) throws IOException {
        return uploadImage(localFilePath, "brasil_burger");
    }
}
