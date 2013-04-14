package tp.pr3.instructions;

import tp.pr3.NavigationModule;
import tp.pr3.RobotEngine;
import tp.pr3.instructions.exceptions.InstructionExecutionException;
import tp.pr3.instructions.exceptions.WrongInstructionFormatException;
import tp.pr3.items.ItemContainer;

public class MoveInstruction implements Instruction{
	public void configureContext(RobotEngine engine, NavigationModule navigation, ItemContainer robotContainer){
		this.engine = engine;
		this.navigation = navigation;
	}
	public void execute() throws InstructionExecutionException{
		
		try {
			this.navigation.move();
		}
		catch (InstructionExecutionException e){
			if (this.navigation.getHeadingStreet() == null){
				throw new InstructionExecutionException("There is no street in direction " + this.navigation.getCurrentHeading().toString());
			}
			else if (!this.navigation.getHeadingStreet().isOpen()){
				throw new InstructionExecutionException("Arrggg, there is a street but it is closed!");
			}
		}
		this.engine.moveToPlace(this.navigation.getCurrentPlace());
		this.navigation.say("Moving in direction " + this.navigation.getCurrentHeading().toString()
				+ LINE_SEPARATOR + this.navigation.getCurrentPlace().toString());
		this.engine.addFuel(-5);
		this.engine.printRobotState();
		
	}
		
	
	public String getHelp(){
		return "MOVE|MOVER";
		
	}
	public Instruction parse(String cad) throws WrongInstructionFormatException{
		String[] comando = cad.split(" ");
		if((comando[0].equalsIgnoreCase("MOVE") ||(cad.equalsIgnoreCase("MOVER")))  && comando.length == 1) {
			return this;
		}
		else throw new WrongInstructionFormatException();
				
	}
	
	private RobotEngine engine;
	private NavigationModule navigation;
}
