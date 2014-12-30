package stockgestion.Entite;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Commande {

    private Article article;
    private int quantite;
    private String date;
    private double montant;
    
    public Commande(){};
    
    public Commande(Article article, int quantite){
        this.article = article;
        this.quantite = quantite;
        this.montant = quantite * article.getPrix();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        this.date = dateFormat.format(date);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    private int id;

}