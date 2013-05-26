package tp.pr5;

public abstract class Controller {

	public Controller(RobotEngine wallE){
		this.robot = wallE;
	}
	protected RobotEngine robot;
}
