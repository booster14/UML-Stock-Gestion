/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockgestion.Manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author 7h1b0
 */
public abstract class Manager {
    protected Connection connexion;
    
    public void connection(){
    
        /* Chargement du driver JDBC */
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e ) {
            System.out.println("here");
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
            System.out.println("Ou ici");
            e.printStackTrace();
        }
    }
}
