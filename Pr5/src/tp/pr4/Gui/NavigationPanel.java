package tp.pr4.Gui;

import java.awt.BorderLayout;
import java.awt.Dimension;


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
		text = new JTextArea("");
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
	
	public void say(String message){
		text.setText(message);
	}
	
	public void move(Direction headingDirection, Place place){
		if (cityPanel != null) cityPanel.move(headingDirection, place);
		text.setText(place.toString());
		if (cityPanel.isSpaceShip()) CloseApp.requestQuit("You finally found your spaceship");
	}
	
	public void rotate(Direction direction){
		cityPanel.turnWalleIcon(direction);
	}
	// TODO estas no estan repetidas con respecto a placecell¿?
	public void setInitialPlace(Place initPlace){
		cityPanel.setInitialPlace(initPlace);
		text.setText(initPlace.toString());
	}
	
	public void setPlace(Place place){
		
		text.setText(place.toString());
	}
	
	private CityPanel cityPanel;
	private JTextArea text;
	private JScrollPane scroller;

}
