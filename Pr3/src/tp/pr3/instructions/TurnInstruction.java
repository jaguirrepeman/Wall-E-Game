package tp.pr3.instructions;

import tp.pr3.Action;
import tp.pr3.NavigationModule;
import tp.pr3.RobotEngine;
import tp.pr3.Rotation;
import tp.pr3.instructions.exceptions.InstructionExecutionException;
import tp.pr3.instructions.exceptions.WrongInstructionFormatException;
import tp.pr3.items.ItemContainer;

public class TurnInstruction implements Instruction{
	public void configureContext(RobotEngine engine,
			NavigationModule navigation, ItemContainer robotContainer) {
		this.engine = engine;
		this.navigation = navigation;
		this.robotContainer = robotContainer;
	}

	public String getHelp() {

		return "TURN | GRITAR < LEFT|RIGHT >";

	}
	
	public void execute() throws InstructionExecutionException {
		
	}

	public Instruction parse(String cad) throws WrongInstructionFormatException {

		String[] comando = cad.split(" ");
		if ((comando.length == 2)
				&& ((comando[0].equalsIgnoreCase("TURN")) || (comando[0].equalsIgnoreCase("GIRAR"))
						&& ((comando[1].equalsIgnoreCase("LEFT")) || (comando[1].equalsIgnoreCase("RIGHT")))))
			return this;
		else throw new WrongInstructionFormatException();	
	}

	private RobotEngine engine;
	private NavigationModule navigation;
	private ItemContainer robotContainer;
}
