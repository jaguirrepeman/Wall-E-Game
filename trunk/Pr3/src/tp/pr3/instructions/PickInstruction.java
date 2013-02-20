package tp.pr3.instructions;

import tp.pr3.Interpreter;
import tp.pr3.NavigationModule;
import tp.pr3.RobotEngine;
import tp.pr3.instructions.exceptions.InstructionExecutionException;
import tp.pr3.instructions.exceptions.WrongInstructionFormatException;
import tp.pr3.items.Item;
import tp.pr3.items.ItemContainer;

public class PickInstruction {
	public void configureContext(RobotEngine engine, NavigationModule navigation, ItemContainer robotContainer){
		
	}
	public void execute()throws InstructionExecutionException{
		Instruction instruccion = Interpreter.generateInstruction(command);
		Item item = this.navigation.getCurrentPlace().pickItem(instruccion.getId());
	}
	public String getHelp(){
		return null;
		
	}
	public Instruction parse(java.lang.String cad)throws WrongInstructionFormatException{
		return null;
		
	}
	private RobotEngine engine;
	private NavigationModule navigation;
	private ItemContainer robotContainer;
}
