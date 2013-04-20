package tp.pr4;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import tp.pr4.City;
import tp.pr4.Direction;
import tp.pr4.Place;
import tp.pr4.RobotEngine;
import tp.pr4.Street;
import tp.pr4.Gui.MainWindow;
import tp.pr4.cityLoader.CityLoaderFromTxtFile;
import tp.pr4.cityLoader.cityLoaderExceptions.WrongCityFormatException;
import tp.pr4.items.CodeCard;
import tp.pr4.items.Fuel;
import tp.pr4.items.Garbage;
import org.apache.commons.cli.*;

/*
 * 
 * public class FilterOutputStream extends OutputStream{privateOutputStream out; private boolean is writing;
 * Const(OutputStream out, boolean isWriting){this.out = out; this.isWriting = isWriting}
 * @Override 
 * public void write(int b)throws IOException{
 * if (isWriting) out.write(b);
 * 
 * 
 * }
 * 
 * System.setOut(new PrintStream(new FilterOutputStream(Sytem.out, true))));
 * System.setErr(new PrintStream(new FilterOutputStream(System.err, true))));
 */
/**
 * gluglu Aplicación que utiliza las clases de la práctica sobre el mapa que
 * aparece como ejemplo en el enunciado.
 * 
 * @author PuriArenas
 * @y.exclude
 */
public class Main {

	/**
	 * Creates the places and configure the initial current place.
	 * 
	 * @return The place where the robot starts the game
	 */
	
	private static Place[] createPlaces() {
		Place[] _places = new Place[10];
		// 0: Puerta del Sol
		_places[0] = new Place(
				"SOL",
				false,
				"You are at the PUERTA DEL SOL, the center of Madrid. "
						+ Interpreter.LINE_SEPARATOR
						+ "Ufff, there are a lot of streets, but all of them are full of garbage."
						+ Interpreter.LINE_SEPARATOR
						+ "You should walk around this place to look for some interesting"
						+ Interpreter.LINE_SEPARATOR
						+ "objects to pick up."
						+ Interpreter.LINE_SEPARATOR
						+ "Note that there is a big clock. Remember, leave the square before "
						+ Interpreter.LINE_SEPARATOR
						+ "night. It can be dangerous");
		// 1: Plaza Mayor
		_places[1] = new Place("PLAZA MAYOR", false,
				"Mmmh... it smells squid fried in butter. You should try to eat something");

		// 2: Plaza España
		_places[2] = new Place(
				"PLAZA ESPAÑA",
				false,
				"What a big square! You have a big park where you can sleep under a tree."
						+ Interpreter.LINE_SEPARATOR
						+ "But you have a problem, you have to come back to PLAZA MAYOR. "
						+ Interpreter.LINE_SEPARATOR + "There is no other exit");

		// 3: Plaza de Callao
		_places[3] = new Place("CALLAO", false,
				"In this small square you can find some some fuel. "
						+ Interpreter.LINE_SEPARATOR
						+ "Go to fnac and take the fuel for the heating");

		// 4: Plaza de Colón (END)
		_places[4] = new Place(
				"COLON",
				true,
				"Sometime ago, Spanish people concentrates here to watch football "
						+ Interpreter.LINE_SEPARATOR
						+ "in a big screen."
						+ Interpreter.LINE_SEPARATOR
						+ "Wall-e, did you know that in Spain there were very good football teams?."
						+ Interpreter.LINE_SEPARATOR
						+ "Look for cans! People throw cans after watching football!");

		// 5: Cibeles
		_places[5] = new Place("CIBELES", false,
				"Arggg, the fountain is ugly! The water is very dirty. "
						+ Interpreter.LINE_SEPARATOR
						+ "You should leave before the lions attack you");

		// 6: Neptuno
		_places[6] = new Place("NEPTUNO", false,
				"Watch Wall-e! Another fountain! Perhaps this one has water for drinking"
						+ Interpreter.LINE_SEPARATOR
						+ "If you are cold, use that red and white scarf");

		// 7: Atocha
		_places[7] = new Place("ATOCHA", false,
				"You have a lot of old trains here, but they do not work"
						+ Interpreter.LINE_SEPARATOR
						+ "All trains were destroyed during the crisis of 2013"
						+ Interpreter.LINE_SEPARATOR
						+ "Take all the iron you find");

		// 8: Puerta de Alcala
		_places[8] = new Place("ALCALA", true,
				"Ok, finally you have found your spaceship....");

		// 9: Plaza de Jacinto Benavente
		_places[9] = new Place("BENAVENTE", false,
				"If you are cold you can start a fire with the wheels of those old buses");

		return _places;
	}

	/**
	 * Creates the different streets between the places. It adds items to the
	 * places
	 * 
	 * @param _places
	 * @return An array of streets
	 */
	private static Street[] createStreets(Place[] _places) {
		Street[] _streets = new Street[11];

		_streets[0] = new Street(_places[0], Direction.NORTH, _places[3],
				false, "red_pill");
		_streets[1] = new Street(_places[0], Direction.SOUTH, _places[9]);
		_streets[2] = new Street(_places[0], Direction.EAST, _places[5], false,
				"12345");
		_streets[3] = new Street(_places[0], Direction.WEST, _places[1]);

		_streets[4] = new Street(_places[1], Direction.NORTH, _places[2]);

		_streets[5] = new Street(_places[3], Direction.WEST, _places[2]);

		_streets[6] = new Street(_places[5], Direction.NORTH, _places[4],
				false, "Joshua");
		_streets[7] = new Street(_places[5], Direction.EAST, _places[8], false,
				"eva");
		_streets[8] = new Street(_places[5], Direction.SOUTH, _places[6]);

		_streets[9] = new Street(_places[6], Direction.SOUTH, _places[7]);
		_streets[10] = new Street(_places[9], Direction.EAST, _places[6]);

		_places[0].addItem(new Garbage("newspapers", "news on sports", 5));
		_places[0].addItem(new Fuel("grapes", "celebrations of the new year",
				1, 1));
		_places[0]
				.addItem(new CodeCard(
						"Spaceballs-card",
						"This is the kind of combination an idiot would have on his luggage",
						"12345"));
		_places[0]
				.addItem(new Fuel(
						"Coal",
						"Be careful with this fuel because it is extremely contaminant",
						-80, 1));

		_places[1].addItem(new Fuel("gas-oil", "from the buses..", 5, 2));
		_places[1].addItem(new Garbage("explosive-plastic",
				"too dangerous to be in the street", 10));
		_places[1].addItem(new CodeCard("matrix-card",
				"It shows you how deep the rabbit hole goes", "red_pill"));

		_places[3].addItem(new Garbage("cans-of-beer", "all of them are empty",
				2));
		_places[3].addItem(new Fuel("petrol", "from olds heatings", 10, 3));

		_places[4].addItem(new Fuel("battery", "to get cracking", 4, 1));
		_places[4].addItem(new Garbage("television",
				"it is broken. Better. Usually programs are really bad", 10));

		_places[5]
				.addItem(new Fuel("water", "to put out possible fires", 11, 3));
		_places[5].addItem(new Garbage("white-scarf", "good for winter", 2));
		_places[5].addItem(new CodeCard("walle-card",
				"I lost it when I was looking for garbage", "eva"));
		_places[5]
				.addItem(new CodeCard(
						"Spaceballs-card",
						"This is the kind of combination an idiot would have on his luggage",
						"12345"));

		_places[6].addItem(new Garbage("tinned-food",
				"it seems spanish people were hungry", 11));
		_places[6].addItem(new Garbage("yellow-press",
				"what stupid things they write", 1));

		_places[7].addItem(new Fuel("red-bull", "good for flying", 5, 1));
		_places[7].addItem(new CodeCard("falken-card", "Shall we play a game?",
				"Joshua"));

		return _streets;
	}

	/**
	 * Creates the city, the engine and finally starts the simulation
	 * 
	 * @param args
	 */


	public static void mainA(String[] args) {
		if (args.length == 0 || args.length > 1) {

			System.err.println("Bad params." + Interpreter.LINE_SEPARATOR
					+ "Usage: "+ Main.class.getCanonicalName() + "<mapfile>"
					+ Interpreter.LINE_SEPARATOR + Interpreter.LINE_SEPARATOR
					+ "<mapfile> : file with the description of the city.");
			System.exit(1);
		}
		try {

			FileInputStream file = new FileInputStream(args[0]);
			CityLoaderFromTxtFile cityLoader = new CityLoaderFromTxtFile();
			City city = cityLoader.loadCity(file);
			RobotEngine WallE = new RobotEngine(city,
					cityLoader.getInitialPlace(), Direction.NORTH);
			WallE.startEngine();
			MainWindow window = new MainWindow(WallE, cityLoader.getInitialPlace());
			window.setVisible(true);
		} catch (FileNotFoundException e) {

			System.err
					.println("Error reading the map file: noExiste.txt (No existe el fichero o el directorio)");
			System.exit(2);
		} catch (WrongCityFormatException e) {

			System.err.println("Error reading the map file: " + args[0]
					+ " (La sintaxis del fichero no es correcta)");
			System.exit(2);
		}

		System.exit(0);

	}
	public static void main(String[] args){
		/**
		 * esto es para que funcione bien en mac 
		 */
		try {
			UIManager.setLookAndFeel(
			        UIManager.getCrossPlatformLookAndFeelClassName());
		} 
		catch (ClassNotFoundException e1) {}
		catch (InstantiationException e1) {} 
		catch (IllegalAccessException e1) {} 
		catch (UnsupportedLookAndFeelException e1) {}
		
		Options opt = new Options();
		Option help = new Option("h", "help", false, "Shows this help message");
		opt.addOption(help);
		Option interf = new Option("i", "interface", true, "The type of interface: console or swing");
		interf.setArgName("type");
		Option map = new Option("m", "map", true, "File with the description of the city");
		opt.addOption(interf);
		opt.addOption(map);
		
		BasicParser parser = new BasicParser();
		boolean swingOn = false;
		String fileName = null;
		try {
			/*
			 * Comprobación de los parámetros para ejecutar la aplicación
			 * 
			 */
			
			CommandLine cmd = parser.parse(opt, args);
			
			HelpFormatter h = new HelpFormatter();
			boolean requestHelp = cmd.hasOption('h');
			if(requestHelp){
				System.out.println("Execute this assignment with these parameters:");
				h.printHelp(
						Main.class.getCanonicalName()
						+ " [-h] [-i <type>] [-m <mapfile>]", opt);
				if (!cmd.hasOption('m') && !cmd.hasOption('i')) System.exit(0);
			}
			/*
			 * Parámetros erróneos, no se ha elegido ni consola ni swing, o no hay mapa que cargar
			 */
			if (!cmd.hasOption('m')) 
				throw new ParseException("Map file not specified");
				
			if (!cmd.hasOption('i')) {
				throw new ParseException("Interface not specified");
			}
			else if (!(cmd.getOptionValue('i').equals("console") || cmd
							.getOptionValue('i').equals("swing"))){
				throw new ParseException("Wrong type of interface");
			}
			
			/*else {
				if (!cmd.hasOption('h'))
					h.printHelp("Help", opt);
				System.err.println("Bad params." + Interpreter.LINE_SEPARATOR
					+ "Usage: "+ Main.class.getCanonicalName() + "<mapfile>"
					+ Interpreter.LINE_SEPARATOR + Interpreter.LINE_SEPARATOR
					+ "<mapfile> : file with the description of the city.");
				///throw new ParseException("hello");
				//System.exit(1);
			}*/
			/*
			 * Si no hay parámetros erróneos se lee el nombre del mapa y se comprueba 
			 * 	si se ha deseado ejecutar la aplicación en swing o no
			 */
			fileName = cmd.getOptionValue('m');
			if (cmd.getOptionValue('i').equals("swing")) {
					swingOn = true;

			}
			/*
			 * Lectura del mapa y creación de la ciudad
			 */
			FileInputStream file = new FileInputStream(fileName);
			
			CityLoaderFromTxtFile cityLoader = new CityLoaderFromTxtFile();
			City city = cityLoader.loadCity(file);
			/*
			 * Creación del robotEngine
			 */
			RobotEngine wallE = new RobotEngine(city, cityLoader.getInitialPlace(), Direction.NORTH);
			/*
			 * Si se ha elegido ejecutar la aplicación con swing, se cancelan las salidas por consola
			 * y se crea el mainWindow
			 */
			if (swingOn){
				System.setOut(new PrintStream(new FilterOutputStream(System.out, false)));
				System.setErr(new PrintStream(new FilterOutputStream(System.err, false)));
				
				MainWindow window = new MainWindow(wallE, cityLoader.getInitialPlace());
				window.setVisible(true);
			}
			/*
			 * Comienza la ejecución del programa
			 */
			wallE.startEngine();
			System.exit(0);
		}
		/*
		 * Parseo erróneo
		 */
		catch (ParseException e) {
			//e.printStackTrace();
			System.err.println(e.getMessage());
			System.exit(1);
			
			
		} 
		/*
		 * Mapa no encontrado
		 */
		catch (FileNotFoundException e) {
		 

			System.err
					.println("Error reading the map file: "+ fileName +" (No existe el fichero o el directorio)");
			System.exit(2);
		}
		/*
		 * Mapa con formato erróneo
		 */
		catch (WrongCityFormatException e) {

			System.err.println("Error reading the map file: " + args[0]
					+ " (La sintaxis del fichero no es correcta)");
			System.exit(2);
		}
		

		
	}
	public static void mainAnt(String[] args){
		/**
		 * esto es para que funcione bien en mac 
		 */
		try {
			UIManager.setLookAndFeel(
			        UIManager.getCrossPlatformLookAndFeelClassName());
		} 
		catch (ClassNotFoundException e1) {}
		catch (InstantiationException e1) {} 
		catch (IllegalAccessException e1) {} 
		catch (UnsupportedLookAndFeelException e1) {}
		Options opt = new Options();
		Option help = new Option("h", "help", false, "Shows this help message");
		opt.addOption(help);
		Option interf = new Option("i", "interface", true, "The type of interface: console or swing");
		interf.setArgName("type");
		opt.addOption(interf);
		Option map = new Option("m", "map", true, "File with the description of the city");
		map.setArgName("mapfile");
		opt.addOption(map);
		
		BasicParser parser = new BasicParser();
		boolean swingOn = false;
		try {
			CommandLine cmd = parser.parse(opt, args);
			if(cmd.hasOption('h')){
				HelpFormatter h = new HelpFormatter();
				h.printHelp("Help", opt);
			}
			
			//
			
			if(cmd.getOptionValue('i').equals("swing")){
				swingOn = true;
				System.setOut(new PrintStream(new FilterOutputStream(System.out, false)));
				System.setErr(new PrintStream(new FilterOutputStream(System.err, false)));
			}
			else if (cmd.getOptionValue('i').equals("console")){
				System.setOut(new PrintStream(new FilterOutputStream(System.out, true)));
				System.setErr(new PrintStream(new FilterOutputStream(System.err, true)));
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		// create the different places
		Place[] places = createPlaces();
		// create the different Streets generating the map
		Street[] streets = createStreets(places);
		// crate the engine of the game
		RobotEngine engine = new RobotEngine(new City(streets), places[0],
				Direction.NORTH);
		// plays
		if (swingOn){
			MainWindow window = new MainWindow(engine, places[0]);
			window.setVisible(true);
		}
		engine.startEngine();

		

	}
	
}
