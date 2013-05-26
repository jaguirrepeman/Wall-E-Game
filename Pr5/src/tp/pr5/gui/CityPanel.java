package tp.pr5.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import tp.pr5.Direction;
import tp.pr5.PlaceInfo;
@SuppressWarnings("serial")
public class CityPanel extends JPanel{
	
	public CityPanel(JTextArea text) {
		
		this.setLayout(new BorderLayout());
		
		walle = createImageIcon("images/walleNorth.png", "WALLE");
		walleSouth = createImageIcon("images/walleSouth.png", "WALLE");
		walleEast = createImageIcon("images/walleEast.png", "WALLE");
		walleWest = createImageIcon("images/walleWest.png", "WALLE");
		imagenWalle = new JLabel(walle);
		this.add(imagenWalle, BorderLayout.WEST);
		
		JPanel cityMap = this.setCity(text);
		this.add(cityMap);

		
	}
	
	public void turnWalleIcon (Direction direction){
		
		switch (direction) {
		 
		case NORTH:
			imagenWalle.setIcon(walle);
			break;
		case SOUTH:
			imagenWalle.setIcon(walleSouth);
			break;
		case EAST:
			imagenWalle.setIcon(walleEast);
			break;
		case WEST:
			imagenWalle.setIcon(walleWest);
			break;
		default:
			break;
		}
		
	}

	protected static ImageIcon createImageIcon(String path, String description) {
		java.net.URL imgURL = NavigationPanel.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL, description);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}
	
	public JPanel setCity(JTextArea text){
		//aqui se crea la ciudad que se mostrará en la ventana
		JPanel cityMap = new JPanel();
		TitledBorder titled = new TitledBorder("City Map");
		cityMap.setBorder(titled);
		cityMap.setLayout(new GridLayout(11, 11));
		
		places = new PlaceCell[CELDAS_FILAS][CELDAS_COLUMNAS];
		for (int i = 0; i< CELDAS_FILAS; i++) 
			for (int j = 0; j< CELDAS_COLUMNAS; j++){
				places[i][j] = new PlaceCell(text);
				
				cityMap.add(places[i][j]);
			}
		return cityMap;
	}
	
	public void move (Direction headingDirection, PlaceInfo place){
		places[pos_y][pos_x].setActual(false);
		if (headingDirection == Direction.NORTH) 		pos_y--;
		else if (headingDirection == Direction.SOUTH) 	pos_y++;
		else if (headingDirection == Direction.EAST) 	pos_x++;
		else if (headingDirection == Direction.WEST) 	pos_x--;
		places[pos_y][pos_x].setPlace(place).setActual(true);
		
			
	}
	
	public boolean isSpaceShip(){
		if (places[pos_y][pos_x] != null)
			return places[pos_y][pos_x].isSpaceShip();
		else 
			return false;
	}
	
	public void setInitialPlace(PlaceInfo initialPlace){
		places[INIT_X][INIT_Y].setPlace(initialPlace).setActual(true);
	}
	
	 private static final int CELDAS_FILAS = 11;
	 private static final int CELDAS_COLUMNAS = 11;
	 private static final int INIT_X = 5;
	 private static final int INIT_Y = 5;
	 private int pos_x = 5;
	 private int pos_y = 5;
	 
	 private PlaceCell[][] places;
	 
	 private ImageIcon walle;
	 private ImageIcon walleSouth;
	 private ImageIcon walleEast;
	 private ImageIcon walleWest;
	 private JLabel imagenWalle;
	
}
