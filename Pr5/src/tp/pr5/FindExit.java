package tp.pr5;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Stack;
import java.util.Vector;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.MissingOptionException;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import tp.pr5.cityLoader.CityLoaderFromTxtFile;
import tp.pr5.cityLoader.cityLoaderExceptions.WrongCityFormatException;
import tp.pr5.console.Console;
import tp.pr5.gui.GUIController;
import tp.pr5.gui.MainWindow;
import tp.pr5.instructions.Instruction;
import tp.pr5.instructions.MoveInstruction;
import tp.pr5.instructions.OperateInstruction;
import tp.pr5.instructions.PickInstruction;
import tp.pr5.instructions.TurnInstruction;
import tp.pr5.instructions.exceptions.InstructionExecutionException;


public class FindExit {
	FindExit(City city, Place initial, Direction dir){
		game = new RobotEngine(city, initial, dir);
	}

	Instruction ins;
	//(-d|-max-depth) n (-m|-map) <mapFilename>
	public void solve(int maxDepth) {

		solucion = new Stack<Instruction>();// [maxDepth+1];
		solucionMejor = new Stack<Instruction>();// [maxDepth+1];
		maze(0, maxDepth);
		System.out.println("Que tal");
		for (int i = 0; i < solucionMejor.size(); i++)
			System.out.println(solucionMejor.get(i).toString());

		MainWindow window = new MainWindow(new GUIController(game));
//		Console console = new Console();
		game.addEngineObserver(window);
//		game.addEngineObserver(console);
//		game.addItemContainerObserver(console);
//		game.addNavigationObserver(console);
		window.disableButtons();
		window.setVisible(true);

		for (int i = 0; i < solucionMejor.size(); i++) {
			game.communicateRobot(solucionMejor.get(i));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();	
			}
		}
	}
		
		
	
	public static void main(String args[]){

		Options opt = new Options();
		Option depth = new Option("d", "max-depth", true, "The maximum depth of the route");
		opt.addOption(depth);
		Option map = new Option("m", "map", true, "File with the description of the city");
		map.setArgName("mapfile");
		opt.addOption(map);
		
		BasicParser parser = new BasicParser();
		String fileName = null;
		try {
			/*
			 * Comprobación de los parámetros para ejecutar la aplicación
			 * 
			 */
			
			CommandLine cmd = parser.parse(opt, args);
			if (!cmd.hasOption('m')) 
				throw new MissingOptionException("Map file not specified");
				
			if (!cmd.hasOption('d')) {
				throw new MissingOptionException("Maximum depth not specified");
			}
			
			/*
			 * Si no hay parámetros erróneos se lee el nombre del mapa y se comprueba 
			 * 	si se ha deseado ejecutar la aplicación en swing, en consola o en ambas
			 */
			fileName = cmd.getOptionValue('m');

			/*
			 * Lectura del mapa y creación de la ciudad
			 */
			FileInputStream file = new FileInputStream(fileName);
			
			CityLoaderFromTxtFile cityLoader = new CityLoaderFromTxtFile();
			City city = cityLoader.loadCity(file);
			/*
			 * Creación del robotEngine
			 */

				String d = cmd.getOptionValue('d');
				int maxDepth = Integer.parseInt(d);
				cmd.getOptionValue('m');
				FindExit fe = new FindExit(city, cityLoader.getInitialPlace(), Direction.NORTH);
				fe.solve(maxDepth);

			
			
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
			System.err.println(e.getLocalizedMessage());
			System.err.println("Error reading the map file: " + fileName
					+ " (La sintaxis del fichero no es correcta)");
			System.exit(2);
		}
		
	}

	private boolean esValida(){
		return game.getFuel() > 0;
	}
	
	private void dataProcess(Instruction instruccion, int k, int maxDepth){
		
		//solucion[k] = instruccion;
		solucion.push(instruccion);
		
		if (isSolution()){
			if (k < costeMejor){
				costeMejor = k;
				//for (int indice = 0; indice <= k; indice++)
				//	solucionMejor[indice] = solucion[indice];
				solucionMejor = (Stack<Instruction>)solucion.clone();
				//solucionMejor = (Instruction[])solucion.clone();
			}
			
			System.out.println("Se ha encontrado una solucion");
			//imprimirSolucion(solucionMejor, k);
			solucion.pop().undo();
		}
		
		else {
			maze(k+1, maxDepth);
			//solucion[k].undo();
			solucion.pop().undo();
			//solucion[k].undo();
			//game.undoInstruction();
		}
	}
	
	private void executor(Instruction instruccion, int k, int maxDepth){
		
		game.configureLittleContext(instruccion);
		try {
			instruccion.execute();
			if (esValida())
				dataProcess(instruccion, k, maxDepth);
			
			else instruccion.undo();
			
		} catch (InstructionExecutionException e) {
			
		}
		
	}
	/**
	 * Funcion del laberinto
	 * @param k
	 * @param maxDepth
	 * @param coste
	 */
	 void maze (int k, int maxDepth){
		for (int i = 0; i< instructions.length && k < maxDepth; i++){
			
			if (i==0){
			//	if (game.canMove()){
					executor(new MoveInstruction(), k, maxDepth);
				//}
			}
			else if (i== 1){
				executor(new TurnInstruction("Right"), k, maxDepth);
			}
			else if (i==2){
				executor(new TurnInstruction("Left"), k, maxDepth);
			}
			else if (i==3){
				for (String objects: game.robotItems()){
					executor(new OperateInstruction(objects), k, maxDepth);
				}
			}
			else if (i==4){
				for (String objects: game.placeItems()){
					executor(new PickInstruction(objects), k, maxDepth);
				}
			}
			
		}
		
	}
	private boolean isSolution(){
		return game.atSpaceship();
	}
	private String objectToOperate;
	private String objectToPick;
	private RobotEngine game;
	private Instruction[] instructions = { new MoveInstruction(),
			new TurnInstruction("Right"), new TurnInstruction("Left"), 
			new OperateInstruction(objectToOperate), new PickInstruction(objectToPick)
	};
	
	private Vector<Instruction> solucionMejor;
	private Stack<Instruction> solucion;
	private int costeMejor = 1000000000;

}


