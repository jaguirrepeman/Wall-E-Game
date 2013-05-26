package tp.pr5;

import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.items.Item;

public class NavigationModule extends tp.pr5.Observable<NavigationObserver>{

	public NavigationModule(City aCity, Place initialPlace) {

		this.city = aCity;
		this.place = initialPlace;
	}

	public boolean atSpaceship() {

		return place.isSpaceship();

	}

	public void dropItemAtCurrentPlace(Item it) {
		this.place.addItem(it);

		for (NavigationObserver obs: this.observers)
			obs.placeHasChanged(this.place);
		
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
				for (NavigationObserver obs : this.observers)
					obs.robotArrivesAtPlace(this.direction, this.place);

			} else {
				throw new InstructionExecutionException(
						"Arrggg, there is a street but it is closed!");
			}
		} else {
			throw new InstructionExecutionException(
					"There is no street in direction "
							+ this.direction.toString());
		}
	}
	public void moveBackwards() {
		
			this.place = this.city.lookForStreet(this.place, direction.Opposite()).nextPlace(place);
			for (NavigationObserver obs: this.observers)
				obs.robotArrivesAtPlace(this.direction.Opposite(), this.place);
	}
	
	public Item pickItemFromCurrentPlace(String id) {
		
		Item item = this.place.pickItem(id);
		
		for (NavigationObserver obs: this.observers)
			obs.placeHasChanged(this.place);
		
		return item;
	}

	public void rotate(Rotation rotation) {

		this.direction = direction.nextDirection(rotation);
		
		for (NavigationObserver obs: this.observers){
			obs.headingChanged(this.direction);
		}
	}

	public void scanCurrentPlace() {
		for (NavigationObserver obs : this.observers)
			obs.placeScanned(place);
		

	}

	public void addNavigationObserver(NavigationObserver navigationObserver){
		this.addObserver(navigationObserver);
	}
	
	public void initialize() {
		for (NavigationObserver o : this.observers){ 
			o.initNavigationModule(this.place, this.direction);
		}
	}
	


	private City city;
	private Place place;
	private Direction direction;
	

	
}
