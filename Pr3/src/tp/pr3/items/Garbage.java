package tp.pr3.items;

import tp.pr3.NavigationModule;
import tp.pr3.RobotEngine;

public class Garbage extends Item {

	public Garbage(String id, String description, int recycledMaterial) {
		super(id, description);
		this.recycledMaterial = recycledMaterial;
	}

	@Override
	public boolean canBeUsed() {
		return this.recycledMaterial != 0;

	}

	@Override
	public boolean use(RobotEngine r, NavigationModule nav) {
		if (this.canBeUsed()) {
			r.addRecycledMaterial(recycledMaterial);
			this.recycledMaterial = 0;
			return true;
		} else
			return false;

	}

	public String toString() {
		return this.getId() + ": " + super.toString()
				+ "// recycled material = " + this.recycledMaterial;
	}

	private int recycledMaterial;
}
