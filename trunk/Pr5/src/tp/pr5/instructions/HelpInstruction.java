package tp.pr5.instructions;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;
import tp.pr5.items.ItemContainer;

public class HelpInstruction extends NotUndoableInstruction{
	
	public void configureContext(RobotEngine engine, NavigationModule navigation, ItemContainer robotContainer){
		this.engine = engine;
	}
	
	public void execute() throws InstructionExecutionException{
		engine.requestHelp();
	}
	
	public String getHelp(){
		return "HELP|AYUDA";
	}
	
	public Instruction parse(String cad) throws WrongInstructionFormatException{
		String[] comando = cad.split(" ");
		if((comando[0].equalsIgnoreCase("HELP") ||(comando[0].equalsIgnoreCase("AYUDA")))  && comando.length == 1)
			return this;
		else throw new WrongInstructionFormatException();
		
	}
	
	private RobotEngine engine;
	
}
