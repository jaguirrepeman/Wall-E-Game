package tp.pr5;

import tp.pr5.items.Item;
import tp.pr5.items.ItemContainer;

public class Place implements PlaceInfo{

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
	
	public String getName(){
		return this.name;
	}

	public boolean isSpaceship() {

		return this.isSpaceShip;
	}

	public String getDescription() {
		//antes era toString();
		if (this.items.numberOfItems() != 0)
			return this.name + LINE_SEPARATOR + this.description
					+ LINE_SEPARATOR + "The place contains these objects:"
					+ LINE_SEPARATOR + this.items.toString();
		else
			return this.name + LINE_SEPARATOR + this.description
					+ LINE_SEPARATOR
					+ "The place is empty. There are no objects to pick"
					+ LINE_SEPARATOR;
	}

	public boolean existItem(String id) {
		return (this.items.searchItem(id) >= 0);

	}

	public Item pickItem(String id) {

		if (this.equals(null))
			return null;
		else
			return this.items.pickItem(id);
		
	}

	public boolean addItem(Item it) {

		return this.items.addItem(it);
	}

	public boolean dropItem(Item it) {
		if (this.items.searchItem(it.getId()) != -1)
			return false;
		else {
			this.addItem(it);
			return true;
		}
	}

	private static final String LINE_SEPARATOR = System
			.getProperty("line.separator");


}
