package tp.pr4.items;

import tp.pr4.NavigationModule;
import tp.pr4.RobotEngine;

public abstract class Item {
	
	
	public Item(String id, String description) {
		
		this.id = id;
		this.description = description;
	}

	public abstract boolean canBeUsed();

	public abstract boolean use(RobotEngine r, NavigationModule nav);
	
	public  void undoUse(RobotEngine r, NavigationModule nav){
		
	}

	public String getId() {
		
		return this.id;
	}

	public String toString() {
		
		return this.description;
	}

	protected String id;
	protected String description;

}
