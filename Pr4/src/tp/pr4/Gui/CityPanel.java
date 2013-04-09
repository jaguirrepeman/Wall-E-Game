package tp.pr4.Gui;

import java.awt.BorderLayout;

import java.awt.GridLayout;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import tp.pr4.instructions.OperateInstruction;
@SuppressWarnings("serial")
public class CityPanel extends JPanel{
	public CityPanel(JTextArea text) {
		
		this.setLayout(new BorderLayout());
		ImageIcon icon = createImageIcon("images/walleNorth.png", "WALLE");
		JLabel walle = new JLabel(icon);
		this.add(walle, BorderLayout.WEST);
		JPanel cityMap = this.setCity(text);
		this.add(cityMap);

		
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
		//aqui se crea la ciudad que se mostrara en la ventana
		JPanel cityMap = new JPanel();
		TitledBorder titled = new TitledBorder("City Map");
		cityMap.setBorder(titled);
		cityMap.setLayout(new GridLayout(11, 11));
		PlaceCell[][] places = new PlaceCell[CELDAS_FILAS][CELDAS_COLUMNAS];
		for (int i = 0; i< CELDAS_FILAS; i++) 
			for (int j = 0; j< CELDAS_COLUMNAS; j++){
				places[i][j] = new PlaceCell(text);
				
				cityMap.add(places[i][j]);
			}
		

		//PlaceCell place = new PlaceCell();
		//place.setActual(true);
		//cityMap.add(place);
		return cityMap;
	}
	
	 private static final int CELDAS_FILAS = 11;
	 private static final int CELDAS_COLUMNAS = 11;
}
