package stockgestion.Controlleur;

import stockgestion.Entite.*;
import stockgestion.Manager.CommandeManagerBDD;

public class CommandeControlleur {
    private static CommandeControlleur instance = null;
    private CommandeManagerBDD bdd;
    
    private CommandeControlleur(){
        bdd = CommandeManagerBDD.getInstance();
    }
    
    public static CommandeControlleur getInstance(){
        if(instance == null){
            instance = new CommandeControlleur();
        }
        return instance;
    }

    /**
     * 
     * @param commande commande a ajouter dans la BDD
     */
    public void ajouter(Commande commande) {
        bdd.ajouter(commande);
    }

    /**
     * 
     * @param commande commande a supprimer de la BDD
     */
    public void annuler(Commande commande) {
        bdd.supprimer(commande);
    }

    /**
     * 
     * @param commande commande a imprimer
     */
    public void imprimer(Commande commande) {
    }

    /**
     * Retourner si un article est en commande ou pas
     * @param article
     * @return article en commande ou pas
     */
    public boolean articleEnCommande(Article article){
        return bdd.estCommande(article);
    }
    
    /**
     * Retourner la commande d'un article
     * @param article
     * @return la commande d'un article
     */
    public Commande getCommandeArticle(Article article){
        return bdd.get(article);
    }
}