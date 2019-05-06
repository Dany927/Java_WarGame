
import java.util.ArrayList;
import java.util.Arrays;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;

public class Plateau extends JPanel
{
    private final Polygon hexagon = new Polygon();
    private final BasicStroke bs1 = new BasicStroke(1);// Permet de fixer l'épaisseur du trait dans la suite
    private final BasicStroke bs3 = new BasicStroke(2);// Idem
    private final Point focusedHexagonLocation = new Point();
    private final Dimension dimension;
    private final int ligne, colonne, side;
    private Point mousePosition;
    private int number;
    private Image image_mer, image_chateau,image_sand, todd, todd2;
    Image[][] tableauImg = new Image[10][10];

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

    public Plateau(final int ligne, final int colonne, final int side) {
        this.ligne = ligne;
        this.colonne = colonne;
        this.side = side;
        dimension = getHexagon(0, 0).getBounds().getSize();
        System.out.println(dimension);
        MouseInputAdapter mouseHandler = new MouseInputAdapter() {
            @Override
            public void mouseMoved(final MouseEvent e) {
                mousePosition = e.getPoint();
                repaint();
            }

            @Override
            public void mousePressed(final MouseEvent e) {
                if (number != -1) {
                    System.out.println("Hexagon " + (number + 1));
                    // System.out.println(e.getX());
                    // System.out.println(e.getY());

                }
            }
        };
        addMouseMotionListener(mouseHandler);
        addMouseListener(mouseHandler);

        try {
            image_mer = ImageIO.read(new File("./ImagePlateau/mer.png"));
            image_chateau = ImageIO.read(new File("./ImagePlateau/medieval_openCastle.png"));
            image_sand  = ImageIO.read(new File("./ImagePlateau/dirt_06.png"));
            todd = ImageIO.read(new File("./ImagePlateau/todd.png"));
            todd2 = ImageIO.read(new File("./ImagePlateau/stone_16.png"));
        } catch (IOException exc) {
            exc.printStackTrace();
        }

    }

    public void 

    @Override
    public void paintComponent(final Graphics g) {

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.black);
        g2d.setStroke(bs1);
        number = -1;
        // System.out.println(dimension);
        
       /* System.out.println("\nMATRICE POS : ");
        for(int i = 0; i < pos_unites.length; i++)
        {
            for (int j = 0 ; j < pos_unites.length ; j++)
            {
                System.out.print(pos_unites[i][j] + " ");
            }
            System.out.println();
        }*/

        for (int row = 0; row < ligne; row += 2) {
            for (int column = 0; column < colonne; column++) {

                getHexagon(column * dimension.width, (int) (row * side * 1.5));
                if (mousePosition != null && hexagon.contains(mousePosition)) {
                    focusedHexagonLocation.x = column * dimension.width;
                    focusedHexagonLocation.y = (int) (row * side * 1.5);
                    number = row * colonne + column;
                }
                
                
                g2d.draw(hexagon);

                if (cases[row][column]==0) //village
                    tableauImg[row][column] = image_mer;
                else if (cases[row][column]==1) //forteresse
                    tableauImg[row][column] = image_chateau;
                else if (cases[row][column]==2) // foret
                    tableauImg[row][column] = image_sand;

                if (pos_unites[row][column]==1)
                    tableauImg[row][column] = todd;
                else if (pos_unites[row][column]==2)
                    tableauImg[row][column] = todd2;
                
                g2d.drawImage(tableauImg[row][column],(int)(hexagon.getBounds().x),
                        (int) (hexagon.getBounds().y), 60, 70, this);

            }

        }
        
        for (int row = 1; row < ligne; row += 2) {
            for (int column = 0; column < colonne; column++) {
                getHexagon(column * dimension.width + dimension.width / 2, (int) (row * side * 1.5 + 0.5));
                if (mousePosition != null && hexagon.contains(mousePosition)) {
                    focusedHexagonLocation.x = column * dimension.width + dimension.width / 2;
                    focusedHexagonLocation.y = (int) (row * side * 1.5 + 0.5);
                    number = row * colonne + column;
                }
                g2d.draw(hexagon);
                

                if (cases[row][column]==0) //village
                    tableauImg[row][column] = image_mer;
                else if (cases[row][column]==1) //forteresse
                    tableauImg[row][column] = image_chateau;
                else if (cases[row][column]==2) // foret
                    tableauImg[row][column] = image_sand;

                if (pos_unites[row][column]==1)
                    tableauImg[row][column] = todd;
                else if (pos_unites[row][column]==2)
                    tableauImg[row][column] = todd2;

                //this.repaint();



                g2d.drawImage(tableauImg[row][column],(int)(hexagon.getBounds().x),
                        (int) (hexagon.getBounds().y), 60, 70, this);

                // g2d.drawImage(image1,0,0,60, 70, this);
            }
        }
        
        


        if (number != -1) {
            g2d.setColor(Color.red);
            g2d.setStroke(bs3);
            Polygon focusedHexagon = getHexagon(focusedHexagonLocation.x, focusedHexagonLocation.y);
            g2d.draw(focusedHexagon);
        }


    }

    public void update( Graphics g )
    {
        paint( g );
    }

    public Polygon getHexagon(final int x, final int y) { // la forme de l'hexagone
        hexagon.reset();
        int h = side / 2;
        int w = (int) (side * (Math.sqrt(3) / 2));
        hexagon.addPoint(x, y + h);
        hexagon.addPoint(x + w, y);
        hexagon.addPoint(x + 2 * w, y + h);
        hexagon.addPoint(x + 2 * w, y + (int) (1.5 * side));
        hexagon.addPoint(x + w, y + 2 * side);
        hexagon.addPoint(x, y + (int) (1.5 * side));
        return hexagon;
    }


    

    void affichagePlateau()
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
    }

    

    public static void main(String[] args)
    {
        Runnable gui = new Runnable() {
           public void run() {
                JFrame f = new JFrame("WarGame");
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.add(new Plateau(10, 10, 35)); // Crée une liste d'hexagones
                f.setSize(650, 600);
                f.setLocationRelativeTo(null);
                f.setVisible(true);
           }
        };
        // GUI must start on EventDispatchThread:
        SwingUtilities.invokeLater(gui);
  
        int ligne_debut_eq1 = 0;
        int ligne_debut_eq2 = 9;
        int colonne_debut_eq1 = 4;
        int colonne_debut_eq2 = 4;
        int tour_de_jeu=0; //equipe1 qui commence (1 pour equipe2)
        Plateau plat = new Plateau(0,0,0);       

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

        plat.repaint();

        System.out.println("Matrices : A gauche, Terrain de jeu / A droite, Positionnement des unites");
        while(true)
        {
            plat.affichagePlateau();
            System.out.println("\nTour de l'equipe " + (tour_de_jeu+1));
            if (tour_de_jeu==0)
            {
                equipe1.deplacer(plat, terrain, 1, equipe2);
            }
            else
            {
                equipe2.deplacer(plat, terrain, 2, equipe1);
            }
            tour_de_jeu+=1;
            tour_de_jeu%=2; //modulo 2
        }
    }
}
