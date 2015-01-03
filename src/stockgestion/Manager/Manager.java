package stockgestion.Manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe abstraite permettant au Manager de se connecter à la BDD
 */
public abstract class Manager {
    protected Connection connexion;
    
    /**
     * Effectue la connection avec la base de donnée
     */
    public void connection(){
    
        /* Chargement du driver JDBC */
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e ) {
            e.printStackTrace();
        }

        /* Connexion à la base de données */
        String url = "jdbc:derby://localhost:1527/uml";
        String utilisateur = "uml";
        String motDePasse = "groupe3";
        try {
            connexion = DriverManager.getConnection( url, utilisateur, motDePasse );
        }
        catch ( SQLException e ) {
            e.printStackTrace();
        }
    }
}
