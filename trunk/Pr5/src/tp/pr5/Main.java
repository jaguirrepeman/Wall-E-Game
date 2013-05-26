package tp.pr5;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import tp.pr5.City;
import tp.pr5.Direction;
import tp.pr5.RobotEngine;
import tp.pr5.cityLoader.CityLoaderFromTxtFile;
import tp.pr5.cityLoader.cityLoaderExceptions.WrongCityFormatException;
import tp.pr5.console.Console;
import tp.pr5.console.ConsoleController;
import tp.pr5.gui.GUIController;
import tp.pr5.gui.MainWindow;
import org.apache.commons.cli.*;


/**
 * gluglu Aplicación que utiliza las clases de la prática sobre el mapa que
 * aparece como ejemplo en el enunciado.
 * 
 * But in our own way, we are king.
 *	Hails to the king, baby.
 * 
 * @author Jesús Aguirre Pemán, Jaime Dan Porras Rhee
 * @y.exclude
 */
public class Main {

	

	/**
	 * Creates the city, the engine and the mainwindow and finally starts the simulation
	 * 
	 * @param args
	 */

	public static void main(String[] args){
		
		/*
		 * Creación de las opciones del main
		 */
		Options opt = new Options();
		Option help = new Option("h", "help", false, "Shows this help message");
		opt.addOption(help);
		Option interf = new Option("i", "interface", true, "The type of interface: console, swing or both");
		interf.setArgName("type");
		Option map = new Option("m", "map", true, "File with the description of the city");
		map.setArgName("mapfile");
		opt.addOption(interf);
		opt.addOption(map);
		
		BasicParser parser = new BasicParser();
		boolean /*cons = false, */swingOn = false, both = false;
		String fileName = null;
		try {
			/*
			 * Comprobación de los parámetros para ejecutar la aplicación
			 * 
			 */
			
			CommandLine cmd = parser.parse(opt, args);
			/*
			 * Mensaje de ayuda
			 */
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
			 * Faltan argumentos, no se ha elegido ni consola ni swing, o no hay mapa que cargar
			 */
			if (!cmd.hasOption('m')) 
				throw new MissingOptionException("Map file not specified");
				
			if (!cmd.hasOption('i')) {
				throw new MissingOptionException("Interface not specified");
			}
			else if (!(cmd.getOptionValue('i').equals("console") || cmd
							.getOptionValue('i').equals("swing") || cmd.getOptionValue('i').equals("both"))){
				throw new ParseException("Wrong type of interface");
			}
			
			
			/*
			 * Si no hay parámetros erróneos se lee el nombre del mapa y se comprueba 
			 * 	si se ha deseado ejecutar la aplicación en swing, en consola o en ambas
			 */
			fileName = cmd.getOptionValue('m');
			if (cmd.getOptionValue('i').equals("console")) {
				//cons = true;//swingOn = true;
				swingOn = false;

			}
			else if (cmd.getOptionValue('i').equals("swing")) {
				swingOn = true;//swingOn = true;
			
				
			}
			else{
				swingOn = true;
				both = true;
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
			
			if (!swingOn){
				/*
				 * Se crea el controlador de la consola y se añade el observador consola
				 */
				ConsoleController consCont = new ConsoleController(wallE);
				Console console = new Console();
				wallE.addEngineObserver(console);
				wallE.addNavigationObserver(console);
				wallE.addItemContainerObserver(console);
				consCont.startEngine();
			}

			if (swingOn){
				try {
					UIManager.setLookAndFeel(
					        UIManager.getCrossPlatformLookAndFeelClassName());
				} 
				catch (ClassNotFoundException e1) {}
				catch (InstantiationException e1) {} 
				catch (IllegalAccessException e1) {} 
				catch (UnsupportedLookAndFeelException e1) {}
				/*
				 * Se crea el controlador de GUI y se añaden los observadores
				 */
				GUIController guiCont = new GUIController(wallE);
				MainWindow window = new MainWindow(guiCont);
				window.setVisible(true);
				wallE.addEngineObserver(window);
				if (both){
					Console console = new Console();
					wallE.addEngineObserver(console);
					wallE.addNavigationObserver(console);
					wallE.addItemContainerObserver(console);
				}
				guiCont.startController();
			}
			 
			
		}
		/*
		 * Faltan argumentos
		 */
		catch (MissingOptionException e){
			System.err.println(e.getMessage());
			System.exit(1);
		}
		/*
		 * Parseo erróneo
		 */
		catch (ParseException e) {
			//e.printStackTrace();
			System.err.println(e.getMessage());
			System.exit(3);
			
			
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

			System.err.println("Error reading the map file: " + fileName
					+ " (La sintaxis del fichero no es correcta)");
			System.exit(2);
		}
		

		
	}
	
	
}
