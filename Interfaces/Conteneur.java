/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Interfaces;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;


public class Conteneur  extends JFrame{    
    private JMenuBar Menub = new JMenuBar();//MENU
    //les elements du menus et leurs noms
    private JMenu m0 = new JMenu(" File ");
    private JMenu m1 = new JMenu("Exit");
    public static Container contenu;
    public static CardLayout pile = new CardLayout();
    //Les items et leurs noms
    private JMenuItem Accueil = new JMenuItem("accueil");
    private JMenuItem Quitter = new JMenuItem("Quitter");
    
     public Conteneur() {
       // this.setResizable(false);
        setSize(1200, 800);
        setTitle("Wargame");
        this.setLocationRelativeTo(null);
        this.setLayout(pile);
        Toolkit tool = Toolkit.getDefaultToolkit();
        Font font = new Font("Serial", Font.BOLD, 20);
        this.setLayout(pile);
        contenu = this.getContentPane();
        contenu.add(new Accueil(), "Accueil");
        contenu.add(new Pan1(), "Pan1");
        contenu.add(new Pan2(), "Pan2");
        contenu.add(new Pan3(), "Pan3");
        contenu.add(new Pan3(), "Plateau");

        
        
        //ajouts des composants au menu principale
        this.setJMenuBar(Menub);
        Menub.setBackground(Color.GREEN);
        Menub.add(Box.createRigidArea(new Dimension(70,25)));
        Menub.setPreferredSize(new Dimension(70,50));
        Menub.add(m0);
        Menub.add(m1);
        m0.add(Accueil);
        m1.add(Quitter);
        m0.setFont(font);
        m1.setFont(font);
        
         
        Accueil.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent E) {
                pile.show(contenu, "Accueil");
                    
             
            }
        });
        Quitter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent E) {
                System.exit(0);
            }
        });
    }
    

    public static void toPan1() {
        pile.show(contenu, "Pan1");
    }

    public static void toPan2() {
        pile.show(contenu, "Pan2");
    }
    public static void toPan3() {
        pile.show(contenu, "Pan3");
    }
    public static void toPlateau() {
        pile.show(contenu, "<default package>.Plateau");
    }
    public static void main(String[] args) {
        Conteneur c = new Conteneur();  //La fenêtre principale
        c.setVisible(true);
    }

}
