package tp.pr4.Gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class NavigationPanel extends JPanel {
	public NavigationPanel() {
		this.setLayout(new BorderLayout(10, 10));
		//ejemplo de como meter imagenes
		CityPanel cityPanel = new CityPanel();
		//mapViewPanel.setIcon(icon); 
		this.add(cityPanel);
		
		//setVisible(true);
		// JScrollPanel
		TitledBorder titled = new TitledBorder("Log");
		JTextArea text = new JTextArea("Aqui ira el texto y esas mierdas");
		text.setEditable(false);
		JScrollPane scroller = new JScrollPane(text);
        scroller.setPreferredSize(new Dimension(100,100));
        scroller.setBorder(titled);
		// JScrollPanel text = new JScrollPanel();
		this.add(scroller, BorderLayout.SOUTH);
	}


}
