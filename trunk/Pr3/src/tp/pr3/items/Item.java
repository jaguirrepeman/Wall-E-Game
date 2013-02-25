package tp.pr3.items;

import tp.pr3.NavigationModule;
import tp.pr3.Place;
import tp.pr3.RobotEngine;

public abstract class Item {
	
	
	public Item(String id, String description) {
		
		this.id = id;
		this.description = description;
	}

	public abstract boolean canBeUsed();

	public abstract boolean use(RobotEngine r, NavigationModule nav);

	public String getId() {
		
		return this.id;
	}

	public String toString() {
		
		return this.description;
	}

	protected String id;
	protected String description;

}
