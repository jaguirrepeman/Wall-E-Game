package tp.pr5.console;

import java.util.List;

import tp.pr5.Direction;
import tp.pr5.Interpreter;
import tp.pr5.NavigationObserver;
import tp.pr5.PlaceInfo;
import tp.pr5.RobotEngineObserver;
import tp.pr5.items.InventoryObserver;
import tp.pr5.items.Item;

public class Console implements NavigationObserver, RobotEngineObserver,
		InventoryObserver {

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
		// TODO Auto-generated method stub

	}

	@Override
	public void itemEmpty(String itemName) {
		// TODO Auto-generated method stub

	}

	@Override
	public void raiseError(String msg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void engineOff(boolean atShip) {
		
		if (atShip)
			robotSays("I am at my spaceship. Bye bye");
		else 
			robotSays("I run out of fuel. I cannot move. Shutting down...");
		

	}

	@Override
	public void communicationCompleted() {
		robotSays("I have communication problems. Bye bye");

	}

	@Override
	public void robotUpdate(int fuel, int recycledMaterial) {
		//robotSays("Robot attributes has been updated");
		System.out.println("      * My power is " + fuel);
		System.out.println("      * My recycled material is "
				+ recycledMaterial);
	}

	@Override
	public void robotSays(String message) {
		System.out.println(message);
	}
	public void say(String message){
		System.out.println("WALL·E says: " + message);
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
		placeDescription.getDescription();

	}
	
	public void headingChanged(Direction newHeading){
		System.out.println("WALL·E is looking at direction "
				+ newHeading);

	}

	@Override
	public void communicationHelp(String help) {
		System.out.println(Interpreter.interpreterHelp());
		
	}


}
