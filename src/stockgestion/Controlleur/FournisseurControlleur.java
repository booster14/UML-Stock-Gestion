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
     * Ajouter un fournisseur
     * @param fournisseur fournisseur a ajouter dans la BDD
     */
    public void ajouter(Fournisseur fournisseur) {
        bdd.ajouter(fournisseur);
    }

    /**
     * Supprimer un fournisseur
     * @param fournisseur fournisseur a supprimer de la BDD
     */
    public void supprimer(Fournisseur fournisseur) {
        bdd.supprimer(fournisseur);
    }

    /**
     * Editer un fournisseur
     * @param fournisseur fournisseur a editer dans la BDD
     */
    public void editer(Fournisseur fournisseur) {
        bdd.editer(fournisseur);
    }

    /**
     * Obtenir la liste de tous les fournisseurs
     * @return List<Fournisseur>
     */
    public List<Fournisseur> getAllFournisseur() {
        return bdd.getAll();
    }

    /**
     * Obtenir un fournisseur 
     * @param id id du fournisseur
     * @return Fournisseur
     */
    public Fournisseur getFournisseur(int id) {
        return bdd.get(id);
    }

}