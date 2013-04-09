package tp.pr4.Gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.*;
import javax.swing.border.TitledBorder;

import tp.pr4.Place;
import tp.pr4.RobotEngine;
@SuppressWarnings("serial")
public class MainWindow extends JFrame {
	public MainWindow(RobotEngine engine, Place initPlace){
		super("WALL·E");
		this.setSize(1080, 720);
		this.setLayout(new BorderLayout(10, 10));
		robotPan = new RobotPanel(engine);
	//	robotPan.setPreferredSize(new Dimension(200, 200));
		robotPan.setSize(200, 200);
		this.add(robotPan, BorderLayout.NORTH);
		//setVisible(true);
		navPanel = new NavigationPanel();
		this.add(navPanel);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.engine = engine;
		this.engine.setNavigationPanel(navPanel);
		this.navPanel.setInitialPlace(initPlace);
	}
	
	public NavigationPanel getNavigationPanel(){
		return this.navPanel;
	}
	
	private RobotEngine engine;
	private RobotPanel robotPan;
	private NavigationPanel navPanel;
}