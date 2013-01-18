package tp.pr3;

public abstract class Item {
	
	
	public Item(java.lang.String id, java.lang.String description) {
		
		this.id = id;
		this.description = description;
	}

	public abstract boolean canBeUsed();

	public abstract boolean use(RobotEngine r, Place p);

	public java.lang.String getId() {
		
		return this.id;
	}

	public java.lang.String toString() {
		
		return this.description;
	}

	protected String id;
	protected String description;

}
