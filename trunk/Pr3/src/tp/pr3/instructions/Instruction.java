package tp.pr3.instructions;

import tp.pr3.NavigationModule;
import tp.pr3.RobotEngine;
import tp.pr3.instructions.exceptions.InstructionExecutionException;
import tp.pr3.instructions.exceptions.WrongInstructionFormatException;
import tp.pr3.items.ItemContainer;

public interface Instruction {
	Instruction parse(java.lang.String cad) throws WrongInstructionFormatException;

    String getHelp();

	void configureContext(RobotEngine engine, NavigationModule navigation, ItemContainer robotContainer);

	void execute() throws InstructionExecutionException;

}
