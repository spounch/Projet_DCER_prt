/*
Auteur : Jérémie Pannequin
18/11/2016
Version 1.0
Mise en place de la bdd
    Liste_defauts
    Liste_solutions
Interface graphique
    Affichage du numéro de panne et sa description de la panne en titre de fenêtre
    Affichage des solutions correspondantes à une panne
*/

package projet_dcer_prt;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Window;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import static sun.audio.AudioDevice.device;
import sun.misc.Cache;

public class Projet_DCER_prt {

        public static Connection connexion = null;    // variable pour la connexion à la bdd
        public static Statement st = null;            // Déclaration d'un statement pour faire une requête sql
        public static ResultSet rs = null;            // Déclaration d'un resultSet pour lire dans la bdd
        public static PreparedStatement st2 = null;   // un statement que dont on peut modifier la requête avec des ?
        
    public static void connexionBase() throws SQLException {
                /* Connexion à la base de données */
            String url = "jdbc:derby://localhost:1527/prt_dcer";
            String utilisateur = "root";
            String motDePasse = "root";
            connexion = null;
            try {
                connexion = DriverManager.getConnection( url, utilisateur, motDePasse);

            } catch ( SQLException e ) {
                System.out.println(e);
                /* Gérer les éventuelles erreurs ici */ }

            st = connexion.createStatement();
            rs = null;
            
                    // Connexion à la base
                /* Chargement du driver JDBC pour MySQL */
            try {
                Class.forName( "com.derby.jdbc.Driver" );
            } catch ( ClassNotFoundException e ) {
                /* Gérer les éventuelles erreurs ici. */
            }
        
        }
        
    // méthode pour récupérer la liste des solutions d'un défaut précis
    public static ArrayList<Object[]> getListeSolution(int code_erreur){
            
            ArrayList<Object[]> ListeSolution = new ArrayList<>();
            String a = null;
            Object[] row = {a};
            
            try{
            String sql = "select * from APP.LISTE_SOLUTIONS where ID_DEFAUT = ? ";
            
            st2 =connexion.prepareStatement(sql);
            st2.setString(1,String.valueOf(code_erreur));
            
            rs = st2.executeQuery();    
            while (rs.next()) {
                
                // on récupère chaque données et on les mets dans un Object[]
                String nom_solution = rs.getString("NOM_SOLUTION");
                String type = rs.getString("TYPE");
                Date date = rs.getDate("DATE");
                int fiabilite = rs.getInt("FIABILITE");
                boolean presence_documentation = rs.getBoolean("PRESENCE_DOCUMENTATION");
                boolean presence_photo = rs.getBoolean("PRESENCE_PHOTO");
                //int ID_solution = rs.getInt("ID_SOLUTION");
                // on met tt les données dans un Object[]
                
                row = new String[] {nom_solution,type,String.valueOf(date),String.valueOf(fiabilite),
                    String.valueOf(presence_documentation),String.valueOf(presence_photo)};
                //row = {nom_solution + type + date + fiabilite + presence_documentation + presence_photo};
                ListeSolution.add(row);

                    }
            //row = {nom_solution};
            //ListeSolution.add(row);
            }
             catch(Exception e)
            {
                System.out.println(e);
                System.out.println("erreur requête");
                return null;
            }
            
            
            return ListeSolution;
            
        }
        
    public static String getDescriptionDefaut (int defaut){
        String DescriptionDefaut = null;
        try{
            String sql = "select distinct (DESCRIPTION_DEFAUT) from APP.LISTE_DEFAUTS where ID_DEFAUT = ? ";
            st2 =connexion.prepareStatement(sql);
            st2.setString(1,String.valueOf(defaut));
            rs=st2.executeQuery();
            rs.next();
            DescriptionDefaut = rs.getString("DESCRIPTION_DEFAUT");
        }
         catch(Exception e)
            {
                System.out.println(e);
                System.out.println("erreur requête, DescriptionDefaut");
                return null;
            }
        
        return DescriptionDefaut;
    }
       
    public static ArrayList<String> getInfosSolution(String nomSolution, int code_erreur) {
       
        ArrayList<String> InfosSolution = new ArrayList<>();
        try {
            String sql = "select * from APP.LISTE_SOLUTIONS where nom_solution = ? and  id_defaut = ?";
            st2 = connexion.prepareStatement(sql);
            st2.setString(1, nomSolution);
            st2.setString(2, String.valueOf(code_erreur));
            rs=st2.executeQuery();
            rs.next();
            InfosSolution.add(rs.getString("description"));
            InfosSolution.add(String.valueOf(rs.getBoolean("PRESENCE_DOCUMENTATION")));
            InfosSolution.add(String.valueOf(rs.getBoolean("PRESENCE_PHOTO")));
            InfosSolution.add(String.valueOf(rs.getInt("fiabilite")));
            InfosSolution.add(rs.getString("type"));
            InfosSolution.add(String.valueOf(rs.getInt("nombre_votes")));
            InfosSolution.add(String.valueOf(rs.getInt("cause_traitee")));
        }
        
        catch(Exception e)
            {
                System.out.println(e);
                System.out.println("erreur requête, DescriptionSolution");
                return null;
            }
        
        return InfosSolution;
        // description, presence_documentation, presence_photo, fiabilité, type, nb_votes, cause_traités
    }
    
    public static void main(String[] args) throws SQLException {
  
        connexionBase(); 
        
        // lancement de la fenêtre d'acceuil
        int code_erreur = 2;
        // on récupère la description du code defaut
        String descriptionDefaut = getDescriptionDefaut(code_erreur);
        JFrame fenetre = new acceuil(code_erreur, descriptionDefaut);
        fenetre.setExtendedState(JFrame.MAXIMIZED_BOTH);
        fenetre.setVisible(true);
        fenetre.setTitle("Logiciel DCER");
        //Termine le processus lorsqu'on clique sur la croix rouge
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        

            }
    
    
    public static String getMdpEmploye(String matricule){
      
    String mdp = null;    
    try{
    String sql = "select mdp from APP.EMPLOYES where matricule = ?";
    st2 =connexion.prepareStatement(sql);
    st2.setString(1,matricule);
    
    rs = st2.executeQuery();    
    while (rs.next()) {
        mdp = rs.getString("mdp");
            }
    }
    
    catch(Exception e)
    {
        System.out.println(e);
        System.out.println("erreur requête");
        return null;
    }
    
    //return info_employe;
    return mdp;
    
}
    

}
    
