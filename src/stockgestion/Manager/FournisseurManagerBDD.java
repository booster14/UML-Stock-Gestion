package stockgestion.Manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import stockgestion.Entite.*;

public class FournisseurManagerBDD extends Manager{

        private static FournisseurManagerBDD instance = null;
    
        private FournisseurManagerBDD(){
            super.connection();     
        }
    
        public static FournisseurManagerBDD getInstance(){
            if(instance == null){
                instance = new FournisseurManagerBDD();
            }
            return instance;
        }
	/**
	 * 
	 * @param fournisseur
	 */
	public void ajouter(Fournisseur fournisseur) {
                
            String nom = fournisseur.getNom();
            String adresse = fournisseur.getAdresse();
            int codePostal = fournisseur.getCodePostal();
            int telephone = fournisseur.getNumeroTelephone();

            String query = "INSERT INTO FOURNISSEUR(NOM,ADRESSE,CODEPOSTAL,NUMEROTELEPHONE) VALUES (?,?,?,?)";
            PreparedStatement statement;
            try {
                statement = connexion.prepareStatement(query);
                statement.setString(1, nom);
                statement.setString(2, adresse);
                statement.setInt(3, codePostal);
                statement.setInt(4, telephone);
                statement.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(FournisseurManagerBDD.class.getName()).log(Level.SEVERE, null, ex);
            }  
	}

	/**
	 * 
	 * @param fournisseur
	 */
	public void supprimer(Fournisseur fournisseur) {
            int id = fournisseur.getId();
            
            try {
                Statement statement = connexion.createStatement();
                String string = "DELETE FROM FOURNISSEUR WHERE ID = "+id;
                statement.executeUpdate(string);
            } catch (SQLException ex) {
                Logger.getLogger(ArticleManagerBDD.class.getName()).log(Level.SEVERE, null, ex);
            } 
	}

	/**
	 * 
	 * @param fournisseur
	 */
	public void editer(Fournisseur fournisseur) {
		// TODO - implement FournisseurManagerBDD.editer
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param id
	 */
	public Fournisseur get(int id) {
            Fournisseur fournisseur = new Fournisseur();
            
            try { 
                Statement statement = connexion.createStatement();
                String string = "SELECT ID,NOM,ADRESSE,CODEPOSTAL,NUMEROTELEPHONE FROM FOURNISSEUR WHERE ID ="+id;
                ResultSet resultat = statement.executeQuery(string);
                resultat.next();
                String nom = resultat.getString("NOM");
                String adresse = resultat.getString("ADRESSE");
                int codePostal = resultat.getInt("CODEPOSTAL");
                int telephone = resultat.getInt("NUMEROTELEPHONE");

                fournisseur.setId(id);
                fournisseur.setNom(nom);
                fournisseur.setAdresse(adresse);
                fournisseur.setCodePostal(codePostal);
                fournisseur.setNumeroTelephone(telephone);       
            } 
            catch (SQLException ex) {
                Logger.getLogger(FournisseurManagerBDD.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return fournisseur;
	}

	public List<Fournisseur> getAll() {
		// TODO - implement FournisseurManagerBDD.getAllFournisseurs
		throw new UnsupportedOperationException();
	}

}