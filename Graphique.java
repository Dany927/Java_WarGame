
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;

public class Graphique extends JPanel {

	private final Polygon hexagon = new Polygon();
	private final BasicStroke bs1 = new BasicStroke(1);// Permet de fixer l'épaisseur du trait dans la suite
	private final BasicStroke bs3 = new BasicStroke(2);// Idem
	private final Point focusedHexagonLocation = new Point();
	private final Dimension dimension;
	private final int ligne, colonne, side;
	private Point mousePosition;
	private int number;
	private Image image_mer, image_chateau,image_sand;
	Image[][] tableauImg = new Image[10][10];

	public Graphique(final int ligne, final int colonne, final int side) {
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
		
		
	

		for (int row = 0; row < ligne; row += 2) {
			for (int column = 0; column < colonne; column++) {

				getHexagon(column * dimension.width, (int) (row * side * 1.5));
				if (mousePosition != null && hexagon.contains(mousePosition)) {
					focusedHexagonLocation.x = column * dimension.width;
					focusedHexagonLocation.y = (int) (row * side * 1.5);
					number = row * colonne + column;
				}
				
				
				g2d.draw(hexagon);
				tableauImg[row][column] = image_sand;
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
				tableauImg[row][column] = image_chateau;
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

	public static void main(final String[] args) {
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
	}

}