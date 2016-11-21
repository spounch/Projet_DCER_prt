/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet_dcer_prt;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import static projet_dcer_prt.Projet_DCER_prt.ListeSolution;

/**
 *
 * @author jeremie
 */
public class Affichage_solutions extends javax.swing.JPanel {

    private int code_erreur;
    private String descriptionDefaut;
    private JFrame f;
    
    public Affichage_solutions(int code_erreur, String descriptionDefaut, JFrame f) {
        initComponents();
        this.code_erreur = code_erreur;
        this.descriptionDefaut = descriptionDefaut;
        this.f = f;
        
        jLabel1.setText("<html><body> <p align=\"center\">Acceuil : Defaut " + this.code_erreur + "</p>" +
                  "<p align=\"center\">" + this.descriptionDefaut + "</p></body></html>");
        jLabel1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        
        // récupérer les infos de la base de données et les insérer dans le tableau
        // à partir du code erreur on va selectionner les solutions qui correspondent à la panne
        
        ArrayList<Object[]> ensembleDesSolutions; //tableau compact dyn d'Object
        Object[] row;
        ensembleDesSolutions = ListeSolution(1);
        
        Iterator<Object[]> it = ensembleDesSolutions.iterator();
 
        while (it.hasNext()) {
            row = it.next();
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.addRow(row);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        jButton1.setText("Détail solution");
        jButton1.setMaximumSize(new java.awt.Dimension(40, 30));
        jButton1.setMinimumSize(new java.awt.Dimension(40, 30));
        jButton1.setPreferredSize(new java.awt.Dimension(40, 30));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Solution", "Type", "Date", "Fiabilité", "Documentation", "Photos"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Acceuil : défaut n ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1)))
                .addGap(22, 22, 22))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(500, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
                .addContainerGap(500, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(202, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        // on affiche le panel detail_solution
        this.f.setContentPane(new Detail_solution(this.code_erreur,this.descriptionDefaut,this.f));
        this.f.setVisible(true);
        this.f.repaint();

        /* Detail_solution fenetre2 = new Detail_solution();
        fenetre2.setVisible(true);//code_erreur, descriptionDefaut
        //JFrame fenetre2 = new Detail_solution();
        fenetre2.setExtendedState(JFrame.MAXIMIZED_BOTH);
        fenetre2.setVisible(true);
        fenetre2.setTitle("Menu Principal");
        //Termine le processus lorsqu'on clique sur la croix rouge
        fenetre2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.dispose();*/
        //this.setVisible(false);

        //Reponse R = TPCineL3A2.infoRealisateurEtActeur(titre);

        //Projet_DCER_prt.listeEmploye("tet");

        //      Interaction_bdd interact1 = new Interaction_bdd();
        /*String element = "matricule";
        String table = "employe" ;
        try {
            interact1.lecture(element,table);  // on va lire le matricule des employé dans la table employe
        } catch (SQLException ex) {
            Logger.getLogger(acceuil.class.getName()).log(Level.SEVERE, null, ex);
        }*/

    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
