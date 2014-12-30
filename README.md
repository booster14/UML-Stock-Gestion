# UML

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
