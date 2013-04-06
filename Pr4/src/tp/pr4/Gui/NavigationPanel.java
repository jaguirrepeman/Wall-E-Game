package tp.pr4.Gui;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class NavigationPanel extends JPanel {
	public NavigationPanel() {
		this.setLayout(new BorderLayout(10, 10));
		//ejemplo de como meter imagenes
		ImageIcon icon = createImageIcon("images/walleNorth.png", "WALLE");
		JLabel mapViewPanel = new JLabel(icon);
		//mapViewPanel.setIcon(icon); 
		this.add(mapViewPanel, BorderLayout.NORTH);
		setVisible(true);
		// JScrollPanel
		JTextArea text = new JTextArea("Aqui ira el texto y esas mierdas");
		// JScrollPanel text = new JScrollPanel();
		this.add(text, BorderLayout.SOUTH);
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
}
