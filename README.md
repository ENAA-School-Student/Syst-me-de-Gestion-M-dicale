1. Nom du projet

Nom du projet : HealthCare+ - Système de Gestion Médicale

2. Présentation du projet

Ce projet est une application web RESTful d'entreprise qui permet de moderniser et d'automatiser la gestion des flux de travail cliniques.

Il s'adresse principalement aux professionnels de santé (médecins, administratifs) et aux patients des établissements médicaux.

Son objectif principal est d'orchestrer et de centraliser de manière sécurisée et efficace les interactions entre la gestion des patients, le suivi des médecins, la planification des rendez-vous et la tenue des dossiers médicaux.

3. Problématique

Le problème identifié est que la gestion manuelle ou décentralisée des dossiers patients et des agendas de consultation entraîne des erreurs de saisie, des pertes d'informations médicales critiques et des difficultés de coordination au sein des établissements de santé.

La solution proposée permet d'offrir une plateforme unifiée, sécurisée et conteneurisée permettant de centraliser les dossiers cliniques, d'automatiser la prise de rendez-vous en temps réel et de contrôler rigoureusement les accès aux données selon le rôle de l'utilisateur (ADMIN, MEDECIN, PATIENT).

4. Fonctionnalités principales

Gérer les profils et les dossiers des patients (Création, Lecture, Modification, Suppression).

Suivre les spécialités, les disponibilités et les affectations des médecins.

Planifier et effectuer des recherches multicritères de rendez-vous médicaux.

Centraliser le Dossier Médical Partagé (DMP) incluant les diagnostics, les observations et l'historique clinique.

Sécuriser l'accès aux ressources via une authentification stateless basée sur des jetons JWT et la gestion des rôles.

Exposer et documenter l'ensemble des API REST de manière interactive avec Swagger / OpenAPI 3.

5. Technologies utilisées

Technologie

Utilisation dans le projet

Java 21 & Spring Boot 3

Développement du backend, de la logique métier et des API RESTful

Spring Security & JWT

Gestion de l'authentification stateless et contrôle d'accès par rôles

Spring Data JPA & Hibernate

Object-Relational Mapping (ORM) et gestion de la persistance des données

MySQL 8.0

Base de données relationnelle pour le stockage permanent des données

Flyway

Versionnement et migration automatisée du schéma de la base de données

Docker & Docker Compose

Conteneurisation et déploiement de l'écosystème complet

React & Bootstrap / CSS

Développement de l'interface utilisateur frontend dynamique

Maven

Gestionnaire de dépendances, compilation et build de l'application

Nous avons utilisé Spring Boot pour concevoir une architecture backend robuste en couches (Controller, Service, Repository, DTO).

6. Installation et lancement

6.1 Prérequis

Pour utiliser ce projet, vous devez disposer de :

Java 21 (JDK 21)

Maven 3.9+

Node.js (v18+) et npm

Docker & Docker Compose

Git

6.2 Cloner le dépôt

git clone https://github.com/votre-compte/healthcare-plus.git


6.3 Ouvrir le dossier

cd healthcare-plus


6.4 Installer les dépendances

Pour le backend (Spring Boot) :

mvn clean package -DskipTests


Pour le frontend (React) :

npm install


6.5 Variables d'environnement

Créer un fichier .env à la racine ou configurer application.properties :

DATABASE_URL=jdbc:mysql://localhost:3306/healthcare_db
DATABASE_USERNAME=root
DATABASE_PASSWORD=root
JWT_SECRET=votre_cle_secrete_super_securisee_jwt_123456789
PORT=8080


6.6 Lancer le projet

Option A : Lancement automatisé avec Docker Compose (Recommandé)

docker compose up --build -d


Option B : Lancement manuel local

Démarrer le backend :

mvn spring-boot:run


Démarrer le frontend :

npm run dev


6.7 Ouvrir le projet

Après le lancement :

Interface Frontend : http://localhost:3000 (ou http://localhost:5173)

Documentation API (Swagger) : http://localhost:8080/swagger-ui.html

7. Captures d'écran

Capture 1

Titre

Tableau de Bord et Gestion des Patients


Image

Explication

Cette capture montre l'interface d'administration du tableau de bord avec la liste complète des patients, permettant d'effectuer les actions de consultation, modification et suppression selon les privilèges de l'utilisateur connecté.

Capture 2

Titre

Documentation OpenAPI / Swagger UI


Image

Explication

Cette capture montre la documentation interactive Swagger des API REST de HealthCare+, exposant l'ensemble des endpoints sécurisés pour la gestion des médecins, rendez-vous et dossiers médicaux.

8. Contribution personnelle

Ma contribution principale a porté sur la conception et l'architecture backend globale de l'application sous Spring Boot 3.

J'ai également travaillé sur la mise en place de la chaîne de sécurité avec Spring Security et JWT, la création des migrations de base de données avec Flyway, ainsi que la configuration des conteneurs avec Docker Compose.

J'ai été responsable de l'intégration de la gestion globale des exceptions (@RestControllerAdvice), du mapping des entités DTO via MapStruct et du raccordement de l'interface frontend React aux API REST.

9. Difficultés rencontrées

Difficulté 1

Problème rencontré

Gestion uniforme des erreurs de sécurité (erreurs 401 Unauthorized et 403 Forbidden) renvoyées par Spring Security sous forme de réponses JSON personnalisées.

Recherches / Tests

Analyse de la chaîne de filtres Spring Security et recherche sur la personnalisation de AuthenticationEntryPoint et AccessDeniedHandler.

Solution

Création de classes personnalisées implémentant ces interfaces et injection dans la classe de configuration SecurityConfig pour intercepter les exceptions et retourner un objet JSON structuré unifié ({"message": "..."}).

Ce que j'ai appris

J'ai approfondi ma compréhension du fonctionnement interne du filtre de sécurité Spring Security et de la gestion de l'authentification stateless.

Difficulté 2

Problème rencontré

Échec de démarrage du conteneur backend Spring Boot lors du lancement avec Docker Compose car la base de données MySQL n'était pas encore totalement prête à recevoir les migrations Flyway.

Recherches / Tests

Inspection des logs Docker et tests avec la directive depends_on.

Solution

Ajout d'un contrôle de santé (healthcheck) sur le conteneur MySQL dans le fichier docker-compose.yml et configuration de l'application backend pour qu'elle attende le statut service_healthy.

Ce que j'ai appris

J'ai appris à gérer l'orchestration avancée de conteneurs dépendants avec Docker Compose et à mettre en place des verrous de démarrage réseaux.

10. Améliorations possibles

Dans une prochaine version, je pourrais :

Développer une application mobile dédiée (React Native / Flutter) à destination des patients pour la prise de rendez-vous directe.

Intégrer un service d'envoi automatique de notifications et rappels par SMS et e-mail (Twilio / SendGrid).

Mettre en place un système d'intelligence artificielle d'aide au diagnostic clinique basé sur l'historique des dossiers médicaux.

Ajouter des tests d'intégration automatisés avec la bibliothèque Testcontainers.

Conclusion

Ces améliorations permettraient de renforcer la valeur ajoutée clinique de HealthCare+, d'améliorer la fidélisation des patients et d'assurer une couverture de tests automatisés encore plus robuste.
