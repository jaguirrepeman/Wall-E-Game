package tp.pr5.console;

import java.util.Scanner;

import tp.pr5.Interpreter;
import tp.pr5.RobotEngine;
import tp.pr5.instructions.Instruction;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;

public class ConsoleController extends tp.pr5.Controller{
	public ConsoleController(RobotEngine game){
		super(game);
	}
	public void startEngine() {
		Instruction instruccion = null;
		String command = new String();
		/*for (RobotEngineObserver o : robObservers){ 
			game.o.robotSays(this.place.getDescription());
			o.robotSays("WALL·E is looking at direction "
				+ this.direction.toString());
			o.robotUpdate(fuel, recycledMaterial);
		}*/
		robot.requestStart();
		/**
		 Cosas antiguas
		 
		System.out.println(this.place.toString());
		System.out.println("WALL·E is looking at direction "
				+ this.direction.toString());
		printRobotState();
*/
		Scanner comando = new Scanner(System.in);

		while (!robot.isOver()) {

			if (!robot.isOver()) {

				prompt();
				command = comando.nextLine();
				try {
					instruccion = Interpreter.generateInstruction(command);
					robot.communicateRobot(instruccion);
				} catch (WrongInstructionFormatException exc) {
					robot.requestError(exc.getMessage());
				}
			}
		}
		comando.close();
		//robot.requestQuit();
		/**
		  Más cosas antiguas
		
		if (this.place.isSpaceship())
			say("I am at my spaceship. Bye bye");
		else if (this.fuel == 0)
			say("I run out of fuel. I cannot move. Shutting down...");
		else
			say("I have communication problems. Bye bye");
			
		 */
	}
//estos dos deben ser eliminados
	private void say(String message) {
		// TODO Auto-generated method stub
		
	}
	private void prompt() {
		System.out.print("WALL·E> ");
	}

	//private RobotEngine game;

}
