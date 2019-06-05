
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;

import java.io.*;

public class Plateau extends JPanel implements Serializable
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
    // le type 3 correspond à une mer
    // le type 4 correspond au sable
    // le type 5 correspond au rocher                              
    private int cases[][] = { {0,0,5,0,0,0,5,0,0,0},
                              {1,0,0,0,1,0,0,0,0,0},
                              {2,2,2,2,0,0,0,0,2,2},
                              {1,0,0,2,2,0,5,0,2,2},
                              {1,0,0,0,2,2,2,2,2,2},
                              {4,4,4,4,4,5,5,1,2,2},
                              {3,3,3,3,3,3,5,0,0,0},
                              {3,3,3,3,3,3,5,0,0,0},
                              {4,4,4,4,4,3,5,0,0,0},
                              {1,0,0,0,1,3,5,0,1,0} };
                

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
        	if (i%2==0) //pair
        	{
        		//affiche les colonnes de la matrice du plateau
	            for(int j = 0; j < this.getCases()[i].length; j++)
	            {
	                System.out.print(this.getCases()[i][j] + " ");
	            }
	            System.out.print("  || ");

	            //affiche les colonnes de la matrice des positions des unites
	            //meme nombre de ligne donc on recommence seulement les colonnes
	            for (int j = 0 ; j < this.getPosUnites().length ; j++)
	            {
	                System.out.print(this.getPosUnites()[i][j] + " ");
	            }
	            System.out.println();
        	}
        	else
        	{
        		System.out.print(" ");
        		//affiche les colonnes de la matrice du plateau
	            for(int j = 0; j < this.getCases()[i].length; j++)
	            {
	                System.out.print(this.getCases()[i][j] + " ");
	            }
	            System.out.print(" ||   ");

	            //affiche les colonnes de la matrice des positions des unites
	            //meme nombre de ligne donc on recommence seulement les colonnes
	            for (int j = 0 ; j < this.getPosUnites().length ; j++)
	            {
	                System.out.print(this.getPosUnites()[i][j] + " ");
	            }
	            System.out.println();
        	}

            
        }
        System.out.println();
        graph.repaint();
    }

    void sauvegarder(Equipe eq1, Equipe eq2) throws IOException
    {
    	File fic = new File("save1.ser");
    	ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fic));
    	oos.writeObject(this);

    	File fic1 = new File("save2.ser");
    	ObjectOutputStream oos1 = new ObjectOutputStream(new FileOutputStream(fic1));
    	oos1.writeObject(eq1);

    	File fic2 = new File("save3.ser");
    	ObjectOutputStream oos2 = new ObjectOutputStream(new FileOutputStream(fic2));
    	oos2.writeObject(eq2);

    	//System.out.println("eq1 : " + eq1.size()  +" / eq2 : " + eq2.size());
    }

    

    public static void main(String[] args)
    {
  
        int ligne_debut_eq1 = 0;
        int ligne_debut_eq2 = 9;
        int colonne_debut_eq1 = 2;
        int colonne_debut_eq2 = 2;
        int tour_de_jeu=0; //equipe1 qui commence (1 pour equipe2)

        int ia1=0;
        int ia2=0;

        int sauvegarde=0;
        int charger=0;

        Scanner sc = new Scanner(System.in);

        Plateau plat = new Plateau();       

        //deux equipes
        Equipe equipe1 = new Equipe();
        Equipe equipe2 = new Equipe(); 

        GroupeUnite perso = new GroupeUnite();

        GroupeTerrain terrain = new GroupeTerrain();

        System.out.println("Voulez-vous charger votre ancienne partie ? Si oui, tapez 1 sinon un autre numéro");
        System.out.print("Choix : ");
        charger = sc.nextInt();

        System.out.println("Equipe1 : ");
        System.out.println("Voulez-vous être une IA ? Si oui, tapez 1 sinon un  autre numéro");
        System.out.print("Choix : ");
        ia1 = sc.nextInt();

        System.out.println("Equipe2 : ");
        System.out.println("Voulez-vous être une IA ? Si oui, tapez 1 sinon un  autre numéro");
        System.out.print("Choix : ");
        ia2 = sc.nextInt();

        Graphique graph = new Graphique(plat, equipe1, equipe2, 35);

        JFrame f = new JFrame("WarGame");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(graph); // Crée une liste d'hexagones
        f.setSize(650, 650);
        f.setLocationRelativeTo(null);
        f.setVisible(true);

        //ajout des terrains
        //String nom, int depl, double bonus_def
        terrain.ajoutTerrain("Village", 1, 0.40);
        terrain.ajoutTerrain("Forteresse", 3, 0.60);
        terrain.ajoutTerrain("Foret", 2, 0.40);
        terrain.ajoutTerrain("Mer", 4, 0.5);
        terrain.ajoutTerrain("Sable", 2, 0.2);
        terrain.ajoutTerrain("Rocher", 1, 0.6);
        

        if(charger!=1)
        {
        	

	        //ajout des personnages
	        //String nom, int pv, int p_att, int p_def, int depl,int vision
	        perso.ajoutUnite("Infanterie", 28, 5, 3, 6, 4);
	        perso.ajoutUnite("Archer", 33, 6, 2, 5, 7);
	        perso.ajoutUnite("Mage", 21, 5, 1, 5, 5);
	        perso.ajoutUnite("Cavalerie", 38, 8, 3, 8, 6);
	        perso.ajoutUnite("Infanterie Lourde", 38, 10, 10, 4, 4);

	        

	        //ajout des personnages créés aux équipes (choix des perso)
	        System.out.println("Construction equipe 1 : ");
	        equipe1.ajoutEquipe(perso.getListeUnite(), ia1, ligne_debut_eq1, colonne_debut_eq1);
	        System.out.println("Construction equipe 2 : ");
	        equipe2.ajoutEquipe(perso.getListeUnite(), ia2, ligne_debut_eq2, colonne_debut_eq2);

	        plat.initPosUnites(equipe1,1,ligne_debut_eq1,colonne_debut_eq1);
        	plat.initPosUnites(equipe2,2,ligne_debut_eq2,colonne_debut_eq2);
        }

        else
        {
        	try {
					File fic = new File("save1.ser");
			    	ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fic));
			    	plat = (Plateau) ois.readObject();

			    	File fic1 = new File("save2.ser");
			    	ObjectInputStream ois1 = new ObjectInputStream(new FileInputStream(fic1));
			    	equipe1 = (Equipe) ois1.readObject();

			    	File fic2 = new File("save3.ser");
			    	ObjectInputStream ois2 = new ObjectInputStream(new FileInputStream(fic2));
			    	equipe2 = (Equipe) ois2.readObject();

			    	System.out.println("Eq 1 : " + equipe1.getListeEquipe().size() + " eq 2 : " + equipe2.getListeEquipe().size());


			    	graph.equipe1 = equipe1;
			    	graph.equipe2 = equipe2;
			    	graph.plat = plat;

		
		

				}
				catch(IOException e) {
				  e.printStackTrace();
				}
				catch(ClassNotFoundException e)
				{
					e.printStackTrace();
				}
        }
        System.out.println("Eq 1 : " + equipe1.getListeEquipe().size() + " eq 2 : " + equipe2.getListeEquipe().size());

        terrain.afficheCaracTerrain();



        //intialiser le positionnement des unites de l'equipe 1
        //initPosUnites(ligne,colonne) : coord de la premiere unite
        //puis on augmente la colonne pour les autres unites
        

        System.out.println("Matrices : A gauche, Terrain de jeu / A droite, Positionnement des unites");
        while(equipe1.getListeEquipe().size()>0&&equipe2.getListeEquipe().size()>0)
        {
            plat.affichagePlateau(graph);
            System.out.println("\nTour de l'equipe " + (tour_de_jeu+1));

	        System.out.println("Voulez-vous sauvegarder votre partie ? Si oui, tapez 1 sinon un autre numéro");
	        System.out.print("Choix : ");
	        sauvegarde = sc.nextInt();

	        if (sauvegarde==1)
	        {
	        	try {
				  plat.sauvegarder(equipe1, equipe2);
				}
				catch(IOException e) {
				  e.printStackTrace();
				}
	        }

            if (tour_de_jeu==0)
            {
                equipe1.deplacer(ia1, graph, plat, terrain, 1, equipe2);
            }
            else
            {
                equipe2.deplacer(ia2 ,graph, plat, terrain, 2, equipe1);
            }
            tour_de_jeu+=1;
            tour_de_jeu%=2; //modulo 2
        }
        if (equipe1.getListeEquipe().size()==0)
        	System.out.println("L'equipe 2 a gagne !");
        if(equipe2.getListeEquipe().size()==0)
        	System.out.println("L'equipe 1 a gagne !");
    }
}
