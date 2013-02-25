package tp.pr3.instructions;

import tp.pr3.Interpreter;
import tp.pr3.NavigationModule;
import tp.pr3.RobotEngine;
import tp.pr3.instructions.exceptions.InstructionExecutionException;
import tp.pr3.instructions.exceptions.WrongInstructionFormatException;
import tp.pr3.items.ItemContainer;

public class HelpInstruction implements Instruction{
	public void configureContext(RobotEngine engine, NavigationModule navigation, ItemContainer robotContainer){
		
	}
	public void execute() throws InstructionExecutionException{
		
		System.out.println(Interpreter.interpreterHelp()); 

	}
	public String getHelp(){
		return "HELP";
		
	}
	public Instruction parse(String cad) throws WrongInstructionFormatException{
		
		if (!((cad.compareToIgnoreCase("HELP") == 1) || (cad.compareToIgnoreCase("AYUDA") == 1))) throw new WrongInstructionFormatException();
		else return new HelpInstruction();
		
	}
}