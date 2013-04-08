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
public class RobotPanel extends JPanel {

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
		JFormattedTextField robotInfo = new JFormattedTextField("Fuel: " + engine.getFuel() + " Recycled: " + engine.getRecycledMaterial());
		robotInfo.setBorder(null);
		robotInfo.setFont(font);
		robotInfo.setEditable(false);
		statusPanel.add(robotInfo);

		dataPanel.add(statusPanel, BorderLayout.CENTER);
		
		//JScrollPane tabla = new JScrollPane();

        String[] columnNames = {"id", "Description"};

        Object[][] data = {
	    {"Newspapers", "News on sport"},
        };

        final JTable table = new JTable(data, columnNames);
        table.setEnabled(false);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
		
        JScrollPane tableScrollPane = new JScrollPane(table);
		dataPanel.add(tableScrollPane, BorderLayout.SOUTH);
		dataPanel.setBorder(titled);
		
		this.add(dataPanel, BorderLayout.CENTER);
		
		
		rotacion = directionToTurn.getSelectedItem().toString();
	}

	public JPanel createInstructionPanel(final RobotEngine engine) {
		JPanel instructionPanel = new JPanel();
		TitledBorder titled = new TitledBorder("Instructions");
		instructionPanel.setBorder(titled);
		instructionPanel.setLayout(new GridLayout(4, 2, 3, 3));
		JButton move = new JButton("MOVE") {
			{
				this.addMouseListener(new MouseListener() {

					@Override
					public void mouseClicked(MouseEvent e) {
						engine.communicateRobot(new MoveInstruction());
					}

					@Override
					public void mousePressed(MouseEvent e) {}
					@Override
					public void mouseReleased(MouseEvent e) {}
					@Override
					public void mouseEntered(MouseEvent e) {}
					@Override
					public void mouseExited(MouseEvent e) {}
				});
			}
			
		};
		
		
		JButton quit = new JButton("QUIT"){
			{
				this.addMouseListener(new MouseListener() {

					@Override
					public void mouseClicked(MouseEvent e) {
						engine.communicateRobot(new QuitInstruction());
					}

					@Override
					public void mousePressed(MouseEvent e) {}
					@Override
					public void mouseReleased(MouseEvent e) {}
					@Override
					public void mouseEntered(MouseEvent e) {}
					@Override
					public void mouseExited(MouseEvent e) {}
				});
			}
			
		};
		JButton turn = new JButton("TURN"){
			{
				this.addMouseListener(new MouseListener() {

					@Override
					public void mouseClicked(MouseEvent e) {
						engine.communicateRobot(new TurnInstruction(rotacion));
					}

					@Override
					public void mousePressed(MouseEvent e) {}
					@Override
					public void mouseReleased(MouseEvent e) {}
					@Override
					public void mouseEntered(MouseEvent e) {}
					@Override
					public void mouseExited(MouseEvent e) {}
				});
			}
			
		};
		JButton	pick = new JButton("PICK"){
			{
				this.addMouseListener(new MouseListener() {

					@Override
					public void mouseClicked(MouseEvent e) {
						engine.communicateRobot(new PickInstruction());
					}

					@Override
					public void mousePressed(MouseEvent e) {}
					@Override
					public void mouseReleased(MouseEvent e) {}
					@Override
					public void mouseEntered(MouseEvent e) {}
					@Override
					public void mouseExited(MouseEvent e) {}
				});
			}
			
		};
		JButton drop = new JButton("DROP"){
			{
				this.addMouseListener(new MouseListener() {

					@Override
					public void mouseClicked(MouseEvent e) {
						engine.communicateRobot(new DropInstruction());
					}

					@Override
					public void mousePressed(MouseEvent e) {}
					@Override
					public void mouseReleased(MouseEvent e) {}
					@Override
					public void mouseEntered(MouseEvent e) {}
					@Override
					public void mouseExited(MouseEvent e) {}
				});
			}
			
		};
		JButton operate = new JButton("OPERATE"){
			{
				this.addMouseListener(new MouseListener() {

					@Override
					public void mouseClicked(MouseEvent e) {
						engine.communicateRobot(new OperateInstruction());
					}

					@Override
					public void mousePressed(MouseEvent e) {}
					@Override
					public void mouseReleased(MouseEvent e) {}
					@Override
					public void mouseEntered(MouseEvent e) {}
					@Override
					public void mouseExited(MouseEvent e) {}
				});
			}
			
		};
		JTextField objectToPick = new JTextField();
		String[] rotationString = {Rotation.LEFT.toString(), Rotation.RIGHT.toString()};
		directionToTurn = new JComboBox(rotationString);
		directionToTurn.setSelectedIndex(1);
		directionToTurn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				rotacion = directionToTurn.getSelectedItem().toString();
				//System.out.println(rotacion);
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
		return instructionPanel;

	}OyenteRaton ratonPerez;
	String rotacion;
	JComboBox directionToTurn;
}
