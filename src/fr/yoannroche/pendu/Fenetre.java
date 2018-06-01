package fr.yoannroche.pendu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;


import fr.yoannroche.pendu.Rules;

public class Fenetre extends JFrame{


	private JPanel container = new JPanel ();
	private JPanel centre = new JPanel ();
	private JPanel accueil = new JPanel ();
	JToolBar toolBar = new JToolBar ();
	private JButton nouveau = new JButton (new ImageIcon("src/fr/yoannroche/pendu/images/new.png"));
	private JButton classement = new JButton(new ImageIcon("src/fr/yoannroche/pendu/images/rank.png"));
	private JButton regle = new JButton(new ImageIcon("src/fr/yoannroche/pendu/images/rule.png"));
	private Dimension dim ;
	private Dimension dimh = new Dimension (550,500);


	private RuleListener rListener = new RuleListener ();
	private NewListener nListener = new NewListener ();
	private ClassementListener cListener = new ClassementListener ();

	/**
	 * Création de la fenêtre où sera stocké nos JPanel.
	 */
	public Fenetre () {
		this.setSize(600, 630);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);


		/**
		 * On initialise la méthode initAccueil et initToolBar pour génerer leurs JPanel dans la fenêtre.
		 */
		initAccueil();
		initToolBar();


		this.container.setPreferredSize(this.dim);
		this.setVisible(true);





		this.container.add(toolBar,BorderLayout.NORTH);
		this.container.add(centre,BorderLayout.CENTER);

		this.setContentPane(this.container);








	}
	/**
	 * Méthode pour définir le JPanel accueil.
	 */
	void initAccueil () {
		accueil.setPreferredSize(new Dimension(600,513));
		accueil.setBackground(Color.DARK_GRAY);
		accueil.setBorder(BorderFactory.createLineBorder(Color.black));
		accueil.add(new JLabel(new ImageIcon("src/fr/yoannroche/pendu/images/accueil.png")), BorderLayout.CENTER);
		centre.add(accueil,BorderLayout.CENTER);
	}

	/**
	 * Méthode pour définir le JPanel de la ToolBar.
	 * nouveau, classement et regle on était ajoutés 
	 */
	void initToolBar () {

		toolBar.addSeparator();



		toolBar.add(nouveau);
		nouveau.setBorder(BorderFactory.createLineBorder(Color.black));
		nouveau.addMouseListener(new SourisListener());

		toolBar.addSeparator();
		toolBar.addSeparator();

		toolBar.add(classement);
		classement.setBorder(BorderFactory.createLineBorder(Color.black));
		classement.addMouseListener(new SourisListener());


		toolBar.addSeparator();
		toolBar.addSeparator();

		toolBar.add(regle);
		regle.setBorder(BorderFactory.createLineBorder(Color.black));
		regle.addMouseListener(new SourisListener());



		nouveau.addActionListener(nListener);
		regle.addActionListener(rListener);
		classement.addActionListener(cListener);



		toolBar.setPreferredSize(new Dimension(600,40));
		toolBar.setBorder(BorderFactory.createLineBorder(Color.black));
		toolBar.setBackground(Color.getHSBColor(0.0400f, 0.54f, 0.80f));

	}














	/**
	 * class pour définir une MouseListener.
	 * Lorsque la souris passe sur le bouton celui ci s'affiche blanc.
	 * Lorsque la souris se retire du bouton celui ci s'affiche noir.
	 * @author yoann
	 *
	 */
	class SourisListener implements MouseListener{


		/**
		 * Affichage blanc des border au moment du mouseEntered		
		 */
		public void mouseEntered(MouseEvent arg0) {
			if(arg0.getSource()==nouveau)
				nouveau.setBorder(BorderFactory.createLineBorder(Color.white));
			if(arg0.getSource()==classement)
				classement.setBorder(BorderFactory.createLineBorder(Color.white));
			if(arg0.getSource()==regle)
				regle.setBorder(BorderFactory.createLineBorder(Color.white));


		}
		/**
		 * Affichage noir des border au moment du mouseExited
		 */
		public void mouseExited(MouseEvent arg0) {
			nouveau.setBorder(BorderFactory.createLineBorder(Color.black));
			classement.setBorder(BorderFactory.createLineBorder(Color.black));
			regle.setBorder(BorderFactory.createLineBorder(Color.black));

		}
		public void mousePressed(MouseEvent arg0) {
		}

		public void mouseReleased(MouseEvent arg0) {
		}
		public void mouseClicked(MouseEvent arg0) {	

		}

	}
	/**
	 * class pour retirer le JPanel centre et le remplacer par le JPanel Rule.
	 * @author yoann
	 *
	 */
	class RuleListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0){

			centre.removeAll();
			accueil.setVisible(false);
			centre.add(new Rules(dimh).getPanel(), BorderLayout.SOUTH);		
			centre.revalidate();
		}

	}
	/**
	 * class pour retirer le JPanel centre et le remplacer par le JPanel New
	 * @author yoann
	 *
	 */
	class NewListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {

			centre.removeAll();

			accueil.setVisible(false);
			centre.add(new Jeu(dimh).getPanel(), BorderLayout.SOUTH);

			centre.revalidate();

		}


	}
	class ClassementListener implements ActionListener {


		public void actionPerformed(ActionEvent arg0) {

			centre.removeAll();

			accueil.setVisible(false);
			centre.add(new Classement(dimh).getPanel(), BorderLayout.SOUTH);

			centre.revalidate();
		}

	}


	public void update(String mot, int pts, String imgPath, int nbreMot) {}
	public void restart(String word) {}

}

