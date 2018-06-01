package fr.yoannroche.pendu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Home extends Panelall {
	
	public Home(Dimension dim) {
		super(dim);
		initPanel();
	}
	public void initPanel () {
		this.panel.add(new JLabel(new ImageIcon("src/fr/yoannroche/pendu/images/pendu.png")), BorderLayout.CENTER);
		
	}
		

	 
}
	