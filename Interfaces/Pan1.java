/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import static Interfaces.Conteneur.toPlateau;
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author Dell
 */
public class Pan1 extends javax.swing.JPanel {

    /**
     * Creates new form Pan1
     */
    public Pan1() {
        initComponents();
         jTextField1.addKeyListener(new KeyAdapter() {
    public void keyTyped(KeyEvent e) {
      char c = e.getKeyChar();
      if (!((c >= '0') && (c <= '9') ||
         (c == KeyEvent.VK_BACK_SPACE) ||
         (c == KeyEvent.VK_DELETE))) {
        getToolkit().beep();
        e.consume();
      }
    }
  });
         
jTextField2.setForeground(Color.BLACK);
jTextField2.addFocusListener(new FocusListener() {
    
    @Override
    public void focusGained(FocusEvent e) {
        if (jTextField2.getText().equals("Pseudo Du Joueur 1")) {
            jTextField2.setText("");
            jTextField2.setForeground(Color.BLACK);
            jTextField2.setBackground(Color.GREEN);
        }
    }
   
    @Override
    public void focusLost(FocusEvent e) {
        if (jTextField2.getText().isEmpty()) {
            jTextField2.setForeground(Color.BLACK);
            jTextField2.setText("Pseudo Du Joueur 1");
            jTextField2.setBackground(Color.RED);
        }
    }        
    });
jTextField3.setForeground(Color.BLACK);
jTextField3.addFocusListener(new FocusListener() {
    
    @Override
    public void focusGained(FocusEvent e) {
        if (jTextField3.getText().equals("Pseudo Du Joueur 1")) {
            jTextField3.setText("");
            jTextField3.setForeground(Color.BLACK);
            jTextField3.setBackground(Color.GREEN);
        }
    }
   
    @Override
    public void focusLost(FocusEvent e) {
        if (jTextField3.getText().isEmpty()) {
            jTextField3.setForeground(Color.BLACK);
            jTextField3.setText("Pseudo Du Joueur 1");
            jTextField3.setBackground(Color.RED);
        }
    }        
    });
jTextField1.setForeground(Color.BLACK);
jTextField1.addFocusListener(new FocusListener() {
    
    @Override
    public void focusGained(FocusEvent e) {
        if (jTextField1.getText().equals("Préciser le Nombre d'unités")) {
            jTextField1.setText("");
            jTextField1.setForeground(Color.BLACK);
            jTextField1.setBackground(Color.GREEN);
        }
    }
   
    @Override
    public void focusLost(FocusEvent e) {
        if (jTextField1.getText().isEmpty()) {
            jTextField1.setForeground(Color.BLACK);
            jTextField1.setText("Préciser le Nombre d'unités");
            jTextField1.setBackground(Color.RED);
        }
    }        
    });

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        jComboBox4 = new javax.swing.JComboBox<>();
        jComboBox5 = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jComboBox6 = new javax.swing.JComboBox<>();
        jComboBox7 = new javax.swing.JComboBox<>();
        jComboBox8 = new javax.swing.JComboBox<>();
        jComboBox9 = new javax.swing.JComboBox<>();
        jComboBox10 = new javax.swing.JComboBox<>();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Showcard Gothic", 1, 48)); // NOI18N
        jLabel1.setText("Joueur Vs Joueur");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 20, 500, 50));

        jPanel1.setBackground(new java.awt.Color(167, 226, 143));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Constitution de l'équipe", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Showcard Gothic", 1, 18)); // NOI18N
        jLabel2.setText("Nombre Unités");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 170, 28));

        jLabel3.setFont(new java.awt.Font("Showcard Gothic", 1, 18)); // NOI18N
        jLabel3.setText("Pseudo  joueur 2");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 180, 34));

        jTextField1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 50, 216, 45));

        jTextField2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel1.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 150, 216, 41));

        jLabel25.setFont(new java.awt.Font("Showcard Gothic", 1, 18)); // NOI18N
        jLabel25.setText("Pseudo  joueur 1");
        jPanel1.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 180, 28));

        jTextField3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });
        jPanel1.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 100, 216, 45));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 130, 510, 210));

        jPanel3.setBackground(new java.awt.Color(167, 226, 143));

        jPanel2.setBackground(new java.awt.Color(167, 226, 143));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Unitées Joueur 1", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 3, 14))); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Showcard Gothic", 1, 10)); // NOI18N
        jLabel4.setText("Infanterie");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 44, 80, 20));

        jLabel5.setFont(new java.awt.Font("Showcard Gothic", 1, 10)); // NOI18N
        jLabel5.setText("Archer");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, 50, 30));

        jLabel6.setFont(new java.awt.Font("Showcard Gothic", 1, 12)); // NOI18N
        jLabel6.setText("Mage");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 46, -1, -1));

        jLabel7.setFont(new java.awt.Font("Showcard Gothic", 1, 12)); // NOI18N
        jLabel7.setText("Cavalerie");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 40, -1, 30));

        jLabel8.setFont(new java.awt.Font("Showcard Gothic", 1, 12)); // NOI18N
        jLabel8.setText("Infanterie Lourde");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 46, -1, -1));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaces/inffaaa.gif"))); // NOI18N
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(-8, 66, 70, 60));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaces/archeee1.gif"))); // NOI18N
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, -1, 70));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaces/mageeee.gif"))); // NOI18N
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 56, 60, 80));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaces/cavalerievv.gif"))); // NOI18N
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 60, 70, 80));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaces/inflourd.gif"))); // NOI18N
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 60, 70, 80));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " 1", " 2", " 3", " 4", " 5" }));
        jPanel2.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 60, -1));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " 1", " 2", " 3", " 4", " 5" }));
        jPanel2.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, 60, -1));

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " 1", " 2", " 3", " 4", " 5" }));
        jPanel2.add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 150, 60, -1));

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " 1", " 2", " 3", " 4", " 5" }));
        jPanel2.add(jComboBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 150, 60, -1));

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " 1", " 2", " 3", " 4", " 5" }));
        jPanel2.add(jComboBox5, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 150, 60, -1));

        jPanel5.setBackground(new java.awt.Color(167, 226, 143));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Unitées Joueur 2", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 3, 14))); // NOI18N
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setFont(new java.awt.Font("Showcard Gothic", 1, 10)); // NOI18N
        jLabel14.setText("Infanterie");
        jPanel5.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 46, 70, -1));

        jLabel15.setFont(new java.awt.Font("Showcard Gothic", 1, 10)); // NOI18N
        jLabel15.setText("Archer");
        jPanel5.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(94, 46, -1, -1));

        jLabel16.setFont(new java.awt.Font("Showcard Gothic", 1, 10)); // NOI18N
        jLabel16.setText("Mage");
        jPanel5.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 50, -1, 10));

        jLabel17.setFont(new java.awt.Font("Showcard Gothic", 1, 10)); // NOI18N
        jLabel17.setText("Cavalerie");
        jPanel5.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(219, 46, -1, -1));

        jLabel18.setFont(new java.awt.Font("Showcard Gothic", 1, 10)); // NOI18N
        jLabel18.setText("Infanterie Lourde");
        jPanel5.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 46, -1, -1));
        jPanel5.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 66, 60, 70));

        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " 1", " 2", " 3", " 4", " 5" }));
        jPanel5.add(jComboBox6, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 150, 70, -1));

        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " 1", " 2", " 3", " 4", " 5" }));
        jPanel5.add(jComboBox7, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 150, 60, -1));

        jComboBox8.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " 1", " 2", " 3", " 4", " 5" }));
        jPanel5.add(jComboBox8, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 150, 60, -1));

        jComboBox9.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " 1", " 2", " 3", " 4", " 5" }));
        jPanel5.add(jComboBox9, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 150, 60, -1));

        jComboBox10.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " 1", " 2", " 3", " 4", " 5" }));
        jPanel5.add(jComboBox10, new org.netbeans.lib.awtextra.AbsoluteConstraints(308, 150, 70, -1));

        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaces/inffaaa.gif"))); // NOI18N
        jPanel5.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(-8, 66, 80, 70));

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaces/archeee1.gif"))); // NOI18N
        jPanel5.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 60, -1, 70));

        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaces/mageeee.gif"))); // NOI18N
        jPanel5.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 56, 60, 80));

        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaces/cavalerievv.gif"))); // NOI18N
        jPanel5.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 60, 70, 80));

        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaces/inflourd.gif"))); // NOI18N
        jPanel5.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 60, 70, 80));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 360, 840, 270));

        jButton1.setFont(new java.awt.Font("Showcard Gothic", 3, 24)); // NOI18N
        jButton1.setForeground(new java.awt.Color(204, 0, 0));
        jButton1.setText("Commencer la partie");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 640, 340, 60));

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaces/background3.PNG"))); // NOI18N
        add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 730));
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        toPlateau();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox10;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JComboBox<String> jComboBox7;
    private javax.swing.JComboBox<String> jComboBox8;
    private javax.swing.JComboBox<String> jComboBox9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables

   

}
