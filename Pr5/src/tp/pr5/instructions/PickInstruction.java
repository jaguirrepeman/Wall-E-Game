package tp.pr5.instructions;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;
import tp.pr5.items.Item;
import tp.pr5.items.ItemContainer;

/**
 * 
 * 
 * @author usuario_local
 * 
 */
public class PickInstruction extends UndoableInstruction {
	
	public PickInstruction(){
		
	}
	public PickInstruction(String itemId){
		this.id = itemId;
	}
	
	public void configureContext(RobotEngine engine,
			NavigationModule navigation, ItemContainer robotContainer) {
		this.engine = engine;
		this.navigation = navigation;
		this.robotContainer = robotContainer;
	}

	public void execute() throws InstructionExecutionException {
		Item item = this.navigation.pickItemFromCurrentPlace(id);
		if (item != null) {
			if (this.robotContainer.addItem(item))
				engine.saySomething("I am happy! Now I have " + id);
			else {
				throw new InstructionExecutionException(
						"I am stupid! I had already the object " + id);
			}
		} else {

			throw new InstructionExecutionException("Ooops, this place has not the object " + id);
		}
	}
	
	public void undo() {
		Item item = robotContainer.pickItem(id);
		this.navigation.dropItemAtCurrentPlace(item);
	}
	
	public String getHelp() {
		return "PICK|COGER <id>";

	}
	
	public String toString(){
		return "PICK " + this.id;
	}

	public Instruction parse(String cad) throws WrongInstructionFormatException {
		
		String[] comando = cad.split(" ");

		if ((comando[0].equalsIgnoreCase("PICK") || comando[0]
				.equalsIgnoreCase("COGER")) && (comando.length == 2)) {
			this.id = comando[1];
			return this;
		} else
			throw new WrongInstructionFormatException();
	}

	private RobotEngine engine;
	private NavigationModule navigation;
	private ItemContainer robotContainer;
	private String id;

}
