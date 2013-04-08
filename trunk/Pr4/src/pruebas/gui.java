package pruebas;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.*;

import tp.pr4.Gui.CloseApp;

@SuppressWarnings("serial")

public class gui extends JFrame {
	public gui() {
		super("Pikey Window");
		this.setSize(320, 200);
		
		this.setLayout(new GridLayout(3,4));
		//setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//JButton boton = new JButton("They aren't pikeys, are they?");
		//JButton boton2 = new JButton("No pulsar");
		//JButton boton3 = new JButton("Hola");
		
		for (int i=1; i<=10; i++){
			JButton button = new JButton(i%10+"");
			this.add(button);
		}
		this.add(new JButton("START"));
		JButton button = new JButton("STOP");
		this.add(button);
		
		CloseApp oBoton = new CloseApp();
		button.addActionListener(oBoton);
		//this.add(boton);
		//this.add(boton2);
		//this.add(boton3);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			gui Gui = new gui();
			Gui.setVisible(true);
			
	}

}
