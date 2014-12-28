package stockgestion.Manager;

import java.util.List;
import stockgestion.Entite.*;

public class FournisseurManagerBDD extends Manager{

        private static FournisseurManagerBDD instance = null;
    
        private FournisseurManagerBDD(){
            super.connection();     
        }
    
        public static FournisseurManagerBDD getInstance(){
            if(instance == null){
                instance = new FournisseurManagerBDD();
            }
            return instance;
        }
	/**
	 * 
	 * @param fournisseur
	 */
	public void ajouter(Fournisseur fournisseur) {
		// TODO - implement FournisseurManagerBDD.ajouter
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param fournisseur
	 */
	public void supprimer(Fournisseur fournisseur) {
		// TODO - implement FournisseurManagerBDD.supprimer
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param fournisseur
	 */
	public void editer(Fournisseur fournisseur) {
		// TODO - implement FournisseurManagerBDD.editer
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param id
	 */
	public Fournisseur getFounisseur(int id) {
		// TODO - implement FournisseurManagerBDD.getFounisseur
		throw new UnsupportedOperationException();
	}

	public List<Fournisseur> getAllFournisseurs() {
		// TODO - implement FournisseurManagerBDD.getAllFournisseurs
		throw new UnsupportedOperationException();
	}

}