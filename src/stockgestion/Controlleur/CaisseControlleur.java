package stockgestion.Controlleur;

import java.util.Map;
import stockgestion.Entite.*;
import stockgestion.Manager.ArticleManagerBDD;

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
        caisse.getListClients().add(client);
    }
    
    /**
     * 
     * @param caisse la caisse dont on veut calculer le total
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