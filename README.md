# 🍔 Brasil Burger – Espace Gestionnaire
> **Système complet de gestion de restauration rapide développé avec Symfony 7.3**

---

## 📋 Présentation
Cette application permet aux administrateurs de piloter l'activité complète du restaurant, de la gestion du catalogue de produits à l'affectation des livreurs, tout en suivant les performances via un tableau de bord analytique.

---

## ✅ Fonctionnalités Clés

### 🔐 Sécurité & Accès
* **Authentification Robuste :** Connexion sécurisée via matricule et mot de passe.
* **Gestion des Sessions :** Persistance de connexion et déconnexion sécurisée.

### 🍔 Gestion du Catalogue (CRUD & Workflow)
* **Produits :** Gestion complète des **Burgers**, **Menus** et **Compléments**.
* **Cycle de vie :** Système d'états dynamiques pour chaque produit :
  * `Disponible` : Visible par les clients.
  * `Indisponible` : Temporairement retiré.
  * `Archivé` : Retiré du catalogue mais conservé en historique.

### 📦 Pilotage des Commandes
* **Suivi en temps réel :** Liste exhaustive avec filtres multicritères (État, Date, Client).
* **Gestion Logistique :** * Regroupement automatique des commandes par **Zone**.
  * Affectation manuelle ou automatique d'un **Livreur**.
* **Actions rapides :** Terminer ou annuler une commande en un clic depuis le détail.

### 📊 Tableau de Bord & Statistiques
* **Monitoring Journalier :** Compteur des commandes en cours, validées et annulées.
* **Finances :** Calcul automatique des recettes journalières.
* **Analyse des Ventes :** Top 5 des Burgers et Menus les plus populaires.

---

## 📂 Architecture du Projet
```text
symfony-brasil-burger/
├── config/             # Configuration Doctrine, Sécurité et Framework
├── src/
│   ├── Controller/     # Logique métier (Livraison, Commande, Sécurité...)
│   ├── Entity/         # Modèles de données (Burger, Menu, Commande...)
│   ├── Repository/     # Requêtes personnalisées (Statistiques, Filtres...)
│   └── Form/           # Formulaires de saisie
├── templates/          # Vues Twig organisées par module
├── public/             # Assets (CSS, JS, Images)
├── Dockerfile          # Configuration pour le déploiement
└── .env                # Variables d'environnement (Base de données Neon)