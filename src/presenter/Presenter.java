package presenter;

import java.util.HashMap;

import modele.Environnement;
import modele.MicroEnvironnement;
import modele.Modele;
import view.View;

public class Presenter {
	/**
	 * Parametre du modele: nb iteration, taille, pMortVieillesse et pReprod
	 */
	int iteration, taille;
	private double pMortVieillesse, pReprod, carac1, carac2, carac3;
	
	/**
	 * Instance du modele
	 */
	private Modele modele;
	
	/**
	 * Instance de la vue
	 */
	private View view;
	
	
	

	public Presenter(){
		taille = 80;
		carac1 = 0.4;
		carac2 = 0.2;
		carac3 = 0.7;
		
		pReprod = 0.35;
		
		modele = new Modele(10, taille, carac1, carac2, carac3, pReprod);
		
		view = new View(this);
	}
	
	
	public void start(){
		view.setVisible(true);
	}
	
	public void newModele(){
		taille = view.getInformationsPane().getTaille();
		pReprod = view.getInformationsPane().getPReprod();

		carac1 = view.getInformationsPane().getFirstCarac();
		carac2 = view.getInformationsPane().getSecondCarac();
		carac3 = view.getInformationsPane().getThirdCarac();
		
		modele = new Modele(10, taille, carac1, carac2, carac3, pReprod);
	}
	
	public void iteration(){
		modele.getEnvironnement().iteration();
	}
	
	public void simulationComplete(){
		modele.iterEnvironnement();
	}
	
	/**
	 * GETTER
	 */
	public int getTaille(){
		return taille;
	}
	
	public int[][] getCellEnvironment(){ 
		//modele.iterEnvironnement();
		//retourne un tableau avec 1 si il y a une cellule et 0 s'il y en a pas
		MicroEnvironnement[][] grille = modele.getEnvironnement().getGrille();
		int[][] res = new int[grille.length+4][grille.length+4];
		
		for(int i = 0; i < res.length; i++){
			res[i][0] = -1;
			res[i][1] = -1;
			res[0][i] = -1;
			res[1][i] = -1;
			
			res[res.length-1][i] = -1;
			res[res.length-2][i] = -1;
			res[i][res.length-1] = -1;
			res[i][res.length-2] = -1;
			}
		
		for(int i = 4; i < res.length-4; i++){
			for(int j = 4; j < res.length-4; j++){
				if(grille[i-4][j-4].isFree()){
					res[i][j] = 0;
				}
				else{
					switch(grille[i-4][j-4].getName()){
						case "ARIDE": res[i][j] = 1; break;
						case "TEMPERE": res[i][j] = 2; break;
						case "EXOTIQUE": res[i][j] = 3; break;
						case "GLACIER": res[i][j] = 4; break;
					}
				}
			}
		}
		return res;
	}
	
	public double getAverageCarac(){	
		return modele.getEnvironnement().getAverageCarac();
	}
	
	public double getRobustesseMicroE(String name){
		HashMap<String, Double> tmp = modele.getEnvironnement().getMicroEnvironnementRobustesse();
		Double res = null;
		switch(name){
		case "Aride": res = tmp.get("Aride"); break;
		case "Tempere": res = tmp.get("Tempere"); break;
		case "Exotique": res = tmp.get("Exotique"); break;
		case "Glacier": res = tmp.get("Glacier"); break;
		}
		return res;
	}
	

	public Environnement getEnvironnement() {
		return modele.getEnvironnement();
	}
	public double getPReprod(){
		return pReprod;
	}
	
	public double getpMortVieillesse(){
		return pMortVieillesse;
	}

	public double getFirstCarac() {
		return carac1;
	}
	
	public double getSecondCarac() {
		return carac2;
	}
	
	public double getThirdCarac() {
		return carac3;
	}
}
