package tp.pr4.Gui;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.*;

import tp.pr4.RobotEngine;
@SuppressWarnings("serial")
public class MainWindow extends JFrame {
	public MainWindow(RobotEngine engine){
		super("Pikey Window");
		this.setSize(1080, 720);
		
		this.setLayout(new BorderLayout(10, 10));
		RobotPanel robotPan = new RobotPanel();
		this.add(robotPan, BorderLayout.NORTH);
		//setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.engine = engine;
	}
	
	private RobotEngine engine;
}
