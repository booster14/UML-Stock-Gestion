package stockgestion;

import stockgestion.Controlleur.ArticleControlleur;
import stockgestion.Controlleur.CaisseControlleur;
import stockgestion.Controlleur.ClientControlleur;
import stockgestion.Controlleur.CommandeControlleur;
import stockgestion.Controlleur.FournisseurControlleur;

public class StockGestion {
    private ArticleControlleur articleControlleur;
    private CaisseControlleur caisseControlleur;
    private ClientControlleur clientControlleur;
    private CommandeControlleur commandeControlleur;
    private FournisseurControlleur fournisseurControlleur;
    
    public StockGestion(){
        articleControlleur = ArticleControlleur.getInstance();
        caisseControlleur = CaisseControlleur.getInstance();
        clientControlleur = ClientControlleur.getInstance();
        commandeControlleur = CommandeControlleur.getInstance();
        fournisseurControlleur = FournisseurControlleur.getInstance();
    }
}
