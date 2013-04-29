package tp.pr5.instructions;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;
import tp.pr5.items.ItemContainer;

public class UndoInstruction implements Instruction {

	@Override
	public Instruction parse(String cad) throws WrongInstructionFormatException {
		String[] comando = cad.split(" ");
		if((comando[0].equalsIgnoreCase("UNDO"))  && comando.length == 1)
			return this;
		else throw new WrongInstructionFormatException();
	}

	@Override
	public String getHelp() {
		return "UNDO";
	}

	@Override
	public void configureContext(RobotEngine engine,
			NavigationModule navigation, ItemContainer robotContainer) {
		this.engine = engine;
	}

	@Override
	public void execute() throws InstructionExecutionException {
		engine.undoInstruction();
	}

	@Override
	public boolean isUndoableInstruction() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void undo() {}
	
	private RobotEngine engine;

}
