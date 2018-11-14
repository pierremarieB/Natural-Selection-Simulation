package main;

import modele.*;

public class Main {
	public static void main(String[] args){
		/*
		 * La ligne de commandes est vide : l'utilisateur demande de l'aide. 
		 */
		if (args.length == 0) {
		    final String message = "Usage: java Main taille iteration pMort pReprod";
		    System.out.println(message);
		    return;
		}

		/* 
		 * Le nombre d'arguments est different de 2: l'utilisateur fait
		 * n'importe quoi.
		 */
		if (args.length != 4) {
		    System.err.println("Nombre d'arguments incorrect.");
		    return;
		}
		
		/*
		 * Extraction de la taille
		 */
		final int taille;
		try {
		    taille = Integer.parseInt(args[0]);
		    
		    if (taille < 5) {
		    	throw new NumberFormatException();
		    }
		}
		catch (NumberFormatException e) {
		    final String message = "La taille doit etre superieure a 5.";
		    System.err.println(message);
		    return;
		}
		
		/*
		 * Extraction du nombre d'itérations
		 */
		final int iterations;
		try {
		    iterations = Integer.parseInt(args[1]);
		    
		    if (iterations < 0) {
		    	throw new NumberFormatException();
		    }
		}
		catch (NumberFormatException e) {
		    final String message = "Le nombre d'iteration doit etre positif.";
		    System.err.println(message);
		    return;
		}
		
		/*
		 * Extraction de la pMort
		 */
		final float pMort;
		try {
		    pMort = Float.parseFloat(args[2]);
		    
		    if (pMort < 0 || pMort > 1) {
		    	throw new NumberFormatException();
		    }
		}
		catch (NumberFormatException e) {
		    final String message = "La probabilité de mort doit être comprise entre 0 et 1.";
		    System.err.println(message);
		    return;
		}
		
		/*
		 * Extraction de la pReprod
		 */
		final float pReprod;
		try {
		    pReprod = Float.parseFloat(args[3]);
		    
		    if (pReprod < 0 || pReprod > 1) {
		    	throw new NumberFormatException();
		    }
		}
		catch (NumberFormatException e) {
		    final String message = "La probabilité de reproduction doit être comprise entre 0 et 1.";
		    System.err.println(message);
		    return;
		}
		/*
		Modele modele = new Modele(iterations, taille, pMort, pReprod);
		modele.iterEnvironnement();
		*/
	}
}
