package tp.pr5;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Stack;
import java.util.Vector;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.MissingOptionException;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import tp.pr5.cityLoader.CityLoaderFromTxtFile;
import tp.pr5.cityLoader.cityLoaderExceptions.WrongCityFormatException;
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
	public boolean solve(int maxDepth) {
		boolean solved = false;
		solution = new Stack<Instruction>();// [maxDepth+1];
		bestSolution = new Stack<Instruction>();// [maxDepth+1];
		maze(0, maxDepth);
		
		if (bestSolution.size() > 0) solved = true;
		
		if (solved){
			
			try {
				
				System.out.println("Found a solution of size: " + bestSolution.size());
				/**
				 * Usado para que salga bien en Mac
				 */
				UIManager.setLookAndFeel(
				        UIManager.getCrossPlatformLookAndFeelClassName());
				
				GUIController gameController = new GUIController(game);
				MainWindow window = new MainWindow(gameController);
				game.addEngineObserver(window);
				window.disableButtons();
				window.setVisible(true);
				gameController.startController();

				for (int i = 0; i < bestSolution.size(); i++) {
					System.out.println(bestSolution.get(i).toString());
					Thread.sleep(300);
					game.communicateRobot(bestSolution.get(i));

					Thread.sleep(1000);

				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (UnsupportedLookAndFeelException e) {
				e.printStackTrace();
			}
		}
		else System.out.println("Could not find a solution with depth "+ maxDepth );
		return solved;
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
				boolean solved = false;
				for (int i = 1; i <= maxDepth && ! solved; i++)
					solved = fe.solve(i);

			
			
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

	private boolean isValid(){
		return game.getFuel() > 0;
	}
	
	@SuppressWarnings("unchecked")
	private void dataProcess(Instruction instruccion, int k, int maxDepth){

		solution.push(instruccion);
		
		if (isSolution()){
			if (k < betterCost){
				betterCost = k;
				//for (int indice = 0; indice <= k; indice++)
				//	solucionMejor[indice] = solucion[indice];
				bestSolution = (Vector<Instruction>) solution.clone();
				//solucionMejor = (Instruction[])solucion.clone();
			}
			
			solution.pop().undo();
		}
		
		else {
			maze(k+1, maxDepth);
			//solucion[k].undo();
			solution.pop().undo();
			//solucion[k].undo();
			//game.undoInstruction();
		}
	}
	
	private void executor(Instruction instruccion, int k, int maxDepth){
		
		game.configureLittleContext(instruccion);
		try {
			instruccion.execute();
			if (isValid())
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
	
	private Vector<Instruction> bestSolution;
	private Stack<Instruction> solution;
	private int betterCost = 1000000000;

}


