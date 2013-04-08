package tp.pr4.Gui;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;

import tp.pr4.Place;

public class PlaceCell extends JButton {
	public PlaceCell(){
		//con JColorChooser parece que se cambia el color, hay que mirarlo
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
	private Place place;
}
