package tp.pr5.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class CloseApp implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {

		int seleccion = JOptionPane.showOptionDialog(null, "Select an option",
				"Exit WALL·E", JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, // null para icono por
													// defecto.
				null, // null para YES, NO y CANCEL, si no: new Object[] {
						// "opcion 1", "opcion 2", "opcion 3" }
				"null");

		if (seleccion == -1 || seleccion == 0)
			System.exit(0);

		else
			System.out.println("seleccionada opcion " + (seleccion + 1));

	}

	public static void requestQuit(String quitReason) {
		JOptionPane.showMessageDialog(null, quitReason, "End of the game",
				JOptionPane.OK_OPTION,
				CityPanel.createImageIcon("images/2WALLExit.png", "WALLE"));
		System.exit(0);

	}
	
	public static void quitApp(){
		int seleccion = JOptionPane.showOptionDialog(null,
				null, "Exit WALL·E",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
				CityPanel.createImageIcon("images/2WALLExit.png", "WALLE"), 
					new Object[] { "YES", "NO"},"null");

		if (seleccion == -1 || seleccion == 0) System.exit(0);
	}

}
