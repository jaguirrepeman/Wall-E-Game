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
		// si existe una calle en la dirección actual del
		// robot...System.out.println("WALL·E > " + mensaje);
		/*if (!(this.navigation.getCurrentHeading()== null)) {
			//
			if (this.navigation.getHeadingStreet().isOpen()) {
				this.navigation.move();
				this.navigation.say("Moving in direction " + this.navigation.getCurrentHeading().toString()
						+ LINE_SEPARATOR + this.navigation.getCurrentPlace().toString());
				this.engine.addFuel(-5);//fuel -= 5;
				this.engine.printStatus();
				System.out.println("WALL·E is looking at direction "
						+ this.navigation.getCurrentHeading().toString());
			} else{
				this.engine.say("Arrggg, there is a street but it is closed!");
				throw new InstructionExecutionException("Arrggg, there is a street but it is closed!");
			}
				
		}
		// en caso contrario
		else{
			this.engine.say("There is no street in direction "
					+ this.navigation.getCurrentHeading().toString());
			throw new InstructionExecutionException("There is no street in direction "
					+ this.navigation.getCurrentHeading().toString());
		}*/
		try {
			this.navigation.move();
		}
		catch (InstructionExecutionException e){
			if (this.navigation.getHeadingStreet() == null){
				//navigation.say("There is no street in direction " + this.navigation.getCurrentHeading().toString());
				throw new InstructionExecutionException("There is no street in direction " + this.navigation.getCurrentHeading().toString());
			}
			else if (!this.navigation.getHeadingStreet().isOpen()){
				//navigation.say("Arrggg, there is a street but it is closed!");
				throw new InstructionExecutionException("Arrggg, there is a street but it is closed!");
			}
		}
		this.navigation.say("Moving in direction " + this.navigation.getCurrentHeading().toString()
				+ LINE_SEPARATOR + this.navigation.getCurrentPlace().toString());
		this.engine.addFuel(-5);//fuel -= 5;
		this.engine.printRobotState();
		System.out.println("WALL·E is looking at direction "
				+ this.navigation.getCurrentHeading().toString());
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
