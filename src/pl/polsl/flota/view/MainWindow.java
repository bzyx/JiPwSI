/**
 * This file has a main method. From which the application starts. 
 */
package pl.polsl.flota.view;

import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JWindow;

import pl.polsl.flota.exceptions.ElementAlredyExists;
import pl.polsl.flota.exceptions.ElementNotFound;
import pl.polsl.flota.exceptions.MenuItemNotFound;
import pl.polsl.flota.helpers.*;
import pl.polsl.flota.view.MenuEnums.AdminDriverMenu;
import pl.polsl.flota.view.MenuEnums.AdminMenu;
import pl.polsl.flota.view.MenuEnums.DriverMenu;


/*
 * GUI z wykorzystaniem Swing, 
 * - menu, 
 * - pasek narzędziowy, 
 * - zaawans. komponent (JTable lub JTree), 
 * - zaawans. kontener (JSplitPane lub JTabbedPane) 
 * - okno dialogowe, 
 * - okna komunikatów (JOptionPane) 
 * - obsługa zdarzeń głównego okna (zamknięcie aplikacji, minimalizacja, itp.), 
 * - uruchamianie jako aplikacja. 
 * 
 */
/**
 * This is the main class of the application instance.
 * 
 * @author Marcin Jabrzyk
 */
public final class MainWindow {
	AdminView adminView;
	DriverView driverView;
	Integer currentUserId;
	/**
	 * Default constructor which initializes some values which are global for
	 * whole Application.
	 */
	public MainWindow() {
		currentUserId = -2; //An non-valid user id.

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

		
	    JFrame f = new JFrame("The Frame");
	    f.setSize(300, 300);
	    f.setLocation(100, 100);

	    f.add(new AdminViewSwing());
	    f.setVisible(true);
		System.out.println("Po window.");
		
/*		while (true) {
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
		}*/
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
		AdminMenu retVal = null;
		try {
		retVal = Helpers.presentAdminMenuAndGetValue();
		} catch (MenuItemNotFound e) {
			System.out.println("Podano nieprawidłową wartość. Podaj ponownie.");
			mainMenuAdmin(mywindow);
		}
		switch (retVal) {
		case ADMIN_MENU_ADD_CAR : {
			try {
				mywindow.adminView.addCar();
			} catch (ElementAlredyExists e) {
				System.out.println("Taki samochód już istnieje.");
			}
			break;
		}
		case  ADMIN_MENU_VIEW_CARS: {
			mywindow.adminView.listCars();
			break;
		}
		case ADMIN_MENU_EDIT_OR_VIEW_CAR: {
			try {
				mywindow.adminView.editOrViewCar();
			} catch (ElementNotFound e) {
				System.out
						.println("Samochód o podanych parametrach nie został znaleiziony.");
			}

			break;
		}
		case ADMIN_MENU_REMOVE_CAR: {
			try {
				mywindow.adminView.deleteCar();
			} catch (ElementNotFound e) {
				System.out
						.println("Samochód o podanych parametrach nie został znaleiziony.");
			}
			break;
		}
		case ADMIN_MENU_DRIVER : {
			Helpers.clearScren();
			mainMenuAdminDriver(mywindow);
			break;
		}
		case ADMIN_MENU_LOGOUT: {
			mywindow.adminView.save();
			mywindow.currentUserId = -2;
			break;
		}
		case ADMIN_MENU_EXIT : {
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
		AdminDriverMenu retVal = null;
		try {
		retVal = Helpers.presentAdminDriverMenuAndGetValue();
		} catch (MenuItemNotFound e) {
			System.out.println("Podano nieprawidłową wartość. Podaj ponownie.");
			mainMenuAdminDriver(mywindow);
		}
		switch (retVal) {
		case ADMIN_DRIVER_ADD_DRIVER: {
			try {
				mywindow.adminView.addUser();
			} catch (ElementAlredyExists e) {
				System.out.println("Taki użytkownik już istnieje");
			}
			break;
		}
		case ADMIN_DRIVER_VIEW_DRIVERS: {
			mywindow.adminView.listUsers();
			break;
		}
		case ADMIN_DRIVER_CHANGE_PASSWORD: {
			try {
				mywindow.adminView.editUser();
			} catch (ElementNotFound e) {
				System.out.println("Podano błędne id użytkownika.");
			}
			break;
		}
		case ADMIN_DRIVER_RMOVE_DRIVER: {
			try {
				mywindow.adminView.deleteUser();
			} catch (ElementNotFound e) {
				System.out.println("Podano błędne id użytkownika.");
			}
			break;
		}
		case ADMIN_DRIVER_BACK: {
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
		DriverMenu retVal = null;
		try {
		retVal = Helpers.presentDriverMenuAndGetValue();
		} catch (MenuItemNotFound e) {
			System.out.println("Podano nieprawidłową wartość. Podaj ponownie.");
			mainMenuDriver(mywindow);
		}
		switch (retVal) {
		case DRIVER_ADD_REFUEL: {
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
		case DRIVER_CHANGE_CAR: {
			try {
				Helpers.clearScren();
				mywindow.driverView.userChangeCar(mywindow.currentUserId);
			} catch (ElementNotFound e) {
				System.out
						.println("Podano nieprawidłowy numer rejestracyjny pojazdu.");
			}
			break;
		}
		case DRIVER_LOGOUT: {
			mywindow.driverView.save();
			mywindow.currentUserId = -2;
			break;
		}
		case DRIVER_EXIT: {
			mywindow.driverView.save();
			System.exit(0);
		}
		}
	}
}
