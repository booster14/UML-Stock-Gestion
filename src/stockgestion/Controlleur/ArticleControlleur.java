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
     * Ajouter un article
     * @param article article a ajouter
     */
    public void ajouter(Article article) {
        bdd.ajouter(article);
    }

    /**
     * Supprimer un article
     * @param article article a supprimer
     */
    public void supprimer(Article article) {
        bdd.supprimer(article);
    }

    /**
     * Editer un article
     * @param article article a modifier
     */
    public void editer(Article article) {
        bdd.editer(article);
    }

    /**
     * Obtenir un Article à partir de son ID
     * @param id l'id de l'article
     * @return Article
     */
    public Article getArticle(int id) {
        return bdd.get(id);
    }
    
    /**
     * Obtenir un Article à partir de son code barre
     * @param codeBarre le code barre de l'article
     * @return Article
     */
    public Article getArticleByCodebarre(int codeBarre) {
        return bdd.getByCodebarre(codeBarre);
    }

    /**
     * Obtenir la liste de tous les articles
     * @return List<Article> 
     */
    public List<Article> getAllArticles() {
        return bdd.getAll();
    }

    /**
     * Obtenir la liste de tous les articles à commander
     * @return List<Article>
     */
    public List<Article> getArticlesACommander() {
        return bdd.getACommander();
    }
    
    /**
     * Obtenir un article aléatoire
     * @return Article
     */
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