package stockgestion.Entite;

import java.util.List;

public class Article {

    private int id;
    private String nom;
    private int quantite;
    private int codeBarre;
    private double prix;
    private int seuilDeReassortiment;
    private boolean typeDeVente;
    private List<Fournisseur> listFournisseur;

    public List<Fournisseur> getListFournisseur() {
        return listFournisseur;
    }

    public void setListFournisseur(List<Fournisseur> listFournisseur) {
        this.listFournisseur = listFournisseur;
    }

    
    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getCodeBarre() {
        return codeBarre;
    }

    public void setCodeBarre(int codeBarre) {
        this.codeBarre = codeBarre;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getSeuilDeReassortiment() {
        return seuilDeReassortiment;
    }

    public void setSeuilDeReassortiment(int seuilDeReassortiment) {
        this.seuilDeReassortiment = seuilDeReassortiment;
    }

    public boolean isTypeDeVente() {
        return typeDeVente;
    }

    public void setTypeDeVente(boolean typeDeVente) {
        this.typeDeVente = typeDeVente;
    }
    
    public String listFournisseurToString(){
        String s = "";
        for(Fournisseur fournisseur : listFournisseur){
            s += fournisseur.getNom() + ",";
        }
        return s;
    }
}