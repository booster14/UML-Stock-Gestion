package stockgestion.Entite;

import java.util.ArrayList;
import java.util.List;

public class Client {

    private List<Article> listArticles;
    private double somme;
    
    public Client(){
        this.listArticles = new ArrayList<Article>();
    }

    public List<Article> getListArticles() {
        return listArticles;
    }

    public void setListArticles(List<Article> listArticles) {
        this.listArticles = listArticles;
    }

    public double getSomme() {
        return somme;
    }

    public void setSomme(double somme) {
        this.somme = somme;
    }
}