package tp.pr5.gui;

import java.util.List;

import javax.swing.JFormattedTextField;
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
		displayMessage = new JFormattedTextField();
	}
	@Override
	public void inventoryChange(List<Item> inventory) {
		// TODO Auto-generated method stub

	}

	@Override
	public void inventoryScanned(String inventoryDescription) {
		// TODO Auto-generated method stub

	}

	@Override
	public void itemScanned(String description) {
		
		this.displayMessage.setText(description);

	}

	@Override
	public void itemEmpty(String itemName) {
		
		this.displayMessage.setText("You have got no more " + itemName + " in your inventory.");

	}

	@Override
	public void headingChanged(Direction newHeading) {
		this.displayMessage.setText("Wall·E is looking at direction " + newHeading.toString());

	}

	@Override
	public void initNavigationModule(PlaceInfo initialPlace, Direction heading) {
		// TODO Auto-generated method stub

	}

	@Override
	public void robotArrivesAtPlace(Direction heading, PlaceInfo place) {
		// TODO Auto-generated method stub

	}

	@Override
	public void placeScanned(PlaceInfo placeDescription) {
		// TODO Auto-generated method stub

	}

	@Override
	public void placeHasChanged(PlaceInfo placeDescription) {
		// TODO Auto-generated method stub

	}

	@Override
	public void raiseError(String msg) {
		// TODO Auto-generated method stub
		this.displayMessage.setText(msg);
	}

	@Override
	public void communicationHelp(String help) {
		// TODO Auto-generated method stub

	}

	@Override
	public void engineOff(boolean atShip) {
		// TODO Auto-generated method stub

	}

	@Override
	public void communicationCompleted() {
		//CloseApp.requestQuit("I have communication problems, bye bye!");
	}

	@Override
	public void robotUpdate(int fuel, int recycledMaterial) {
		
		this.displayMessage.setText("Wall·E's attributes have been updated: ("
				+ fuel + ", " + recycledMaterial + ")");

	}

	@Override
	public void robotSays(String message) {
		this.displayMessage.setText("Wall·E says: " + message);
	}
	
	private JFormattedTextField displayMessage;

	

}
