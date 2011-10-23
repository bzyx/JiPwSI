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

	List<String> menuAdminMain = new ArrayList<String>();
	List<String> menuAdminDriver = new ArrayList<String>();
	List<String> menuDriverLvl1 = new ArrayList<String>();
	
	
	/**
	 * Default constructor which initializes some values which are global for whole Application.
	 * @since 1.0.0 21/10/2011
	 */
	public MainWindow() {
		//Values of menu for level 1 of the Application
		menuAdminMain.add("	1) Dodaj pojazd.");
		menuAdminMain.add("	2) Przeglądaj pojazd.");
		menuAdminMain.add("	3) Edytuj pojazd.");
		menuAdminMain.add("	4) Usuń pojazd.");
		menuAdminMain.add("	5) Kierowca.");
		menuAdminMain.add("	6) Wyjście z programu.");
		
		menuAdminDriver.add(" 1) Dodaj kierowcę.");
		menuAdminDriver.add(" 2) Przeglądaj kierowcę.");
		menuAdminDriver.add(" 3) Edytuj kierowcę.");
		menuAdminDriver.add(" 4) Usuń kierowcę.");
		menuAdminDriver.add(" 5) Cofnij do menu głównego");
		
		//This menu is for the car driver
		menuDriverLvl1.add("	1) Zanotuj tankowanie.");
		menuDriverLvl1.add("	2) Zmień pojazd.");
		menuDriverLvl1.add("	3) Wyjście.");
		
	}

	/**
	 * @param args Runtime args
	 * @since 1.0.0 21/10/2011       
	 */
	public static void main(String[] args) {
		MainWindow mywindow = new MainWindow();

		System.out.println("Witaj w programie Flota.");
		// TODO Auto-generated method stub
		Integer retVal = Helpers.menuToOptionId(mywindow.menuAdminMain);
		switch (retVal) {
		case 1:	{
			//Dodaj pojazd
			break;
		}
		case 2:{
			//Przeglądaj pojazd
			break;
		}
		case 3:{
			//Edytuj pojazd
			 break;
		}
		case 4:{
			//Usuń pojazd
			break;
		}
		case 5:{
			//Kierowca
			//TODO: Should clean the console first
			Helpers.menuToOptionId(mywindow.menuAdminDriver);
			//Should check the return value and make the right functions
			break;
		}
		case 6:{
			//Wyjście z programu
		}
		}
		System.out.println(retVal);
	}

}
