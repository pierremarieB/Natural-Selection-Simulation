package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;



public class InformationsPane extends JPanel{
	/**
	 * Instance de la vue
	 */
	private View view;
	
	/**
	 * Label contenant l'iteration actuelle et la robestesse moyenne de l'environnement
	 */
	protected JLabel iteration, robustesse, couleurs;
	protected JLabel robustesseRED, robustesseYELLOW, robustesseGREEN, robustesseBLUE;
	protected JLabel pMortRED, pMortYELLOW, pMortGREEN, pMortBLUE;
	
	/**
	 * TextField pour les options: taille, caracteristique, pReprod
	 */
	protected JFormattedTextField taille, carac1, carac2, carac3, pReprod;
	
	public InformationsPane(View view){
		this.view = view;
		
		/*
		 * Mise en place de la bordure
		 */
		TitledBorder border = new TitledBorder("Informations");
	    border.setTitleJustification(TitledBorder.CENTER);
	    border.setTitlePosition(TitledBorder.TOP);
	    setBorder(border);	
	    
	    setLayout(new GridBagLayout());
	    GridBagConstraints c = new GridBagConstraints();
	    
	    iteration = new JLabel("Iteration actuelle: 0" + "\n");
	    c.fill = GridBagConstraints.BOTH;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 0;
		add(iteration,c);
	    robustesse = new JLabel("Robustesse moyenne: ");
		c.gridx = 0;
		c.gridy = 1;
		add(robustesse,c);
		
		robustesseRED = new JLabel("<html>Robustesse moyenne: <font color='red'>ARIDE</font></html>");
		c.gridx = 0;
		c.gridy = 2;
		add(robustesseRED,c);
		robustesseYELLOW = new JLabel("<html>Robustesse moyenne: <font color='orange'>TEMPERE</font></html>");
		c.gridx = 0;
		c.gridy = 3;
		add(robustesseYELLOW,c);
		robustesseGREEN = new JLabel("<html>Robustesse moyenne: <font color='green'>EXOTIQUE</font></html>");
		c.gridx = 0;
		c.gridy = 4;
		add(robustesseGREEN,c);
		robustesseBLUE = new JLabel("<html>Robustesse moyenne: <font color='blue'>GLACIER</font></html>");
		c.gridx = 0;
		c.gridy = 5;
		add(robustesseBLUE,c);
		
		/*pMortRED = new JLabel("<html>pMort moyenne: <font color='red'>ARIDE</font></html>");
		c.gridx = 1;
		c.gridy = 2;
		add(pMortRED,c);
		pMortYELLOW = new JLabel("<html>pMort moyenne: <font color='orange'>TEMPERE</font></html>");
		c.gridx = 1;
		c.gridy = 3;
		add(pMortYELLOW,c);
		pMortGREEN = new JLabel("<html>pMort moyenne: <font color='green'>EXOTIQUE</font></html>");
		c.gridx = 1;
		c.gridy = 4;
		add(pMortGREEN,c);
		pMortBLUE = new JLabel("<html>pMort moyenne: <font color='blue'>GLACIER</font></html>");
		c.gridx = 1;
		c.gridy = 5;
		add(pMortBLUE,c);*/
	
		taille = new JFormattedTextField(view.getPresenter().getTaille());
		c.gridx = 0;
		c.gridy = 6;
		add(new JLabel("Taille: "), c);
		c.gridx = 1;
		c.gridy = 6;
		add(taille,c);
		
		carac1 = new JFormattedTextField(view.getPresenter().getFirstCarac());
		c.gridx = 0;
		c.gridy = 7;
		add(new JLabel("Caracteristique 1 : "), c);
		c.gridx = 1;
		c.gridy = 7;
		add(carac1,c);
		
		carac2 = new JFormattedTextField(view.getPresenter().getSecondCarac());
		c.gridx = 0;
		c.gridy = 8;
		add(new JLabel("Caracteristique 2 : "), c);
		c.gridx = 1;
		c.gridy = 8;
		add(carac2,c);
		
		carac3 = new JFormattedTextField(view.getPresenter().getThirdCarac());
		c.gridx = 0;
		c.gridy = 9;
		add(new JLabel("Caracteristique 3 : "), c);
		c.gridx = 1;
		c.gridy = 9;
		add(carac3,c);
		
		pReprod = new JFormattedTextField(view.getPresenter().getPReprod());
		c.gridx = 0;
		c.gridy = 10;
		add(new JLabel("pReprod: "), c);
		c.gridx = 1;
		c.gridy = 10;
		add(pReprod,c);
		
		/*
		couleurs = new JLabel("Rouge: ARIDE; Jaune: TEMPERE; Vert: EXOTIQUE; Bleu: GLACIER" + "\n");
		c.gridx = 0;
		c.gridy = 9;
		add(couleurs,c);
		*/
	}
	
	/**
	 * GETTER
	 */
	public int getTaille(){
		return (int) taille.getValue();
	}
	
	public double getPReprod(){
		return (double) pReprod.getValue();
	}

	public double getFirstCarac() {
		return (double) carac1.getValue();
	}

	public double getSecondCarac() {
		return (double) carac2.getValue();
	}
	
	public double getThirdCarac() {
		return (double) carac3.getValue();
	}
	
	
}
