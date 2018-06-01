package fr.yoannroche.pendu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import fr.yoannroche.pendu.Panelall;

public  class Rules extends Panelall{
	
	

	public Rules (Dimension dim){
		super(dim);
		initPanel();
	}
	
	protected void initPanel(){
		
		Font police = new Font("Impact", Font.BOLD,40);
		Font normal = new Font ("arial", Font.BOLD, 10);
		JLabel titre = new JLabel();
		titre.setFont(police);
		titre.setText("Le jeu du pendu");
		titre.setHorizontalAlignment(JLabel.CENTER);
		
		this.panel.add(titre, BorderLayout.CENTER);
		this.panel.setBackground(Color.getHSBColor(0.0400f, 0.54f, 0.80f));
		this.panel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		
		JTextArea accueil = new JTextArea();
		accueil.setBackground(Color.gray);
		accueil.setPreferredSize(new Dimension (550,400));
		accueil.setBorder(BorderFactory.createLineBorder(Color.black));
		accueil.setText(	"\n\n\n  Vous avez sept coups pour trouver le mot caché. Si vous r�ussissez, on recommence !\n" +
							"  Plus vous trouvez de mots, plus votre score augmente. Alors, é vous de jouer !\n" +
							"\n\n  COMPTE DES POINTS :\n\n\tMot trouvé sans erreur\t\t100 pts\n\tMot trouvé avec une erreur\t                        50 pts\n" +
							"\tMot trouvé avec deux erreurs\t35 pts\n\tMot trouvé avec trois erreurs\t25 pts\n\tMot trouvé avec quatre erreurs\t15 pts\n" +
							"\tMot trouvé avec cinq erreurs\t10 pts\n\tMot trouvé avec six erreurs\t                           5 pts\n\n\n" +
							"  Je vous souhaite bien du plaisir !\n  Si vous pensez pouvoir trouver un mot en un seul essai, c'est que vous croyez que le dictionnaire est petit.\n" +
							"  Or, pour votre information, il contient plus de 336 000 mots� Bonne chance ! ;)");
		accueil.setFont(normal);
		accueil.setEditable(false);
		this.panel.add(accueil, BorderLayout.SOUTH);
	}
		
		
		
	}


	




	
