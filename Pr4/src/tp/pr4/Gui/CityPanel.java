package tp.pr4.Gui;

import java.awt.BorderLayout;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
		cityMap.setLayout(new GridLayout(11, 11));
		return cityMap;
	}
}
