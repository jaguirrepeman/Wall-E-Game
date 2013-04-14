package tp.pr4.Gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import tp.pr4.Direction;
import tp.pr4.RobotEngine;
import tp.pr4.Rotation;
import tp.pr4.instructions.*;

@SuppressWarnings("serial")
public class RobotPanel extends JPanel implements PropertyChangeListener {

	public RobotPanel(final RobotEngine engine) {
		this.engine = engine;
		this.setLayout(new BorderLayout(10, 10));

		// setVisible(true);
		// JScrollPanel
		JPanel instructionPanel = createInstructionPanel();
		this.add(instructionPanel, BorderLayout.WEST);

		TitledBorder titled = new TitledBorder("Robot Info");
		JPanel dataPanel = new JPanel();
		dataPanel.setLayout(new BorderLayout());
		// aqui seguramente habria que hacer lo de las clases internas que sale
		// en los apunntes
		JPanel statusPanel = new JPanel();
		statusPanel.setLayout(new FlowLayout());
		Font font = new Font(null, Font.BOLD, 12);
		robotInfo = new JFormattedTextField("Fuel: " + engine.getFuel()
				+ " Recycled: " + engine.getRecycledMaterial());
		robotInfo.setBorder(null);
		robotInfo.setFont(font);
		robotInfo.setEditable(false);
		robotInfo.addPropertyChangeListener("value", this);
		statusPanel.add(robotInfo);
		dataPanel.add(statusPanel, BorderLayout.CENTER);

		// JScrollPane tabla = new JScrollPane();

		//String[] columnNames = { "id", "Description" };

		// Object[][] objeticos = engine.getItemsFromContainer(flags);
		//Object[][] data = { { "Newspapers", "News on sport" } };
		// Object[][] objects = engine.getItemsFromContainer();

		tabla = new DefaultTableModel(new String[] { "Id.", "Description" }, 0);
		JTable table = new JTable(tabla);
		// table.addComponentListener(new ComponentListener())

		// final JTable table = new JTable(data, columnNames);
		// table.setEnabled(false); // para no poder modificar la tabla
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

	// no funciona
	public void propertyChange(PropertyChangeEvent evt, RobotEngine engine) {

		robotInfo.setValue("Fuel: " + engine.getFuel() + " Recycled: "
				+ engine.getRecycledMaterial());

	}

	public void changeInventory(DefaultTableModel modelo) {
		int rows = modelo.getRowCount();
		for (int i = 0; i < rows; i++)
			modelo.removeRow(0);

		for (int i = 0; i < engine.numberOfItems(); i++)
			modelo.addRow(engine.getItemsFromContainer(i)/* objeto del array */);

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public JPanel createInstructionPanel() {
		JPanel instructionPanel = new JPanel();
		TitledBorder titled = new TitledBorder("Instructions");
		instructionPanel.setBorder(titled);
		instructionPanel.setLayout(new GridLayout(4, 2, 3, 3));
		JButton move = new JButton("MOVE") {
			{
				this.addMouseListener(new OyenteRaton() {

					@Override
					public void mouseClicked(MouseEvent e) {
						engine.communicateRobot(new MoveInstruction());
						robotInfo.setText("Fuel: " + engine.getFuel()
								+ " Recycled: " + engine.getRecycledMaterial());
					}
					// hacer cosas con el fuel y tal...

				});
				// robotInfo.setText("Fuel: " + engine.getFuel() + " Recycled: "
				// + engine.getRecycledMaterial());
				// robotInfo.setValue("Fuel: " + engine.getFuel() +
				// " Recycled: " + engine.getRecycledMaterial());
			}

		};

		JButton quit = new JButton("QUIT") {
			{
				this.addMouseListener(new OyenteRaton() {

					@Override
					public void mouseClicked(MouseEvent e) {
						engine.communicateRobot(new QuitInstruction());
					}
				});
			}

		};
		JButton turn = new JButton("TURN") {
			{
				this.addMouseListener(new OyenteRaton() {

					@Override
					public void mouseClicked(MouseEvent e) {
						engine.communicateRobot(new TurnInstruction(rotacion));
						robotInfo.setText("Fuel: " + engine.getFuel()
								+ " Recycled: " + engine.getRecycledMaterial());
					}

				});

				// robotInfo.setValue("Fuel: " + engine.getFuel() +
				// " Recycled: " + engine.getRecycledMaterial());
				// al compilar no sale la ventana
			}

		};
		JButton pick = new JButton("PICK") {
			{
				this.addMouseListener(new OyenteRaton() {

					@Override
					public void mouseClicked(MouseEvent e) {
						if (!objectToPick.getText().isEmpty())
							engine.communicateRobot(new PickInstruction(
									objectToPick.getText()));
						// hacer cosas nazis con la tabla
						changeInventory(tabla);
						// tabla.fireTableDataChanged();
					}

				});
			}

		};
		JButton drop = new JButton("DROP") {
			{
				this.addMouseListener(new OyenteRaton() {

					@Override
					public void mouseClicked(MouseEvent e) {
						engine.communicateRobot(new DropInstruction());
						changeInventory(tabla);
					}
					// hacer cosas nazis con la tabla

				});
			}

		};
		JButton operate = new JButton("OPERATE") {
			{
				this.addMouseListener(new OyenteRaton() {

					@Override
					public void mouseClicked(MouseEvent e) {
						engine.communicateRobot(new OperateInstruction());
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
				// System.out.println(rotacion);
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

		objectToPick.getName();

		return instructionPanel;

	}

	private JTextField objectToPick;
	private String rotacion;
	@SuppressWarnings("rawtypes")
	private JComboBox directionToTurn;
	private DefaultTableModel tabla;
	private JFormattedTextField robotInfo;
	
	private RobotEngine engine;
	
	@Override
	//no funciona
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		/*robotInfo.setText("Fuel: " + engFuel
				+ " Recycled: " + engRecycled);
				*/

	}

}