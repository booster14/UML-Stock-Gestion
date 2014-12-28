package stockgestion.Manager;

import java.util.List;
import stockgestion.Entite.*;

public class CommandeManagerBDD extends Manager{
        private static CommandeManagerBDD instance = null;
    
        private CommandeManagerBDD(){
            super.connection();     
        }
    
        public static CommandeManagerBDD getInstance(){
            if(instance == null){
                instance = new CommandeManagerBDD();
            }
            return instance;
        }

	/**
	 * 
	 * @param commande
	 */
	public void ajouter(Commande commande) {
		// TODO - implement CommandeManagerBDD.ajouter
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param commande
	 */
	public void supprimer(Commande commande) {
		// TODO - implement CommandeManagerBDD.supprimer
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param commande
	 */
	public void editer(Commande commande) {
		// TODO - implement CommandeManagerBDD.editer
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param id
	 */
	public Commande getCommande(int id) {
		// TODO - implement CommandeManagerBDD.getCommande
		throw new UnsupportedOperationException();
	}

	public List<Commande> getAllCommandes() {
		// TODO - implement CommandeManagerBDD.getAllCommandes
		throw new UnsupportedOperationException();
	}

}