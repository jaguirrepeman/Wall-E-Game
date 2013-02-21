package tp.pr3.instructions;

import tp.pr3.Action;
import tp.pr3.NavigationModule;
import tp.pr3.RobotEngine;
import tp.pr3.instructions.exceptions.InstructionExecutionException;
import tp.pr3.instructions.exceptions.WrongInstructionFormatException;
import tp.pr3.items.ItemContainer;

public class MoveInstruction implements Instruction{
	public void configureContext(RobotEngine engine, NavigationModule navigation, ItemContainer robotContainer){
		this.engine = engine;
		this.navigation = navigation;
		this.robotContainer = robotContainer;
	}
	public void execute() throws InstructionExecutionException{
		// si existe una calle en la dirección actual del
		// robot...System.out.println("WALL·E > " + mensaje);
		if (!(this.cityMap.lookForStreet(this.place, this.direction) == null)) {
			//
			if (this.cityMap.lookForStreet(this.place, this.direction)
					.isOpen()) {
				this.navigation.getCurrentPlace() = this.cityMap.lookForStreet(this.place,
						direction).nextPlace(place);
				say("Moving in direction " + this.direction.toString()
						+ LINE_SEPARATOR + this.place.toString());
				this.fuel -= 5;
				printStatus();
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
					+ this.direction.toString());
		}
	}
		
	
	public String getHelp(){
		return "MOVE|MOVER";
		
	}
	public Instruction parse(java.lang.String cad)throws WrongInstructionFormatException{
		if (!((cad.equalsIgnoreCase("MOVE"))||(cad.equalsIgnoreCase("MOVER"))) throw new WrongInstructionFormatException();
		else return new Instruction();
		return null;
		
	}
	private static final String LINE_SEPARATOR = System.getProperty("line.separator");
	private RobotEngine engine;
	private NavigationModule navigation;
	private ItemContainer robotContainer;
}
