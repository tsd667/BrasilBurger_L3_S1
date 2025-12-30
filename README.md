🍔 BRASIL BURGER – GESTIONNAIRE (Symfony 7.3)
✅ FONCTIONNALITÉS
🔐 Authentification
Connexion gestionnaire (matricule + mot de passe)
Gestion des sessions
Déconnexion sécurisée
🍔 Gestion des Produits
Burgers
Lister
Modifier
Archiver
Menus
Lister
Modifier
Archiver
Compléments
Lister
Modifier
Archiver
Gestion des états : disponible / indisponible / archivé
📦 Gestion des Commandes
Lister toutes les commandes
Filtrer par :
État
Date
Client
Consulter les détails d’une commande
Terminer une commande
Annuler une commande
Regrouper les commandes par zone
Affecter un livreur à une commande
📊 Statistiques
Commandes en cours du jour
Commandes validées du jour
Commandes annulées du jour
Recettes journalières
Top 5 burgers les plus vendus
Top 5 menus les plus vendus

symfony-brasil-burger/
├── config/
│   └── packages/
│       ├── doctrine.yaml
│       ├── security.yaml
│       ├── framework.yaml
│       └── twig.yaml
├── src/
│   ├── Controller/
│   ├── Entity/
│   ├── Repository/
│   ├── Form/
│   └── Kernel.php
├── templates/
│   ├── base.html.twig
│   ├── security/
│   ├── dashboard/
│   ├── burger/
│   ├── menu/
│   ├── complement/
│   └── commande/
├── public/
│   ├── index.php
│   └── css/
│       └── style.css
├── .env
├── composer.json
├── Dockerfile
└── README.md
