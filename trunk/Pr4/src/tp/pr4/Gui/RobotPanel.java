package tp.pr4.Gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class RobotPanel extends JPanel {
	public RobotPanel(){
		this.setLayout(new BorderLayout(10, 10));
		
				//setVisible(true);
		//JScrollPanel
		JPanel instructionPanel = createInstructionPanel();
		this.add(instructionPanel, BorderLayout.WEST);

		JPanel dataPanel = new JPanel();
		//aqui seguramente habria que hacer lo de las clases internas que sale en los apunntes
		
		JPanel statusPanel = new JPanel();
		statusPanel.setLayout(new FlowLayout());
		
		JScrollPane tabla = new JScrollPane();
		dataPanel.add(tabla, BorderLayout.SOUTH);
		dataPanel.add(statusPanel, BorderLayout.CENTER);
		
		this.add(dataPanel, BorderLayout.CENTER);
	}
	public JPanel createInstructionPanel(){
		
		JPanel instructionPanel = new JPanel();
		instructionPanel.setLayout(new GridLayout(4, 2));
		JButton move = new JButton("MOVE"), quit = new JButton("QUIT"), turn = new JButton("TURN"), pick = new JButton("PICK"), drop = new JButton("DROP"), operate = new JButton("OPERATE");
		JTextField objectToPick = new JTextField();
		JComboBox directionToTurn = new JComboBox();
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
