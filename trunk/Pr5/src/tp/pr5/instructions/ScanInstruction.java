package tp.pr5.instructions;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;
import tp.pr5.items.Item;
import tp.pr5.items.ItemContainer;

public class ScanInstruction extends NotUndoableInstruction {
	public void configureContext(RobotEngine engine,
			NavigationModule navigation, ItemContainer robotContainer) {
		this.engine = engine;
		this.robotContainer = robotContainer;
	}

	public void execute() throws InstructionExecutionException {
		if (this.robotContainer.numberOfItems() == 0){
			throw new InstructionExecutionException("My inventory is empty");
		}
		else if (id == null)
			//TODO
			engine.say("I am carrying the following items" + LINE_SEPARATOR
					+ this.robotContainer.toString());
		else {
			Item item3 = this.robotContainer.getItem(id);
			//TODO
			if (item3 != null)
				engine.say(item3.toString());
			else{
				throw new InstructionExecutionException("I don't have the item "+ id);
			}
		}
	}

	public String getHelp() {
		return "SCAN|ESCANEAR <id>";

	}

	public Instruction parse(String cad) throws WrongInstructionFormatException {
		String[] comando = cad.split(" ");
		if (((comando[0].equalsIgnoreCase("SCAN")) || (comando[0].equalsIgnoreCase("ESCANEAR"))) && 
			((comando.length == 1)|| (comando.length == 2))) {
			if (comando.length == 2) id = comando[1];
			else id = null;
			return this;
		} else
			throw new WrongInstructionFormatException();
	}

	private RobotEngine engine;
	private ItemContainer robotContainer;
	private String id;
}
