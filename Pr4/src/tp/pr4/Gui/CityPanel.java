package tp.pr4.Gui;

import java.awt.BorderLayout;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
@SuppressWarnings("serial")
public class CityPanel extends JPanel{
	public CityPanel() {
		
		this.setLayout(new BorderLayout(10, 10));
		ImageIcon icon = createImageIcon("images/walleNorth.png", "WALLE");
		JLabel walle = new JLabel(icon);
		this.add(walle, BorderLayout.WEST);
		JPanel cityMap = this.setCity();
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
	public JPanel setCity(){
		//aqui se crea la ciudad que se mostrara en la ventana
		JPanel cityMap = new JPanel();
		TitledBorder titled = new TitledBorder("City Map");
		cityMap.setBorder(titled);
		cityMap.setLayout(new GridLayout(11, 11));
		for (int i = 0; i< 121; i++) cityMap.add(new PlaceCell());
		PlaceCell[] places;
		PlaceCell place = new PlaceCell();
		//place.setActual(true);
		//cityMap.add(place);
		return cityMap;
	}
}
