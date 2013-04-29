package tp.pr4.items;

import tp.pr4.NavigationModule;
import tp.pr4.RobotEngine;

public class Garbage extends Item {

	public Garbage(String id, String description, int recycledMaterial) {
		super(id, description);
		this.recycledMaterial = recycledMaterial;
		this.hasBeenUsed = false;
	}

	@Override
	public boolean canBeUsed() {
		return !this.hasBeenUsed;

	}

	@Override
	public boolean use(RobotEngine r, NavigationModule nav) {
		if (this.canBeUsed()) {
			r.addRecycledMaterial(recycledMaterial);
			this.hasBeenUsed = true;
			return true;
		} else
			return false;

	}
	
	@Override
	public void undoUse(RobotEngine r, NavigationModule nav) {
		
		this.hasBeenUsed = false;
		r.addRecycledMaterial(-this.recycledMaterial);
	}
	
	public String toString() {
		return this.getId() + ": " + super.toString()
				+ "// recycled material = " + this.recycledMaterial;
	}

	private int recycledMaterial;
	private boolean hasBeenUsed;

}
