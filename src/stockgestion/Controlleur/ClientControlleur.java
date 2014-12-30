package stockgestion.Controlleur;

import java.util.List;
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
     * 
     * @param client
     */
    public void calculerSomme(Client client) {
        double somme = 0;
        
        for(Article article : client.getListArticles()){
            somme += article.getPrix();
        }
        client.setSomme(somme);
    }

}