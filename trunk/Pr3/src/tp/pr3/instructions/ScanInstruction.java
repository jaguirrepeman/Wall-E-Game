package tp.pr3.instructions;

import tp.pr3.NavigationModule;
import tp.pr3.RobotEngine;
import tp.pr3.instructions.exceptions.InstructionExecutionException;
import tp.pr3.instructions.exceptions.WrongInstructionFormatException;
import tp.pr3.items.Item;
import tp.pr3.items.ItemContainer;

public class ScanInstruction implements Instruction {
	public void configureContext(RobotEngine engine,
			NavigationModule navigation, ItemContainer robotContainer) {
		this.engine = engine;
		this.robotContainer = robotContainer;
	}

	public void execute() throws InstructionExecutionException {
		if (this.robotContainer.numberOfItems() == 0)
			engine.say("My inventory is empty");
		else if (id == null)
			engine.say("I am carrying the following items" + LINE_SEPARATOR
					+ this.robotContainer.toString());
		else {
			Item item3 = this.robotContainer.getItem(id);
			if (item3 != null)
				engine.say(item3.toString());
			else
				engine.say("I don't have that item");
		}
	}

	public String getHelp() {
		return "SCAN|ESCANEAR <id>";

	}

	public Instruction parse(String cad) throws WrongInstructionFormatException {
		String[] comando = cad.split(" ");
		if (((comando[0].equalsIgnoreCase("SCAN")) || (comando[0]
				.equalsIgnoreCase("ESCANEAR"))) && ((comando.length == 1)|| (comando.length == 2))) {
			comando[1] = id;
			return this;
		} else
			throw new WrongInstructionFormatException();
	}

	private RobotEngine engine;
	private ItemContainer robotContainer;
	private String id;
}
