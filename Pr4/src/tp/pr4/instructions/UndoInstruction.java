package tp.pr4.instructions;

import tp.pr4.NavigationModule;
import tp.pr4.RobotEngine;
import tp.pr4.instructions.exceptions.InstructionExecutionException;
import tp.pr4.instructions.exceptions.WrongInstructionFormatException;
import tp.pr4.items.ItemContainer;

public class UndoInstruction implements Instruction {

	@Override
	public Instruction parse(String cad) throws WrongInstructionFormatException {
		return null;
	}

	@Override
	public String getHelp() {
		return null;
	}

	@Override
	public void configureContext(RobotEngine engine,
			NavigationModule navigation, ItemContainer robotContainer) {

	}

	@Override
	public void execute() throws InstructionExecutionException {
		
	}

}
