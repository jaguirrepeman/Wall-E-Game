package tp.pr3.instructions;

import tp.pr3.NavigationModule;
import tp.pr3.RobotEngine;
import tp.pr3.instructions.exceptions.InstructionExecutionException;
import tp.pr3.instructions.exceptions.WrongInstructionFormatException;
import tp.pr3.items.ItemContainer;

public class OperateInstruction implements Instruction{
	
	public void configureContext(RobotEngine engine, NavigationModule navigation, ItemContainer robotContainer){
		this.engine = engine;
		this.navigation = navigation;
		this.items = robotContainer;
	}
	
	public void execute() throws InstructionExecutionException{
		
	}
	
	public java.lang.String getHelp(){
		
		return "OPERATE|OPERAR <ID>";
	}
	
	public Instruction parse (java.lang.String cad) throws WrongInstructionFormatException{
		return null;
		
	}
	
	private RobotEngine engine;
	private NavigationModule navigation;
	private ItemContainer items;
}
