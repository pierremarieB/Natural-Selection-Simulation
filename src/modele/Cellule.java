
package modele;

import java.util.ArrayList;
import java.util.Random;
import java.util.Hashtable;

public class Cellule {
	
	public static Random rand = new Random();
	
	/**
	 * Probabilite de reproduction
	 */
	protected double pReprod;
	
	
	/**
	 * Caracteristique arbitraire 1
	 */
	protected double firstCarac;
	
	/**
	 * Caracterisitque arbitraire 2
	 */
	protected double secondCarac;
	
	/**
	 * Caracteristique arbitraire 3
	 */
	protected double thirdCarac;
	
	/**
	 * Tableau des caracteristique
	 */
	protected double[] caracArray;
	/**
	 * Booleen pour savoir si la cellule vient de naitre a une iteration donne
	 */	
	protected boolean recentNaissance = false;

	/**
	 * Caracteristique d'une cellule qui definira la facon dont l'environnement agit sur elle
	 */
	protected double caracteristique;
	
	/**
	 * Valeur permettetant de savoir si la cellule est plus ou moins vieille
	 */	
	protected double pMortVieillesse;

	/**
	 * Constructeur logique
	 * 
	 * @param microE micro environnement dans laquelle est notre cellule
	 */
	public Cellule(double carac1, double carac2 ,double carac3, double pReprod){
		//Caracteristique qui seront interpretes pas le MicroEnv
		this.firstCarac = carac1;
		this.secondCarac = carac2;
		this.thirdCarac = carac3;
		//Tableau des trois caracteristiques
		this.caracArray = new double[]{carac1,carac2,carac3};
		//Probabilite de reprod et de mort par vieillissement
		this.pReprod = pReprod;
		this.pMortVieillesse = 0.1;
		
	}
	
	public boolean getRecentNaissance(){
		return this.recentNaissance;
	}
	
	public void setRecentNaissance(boolean bool){
		this.recentNaissance = bool;
	}
	
	public double getPMortVieillesse(){
		return pMortVieillesse;
	}
	
	public void setPMortVieillesse(double value){
		pMortVieillesse = value;
	}

	public double getPReprod(){
		return pReprod;
	}
	
	public void setPReprod(double pReprod){
		this.pReprod = pReprod;
	}
	
	public double getFirstCarac() {
		return this.firstCarac;
	}
	
	public void setFirstCarac(double carac) {
		this.firstCarac = carac;
	}
	
	public double getSecondCarac() {
		return this.secondCarac;
	}
	
	public void setSecondCarac(double carac) {
		this.secondCarac = carac;
	}
	
	public double getThirdCarac() {
		return this.thirdCarac;
	}
	
	public void setThirdCarac(double carac) {
		this.thirdCarac = carac;
	}
	
	/**
	 * @return une cellule enfante 
	 */
	public Cellule genererEnfant(){
		Random rand = new Random();
		double factorCarac1 = rand.nextGaussian()/200;
		double factorCarac2 = rand.nextGaussian()/200;
		double factorCarac3 = rand.nextGaussian()/200;
		
		double caracUpdate1 = normalizeCaracteristic(this.firstCarac+factorCarac1);
		double caracUpdate2 = normalizeCaracteristic(this.secondCarac+factorCarac2);
		double caracUpdate3 = normalizeCaracteristic(this.thirdCarac+factorCarac3);
		
		Cellule cell = new Cellule(caracUpdate1, caracUpdate2, caracUpdate3, this.pReprod);
		return cell;
	}
	
	public double normalizeCaracteristic(double caracteristic) {
		if(caracteristic < 0){
			return (double) 0;
		} else if (caracteristic > 1) {
			return (double) 1;
		} else {
			return caracteristic;
		}
	}
}
