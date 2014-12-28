package stockgestion.Manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import stockgestion.Entite.*;

public class ArticleManagerBDD extends Manager{
        private static ArticleManagerBDD instance = null;
        
        private ArticleManagerBDD(){
            super.connection();     
        }
    
        public static ArticleManagerBDD getInstance(){
            if(instance == null){
                instance = new ArticleManagerBDD();
            }
            return instance;
        }

	/**
	 * 
	 * @param article
	 */
	public void ajouter(Article article) {
            String nom = article.getNom();
            int quantite = article.getQuantite();
            double prix = article.getPrix();
            int codeBarre = article.getCodeBarre();
            int seuilDeReassortiment = article.getSeuilDeReassortiment();
            boolean typeDeVente = article.isTypeDeVente();

            String query = "INSERT INTO ARTICLE(NOM,QUANTITE,PRIX,CODEBARRE,SEUILDEREASSORTIMENT, TYPEDEVENTE) VALUES (?,?,?,?,?,?)";
            PreparedStatement statement;
            try {
                statement = connexion.prepareStatement(query);
                statement.setString(1, nom);
                statement.setInt(2, quantite);
                statement.setDouble(3, prix);
                statement.setInt(4, codeBarre);
                statement.setInt(5, seuilDeReassortiment);
                statement.setBoolean(6,typeDeVente);
                statement.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(ArticleManagerBDD.class.getName()).log(Level.SEVERE, null, ex);
            }  
	}

	/**
	 * 
	 * @param article
	 */
	public void supprimer(Article article) {
            int id = article.getId();
            
            try {
                Statement statement = connexion.createStatement();
                String string = "DELETE FROM ARTICLE WHERE ID = "+id;
                statement.executeUpdate(string);
            } catch (SQLException ex) {
                Logger.getLogger(ArticleManagerBDD.class.getName()).log(Level.SEVERE, null, ex);
            } 
	}

	/**
	 * 
	 * @param article
	 */
	public void editer(Article article) {
		// TODO - implement ArticleManagerBDD.editer
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param codeBarre
	 */
	public Article get(int codeBarre) {
            Article article = new Article();
            
            try { 
                Statement statement = connexion.createStatement();
                String string = "SELECT ID,NOM,QUANTITE,PRIX,CODEBARRE,SEUILDEREASSORTIMENT, TYPEDEVENTE FROM ARTICLE WHERE CODEBARRE ="+codeBarre;
                ResultSet resultat = statement.executeQuery(string);
                resultat.next();
                int id = resultat.getInt("ID");
                String nom = resultat.getString("NOM");
                int quantite = resultat.getInt("QUANTITE");
                double prix = resultat.getDouble("PRIX");
                int codebarre = resultat.getInt("CODEBARRE");
                int seuilDeReassortiment = resultat.getInt("SEUILDEREASSORTIMENT");
                boolean typeDeVente = resultat.getBoolean("TYPEDEVENTE");

                article.setId(id);
                article.setNom(nom);
                article.setQuantite(quantite);
                article.setPrix(prix);
                article.setCodeBarre(codebarre);
                article.setSeuilDeReassortiment(seuilDeReassortiment);
                article.setTypeDeVente(typeDeVente);        
            } 
            catch (SQLException ex) {
                Logger.getLogger(ArticleManagerBDD.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return article;
	}

	public List<Article> getAll() {
            List<Article> listArticle = new ArrayList<Article>();

            try { 
                Statement statement = connexion.createStatement();
                String string = "SELECT ID,NOM,QUANTITE,PRIX,CODEBARRE,SEUILDEREASSORTIMENT, TYPEDEVENTE FROM ARTICLE";
                ResultSet resultat = statement.executeQuery(string);
                while(resultat.next()){
                    Article article = new Article();
                    int id = resultat.getInt("ID");
                    String nom = resultat.getString("NOM");
                    int quantite = resultat.getInt("QUANTITE");
                    double prix = resultat.getDouble("PRIX");
                    int codebarre = resultat.getInt("CODEBARRE");
                    int seuilDeReassortiment = resultat.getInt("SEUILDEREASSORTIMENT");
                    boolean typeDeVente = resultat.getBoolean("TYPEDEVENTE");

                    article.setId(id);
                    article.setNom(nom);
                    article.setQuantite(quantite);
                    article.setPrix(prix);
                    article.setCodeBarre(codebarre);
                    article.setSeuilDeReassortiment(seuilDeReassortiment);
                    article.setTypeDeVente(typeDeVente);

                    listArticle.add(article);
                }            
            } 
            catch (SQLException ex) {
                Logger.getLogger(ArticleManagerBDD.class.getName()).log(Level.SEVERE, null, ex);
            }
            return listArticle;
	}

	public List<Article> getACommander() {
            List<Article> listArticle = new ArrayList<Article>();

            try { 
                Statement statement = connexion.createStatement();
                String string = "SELECT ID,NOM,QUANTITE,PRIX,CODEBARRE,SEUILDEREASSORTIMENT, TYPEDEVENTE FROM ARTICLE WHERE QUANTITE < SEUILDEREASSORTIMENT";
                ResultSet resultat = statement.executeQuery(string);
                while(resultat.next()){
                    Article article = new Article();
                    int id = resultat.getInt("ID");
                    String nom = resultat.getString("NOM");
                    int quantite = resultat.getInt("QUANTITE");
                    double prix = resultat.getDouble("PRIX");
                    int codebarre = resultat.getInt("CODEBARRE");
                    int seuilDeReassortiment = resultat.getInt("SEUILDEREASSORTIMENT");
                    boolean typeDeVente = resultat.getBoolean("TYPEDEVENTE");

                    article.setId(id);
                    article.setNom(nom);
                    article.setQuantite(quantite);
                    article.setPrix(prix);
                    article.setCodeBarre(codebarre);
                    article.setSeuilDeReassortiment(seuilDeReassortiment);
                    article.setTypeDeVente(typeDeVente);

                    listArticle.add(article);
                }            
            } 
            catch (SQLException ex) {
                Logger.getLogger(ArticleManagerBDD.class.getName()).log(Level.SEVERE, null, ex);
            }
            return listArticle;
	}

}