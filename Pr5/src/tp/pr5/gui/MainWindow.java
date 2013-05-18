package tp.pr5.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import tp.pr5.RobotEngineObserver;
import tp.pr5.instructions.QuitInstruction;

@SuppressWarnings("serial")
public class MainWindow extends JFrame implements RobotEngineObserver {
	
	
	/*
	 * TODO no
	 * public MainWindow(RobotEngine engine, Place initPlace){
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
		//this.engine = engine;
		//this.engine.setNavigationPanel(navPanel);
		//this.engine.setRobotPanel(robotPan);
		this.navPanel.setInitialPlace(initPlace);
	}
*/
	public MainWindow(final GUIController gameController){
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
				gameController.communicateInstruction(new QuitInstruction());			
			}
			
		});
		this.setJMenuBar(menuBar);	
		
			
		
		robotPan = new RobotPanel(gameController);
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
		this.add(infoPanel, BorderLayout.AFTER_LAST_LINE);
		
		gameController.registerEngineObserver(robotPan);
		gameController.registerEngineObserver(infoPanel);
		gameController.registerItemContainerObserver(robotPan);
		gameController.registerItemContainerObserver(infoPanel);
		gameController.registerRobotObserver(navPanel);
		gameController.registerRobotObserver(infoPanel);
		
	}
	@Override
	public void raiseError(String msg){
		
	}
	@Override
	public void communicationHelp(String help){
		
	}
	@Override
	public void engineOff(boolean atShip){
		if (atShip)
			CloseApp.requestQuit("You finally found your spaceship");
		else
			CloseApp.requestQuit("You run out of fuel");
	}
	
	@Override
	public void communicationCompleted() {
		CloseApp.quitApp();
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