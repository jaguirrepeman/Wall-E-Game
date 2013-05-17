package tp.pr5.items;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;

public abstract class Item {
	
	
	public Item(String id, String description) {
		
		this.id = id;
		this.description = description;
	}

	public abstract boolean canBeUsed();

	public abstract boolean use(RobotEngine r, NavigationModule nav);
	
	public  void undoUse(RobotEngine r, NavigationModule nav){
		
	}
	
	public String[] itemForTable(){
		String[] object= new String[2];
		object[0] = this.id;
		object[1] = this.description;
		return object;
		
	}

	public String getId() {
		
		return this.id;
	}
	
	public String getDescription(){
		
		return this.description;
	}

	public String toString() {
		
		return this.description;
	}

	protected String id;
	protected String description;

}
