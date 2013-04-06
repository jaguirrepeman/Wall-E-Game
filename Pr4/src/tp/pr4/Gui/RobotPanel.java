package tp.pr4.Gui;

import java.awt.BorderLayout;
import java.awt.LayoutManager;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class RobotPanel extends JPanel {
	public RobotPanel(){
		this.setLayout(new BorderLayout(10, 10));
		
		JPanel instructionPanel = new JPanel();
		this.add(instructionPanel, BorderLayout.WEST);
		//setVisible(true);
		//JScrollPanel
		JPanel dataPanel = new JPanel();
		this.add(dataPanel);
	}
}
