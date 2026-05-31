# HealthCare+ : Système de Gestion Médicale

HealthCare+ est une application web RESTful d'entreprise conçue pour moderniser et automatiser la gestion des flux de travail cliniques. Développée avec **Java 21** et **Spring Boot**, l'application offre une plateforme sécurisée, scalable et conteneurisée pour orchestrer les interactions entre patients, médecins, rendez-vous et dossiers médicaux.

---

## Architecture & Fonctionnalités Core

### Architecture du Projet
L'application adopte une **Architecture Multi-Couches (MVC/N-Tier)** stricte pour garantir la séparation des préoccupations (Separation of Concerns) :
* **Couche Présentation (Controller) :** REST APIs documentées avec Swagger (OpenAPI 3).
* **Couche Business (Service) :** Encapsulation de la logique métier, gestion des transactions et sécurité.
* **Couche Accès aux Données (Repository) :** Abstraction de la base de données via Spring Data JPA.
* **Couche Transfert (DTO & Mappers) :** Isolation des entités de la base de données via **MapStruct**.

### Structure du projet
<img width="850" height="792" alt="image" src="https://github.com/user-attachments/assets/475fe548-7894-46e5-9f13-38b8f0abee03" />

###  Fonctionnalités Principales
* **Gestion des Patients :** CRUD complet, historique de consultations et archivage clinique.
* **Gestion des Médecins :** Suivi des spécialités, plannings et associations médicales.
* **Gestion des Rendez-vous :** Planification avancée, annulation et moteurs de recherche multicritères (par médecin/patient).
* **Dossier Médical Partagé (DMP) :** Gestion centralisée des diagnostics, observations cliniques et suivi chronologique.


##   Modélisation & Diagrammes UML

###  Diagramme de Cas d'Utilisation
<img width="444" height="348" alt="image" src="https://github.com/user-attachments/assets/72a4203e-afb9-4aac-91a2-f07498be9f2c" />

###  Diagramme de Classe
<img width="509" height="316" alt="diagrameDeClasse" src="https://github.com/user-attachments/assets/00b2746e-5fc8-4583-b21f-b002bfdcd3a8" />

###  Diagrammes de Séquence

#### 1. Lister les Médecins
<img width="425" height="260" alt="listerMedecin" src="https://github.com/user-attachments/assets/c827cb7c-a7ad-4e28-857a-c514aaf21a00" />

#### 2. Ajouter un Patient
<img width="524" height="309" alt="addPatient" src="https://github.com/user-attachments/assets/e24bbf47-9c99-40bf-a68a-8ccccc304227" />


##  Sécurité Avancée & Gestion des Exceptions

###  Couche Sécurité (Spring Security & JWT)
L'application intègre une architecture de sécurité **Stateless** basée sur des jetons **JSON Web Tokens (JWT)** :
* **Authentification & Autorisation :** Gestion des accès via des filtres personnalisés (`JwtFilter`) positionnés avant la chaîne de sécurité Spring Security.
* **Contrôle d'Accès Granulaire :** Sécurisation des Endpoints via les annotations `@PreAuthorize` (gestion par rôles : `ADMIN`, `MEDECIN`, `PATIENT`).
* **Hachage des Mots de Passe :** Utilisation de l'algorithme robuste **BCrypt** via `PasswordEncoder`.

###  Gestion Globale des Exceptions (Best Practices)
La gestion des erreurs a été centralisée à l'aide d'un composant `@RestControllerAdvice` couplé à des Handlers de sécurité délégués (`AuthenticationEntryPoint` & `AccessDeniedHandler`) :
* **Réponses Unifiées :** Toutes les erreurs retournent un format JSON uniforme (`Map<String, String>`) pour faciliter la consommation par le Frontend (ex: `{"message": "Détails de l'erreur"}`).
* **Validation des Données :** Interception automatique des contraintes de validation (`@Valid`, `@NotNull`) avec retour ciblé des champs non conformes.


##   Infrastructure, DevOps & Documentation

###  Cycle de Vie de la Base de Données
* **Flyway Migration :** Le schéma de la base de données MySQL est versionné. Les scripts de migration automatisent la création des tables et le chargement des données initiales sans perte d'intégrité.
* **Hibernate DDL :** Configuré sur `update` ou `validate` pour assurer la conformité avec les versions de Flyway.

###  Conteneurisation & DevOps (Docker)
L'ensemble de l'écosystème HealthCare+ est entièrement orchestré via **Docker Compose**, permettant un déploiement "Zero-Configuration" :
* **`Dockerfile` :** Basé sur l'image légère `eclipse-temurin:21-jre-alpine` pour optimiser la taille de l'image finale.
* **`docker-compose.yml` :** Orchestre deux services interconnectés : `healthcare-app` (Spring Boot) et `healthcare-db` (MySQL 8.0).
* **Control de Santé (Healthcheck) :** L'application Spring Boot attend que le conteneur MySQL soit totalement prêt (`healthy`) avant d'amorcer le démarrage et les migrations Flyway.


##  Guide de Démarrage Rapide

### 1. Prérequis
* Docker & Docker Compose installés.
* Maven 3.9+ (si build local).

### 2. Lancement de l'environnement complet (Docker)
Générez le fichier d'archive `.jar` puis lancez les conteneurs :
```bash
mvn clean package -DskipTests
docker compose up --build -d
