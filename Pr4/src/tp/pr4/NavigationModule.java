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
				navPanel.move(direction, place);

			} else {
				throw new InstructionExecutionException(
						"Arrggg, there is a street but it is closed!");
			}
		} else
			throw new InstructionExecutionException(
					"There is no street in direction "
							+ this.direction.toString());
	}

	public Item pickItemFromCurrentPlace(String id) {

		return place.pickItem(id);
	}

	public void rotate(Rotation rotation) {

		this.direction = direction.nextDirection(rotation);
		
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
