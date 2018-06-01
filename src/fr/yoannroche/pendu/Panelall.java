package fr.yoannroche.pendu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Panel;

import javax.swing.JPanel;

public abstract class Panelall {
	protected JPanel panel;
	
	public Panelall (Dimension dim) {
		this.panel = new JPanel ();
		this.panel.setPreferredSize(dim);
		this.panel.setBackground(Color.white);
		
		
	}
	protected JPanel  getPanel () {
		return this.panel;
	}

	protected abstract void initPanel ();
}
