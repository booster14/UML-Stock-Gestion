package stockgestion.Controlleur;

import stockgestion.Entite.*;

public class CaisseControlleur {
    private static CaisseControlleur instance = null;
    
    private CaisseControlleur(){}
    
    public static CaisseControlleur getInstance(){
        if(instance == null){
            instance = new CaisseControlleur();
        }
        return instance;
    }

    /**
     * 
     * @param caisse la caisse a ouvrir
     */
    public void ouvrir(Caisse caisse) {
        caisse.setEtat(true);
    }

    /**
     * 
     * @param caisse la caisse a fermer
     */
    public void fermer(Caisse caisse) {
        caisse.setEtat(false);
    }

    /**
     * 
     * @param caisse la caisse dont on veut ajouter un client
     * @param client le client a ajouter a la caisse
     */
    public void ajouterClient(Caisse caisse, Client client) {
        caisse.getListClients.add(client);
    }

}