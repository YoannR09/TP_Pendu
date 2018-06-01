package fr.yoannroche.pendu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;




public class Classement extends Panelall {
	
	
private JPanel cadre = new JPanel ();

private int [] policeSize = { 25 , 15 , 10 };

	public Classement(Dimension dim) {
		super(dim);
		initPanel();
		
	}



	protected void initPanel() {
		
		this.panel.setBackground(Color.getHSBColor(0.0400f, 0.54f, 0.80f));
		this.panel.add(cadre,BorderLayout.CENTER);
		this.panel.setBorder(BorderFactory.createLineBorder(Color.black));
		cadre.setPreferredSize(new Dimension (540,490));
		cadre.setBorder(BorderFactory.createLineBorder(Color.black));
		cadre.setBackground(Color.gray);
		
		}

}
