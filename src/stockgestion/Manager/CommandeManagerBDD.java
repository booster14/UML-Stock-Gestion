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
 * Manager des Commandes
 * @author 7h1b0
 */
public class CommandeManagerBDD extends Manager{
        private static CommandeManagerBDD instance = null;
    
        private CommandeManagerBDD(){
            super.connection();     
        }
    
        public static CommandeManagerBDD getInstance(){
            if(instance == null){
                instance = new CommandeManagerBDD();
            }
            return instance;
        }

	/**
	 * Ajoute une commande à la base de donnée
	 * @param commande
	 */
	public void ajouter(Commande commande) {
            int id_article = commande.getArticle().getId();
            int quantite = commande.getQuantite();
            String date = commande.getDate();
            double montant = commande.getMontant();

            String query = "INSERT INTO COMMANDE(ID_ARTICLE,QUANTITE,DATE_COMMANDE,MONTANT) VALUES (?,?,?,?)";
            PreparedStatement statement;
            try {
                statement = connexion.prepareStatement(query);
                statement.setInt(1, id_article);
                statement.setInt(2, quantite);
                statement.setString(3,date);
                statement.setDouble(4,montant);
                statement.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(CommandeManagerBDD.class.getName()).log(Level.SEVERE, null, ex);
            } 
	}

	/**
	 * Supprime une Commande de la base de donnée
	 * @param commande
	 */
	public void supprimer(Commande commande) {
            int id = commande.getId();
            
            try {
                Statement statement = connexion.createStatement();
                String string = "DELETE FROM COMMANDE WHERE ID = "+id;
                statement.executeUpdate(string);
            } catch (SQLException ex) {
                Logger.getLogger(CommandeManagerBDD.class.getName()).log(Level.SEVERE, null, ex);
            } 
	}
        
        /**
         * Détermine si un article est en commande ou non
         * @param id (Id de l'article
         * @return true si commandé, false sinon
         */
        public Boolean estCommande(int id){
            boolean estCommande = false;
            
            try{
                Statement statement = connexion.createStatement();
                String string = "SELECT ID FROM COMMANDE WHERE ID_ARTICLE ="+id;
                ResultSet resultat = statement.executeQuery(string);
                estCommande = resultat.next();
            }catch (SQLException ex) {
                Logger.getLogger(FournisseurManagerBDD.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return estCommande;
        }
        
        /**
         * Détermine si un article est en commande ou non
         * @param Article
         * @return true si commandé, false sinon
         */
        public Boolean estCommande(Article article){
            boolean estCommande = false;
            int id = article.getId();
            
            try{
                Statement statement = connexion.createStatement();
                String string = "SELECT ID FROM COMMANDE WHERE ID_ARTICLE ="+id;
                ResultSet resultat = statement.executeQuery(string);
                estCommande = resultat.next();
            }catch (SQLException ex) {
                Logger.getLogger(FournisseurManagerBDD.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return estCommande;
        }
        
        /**
	 * Retourne la commande d'un article
	 * @param article
         * @return Commande
	 */
	public Commande get(Article article) {
            Commande commande = new Commande();
            int id = article.getId();
            
            try { 
                Statement statement = connexion.createStatement();
                String string = "SELECT ID,ID_ARTICLE, QUANTITE, DATE_COMMANDE,MONTANT FROM COMMANDE WHERE ID_ARTICLE ="+id;
                ResultSet resultat = statement.executeQuery(string);
                resultat.next();
                int id_article = resultat.getInt("ID_ARTICLE");
                int quantite = resultat.getInt("QUANTITE");
                String date = resultat.getString("DATE_COMMANDE");
                double montant = resultat.getDouble("MONTANT");
                
                commande.setId(id);
                commande.setArticle(article);
                commande.setQuantite(quantite);
                commande.setDate(date);
                commande.setMontant(montant);
            } 
            catch (SQLException ex) {
                Logger.getLogger(FournisseurManagerBDD.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return commande;
	}
        

	/**
	 * Retourne une Commande à partir de son ID
	 * @param id ( ID de la commande )
         * @return Commande
	 */
	public Commande get(int id) {
            Commande commande = new Commande();
            
            try { 
                Statement statement = connexion.createStatement();
                String string = "SELECT ID,ID_ARTICLE, QUANTITE, DATE_COMMANDE,MONTANT FROM COMMANDE WHERE ID ="+id;
                ResultSet resultat = statement.executeQuery(string);
                resultat.next();
                int id_article = resultat.getInt("ID_ARTICLE");
                int quantite = resultat.getInt("QUANTITE");
                String date = resultat.getString("DATE_COMMANDE");
                double montant = resultat.getDouble("MONTANT");

                Article article = ArticleManagerBDD.getInstance().get(id_article);
                commande.setId(id);
                commande.setArticle(article);
                commande.setQuantite(quantite);
                commande.setDate(date);
                commande.setMontant(montant);
            } 
            catch (SQLException ex) {
                Logger.getLogger(FournisseurManagerBDD.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return commande;
	}

        /**
         * Retourne la liste de toutes les commandes
         * @return List<Commande>
         */
	public List<Commande> getAll() {
            List<Commande> listcommande = new ArrayList<Commande>();
            
            try { 
                Statement statement = connexion.createStatement();
                String string = "SELECT ID,ID_ARTICLE, QUANTITE, DATE_COMMANDE,MONTANT FROM COMMANDE";
                ResultSet resultat = statement.executeQuery(string);
                while(resultat.next()){
                    int id = resultat.getInt("ID");
                    int id_article = resultat.getInt("ID_ARTICLE");
                    int quantite = resultat.getInt("QUANTITE");
                    String date = resultat.getString("DATE_COMMANDE");
                    double montant = resultat.getDouble("MONTANT");
                    Commande commande = new Commande();
                    
                    Article article = ArticleManagerBDD.getInstance().get(id_article);
                    commande.setId(id);
                    commande.setArticle(article);
                    commande.setQuantite(quantite);
                    commande.setDate(date);
                    commande.setMontant(montant); 
                    
                    listcommande.add(commande);
                }
                
            } 
            catch (SQLException ex) {
                Logger.getLogger(FournisseurManagerBDD.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return listcommande;
	}

}