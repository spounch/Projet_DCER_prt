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
        
    /// aide arraylist !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! //
    /* ArrayList al = new ArrayList();
    al.add(12);
    al.add("Une chaîne de caractères !");
    al.add(12.20f);
    al.add('d');
                
    for(int i = 0; i < al.size(); i++)
    {
      System.out.println("donnée à l'indice " + i + " = " + al.get(i));
    } */
    /// aide arraylist !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! //
        
        
        
        
        
    // méthode pour récupérer la liste des solutions d'un défaut précis
        public static ArrayList<Object[]> ListeSolution(int code_erreur){
            
            ArrayList<Object[]> ListeSolution = new ArrayList<>();
            String a = "bebeb";
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
        
    public static String DescriptionDefaut (int defaut){
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
        
    public static String MdpEmploye(String matricule){
      
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
            

        
    
    
    
    
    public static void main(String[] args) throws SQLException {
  
        connexionBase(); 
        
        //ArrayList<String> ensembleDesEmploye; //tableau compact dyn de String
        //ArrayList<String> ensembleDesMdp; 
        
        String MdpEmploye = MdpEmploye("paul");
        System.out.println(MdpEmploye);
        //Info_employe info_employe_1 = MdpEmploye("paul");
        //System.out.println(info_employe_1);
        
        // lancement de la fenêtre d'acceuil
        int code_erreur = 1;
        // on récupère la description du code defaut
        String descriptionDefaut = DescriptionDefaut(code_erreur);
        JFrame fenetre = new acceuil(code_erreur, descriptionDefaut);
        
        fenetre.setExtendedState(JFrame.MAXIMIZED_BOTH);
        fenetre.setVisible(true);
        fenetre.setTitle("Menu Principal");
        //Termine le processus lorsqu'on clique sur la croix rouge
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
         

        
        
        
            }

}
    



       /*Interaction_bdd interact1 = new Interaction_bdd();
        String element = "matricule";
        String table = "employe" ;
        interact1.lecture(element,table);  // on va lire le matricule des employé dans la table employe
        
        element = "mdp";
        interact1.lecture(element,table);  // on va lire le matricule des employé dans la table employe
        */