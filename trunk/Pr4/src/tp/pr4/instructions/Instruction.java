package tp.pr4.instructions;

import tp.pr4.NavigationModule;
import tp.pr4.RobotEngine;
import tp.pr4.instructions.exceptions.InstructionExecutionException;
import tp.pr4.instructions.exceptions.WrongInstructionFormatException;
import tp.pr4.items.ItemContainer;

public interface Instruction {
	Instruction parse(java.lang.String cad) throws WrongInstructionFormatException;

    String getHelp();

	void configureContext(RobotEngine engine, NavigationModule navigation, ItemContainer robotContainer);

	void execute() throws InstructionExecutionException;
	
	public boolean isUndoableInstruction();
	
	public void undo();
	
	static final String LINE_SEPARATOR = System.getProperty("line.separator");

	
}
