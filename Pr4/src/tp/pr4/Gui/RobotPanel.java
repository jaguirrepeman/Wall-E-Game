package tp.pr4.Gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import tp.pr4.Direction;
import tp.pr4.RobotEngine;
import tp.pr4.Rotation;

@SuppressWarnings("serial")
public class RobotPanel extends JPanel {

	public RobotPanel(RobotEngine engine) {
		this.setLayout(new BorderLayout(10, 10));

		// setVisible(true);
		// JScrollPanel
		JPanel instructionPanel = createInstructionPanel();
		this.add(instructionPanel, BorderLayout.WEST);

		TitledBorder titled = new TitledBorder("Robot Info");
		JPanel dataPanel = new JPanel();
		// aqui seguramente habria que hacer lo de las clases internas que sale
		// en los apunntes

		JPanel statusPanel = new JPanel();
		statusPanel.setLayout(new FlowLayout());
		Font font = new Font("Verdana", Font.ITALIC, 12);
		JFormattedTextField robotInfo = new JFormattedTextField("Fuel: " + engine.getFuel() + " Recycled: " + engine.getRecycledMaterial());
		robotInfo.setFont(font);
		robotInfo.setEditable(false);
		statusPanel.add(robotInfo);

		dataPanel.add(statusPanel, BorderLayout.CENTER);
		
		JScrollPane tabla = new JScrollPane();
		dataPanel.add(tabla, BorderLayout.SOUTH);
		dataPanel.setBorder(titled);

		this.add(dataPanel, BorderLayout.CENTER);
	}

	public JPanel createInstructionPanel() {
		JPanel instructionPanel = new JPanel();
		TitledBorder titled = new TitledBorder("Instructions");
		instructionPanel.setBorder(titled);
		instructionPanel.setLayout(new GridLayout(4, 2, 3, 3));
		JButton move = new JButton("MOVE"), quit = new JButton("QUIT"), turn = new JButton("TURN"), 
				pick = new JButton("PICK"), drop = new JButton("DROP"), operate = new JButton("OPERATE");
		JTextField objectToPick = new JTextField();
		String[] rotationString = {Rotation.LEFT.toString(), Rotation.RIGHT.toString()};
		JComboBox directionToTurn = new JComboBox(rotationString);
		directionToTurn.setSelectedIndex(1);

		instructionPanel.add(move);
		instructionPanel.add(quit);
		instructionPanel.add(turn);
		instructionPanel.add(directionToTurn);
		instructionPanel.add(pick);
		instructionPanel.add(objectToPick);
		instructionPanel.add(drop);
		instructionPanel.add(operate);
		return instructionPanel;

	}
}
