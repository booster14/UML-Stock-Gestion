package stockgestion;

import stockgestion.Controlleur.*;
import stockgestion.UI.*;
import stockgestion.Entite.Fournisseur;
import java.util.List;

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
    private ViewArticle viewArticle;
    
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
        viewArticle = ViewArticle.getInstance();
        
        //Initialisation des fenetres
        refreshUI();
    }
    
    public static StockGestion getInstance(){
        if(instance == null){
            instance = new StockGestion();
        }
        return instance;
    }
    
    private void refreshInventaire(){
        inventaire.refreshTable(articleControlleur.getAllArticles());
    }
    
    private void refreshCommanderProduits(){
        commanderProduit.refreshTable(articleControlleur.getArticlesACommander());
    }
    
    private void refreshListeFournisseur(){
        List<Fournisseur> list = fournisseurControlleur.getAllFournisseur();
        ajouterArticle.refreshListeFournisseur(list);
        viewArticle.refreshListeFournisseur(list);
    }
    
    public void refreshUI(){
        refreshCommanderProduits();
        refreshInventaire();
        refreshListeFournisseur();
    }
}
