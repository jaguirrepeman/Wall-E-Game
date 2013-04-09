package tp.pr4.Gui;

import java.awt.event.MouseEvent;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JTextArea;

import tp.pr4.Place;

public class PlaceCell extends JButton {
	
	public PlaceCell(final JTextArea text){
		//con JColorChooser parece que se cambia el color, hay que mirarlo
		actual = true;
		this.setFocusPainted(actual);
		
				this.addMouseListener(new OyenteRaton(){

					@Override
					public void mouseClicked(MouseEvent e) {
						if (place != null) text.setText(place.toString());
						else text.setText("This place hasn't been explored yet.");
					}
					
				});
		
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
