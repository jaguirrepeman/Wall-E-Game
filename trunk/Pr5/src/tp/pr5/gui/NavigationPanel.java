package tp.pr5.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;


import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import tp.pr5.Direction;
import tp.pr5.NavigationObserver;
import tp.pr5.Place;
import tp.pr5.PlaceInfo;

@SuppressWarnings("serial")
public class NavigationPanel extends JPanel implements NavigationObserver {
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
	
	/*public void rotate(Direction direction){
		cityPanel.turnWalleIcon(direction);
	}*/
	// TODO estas no estan repetidas con respecto a placecell¿?
	public void setInitialPlace(Place initPlace){
		cityPanel.setInitialPlace(initPlace);
		text.setText(initPlace.toString());
	}
	
	/*public void setPlace(Place place){
		
		text.setText(place.getDescription());
	}*/
	
	@Override
	public void headingChanged(Direction newHeading) {
		cityPanel.turnWalleIcon(newHeading);
	}

	@Override
	public void initNavigationModule(PlaceInfo initialPlace, Direction heading) {
		this.direction = direction;
		this.cityPanel.setInitialPlace(initialPlace);
		
	}

	@Override
	public void robotArrivesAtPlace(Direction heading, PlaceInfo place) {
		// TODO (cityPanel != null)
		this.cityPanel.move(heading, place);
		text.setText(place.getDescription());
	}

	@Override
	public void placeScanned(PlaceInfo placeDescription) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void placeHasChanged(PlaceInfo placeDescription) {
		// TODO Auto-generated method stub
		text.setText(placeDescription.getDescription());
	}
	
	
	
	private CityPanel cityPanel;
	private JTextArea text;
	private JScrollPane scroller;
	
	private Direction direction;

}
