package stockgestion.Manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import stockgestion.Entite.*;

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
	 * 
	 * @param commande
	 */
	public void ajouter(Commande commande) {
            int id_article = commande.getArticle().getId();
            int quantite = commande.getQuantite();

            String query = "INSERT INTO COMMANDE(ID_ARTICLE,QUANTITE) VALUES (?,?)";
            PreparedStatement statement;
            try {
                statement = connexion.prepareStatement(query);
                statement.setInt(1, id_article);
                statement.setInt(2, quantite);
                statement.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(CommandeManagerBDD.class.getName()).log(Level.SEVERE, null, ex);
            } 
	}

	/**
	 * 
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
	 * 
	 * @param commande
	 */
	public void editer(Commande commande) {
		// TODO - implement CommandeManagerBDD.editer
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param id
	 */
	public Commande get(int id) {
            Commande commande = new Commande();
            
            try { 
                Statement statement = connexion.createStatement();
                String string = "SELECT ID,ID_ARTICLE, QUANTITE WHERE ID ="+id;
                ResultSet resultat = statement.executeQuery(string);
                resultat.next();
                int id_article = resultat.getInt("ID_ARTICLE");
                int quantite = resultat.getInt("QUANTITE");

                Article article = ArticleManagerBDD.getInstance().get(id_article);
                commande.setArticle(article);
                commande.setQuantite(quantite);
            } 
            catch (SQLException ex) {
                Logger.getLogger(FournisseurManagerBDD.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return commande;
	}

	public List<Commande> getAll() {
		// TODO - implement CommandeManagerBDD.getAllCommandes
		throw new UnsupportedOperationException();
	}

}