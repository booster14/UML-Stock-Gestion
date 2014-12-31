package stockgestion;

import stockgestion.Controlleur.*;
import stockgestion.UI.*;

public class StockGestion {
    private static StockGestion instance = null;
    
    private ArticleControlleur articleControlleur;
    private CaisseControlleur caisseControlleur;
    private ClientControlleur clientControlleur;
    private CommandeControlleur commandeControlleur;
    private FournisseurControlleur fournisseurControlleur;
    
    private AjouterArticle ajouterArticle;
    private Caisse caisse;
    private CommanderProduit commanderProduit;
    private Inventaire inventaire;
    
    private StockGestion(){
        articleControlleur = ArticleControlleur.getInstance();
        caisseControlleur = CaisseControlleur.getInstance();
        clientControlleur = ClientControlleur.getInstance();
        commandeControlleur = CommandeControlleur.getInstance();
        fournisseurControlleur = FournisseurControlleur.getInstance();
        
        ajouterArticle = AjouterArticle.getInstance();
        caisse = Caisse.getInstance();
        commanderProduit = CommanderProduit.getInstance();
        inventaire = Inventaire.getInstance();
        
        //Initialisation des interfaces
        refreshInventaire();
        refreshCommanderProduits();
    }
    
    public static StockGestion getInstance(){
        if(instance == null){
            instance = new StockGestion();
        }
        return instance;
    }
    
    public void refreshInventaire(){
        inventaire.refreshTable(articleControlleur.getAllArticles());
    }
    
    public void refreshCommanderProduits(){
        commanderProduit.refreshTable(articleControlleur.getArticlesACommander());
    }
}
