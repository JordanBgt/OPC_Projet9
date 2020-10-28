[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=JordanBgt_OPC_Projet9&metric=coverage)](https://sonarcloud.io/dashboard?id=JordanBgt_OPC_Projet9)

# Openclassrooms Projet 9 : Testez vos développements Java

## Context

Votre équipe est en train de réaliser un système de facturation et de comptabilité pour un client. Le développement a débuté depuis quelques temps et vous devez commencer à vérifier que l'application fonctionne correctement, qu'elle répond bien aux règles de gestion et les respecte.

### Extrait dossier de spécifications fonctionelles 

#### Diagramme de classe 

![diagramme de classe](https://user.oc-static.com/upload/2017/03/09/14890556605444_DC_Comptabilite.png)

#### Règles de gestion

| ID            | Description   |       
| ------------- |:-------------:|
| RG_Compta_1   | Le solde d'un compte comptable est égal à la somme des montants au débit des lignes d'écriture diminuées de la somme des montants au crédit. Si le résultat est positif, le solde est dit "débiteur", si le résultat est négatif le solde est dit "créditeur" |
| RG_Compta_2   | Pour qu'une écriture comptable soit valide, elle doit être équilibrée : la somme des montants au crédit des lignes d'écriture doit être égale à la somme des montants au débit |
| RG_Compta_3   | Une écriture comptable doit contenir au moins deux lignes d'écriture : une au débit et une au crédit |
| RG_Compta_4   | Les montants des lignes d'écriture sont signés et peuvent prendre des valeurs négatives (même si cela est peu fréquent) |
| RG_Compta_5   | La référence d'une écriture comptable est composée du code du journal dans lequel figure l'écriture suivi de l'année et d'un numéro de séquence (propre à chaque journal) sur 5 chiffres incrémenté automatiquement à chaque écriture. Le formatage de la référence est : XX-AAAA/#####.
Ex : Journal de banque (BQ), écriture au 31/12/2016
--> BQ-2016/00001 |
| RG_Compta_6   | La référence d'une écriture comptable doit être unique, il n'est pas possible de créer plusieurs écritures ayant la même référence |
| RG_Compta_4   | Les montants des lignes d'écritures peuvent comporter 2 chiffres maximum après la virgule |

## Ressources : Myerp

### Organisation du répertoire

*   `doc` : documentation
*   `docker` : répertoire relatifs aux conteneurs _docker_ utiles pour le projet
    *   `dev` : environnement de développement
*   `src` : code source de l'application


### Environnement de développement

Les composants nécessaires lors du développement sont disponibles via des conteneurs _docker_.
L'environnement de développement est assemblé grâce à _docker-compose_
(cf docker/dev/docker-compose.yml).

Il comporte :

*   une base de données _PostgreSQL_ contenant un jeu de données de démo (`postgresql://127.0.0.1:9032/db_myerp`)

#### Lancement

    cd docker/dev
    docker-compose up


#### Arrêt

    cd docker/dev
    docker-compose stop


#### Remise à zero

    cd docker/dev
    docker-compose stop
    docker-compose rm -v
    docker-compose up

## Travail demandé

### Les tests 

Réaliser deux types de tests : 
* des tests unitaires : leurs objectifs sont de valider les règles de gestion unitaires de chaque "composant" de l'application
* des tests d'intégration : leurs objectifs sont de valider la bonne interaction entre les différents composants de l'application

À vous de définir vos tests et la stratégie que vous allez mettre en place pour les tests d'intégration.

Vous implémenterez et automatiserez ces tests à l'aide de JUnit, Mockito, Maven et Travis CI / GitLab CI / Jenkins.

Les tests seront lancés via le build Maven.

Les tests d'intégration font l'objet de profils Maven spécifiques (cf. le fichier pom.xml du projet parent fourni).

Cet environnement sera construit (à partir des éléments disponibles dans le dépôt Git du projet) et monté à la volée par votre système d'intégration continue.

Quelques erreurs ont été volontairement disséminées dans le code de l'application. Il vous faudra toutes les corriger.
Si les tests que vous allez créer sont pertinents, aucun soucis, vous devriez toutes les relever !
Vos tests devront donc présenter une très bonne couverture de votre code.



N'hésitez pas à améliorer le code ou faire du refactoring pendant l'élaboration des tests. Vous augmenterez ainsi la maintenabilité et la lisibilité du code et faciliterez également le débogage de l'application.
Vous pouvez par exemple :

    * tracer les requêtes SQL exécutées
    * compléter les messages des exceptions (détail des violations de contraintes...)
    * tracer les informations de règles de gestion violées avec leur identifiants associés dans le dossier de spécifications

### Complément d'implémentation

Vous compléterez également l'implémentation de l'application en vous appuyant sur les commentaires TODO insérés dans le code source. Vous devez tous les réaliser, il ne devra rester aucun TODO dans votre livrable.
Bien évidemment, tout le code supplémentaire que vous aller écrire pour implémenter les TODO sera testé par vos tests unitaires/d'intégration !


## Référentiel d'évaluation

### Réaliser l'audit d'un système

* Les 5 erreurs de développements dans le projet fourni sont identifiés et résolues
* Le développement a été complété en suivant les TODO

### Mettre en place une démarche qualité et sa méthodologie

* Les tests unitaires ont été réalisés à l’aide de JUnit
* Les tests d’intégration ont été réalisés à l’aide des “profiles” Maven
* L'ensemble des modules a un code coverage de 75% minimum

### Gérer l'évolutivité et l'adaptabilité d'un système

* Un serveur d'intégration est installé et configuré (Jenkins / Travis CI / GitLab CI au choix)
* Un rapport d'exécution des tests est automatiquement généré à chaque commit
* Un logiciel de versionning a été correctement utilisé
