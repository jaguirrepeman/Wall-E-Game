package tp.pr4.Gui;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;

public class PlaceCell extends JButton {
	public PlaceCell(){
		//hay que averigüar como leches se pone el color de fondo para el boton
		actual = true;
		this.setFocusPainted(actual);
		
	}
	
	
	public boolean isActual() {


		return actual;
	}


	public void setActual(boolean actual) {
		this.actual = actual;
	}


	private boolean actual;
}
