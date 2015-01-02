# UML

## Vidéo de présentation

### Produits à commander
* Commander un produit (clic droit sur l'article, n'oublie pas de sélectionner l'article avant)
* Imprimer la page (menu en haut)
* Déclaré qu'un produit en commande est arrivé (et montrer que le stock a bien été mis à jour)

### Inventaire
* Supprimer un/des articles (avec ou pas la multi-sélection)
* Consulter puis Editer un article (ne sélectionnes pas plusieurs, c'est toujours le premier article qui est édité)
* Dans Voir article, éditer un fournisseur existant (sélectionne un fournisseur puis clic droit)
* Ajouter un fournisseur
* Choisir plusieurs fournisseurs pour l'article avec la multi-sélection
* Imprimer la page (toujours menu en haut)

### Ajouter un article
* Ajouter un article
* Montrer dans l'Inventaire que l'article a bien été ajouté

### Caisse
* Tu connais mieux que moi Quentin :smiley:


## Base de donnée

### Database

1. Créer une base de donnée Derby nommé `uml` avec en login : `uml` et en mot de passe `groupe3`
2. Utiliser le script `scriptBDD.sql` pour générer les tables
3. Utiliser le script `scriptAJout.sql` pour ajouter des données dans les tables

Si Netbeans ne trouve pas le pilote de Derby, merci d'ajouter à votre projet la librairie correspondante. Clique droit sur le projet puis :
`Properties -> Librairies -> Add Jar/Folder`

### Manager

Chaque Manager est un singleton. Exemple d'utilisation :
```
Article article = ArticleManagerBDD.getInstance().get(2);
```

Cette ligne initialisera un objet Article nommé article dont l'ID dans la base de donnée est 2.


## Autheurs

### Thibault
* Etape 1 : 2)
* Etape 2 : 2.2)
* Manager

### Quentin
* Etape 1 : 3)
* Etape 2: 2.1)
* UI + Entité

### Ngocky
* Etape 1 : 4)
* Etape 2 : 1)
* Contrôlleurs
