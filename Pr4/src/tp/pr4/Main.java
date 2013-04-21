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
 * @author Jesús Aguirre Pemán, Jaime Dan Porras Rhee
 * @y.exclude
 */
public class Main {

	

	/**
	 * Creates the city, the engine and finally starts the simulation
	 * 
	 * @param args
	 */

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
		map.setArgName("mapfile");
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
						+ " [-h] [-i <type>] [-m <mapfyle>]", opt);
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
	
	
}
