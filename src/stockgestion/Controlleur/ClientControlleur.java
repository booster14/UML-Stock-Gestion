package stockgestion.Controlleur;

import stockgestion.Entite.*;
import stockgestion.Manager.ArticleManagerBDD;

public class ClientControlleur {
    private static ClientControlleur instance = null;
    
    private ClientControlleur(){}
    
    public static ClientControlleur getInstance(){
        if(instance == null){
            instance = new ClientControlleur();
        }
        return instance;
    }

    /**
     * Ajout un article dans la liste des articles du client à partir de l'ID de l'article
     * @param client
     * @param id_article
     */
    public void ajouterArticle(Client client,int id_article) {
        Article article = ArticleManagerBDD.getInstance().get(id_article);
        client.getListArticles().add(article);
    }

    /**
     * Après appuie sur la touche <Total>, le tiroir s'ouvre et le montant de la vente est calculer et s'affiche
     * @param client
     */
    public void calculerSomme(Client client) {
        double somme = 0;
        
        // Calcul du montant de la vente
        for(Article article : client.getListArticles()){
            somme += article.getPrix();
        }
        
        client.setSomme(somme);
    }
    
    /** 
     * Après la fermeture du tiroir, le système met à jour les quantités des articles
     * @param client 
     */
    public void terminerVente(Client client){
        
        // Mise à jour des quantités
         for(Article article : client.getListArticles()){
            int quantite = article.getQuantite();
            article.setQuantite(quantite-1);
            ArticleManagerBDD.getInstance().editer(article);
        }
    }

}