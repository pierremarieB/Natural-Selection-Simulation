 package modele;

public class MicroEnvironnement {
	
	private final ActionDesMicroEnvironnements s;
	private Cellule cellule;
	private Environnement environnement;
	
	public MicroEnvironnement(ActionDesMicroEnvironnements s, Cellule cellule, Environnement environnement){
		this.s = s;
		this.cellule = cellule;
		this.environnement = environnement;
	}
	
	public boolean isFree(){
		if(cellule == null){
			return true;
		}
		return false;
	}
	
	public void setCellule(Cellule cell){
		this.cellule = cell;
		/*double pMortVieillesse;
		if(cell != null && (s.getName() == "GLACIER" || s.getName() == "ARIDE")){
			cell.setPMortVieillesse(1-cell.getCaracteristique());
		}*/
	}

	public void newCellule(double carac1, double carac2, double carac3, double pReprod){
		/*double pMortVieillesse;
		if(s.getName() == "GLACIER" || s.getName() == "ARIDE"){
			pMortVieillesse = 1 - caracteristique;
		}
		else{
			pMortVieillesse = caracteristique;
		}*/
		this.cellule = new Cellule(carac1, carac2, carac3, pReprod);
	}
	
	public Cellule getCellule(){
		return this.cellule;
	}

	
	public void vieCellule() {
		s.vieCellule(cellule,this,environnement);
	}

	
	public void vieillissement() {
		s.vieillissement(cellule);	
	}

	
	public void reprodCellule() {
		s.reprodCellule(cellule,environnement);
	}
	
	public String getName(){
		return s.getName();
	}
	

}
