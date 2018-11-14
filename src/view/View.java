package view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;


import presenter.Presenter;

public class View extends JFrame {
	
	/**
	 * Instance du presenter
	 */
	private Presenter presenter;
	
	/**
	 * Instanciation de tous nos JPanels
	 */
	protected Toolbar toolbar;
	protected GraphicEnvironment environment;
	protected InformationsPane infoPane;
	protected JPanel environmentAndPane;
	
	public View(Presenter presenter){
		this.presenter = presenter;
		
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		/*
		 * Mis en place de chaque JPanel un par un dans notre fenêtre principale
		 */
		toolbar = new Toolbar(this);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 0;
		add(toolbar,c);
		
		
		environmentAndPane = new JPanel();
		environmentAndPane.setLayout(new GridBagLayout());
		infoPane = new InformationsPane(this);
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 0;
		environmentAndPane.add(infoPane,c);
		environment = new GraphicEnvironment(this);
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 0;
		environmentAndPane.add(environment,c);
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 1;
		add(environmentAndPane,c);
		
		setTitle("Simulation de selection naturelle");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(960, 1024);
	}
	
	/**
	 * Getter
	 */
	
	public Presenter getPresenter(){
		return presenter;
	}
	
	public GraphicEnvironment getGraphicEnvironment(){
		return environment;
	}
	
	public InformationsPane getInformationsPane(){
		return infoPane;
	}
	
	
}
