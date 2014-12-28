package stockgestion.Controlleur;

import java.util.List;
import stockgestion.Entite.*;
import stockgestion.Manager.FournisseurManagerBDD;

public class FournisseurControlleur {
    private static FournisseurControlleur instance = null;
    private FournisseurManagerBDD bdd;
    
    private FournisseurControlleur(){
        bdd = FournisseurManagerBDD.getInstance();
    }

    /**
     * 
     * @param fournisseur fournisseur a ajouter dans la BDD
     */
    public void ajouter(Fournisseur fournisseur) {
    }

    /**
     * 
     * @param fournisseur fournisseur a supprimer de la BDD
     */
    public void supprimer(Fournisseur fournisseur) {
    }

    /**
     * 
     * @param fournisseur fournisseur a editer dans la BDD
     */
    public void editer(Fournisseur fournisseur) {
    }

    public List<Fournisseur> getAllFournisseur() {
    }

    /**
     * 
     * @param id id du fournisseur
     */
    public Fournisseur getFournisseur(int id) {
    }

}