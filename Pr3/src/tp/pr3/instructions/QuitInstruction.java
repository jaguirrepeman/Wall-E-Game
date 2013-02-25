package tp.pr3.instructions;

import tp.pr3.NavigationModule;
import tp.pr3.RobotEngine;
import tp.pr3.instructions.exceptions.InstructionExecutionException;
import tp.pr3.instructions.exceptions.WrongInstructionFormatException;
import tp.pr3.items.ItemContainer;

public class QuitInstruction implements Instruction{
	public QuitInstruction() {
	}
	
	public void configureContext(RobotEngine engine, NavigationModule navigation, ItemContainer robotContainer){
		this.engine = engine;
		this.navigation = navigation;
	}
	public void execute() throws InstructionExecutionException{
		if (this.navigation.getCurrentPlace().isSpaceship())
			engine.say("I am at my space ship. Bye Bye");
		else if (this.engine.getFuel() == 0)
			engine.say("I run out of fuel. I cannot move. Shutting down...");
		else
			engine.say("I have communication problems. Bye bye");
	}
	public String getHelp(){
		return "QUIT|SALIR";
		
	}
	public Instruction parse(String cad) throws WrongInstructionFormatException{
		String[] comando = cad.split(" ");
		if ((comando.length == 1) && (comando[0].equalsIgnoreCase("QUIT") || comando[0].equalsIgnoreCase("SALIR")) )
			return this;
		else throw new WrongInstructionFormatException(); 
	}
	private RobotEngine engine;
	private NavigationModule navigation;
}
