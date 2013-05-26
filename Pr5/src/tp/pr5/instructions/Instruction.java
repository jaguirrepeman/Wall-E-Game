package tp.pr5.instructions;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;
import tp.pr5.items.ItemContainer;

public interface Instruction {
	Instruction parse(java.lang.String cad) throws WrongInstructionFormatException;

    String getHelp();

	void configureContext(RobotEngine engine, NavigationModule navigation, ItemContainer robotContainer);

	void execute() throws InstructionExecutionException;
	
	public boolean isUndoableInstruction();
	
	public void undo();
	
	public String toString();
	
	static final String LINE_SEPARATOR = System.getProperty("line.separator");

	
}
