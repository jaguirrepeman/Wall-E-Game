package tp.pr4.Gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import tp.pr4.Direction;
import tp.pr4.Place;

@SuppressWarnings("serial")
public class NavigationPanel extends JPanel {
	public NavigationPanel() {
		this.setLayout(new BorderLayout(10, 10));
		
		//setVisible(true);
		// JScrollPanel
		TitledBorder titled = new TitledBorder("Log");
		text = new JTextArea("Aqui ira el texto y esas mierdas");
		text.setEditable(false);
		//ejemplo de como meter imagenes
		cityPanel = new CityPanel(text);
		//mapViewPanel.setIcon(icon); 
		this.add(cityPanel);
				
		scroller = new JScrollPane(text);
        scroller.setPreferredSize(new Dimension(100,100));
        scroller.setBorder(titled);
		// JScrollPanel text = new JScrollPanel();
		this.add(scroller, BorderLayout.SOUTH);
	}
	public void move(Direction headingDirection, Place place){
		cityPanel.move(headingDirection, place);
		text.setText(place.toString());
	}
	
	public void rotate(Direction direction){
		cityPanel.turnWalleIcon(direction);
	}
	
	public void setInitialPlace(Place initPlace){
		cityPanel.setInitialPlace(initPlace);
	}
	
	public void setPlace(Place place){
		
		text.setText(place.toString());
	}
	
	private CityPanel cityPanel;
	private JTextArea text;
	private JScrollPane scroller;

}
