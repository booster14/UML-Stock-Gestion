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

/**
 * Manager des Articles
 * @author 7h1b0
 */
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
	 * Ajoute un Article dans la BDD
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
            
            int id = get(article.getNom()).getId();
            ajouterFournisseur(id,article.getListFournisseur());
	}

	/**
	 * Supprime un Article de la base de donnée
	 * @param article
	 */
	public void supprimer(Article article) {
            int id = article.getId();
            
            try {
                Statement statement = connexion.createStatement();
                String string = "DELETE FROM FOURNI WHERE ID_ARTICLE ="+id;
                statement.executeUpdate(string);
                
                string = "DELETE FROM ARTICLE WHERE ID = "+id;
                statement.executeUpdate(string);
            } catch (SQLException ex) {
                Logger.getLogger(ArticleManagerBDD.class.getName()).log(Level.SEVERE, null, ex);
            } 
	}

	/**
	 * Edite les propriétés d'un Article dans le BDD
	 * @param article
	 */
	public void editer(Article article) {
            int id = article.getId();
            String nom = article.getNom();
            int quantite = article.getQuantite();
            double prix = article.getPrix();
            int codeBarre = article.getCodeBarre();
            int seuilDeReassortiment = article.getSeuilDeReassortiment();
            boolean typeDeVente = article.isTypeDeVente();

            String query = "UPDATE ARTICLE SET NOM ='"+nom+"',QUANTITE = "+quantite+",PRIX ="+prix+",CODEBARRE = "+codeBarre+",SEUILDEREASSORTIMENT="+seuilDeReassortiment+", TYPEDEVENTE ="+typeDeVente+" WHERE ID ="+id;
            PreparedStatement statement;
            try {
                statement = connexion.prepareStatement(query);
                statement.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(FournisseurManagerBDD.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            // -------------------------------------------
            // Mise à jour des fournisseurs
            
            // Suppression des fournisseurs
            try{
                Statement statement2 = connexion.createStatement();
                String string = "DELETE FROM FOURNI WHERE ID_ARTICLE ="+id;
                statement2.executeUpdate(string);
            } catch (SQLException ex) {
                Logger.getLogger(ArticleManagerBDD.class.getName()).log(Level.SEVERE, null, ex);
            } 
            
            // AJout des fournisseurs
            ajouterFournisseur(id,article.getListFournisseur());
	}

	/**
	 * Retourne un Article à partir de son ID
	 * @param id
         * @return Article
	 */
	public Article get(int id) {
            Article article = new Article();
            
            try { 
                Statement statement = connexion.createStatement();
                String string = "SELECT ID,NOM,QUANTITE,PRIX,CODEBARRE,SEUILDEREASSORTIMENT, TYPEDEVENTE FROM ARTICLE WHERE ID ="+id;
                ResultSet resultat = statement.executeQuery(string);
                resultat.next();
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
                article.setListFournisseur(getListFournisseur(id));
                
            } 
            catch (SQLException ex) {
                Logger.getLogger(ArticleManagerBDD.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return article;
	}
        
        /**
	 * Retourne un Article à partir de son Code barre
	 * @param codebarre
         * @return Article
	 */
	public Article getByCodebarre(int codebarre) {
            Article article = new Article();
            
            try { 
                Statement statement = connexion.createStatement();
                String string = "SELECT ID,NOM,QUANTITE,PRIX,CODEBARRE,SEUILDEREASSORTIMENT, TYPEDEVENTE FROM ARTICLE WHERE CODEBARRE ="+codebarre;
                ResultSet resultat = statement.executeQuery(string);
                resultat.next();
                int id = resultat.getInt("ID");
                String nom = resultat.getString("NOM");
                int quantite = resultat.getInt("QUANTITE");
                double prix = resultat.getDouble("PRIX");
                int seuilDeReassortiment = resultat.getInt("SEUILDEREASSORTIMENT");
                boolean typeDeVente = resultat.getBoolean("TYPEDEVENTE");
                
                article.setId(id);
                article.setNom(nom);
                article.setQuantite(quantite);
                article.setPrix(prix);
                article.setCodeBarre(codebarre);
                article.setSeuilDeReassortiment(seuilDeReassortiment);
                article.setTypeDeVente(typeDeVente);
                article.setListFournisseur(getListFournisseur(id));
                
            } 
            catch (SQLException ex) {
                Logger.getLogger(ArticleManagerBDD.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return article;
	}
        
        /**
         * Retourne un Article à partir de son nom
         * @param nom
         * @return Article
         */
        public Article get(String nom) {
            Article article = new Article();
            
            try { 
                Statement statement = connexion.createStatement();
                String string = "SELECT ID,NOM,QUANTITE,PRIX,CODEBARRE,SEUILDEREASSORTIMENT, TYPEDEVENTE FROM ARTICLE WHERE NOM='"+nom+"'";
                ResultSet resultat = statement.executeQuery(string);
                resultat.next();
                int id = resultat.getInt("ID");
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
                article.setListFournisseur(getListFournisseur(id));
                
            } 
            catch (SQLException ex) {
                Logger.getLogger(ArticleManagerBDD.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return article;
	}

        /** 
         * Retourne la liste de tous les articles
         * @return List<Article>
         */
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
                    article.setListFournisseur(getListFournisseur(id));

                    listArticle.add(article);
                }            
            } 
            catch (SQLException ex) {
                Logger.getLogger(ArticleManagerBDD.class.getName()).log(Level.SEVERE, null, ex);
            }
            return listArticle;
	}

        /**
         * Retourne la liste de tous les articles dont la quantité est inférieur à seuil de réassortiment
         * @return List<Article>
         */
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
                    article.setListFournisseur(getListFournisseur(id));

                    listArticle.add(article);
                }            
            } 
            catch (SQLException ex) {
                Logger.getLogger(ArticleManagerBDD.class.getName()).log(Level.SEVERE, null, ex);
            }
            return listArticle;
	}
        
        /**
         * Retourne les fournisseurs à partir de l'ID d'un article
         * @param id_article
         * @return List<Fournisseur>
         */
        private List<Fournisseur> getListFournisseur(int id_article){
            
            List<Fournisseur> listFournisseur = new ArrayList<Fournisseur>();
            try{
                Statement statement = connexion.createStatement();
                String req = "SELECT ID_FOURNISSEUR FROM FOURNI WHERE ID_ARTICLE ="+id_article;
                ResultSet resultat = statement.executeQuery(req);
                while(resultat.next()){
                    int id_fournisseur = resultat.getInt("ID_FOURNISSEUR");
                    Fournisseur fournisseur = FournisseurManagerBDD.getInstance().get(id_fournisseur);
                    listFournisseur.add(fournisseur);
                }  
            }
            catch (SQLException ex) {
                Logger.getLogger(ArticleManagerBDD.class.getName()).log(Level.SEVERE, null, ex);
            }     
            
            return listFournisseur;
        }
        
        /**
         * Ajoute des fournisseurs à un article
         * @param id ( Id de l'article )
         * @param listFournisseur 
         */
        private void ajouterFournisseur(int id,List<Fournisseur> listFournisseur){
            PreparedStatement statement;
            
            for(Fournisseur fournisseur : listFournisseur){
                try{     
                    String query ="INSERT INTO FOURNI(ID_ARTICLE,ID_FOURNISSEUR) VALUES (?,?)";
                    statement = connexion.prepareStatement(query);
                    statement.setInt(1,id);
                    statement.setInt(2,fournisseur.getId());
                    statement.executeUpdate();
                } catch (SQLException ex) {
                    Logger.getLogger(ArticleManagerBDD.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
}