package view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.TitledBorder;

public class Toolbar extends JPanel implements ActionListener{
	/*
	 * Instance de la vue
	 */
	private View view;
	
	/*
	 * Instance de nos bouttons
	 */
	private JButton start, newGame, info, quitter, step;
	private Timer timer;
	private int count = 0;
	private double averageCarac;
	private boolean test = true;
	
	/*
	 * Constructeur logique
	 * 
	 * @param vue notre fenêtre principale
	 */
	public Toolbar(View view){
		this.view = view;
		
		/*
		 * Mise en place de la bordure
		 */
		TitledBorder border = new TitledBorder("Commandes");
	    border.setTitleJustification(TitledBorder.CENTER);
	    border.setTitlePosition(TitledBorder.TOP);
	    setBorder(border);	
		
		setLayout(new FlowLayout(FlowLayout.LEFT));
	    
		/*
		 * Initialisation des bouttons
		 */
		start = new JButton();
		start.setIcon(new ImageIcon("Icones/playpause.png"));
		start.addActionListener(this);
		add(start);
		
		step = new JButton();
		step.setIcon(new ImageIcon("Icones/forward.png"));
		step.addActionListener(this);
		add(step);
		
		newGame = new JButton();
		newGame.setIcon(new ImageIcon("Icones/nouveau.png"));
		newGame.addActionListener(this);
		add(newGame);
		
		info = new JButton();
		info.setIcon(new ImageIcon("Icones/a-propos.png"));
		info.addActionListener(this);
		add(info);
		
		quitter = new JButton();
		quitter.setIcon(new ImageIcon("Icones/quitter.png"));
		quitter.addActionListener(this);
		add(quitter);
	}
	
	/**
	 * Getter
	 * 
	 * @return vue
	 */
	public View getView(){
		return view;
	}
	
	/**
	 * Methode qui gere le clique sur tous nos bouttons grace a un switch a l'interieur
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		
		/*
		 * En fonction de notre boutton, on a une action differente
		 */
		if(src == start){
			if(test){
				test = false;
				timer = new Timer(100, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e){
						view.getInformationsPane().iteration.setText("Iteration actuelle: "+count);
						
						updateLabels();
						
						view.getPresenter().iteration();
						view.getGraphicEnvironment().repaint();
						count++;
					}
				});
				timer.start();
			}
			else{
				test = true;
				timer.stop();
			}
		}	
		else if(src == step){
			view.getInformationsPane().iteration.setText("Iteration actuelle: "+count);
			
			updateLabels();
			
			view.getPresenter().iteration();
			view.getGraphicEnvironment().repaint();
			count++;
		}
		else if(src == newGame){
			view.getPresenter().newModele();
			view.getGraphicEnvironment().repaint();
			count = 0;
			view.getInformationsPane().iteration.setText("Iteration actuelle: "+count);
		}
		else if(src == info){
			JOptionPane.showMessageDialog(view, "Application developpee par BRIEDA Pierre-Marie et MIKA Vadim.");
		}
		else if(src == quitter){
			//view.dispose();
			System.exit(0);
		}
	}
	
	public void updateLabels(){
		
		averageCarac = view.getPresenter().getAverageCarac();
		view.getInformationsPane().robustesse.setText("Robustesse moyenne: "+averageCarac);
		view.getInformationsPane().robustesseRED.setText("<html>Robustesse moyenne: <font color='red'>"+view.getPresenter().getRobustesseMicroE("Aride")+"</font></html>");
		view.getInformationsPane().robustesseYELLOW.setText("<html>Robustesse moyenne: <font color='orange'>"+view.getPresenter().getRobustesseMicroE("Tempere")+"</font></html>");
		view.getInformationsPane().robustesseGREEN.setText("<html>Robustesse moyenne: <font color='green'>"+view.getPresenter().getRobustesseMicroE("Exotique")+"</font></html>");
		view.getInformationsPane().robustesseBLUE.setText("<html>Robustesse moyenne: <font color='blue'>"+view.getPresenter().getRobustesseMicroE("Glacier")+"</font></html>");
		
		/*
		view.getInformationsPane().pMortRED.setText("<html>pMort moyenne: <font color='red'>"+view.getPresenter().getPMortMicroE("Aride")+"</font></html>");
		view.getInformationsPane().pMortYELLOW.setText("<html>pMort moyenne: <font color='orange'>"+view.getPresenter().getPMortMicroE("Tempere")+"</font></html>");
		view.getInformationsPane().pMortGREEN.setText("<html>pMort moyenne: <font color='green'>"+view.getPresenter().getPMortMicroE("Exotique")+"</font></html>");
		view.getInformationsPane().pMortBLUE.setText("<html>pMort moyenne: <font color='blue'>"+view.getPresenter().getPMortMicroE("Glacier")+"</font></html>");
		*/
	}
	
	
}