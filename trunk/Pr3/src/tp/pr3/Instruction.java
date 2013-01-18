package tp.pr3;


public class Instruction {
	
	
	public Instruction (){
		this.action = Action.UNKNOWN;
		this.rotation = Rotation.UNKNOWN;
		this.id = null;
	}
	
	public Instruction (Action action){
		this.action = action;
		this.rotation = Rotation.UNKNOWN;
		this.id = null;
	}

	public Instruction (Action action, Rotation rotation){
		this.action = action;
		this.rotation = rotation;
		this.id = null;
	}
	
	public Instruction(Action action, String id){
		this.action = action;
		this.rotation = Rotation.UNKNOWN;
		this.id = id;
	}

	public Action getAction(){
		return this.action ;	
	}
	
	public Rotation getRotation(){
		return this.rotation;
	}
	
	public boolean isValid(){
		boolean unkAction, unkRot, idAction;
		unkAction = (this.action.equals(Action.UNKNOWN));
		unkRot = ((this.action.equals(Action.TURN) && this.rotation.equals(Rotation.UNKNOWN)));
		idAction = (((this.action.equals(Action.OPERATE) || (this.action.equals(Action.PICK)))
				&& this.id == null));
		return  !( unkAction|| unkRot || idAction) ; 
	}
	public String getId(){
		return this.id;
	}
	
	private Action action;
	private Rotation rotation;
	private String id;
}
