
import java.util.ArrayList;
import java.util.Arrays;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;

public class Plateau
{
    //terrain non occupé par des unites au debut
    private boolean occupe[][] = { {false,false,false,false,false,false,false,false,false,false},
                                   {false,false,false,false,false,false,false,false,false,false},
                                   {false,false,false,false,false,false,false,false,false,false},
                                   {false,false,false,false,false,false,false,false,false,false},
                                   {false,false,false,false,false,false,false,false,false,false},
                                   {false,false,false,false,false,false,false,false,false,false},
                                   {false,false,false,false,false,false,false,false,false,false},
                                   {false,false,false,false,false,false,false,false,false,false},
                                   {false,false,false,false,false,false,false,false,false,false},
                                   {false,false,false,false,false,false,false,false,false,false} };

    // Cette variable définit la carte de jeu : elle indique le type de chacune des 10x10 cases.
    // Dans notre programme, 
    // le type 0 correspond à un village
    // le type 1 correspond à une forteresse
    // le type 2 correspond à une foret
    private int cases[][] = { {1,1,1,1,1,1,1,1,1,1},
                              {1,0,0,0,1,0,0,0,0,0},
                              {1,0,0,0,0,0,0,0,2,2},
                              {1,0,0,0,0,0,0,0,2,2},
                              {1,0,0,0,0,0,0,0,2,2},
                              {1,0,0,0,0,0,0,0,0,0},
                              {1,0,0,0,0,0,0,0,0,0},
                              {1,0,0,0,0,0,0,0,1,1},
                              {1,0,0,0,0,0,0,0,0,0},
                              {1,0,0,1,0,0,0,0,0,0} };
                

    private int pos_unites[][] = { {0,0,0,0,0,0,0,0,0,0},
                                   {0,0,0,0,0,0,0,0,0,0},
                                   {0,0,0,0,0,0,0,0,0,0},
                                   {0,0,0,0,0,0,0,0,0,0},
                                   {0,0,0,0,0,0,0,0,0,0},
                                   {0,0,0,0,0,0,0,0,0,0},
                                   {0,0,0,0,0,0,0,0,0,0},
                                   {0,0,0,0,0,0,0,0,0,0},
                                   {0,0,0,0,0,0,0,0,0,0},
                                   {0,0,0,0,0,0,0,0,0,0} };
                                

    public int[][] getCases() { return cases; }

    public int[][] getPosUnites() { return pos_unites; }

    public boolean[][] getEtatOccupe() { return occupe; }

    public int[][] setPosUnites(int tour, int ligne, int colonne)
    {
        occupe[ligne][colonne]=true;
        if (tour==1)
        {
            pos_unites[ligne][colonne]=1; //equipe 1
        }
            
        else
        {
            pos_unites[ligne][colonne]=2; //equipe 2
        }
        return pos_unites;
    }

    public int[][] effacerPosUnites(int ligne, int colonne)
    {
        occupe[ligne][colonne]=false;
        pos_unites[ligne][colonne]=0;
        return pos_unites;
    }

    void initPosUnites(Equipe eq, int tour, int ligne, int colonne)
    {
        int i;
        for (i=0;i<eq.getListeEquipe().size();i++)
        {
            this.setPosUnites(tour,ligne,colonne);
            colonne+=1;
        }
    }
    

    void affichagePlateau(Graphique graph)
    {
        // Affichage de la matrice
        for(int i = 0; i < this.getCases().length; i++)
        {
            //affiche les colonnes de la matrice du plateau
            for(int j = 0; j < this.getCases()[i].length; j++)
            {
                System.out.print(this.getCases()[i][j] + " ");
            }
            System.out.print("|| ");

            //affiche les colonens de la matrice des positions des unites
            //meme nombre de ligne donc on recommence seulement les colonnes
            for (int j = 0 ; j < this.getPosUnites().length ; j++)
            {
                System.out.print(this.getPosUnites()[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        graph.repaint();
    }

    

    public static void main(String[] args)
    {
        
  
        int ligne_debut_eq1 = 0;
        int ligne_debut_eq2 = 9;
        int colonne_debut_eq1 = 4;
        int colonne_debut_eq2 = 4;
        int tour_de_jeu=0; //equipe1 qui commence (1 pour equipe2)
        Plateau plat = new Plateau();       

        Graphique graph = new Graphique(plat, 35);

        Runnable gui = new Runnable() {
            public void run() {
                JFrame f = new JFrame("WarGame");
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.add(new Graphique(plat, 35)); // Crée une liste d'hexagones
                f.setSize(650, 600);
                f.setLocationRelativeTo(null);
                f.setVisible(true);
            }
        };
        // GUI must start on EventDispatchThread:
        SwingUtilities.invokeLater(gui);

        //deux equipes
        Equipe equipe1 = new Equipe();
        Equipe equipe2 = new Equipe(); 

        GroupeUnite perso = new GroupeUnite();
        GroupeUnite perso2 = new GroupeUnite();

        GroupeTerrain terrain = new GroupeTerrain();

        //ajout des personnages
        //String nom, int pv, int p_att, int p_def, int depl,int vision
        perso.ajoutUnite("Archer", 33, 6, 2, 5, 7);
        perso.ajoutUnite("Infanterie", 28, 5, 3, 6, 4);
        perso.ajoutUnite("Cavalerie", 38, 8, 3, 8, 6);

        //ajout des terrains
        //String nom, int depl, double bonus_def
        terrain.ajoutTerrain("Village", 1, 0.40);
        terrain.ajoutTerrain("Forteresse", 4, 0.60);
        terrain.ajoutTerrain("Foret", 2, 0.40);

        //ajout des personnages créés aux équipes (choix des perso)
        System.out.println("Construction equipe 1 : ");
        equipe1.ajoutEquipe(perso.getListeUnite(), ligne_debut_eq1, colonne_debut_eq1);
        System.out.println("Construction equipe 2 : ");
        equipe2.ajoutEquipe(perso.getListeUnite(), ligne_debut_eq2, colonne_debut_eq2);

        terrain.afficheCaracTerrain();

        //intialiser le positionnement des unites de l'equipe 1
        //initPosUnites(ligne,colonne) : coord de la premiere unite
        //puis on augmente la colonne pour les autres unites
        plat.initPosUnites(equipe1,1,ligne_debut_eq1,colonne_debut_eq1);
        plat.initPosUnites(equipe2,2,ligne_debut_eq2,colonne_debut_eq2);

        System.out.println("Matrices : A gauche, Terrain de jeu / A droite, Positionnement des unites");
        while(true)
        {
            plat.affichagePlateau(graph);
            System.out.println("\nTour de l'equipe " + (tour_de_jeu+1));
            if (tour_de_jeu==0)
            {
                equipe1.deplacer(graph, plat, terrain, 1, equipe2);
            }
            else
            {
                equipe2.deplacer(graph, plat, terrain, 2, equipe1);
            }
            tour_de_jeu+=1;
            tour_de_jeu%=2; //modulo 2
        }
    }
}
