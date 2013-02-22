package tp.pr3.instructions;

import tp.pr3.NavigationModule;
import tp.pr3.RobotEngine;
import tp.pr3.items.ItemContainer;

public class TurnInstruction {
	public void configureContext(RobotEngine engine, NavigationModule navigation, ItemContainer robotContainer){
		this.engine = engine;
		this.navigation = navigation;
		this.robotContainer = robotContainer;
	}
	public void execute(){
		/**
		 * else if (instruccion.getAction().equals(Action.TURN)
				&& !(instruccion.getRotation().equals(Rotation.UNKNOWN))) {
			this.direction = direction.nextDirection(instruccion
					.getRotation());
			this.fuel -= 1;
			printStatus();
			System.out.println("WALL·E is looking at direction "
					+ this.direction.toString());
*/
	}
	public String getHelp(){
		
		return "TURN | GRITAR < LEFT|RIGHT >";
		
	}
	public Instruction parse (String cad){
		
		String[] comando = cad.split(" ");
		
		
		return null;
		
		
		
	}
	private RobotEngine engine;
	private NavigationModule navigation;
	private ItemContainer robotContainer;
}
