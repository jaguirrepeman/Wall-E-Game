package tp.pr5.gui;

import tp.pr5.Controller;
import tp.pr5.RobotEngine;
import tp.pr5.instructions.Instruction;

public class GUIController extends Controller {
	//
	public GUIController (RobotEngine robot){
		super(robot);
	}
	
	public void startController(){
		
		this.robot.requestStart();
	}
	
	public void communicateInstruction(Instruction ins){
		this.robot.communicateRobot(ins);
	}

}
