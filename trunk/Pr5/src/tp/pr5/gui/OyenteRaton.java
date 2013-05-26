package tp.pr5.gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
public abstract class OyenteRaton implements MouseListener {

		@Override
	abstract public void mouseClicked(MouseEvent e);

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}



}
