package stockgestion.Controlleur;

import java.util.List;
import java.util.Random;
import stockgestion.Entite.*;
import stockgestion.Manager.ArticleManagerBDD;

public class ArticleControlleur {
    private ArticleManagerBDD bdd;
    private static ArticleControlleur instance = null;
    
    private ArticleControlleur(){
        bdd = ArticleManagerBDD.getInstance();       
    }
    
    public static ArticleControlleur getInstance(){
        if(instance == null){
            instance = new ArticleControlleur();
        }
        return instance;
    }

    /**
     * 
     * @param article article a ajouter
     */
    public void ajouter(Article article) {
        bdd.ajouter(article);
    }

    /**
     * 
     * @param article article a supprimer
     */
    public void supprimer(Article article) {
        bdd.supprimer(article);
    }

    /**
     * 
     * @param article article a modifier
     */
    public void editer(Article article) {
        bdd.editer(article);
    }

    /**
     * 
     * @param id l'id de l'article
     */
    public Article getArticle(int id) {
        return bdd.get(id);
    }
    
    /**
     * 
     * @param codeBarre le code barre de l'article
     */
    public Article getArticleByCodebarre(int codeBarre) {
        return bdd.getByCodebarre(codeBarre);
    }

    public List<Article> getAllArticles() {
        return bdd.getAll();
    }

    public List<Article> getArticlesACommander() {
        return bdd.getACommander();
    }
    
    public Article getRandomArticle(){
        List<Article> list = getAllArticles();
        Random rand = new Random();
        int randomNum = rand.nextInt((list.size() - 0));
        
        Article a = list.get(randomNum);
        if(a.getQuantite() > 0)
            return a;
        else 
            return getRandomArticle();
    }

}