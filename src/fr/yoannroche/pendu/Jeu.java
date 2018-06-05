package fr.yoannroche.pendu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;



public class Jeu extends Panelall{

	char  [] carac = {'A','Z','E','R','T','Y','U','I','O','P','Q','S','D','F','G','H','J','K','L','M','W','X','C','V','B','N'};
	JButton [] button = new JButton[carac.length];
	static Dimension buttonDimension = new Dimension(50,30);
	private JButton bouton[];
	private JProgressBar bar ;
	private Thread t;
	private JButton launch ;
	private String word = "", secretWord = "";
	private int point ;
	private int nombreC = 0;
	private int pointMarque ;
	private int nombreE = 0 ;
	private String topC = "" , topP = "" , topM = "";
	JLabel image = new JLabel () ;
	String nom ;
	
	private ImageIcon pendu0 = new ImageIcon("src/fr/yoannroche/pendu/images/pendu0.png");
	private ImageIcon pendu1 = new ImageIcon("src/fr/yoannroche/pendu/images/pendu1.png");
	private ImageIcon pendu2 = new ImageIcon("src/fr/yoannroche/pendu/images/pendu2.png");
	private ImageIcon pendu3 = new ImageIcon("src/fr/yoannroche/pendu/images/pendu3.png");
	private ImageIcon pendu4 = new ImageIcon("src/fr/yoannroche/pendu/images/pendu4.png");
	private ImageIcon pendu5 = new ImageIcon("src/fr/yoannroche/pendu/images/pendu5.png");
	private ImageIcon pendu6 = new ImageIcon("src/fr/yoannroche/pendu/images/pendu6.png");
	private ImageIcon pendu7 = new ImageIcon("src/fr/yoannroche/pendu/images/pendu7.png");






	JLabel motSecret = new JLabel () ;
	JLabel motTrouver = new JLabel ();

	JLabel nombreMot = new JLabel ();
	JLabel score = new JLabel () ;

	/**
	 * Création des JPanel qui seront utilisés dans notre class Jeu
	 */
	JPanel ecranSI = new JPanel ();
	JPanel clavier = new JPanel ();
	JPanel clavierV = new JPanel ();
	JPanel clavierP = new JPanel ();

	/**
	 * Création des JLabel qui seront utilisés dans notre class Jeu
	 */

	JLabel imgP = new JLabel () ;

	BoutonListener boutonA = new BoutonListener ();
	SourisListener bsListener = new SourisListener ();
	Font normal = new Font ("arial", Font.BOLD, 13);
	Font motCA = new Font ("arial Solid", Font.BOLD, 30);


	/**
	 * Contructeur avec le parametre Dimension
	 * On itinialise le JPanel 
	 * @param dim
	 */

	
	public Jeu(Dimension dim){

		super(dim);
		top();
		initMot();
		initPanel();


	}


	/**
	 * Méthode qui contient le Panel de notre class Jeu
	 */
	protected void initPanel() {


		this.panel.setPreferredSize(new Dimension(750,750));
		this.panel.setBackground(Color.DARK_GRAY);



		/**
		 * Ajout d'un text pour le bouton lancer
		 */
		JTextArea lancer = new JTextArea ();
		lancer.setFont(normal);
		lancer.setText("\n     Cliquez sur le bouton LANCER    \n" + "\n         pour lancer une partie !\n    " + "\n     Vous aurez alors 60 secondes pour trouver le mot sercret      \n\n   ");
		lancer.setBackground(Color.DARK_GRAY);
		lancer.setForeground(Color.white);
		lancer.setEditable(false);
		lancer.setBorder(BorderFactory.createLineBorder(Color.black));
		clavierV.add(lancer,BorderLayout.CENTER);

		/**
		 * Ajout d'un texte en cas des 60 secondes passées 
		 */
		JTextArea loose = new JTextArea () ;
		loose.setFont(normal);
		loose.setBackground(Color.DARK_GRAY);
		loose.setForeground(Color.white);
		loose.setEditable(false);
		loose.setBorder(BorderFactory.createLineBorder(Color.black));
		loose.setText(" \n\n    Vous avez perdu           \n "  + " \n\n   Relancez la partie en cliquant sur NEW      \n \n");
		clavierP.add(loose,BorderLayout.CENTER);

		/**
		 * Création des boutons du clavier 
		 * Ajout d'un MooseListener pour un changement de bordure blanc/noir.
		 * Ajout d'un ActionListener pour �crire les lettres choisis dans l'ecran
		 * 
		 */
		Dimension buttonDimension = new Dimension(50,40);
		this.bouton = new JButton[26];
		int i = 0;
		for(char c : carac){
			this.bouton[i] = new JButton(String.valueOf(c).toUpperCase());

			bouton[i].setPreferredSize(buttonDimension);
			bouton[i].setBackground(Color.DARK_GRAY);
			bouton[i].setBorder(BorderFactory.createLineBorder(Color.black));
			bouton[i].setForeground(Color.white);
			bouton[i].addMouseListener(bsListener);
			bouton[i].addActionListener(boutonA);

			clavier.add(bouton[i]).setEnabled(true);
			i++;
		}

		JPanel imageE = new JPanel ();
		JPanel imgP = new JPanel ();
		JPanel scoreP = new JPanel ();
		JPanel motE = new JPanel ();

		/**
		 * Ajout des affichages clavier avant lancement partie , pendant la partie et aprés.
		 */

		this.panel.add(ecranSI,BorderLayout.NORTH);
		this.panel.add(clavier,BorderLayout.SOUTH);
		this.panel.add(clavierV,BorderLayout.SOUTH);
		this.panel.add(clavierP,BorderLayout.SOUTH);

		clavierP.setVisible(false);

		ecranSI.setBackground(Color.white);
		ecranSI.add(motE);
		ecranSI.add(imageE);
		imageE.add(imgP);
		imageE.add(scoreP);

		JPanel motP = new JPanel ();
		JPanel progress = new JPanel ();
		JPanel startb = new JPanel ();
		JPanel top1 = new JPanel ();

		this.motSecret.setFont(motCA);
		this.motSecret.setText("_ _ _ _ _ _ _ _" );
		this.motSecret.setForeground(Color.white);
		this.motSecret.setHorizontalAlignment(JLabel.CENTER);	


		motP.add(motSecret,BorderLayout.CENTER);

		motP.setPreferredSize(new Dimension(320,150));
		motP.setBackground(Color.DARK_GRAY);
		motP.setBorder(BorderFactory.createLineBorder(Color.black));
		motE.add(motP);

		progress.setPreferredSize(new Dimension(320,52));
		progress.setBackground(Color.getHSBColor(0.0400f, 0.54f, 0.80f));
		progress.setBorder(BorderFactory.createLineBorder(Color.black));
		t = new Thread(new Traitement());

		bar = new JProgressBar();

		bar.setMaximum(6000);
		bar.setMinimum(0);
		bar.setStringPainted(false);
		bar.setPreferredSize(new Dimension(300,40));

		bar.setForeground(Color.getHSBColor(0.589f, 0.77f, 1.0f));
		bar.setBorder(BorderFactory.createLineBorder(Color.black));

		progress.add(bar);
		motE.add(progress);

		startb.setPreferredSize(new Dimension(70,45));
		startb.setBackground(Color.DARK_GRAY);
		startb.setBorder(BorderFactory.createLineBorder(Color.black));
		launch = new JButton(new ImageIcon("src/fr/yoannroche/pendu/images/lancer.png"));
		launch.setBorder(BorderFactory.createLineBorder(Color.black));
		launch.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				t = new Thread(new Traitement());
				t.start(); 
				launch.setEnabled(false);
				clavier.setVisible(true);
				clavierV.setVisible(false);
				nombreErreurs ();


			}
		});


		startb.add(launch);

		motE.add(startb);


		top1.setPreferredSize(new Dimension(250,80));

		top1.setBackground(Color.getHSBColor(0.133f, 0.38f, 0.93f));
		top1.setBorder(BorderFactory.createRaisedBevelBorder());
		top1.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.DARK_GRAY));

		JLabel rank = new JLabel ();
		JLabel pts = new JLabel ();
		JLabel nmot = new JLabel ();
		rank.setText("Meilleur joueur :  " + topC );
		rank.setHorizontalAlignment(JLabel.CENTER);
		rank.setPreferredSize(new Dimension(300,17));
		pts.setText("Nombre de points : "  + topP );
		pts.setPreferredSize(new Dimension(300,17));
		pts.setHorizontalAlignment(JLabel.CENTER);

		nmot.setText("Nombres de mots trouvés : " + topM );
		nmot.setPreferredSize(new Dimension(300,17));
		nmot.setHorizontalAlignment(JLabel.CENTER);


		top1.add(rank);
		top1.add(pts);
		top1.add(nmot);
		motE.add(top1);

		clavier.setVisible(false);
		clavier.setPreferredSize(new Dimension (580,150));
		clavier.setBackground(Color.getHSBColor(0.0400f, 0.54f, 0.80f));
		clavier.setBorder(BorderFactory.createLineBorder(Color.black));
		clavierV.setVisible(true);
		clavierV.setPreferredSize(new Dimension (580,150));
		clavierV.setBackground(Color.getHSBColor(0.0400f, 0.54f, 0.80f));
		clavierV.setBorder(BorderFactory.createLineBorder(Color.black));
		clavierP.setPreferredSize(new Dimension (580,150));
		clavierP.setBorder(BorderFactory.createLineBorder(Color.black));
		clavierP.setBackground(Color.getHSBColor(0.0400f, 0.54f, 0.80f));


		ecranSI.setPreferredSize(new Dimension(580,370));
		ecranSI.setBorder(BorderFactory.createLineBorder(Color.black));
		ecranSI.setBackground(Color.LIGHT_GRAY);

		motE.setPreferredSize(new Dimension(330,355));
		motE.setBorder(BorderFactory.createLineBorder(Color.black));
		motE.setBackground(Color.gray);

		imageE.setPreferredSize(new Dimension(230,355));
		imageE.setBorder(BorderFactory.createLineBorder(Color.black));
		imageE.setBackground(Color.getHSBColor(0.0400f, 0.54f, 0.80f));

		image.setIcon(pendu0);
		imgP.setPreferredSize(new Dimension(220,260));
		imgP.setBorder(BorderFactory.createLineBorder(Color.black));
		imgP.setBackground(Color.DARK_GRAY);
		imgP.add(image,BorderLayout.CENTER);

		scoreP.setPreferredSize(new Dimension(220,65));

		scoreP.setBorder(BorderFactory.createLineBorder(Color.black));
		scoreP.setBackground(Color.DARK_GRAY);

		this.nombreMot = new JLabel();
		this.score = new JLabel();
		this.nombreMot.setText("Nombre de mots trouvés : 0" );
		this.nombreMot.setPreferredSize(new Dimension(300, 20));
		this.nombreMot.setHorizontalAlignment(JLabel.CENTER);
		this.nombreMot.setForeground(Color.white);
		this.nombreMot.setFont(normal);
		scoreP.add(this.nombreMot,BorderLayout.CENTER);

		this.score.setText("Votre score :  "+ point);
		this.score.setPreferredSize(new Dimension(300, 20));
		this.score.setHorizontalAlignment(JLabel.CENTER);
		this.score.setForeground(Color.white);
		this.score.setFont(normal);
		scoreP.add(this.score,BorderLayout.CENTER);

	}

	class SourisListener implements MouseListener {


		public void mouseClicked(MouseEvent arg0) {
			((JButton)arg0.getSource()).setEnabled(false);

		}

		public void mouseEntered(MouseEvent arg0) {

		}

		public void mouseExited(MouseEvent arg0) {

		}

		public void mousePressed(MouseEvent arg0) {

		}

		public void mouseReleased(MouseEvent arg0) {
		}

	}


	class Traitement implements Runnable{   
		public void run(){


			for(int val = 0; val <= 6000; val++){
				motSecret.setText(secretWord);

				bar.setValue(val);
				if(bar.getValue()== 6000) {
					t.stop();
					clavier.setVisible(false);
					clavierV.setVisible(false);
					clavierP.setVisible(true);
					clavierV.setVisible(false);
					JOptionPane.showMessageDialog(null,
							"Désolé, vous avez perdu!\n" + "Le mot était : "+word +
							"\n\n\tVous n'avez  pas assez de points pour enregistrer votre score.          \n" ,

							"Vous avez perdu", JOptionPane.NO_OPTION);

				}
				try {
					t.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}


		}


	}
	/**
	 * 
	 * @author yoann
	 *
	 */
	class BoutonListener implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			
			char proposition = ((JButton)arg0.getSource()).getText().charAt(0);

			boolean mot_Fini = false;
			boolean lettre_ok = false;
			char [] charArray = word.toCharArray();
			char [] charArray2 = secretWord.toCharArray();
			int lettre_trouver = 0 ;
			
			
			for(int i = 0 ; i < charArray.length ; i++ ) {
				
				char lettre_find = word.charAt(i);
				if(proposition == lettre_find) {
					lettre_ok = true ;
					if(lettre_ok == true ) {
						char lettreV = charArray2[i];
						char lettreK = charArray[i];
						lettreV = lettreK ;
						
					    charArray2[i] = lettreV ;
					    String str = new String(charArray2);
					   secretWord=str;
					   lettre_trouver = +i ;
					   char trait = '-' ;
					   boolean trouve = (secretWord.indexOf(trait) != -1);
					   if(trouve == false) {
						   mot_fini();
					}
				
				}
				
				}
				
				}
			
				
	
			
			System.out.println(nombreC);
			if(lettre_ok == false) {
				++nombreC;
			++nombreE;
			nombreErreurs();
			
			}
			
			}
			
			}


		

		


	

	public void initMot(){

		int i = (int)(Math.random() * 100000);
		while(i > 336529){
			i /= 2;
		}

		try {
			LineNumberReader fnr = new LineNumberReader(new FileReader(new File("src/fr/yoannroche/pendu/dictionnaire.txt")));
			int carac;
			word = "";
			secretWord= "";
			while((carac = fnr.read()) != -1){
				if(fnr.getLineNumber() == (i+1))
					break;

				else{
					if(fnr.getLineNumber() == i){
						word += (char)carac;						
					}
				}
			}

			word = word.trim().toUpperCase();
			for(int j = 0; j < this.word.length(); j++)
			{
				secretWord +=  "-" ;
			}


			fnr.close();
			nombreC = 0;
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Erreur !", "ERREUR", JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erreur !", "ERREUR", JOptionPane.ERROR_MESSAGE);
		}




	}

	public void nombreErreurs () {

		if (nombreE == 1 ) {
			image.setIcon(pendu1);
		}
		if (nombreE == 2 ) {
			image.setIcon(pendu2);
		}
		if (nombreE == 3 ) {
			image.setIcon(pendu3);
		}
		if (nombreE == 4 ) {
			image.setIcon(pendu4);

		}
		if (nombreE == 5 ) {
			image.setIcon(pendu5);

		}
		if (nombreE == 6 ) {
			image.setIcon(pendu6);
		}
		if (nombreE == 7 ) {
			image.setIcon(pendu7);
			JOptionPane.showMessageDialog(null,
					"Désolé, vous avez perdu !\n" + "Le mot était :" + word +
					"\n\n\tVous n'avez pas assez de points pour enregistrer votre score.                \n" ,

					"Vous avez perdu", JOptionPane.NO_OPTION);
			clavier.setVisible(false);
			clavierV.setVisible(false);
			clavierP.setVisible(true);
			t.stop();
		}

	}

	public void top () {
		try {
			LineNumberReader premier = new LineNumberReader(new FileReader(new File("src/fr/yoannroche/pendu/score.txt")));
			int carac ;
			topC = "";
			while((carac = premier.read()) != -1){
				if(premier.getLineNumber() == (2))
					break;

				else{
					if(premier.getLineNumber() == 0){
						topC += (char)carac;						
					}
				}
			}

			premier.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Erreur !", "ERREUR", JOptionPane.ERROR_MESSAGE);

		} catch (IOException e) {

			e.printStackTrace();
		}
		try {
			LineNumberReader premier = new LineNumberReader(new FileReader(new File("src/fr/yoannroche/pendu/score.txt")));
			int carac ;
			topP = "";
			while((carac = premier.read()) != -1){
				if(premier.getLineNumber() == (3))
					break;

				else{
					if(premier.getLineNumber() == 1){
						topP += (char)carac;						
					}
				}
			}


			premier.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Erreur !", "ERREUR", JOptionPane.ERROR_MESSAGE);

		} catch (IOException e) {

			e.printStackTrace();
		}

		try {
			LineNumberReader premier = new LineNumberReader(new FileReader(new File("src/fr/yoannroche/pendu/score.txt")));
			int carac ;
			topM = "";
			while((carac = premier.read()) != -1){
				if(premier.getLineNumber() == (3))
					break;

				else{
					if(premier.getLineNumber() == 2){
						topM += (char)carac;						
					}
				}
			}

			premier.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Erreur !", "ERREUR", JOptionPane.ERROR_MESSAGE);

		} catch (IOException e) {

			e.printStackTrace();
		}



}
	public void mot_fini(){
		t.stop();
		JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
	    String nom = jop.showInputDialog(null, "Veuillez écrire votre pseudo !", "Gagné", JOptionPane.QUESTION_MESSAGE);
	    jop2.showMessageDialog(null, "Votre nom est " + nom,"Pseudo", JOptionPane.INFORMATION_MESSAGE);
	    nombreCoup ();
	    ObjectInputStream ois;
	    ObjectOutputStream oos;
	    try {
	      oos = new ObjectOutputStream(
	              new BufferedOutputStream(
	                new FileOutputStream(
	                  new File("src/fr/yoannroche/pendu/score.txt"))));
	        	
	      oos.writeObject(point);
	      oos.writeObject(nom);
	    
	} catch (FileNotFoundException e) {
	      e.printStackTrace();
	    } catch (IOException e) {
	      e.printStackTrace();
	    }     	
	  }
	      
	    
	    
	    
	    
	  
	public void nombreCoup () {

		if (nombreC == 0 ) {
			point = +100 ;
		}
		if (nombreC == 1 ) {
			point = +50 ;
		}
		if (nombreC == 2 ) {
			point = +35 ;
		}
		if (nombreC == 3 ) {
			point = +25 ;
		}
		if (nombreC == 4 ) {
			point = +15 ;

		}
		if (nombreC == 5 ) {
			point = +10 ;

		}
		if (nombreC == 6 ) {
			point = +5 ;
		}
		if (nombreC == 7 ) {
			point = 0 ;
		}
	
	}
}

