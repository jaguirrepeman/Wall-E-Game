package tp.pr4.instructions;

import tp.pr4.NavigationModule;
import tp.pr4.RobotEngine;
import tp.pr4.instructions.exceptions.InstructionExecutionException;
import tp.pr4.instructions.exceptions.WrongInstructionFormatException;
import tp.pr4.items.ItemContainer;

public class QuitInstruction implements Instruction{
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
