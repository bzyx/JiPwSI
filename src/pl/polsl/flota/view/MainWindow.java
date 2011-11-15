/**
 * This file has a main method. From which the application starts. 
 */
package pl.polsl.flota.view;

//TODO: Dodac komentarze na poczatku klas. 
//TODO: Zmienic kolejnosc pierwsze opis potem autor.
//TODO: Tworzenie samochodow nie za pomoca konstruktora tylko budowniczgo - przyklad zakomentiwany.
//TODO: Usunac zbedne entery przed klamrami.
//- zdefiniowanie i wykorzystanie typu wyliczeniowego, 
//- wykorzystanie adnotacji. 
//Testy jednostkowe dla klas modelu 
//- testy zbiorcze. 
//Uwaga: klasy modelu nie mogą zawierać składników statycznych. 
//Powinny za to sytuacje nietypowe sygnalizować zgłaszaniem wyjątków. 

//FIXME: IMPLEMNETACJA r3 do 18/11/2011 ;)
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import pl.polsl.flota.exceptions.ElementAlredyExists;
import pl.polsl.flota.exceptions.ElementNotFound;
import pl.polsl.flota.exceptions.MenuItemNotFound;
import pl.polsl.flota.helpers.*;

/**
 * This is the main class of the application instance.
 * 
 * @author Marcin Jabrzyk
 */
public final class MainWindow {
	List<String> menuAdminMain = new ArrayList<String>();
	List<String> menuAdminDriver = new ArrayList<String>();
	List<String> menuDriverLvl1 = new ArrayList<String>();
	AdminView adminView;
	DriverView driverView;
	Integer currentUserId;
	
	static final int AD_DODAJ_KIEROWCE = 1;
	static final int AD_PRZEGLADAJ_KIEROWCOW = 2;
	static final int AD_ZMIEN_HASLO = 3;
	static final int AD_USUN_KIEROWCE = 4;
	static final int AD_COFNIJ_DO_MENU = 5;

	static final int D_ZANOTUJ_TANKOWANIE = 1;
	static final int D_ZMIEN_POJAZD = 2;
	static final int D_WYLOGUJ = 3;
	static final int D_WYJSCIE = 4;

	/**
	 * Default constructor which initializes some values which are global for
	 * whole Application.
	 */
	public MainWindow() {
		// Values of menu for level 1 of the Application
//		menuAdminMain.add("	1) Dodaj pojazd.");
//		menuAdminMain.add("	2) Przeglądaj pojazdy.");
//		menuAdminMain.add("	3) Edytuj/Przeglądaj pojazd.");
//		menuAdminMain.add("	4) Usuń pojazd.");
//		menuAdminMain.add("	5) Kierowca.");
//		menuAdminMain.add("	6) Wyloguj.");
//		menuAdminMain.add("	7) Wyjście z programu.");
/*		menuAdminMain.add(AdminMenu.AM_DODAJ_POJAZD.toString());
		menuAdminMain.add(AdminMenu.AM_PRZEGLADAJ_POJAZDY.toString());
		menuAdminMain.add(AdminMenu.AM_EDYTUJ_POJAZD.toString());
		menuAdminMain.add(AdminMenu.AM_USUN_POJAZD.toString());
		menuAdminMain.add(AdminMenu.AM_KIEROWCA.toString());
		menuAdminMain.add(AdminMenu.AM_WYLOGUJ.toString());
		menuAdminMain.add(AdminMenu.AM_WYJSCIE.toString());
*/
		// This is menu for admin - "kierowca"
		menuAdminDriver.add(" 1) Dodaj kierowcę.");
		menuAdminDriver.add(" 2) Przeglądaj kierowców.");
		menuAdminDriver.add(" 3) Zmień hasło.");
		menuAdminDriver.add(" 4) Usuń kierowcę.");
		menuAdminDriver.add(" 5) Cofnij do menu głównego");

		// This menu is for the car driver
		menuDriverLvl1.add("	1) Zanotuj tankowanie.");
		menuDriverLvl1.add("	2) Zmień pojazd.");
		menuDriverLvl1.add("	3) Wyloguj.");
		menuDriverLvl1.add("	4) Wyjście.");

		currentUserId = -2;

		adminView = new AdminView();
		driverView = new DriverView();

	}

	/**
	 * @param args
	 *            Runtime args
	 */
	public static void main(String[] args) {
		MainWindow mywindow = new MainWindow();

		if (args.length < 2) {
			System.out
					.println("Podaj nazwy pliku z użytkownikami i samochodami");
			System.out.println("np. flota users.json cars.json ");
			System.exit(0);
		}
		System.out.println("Witaj w programie Flota.");
		try {
			mywindow.adminView.setFileNameUserList(args[0]);
			mywindow.driverView.setFileNameUserList(args[0]);
			mywindow.adminView.setFileNameCarList(args[1]);
			mywindow.driverView.setFileNameCarList(args[1]);
		} catch (IOException e) {
			System.out.println("Błąd podczas otwierania pliku.");
			System.exit(0);
		}

		while (true) {
			mywindow.currentUserId = loginScreen(mywindow.currentUserId,
					mywindow);
			Helpers.clearScren();
			if (mywindow.currentUserId < 0) {
				System.out.println("Podaj prawidłowy login i hasło");
				mywindow.currentUserId = loginScreen(mywindow.currentUserId,
						mywindow);
			} else {
				if (mywindow.adminView.userIsAdmin(mywindow.currentUserId) == true) {
					mainMenuAdmin(mywindow);
				} else {
					mainMenuDriver(mywindow);
				}
			}
		}
	}

	/**
	 * Login screen at startup of the app. If currentId is valid then simply
	 * returns out of the function.
	 * 
	 * @param currentId
	 *            te id of current working user
	 * @param mywindow
	 *            refrence to current window
	 * @return the userId
	 */
	private static Integer loginScreen(Integer currentId, MainWindow mywindow) {
		if (currentId < 0) {
			Scanner scanner = new Scanner(System.in);
			System.out.println("Podaj login: ");
			String login = scanner.nextLine();
			System.out.println("Podaj hasło: ");
			String passwd = scanner.nextLine();

			return mywindow.adminView.checkUser(login, passwd);

		}
		return currentId;

	}

	/**
	 * This function generates and handles the main menu for application admin.
	 * 
	 * @param mywindow
	 */
	private static void mainMenuAdmin(MainWindow mywindow) {
		//Integer retVal = Helpers.menuToOptionId(mywindow.menuAdminMain);
		AdminMenu retVal = null;
		try {
		retVal = Helpers.presentAdminMenuAndGetValue(/*mywindow.menuAdminMain*/);
		} catch (MenuItemNotFound e) {
			System.out.println("Podano nieprawidłową wartość. Podaj ponownie.");
			mainMenuAdmin(null);
		}
		switch (retVal) {
		case AM_DODAJ_POJAZD : {
			try {
				mywindow.adminView.addCar();
			} catch (ElementAlredyExists e) {
				System.out.println("Taki samochód już istnieje.");
			}
			break;
		}
		case  AM_PRZEGLADAJ_POJAZDY: {
			mywindow.adminView.listCars();
			break;
		}
		case AM_EDYTUJ_POJAZD: {
			try {
				mywindow.adminView.editOrViewCar();
			} catch (ElementNotFound e) {
				System.out
						.println("Samochód o podanych parametrach nie został znaleiziony.");
			}

			break;
		}
		case AM_USUN_POJAZD: {
			try {
				mywindow.adminView.deleteCar();
			} catch (ElementNotFound e) {
				System.out
						.println("Samochód o podanych parametrach nie został znaleiziony.");
			}
			break;
		}
		case AM_KIEROWCA : {
			Helpers.clearScren();
			mainMenuAdminDriver(mywindow);
			break;
		}
		case AM_WYLOGUJ: {
			mywindow.currentUserId = -2;
			mywindow.adminView.save();
			break;
		}
		case AM_WYJSCIE : {
			mywindow.adminView.save();
			System.exit(0);
		}
		}
	}

	/**
	 * This function generates and handles Driver section in admin menu
	 * 
	 * @param mywindow
	 */
	private static void mainMenuAdminDriver(MainWindow mywindow) {
		//Integer retVal = Helpers.menuToOptionId(mywindow.menuAdminDriver);
		Integer retVal = 0;
		switch (retVal) {
		case AD_DODAJ_KIEROWCE: {
			try {
				mywindow.adminView.addUser();
			} catch (ElementAlredyExists e) {
				System.out.println("Taki użytkownik już istnieje");
			}
			break;
		}
		case AD_PRZEGLADAJ_KIEROWCOW: {
			mywindow.adminView.listUsers();
			break;
		}
		case AD_ZMIEN_HASLO: {
			try {
				mywindow.adminView.editUser();
			} catch (ElementNotFound e) {
				System.out.println("Podano błędne id użytkownika.");
			}
			break;
		}
		case AD_USUN_KIEROWCE: {
			try {
				mywindow.adminView.deleteUser();
			} catch (ElementNotFound e) {
				System.out.println("Podano błędne id użytkownika.");
			}
			break;
		}
		case AD_COFNIJ_DO_MENU: {
			Helpers.clearScren();
			mainMenuAdmin(mywindow);
			break;
		}
		}
	}

	/**
	 * This function generates and handles Driver menu
	 * 
	 * @param mywindow
	 */
	private static void mainMenuDriver(MainWindow mywindow) {
		//Integer retVal = Helpers.menuToOptionId(mywindow.menuDriverLvl1);
		Integer retVal = 0;
		switch (retVal) {
		case D_ZANOTUJ_TANKOWANIE: {
			try {
				Helpers.clearScren();
				mywindow.driverView.userRefuel(mywindow.currentUserId);
			} catch (NumberFormatException e) {
				System.out
						.println("Błąd podczas dodawania tankowania. Sprawdź format danych.");
			} catch (ElementNotFound e) {
				System.out.println("Błąd podczas dodawania tankowania.");
			}
			break;
		}
		case D_ZMIEN_POJAZD: {
			try {
				Helpers.clearScren();
				mywindow.driverView.userChangeCar(mywindow.currentUserId);
			} catch (ElementNotFound e) {
				System.out
						.println("Podano nieprawidłowy numer rejestracyjny pojazdu.");
			}
			break;
		}
		case D_WYLOGUJ: {
			mywindow.currentUserId = -2;
			mywindow.driverView.save();
			break;
		}
		case D_WYJSCIE: {
			mywindow.driverView.save();
			System.exit(0);
		}
		}
	}
}
