package tp.pr4.Gui;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class NavigationPanel extends JPanel {
	public NavigationPanel(){
		this.setLayout(new BorderLayout(10, 10));
		JLabel mapViewPanel = new JLabel("Aquí va la imagen del puto WALLE");
		this.add(mapViewPanel, BorderLayout.NORTH);
		setVisible(true);
		//JScrollPanel
		JTextArea text = new JTextArea();
		this.add(text, BorderLayout.SOUTH);
	}
}
