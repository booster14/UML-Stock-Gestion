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
    
    public static FournisseurControlleur getInstance(){
        if(instance == null){
            instance = new FournisseurControlleur();
        }
        return instance;
    }

    /**
     * 
     * @param fournisseur fournisseur a ajouter dans la BDD
     */
    public void ajouter(Fournisseur fournisseur) {
        bdd.ajouter(fournisseur);
    }

    /**
     * 
     * @param fournisseur fournisseur a supprimer de la BDD
     */
    public void supprimer(Fournisseur fournisseur) {
        bdd.supprimer(fournisseur);
    }

    /**
     * 
     * @param fournisseur fournisseur a editer dans la BDD
     */
    public void editer(Fournisseur fournisseur) {
        bdd.editer(fournisseur);
    }

    public List<Fournisseur> getAllFournisseur() {
        return bdd.getAll();
    }

    /**
     * 
     * @param id id du fournisseur
     */
    public Fournisseur getFournisseur(int id) {
        return bdd.get(id);
    }

}