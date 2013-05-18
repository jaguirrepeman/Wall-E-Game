package tp.pr5;

import tp.pr5.instructions.Instruction;
import tp.pr5.instructions.MoveInstruction;
import tp.pr5.instructions.OperateInstruction;
import tp.pr5.instructions.PickInstruction;
import tp.pr5.instructions.TurnInstruction;
import tp.pr5.instructions.exceptions.InstructionExecutionException;


public class FindExit {
	Instruction ins;

	void solve(){
		int coste = 0, costeMejor = -1;
		City city = null;
		Instruction[] solucion = null, solucionMejor = null;
		boolean marcas[][] = null;
		RobotEngine engine = null;
		laberinto( city, solucion, solucionMejor, 0, marcas, coste, costeMejor, engine);
			
	}

	void laberinto(City city, Instruction solucion[], Instruction solucionMejor[], int k, boolean marcas[][], int coste, int costeMejor, RobotEngine engine) {
		if (coste < costeMejor || costeMejor == -1) {
			for (int i = 0; i < instructions.length; i++) {
				solucion[k] = sigInstruccion(i);
				game.communicateRobot(solucion[k]);
				try { 	//if (esValida(city, solucion[k], marcas)) si no fuera valida pasa al catch
					solucion[k].execute();
				{
						if (esSolucion(solucion[k], game)) {
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
							laberinto(city, solucion, solucionMejor, k + 1, marcas, coste,
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
		
	private boolean esSolucion(Instruction instruction, RobotEngine game) {
		// TODO Auto-generated method stub
		return game.atSpaceship();
	}

	private void copiarSolucion(Instruction[] solucion,
			Instruction[] solucionMejor) {
		solucionMejor = solucion;
		// TODO esto a lo mejor solo copia los punteros, estaria bien saberlo
	}

	private void desmarcar() {
		// TODO Auto-generated method stub
		
		
	}
	private void marcar() {
		// TODO Auto-generated method stub
		
	}

	private boolean esSolucion(Instruction instruction[], City city, RobotEngine game) {
		// TODO Auto-generated method stub
		//isSpaceship y esas cosas
		return false;
	}

	private boolean esValida(City city, Instruction instruction, boolean[][] marcas, RobotEngine game) {
		// TODO Auto-generated method stub
		return false;
	}

	private Instruction sigInstruccion(int i) {
		i++;

		return instructions[i];

	}
	private Place place;
	private String objectToOperate;
	private String objectToPick;
	private RobotEngine game;
	private  Instruction[] instructions = { new MoveInstruction(),
			new OperateInstruction(objectToOperate), new PickInstruction(objectToPick), 
			new TurnInstruction("Right"), new TurnInstruction("Left")
	};

}
