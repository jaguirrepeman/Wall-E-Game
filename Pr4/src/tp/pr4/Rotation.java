package tp.pr4;


public enum Rotation {

	LEFT, RIGHT, UNKNOWN;

	public Rotation Opposite(){
		
		switch(this){
			case LEFT:
				return RIGHT;
			case RIGHT:
				return LEFT;
			default :
				return UNKNOWN;
		}
	}
}
