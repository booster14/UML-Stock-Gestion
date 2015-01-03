package stockgestion.Controlleur;

import java.util.Map;
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

        for(Map.Entry <Article, Integer> entry : client.getListArticles().entrySet()){
            if(entry.getKey().getId() == article.getId()){
                entry.setValue(entry.getValue() + 1);
                return;
            }
        }
        
        client.getListArticles().put(article, 1);
    }

    /**
     * Après appuie sur la touche <Total>, le tiroir s'ouvre et le montant de la vente est calculer et s'affiche
     * @param client
     * @return Double
     */
    public double calculerSomme(Client client) {
        double somme = 0;
        for(Map.Entry <Article, Integer> entry : client.getListArticles().entrySet()){
            if(entry.getValue() > 0){
                somme += entry.getKey().getPrix() * entry.getValue();
            }
        }
        
        client.setSomme(somme);
        return somme;
    }
    
    /** 
     * Après la fermeture du tiroir, le système met à jour les quantités des articles
     * @param client 
     */
    public void terminerVente(Client client){
        
        // Mise à jour des quantités
         for(Map.Entry <Article, Integer> entry : client.getListArticles().entrySet()){
            int quantite = ArticleManagerBDD.getInstance().get(entry.getKey().getId()).getQuantite(); // récupère la quantité dans le BDD et pas dans l'objet
            entry.getKey().setQuantite(quantite-entry.getValue());
            ArticleManagerBDD.getInstance().editer(entry.getKey());
        }
    }
    
    /**
     * Après appuis sur la touche <Retour>, le caissier peut récupérer un article
     * @param client
     * @param article
     */
    public void retournerArticle(Client client, Article article){
        int quantite = ArticleManagerBDD.getInstance().get(article.getId()).getQuantite();
        article.setQuantite(quantite + 1);
        ArticleManagerBDD.getInstance().editer(article);
        
        // et on ajoute la modif dans la listArticles
        client.getListArticles().put(article, -1);
    }
    
    /**
     * Vide la liste d'articles du client
     * @param client
     */
    public void resetArticles(Client client){
        client.getListArticles().clear();
    }
}