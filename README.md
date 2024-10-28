# Gestion des Associations avec Authentification par E-mail et JWT

## Description Générale
Cette application permet de gérer efficacement les associations, leurs membres et leurs événements, avec une sécurisation avancée grâce à un système d'authentification par e-mail et JWT. Elle propose des fonctionnalités complètes pour les administrateurs et utilisateurs, incluant la gestion des sessions utilisateur et la récupération de mot de passe.

## Objectifs
- **Simplifier la gestion des associations** : Permettre aux administrateurs de gérer efficacement les membres et événements.
- **Assurer la sécurité des utilisateurs** : Authentification sécurisée par e-mail et tokens JWT.
- **Faciliter la récupération de mot de passe** : Fonctionnalité pour réinitialiser le mot de passe en cas d'oubli.

## Fonctionnalités Clés

### Authentification et Sécurité
- **Vérification par e-mail** : Envoi d'un e-mail de confirmation lors de l'inscription pour activer le compte.
- **Réinitialisation de mot de passe** : Envoi d'un lien de réinitialisation par e-mail en cas d'oubli.
- **JSON Web Tokens (JWT)** : Gestion sécurisée des sessions pour un accès authentifié à l’API.

### Gestion des Associations et des Membres
- **CRUD Associations** : Création, lecture, mise à jour, suppression d'associations.
- **Gestion des membres** : Ajout de membres, modification de président et assignation de rôles.

### Gestion des Événements
- **CRUD Événements** : Création, lecture, mise à jour et suppression d’événements pour suivre les activités de chaque association.

## Technologies Utilisées

- **Backend (Spring Boot)**
  - Spring Security : Gestion de l'authentification et de l'autorisation.
  - JWT : Sécurisation des sessions et des requêtes.
  - JavaMail : Envoi d’e-mails pour vérification de compte et récupération de mot de passe.
- **Base de Données (MySQL)**
  - Stockage des utilisateurs, associations, événements, et jetons de vérification.

## Cas d'Utilisation

- **Administrateur** :
  - Gérer les associations, membres, et événements.
  - Valider les comptes des nouveaux utilisateurs.

- **Utilisateur** :
  - Inscription avec vérification par e-mail.
  - Réinitialisation de mot de passe.
  - Accès à l'application après authentification JWT.

## Avancement du Projet
Actuellement, le projet est en phase d'intégration des DTOs, mappers, services CRUD, et contrôleurs pour chaque entité, ainsi que les fonctionnalités d'authentification par e-mail et gestion des sessions JWT.

## Instructions d'Installation
# Cloner le projet
git clone https://github.com/rabiejabnouni/-Gestion-des-Associations

# Construire le projet
./mvnw clean install

# Lancer l'application
./mvnw spring-boot:run
## API Endpoints

### Endpoints pour la Gestion des Associations (`/api/associations`)
- **Créer une Association** : `POST /api/associations`
- **Obtenir une Association par ID** : `GET /api/associations/getById?id={id}`
- **Mettre à Jour une Association** : `PUT /api/associations/update?id={id}`
- **Supprimer une Association** : `DELETE /api/associations/deleteById?id={id}`
- **Ajouter un Membre** : `POST /api/associations/addMembre?associationId={associationId}&userId={userId}`
- **Changer de Président** : `PUT /api/associations/changePresident?associationId={associationId}&newPresidentUsername={username}`

### Endpoints pour la Gestion de l'Authentification et des Comptes Utilisateurs (`/api/`)
- **Inscription** : `POST /api/signup`
- **Inscription avec Google** : `POST /api/signupWithGoogle`
- **Confirmation de Compte** : `GET /api/confirm?token={token}`
- **Activer un Compte** : `GET /api/enable?email={email}`
- **Liste des Utilisateurs** : `GET /api/users`
- **Supprimer un Utilisateur** : `DELETE /api/delete?email={email}`

### Endpoints pour la Gestion de la Connexion (`/api/users`)
- **Connexion avec Google** : `POST /api/users/google`
- **Connexion Standard** : `POST /api/users/login`

### Endpoints pour la Gestion du Mot de Passe Oublié (`/api/forget`)
- **Réinitialiser le Mot de Passe** : `POST /api/forget?username={username}`
- **Mettre à Jour le Mot de Passe** : `POST /api/forget/updatePassword`
- **Vérification de Confirmation** : `GET /api/forget/get?token={token}`

### Endpoints pour la Gestion des Événements (`/events`)
- **Créer un Événement** : `POST /events` — Crée un nouvel événement à partir des données fournies dans `EventRequestDTO`.
- **Lire Tous les Événements** : `GET /events` — Retourne la liste des événements sous forme de `EventResponseDTO`.
- **Lire un Événement par ID** : `GET /events/getById?id={id}` — Retourne un événement par ID.
- **Supprimer un Événement par ID** : `DELETE /events/deleteById?id={id}` — Supprime l’événement spécifié par son ID.
- **Ajouter un Participant à un Événement** : `POST /events/addUser?eventId={eventId}&userId={userId}` — Ajoute un utilisateur aux participants d'un événement.
