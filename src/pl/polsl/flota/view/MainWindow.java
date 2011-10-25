/**
 * 
 */
package pl.polsl.flota.view;

import java.util.ArrayList;
import java.util.List;

import pl.polsl.flota.exceptions.ElementAlredyExists;
import pl.polsl.flota.exceptions.ElementNotFound;
import pl.polsl.flota.helpers.*;
import pl.polsl.flota.model.Car;
import pl.polsl.flota.model.CarList;
import pl.polsl.flota.model.Refuel;
import pl.polsl.flota.model.User;
import pl.polsl.flota.model.UserList;

/**
 * @author Marcin Jabrzyk
 * @since 21/10/2011
 */
public final class MainWindow {

	List<String> menuAdminMain = new ArrayList<String>();
	List<String> menuAdminDriver = new ArrayList<String>();
	List<String> menuDriverLvl1 = new ArrayList<String>();

	/**
	 * Default constructor which initializes some values which are global for
	 * whole Application.
	 * 
	 * @since 1.0.0 21/10/2011
	 */
	public MainWindow() {
		// Values of menu for level 1 of the Application
		menuAdminMain.add("	1) Dodaj pojazd.");
		menuAdminMain.add("	2) Przeglądaj pojazd.");
		menuAdminMain.add("	3) Edytuj pojazd.");
		menuAdminMain.add("	4) Usuń pojazd.");
		menuAdminMain.add("	5) Kierowca.");
		menuAdminMain.add("	6) Wyloguj.");
		menuAdminMain.add("	7) Wyjście z programu.");

		// This is menu for admin - "kierowca"
		menuAdminDriver.add(" 1) Dodaj kierowcę.");
		menuAdminDriver.add(" 2) Przeglądaj kierowcę.");
		menuAdminDriver.add(" 3) Edytuj kierowcę.");
		menuAdminDriver.add(" 4) Usuń kierowcę.");
		menuAdminDriver.add(" 5) Cofnij do menu głównego");

		// This menu is for the car driver
		menuDriverLvl1.add("	1) Zanotuj tankowanie.");
		menuDriverLvl1.add("	2) Zmień pojazd.");
		menuDriverLvl1.add("	3) Wyjście.");

	}

	/**
	 * @param args
	 *            Runtime args
	 * @since 1.0.0 21/10/2011
	 */
	public static void main(String[] args) {
		MainWindow mywindow = new MainWindow();

		System.out.println("Witaj w programie Flota.");
		CarList carList = new CarList();
		try {
		carList.addItem(new Car("SR12000", "Toyota Corolla", 100000,
				(float) 5.2));
		carList.addItem(new Car("SR12001", "Toyota Avensis", 200000,
				(float) 7.2));
		carList.addItem(new Car("SR12004", "Mazda 626", 200000,
				(float) 7.2));
		//carList.addItem(new Car("SR12001", "Toyota Avensis", 200000,
		//		(float) 7.2));
		} catch (ElementAlredyExists e) {
			System.out.println("Było identical");
			e.printStackTrace();
		}
		List<Car> moje_auta;
		try {
			moje_auta = carList.getCarByName("62");
			System.out.print(moje_auta.size());
		} catch (ElementNotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Car my_car = null;
		try {
			my_car = carList.getCarByRegNumber("SR12001");
		} catch (ElementNotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		java.util.Date date = new java.util.Date();
		my_car.addRefuel(new Refuel(100001, (float) 100.2, (float) 57.24, date));
		my_car.addRefuel(new Refuel(100002, (float) 102.2, (float) 52.24, date));
		System.out.println(my_car.getHistoryOfRefuel().get(0).getAmount());
		carList.save("test.txt");

		CarList carList2 = new CarList();
		carList2.load("test.txt");
		carList.save("test2.txt");
		
		//List<Car> myList = carList2.get();
		for (Car item : carList2.getListOfCars() ){
			System.out.println(item.toString());
		}
		
		//User u1 = (new User("bzyx","marcin","Marcin Jabrzyk"));
		//User u2 = (new User("sasia","sandra","Sandra Jabrzyk"));
		//u1.setIsAdmin(true);
		UserList ulist = new UserList();
		//ulist.addItem(u1);
		//ulist.addItem(u2);
		//ulist.save("listaUserow.txt");
		ulist.load("listaUserow_2.txt");
		try {
			ulist.addUser(new User("karol", "karol", "Karol Skoruch"));
		} catch (ElementAlredyExists e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ulist.save("listaUserow.txt");
		

		Helpers.clearScren();
		mainMenuAdmin(mywindow);
	}

	/**
	 * This function generates and handles the main menu for application admin.
	 * 
	 * @param mywindow
	 * @since 1.0.0 24/10/2011
	 */
	private static void mainMenuAdmin(MainWindow mywindow) {
		Integer retVal = Helpers.menuToOptionId(mywindow.menuAdminMain);
		switch (retVal) {
		case 1: {
			// Dodaj pojazd
			break;
		}
		case 2: {
			// Przeglądaj pojazd
			break;
		}
		case 3: {
			// Edytuj pojazd
			break;
		}
		case 4: {
			// Usuń pojazd
			break;
		}
		case 5: {
			// Kierowca
			// TODO: Should clean the console first
			Helpers.clearScren();
			mainMenuAdminDriver(mywindow);
			// Should check the return value and make the right functions
			break;
		}
		case 6: {
			// Wyloguj do okna głównego
		}
		case 7: {
			// Wyjście z programu
			System.exit(0);
		}
		}
		// System.out.println("Menu admin option: " + retVal);
	}

	/**
	 * This function generates and handles Driver section in admin menu
	 * 
	 * @param mywindow
	 * @since 1.0.0 24/10/2011
	 */
	private static void mainMenuAdminDriver(MainWindow mywindow) {
		Integer retVal = Helpers.menuToOptionId(mywindow.menuAdminDriver);
		switch (retVal) {
		case 1: {
			// Dodaj kierowcę
			break;
		}
		case 2: {
			// Przeglądaj kierowcę
			break;
		}
		case 3: {
			// Edytuj kierowcę
			break;
		}
		case 4: {
			// Usuń kierowcę
			break;
		}
		case 5: {
			// Menu główne
			Helpers.clearScren();
			mainMenuAdmin(mywindow);
			break;
		}
		}
		// System.out.println("Menu admin for driver option: " + retVal);
	}

	/**
	 * This function generates and handles Driver menu
	 * 
	 * @param mywindow
	 * @since 1.0.0 24/10/2011
	 */
	private static void mainMenuDriver(MainWindow mywindow) {
		Integer retVal = Helpers.menuToOptionId(mywindow.menuAdminDriver);
		switch (retVal) {
		case 1: {
			// Zanotuj tankowanie
			break;
		}
		case 2: {
			// Zmień pojazd
			break;
		}
		case 3: {
			// Wyloguj
			break;
		}
		}
	}
}
