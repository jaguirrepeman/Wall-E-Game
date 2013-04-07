package tp.pr4.Gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class NavigationPanel extends JPanel {
	public NavigationPanel() {
		this.setLayout(new BorderLayout(10, 10));
		//ejemplo de como meter imagenes
		ImageIcon icon = createImageIcon("images/walleNorth.png", "WALLE");
		JLabel mapViewPanel = new JLabel(icon);
		//mapViewPanel.setIcon(icon); 
		this.add(mapViewPanel);
		setVisible(true);
		// JScrollPanel
		JTextArea text = new JTextArea("Aqui ira el texto y esas mierdas");
		JScrollPane scroller = new JScrollPane(text);
        scroller.setPreferredSize(new Dimension(100,100));

		// JScrollPanel text = new JScrollPanel();
		this.add(scroller, BorderLayout.SOUTH);
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
