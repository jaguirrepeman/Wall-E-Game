package tp.pr3;


import java.util.Scanner;

import tp.pr3.instructions.Instruction;
import tp.pr3.items.Item;
import tp.pr3.items.ItemContainer;

public class RobotEngine {
	
	public RobotEngine(City cityMap, Place initialPlace, Direction dir) {

		this.cityMap = cityMap;
		this.direction = dir;
		this.place = initialPlace;
		this.fuel = 50;
		this.items = new ItemContainer();
		this.recycledMaterial = 0;
		this.navigation = new NavigationModule (cityMap, initialPlace);
	}

	public void startEngine() {
	/*	
		Instruction instruccion = new Instruction();
		String command = new String();
		System.out.println(this.place.toString());
		printStatus();
		System.out.println("WALL·E is looking at direction "
				+ this.direction.toString());

		prompt();
		Scanner comando = new Scanner(System.in);

		command = comando.nextLine();
		instruccion = Interpreter.generateInstruction(command);
		while (!(instruccion.getAction().equals(Action.QUIT)
				|| this.place.isSpaceship() || this.fuel == 0)) {
			if (instruccion.getAction().equals(Action.HELP)) {
				System.out.println(Interpreter.interpreterHelp());
			} else if (instruccion.getAction().equals(Action.TURN)
					&& !(instruccion.getRotation().equals(Rotation.UNKNOWN))) {
				this.direction = direction.nextDirection(instruccion
						.getRotation());
				this.fuel -= 1;
				printStatus();
				System.out.println("WALL·E is looking at direction "
						+ this.direction.toString());

			} else if (instruccion.getAction().equals(Action.MOVE)) {
				// si existe una calle en la dirección actual del
				// robot...System.out.println("WALL·E > " + mensaje);
				if (!(this.cityMap.lookForStreet(this.place, this.direction) == null)) {
					//
					if (this.cityMap.lookForStreet(this.place, this.direction)
							.isOpen()) {
						this.place = this.cityMap.lookForStreet(this.place,
								direction).nextPlace(place);
						say("Moving in direction " + this.direction.toString()
								+ LINE_SEPARATOR + this.place.toString());
						this.fuel -= 5;
						printStatus();
						System.out.println("WALL·E is looking at direction "
								+ this.direction.toString());
					} else
						say("Arrggg, there is a street but it is closed!");

				}
				// en caso contrario
				else
					say("There is no street in direction "
							+ this.direction.toString());

			} else if (instruccion.getAction().equals(Action.OPERATE)) {
				// Item item2 = this.items.getItem(instruccion.getId());
				// hay que revisar algunas cosas
				int initialFuel = this.fuel;
				int initialRecMat = this.recycledMaterial;

				if (!(this.items.getItem(instruccion.getId()) == null)) {
					if (!this.items.getItem(instruccion.getId()).use(this,
							this.place)) { // si no se puede usar aqui
						if (!this.items.getItem(instruccion.getId())
								.canBeUsed()) { // si ya no se puede usar
							say("What a pity! I have no more "
									+ instruccion.getId() + " in my inventory");
							this.items.pickItem(instruccion.getId());
						} else
							say("I have problems using the object "
									+ instruccion.getId());
					} else {
						if ((initialFuel != this.fuel)
								|| (initialRecMat != this.recycledMaterial))
							printStatus();
						if (!this.items.getItem(instruccion.getId())
								.canBeUsed()) {
							say("What a pity! I have no more "
									+ instruccion.getId() + " in my inventory");
							this.items.pickItem(instruccion.getId());
						}
					}
				} else
					say("I have problems using the object "
							+ instruccion.getId());
			} else if (instruccion.getAction().equals(Action.PICK)) {
				Item item = this.place.pickItem(instruccion.getId());
				if (item != null) {
					if (this.items.addItem(item))
						say("I am happy! Now I have  " + instruccion.getId());
					else
						say("I am stupid! I had already the object "
								+ instruccion.getId());
				} else
					say("Ooops, this place has not the object "
							+ instruccion.getId());
			} else if (instruccion.getAction().equals(Action.SCAN)) {

				if (this.items.numberOfItems() == 0)
					say("My inventory is empty");
				else if (instruccion.getId() == null)
					say("I am carrying the following items" + LINE_SEPARATOR
							+ this.items.toString());
				else {
					Item item3 = this.items.getItem(instruccion.getId());
					if (item3 != null)
						say(item3.toString());
					else say("I don't have that item");
				}
			}

			else {

				say("I do not understand. Please repeat");
			}
			if (!this.place.isSpaceship() && (this.fuel != 0)) {
				// System.out.print(LINE_SEPARATOR);
				prompt();
				command = comando.nextLine();
				instruccion = Interpreter.generateInstruction(command);
			}

		}
		comando.close();
		if (this.place.isSpaceship())
			say("I am at my space ship. Bye Bye");
		else if (this.fuel == 0)
			say("I run out of fuel. I cannot move. Shutting down...");
		else
			say("I have communication problems. Bye bye");
		*/
		//NavigationModule navigation = new NavigationModule(cityMap, place);
		Instruction instruccion;
		String command = new String();
		System.out.println(this.place.toString());
		printStatus();
		System.out.println("WALL·E is looking at direction "
				+ this.direction.toString());

		prompt();
		Scanner comando = new Scanner(System.in);

		command = comando.nextLine();
		instruccion = Interpreter.generateInstruction(command);
		while (!(instruccion.getAction().equals(Action.QUIT)
				|| this.place.isSpaceship() || this.fuel == 0)) {
			
		}
	}

	public void addFuel(int fuel) {
		this.fuel += fuel;
	}

	public void addRecycledMaterial(int weight) {
		this.recycledMaterial += weight;
	}

	public void requestHelp(){
		System.out.println(Interpreter.interpreterHelp());
	}
	
	public void	communicateRobot(Instruction c) {
		c.configureContext(this, navigation, items);
		c.execute();
	}
	
	public void	printRobotState() {
		
	}
	
	public void	requestQuit() {
		
	}
		
	public int getFuel() {
		return this.fuel;
	}

	public int getRecycledMaterial() {
		return this.recycledMaterial;
	}

	public Street getHeadingStreet() {
		return this.cityMap.lookForStreet(this.place, this.direction);
	}

	public void say(String mensaje) {
		System.out.println("WALL·E says: " + mensaje);
	}

	public void prompt(String mensaje) {
		System.out.println("WALL·E > " + mensaje);
	}

	public void prompt() {
		System.out.print("WALL·E > ");
	}

	public void printStatus() {
		if (this.fuel < 0)
			this.fuel = 0;
		System.out.println("   * My power is " + this.fuel);
		System.out.println("   * My recycled material is: "
				+ this.recycledMaterial);
	}

	private static final String LINE_SEPARATOR = System
			.getProperty("line.separator");
	private Place place;
	private Direction direction;
	private City cityMap;
	private int fuel;
	private ItemContainer items;
	private int recycledMaterial;
	private NavigationModule navigation;
}
