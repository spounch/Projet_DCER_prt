/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet_dcer_prt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Interaction_bdd extends Projet_DCER_prt {
    

// on va lire l'element a dans la table b
public Interaction_bdd (String element, String table) throws SQLException {
    
    try {

    /* Exécution d'une requête de lecture */
    String sql = "SELECT " + element + " FROM ROOT." + table;
    rs = st.executeQuery(sql);

            while (rs.next()) {
                System.out.println(rs.getString(element));
            }
    // Requête de lecture //

    }  catch ( SQLException e ) {
        System.out.println("erreur requête :");
        System.out.println(e);

    } /*finally {
        if ( connexion != null )
            try {
                System.out.println("connexion null");
                /* Fermeture de la connexion 
                connexion.close();
            } catch ( SQLException ignore ) {
                /* Si une erreur survient lors de la fermeture, il suffit de l'ignorer. 
            }
    }*/

}

@Override
public String toString() {
        return "ok";
    }
    
}
    

