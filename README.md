# pilpose-back:

- Pull du projet backend : Mettre à jour le code source du projet depuis le référentiel distant.

- Vérifier Lambok : S'assurer que Lombok fonctionne correctement. Si ce n'est pas le cas, ajouter les getters et setters dans les fichiers DTO.

- Mettre à jour le projet : Faire un "Update Project" avec Maven et forcer la mise à jour des snapshots/releases.

- Maven Clean : Nettoyer le projet avec Maven pour supprimer les fichiers temporaires.

- Vérifier le port : Assurez-vous que le port utilisé est correct et correspond à celui spécifié dans le fichier environment.ts du projet front-end.

- Modifier la structure de la base de données : Si des changements affectent la structure de la base de données, il est recommandé de supprimer les tables concernées avant de lancer le projet.

- Configurer le Dashboard : S'assurer que le profil est correctement configuré sur "dev".

- Lancer le projet : Démarrer le projet backend.

# Configuration spécifique:

- Chemin des assets : Modifier le chemin dans la classe ConstantsApplication.java pour spécifier le nouvel emplacement des assets.

- Configuration SMTP : Mettre à jour les variables spring.mail.username et spring.mail.password dans le fichier application.properties avec les informations de configuration de votre boîte mail. Assurez-vous de désactiver les couches de sécurité pour permettre l'envoi distant des e-mails.

- Ces instructions fournissent un guide pour configurer, mettre à jour et exécuter le projet backend "pilpose-back". Assurez-vous de suivre ces étapes avec précision pour garantir le bon fonctionnement du projet.