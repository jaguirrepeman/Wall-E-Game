package tp.pr3.instructions;

import tp.pr3.NavigationModule;
import tp.pr3.RobotEngine;
import tp.pr3.instructions.exceptions.InstructionExecutionException;
import tp.pr3.instructions.exceptions.WrongInstructionFormatException;
import tp.pr3.items.ItemContainer;

public class DropInstruction implements Instruction {

	public void configureContext(RobotEngine engine, NavigationModule navigation, ItemContainer robotContainer){
		this.engine = engine;
		this.navigation = navigation;
		this.robotContainer = robotContainer;
	}
	public void execute() throws InstructionExecutionException{
		
	}
	public String getHelp(){
		return "DROP <id>";
		
	}

	public Instruction parse(String cad) throws WrongInstructionFormatException{
		String[] comando = cad.split(" ");
		if((comando[0].equalsIgnoreCase("PICK")) && (comando.length > 1))
			return this;
		else throw new WrongInstructionFormatException();	
		
	}
	private RobotEngine engine;
	private NavigationModule navigation;
	private ItemContainer robotContainer;
}
