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
    
    /**
     * Mettre à jour la vue Inventaire
     */
    private void refreshInventaire(){
        inventaire.refreshTable(articleControlleur.getAllArticles());
    }
    
    /**
     * Mettre à jour la vue Produit à commander
     */
    private void refreshCommanderProduits(){
        commanderProduit.refreshTable(articleControlleur.getArticlesACommander());
    }
    
    /**
     * Mettre à jour la liste des fournisseurs
     */
    private void refreshListeFournisseur(){
        List<Fournisseur> list = fournisseurControlleur.getAllFournisseur();
        ajouterArticle.refreshListeFournisseur(list);
        viewArticle.refreshListeFournisseur(list);
    }
    
    /**
     * Mettre à jour l'affichage
     */
    public void refreshUI(){
        refreshCommanderProduits();
        refreshInventaire();
        refreshListeFournisseur();
    }
}
