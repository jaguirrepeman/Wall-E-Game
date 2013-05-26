package tp.pr5.instructions;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;
import tp.pr5.items.Item;
import tp.pr5.items.ItemContainer;

public class OperateInstruction extends UndoableInstruction {

	public OperateInstruction() {

	}

	public OperateInstruction(String robotsObject) {

		this.id = robotsObject;
	}

	public void configureContext(RobotEngine engine,
			NavigationModule navigation, ItemContainer robotContainer) {
		this.engine = engine;
		this.navigation = navigation;
		this.items = robotContainer;
	}

	public void execute() throws InstructionExecutionException {

		this.engine.getFuel();
		this.engine.getRecycledMaterial();

		if (!(this.items.getItem(id) == null)) {

			if (!this.items.getItem(id).use(this.engine, this.navigation)) { // si no se puede usar aqui

				if (!this.items.getItem(id).canBeUsed()) { // si ya no se puede usar

					this.usedItem = this.items.pickItem(id);
				} else {

					throw new InstructionExecutionException(
							"I have problems using the object " + id);
				}
			} else {

				if (!this.items.getItem(id).canBeUsed()) {
					// TODO
					this.usedItem = this.items.pickItem(id);
					this.engine.saySomething("What a pity! I have no more "
							+ id + " in my inventory");

				}
			}
		} else {
			throw new InstructionExecutionException(
					"I have problems using the object " + id);
		}
	}

	// @Override
	public void undo() {

		if (usedItem != null) {
			items.addItem(this.usedItem);
		}

		this.items.getItem(id).undoUse(this.engine, this.navigation);
	}

	public String getHelp() {

		return "OPERATE|OPERAR <ID>";
	}

	public String toString() {
		return "OPERATE " + id;
	}

	public Instruction parse(String cad) throws WrongInstructionFormatException {

		String[] comando = cad.split(" ");
		if ((comando[0].equalsIgnoreCase("OPERATE") || comando[0]
				.equalsIgnoreCase("OPERAR")) && (comando.length == 2)) {
			this.id = comando[1];
			return this;
		} else
			throw new WrongInstructionFormatException();

	}

	private RobotEngine engine;
	private NavigationModule navigation;
	private ItemContainer items;
	private String id;
	private Item usedItem = null;

}
