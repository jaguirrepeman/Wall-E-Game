package tp.pr4.Gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

import tp.pr4.Direction;
import tp.pr4.RobotEngine;
import tp.pr4.Rotation;
import tp.pr4.instructions.*;

@SuppressWarnings("serial")
public class RobotPanel extends JPanel implements PropertyChangeListener {

	public RobotPanel(RobotEngine engine) {
		this.setLayout(new BorderLayout(10, 10));

		// setVisible(true);
		// JScrollPanel
		JPanel instructionPanel = createInstructionPanel(engine);
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

		String[] columnNames = { "id", "Description" };
		
		Object[][] objeticos = engine.getItemsFromContainer();
		Object[][] data = { { "Newspapers", "News on sport" } };
		//Object[][] objects = engine.getItemsFromContainer();

		final JTable table = new JTable(data, columnNames);
		table.setEnabled(false); // para no poder modificar la tabla
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

	public JPanel createInstructionPanel(final RobotEngine engine) {
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
					}
				});
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
					}
				});
			}

		};
		JButton pick = new JButton("PICK") {
			{
				this.addMouseListener(new OyenteRaton() {

					@Override
					public void mouseClicked(MouseEvent e) {
						engine.communicateRobot(new PickInstruction(objectId));
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
					}
				});
			}

		};
		JButton operate = new JButton("OPERATE") {
			{
				this.addMouseListener(new OyenteRaton() {

					@Override
					public void mouseClicked(MouseEvent e) {
						engine.communicateRobot(new OperateInstruction());
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
		objectToPick.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				objectId = objectToPick.getText().toString();
				// System.out.println(rotacion);
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
		
		objectId = objectToPick.getName();
		
		return instructionPanel;

	}
	private JTextField objectToPick;
	private String rotacion;
	private String objectId;
	private JComboBox directionToTurn;
	JFormattedTextField robotInfo;

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub

	}

}
