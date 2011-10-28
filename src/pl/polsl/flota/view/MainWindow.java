/**
 * 
 */
package pl.polsl.flota.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import pl.polsl.flota.exceptions.ElementAlredyExists;
import pl.polsl.flota.exceptions.ElementNotFound;
import pl.polsl.flota.helpers.*;

/**
 * 
 * @author Marcin Jabrzyk
 * @since 21/10/2011
 */
public final class MainWindow {

	List<String> menuAdminMain = new ArrayList<String>();
	List<String> menuAdminDriver = new ArrayList<String>();
	List<String> menuDriverLvl1 = new ArrayList<String>();
	AdminView adminView;
	DriverView driverView;
	Integer currentUserId;

	/**
	 * Default constructor which initializes some values which are global for
	 * whole Application.
	 * 
	 * @since 1.0.0 21/10/2011
	 */
	public MainWindow() {
		// Values of menu for level 1 of the Application
		menuAdminMain.add("	1) Dodaj pojazd.");
		menuAdminMain.add("	2) Przeglądaj pojazdy.");
		menuAdminMain.add("	3) Edytuj/Przeglądaj pojazd.");
		menuAdminMain.add("	4) Usuń pojazd.");
		menuAdminMain.add("	5) Kierowca.");
		menuAdminMain.add("	6) Wyloguj.");
		menuAdminMain.add("	7) Wyjście z programu.");

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
	 * @since 1.0.0 21/10/2011
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
	 * Login screen at startup of the app.
	 * If currentId is valid then simply returns out of the function.
	 * @param currentId te id of current working user
	 * @param mywindow refrence to current window
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
	 * @since 1.0.0 24/10/2011
	 */
	private static void mainMenuAdmin(MainWindow mywindow) {
		Integer retVal = Helpers.menuToOptionId(mywindow.menuAdminMain);
		switch (retVal) {
		case 1: {
			// Dodaj pojazd
			try {
				mywindow.adminView.addCar();
			} catch (ElementAlredyExists e) {
				// TODO Auto-generated catch block
				System.out.println("Taki samochód już istnieje.");
				// e.printStackTrace();
			}
			break;
		}
		case 2: {
			// Przeglądaj pojazd
			mywindow.adminView.listCars();
			break;
		}
		case 3: {
			// Edytuj pojazd
			try {
				mywindow.adminView.editOrViewCar();
			} catch (ElementNotFound e) {
				System.out
						.println("Samochód o podanych parametrach nie został znaleiziony.");
			}

			break;
		}
		case 4: {
			// Usuń pojazd
			try {
				mywindow.adminView.deleteCar();
			} catch (ElementNotFound e) {
				System.out
						.println("Samochód o podanych parametrach nie został znaleiziony.");
			}
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
			mywindow.currentUserId = -2;
			mywindow.adminView.save();
			// Wyloguj do okna głównego
			break;
		}
		case 7: {
			// Wyjście z programu
			mywindow.adminView.save();
			System.exit(0);
		}
		}
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
			try {
				mywindow.adminView.addUser();
			} catch (ElementAlredyExists e) {
				// TODO Auto-generated catch block
				System.out.println("Taki użytkownik już istnieje");
			}
			break;
		}
		case 2: {
			// Przeglądaj kierowców
			mywindow.adminView.listUsers();
			break;
		}
		case 3: {
			// Edytuj kierowcę
			try {
				mywindow.adminView.editUser();
			} catch (ElementNotFound e) {
				System.out.println("Podano błędne id użytkownika.");
			}
			break;
		}
		case 4: {
			// Usuń kierowcę
			try {
				mywindow.adminView.deleteUser();
			} catch (ElementNotFound e) {
				System.out.println("Podano błędne id użytkownika.");
			}
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
		Integer retVal = Helpers.menuToOptionId(mywindow.menuDriverLvl1);
		switch (retVal) {
		case 1: {
			// Zanotuj tankowanie
			try {
				Helpers.clearScren();
				mywindow.driverView.userRefuel(mywindow.currentUserId);
			} catch (NumberFormatException | ElementNotFound e) {
				System.out
						.println("Błąd podczas dodawania tankowania. Sprawdź format danych.");
			}
			break;
		}
		case 2: {
			// Zmień pojazd
			try {
				Helpers.clearScren();
				mywindow.driverView.userChangeCar(mywindow.currentUserId);
			} catch (ElementNotFound e) {
				System.out
						.println("Podano nieprawidłowy numer rejestracyjny pojazdu.");
			}
			break;
		}
		case 3: {
			// Wyloguj
			mywindow.currentUserId = -2;
			mywindow.driverView.save();
			break;
		}
		case 4: {
			// Wyjście
			mywindow.driverView.save();
			System.exit(0);
		}
		}
	}
}
