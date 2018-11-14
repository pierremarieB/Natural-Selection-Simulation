package modele;

import java.awt.List;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class Environnement implements Cloneable {
	/**
	 * Directions pour la gestion des voisins
	 */
	private static int[][] directions = new int[][]{{-1,0}, {0,1}, {1,0}, {0, -1}};
	
	protected java.util.Random random = new java.util.Random();
	/**
	 * matrice de micro environnements
	 */
	protected static MicroEnvironnement[][] grille;

	/**
	 * taille de la matrice
	 */
	protected int taille;
	
	/**
	 * Constructeur logique
	 */
	public Environnement(int taille){
		this.taille = taille;
		grille = new MicroEnvironnement[this.taille][this.taille];
		initGrille();
	}
	
	
	/**
	 * Construit notre grille avec des cellules mortes par defaut
	 */
	public void initGrille(){
		for(int i = 0; i != grille.length; i++){
			for(int j = 0; j != grille.length; j++){
				Position pos = new Position(i,j);
				int nbGenerate = (taille/2)-1;
				TypeMicroEnvironnement randomM = TypeMicroEnvironnement.generateMicroE(nbGenerate, i, j);
				System.out.println(randomM);
				grille[i][j] = new MicroEnvironnement(randomM, null, this);
			}
		}
	}
	
	/**
	 * Met les cellules dans des cases random
	 */
	public void peuplementInitGrille(int nbCellule, double carac1, double carac2, double carac3, double pReprod){
		for(int i = 0; i != nbCellule; i++){
			int x = random.nextInt(taille-1);
			int y = random.nextInt(taille-1);
			while (grille[x][y].isFree() == false){
				x = random.nextInt(taille-1);
				y = random.nextInt(taille-1);
			}
			Position pos = new Position(x,y);
			grille[x][y].newCellule(carac1, carac2, carac3, pReprod);
		}
	}
	
	public void setEtatNaissance(){
		for(int i = 0; i < taille; i++){
			for(int j = 0; j < taille; j++){
				Cellule current = getGrille()[i][j].getCellule();
				if(current != null){
					current.setRecentNaissance(false);
				}
			}
		}
	
	}
	
	/**
	 * Reproduction de chaque cellule de l'environnement
	 */
	public void peuplementReprodEnvironnement(){
		setEtatNaissance();
		for(int i = 0; i < taille; i++){
			for(int j = 0; j < taille; j++){
				MicroEnvironnement current = getGrille()[i][j];
				Cellule cell = current.getCellule();
				if(cell != null && !(cell.getRecentNaissance())){
					current.reprodCellule();
				}
			}
		}
	}
	
	/**
	 * Test la vie des cellules et vieillie les survivantes
	 */
	public void setModifEnvironnement(){
		for(int i = 0; i < taille; i++){
			for(int j = 0; j < taille; j++){
				MicroEnvironnement current = getGrille()[i][j];
				Cellule cell = current.getCellule();
				if(cell != null){
					current.vieCellule();
				}
				cell = current.getCellule();
				if(cell != null){
					current.vieillissement();
				}
			}
		}
	}
	
	/**
	 * Methode qui gere la reproduction
	 */
	public void reproductionCell(Cellule cell){
		ArrayList<Position> voisins = getVoisinsLibre(cell);
		
		if (voisins.size() != 0){
			Random rand = new Random();
			Position voisinReprod = voisins.get(rand.nextInt(voisins.size()));
			MicroEnvironnement current = grille[voisinReprod.getLigne()][voisinReprod.getColonne()];
			current.setCellule(cell.genererEnfant());
		}
	}
	
	public void iteration(){
		//getMicroEnvironnementRobustesse();
		peuplementReprodEnvironnement();
		setModifEnvironnement();
	}
	
	/**
	 * Methode qui affiche l'etat (nombre de cellule et robustesse moyenne) de l'environnement
	 */
	
	public void afficheEtatEnvironnement(){
		double nbCells = 0;
		double totalCarac = 0;
		double averageRobust = 0;
		
		for(int i = 0; i < taille; i++){
			for(int j = 0; j < taille; j++){
				if(grille[i][j].getCellule() != null){
					nbCells += 1;
					System.out.println(grille[i][j].getCellule().getPMortVieillesse());
					totalCarac += grille[i][j].getCellule().getPMortVieillesse();
				}
			}
		}
		averageRobust = totalCarac/nbCells;
		System.out.println("Le nombre de cellule a cette iteration est de "+Double.toString(nbCells)+" et la robustesse moyenne de "+Double.toString(averageRobust));
	}
	
	public HashMap<String, Double> getMicroEnvironnementRobustesse(){
		ArrayList<Cellule> cellAride = new ArrayList<Cellule>();
		ArrayList<Cellule> cellGlacier = new ArrayList<Cellule>();
		ArrayList<Cellule> cellTempere = new ArrayList<Cellule>();
		ArrayList<Cellule> cellExotique = new ArrayList<Cellule>();
		
		for(int i = 0; i < taille; i++){
			for(int j = 0; j < taille; j++){
				if(grille[i][j].getCellule() != null){
					if(grille[i][j].getName() == "ARIDE"){cellAride.add(grille[i][j].getCellule());}
					else if(grille[i][j].getName() == "GLACIER"){cellGlacier.add(grille[i][j].getCellule());}
					else if(grille[i][j].getName() == "TEMPERE"){cellTempere.add(grille[i][j].getCellule());}
					else {cellExotique.add(grille[i][j].getCellule());}
				}
			}
		}
		HashMap <String, Double> res = new HashMap<String, Double>();
		res.put("Aride", calculCaracMoyenne(cellAride));
		res.put("Glacier", calculCaracMoyenne(cellGlacier));
		res.put("Tempere", calculCaracMoyenne(cellTempere));
		res.put("Exotique", calculCaracMoyenne(cellExotique));
		System.out.println("--- ITERATION ---");
		System.out.println("ARIDE "+calculCaracMoyenne(cellAride));
		System.out.println("GLACIER "+calculCaracMoyenne(cellGlacier));
		System.out.println("TEMPERE "+calculCaracMoyenne(cellTempere));
		System.out.println("EXOTIQUE "+calculCaracMoyenne(cellExotique));
		
		return res;
	}
	
	public HashMap<String, Double> getMicroEnvironnementPMort(){
		ArrayList<Cellule> cellAride = new ArrayList<Cellule>();
		ArrayList<Cellule> cellGlacier = new ArrayList<Cellule>();
		ArrayList<Cellule> cellTempere = new ArrayList<Cellule>();
		ArrayList<Cellule> cellExotique = new ArrayList<Cellule>();
		
		for(int i = 0; i < taille; i++){
			for(int j = 0; j < taille; j++){
				if(grille[i][j].getCellule() != null){
					if(grille[i][j].getName() == "ARIDE"){cellAride.add(grille[i][j].getCellule());}
					else if(grille[i][j].getName() == "GLACIER"){cellGlacier.add(grille[i][j].getCellule());}
					else if(grille[i][j].getName() == "TEMPERE"){cellTempere.add(grille[i][j].getCellule());}
					else {cellExotique.add(grille[i][j].getCellule());}
				}
			}
		}
		HashMap <String, Double> res = new HashMap<String, Double>();
		res.put("Aride", calculPMortMoyenne(cellAride));
		res.put("Glacier", calculPMortMoyenne(cellGlacier));
		res.put("Tempere", calculPMortMoyenne(cellTempere));
		res.put("Exotique", calculPMortMoyenne(cellExotique));
		
		return res;
	}
	
	public double calculPMortMoyenne(ArrayList<Cellule> tabCell){
		double nbCells = tabCell.size();
		double totalPMort = 0;
		
		for(int i=0; i < tabCell.size(); i++){
			double currentPMort = tabCell.get(i).getPMortVieillesse();
			totalPMort += currentPMort;
		}
		return totalPMort / nbCells;
	}

	public double calculCaracMoyenne(ArrayList<Cellule> tabCell){
		double nbCells = tabCell.size();
		double totalCarac = 0;
		
		for(int i=0; i < tabCell.size(); i++){
			double currentRobust = tabCell.get(i).getFirstCarac();
			totalCarac += currentRobust;
		}
		return totalCarac / nbCells;
	}

	public double getAverageCarac(){
		double nbCells = 0;
		double totalCarac = 0;
		double averageCarac = 0;
		
		for(int i = 0; i < taille; i++){
			for(int j = 0; j < taille; j++){
				if(grille[i][j].getCellule() != null){
					nbCells += 1;
					//System.out.println(grille[i][j].getCellule().getCaracteristique());
					//totalCarac += grille[i][j].getCellule().getCaracteristique();
				}
			}
		}
		averageCarac = totalCarac/nbCells;
		return averageCarac;
	}
	
	/**
	 * Renvoie true si la grille est pleine en fonction d'un pourcentage donné
	 */
	public boolean pleine(int pourcent){
		int compteur = 0;
		
		for(int i = 0; i < taille; i++){
			for(int j = 0; j < taille; j++){
				if(grille[i][j].getCellule() != null){
					compteur++;
				}
			}
		}
		return compteur > grille.length*(pourcent/100);
	}

	
	/**
	 * Methode qui renvoie la liste des voisins libre de la cellule
	 */
	
	public ArrayList<Position> getVoisinsLibre(Cellule cell){
	    ArrayList<Position> res = new ArrayList<Position>();
	    Position pos = getPosition(cell);
	    Position posVoisin = null;
	    for (int[] direction : directions) {
	        int cx = pos.getLigne() + direction[0];
	        int cy = pos.getColonne() + direction[1];
	        if(cy >=0 && cy < grille.length){
	            if(cx >= 0 && cx < grille[cy].length){
	            	posVoisin = new Position(cx,cy);
	            }
	        }
			if(posVoisin != null && grille[posVoisin.getLigne()][posVoisin.getColonne()].getCellule() == null){
    			res.add(posVoisin);
    		}
	    }
	    return res;
	}
	
	
	/**
	 * @return taille de la grille
	 */
	
	public int getTaille(){
		return taille;
	}
	
	public void setTaille(int taille){
		this.taille = taille;
	}
	
	/**
	 * @return grille associee a l'environnement
	 */
	public MicroEnvironnement[][] getGrille(){
		return grille;
	}
	
	public void setGrille(MicroEnvironnement[][] grille){
		this.grille = grille;
	}
	
	/**
	 * @return position d'une cellule donnee
	 */
	public Position getPosition(Cellule cell){
		for(int i = 0; i < grille.length; i++){
			for(int j = 0; j < grille[i].length; j++){
				if(grille[i][j].getCellule() == cell){
					return new Position(i,j);
				}
			}
		}
		return null;
	}
	
	public void setPosition(Position pos, Cellule cell){
		grille[pos.getLigne()][pos.getColonne()].setCellule(cell);
	}

	/***
	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		for(int i = 0; i < getGrille().length; i++){
			res.append("\n");
			for(int j = 0; j < getGrille().length; j++){
				if(getGrille()[i][j].getCellule() == null){
					res.append(" * ");
				}
				else if(getGrille()[i][j].getCellule().getEtat()){
					// Representation de la grille, ici, on choisit de representer la robustesse
					// -> complement a 1 de la PMortInitiale
					 * 
					double pMort = (1-(getGrille()[i][j].getCellule().getPMortVieillesse()))*10;
					pMort = double.parsedouble(double.toString(pMort).substring(0, 3));
					res.append(pMort);
				}
				res.append(" ");
			}
		}
		
		String finalString = res.toString();
		return finalString;
		
	}
	*/
	
}
