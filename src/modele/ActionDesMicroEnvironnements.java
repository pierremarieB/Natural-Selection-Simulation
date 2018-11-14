package modele;

public interface ActionDesMicroEnvironnements {
	void vieCellule(Cellule cellule, MicroEnvironnement MicroE, Environnement environnement);
	void vieillissement(Cellule cellule);
	void reprodCellule(Cellule cellule, Environnement environnement);
	String getName();
}
