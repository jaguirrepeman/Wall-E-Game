package tp.pr5.instructions;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;
import tp.pr5.items.ItemContainer;

public class QuitInstruction extends NotUndoableInstruction{
	public QuitInstruction() {
	}
	
	public void configureContext(RobotEngine engine, NavigationModule navigation, ItemContainer robotContainer){
		this.engine = engine;
	}
	public void execute() throws InstructionExecutionException{
		engine.requestQuit();
	}
	public String getHelp(){
		return "QUIT|SALIR";
		
	}
	public Instruction parse(String cad) throws WrongInstructionFormatException{
		String[] comando = cad.split(" ");
		if ((comando.length == 1) && (comando[0].equalsIgnoreCase("QUIT") || comando[0].equalsIgnoreCase("SALIR")) )
			return this;
		else throw new WrongInstructionFormatException(); 
	}
	private RobotEngine engine;
}
