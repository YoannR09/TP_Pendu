package fr.yoannroche.pendu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;




public class Classement extends Jeu {
	
	
private JPanel cadre = new JPanel ();

private int [] policeSize = { 25 , 15 , 10 };
Font normal = new Font ("arial", Font.BOLD, 10);

	public Classement(Dimension dim) {
		super(dim);
		
		
	}



	protected void initPanel() {
		

		Font police = new Font("Impact", Font.BOLD,40);
		Font normal = new Font ("arial", Font.BOLD, 10);
		
		
		
		this.panel.setBackground(Color.getHSBColor(0.0400f, 0.54f, 0.80f));
		this.panel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		JTextArea fichierC = new JTextArea(readFile("src/fr/yoannroche/pendu/score.txt"));
		fichierC.setBackground(Color.gray);
		fichierC.setPreferredSize(new Dimension (150,300));
		fichierC.setBorder(BorderFactory.createLineBorder(Color.black));
		fichierC.setFont(normal);
		fichierC.setEditable(false);
		fichierC.setAlignmentX(JTextArea.CENTER_ALIGNMENT);
		
		this.panel.add(fichierC, BorderLayout.SOUTH);
	}



	private String readFile(String adresse) {
		String phrases ="";
		String ligne;
		try
		{
			BufferedReader reader = new BufferedReader(new FileReader("src/fr/yoannroche/pendu/score.txt"));
			while((ligne=reader.readLine()) != null)
{
	phrases+=ligne+"\n";
}
			
		}
		catch(Exception e)
		{
			
		}
		return phrases;
	}

}
