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
		
		robot.requestStart();
		
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
		
	}

	private void prompt() {
		System.out.print("WALL·E> ");
	}


}
