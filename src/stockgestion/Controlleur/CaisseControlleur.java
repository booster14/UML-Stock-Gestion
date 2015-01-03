package stockgestion.Controlleur;

import java.util.Map;
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
     * Ouvrir la caisse
     * @param caisse la caisse a ouvrir
     */
    public void ouvrir(Caisse caisse) {
        caisse.setEtat(true);
    }

    /**
     * Fermer la caisse
     * @param caisse la caisse a fermer
     */
    public void fermer(Caisse caisse) {
        caisse.setEtat(false);
    }

    /**
     * Ajout un Client dans la liste des clients de la caisse
     * @param caisse la caisse dont on veut ajouter un client
     * @param client le client a ajouter a la caisse
     */
    public void ajouterClient(Caisse caisse, Client client) {
        caisse.getListClients().add(client);
    }
    
    /**
     * Calcul la somme des prix des articles vendu
     * @param caisse la caisse dont on veut calculer le total
     * @return Double ( Somme )
     */
    public double calculerSomme(Caisse caisse) {
        double somme = 0;
        for(Client client : caisse.getListClients())
        {
            for(Map.Entry <Article, Integer> entry : client.getListArticles().entrySet()){
                somme += entry.getKey().getPrix() * entry.getValue();
                System.out.print(entry.getKey() + "   ");
                System.out.println(entry.getValue());
            }
        }
        System.out.println(somme);
        return somme;
    }
}