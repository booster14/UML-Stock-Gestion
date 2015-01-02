package stockgestion.Entite;

import java.util.HashMap;

public class Client {

    private HashMap<Article, Integer> listArticles;
    private double somme;
    
    public Client(){
        this.listArticles = new HashMap<Article, Integer>();
    }

    public HashMap<Article, Integer> getListArticles() {
        return listArticles;
    }

    public void setListArticles(HashMap<Article, Integer> listArticles) {
        this.listArticles = listArticles;
    }

    public double getSomme() {
        return somme;
    }

    public void setSomme(double somme) {
        this.somme = somme;
    }
}