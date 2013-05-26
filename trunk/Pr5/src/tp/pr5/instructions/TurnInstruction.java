package tp.pr5.instructions;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.Rotation;
import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;
import tp.pr5.items.ItemContainer;

public class TurnInstruction extends UndoableInstruction{
	
	public TurnInstruction(String rotacion){
		try {
			this.parse("Turn" + " " + rotacion);
		} catch (WrongInstructionFormatException e) {
			e.printStackTrace();
		}
	}
	
	public TurnInstruction() {
	
	}

	public void configureContext(RobotEngine engine,
			NavigationModule navigation, ItemContainer robotContainer) {
		this.engine = engine;
		this.navigation = navigation;
	}

	public String getHelp() {

		return "TURN | GIRAR < LEFT|RIGHT >";

	}
	
	public String toString(){
		return "TURN " + this.rotation.toString().toLowerCase();
	}
	
	public void execute() throws InstructionExecutionException {
			this.navigation.rotate(rotation);
			this.engine.addFuel(-5);
			//System.out.println("WALL·E is looking at direction "
			//		+ this.navigation.getCurrentHeading().toString());
			//this.engine.printRobotState();
			
	}
	@Override
	public void undo() {
		this.navigation.rotate(rotation.Opposite());
		this.engine.addFuel(5);
	}
	public Instruction parse(String cad) throws WrongInstructionFormatException {

		String[] comando = cad.split(" ");
		if ((comando.length == 2)
				&& ((comando[0].equalsIgnoreCase("TURN")) || (comando[0].equalsIgnoreCase("GIRAR"))
						&& ((comando[1].equalsIgnoreCase("LEFT")) || (comando[1].equalsIgnoreCase("RIGHT"))))){
			
			if (comando[1].equalsIgnoreCase("RIGHT")) this.rotation = Rotation.RIGHT;
			else if (comando[1].equalsIgnoreCase("LEFT")) this.rotation = Rotation.LEFT;
			else throw new WrongInstructionFormatException();
			return this;
		}
			
		else throw new WrongInstructionFormatException();	
	}

	private RobotEngine engine;
	private NavigationModule navigation;
	private Rotation rotation;
	
}
