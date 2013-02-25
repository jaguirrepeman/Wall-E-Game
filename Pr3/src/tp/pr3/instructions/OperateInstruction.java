package tp.pr3.instructions;

import tp.pr3.Action;
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
					// Item item2 = this.items.getItem(instruccion.getId());
			// hay que revisar algunas cosas
			int initialFuel = this.engine.getFuel();
			int initialRecMat = this.engine.getRecycledMaterial();

			if (!(this.items.getItem(id) == null)) {
				if (!this.items.getItem(id).use(this, this.navigation.getCurrentPlace())) { // si no se puede usar aqui
					if (!this.items.getItem(instruccion.getId())
							.canBeUsed()) { // si ya no se puede usar
						say("What a pity! I have no more "
								+ instruccion.getId() + " in my inventory");
						this.items.pickItem(instruccion.getId());
					} else
						say("I have problems using the object "
								+ instruccion.getId());
				} else {
					if ((initialFuel != this.fuel)
							|| (initialRecMat != this.recycledMaterial))
						printStatus();
					if (!this.items.getItem(instruccion.getId())
							.canBeUsed()) {
						say("What a pity! I have no more "
								+ instruccion.getId() + " in my inventory");
						this.items.pickItem(instruccion.getId());
					}
				}
			} else
				say("I have problems using the object "
						+ instruccion.getId());
	}
	
	public String getHelp(){
		
		return "OPERATE|OPERAR <ID>";
	}
	
	public Instruction parse (String cad) throws WrongInstructionFormatException{
		String[] comando = cad.split(" ");
		if((comando[0].equalsIgnoreCase("OPERATE")) && (comando.length > 1)) {
			this.id = comando[1];
			return this;
		}
		else throw new WrongInstructionFormatException();
	
	}
	
	private RobotEngine engine;
	private NavigationModule navigation;
	private ItemContainer items;
	private String id;
}
