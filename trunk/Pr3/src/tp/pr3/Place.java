package tp.pr3;

public class Place {

	private boolean isSpaceShip;
	private String name;
	private String description;
	private ItemContainer items;

	public Place(String name, boolean isSpaceShip, String description) {

		this.name = name;
		this.isSpaceShip = isSpaceShip;
		this.description = description;
		this.items = new ItemContainer();
	}

	public boolean isSpaceship() {

		return this.isSpaceShip;
	}

	public String toString() {
		if (this.items.numberOfItems() != 0)
			return this.name + LINE_SEPARATOR + this.description
					+ LINE_SEPARATOR + "The place contains these objects: "
					+ LINE_SEPARATOR + this.items.toString();
		else
			return this.name + LINE_SEPARATOR + this.description
					+ LINE_SEPARATOR
					+ "The place is empty. There are no objects to pick"
					+ LINE_SEPARATOR;
	}

	public Item pickItem(String id) {

		if (this.equals(null))
			return null;
		else
			// if (this.items.searchItem(id) >= 0)
			return this.items.pickItem(id);
		// else
		// return null;
	}

	public boolean addItem(Item it) {

		return this.items.addItem(it);
	}

	private static final String LINE_SEPARATOR = System
			.getProperty("line.separator");

}
