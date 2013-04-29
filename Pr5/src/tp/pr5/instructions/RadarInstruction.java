package tp.pr5.instructions;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;
import tp.pr5.items.ItemContainer;

public class RadarInstruction extends NotUndoableInstruction{
	public void configureContext(RobotEngine engine, NavigationModule navigation, ItemContainer robotContainer){
		this.navigation = navigation;
	}
	public void execute() throws InstructionExecutionException{
		navigation.scanCurrentPlace();
	}
	public String getHelp(){
		return "RADAR";
		
	}
		
	public Instruction parse(java.lang.String cad) throws WrongInstructionFormatException{
		String[] comando = cad.split(" ");
		if ((comando.length == 1) && (comando[0].equalsIgnoreCase("RADAR")))
			return this;
		else throw new WrongInstructionFormatException(); 
	}
	private NavigationModule navigation;
}
