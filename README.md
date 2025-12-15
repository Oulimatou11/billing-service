# Billing REST API

## Description
Cette API REST permet la gestion des opérations de facturation des clients.
Elle a été développée dans le cadre d’un projet académique sur l’intégration de services.

Les fonctionnalités principales sont :
- Création de factures
- Consultation des factures
- Consultation des factures pour un client donné
- Paiement de factures
- Suivi des factures impayées

## Technologies
- Java / Spring Boot
- RESTful Web Services
- OpenAPI 3 (Swagger)
- Maven

## Démarrage
### Prérequis
- Java 17+
- Maven

### Lancer l’application
mvn spring-boot:run

## Accés à l'API
-Base URL : http://localhost:8081

-Swagger UI :
http://localhost:8081/swagger-ui/index.html

## Documentation API
### La documentation OpenAPI est disponible :
#### via Swagger UI
#### via le fichier openapi.json à la racine du projet

## ENDPOINTS
#### POST /invoices
#### GET /invoices/{id}
#### GET /clients/{clientId}/invoices
#### PUT /invoices/{id}/pay
#### GET /clients/{clientId}/total
#### GET /invoices/pending?days=15
