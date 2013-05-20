package tp.pr5;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Vector;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.MissingOptionException;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import tp.pr5.cityLoader.CityLoaderFromTxtFile;
import tp.pr5.cityLoader.cityLoaderExceptions.WrongCityFormatException;
import tp.pr5.instructions.Instruction;
import tp.pr5.instructions.MoveInstruction;
import tp.pr5.instructions.OperateInstruction;
import tp.pr5.instructions.PickInstruction;
import tp.pr5.instructions.TurnInstruction;
import tp.pr5.instructions.exceptions.InstructionExecutionException;


public class FindExit {
	Instruction ins;
	//(-d|-max-depth) n (-m|-map) <mapFilename>
	void solve(){
		int coste = 0, costeMejor = -1, maxDepth = 100;
		City city = null;
		Vector<Instruction> solucion = new Vector<Instruction>();
		Vector<Instruction> solucionMejor = new Vector<Instruction>();
		boolean marcas[][] = null;
		//RobotEngine engine = null;
		laberinto(city, solucion, solucionMejor, 0, maxDepth, marcas, coste, costeMejor, game);
			
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
				//Instruction[] solucion = null, solucionMejor = null;
				Vector<Instruction> solucion = new Vector<Instruction>();
				Vector<Instruction> solucionMejor = new Vector<Instruction>();
				boolean marcas[][] = null;
				String d = cmd.getOptionValue('d');
				int maxDepth = Integer.parseInt(d);
				cmd.getOptionValue('m');
				int coste = 0, costeMejor = -1;
				inicializarMarcas(marcas);
				laberinto(city, solucion, solucionMejor, 0, maxDepth, marcas, coste, costeMejor, game);
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

	private static void inicializarMarcas(boolean[][] marcas) {
		// TODO Auto-generated method stub
		
	}

	static void laberinto(City city, Vector<Instruction> solucion, Vector<Instruction> solucionMejor, int k, int maxDepth, boolean marcas[][], int coste, int costeMejor, RobotEngine engine) {
		if (coste < costeMejor || costeMejor == -1) {
			for (int i = 0; i < instructions.length; i++) {
				solucion.add(sigInstruccion(i));
				if (i == 3 || i == 4){
					for (int j = 0; j < place.numberOfItems(); j++){
						objectToPick = place.object[j];
						game.communicateRobot(instructions[i]);
					}
				}
			//	solucion[k] = sigInstruccion(i);
			//	game.communicateRobot(solucion[k]);
				game.communicateRobot(solucion.elementAt(k));
				
				try { 	//if (esValida(city, solucion[k], marcas)) si no fuera valida pasa al catch
					solucion.elementAt(k).execute();
				{
						if (esSolucion(solucion.elementAt(k), game)) {
							if(coste < costeMejor || costeMejor == -1){
								costeMejor = coste;
								copiarSolucion(solucion, solucionMejor);
								//imprimirMejorSolucion(solucionMejor, nodosVisitados, coste);
								//cout << endl;
							}
							//tratarSolucion(solucion, k);
						} else {
							// marcar
							marcar();
							//marcas[solucion[k].fila][solucion[k].columna] = true;
							laberinto(city, solucion, solucionMejor, maxDepth, k + 1, marcas, coste,
									costeMejor, engine);
							// desmarcar
							desmarcar();
							// marcas[solucion[k].fila][solucion[k].columna] =
							// false;
						}
					}
					
				} catch (InstructionExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}


			}
		}
	}
		
	private static boolean esSolucion(Instruction instruction, RobotEngine game) {
		// TODO Auto-generated method stub
		return game.atSpaceship();
	}

	private static void copiarSolucion(Vector<Instruction> solucion,
			Vector<Instruction> solucionMejor) {
		solucionMejor = solucion;
		// TODO esto a lo mejor solo copia los punteros, estaria bien saberlo
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

}


