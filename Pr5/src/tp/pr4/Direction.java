package tp.pr4;

public enum Direction {

	EAST, NORTH, SOUTH, UNKNOWN, WEST;

	public Direction Opposite() {

		switch (this) {

		case EAST:
			return Direction.WEST;
		case WEST:
			return Direction.EAST;
		case NORTH:
			return Direction.SOUTH;
		case SOUTH:
			return Direction.NORTH;
		default:
			return null;

		}

	}

	public Direction nextDirection(Rotation rotation) {
		Direction nextDir = null;
		if (rotation.equals(Rotation.LEFT)) {
			if (this.equals(Direction.EAST))
				nextDir = Direction.NORTH;
			else if (this.equals(Direction.NORTH))
				nextDir = Direction.WEST;
			else if (this.equals(Direction.WEST))
				nextDir = Direction.SOUTH;
			else if (this.equals(Direction.SOUTH))
				nextDir = Direction.EAST;
			else
				nextDir = this;
		} else {
			if (this.equals(Direction.EAST))
				nextDir = Direction.SOUTH;
			else if (this.equals(Direction.SOUTH))
				nextDir = Direction.WEST;
			else if (this.equals(Direction.WEST))
				nextDir = Direction.NORTH;
			else if (this.equals(Direction.NORTH))
				nextDir = Direction.EAST;
			else
				nextDir = this;
		}
		return nextDir;
	}

	public Direction turnRight() {
		switch (this) {
		case EAST:
			return SOUTH;
		case SOUTH:
			return WEST;
		case WEST:
			return NORTH;
		case NORTH:
			return EAST;
		default:
			return this;
		}
	}

	public Direction turnLeft() {
		switch (this) {
		case EAST:
			return NORTH;
		case NORTH:
			return WEST;
		case WEST:
			return SOUTH;
		case SOUTH:
			return EAST;
		default:
			return this;
		}
	}

}
