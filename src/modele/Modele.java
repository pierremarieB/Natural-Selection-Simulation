package modele;

import java.util.ArrayList;

public class Modele {
	protected Environnement environnement;
	
	protected int iteration,taille;
	protected double carac1;
	protected double carac2;
	protected double carac3;
	protected double pReprod;
	protected double pMortVieillesse;
	
	public Modele(int iteration, int taille, double carac1, double carac2, double carac3, double pReprod){
		this.iteration = iteration;
		this.taille = taille;
		this.carac1 = carac1;
		this.carac2 = carac2;
		this.carac3 = carac3;
		this.pReprod = pReprod;
		
		init(taille);
	}
	
	public void init(int taille){
		environnement = new Environnement(taille);
		environnement.peuplementInitGrille(taille, carac1, carac2, carac3, pReprod);
	}
	
	public void iterEnvironnement(){
		//System.out.println(environnement);
		for (int i = 0; i < iteration; i++){
			environnement.iteration();
			//System.out.println(environnement);
		}
	}
	
	
	/*
	 * Calcul d'une proba de mort parfaite
	 * 
	 * @param taille la taille de l'environnement voulu
	 */
	//On remarque la proba de reproduction influence énormément les résultats.
	//Surtout que les cellules ne peuvent plus se reproduire si elles n'ont pas de places autour d'elles.
	/*public void calculProbaMort(int taille){
		ArrayList<double> res = new ArrayList<double>();
		
		for(double i = (double) 0.1; i <= 1; i += 0.1){
			System.out.println("Iteration "+i);
			Environnement test = new Environnement(taille);
			test.peuplementInitGrille(taille, i/10, pReprod, caracteristique);
			
			for(int x = 0; x < 990; x++){
				test.iteration();
			}
			for(int x = 0; x < 10; x++){
				if(!test.pleine(80) && !res.contains((double) i) ){
					System.out.println("On ajoute une valeur");
					res.add((double) i);
				}
				test.iteration();
			}
		}
		
		for(int i = 0; i < res.size(); i++){
			System.out.println(res.get(i));
		}
	}
	*/

	
	/**
	 * GETTER
	 */
	public Environnement getEnvironnement(){
		return environnement;
	}
}
