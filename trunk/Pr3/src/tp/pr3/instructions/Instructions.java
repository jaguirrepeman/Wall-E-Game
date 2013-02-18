package tp.pr3.instructions;

import tp.pr3.Instruction;
import tp.pr3.NavigationModule;
import tp.pr3.RobotEngine;
import tp.pr3.instructions.exceptions.InstructionExecutionException;
import tp.pr3.instructions.exceptions.WrongInstructionFormatException;
import tp.pr3.items.ItemContainer;


public interface Instructions {

	public void configureContext (RobotEngine engine, NavigationModule navigation, ItemContainer robotContainer);
	
	public 	void execute()  throws InstructionExecutionException;
	
	public String getHelp();
	
	public Instruction	parse(String cad)  throws WrongInstructionFormatException;

}

