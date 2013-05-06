package tp.pr5;

public abstract class Controller {
	/*TODO 
	 *registerEngineObserver, registerItemContainerObserver, 
	 *registerRobotObserver, startController
	 */
	public Controller(RobotEngine wallE){
		robot = wallE;
	}
	RobotEngine robot;
}
