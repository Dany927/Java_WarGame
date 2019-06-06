
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;

import java.io.*;

public class Graphique extends JPanel {

	private final Polygon hexagon = new Polygon();
	private final BasicStroke bs1 = new BasicStroke(1);// Permet de fixer l'épaisseur du trait dans la suite
	private final BasicStroke bs3 = new BasicStroke(2);// Idem
	private final Point focusedHexagonLocation = new Point();
	private final Dimension dimension;
	private final int side;
	private Point mousePosition;
	private int number;
	private Image image_village, image_mer, image_chateau, image_foret, image_sable, image_rocher;
	private Image im_archer, im_cavalerie, im_infanterie, im_infanterie_lourde, im_mage;
	Image[][] tableauImg = new Image[10][10];
	Plateau plat;
	public Equipe equipe1;
	public Equipe equipe2;

	public Graphique(Plateau _plat, Equipe _equipe1, Equipe _equipe2, final int side) {
		plat = _plat;
		equipe1 = _equipe1;
		equipe2 = _equipe2;

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
					System.out.println("x : " + e.getX() + " coord x : " + e.getX()/60);
					System.out.println("y : " + e.getY() + " coord y : " + e.getY()/60);

				}
			}
		};
		addMouseMotionListener(mouseHandler);
		addMouseListener(mouseHandler);

		try {
			image_village = ImageIO.read(new File("./ImagePlateau/village.png"));
			image_chateau = ImageIO.read(new File("./ImagePlateau/forteresse.png"));
			image_foret  = ImageIO.read(new File("./ImagePlateau/foret.png"));
            image_mer = ImageIO.read(new File("./ImagePlateau/mer.png"));
            image_sable  = ImageIO.read(new File("./ImagePlateau/sable.png"));
            image_rocher  = ImageIO.read(new File("./ImagePlateau/rocher.png"));

            im_archer = ImageIO.read(new File("./ImageUnite/archer.gif"));
            im_cavalerie = ImageIO.read(new File("./ImageUnite/cavalerie.gif"));
            im_infanterie = ImageIO.read(new File("./ImageUnite/infanterie.gif"));
            im_infanterie_lourde = ImageIO.read(new File("./ImageUnite/infanterie_lourde.gif"));
            im_mage = ImageIO.read(new File("./ImageUnite/mage.gif"));

        } catch (IOException exc) {
            exc.printStackTrace();
        }

	}

	@Override
	public void paintComponent(final Graphics g) {

		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(Color.black);
		g2d.setStroke(bs1);
		number = -1;
		// System.out.println(dimension);


		

		for (int row = 0; row < 10; row += 2) {
            for (int column = 0; column < 10; column++) {

                getHexagon(column * dimension.width, (int) (row * side * 1.5));
                if (mousePosition != null && hexagon.contains(mousePosition)) {
                    focusedHexagonLocation.x = column * dimension.width;
                    focusedHexagonLocation.y = (int) (row * side * 1.5);
                    number = row * 10 + column;
                }
                
                
                g2d.draw(hexagon);

                //affichage des terrains
                //on check dans la matrice cases (plateau) si c'est un village, foret, mer, sable, rocher pour charger l'image correspondante
                if (plat.getCases()[row][column]==0) //village
                    tableauImg[row][column] = image_village;
                else if (plat.getCases()[row][column]==1) //forteresse
                    tableauImg[row][column] = image_chateau;
                else if (plat.getCases()[row][column]==2) // foret
                    tableauImg[row][column] = image_foret;
                else if (plat.getCases()[row][column]==3) // mer
                    tableauImg[row][column] = image_mer;
                else if (plat.getCases()[row][column]==4) // sable
                    tableauImg[row][column] = image_sable;
                else if (plat.getCases()[row][column]==5) // rocher
                    tableauImg[row][column] = image_rocher;
                
                //on affiche alors la bonne image
                g2d.drawImage(tableauImg[row][column],(int)(hexagon.getBounds().x),
                        (int) (hexagon.getBounds().y), 60, 70, this);

            }

        }
        
       for (int row = 1; row < 10; row += 2) {
            for (int column = 0; column < 10; column++) {
                getHexagon(column * dimension.width + dimension.width / 2, (int) (row * side * 1.5 + 0.5));
                if (mousePosition != null && hexagon.contains(mousePosition)) {
                    focusedHexagonLocation.x = column * dimension.width + dimension.width / 2;
                    focusedHexagonLocation.y = (int) (row * side * 1.5 + 0.5);
                    number = row * 10 + column;
                }
                g2d.draw(hexagon);
                

                //affichage des terrains
                //on check dans la matrice cases (plateau) si c'est un village, foret, mer, sable, rocher pour charger l'image correspondante
                if (plat.getCases()[row][column]==0) //village
                    tableauImg[row][column] = image_village;
                else if (plat.getCases()[row][column]==1) //forteresse
                    tableauImg[row][column] = image_chateau;
                else if (plat.getCases()[row][column]==2) // foret
                    tableauImg[row][column] = image_foret;
                else if (plat.getCases()[row][column]==3) // mer
                    tableauImg[row][column] = image_mer;
                else if (plat.getCases()[row][column]==4) // sable
                    tableauImg[row][column] = image_sable;
                else if (plat.getCases()[row][column]==5) // rocher
                    tableauImg[row][column] = image_rocher;
                
                //on affiche alors la bonne image
                g2d.drawImage(tableauImg[row][column],(int)(hexagon.getBounds().x),
                        (int) (hexagon.getBounds().y), 60, 70, this);

                // g2d.drawImage(image1,0,0,60, 70, this);
            }
        }

		
		//affichage des unites
		//on parcourt la liste des unites de l'equipe1 
		//on check si la ligne de l'unite est pair ou non (pour regler correctement les coordonnees de l'hexagone)
		//on dessine les bordures noir de l'hexagone noir 
		//on verifie si le nom de l'unite correspond a infanterie, archer, mage, cavalerie, infanterie lourde pour charger l'image approprie
		//on dessine ensuite l'image chargee
		for (int i=0; i<equipe1.getListeEquipe().size();i++)
        {
        	if (equipe1.getListeEquipe().get(i).ligne%2==0) //pair
				getHexagon(equipe1.getListeEquipe().get(i).colonne * dimension.width, (int) (equipe1.getListeEquipe().get(i).ligne * side * 1.5));
			else
				getHexagon(equipe1.getListeEquipe().get(i).colonne * dimension.width + dimension.width / 2, (int) (equipe1.getListeEquipe().get(i).ligne * side * 1.5 + 0.5));

			g2d.setColor(Color.yellow);
			g2d.setStroke(bs3);
        	g2d.draw(hexagon);


        	if (equipe1.getListeEquipe().get(i).getNom().equals("Infanterie"))        	
				tableauImg[equipe1.getListeEquipe().get(i).ligne][equipe1.getListeEquipe().get(i).colonne] = im_infanterie;

			if (equipe1.getListeEquipe().get(i).getNom().equals("Archer"))
				tableauImg[equipe1.getListeEquipe().get(i).ligne][equipe1.getListeEquipe().get(i).colonne] = im_archer;
			if (equipe1.getListeEquipe().get(i).getNom().equals("Mage"))
				tableauImg[equipe1.getListeEquipe().get(i).ligne][equipe1.getListeEquipe().get(i).colonne] = im_mage;
			if (equipe1.getListeEquipe().get(i).getNom().equals("Cavalerie"))
				tableauImg[equipe1.getListeEquipe().get(i).ligne][equipe1.getListeEquipe().get(i).colonne] = im_cavalerie;
			if (equipe1.getListeEquipe().get(i).getNom().equals("Infanterie Lourde"))
				tableauImg[equipe1.getListeEquipe().get(i).ligne][equipe1.getListeEquipe().get(i).colonne] = im_infanterie_lourde;

        	g2d.drawImage(tableauImg[equipe1.getListeEquipe().get(i).ligne][equipe1.getListeEquipe().get(i).colonne],(int)(hexagon.getBounds().x),
                        (int) (hexagon.getBounds().y), 60, 70, this);
        }

        //affichage des unites
		//on parcourt la liste des unites de l'equipe2
		//même chose qu'au dessus mais avec l'equipe2
        for (int i=0; i<equipe2.getListeEquipe().size();i++)
        {
        	g2d.setColor(Color.black);
        	if (equipe2.getListeEquipe().get(i).ligne%2==0) //pair
				getHexagon(equipe2.getListeEquipe().get(i).colonne * dimension.width, (int) (equipe2.getListeEquipe().get(i).ligne * side * 1.5));
			else
				getHexagon(equipe2.getListeEquipe().get(i).colonne * dimension.width + dimension.width / 2, (int) (equipe2.getListeEquipe().get(i).ligne * side * 1.5 + 0.5));

			g2d.setColor(Color.blue);
			g2d.setStroke(bs3);
        	g2d.draw(hexagon);
   


        	if (equipe2.getListeEquipe().get(i).getNom().equals("Infanterie"))
				tableauImg[equipe2.getListeEquipe().get(i).ligne][equipe2.getListeEquipe().get(i).colonne] = im_infanterie;
			if (equipe2.getListeEquipe().get(i).getNom().equals("Archer"))
				tableauImg[equipe2.getListeEquipe().get(i).ligne][equipe2.getListeEquipe().get(i).colonne] = im_archer;
			if (equipe2.getListeEquipe().get(i).getNom().equals("Mage"))
				tableauImg[equipe2.getListeEquipe().get(i).ligne][equipe2.getListeEquipe().get(i).colonne] = im_mage;
			if (equipe2.getListeEquipe().get(i).getNom().equals("Cavalerie"))
				tableauImg[equipe2.getListeEquipe().get(i).ligne][equipe2.getListeEquipe().get(i).colonne] = im_cavalerie;
			if (equipe2.getListeEquipe().get(i).getNom().equals("Infanterie Lourde"))
				tableauImg[equipe2.getListeEquipe().get(i).ligne][equipe2.getListeEquipe().get(i).colonne] = im_infanterie_lourde;

        	g2d.drawImage(tableauImg[equipe2.getListeEquipe().get(i).ligne][equipe2.getListeEquipe().get(i).colonne],(int)(hexagon.getBounds().x),
                        (int) (hexagon.getBounds().y), 60, 70, this);

        }
		
		//repaint();


		if (number != -1) {
			g2d.setColor(Color.red);
			g2d.setStroke(bs3);
			Polygon focusedHexagon = getHexagon(focusedHexagonLocation.x, focusedHexagonLocation.y);
			g2d.draw(focusedHexagon);
		}
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

}