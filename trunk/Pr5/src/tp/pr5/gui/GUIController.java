package tp.pr5.gui;

import tp.pr5.Controller;
import tp.pr5.NavigationObserver;
import tp.pr5.RobotEngine;
import tp.pr5.RobotEngineObserver;
import tp.pr5.instructions.Instruction;
import tp.pr5.items.InventoryObserver;

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
	
	public void registerEngineObserver(RobotEngineObserver gameObserver){
		this.robot.addEngineObserver(gameObserver);
	}
	
	public void registerItemContainerObserver(InventoryObserver containerObserver){
		this.robot.addItemContainerObserver(containerObserver);
	}
	
	public void registerRobotObserver(NavigationObserver playerObserver){
		this.robot.addNavigationObserver(playerObserver);
	}

}
