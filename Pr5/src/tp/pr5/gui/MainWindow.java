package tp.pr5.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import tp.pr5.Place;
import tp.pr5.RobotEngine;
import tp.pr5.RobotEngineObserver;

@SuppressWarnings("serial")
public class MainWindow extends JFrame implements RobotEngineObserver {
	
	
	public MainWindow(RobotEngine engine, Place initPlace){
		super("WALL·E");
		this.setSize(1080, 720);
		this.setLayout(new BorderLayout(10, 10));
		menuBar = new JMenuBar();
		JMenu menu = new JMenu("File");
		menuBar.add(menu);
		JMenuItem quit = new JMenuItem("Quit");
		menu.add(quit);
		quit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int seleccion = JOptionPane.showOptionDialog(null,
						null, "Exit WALL·E",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
						CityPanel.createImageIcon("images/walleExit.png", "WALLE"), 
							new Object[] { "YES", "NO"},"null");

				if (seleccion == -1 || seleccion == 0) System.exit(0);
			
			}
			
		});
		this.setJMenuBar(menuBar);	
		
			
		
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
		this.engine.setRobotPanel(robotPan);
		this.navPanel.setInitialPlace(initPlace);
	}

	public MainWindow(GUIController gameController){
		super("WALL·E");
		this.setSize(1080, 720);
		this.setLayout(new BorderLayout(10, 10));
		menuBar = new JMenuBar();
		JMenu menu = new JMenu("File");
		menuBar.add(menu);
		JMenuItem quit = new JMenuItem("Quit");
		menu.add(quit);
		quit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int seleccion = JOptionPane.showOptionDialog(null,
						null, "Exit WALL·E",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
						CityPanel.createImageIcon("images/walleExit.png", "WALLE"), 
							new Object[] { "YES", "NO"},"null");

				if (seleccion == -1 || seleccion == 0) System.exit(0);
			
			}
			
		});
		this.setJMenuBar(menuBar);	
		
			
		
		robotPan = new RobotPanel(engine);
	//	robotPan.setPreferredSize(new Dimension(200, 200));
		robotPan.setSize(200, 200);
		this.add(robotPan, BorderLayout.NORTH);
		//setVisible(true);
		navPanel = new NavigationPanel();
		this.add(navPanel);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	/*	this.engine = engine;
		this.engine.setNavigationPanel(navPanel);
		this.engine.setRobotPanel(robotPan);
		this.navPanel.setInitialPlace(initPlace);
	 * */
		infoPanel = new InfoPanel();
		this.add(infoPanel, BorderLayout.SOUTH);
		
	}
	@Override
	public void raiseError(String msg){
		
	}
	@Override
	public void communicationHelp(String help){
		
	}
	@Override
	public void engineOff(boolean atShip){
		
	}
	
	@Override
	public void communicationCompleted() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void robotUpdate(int fuel, int recycledMaterial) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void robotSays(String message) {
		// TODO Auto-generated method stub
		
	}
	
	public NavigationPanel getNavigationPanel(){
		return this.navPanel;
	}
	
//	private RobotEngine engine;
	private RobotPanel robotPan;
	private NavigationPanel navPanel;
	private JMenuBar menuBar;
	private InfoPanel infoPanel;
	
}