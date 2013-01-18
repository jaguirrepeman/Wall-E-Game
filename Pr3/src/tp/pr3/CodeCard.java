package tp.pr3;

public class CodeCard extends Item {

	public CodeCard(String id, String description, String code) {
		super(id, description);
		// TODO Auto-generated constructor stub
	this.code = code;
	}

	@Override
	public boolean canBeUsed() {
		return true;
	}

	@Override
	public boolean use(RobotEngine r, Place p) {
		if(!(r.getHeadingStreet() == null)){
			if (r.getHeadingStreet().isOpen()) return r.getHeadingStreet().close(this);
			else return r.getHeadingStreet().open(this);
		}
		else return false;
	}
	public String getCode(){
		return this.code;
	}
	public String toString(){
		return this.getId() + ": " + super.toString();
		
	}
	
	private String code;
	//private static final String LINE_SEPARATOR = System.getProperty("line.separator");

}
