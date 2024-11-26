# Citronix - Gestion de Ferme de Citrons 🍋
## Présentation du Projet
Citronix est une application conçue pour simplifier et optimiser la gestion des fermes de citrons. Ce système permet aux agriculteurs de suivre la production, la récolte et la vente de leurs produits, tout en garantissant une gestion efficace des ressources disponibles, notamment les fermes, champs, arbres, récoltes, et ventes.

## Fonctionnalités Principales

### 1. Gestion des Fermes
### 2. Gestion des Champs
### 3. Gestion des Arbres
### 4. Gestion des Récoltes
### 5. Détails des Récoltes
### 6. Gestion des Ventes

## Architecture Technique
1. Technologies Utilisées
- Spring Boot : Framework principal pour créer l'API REST.
- MapStruct : Mapper pour convertir entre entités et DTOs.
- Lombok : Réduction du code répétitif pour les entités (getters, setters, etc.).
- JUnit & Mockito : Tests unitaires pour garantir la fiabilité.
- Validation Spring : Validation des données via annotations.
2. Architecture en Couches
```bash
📂 src
└── 📂 main
    ├── 📂 java
    │   └── 📂 com.citronix
    │       ├── 📂 controller           # Contrôleurs REST
    │       │   ├── FermeController.java
    │       │   ├── ChampController.java
    │       │   ├── ArbreController.java
    │       │   ├── RecolteController.java
    │       │   ├── RecolteDetailController.java
    │       │   └── VenteController.java
    │       │
    │       ├── 📂 service              # Services contenant la logique métier
    │       │   ├── FermeService.java
    │       │   ├── ChampService.java
    │       │   ├── ArbreService.java
    │       │   ├── RecolteService.java
    │       │   ├── RecolteDetailService.java
    │       │   └── VenteService.java
    │       │
    │       ├── 📂 repository           # Repositories JPA
    │       │   ├── FermeRepository.java
    │       │   ├── ChampRepository.java
    │       │   ├── ArbreRepository.java
    │       │   ├── RecolteRepository.java
    │       │   ├── RecolteDetailRepository.java
    │       │   └── VenteRepository.java
    │       │
    │       ├── 📂 model                # Entités JPA représentant les tables de la base de données
    │       │   ├── Ferme.java
    │       │   ├── Champ.java
    │       │   ├── Arbre.java
    │       │   ├── Recolte.java
    │       │   ├── RecolteDetail.java
    │       │   └── Vente.java
    │       │
    │       └── 📂 dto                  # Objets de transfert de données (DTO)
    │           ├── FermeDto.java
    │           ├── ChampDto.java
    │           ├── ArbreDto.java
    │           ├── RecolteDto.java
    │           ├── RecolteDetailDto.java
    │           └── VenteDto.java
    │
    └── 📂 resources
        └── data.sql             
```
3. Gestion des Exceptions
Gestion centralisée avec des réponses adaptées pour chaque type d'erreur.
## Exemples d'Endpoints
1. Fermes
- Créer une ferme :
#### POST /api/fermes
Body :

```bash
json
{
    "nom": "Ferme Soleil",
    "localisation": "Tunis",
    "superficie": 5.5,
    "dateCreation": "2023-01-15"
}
```

- Rechercher des fermes :
#### GET /api/fermes?nom=Ferme Soleil&localisation=Marrakech

3. Champs
- Créer un champ :
#### POST /api/champs
Body :
``` bash
json
{
    "fermeId": 1,
    "superficie": 2.0
}
```

4. Arbres
- Ajouter un arbre :
#### POST /api/arbres
Body :
``` bash
json
{
    "champId": 1,
    "datePlantation": "2020-03-20"
}
```

5. Récoltes
- Créer une récolte :
#### POST /api/recoltes
Body :
``` bash
json
{
    "date": "2024-04-15",
    "quantite": 300.0,
    "saison": "Printemps",
    "champId": 1
}
```

6. Ventes
- Créer une vente :
#### POST /api/ventes
Body :

```bash
json
{
    "quantite": 50.0,
    "prixUnitaire": 4.5,
    "client": "Client A",
    "recolteId": 1
}
```

##### Lister les ventes :
#### GET /api/ventes

## Structure du Code
- com.citronix.controller : Contient les endpoints de l’API.
- com.citronix.service : Contient la logique métier et les validations.
- com.citronix.repository : Gestion des entités avec Spring Data JPA.
- com.citronix.model : Représentation des entités de la base de données.
- com.citronix.dto : Objets pour transporter les données entre les couches.
## Exécution
### Cloner le dépôt :
```bash
git clone https://github.com/Yassinean/citronix
```
#### Configurer la base de données dans application.properties.
#### Lancer l’application :
```bash
mvn spring-boot:run
```

#### Accéder à l'API :
- Swagger UI : http://localhost:8080/swagger-ui.html
- Base API : http://localhost:8080/api

## Auteur
Développé avec ❤️ par [Yassine Hanach](https://github.com/Yassinean)