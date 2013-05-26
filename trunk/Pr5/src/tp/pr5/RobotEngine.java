package tp.pr5;

import java.util.Scanner;
import java.util.Stack;

import tp.pr5.instructions.*;
import tp.pr5.instructions.exceptions.*;
import tp.pr5.items.*;


public class RobotEngine extends tp.pr5.Observable<RobotEngineObserver>
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
		this.quit = false;
		
		
	}

	public boolean isOver(){
		
		return ((quit) || (fuel == 0) ||( this.place.isSpaceship()));
		
	}
	public boolean atSpaceship(){
		return this.place.isSpaceship();
	}
	
	public void configureLittleContext(Instruction c){
		c.configureContext(this, this.navigation, this.items);
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
			for (RobotEngineObserver o: this.observers)o.robotUpdate(this.fuel, this.recycledMaterial);
			for (RobotEngineObserver o: this.observers)o.engineOff(false);
		}
		/** TODO de momento se quita esto*/ else for (RobotEngineObserver o : this.observers) o.robotUpdate(this.fuel, this.recycledMaterial);
		//if (robotPanel != null) robotPanel.setStatus(this.fuel, this.recycledMaterial);
	}

	public void addRecycledMaterial(int weight) {
		this.recycledMaterial += weight;
		/** TODO de momento se quita esto*/	for (RobotEngineObserver o : this.observers) o.robotUpdate(this.fuel, this.recycledMaterial);
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
		this.emitPartidaEmpezada();
		/*for (NavigationObserver o : navObservers){ 
			o.initNavigationModule(this.place, this.direction);
		}*/
		/*for (RobotEngineObserver obs: this.observers){
			obs.robotUpdate(this.fuel, this.recycledMaterial);
		}*/
	}
	
	public void requestError(String msg){
		for (RobotEngineObserver obs: this.observers){
			obs.raiseError(msg);
		}
	}
	
	public void requestHelp() {
		for (RobotEngineObserver o : this.observers) 
			o.communicationHelp(Interpreter.interpreterHelp());
	}
	
	public void undoInstruction(){
		if (!instructions.isEmpty())
			instructions.pop().undo();
		else saySomething("There is no instruction to be undone.");
	}

	public void commandQuit() {
		quit = true;
		requestQuit();
		
	}
	
	public void requestQuit() {
		if (!quit)
			for (RobotEngineObserver o : this.observers) 
				o.engineOff(this.place.isSpaceship());
		else
			for (RobotEngineObserver o : this.observers)
			o.communicationCompleted();
		
	}
	
	public void saySomething(String message){
		for (RobotEngineObserver obs: this.observers){
			obs.robotSays("WALL·E says: " + message);
		}
	}
	
	public void addNavigationObserver(NavigationObserver robotObserver){
		this.navigation.addNavigationObserver(robotObserver);
	}
	
	public void addEngineObserver(RobotEngineObserver observer){
		this.addObserver(observer);
	}
	
	public void addItemContainerObserver(InventoryObserver c){
		this.items.addItemContainerObserver(c);
	}
//OTROS MÉTODOS
	public Street getHeadingStreet() {
		return this.cityMap.lookForStreet(this.place, this.direction);
	}
	
	public void moveToPlace(Place headingPlace){
		this.place = headingPlace;
		if (this.place.isSpaceship()){
			for (RobotEngineObserver o: this.observers)
				o.engineOff(true);
		}
	}

	public void say(String mensaje) {
		System.out.println("WALL·E says: " + mensaje);
	}

//Obsoleto	
	public void startEngine() {
		Instruction instruccion = null;
		String command = new String();
		emitPartidaEmpezada();

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
		if (!quit) for (RobotEngineObserver o : this.observers) o.engineOff(this.place.isSpaceship());
		else for (RobotEngineObserver o : this.observers) o.communicationCompleted();

		
	}

	//END obsoleto

	public String[] robotItems(){
		return items.listOfItems();
	}
	
	public String[] placeItems(){
		return place.placeItems();
	}
	
	public String[] getItemsFromContainer(int n){
		return items.itemForTable(n);
	}
	public int numberOfItems(){
		return this.items.numberOfItems();
	}
	
	
	private void emitPartidaEmpezada() {
		this.navigation.initialize();
		for (RobotEngineObserver obs: this.observers){
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

}
