package tp.pr4;

import tp.pr4.Gui.NavigationPanel;
import tp.pr4.instructions.exceptions.InstructionExecutionException;
import tp.pr4.items.Item;

public class NavigationModule {

	public NavigationModule(City aCity, Place initialPlace) {

		this.city = aCity;
		this.place = initialPlace;
	}

	public boolean atSpaceship() {

		return place.isSpaceship();

	}

	public void dropItemAtCurrentPlace(Item it) {
		this.place.addItem(it);
		if (navPanel != null) navPanel.setPlace(place);
	}

	public boolean findItemAtCurrentPlace(String id) {
		return this.place.existItem(id);
	}

	public Direction getCurrentHeading() {

		return direction;
	}

	public Place getCurrentPlace() {
		return place;

	}

	public Street getHeadingStreet() {
		return this.city.lookForStreet(place, direction);

	}

	public void initHeading(Direction heading) {
		this.direction = heading;
	}

	public void move() throws InstructionExecutionException {
		if (!(this.city.lookForStreet(this.place, this.direction) == null)) {
			//
			if (this.city.lookForStreet(this.place, this.direction).isOpen()) {

				this.place = this.city.lookForStreet(this.place, direction)
						.nextPlace(place);
				if (navPanel != null)	navPanel.move(direction, place);

			} else {
				if (navPanel != null)navPanel.say("The street is closed");
				throw new InstructionExecutionException(
						"Arrggg, there is a street but it is closed!");
			}
		} else{
			if (navPanel != null)navPanel.say("There is no street in direction "+ this.direction.toString());
			throw new InstructionExecutionException(
					"There is no street in direction "
							+ this.direction.toString());
		}
	}
	public void moveBackwards() {
		
			this.place = this.city.lookForStreet(this.place, direction.Opposite()).nextPlace(place);
			if (navPanel != null) navPanel.move(direction.Opposite(), place);
	}
	
	public Item pickItemFromCurrentPlace(String id) {
		
		Item item = place.pickItem(id);
		if (navPanel != null) navPanel.setPlace(place);
		return item;
	}

	public void rotate(Rotation rotation) {

		this.direction = direction.nextDirection(rotation);
		if (navPanel != null) navPanel.rotate(this.direction);
	}

	public void scanCurrentPlace() {
		System.out.println(this.place.toString());

	}

	public void say(String mensaje) {
		System.out.println("WALL·E says: " + mensaje);
	}

	public void print(String mensaje) {
		System.out.println(mensaje);
	}
	
	public void setNavigationPanel(NavigationPanel navPanel){
		this.navPanel = navPanel;
	}
	
	public void setInitialPlace(Place initPlace){
		this.navPanel.setInitialPlace(initPlace);
	}
	//private static final String LINE_SEPARATOR = System
		//	.getProperty("line.separator");
	private City city;
	private Place place;
	private Direction direction;
	private NavigationPanel navPanel;
	
}
