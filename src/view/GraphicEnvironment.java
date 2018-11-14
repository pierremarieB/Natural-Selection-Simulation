package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import modele.Cellule;
import presenter.Presenter;

public class GraphicEnvironment extends JPanel{
	
	private View view;
	private Presenter presenter;
	
	private int taille;
	private int[][] grid;
	
	private int generationCounter;
	
	public GraphicEnvironment(View view){
		this.view = view;
		presenter = view.getPresenter();
		taille = presenter.getTaille();
		
		/*
		 * Mise en place de la bordure
		 
		TitledBorder border = new TitledBorder("Environnement");
	    border.setTitleJustification(TitledBorder.CENTER);
	    border.setTitlePosition(TitledBorder.TOP);
	    setBorder(border);	
	    */
	   
	    Dimension dim = new Dimension(400, 400);
	    setPreferredSize(dim);
	}
	
	@Override
    protected void paintComponent(Graphics g) {
		grid = presenter.getCellEnvironment();
		
        super.paintComponent(g);
        Color gColor = g.getColor();

        //g.drawString("Generation: " + generationCounter++, 0, 10);
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == -1) {
                    g.setColor(Color.black);
                    g.fillRect(j * 4, i * 4, 4, 4);
                }
                else if (grid[i][j] != 0) {
                	// Grid est la grille theorique elle est donc de taille : taille+4, on retire donc 4
                	// � la longueur de cette grille theorique pour obtenir la correspondance avec la veritable grille
                	Cellule currentCell = presenter.getEnvironnement().getGrille()[i-4][j-4].getCellule();
	                float red = Math.abs((float) currentCell.getFirstCarac());
	                float green = Math.abs((float) currentCell.getSecondCarac());
	                float blue = Math.abs((float) currentCell.getThirdCarac());
	                
	                red = normalizeRGBFloat(red);
	                green = normalizeRGBFloat(green);
	                blue = normalizeRGBFloat(blue);
	                
	                Color color = new Color(red,green,blue);
	                g.setColor(color);
	                System.out.println(red + " " + green + " " + blue);
	                System.out.println(color.getRed() + " " + color.getGreen() + " " + color.getBlue());
	                
	                g.fillRect(j * 4, i * 4, 4, 4);
                }
                /*
                if (grid[i][j] == 1) {
                    g.setColor(Color.red);
                    g.fillRect(j * 4, i * 4, 4, 4);
                }
                if (grid[i][j] == 2) {
                    g.setColor(Color.orange);
                    g.fillRect(j * 4, i * 4, 4, 4);
                }
                if (grid[i][j] == 3) {
                    g.setColor(Color.green);
                    g.fillRect(j * 4, i * 4, 4, 4);
                }
                if (grid[i][j] == 4) {
                    g.setColor(Color.blue);
                    g.fillRect(j * 4, i * 4, 4, 4);
                }
                */
            }
        }

        g.setColor(gColor);
    }
	
	public float normalizeRGBFloat(float value){
		if(value > 1){
			return (float) 1;
		}
		else if(value < 0){
			return (float) 0;
		}
		
		return value;
	}
	
	/**
	 * Getter
	 */
	public View getView(){
		return view;
	}
}
