package tp.pr5.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import tp.pr5.RobotEngine;
import tp.pr5.RobotEngineObserver;
import tp.pr5.Rotation;
import tp.pr5.instructions.*;
import tp.pr5.items.InventoryObserver;
import tp.pr5.items.Item;

@SuppressWarnings("serial")
public class RobotPanel extends JPanel implements RobotEngineObserver,
		InventoryObserver{
	public RobotPanel(GUIController gameController){
		
		this.game = gameController;
		
		this.setLayout(new BorderLayout(10, 10));

		//Creación del panel de instrucciones
		JPanel instructionPanel = createInstructionPanel();
		this.add(instructionPanel, BorderLayout.WEST);

		//Creación del StatusPanel del robot
		TitledBorder titled = new TitledBorder("Robot Info");
		JPanel dataPanel = new JPanel();
		dataPanel.setLayout(new BorderLayout());
		JPanel statusPanel = new JPanel();
		statusPanel.setLayout(new FlowLayout());
		Font font = new Font(null, Font.BOLD, 12);
		robotInfo = new JFormattedTextField();//TODO hay que hacer el robotUpdate, no tonto del culo, se hace en el startController del GUIController
		robotInfo.setBorder(null);
		robotInfo.setFont(font);
		robotInfo.setEditable(false);
		statusPanel.add(robotInfo);
		dataPanel.add(statusPanel, BorderLayout.CENTER);

		//Creación del inventario del robot
		tabla = new DefaultTableModel(new String[] { "Id.", "Description" }, 0);
		final JTable table = new JTable(tabla);
		table.addMouseListener(new OyenteRaton(){

			@Override
			public void mouseClicked(MouseEvent e) {
				int i = table.getSelectedRow();
				
				if (i>=0)
					robotsObject = table.getValueAt(i,0).toString();
				
			}
			
		});
		
		
		
		table.setOpaque(false); // con esto se consigue que el fondo de la tabla
								// este en gris
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table.setFillsViewportHeight(true);

		JScrollPane tableScrollPane = new JScrollPane(table);
		dataPanel.add(tableScrollPane, BorderLayout.SOUTH);
		dataPanel.setBorder(titled);

		this.add(dataPanel, BorderLayout.CENTER);

		rotacion = directionToTurn.getSelectedItem().toString();
 }
	//OBsoleto
	public RobotPanel(final RobotEngine engine) {
		
		this.engine = engine;
		this.setLayout(new BorderLayout(10, 10));

		//Creación del panel de instrucciones
		JPanel instructionPanel = createInstructionPanel();
		this.add(instructionPanel, BorderLayout.WEST);

		//Creación del StatusPanel del robot
		TitledBorder titled = new TitledBorder("Robot Info");
		JPanel dataPanel = new JPanel();
		dataPanel.setLayout(new BorderLayout());
		JPanel statusPanel = new JPanel();
		statusPanel.setLayout(new FlowLayout());
		Font font = new Font(null, Font.BOLD, 12);
		robotInfo = new JFormattedTextField("Fuel: " + engine.getFuel()
				+ " Recycled: " + engine.getRecycledMaterial());
		robotInfo.setBorder(null);
		robotInfo.setFont(font);
		robotInfo.setEditable(false);
		statusPanel.add(robotInfo);
		dataPanel.add(statusPanel, BorderLayout.CENTER);

		//Creación del inventario del robot
		tabla = new DefaultTableModel(new String[] { "Id.", "Description" }, 0);
		final JTable table = new JTable(tabla);
		table.addMouseListener(new OyenteRaton(){

			@Override
			public void mouseClicked(MouseEvent e) {
				int i = table.getSelectedRow();
				
				if (i>=0)
					robotsObject = table.getValueAt(i,0).toString();
				
			}
			
		});
		
		
		
		table.setOpaque(false); // con esto se consigue que el fondo de la tabla
								// este en gris
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table.setFillsViewportHeight(true);

		JScrollPane tableScrollPane = new JScrollPane(table);
		dataPanel.add(tableScrollPane, BorderLayout.SOUTH);
		dataPanel.setBorder(titled);

		this.add(dataPanel, BorderLayout.CENTER);

		rotacion = directionToTurn.getSelectedItem().toString();
	}

	public void changeInventory(DefaultTableModel modelo) {
		int rows = modelo.getRowCount();
		for (int i = 0; i < rows; i++)
			modelo.removeRow(0);
		//TODO cambiar a los métodos del interfaz
		for (int i = 0; i < engine.numberOfItems(); i++)
			modelo.addRow(engine.getItemsFromContainer(i)/* objeto del array */);

	}
	
	public void setStatus(int fuel, int recycled){
		robotInfo.setValue("Fuel: " + fuel + " Recycled: " + recycled);
		if (fuel <= 0){
			CloseApp.requestQuit("You run out of fuel, the game has ended");
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public JPanel createInstructionPanel() {
		JPanel instructionPanel = new JPanel();
		TitledBorder titled = new TitledBorder("Instructions");
		instructionPanel.setBorder(titled);
		instructionPanel.setLayout(new GridLayout(5, 2, 3, 3));
		JButton move = new JButton("MOVE") {
			{
				this.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						game.communicateInstruction(new MoveInstruction());
						
					}
				
				});
				
			}

		};

		JButton quit = new JButton	("QUIT") {
			{

				this.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						int seleccion = JOptionPane.showOptionDialog(null,
								null,
								"Exit WALL·E",
								JOptionPane.YES_NO_CANCEL_OPTION,
								JOptionPane.QUESTION_MESSAGE,
								CityPanel.createImageIcon(
										"images/walleExit.png", "WALLE"),

								// null, // null para icono por defecto.
								/* null, null para YES, NO y CANCEL, si no: */new Object[] {
										"YES", "NO" }, "null");

						if (seleccion == -1 || seleccion == 0)
							System.exit(0);
					}

				});

			}

		};
		JButton turn = new JButton("TURN") {
			{
				this.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						game.communicateInstruction(new TurnInstruction	(rotacion));
						
					}
				
				});
				
			}

		};
		JButton pick = new JButton("PICK") {
			{
				this.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						if (!objectToPick.getText().isEmpty()){
							game.communicateInstruction(new PickInstruction(objectToPick.getText()));
							changeInventory(tabla);
						}
						
					}
				
				});
				
			}

		};
		JButton drop = new JButton("DROP") {
			
			{
				this.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						if (robotsObject != null){
							game.communicateInstruction(new DropInstruction(robotsObject));
							changeInventory(tabla);
						}
					}
				
				});
				
			}
			

		};
		JButton operate = new JButton("OPERATE") {
			{
				this.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						if (robotsObject != null){
							game.communicateInstruction(new OperateInstruction(robotsObject));
							changeInventory(tabla);
						}
					}
				
				});
				
			}

		};
		JButton undo = new JButton("UNDO") {
			
			{
				this.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						game.communicateInstruction(new UndoInstruction());
						changeInventory(tabla);
						
					}
				
				});
				
			}
		};
		objectToPick = new JTextField();
		String[] rotationString = { Rotation.LEFT.toString(),
				Rotation.RIGHT.toString() };
		directionToTurn = new JComboBox(rotationString);
		directionToTurn.setSelectedIndex(1);
		directionToTurn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				rotacion = directionToTurn.getSelectedItem().toString();
			}
		});
		objectToPick.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				objectToPick.getText().toString();

			}
		});
		instructionPanel.add(move);
		instructionPanel.add(quit);
		instructionPanel.add(turn);
		instructionPanel.add(directionToTurn);
		instructionPanel.add(pick);
		instructionPanel.add(objectToPick);
		instructionPanel.add(drop);
		instructionPanel.add(operate);
		instructionPanel.add(undo);

		
		return instructionPanel;

	}

	
	
	@Override
	public void inventoryChange(List<Item> inventory) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inventoryScanned(String inventoryDescription) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void itemScanned(String description) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void itemEmpty(String itemName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void raiseError(String msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void communicationHelp(String help) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void engineOff(boolean atShip) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void communicationCompleted() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void robotUpdate(int fuel, int recycledMaterial) {
		//robotInfo.setText("Fuel: " + fuel
		//		+ " Recycled: " + recycledMaterial);
		robotInfo.setValue("Fuel: " + fuel + " Recycled: " + recycledMaterial);
	}

	@Override
	public void robotSays(String message) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	private JTextField objectToPick;
	private String rotacion;
	private String robotsObject;
	@SuppressWarnings("rawtypes")
	private JComboBox directionToTurn;
	private DefaultTableModel tabla;
	private JFormattedTextField robotInfo;
	
	private GUIController game;
	//private RobotEngine engine;

	
}