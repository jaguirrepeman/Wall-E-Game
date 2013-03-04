package tp.pr4;

import java.util.Scanner;

import tp.pr4.instructions.*;
import tp.pr4.instructions.exceptions.*;
import tp.pr4.items.ItemContainer;

public class RobotEngine {

	public RobotEngine(City cityMap, Place initialPlace, Direction dir) {

		this.cityMap = cityMap;
		this.direction = dir;
		this.place = initialPlace;
		this.fuel = 100;
		this.items = new ItemContainer();
		this.recycledMaterial = 0;
		this.navigation = new NavigationModule(cityMap, initialPlace);
		this.quit = false;
	}

	public void startEngine() {

		navigation.initHeading(direction);
		Instruction instruccion = null;
		String command = new String();
		System.out.println(this.place.toString());
		System.out.println("WALL·E is looking at direction "
				+ this.direction.toString());
		printRobotState();

		Scanner comando = new Scanner(System.in);

		while (!(quit || this.place.isSpaceship() || this.fuel == 0)) {

			if (!this.place.isSpaceship() && (this.fuel != 0) && !quit) {

				prompt();
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
		if (this.place.isSpaceship())
			say("I am at my spaceship. Bye bye");
		else if (this.fuel == 0)
			say("I run out of fuel. I cannot move. Shutting down...");
		else
			say("I have communication problems. Bye bye");
	}

	public void addFuel(int fuel) {
		this.fuel += fuel;
	}

	public void addRecycledMaterial(int weight) {
		this.recycledMaterial += weight;
	}

	public void requestHelp() {
		System.out.println(Interpreter.interpreterHelp());
	}

	public void communicateRobot(Instruction c) {
		c.configureContext(this, navigation, items);
		try {
			c.execute();
			this.place = navigation.getCurrentPlace();
		} catch (InstructionExecutionException exc) {
			System.out.println(exc.getMessage());
		}
	}

	public void printRobotState() {
		if (this.fuel < 0)
			this.fuel = 0;
		System.out.println("      * My power is " + this.fuel);
		System.out.println("      * My reclycled material is "
				+ this.recycledMaterial);
	}

	public void requestQuit() {
		quit = true;
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
		System.out.println("WALL·E> " + mensaje);
	}

	public void prompt() {
		System.out.print("WALL·E> ");
	}

	private Place place;
	private Direction direction;
	private City cityMap;
	private int fuel;
	private ItemContainer items;
	private int recycledMaterial;
	private NavigationModule navigation;
	private boolean quit;
}
