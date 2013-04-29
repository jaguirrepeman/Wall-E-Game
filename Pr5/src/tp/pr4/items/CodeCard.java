package tp.pr4.items;

import tp.pr4.NavigationModule;
import tp.pr4.RobotEngine;

public class CodeCard extends Item {

	public CodeCard(String id, String description, String code) {
		super(id, description);
		this.code = code;
	}

	@Override
	public boolean canBeUsed() {
		return true;
	}

	@Override
	public boolean use (RobotEngine r, NavigationModule nav) {
		if(!(nav.getHeadingStreet() == null)){ //si la calle existe..
			if (nav.getHeadingStreet().isOpen()) return nav.getHeadingStreet().close(this); //si la calle esta abierta, la cierra
			else return nav.getHeadingStreet().open(this);	//si la calle esta cerrada, la abre
		}
		else return false;
	}
	
	@Override
	public void undoUse(RobotEngine r, NavigationModule nav) {
		
		if (nav.getHeadingStreet().isOpen()) nav.getHeadingStreet().close(this);
		else nav.getHeadingStreet().open(this);
	}
	
	public String getCode(){
		return this.code;
	}
	
	public String toString(){
		return this.getId() + ": " + super.toString();
		
	}
	
	private String code;

	
	
}
