package tp.pr3.instructions;

import tp.pr3.NavigationModule;
import tp.pr3.RobotEngine;
import tp.pr3.instructions.exceptions.InstructionExecutionException;
import tp.pr3.instructions.exceptions.WrongInstructionFormatException;
import tp.pr3.items.ItemContainer;

public class HelpInstruction implements Instruction{
	public void configureContext(RobotEngine engine, NavigationModule navigation, ItemContainer robotContainer){
		this.engine = engine;
		//this.navigation = navigation;
		//this.items = robotContainer;
	}
	public void execute() throws InstructionExecutionException{
		engine.requestHelp();
	}
	public String getHelp(){
		return "HELP|AYUDA";
		
	}
	public Instruction parse(String cad) throws WrongInstructionFormatException{
		String[] comando = cad.split(" ");
		if((comando[0].equalsIgnoreCase("HELP") ||(comando[0].equalsIgnoreCase("AYUDA")))  && comando.length == 1)
			return this;
		else throw new WrongInstructionFormatException();
		
	}
	private RobotEngine engine;
	//private NavigationModule navigation;
	//private ItemContainer items;
}
