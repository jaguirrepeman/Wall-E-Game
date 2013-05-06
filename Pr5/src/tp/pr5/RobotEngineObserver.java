package tp.pr5;

public interface RobotEngineObserver {
	
	void raiseError(String msg);
	
	void communicationHelp(String help);
	
	void engineOff(boolean atShip);
	
	void communicationCompleted();
	
	void robotUpdate(int fuel, int recycledMaterial);
	
	void robotSays(String message);
	
	
}
