package stockgestion.Controlleur;

import java.util.List;
import stockgestion.Entite.*;

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
     * 
     * @param client
     * @param article
     */
    public void ajouterArticle(Client client, Article article) {
        client.getListArticles().add(article);
    }

    /**
     * 
     * @param client
     */
    public void calculerSomme(Client client) {
        double somme = 0;
        List<Article> listArticles = client.getListArticles();
        for(Article article: listArticles){
            somme += article.getPrix() * article.getQuantite();
        }
        client.setSomme(somme);
    }

}