package tp.pr5.gui;

import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import tp.pr5.Direction;
import tp.pr5.NavigationObserver;
import tp.pr5.PlaceInfo;
import tp.pr5.RobotEngineObserver;
import tp.pr5.items.InventoryObserver;
import tp.pr5.items.Item;

public class InfoPanel extends JPanel implements
		RobotEngineObserver, NavigationObserver, InventoryObserver {

	
	public InfoPanel(){
		
		displayMessage = new JLabel("");
		this.add(this.displayMessage);
	}
	
	/*
	 * 
	 * (non-Javadoc)
	 * @see tp.pr5.items.InventoryObserver
	 */
	@Override
	public void inventoryChange(List<Item> inventory) {}

	@Override
	public void inventoryScanned(String inventoryDescription) {}

	@Override
	public void itemScanned(String description) {
		
		this.displayMessage.setText(description);

	}

	@Override
	public void itemEmpty(String itemName) {
		
		this.displayMessage.setText("You have got no more " + itemName + " in your inventory.");

	}
	
	/*
	 * (non-Javadoc)
	 * @see tp.pr5.NavigationObserver
	 */

	@Override
	public void headingChanged(Direction newHeading) {
		this.displayMessage.setText("Wall·E is looking at direction " + newHeading.toString());

	}

	@Override
	public void initNavigationModule(PlaceInfo initialPlace, Direction heading) {}

	@Override
	public void robotArrivesAtPlace(Direction heading, PlaceInfo place) {}

	@Override
	public void placeScanned(PlaceInfo placeDescription) {}

	@Override
	public void placeHasChanged(PlaceInfo placeDescription) {}

	/*
	 * (non-Javadoc)
	 * @see tp.pr5.RobotEngineObserver
	 */
	@Override
	public void raiseError(String msg) {
		this.displayMessage.setText(msg);
	}

	@Override
	public void communicationHelp(String help) {}

	@Override
	public void engineOff(boolean atShip) {}

	@Override
	public void communicationCompleted() {}

	@Override
	public void robotUpdate(int fuel, int recycledMaterial) {
		
		this.displayMessage.setText("Wall·E's attributes have been updated: ("
				+ fuel + ", " + recycledMaterial + ")");

	}

	@Override
	public void robotSays(String message) {
		this.displayMessage.setText(message);
	}
	
	private JLabel displayMessage;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


}
