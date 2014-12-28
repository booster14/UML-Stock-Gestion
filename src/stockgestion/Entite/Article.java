package stockgestion.Entite;

public class Article {

    private String nom;
    private int quantite;
    private int codeBarre;
    private double prix;
    private int seuillDeReassortiment;
    private boolean typeDeVente;

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

    public int getSeuillDeReassortiment() {
        return seuillDeReassortiment;
    }

    public void setSeuillDeReassortiment(int seuillDeReassortiment) {
        this.seuillDeReassortiment = seuillDeReassortiment;
    }

    public boolean isTypeDeVente() {
        return typeDeVente;
    }

    public void setTypeDeVente(boolean typeDeVente) {
        this.typeDeVente = typeDeVente;
    }
}