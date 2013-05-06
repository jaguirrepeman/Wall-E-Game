package tp.pr5.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTextArea;

import tp.pr5.Place;

@SuppressWarnings("serial")
public class PlaceCell extends JButton {
	
	public PlaceCell(final JTextArea text){
		//con JColorChooser parece que se cambia el color, hay que mirarlo
		
		actual = true;
		this.setFocusPainted(actual);

		this.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (place != null)
					text.setText(place.toString());
				else
					text.setText("This place hasn't been explored yet.");
			}

		});

	}
	
	public PlaceCell setPlace(Place place){
		
		this.place = place;
		this.setText(place.getName()); 
		return this;
	}
	
	public boolean isActual() {


		return actual;
	}
	
	public boolean isSpaceShip(){
		
		return this.place.isSpaceship();
	}

	public void setActual(boolean actual) {
		this.actual = actual;
		if (actual)this.setBackground(Color.GREEN);
		else this.setBackground(Color.GRAY);
	}


	private boolean actual;
	private Place place;
}
