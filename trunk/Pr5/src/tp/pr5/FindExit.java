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
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;


public class FindExit {
	FindExit(){
	//el constructor para hacer lo que dijo Marco	
	
	}
	Instruction ins;
	//(-d|-max-depth) n (-m|-map) <mapFilename>
	void solve(){
		int coste = 0, costeMejor = -1, maxDepth = 100;
		City city = null;
		Instruction[] sol = null, solMejor = null;
	//	Vector<Instruction> solucion = new Vector<Instruction>();
	//	Vector<Instruction> solucionMejor = new Vector<Instruction>();
		boolean marcas[][] = null;
		//RobotEngine engine = null;
		//laberinto(city, sol, solMejor, 0, maxDepth, marcas, coste, costeMejor, game);
			
	}
	
	public static void main(String args[]){
		Instruction[] instrucciones = new Instruction[instructions.length];
		/*for (int i=0; i<instructions.length; i++){
			
			instrucciones[instructions.length-i-1] = instructions[i];
			System.out.println(instrucciones[instructions.length-i-1].getHelp());
		}
		instrucciones[0] = new OperateInstruction("fuel");*/
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
			/*
			 * Mensaje de ayuda
			 */

			/*
			 * Faltan argumentos, no se ha elegido ni consola ni swing, o no hay mapa que cargar
			 */
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
			game = new RobotEngine(city, cityLoader.getInitialPlace(), Direction.NORTH);
			//Console console = new Console();
			//game.addEngineObserver(console);
			//game.addNavigationObserver(console);
			
			{
				/*
				 * Se crea el controlador de la consola y se añade el observador consola
				 */
			//	ConsoleController consCont = new ConsoleController(game);
			//	Console console = new Console();
			//	game.addEngineObserver(console);
			//	game.addNavigationObserver(console);
			//	game.addItemContainerObserver(console);
				//consCont.startEngine();
				//llamamos a la funcion recursiva
				
			//	Vector<Instruction> solucion = new Vector<Instruction>();
			//	Vector<Instruction> solucionMejor = new Vector<Instruction>();
				boolean marcas[][] = null;
				String d = cmd.getOptionValue('d');
				int maxDepth = Integer.parseInt(d);
				cmd.getOptionValue('m');
				int coste = 0;//, costeMejor = -1;
				//Instruction[] solucion = new Instruction[maxDepth], solucionMejor = new Instruction[maxDepth];
				solucion = new Stack<Instruction>();//[maxDepth+1];
				solucionMejor = new Stack<Instruction>();//[maxDepth+1];
				//solucion.ensureCapacity(maxDepth);
				//solucionMejor = new Vector<Instruction>(maxDepth);
				//solucion = new Vector<Instruction>(maxDepth);
				//solucionMejor.ensureCapacity(maxDepth);
				inicializarMarcas(marcas);
		//		inicializarArray(solucion, maxDepth);
		//		inicializarArray(solucionMejor, maxDepth);
				//solucion.ensureCapacity(maxDepth); // para poder usar el setElement en el backtracking
				//laberinto(city, /*solucion, solucionMejor,*/ 0, maxDepth, marcas, coste, costeMejor, game);
				laberinto(0, maxDepth, 0);
				System.out.println("Que tal");
				for (int i=0; i< solucionMejor.size()/*costeMejor*/; i++)
					System.out.println(solucionMejor.get(i).toString());
					//System.out.println(solucionMejor[i].toString());
				//imprimirSolucion(solucionMejor, maxDepth);
				MainWindow window = new MainWindow(new GUIController(game));
				Console console = new Console();
				game.addEngineObserver(window);
				game.addEngineObserver(console);
				game.addItemContainerObserver(console);
				game.addNavigationObserver(console);
				window.setVisible(true);
				for (int i=0; i< solucionMejor.size(); i++){
					game.communicateRobot(solucionMejor.get(i));
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				//game.addNavigationObserver(new MainWindow(null));
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
			System.err.println(e.getLocalizedMessage());
			System.err.println("Error reading the map file: " + fileName
					+ " (La sintaxis del fichero no es correcta)");
			System.exit(2);
		}
		
	}

	private static void imprimirSolucion(Instruction[] sol, int maxDepth) {
		boolean b = true;
		for (int i = 0; i <= maxDepth && b; i++)
			if (sol[i] != null)	System.out.println(sol[i].toString());
			else b = false;
		
	}

	private static void inicializarArray(Instruction[] solucion, int maxDepth) {
		for (int i = 0; i < maxDepth; i++)
			solucion[i] = null;
		
	}

	private static void inicializarMarcas(boolean[][] marcas) {
		// TODO Auto-generated method stub
		
	}
	
	private static boolean esValida(){
		return game.getFuel() > 0;
	}
	
	private static boolean esValida(Instruction instruccion, int k, int maxDepth, int coste){
		/*if (game.getFuel() <= 0)return false;
		else{
			
			solucion[k] = instruccion;
			if (esSolucion()){
				
			}
			return true;
		}*/
		return game.getFuel() > 0;
	}
	
	private static void trataDatos(Instruction instruccion, int coste, int k, int maxDepth){
		
		//solucion[k] = instruccion;
		solucion.push(instruccion);
		
		if (esSolucion()){
			longitudSolucionMejor = k;
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
			laberinto(k+1, maxDepth, coste);
			//solucion[k].undo();
			solucion.pop().undo();
			//solucion[k].undo();
			//game.undoInstruction();
		}
	}
	
	private static void hazCosas(Instruction instruccion, int coste, int k, int maxDepth){
		
		//game.communicateRobot(instruccion);
		
		game.configureLittleContext(instruccion);
		try {
			instruccion.execute();
			if (esValida()){
				//coste++;
				trataDatos(instruccion, coste, k, maxDepth);
				//game.undoInstruction();
				//instruccion.undo();
				//coste--;
			}
			else{
				//TODO
				//laberinto(k+1, maxDepth, coste);
				//game.undoInstruction();
				instruccion.undo();
				//coste--;
			}
		} catch (InstructionExecutionException e) {
			//TODO si quieres
		}
		
	}
	/**
	 * Funcion del laberinto
	 * TODO hay algo raro, que es que el place del navigation  y del engine no coinciden, no se por qué
	 * habría que ver las instrucciones, no se
	 * he cambiado los arrays de solucion y solucionMejor por pilas, pero no se si sirve para algo
	 * @param k
	 * @param maxDepth
	 * @param coste
	 */
	static void laberinto (int k, int maxDepth, int coste){
		Instruction instruccion;
		for (int i = 0; i< instructions.length && k < maxDepth; i++){
			
			//coste++;
			instruccion = instructions[i];
			if (i==0){
				if (game.canMove()){
					hazCosas(new MoveInstruction(), coste, k, maxDepth);
				}
			}
			else if (i== 1){
				hazCosas(new TurnInstruction("Right"), coste, k, maxDepth);
			}
			else if (i==2){
				hazCosas(new TurnInstruction("Left"), coste, k, maxDepth);
			}
			else if (i==3){
				for (String objects: game.robotItems()){
					hazCosas(new OperateInstruction(objects), coste, k, maxDepth);
				}
			}
			else if (i==4){
				for (String objects: game.placeItems()){
					hazCosas(new PickInstruction(objects), coste, k, maxDepth);
				}
			}
			/**
			if (i==0){//MoveInstruction
				if (game.canMove()){
					game.communicateRobot(instruccion);
					if (esValida()){
						//coste++;
						trataDatos(instruccion, coste, k, maxDepth);
						instruccion.undo();
						//coste--;
					}
					else{
						instruccion.undo();
						//coste--;
					}
				}
			}
			
			else if(i == 3){//OperateInstruction
				
				for (String objects: game.robotItems()){
					instruccion = new OperateInstruction(objects);
					game.communicateRobot(instruccion);
					if (esValida()){
						//coste++;
						trataDatos(instruccion, coste, k, maxDepth);
						instruccion.undo();
						//coste--;
					}
					else{
						instruccion.undo();
						//coste--;
					}
					
				}
			}
			
			else if (i == 4){//PickInstruction	
				for (String objects: game.placeItems()){
					instruccion = new PickInstruction(objects);
					game.communicateRobot(instruccion);
					if (esValida()){
						//coste++;
						trataDatos(instruccion, coste, k, maxDepth);
						instruccion.undo();
						//coste--;
					}
					else{
						instruccion.undo();
						//coste--;
					}
					
				}
			}
			
			else {//Else
				game.communicateRobot(instruccion);
				if (esValida()){
					//coste++;
					trataDatos(instruccion, coste, k, maxDepth);
					instruccion.undo();
					//coste--;
				}
				else{
					instruccion.undo();
					//coste--;
				}
			}*/
		}
		
	}
	/*
	static void laberinto(int k, int maxDepth, int coste){
		Instruction instruccion;
		for (int i= 0; i< instructions.length && k<maxDepth; i++){
			coste++;
			//solucion[k] = instructions[i];
			instruccion =  instructions[i];
			if (i == 3){//operate //TODO TRy 1
				for (String objects: game.robotItems()){
					
					//solucion[k] = new OperateInstruction(objects);
					if (objects != null){
						instruccion = new OperateInstruction(objects);
						//game.communicateRobot(solucion[k]);
						game.communicateRobot(instruccion);
						if (esValida(null, k, maxDepth)){
							//solucion.set(k, instruccion);// = instruccion;
							//solucion.add(instruccion);
							solucion[k] = instruccion;
							if (esSolucion()){
								if (coste < costeMejor || costeMejor == -1) {
									
									costeMejor = coste;
									for (int indice = 0; indice < maxDepth; indice++)
										//solucionMejor.set(indice, solucion.elementAt(indice));
										solucionMejor[indice] = solucion[indice];
									System.out.println("Se ha encontrado una solucion");
									for (int lol=0; lol< solucionMejor.length; lol++)
										System.out.println(solucionMejor[lol].toString());
									//imprimirSolucion(solucionMejor, k);
								}
								for (int lol=0; lol< solucionMejor.length; lol++)
									System.out.println(solucionMejor[lol].toString());
							}
							else {
								laberinto(k+1, maxDepth, coste);
								// desmarcar
								desmarcar();
								game.undoInstruction();
								// marcas[solucion[k].fila][solucion[k].columna] =
								// false;
							}
						}
						else{ //solucion[k].undo();
							instruccion.undo();
							coste--;
						}
					}	
				}
			}
			
			else if (i == 4){//pick
				for (String object: game.placeItems()){
					if (object != null){
						//solucion[k] = new PickInstruction(object);
						instruccion =  new PickInstruction(object);
						//game.communicateRobot(solucion[k]);
						game.communicateRobot(instruccion);
						if (esValida(null, k, maxDepth)) {
							//solucion.add(instruccion);
							solucion[k] = instruccion;
							if (esSolucion()){
								if (coste < costeMejor || costeMejor == -1) {
									
									costeMejor = coste;
									for (int indice = 0; indice < maxDepth; indice++)
										solucionMejor[indice] = solucion[indice];
										//solucionMejor.set(indice, solucion.elementAt(indice));
									System.out.println("Se ha encontrado una solucion");
									for (int lol=0; lol< solucionMejor.length; lol++)
										System.out.println(solucionMejor[lol].toString());
								}
								for (int lol=0; lol< solucionMejor.length; lol++)
									System.out.println(solucionMejor[lol].toString());
							}
							else {
								laberinto(k+1, maxDepth, coste);
								// desmarcar
								desmarcar();
								game.undoInstruction();
								// marcas[solucion[k].fila][solucion[k].columna] =
								// false;
							}
						}
						else{ //solucion[k].undo();
							instruccion.undo();
							coste--;
						}
					}
				}
			}
			
			else {
				//game.communicateRobot(solucion[k]);
				game.communicateRobot(instruccion);
				if (esValida(null, k, maxDepth)){
					solucion[k] = instruccion;
					//solucion.set(k, instruccion);
					//solucion.add(instruccion);
					if (esSolucion()){
						if (coste < costeMejor || costeMejor == -1) {
							
							costeMejor = coste;
							for (int indice = 0; indice <= k; indice++)
								solucionMejor[indice] = solucion[indice];
								//solucionMejor.set(indice, solucion.elementAt(indice));
							System.out.println("Se ha encontrado una solucion");
							for (int lol=0; lol<= k; lol++)
								System.out.println(solucionMejor[lol].toString());
							//imprimirSolucion(solucionMejor, solucion.length);
						}
						for (int lol=0; lol<= k; lol++)
							System.out.println(solucionMejor[lol].toString());
					}
					else {
						laberinto(k+1, maxDepth, coste);
						// desmarcar
						desmarcar();
						game.undoInstruction();
						// marcas[solucion[k].fila][solucion[k].columna] =
						// false;
				}
				}
				else{ //solucion[k].undo();
					instruccion.undo();
					coste--;
				}
				
			}
		}
		
		
	}
	*/
	/*static void laberinto(City city, /*Instruction[] solucion,
			Instruction[] solucionMejor,*/ /*int k, int maxDepth,
			boolean marcas[][], int coste, int costeMejor, RobotEngine engine) {
		if (coste < costeMejor || costeMejor == -1) {
			for (int i = 0; i < instructions.length; i++) {
				//solucion.add(instructions[i]); SUSTITUIDO POR LO DE ABAJO
				if (k<maxDepth){
				//TODO solucion[k] = instructions[i];
				// los siguientes dos ifs podrian cambiarse por un bucle que
				// hiciera parse de la instruccion actual, lo cual aumentaria el
				// coste a cambio de mejorar la legibilidad del programa
				if (i == 3) { // la instruccion es un operate
					String[] itemsRobot = game.robotItems();
					//TODO quitar despues for (int j = 0; j < place.numberOfItems(); j++) {
					//int numero = place.numberOfItems();
					//for (int j = 0; j <numero; j++) {
					for (int j = 0; j < itemsRobot.length; j++) {
						objectToOperate = itemsRobot[j];
						try {
							instructions[i].parse(("operate "+ objectToOperate));
						} catch (WrongInstructionFormatException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						game.communicateRobot(instructions[i]);
					}
				} else if (i == 4) { // la instruccion es un pick
					String[] itemsPlace = game.placeItems();
					for (int j = 0; j < itemsPlace.length; j++) {
						objectToPick = itemsPlace[j];
						try {
							//TODO solucion[k].parse("pick " + objectToPick);
						} catch (WrongInstructionFormatException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						game.communicateRobot(new PickInstruction(objectToPick));
					}
				}
				// solucion[k] = sigInstruccion(i);
				// game.communicateRobot(solucion[k]);

				//try { // if (esValida(city, solucion[k], marcas)) si no fuera
						// valida pasa al catch
					//solucion[k].configureContext(game, navigation, items);
				/*	solucion[k].execute();*/ /** LO SUSTITUIMOS POR LO DE ABAJO*/
					/**
					 * Jaime, tu que controlas de esa parte, el comunicate robot
					 * tiene un metodo requestRobot que avisa a los observadores
					 * de que ha petado la instruccion
					 */
					/** Deberiamos hacer un observador para el FindExit? */
				//TTODO else game.communicateRobot(solucion[k]);
					/*if (esValida(solucion, k, maxDepth)) {
						if (esSolucion(solucion[k], game)) {
							
							if (coste < costeMejor || costeMejor == -1) {
								costeMejor = coste;
								//copiarSolucion(solucion, solucionMejor);
								for (int indice = 0; indice < maxDepth; indice++)
									solucionMejor[indice] = solucion[indice];
								System.out.println("Se ha encontrado una solucion");
								
								// imprimirMejorSolucion(solucionMejor,
								// nodosVisitados, coste);
								// cout << endl;
							}
							// tratarSolucion(solucion, k);
						} else {
							// marcar
							marcar();
							// marcas[solucion[k].fila][solucion[k].columna] =
							// true;
							laberinto(city, /*solucion, solucionMejor,*/ /*k+1,
									maxDepth, marcas, coste, costeMejor, engine);
							// desmarcar
							desmarcar();
							game.undoInstruction();
							// marcas[solucion[k].fila][solucion[k].columna] =
							// false;
						}
					}else game.undoInstruction();
					}
				//game.undoInstruction();
					//solucion[i].undo();

				//} catch (InstructionExecutionException e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
				//}
			}
		}
	}*/

	private static boolean esValida(Instruction[] solucion, int k, int maxDepth) {
		return ! (k > maxDepth || game.getFuel() <= 0) ;//return false;
		//else return true;
	}

	private static boolean esSolucion(Instruction instruction, RobotEngine game) {
		return game.atSpaceship();
	}

	private static boolean esSolucion(){
		return game.atSpaceship();
	}
	private static void desmarcar() {
		// TODO Auto-generated method stub
		
		
	}
	private static void marcar() {
		// TODO Auto-generated method stub
		
	}

	
	private static Instruction sigInstruccion(int i) {
		i++;

		return instructions[i];

	}
	private static Place place;
	private static String objectToOperate;
	private static String objectToPick;
	private static RobotEngine game;
	private static Instruction[] instructions = { new MoveInstruction(),
			new TurnInstruction("Right"), new TurnInstruction("Left"), 
			new OperateInstruction(objectToOperate), new PickInstruction(objectToPick)
	};
	//private static Instruction[] solucion;
	//private static Instruction[]solucionMejor;
	private static Vector<Instruction> solucionMejor;
	private static Stack<Instruction> solucion;
	private static int costeMejor = 1000000000;
	private static int longitudSolucionMejor = 100000000;

}


