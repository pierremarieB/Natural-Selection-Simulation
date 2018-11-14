package main;

import presenter.Presenter;

public class Launcher {
		public static void main(String[] args){
			/*
			 * Instanciation du presenter
			 */
			final Presenter presenter = new Presenter();
			
			/*
			 * Demarrage du presenter
			 */
			presenter.start();

		}
}
