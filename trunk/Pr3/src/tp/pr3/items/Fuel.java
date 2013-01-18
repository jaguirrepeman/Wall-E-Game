package tp.pr3.items;

import tp.pr3.Place;
import tp.pr3.RobotEngine;

public class Fuel extends Item {

	public Fuel(String id, String description, int power, int times) {
		super(id, description);
		// TODO Auto-generated constructor stub
		this.power = power;
		this.times = times;
	}

	@Override
	public boolean canBeUsed() {
		// TODO Auto-generated method stub
		return times > 0;
	}

	@Override
	public boolean use(RobotEngine r, Place p) {
		if (this.canBeUsed()) {
			r.addFuel(power);
			this.times--;
			return true;
		} else
			return false;

	}

	public String toString() {
		return this.getId() + ": " + super.toString() + "// power = "
				+ this.power + ", times = " + this.times;
	}

	private int power;
	private int times;
}
