package tp.pr5;

import tp.pr5.items.CodeCard;

public class Street {

	public Street() {

	}

	public Street(Place source, Direction direction, Place target) {
		this.source = source;
		this.direction = direction;
		this.target = target;
		this.isOpen = true;
		this.code = null;
	}

	public Street(Place source, Direction direction, Place target,
			boolean isOpen, String code) {
		this.source = source;
		this.direction = direction;
		this.target = target;
		this.isOpen = isOpen;
		this.code = code;
	}

	public boolean comeOutFrom(Place place, Direction whichDirection) {

		return ((place.equals(this.source) && this.direction
				.equals(whichDirection)) || (place.equals(this.target) && this.direction
				.equals(whichDirection.Opposite())));
	}

	public Place nextPlace(Place whereAmI) {
		if (whereAmI.equals(this.source))
			return this.target;
		else if (whereAmI.equals(this.target))
			return this.source;
		else
			return null;
	}

	public boolean isOpen() {
		return this.isOpen;
	}

	public boolean open(CodeCard card) {
		if (! (this.code == null)){
			
			if (this.code.equals(card.getCode())) {
				if (!this.isOpen())
					this.isOpen = true;
				return true;
			} else
				return false;
			}
		else return false;
		
	}
	

	public boolean close(CodeCard card) {
		if (! (this.code == null)){
			
			if (this.code.equals(card.getCode())){
				if (this.isOpen()) 
					this.isOpen = false;
				return true;
			}
			else return false;
		}
		else return false;
		
	}

	private Place source;
	private Direction direction;
	private Place target;
	private String code;
	private boolean isOpen;

}
