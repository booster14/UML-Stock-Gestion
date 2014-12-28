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

}