package modele;

import java.util.Random;

public enum TypeMicroEnvironnement implements ActionDesMicroEnvironnements{
	ARIDE(0.2,0.2,0.5),
	EXOTIQUE(0.4,0.4,0.5),
	TEMPERE(0.6,0.6,0.5),
	GLACIER(0.8,0.8,0.5);
	
	
	private static final Random rand = new Random();
	private static final int size = TypeMicroEnvironnement.values().length;
	private final static double vieillesseSeuil = 1;
	
	private double vieillissement = (double) 1.01;
	private double ref1;
	private double ref2;
	private double ref3;
	
	TypeMicroEnvironnement(double ref1, double ref2, double ref3){
		this.ref1 = ref1;
		this.ref2 = ref2;
		this.ref3 = ref3;
	}
	/***
	@Override
	public void vieCellule(Cellule cellule, MicroEnvironnement microE, Environnement environnement){
		System.out.println("caracteristique"+cellule.getCaracteristique());
		double p = rand.nextdouble();
		double total = Math.abs(variable - cellule.getCaracteristique());
		
		
		//if((cellule.getPMortVieillesse() > vieillesseSeuil || environnement.getVoisinsLibre(cellule).size() < 4 ) && !(cellule.getRecentNaissance())){
		if((p < cellule.getCaracteristique())
				|| (cellule.getPMortVieillesse() > vieillesseSeuil)
				&& !(cellule.getRecentNaissance())){
			System.out.println("une cellule meurt");
			microE.setCellule(null);
		}
		else{
			if(environnement.getVoisinsLibre(cellule).size() < 1){
				Random rand = new Random();
				int tirage = rand.nextInt(10);
				if(tirage < 1){ //une chance sur 10 de mourir si elle est entouree de cellules
					System.out.println("une cellule meurt");
					microE.setCellule(null);
				}
			}
		}
	}
	***/
	
	@Override
	public void vieCellule(Cellule cellule, MicroEnvironnement microE, Environnement environnement){
		double p = rand.nextDouble();
		
		double[] arrayData = getInterpretedData(cellule, microE, environnement);
		if((p < arrayData[0])
				|| (arrayData[1] > vieillesseSeuil)
				&& !(cellule.getRecentNaissance())){
			System.out.println("une cellule meurt");
			microE.setCellule(null);
		}
		
		else{
			if(environnement.getVoisinsLibre(cellule).size() < 1){
				Random rand = new Random();
				int tirage = rand.nextInt(10);
				if(tirage < 1){ //une chance sur 10 de mourir si elle est entouree de cellules
					System.out.println("une cellule meurt");
					microE.setCellule(null);
				}
			}
		}
		
	}
	
	public double[] getInterpretedData(Cellule cellule, MicroEnvironnement microE, Environnement environnement) {
		double tmpPMortCellule, tmpPMortVieillissementCellule, tmpPReprodCellule;
		tmpPMortCellule = (double) (0.20*(1+Math.abs((cellule.getFirstCarac()-this.getRef1())+
													(cellule.getSecondCarac()-this.getRef2())+
													(cellule.getThirdCarac()-this.getRef3())
				)));
		tmpPMortVieillissementCellule = (double) (cellule.getPMortVieillesse()*(1+(cellule.getSecondCarac()-this.getRef2())));
		tmpPReprodCellule = (double) (cellule.getPReprod()*1-(cellule.getThirdCarac()-this.getRef3()));
		
		double[] arrayTmp = new double[]{tmpPMortCellule,tmpPMortVieillissementCellule,tmpPReprodCellule};
		return arrayTmp;
	}
	
	@Override
	public void vieillissement(Cellule cellule){
		cellule.setPMortVieillesse(cellule.getPMortVieillesse()*vieillissement);
	}
	
	@Override
	public void reprodCellule(Cellule cellule, Environnement environnement){
		double p = rand.nextDouble();
		
		if(p < cellule.pReprod){
			environnement.reproductionCell(cellule);
		}
	}
	
	
	
	/*
	 * @return un type d'environnement aleatoire
	 */
	public static TypeMicroEnvironnement randomMicroE(){
		return TypeMicroEnvironnement.values()[rand.nextInt(size)];
	}
	
	/*
	 * @return Un type de micro environnement en fonction des lignes/colonnes du tableau (utile a la generation des milieux)
	 * 
	 */
	public static TypeMicroEnvironnement generateMicroE(int n, int ligne, int colonne){
		if(ligne > n){
			if(colonne > n){
				return TypeMicroEnvironnement.values()[0];
			}
			else{
				return TypeMicroEnvironnement.values()[1];
			}
		}
		else{
			if(colonne > n){
				return TypeMicroEnvironnement.values()[2];
			}
			else{
				return TypeMicroEnvironnement.values()[3];
			}
		}
	}
	
	public String getName(){
		return this.name();
	}
	
	public double getRef1() {
		return this.ref1;
	}
	
	public double getRef2() {
		return this.ref2;
	}
	
	public double getRef3() {
		return this.ref3;
	}

}
