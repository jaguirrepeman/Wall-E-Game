package tp.pr3.instructions;

import tp.pr3.NavigationModule;
import tp.pr3.RobotEngine;
import tp.pr3.instructions.exceptions.InstructionExecutionException;
import tp.pr3.instructions.exceptions.WrongInstructionFormatException;
import tp.pr3.items.ItemContainer;

public class RadarInstruction implements Instruction{
	public void configureContext(RobotEngine engine, NavigationModule navigation, ItemContainer robotContainer){
		this.engine = engine;
		this.navigation = navigation;
		this.robotContainer = robotContainer;
	}
	public void execute() throws InstructionExecutionException{
		System.out.println(this.navigation.getCurrentPlace().toString());
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
	private RobotEngine engine;
	private NavigationModule navigation;
	private ItemContainer robotContainer;
}
