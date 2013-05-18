package tp.pr5;

import java.util.Scanner;
import java.util.Stack;
import java.util.Vector;

import tp.pr5.instructions.*;
import tp.pr5.instructions.exceptions.*;
import tp.pr5.items.*;


public class RobotEngine /*extends tp.pr5.Observable<RobotEngineObserver>*/
{

	public RobotEngine(City cityMap, Place initialPlace, Direction dir) {

		this.cityMap = cityMap;
		this.direction = dir;
		this.place = initialPlace;
		this.fuel = 100;
		this.items = new ItemContainer();
		this.recycledMaterial = 0;
		this.navigation = new NavigationModule(cityMap, initialPlace);
		this.navigation.initHeading(dir);
		this.instructions = new Stack<Instruction>();
		this.invObservers = new Vector<InventoryObserver>();
		this.navObservers = new Vector<NavigationObserver>();
		this.robObservers = new Vector<RobotEngineObserver>();
		this.quit = false;
		
		
	}

	public boolean isOver(){
		
		return ((quit) || (fuel == 0) ||( this.place.isSpaceship()));
		
	}
	
	public void communicateRobot(Instruction c) {
		c.configureContext(this, navigation, items);
		try {
			c.execute();
			if(c.isUndoableInstruction())
				instructions.add(c);
			
		} catch (InstructionExecutionException exc) {
			requestError(exc.getMessage());//, pasar al requestError y implementar en lascosas raras
		}
	}
	
	public void addFuel(int fuel) {
		this.fuel += fuel;
		if (this.fuel <= 0){
			this.fuel = 0;
			for (RobotEngineObserver o: robObservers)o.robotUpdate(this.fuel, this.recycledMaterial);
			for (RobotEngineObserver o: robObservers)o.engineOff(false);
		}
		/** TODO de momento se quita esto*/ else for (RobotEngineObserver o : robObservers) o.robotUpdate(this.fuel, this.recycledMaterial);
		//if (robotPanel != null) robotPanel.setStatus(this.fuel, this.recycledMaterial);
	}

	public void addRecycledMaterial(int weight) {
		this.recycledMaterial += weight;
		/** TODO de momento se quita esto*/	for (RobotEngineObserver o : robObservers) o.robotUpdate(this.fuel, this.recycledMaterial);
		//if (robotPanel != null) robotPanel.setStatus(this.fuel, this.recycledMaterial);
	}
	
	public int getFuel() {
		return this.fuel;
	}

	public int getRecycledMaterial() {
		return this.recycledMaterial;
	}
	
	public void requestStart(){
		//TODO emit partida empezada?
		for (NavigationObserver o : navObservers){ 
			o.initNavigationModule(this.place, this.direction);
		}
		for (RobotEngineObserver obs: robObservers){
			obs.robotUpdate(this.fuel, this.recycledMaterial);
		}
	}
	
	public void requestError(String msg){
		for (RobotEngineObserver obs: robObservers){
			obs.raiseError(msg);
		}
	}
	
	public void requestHelp() {
		for (RobotEngineObserver o : robObservers) 
			o.communicationHelp(Interpreter.interpreterHelp());
	}
	
	public void undoInstruction(){
		if (!instructions.isEmpty())
			instructions.pop().undo();
		else say("There is no instruction to be undone.");
	}

	public void commandQuit() {
		quit = true;
		requestQuit();
		
	}
	
	public void requestQuit() {
		if (!quit)
			for (RobotEngineObserver o : robObservers) 
				o.engineOff(this.place.isSpaceship());
		else for (RobotEngineObserver o : robObservers)
			o.communicationCompleted();
		//TODO else 
		//	for (RobotEngineObserver o : robObservers)
		//		o.communicationCompleted();
	}
	
	public void saySomething(String message){
		for (RobotEngineObserver obs: robObservers){
			obs.robotSays("WALL·E says: " + message);
		}
	}
	
	
	
	






	


	
	public void addNavigationObserver(NavigationObserver robotObserver){
		navObservers.add(robotObserver);
		//TODO esto se hará así, supongo
		this.navigation.addNavigationObserver(robotObserver);
	}
	
	public void addEngineObserver(RobotEngineObserver observer){
		robObservers.add(observer);
	}
	
	public void addItemContainerObserver(InventoryObserver c){
		invObservers.add(c);
		this.items.addItemContainerObserver(c);
	}
//OTROS MÉTODOS
	public Street getHeadingStreet() {
		return this.cityMap.lookForStreet(this.place, this.direction);
	}
	
	public void moveToPlace(Place headingPlace){
		this.place = headingPlace;
		if (this.place.isSpaceship()){
			for (RobotEngineObserver o: robObservers)
				o.engineOff(true);
		}
	}

	public void say(String mensaje) {
		System.out.println("WALL·E says: " + mensaje);
	}

//OBsoleto	
	public void startEngine() {
		Instruction instruccion = null;
		String command = new String();
		emitPartidaEmpezada();
		/**
		 Cosas antiguas
		 
		System.out.println(this.place.toString());
		System.out.println("WALL·E is looking at direction "
				+ this.direction.toString());
		printRobotState();
*/
		Scanner comando = new Scanner(System.in);

		while (!(quit || this.place.isSpaceship() || this.fuel == 0)) {

			if (!this.place.isSpaceship() && (this.fuel != 0) && !quit) {

				//prompt();
				command = comando.nextLine();
				try {
					instruccion = Interpreter.generateInstruction(command);
					communicateRobot(instruccion);
				} catch (WrongInstructionFormatException exc) {

					say(exc.getMessage());
				}
			}
		}
		comando.close();
		if (!quit) for (RobotEngineObserver o : robObservers) o.engineOff(this.place.isSpaceship());
		else for (RobotEngineObserver o : robObservers) o.communicationCompleted();
		/**
		  Más cosas antiguas
		
		if (this.place.isSpaceship())
			say("I am at my spaceship. Bye bye");
		else if (this.fuel == 0)
			say("I run out of fuel. I cannot move. Shutting down...");
		else
			say("I have communication problems. Bye bye");
			
		 */
		
	}

	
	public String[] getItemsFromContainer(int n){
		return items.itemForTable(n);
	}
	public int numberOfItems(){
		return this.items.numberOfItems();
	}
	public void printRobotState() {
		if (this.fuel < 0)
			this.fuel = 0;
		System.out.println("      * My power is " + this.fuel);
		System.out.println("      * My recycled material is "
				+ this.recycledMaterial);
	}
//END obsoleto
	
	private void emitPartidaEmpezada() {
		for (NavigationObserver o : navObservers){ 
			o.initNavigationModule(this.place, this.direction);
		}
		for (RobotEngineObserver obs: robObservers){
			obs.robotUpdate(this.fuel, this.recycledMaterial);
		}
	}
	
	private Place place;
	private Direction direction;
	private City cityMap;
	private int fuel;
	private ItemContainer items;
	private int recycledMaterial;
	private NavigationModule navigation;
	private boolean quit;
	private Stack<Instruction> instructions;
	private Vector<RobotEngineObserver> robObservers;
	private Vector<NavigationObserver> navObservers;
	private Vector<InventoryObserver> invObservers;
	
	

}
