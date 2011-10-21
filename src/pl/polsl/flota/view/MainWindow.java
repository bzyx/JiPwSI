/**
 * 
 */
package pl.polsl.flota.view;

import java.util.ArrayList;
import java.util.List;
import pl.polsl.flota.helpers.*;

/**
 * @author Marcin Jabrzyk
 * @since 21/10/2011
 */
public final class MainWindow {

	List<String> menuLvl1 = new ArrayList<String>();
	
	
	/**
	 * Default constructor which initializes some values which are global for whole Application.
	 * @since 21/10/2011
	 */
	public MainWindow() {
		//Values of menu for level 1 of the Application
		menuLvl1.add("	1) Dodaj pojazd.");
		menuLvl1.add("	2) Przeglądaj pojazd.");
		menuLvl1.add("	3) Edytuj pojazd.");
		menuLvl1.add("	4) Usuń pojazd.");
		menuLvl1.add("	5) Wyjście z programu.");
	}

	/**
	 * @param args Runtime args
	 *        
	 */
	public static void main(String[] args) {
		MainWindow mywindow = new MainWindow();

		System.out.println("Witaj w programie Flota.");
		// TODO Auto-generated method stub
		Integer retVal = Helpers.menuToOptionId(mywindow.menuLvl1);
		System.out.println(retVal);
	}

}
