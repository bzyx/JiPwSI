/**
 * 
 */
package pl.polsl.flota.view;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import pl.polsl.flota.controller.CarController;
import pl.polsl.flota.controller.UserController;
import pl.polsl.flota.exceptions.ElementAlredyExists;
import pl.polsl.flota.exceptions.ElementNotFound;
import pl.polsl.flota.helpers.Helpers;

/**
 * @author Marcin Jabrzyk
 * @since 27.10.2011 1.1.2
 */
public class AdminView {

	CarController carController;
	UserController userController;
	String fileNameCarList;
	String fileNameUserList;
	Scanner scanner;

	/**
	 * Contsturcor of AdminVie
	 */
	public AdminView() {
		super();
		this.scanner = new Scanner(System.in);
	}

	/**
	 * A View method to Add a car.
	 * 
	 * @throws ElementAlredyExists
	 */
	public void addCar() throws ElementAlredyExists {
		Helpers.clearScren();

		// carController.init(fileName);
		System.out.println("Podaj numer rejestracyjny: ?");
		String userRegNumber = scanner.nextLine();
		// FIXME: Validate a RegNumber?
		System.out.println("Podaj nazwę pojazdu [Marka Model]: ?");
		String userCarName = scanner.nextLine();
		System.out.println("Podaj przebieg pojazdu: ?");
		String userDistance = scanner.nextLine();
		// FIXME: Is a int, and more than 0?
		Integer userDistanceInt = 0;
		try {
			userDistanceInt = Integer.parseInt(userDistance, 10);
		} catch (NumberFormatException e) {
			System.out
					.println("AdminView addCar should be an Integer. Look at TODO");
		}
		System.out.println("Podaj średnie spalanie: ?");
		String userConsumpiton = scanner.nextLine();
		// FIXME: Is a float, and more than 0?
		Float userConsumpitonFloat = (float) 0.0;
		try {
			userConsumpitonFloat = Float.parseFloat(userConsumpiton);
		} catch (NumberFormatException e) {
			System.out
					.println("AdminView addCar should be an Float. Look at TODO");
		}
		carController.addCar(userRegNumber, userCarName, userDistanceInt,
				userConsumpitonFloat);
	}

	/**
	 * A View to delete a Car
	 * 
	 * @throws ElementNotFound
	 */
	public void deleteCar() throws ElementNotFound {
		Helpers.clearScren();
		System.out
				.println("Podaj numer rejestracyjny samochodu lub jego nazwę: ?");
		String userTypedIn = scanner.nextLine();

		List<String> carParams = null;
		carParams = carController.findCar(userTypedIn);
		System.out.println("Czy usunąć samochód [" + carParams.get(1)
				+ "] nr rej. [" + carParams.get(0) + "] [T/N]");
		String wantToDelete = scanner.nextLine();
		if (wantToDelete.toUpperCase().contains("T")) {
			carController.deleteCar(carParams.get(0));
		}

	}

	/**
	 * A method of View to present a one car it has an ability to edit this Car.
	 * 
	 * @throws ElementNotFound
	 */
	public void editOrViewCar() throws ElementNotFound {
		Helpers.clearScren();
		System.out
				.println("Podaj numer rejestracyjny samochodu lub jego nazwę: ?");
		String userTypedIn = scanner.nextLine();

		List<String> carParams = null;
		carParams = carController.findCar(userTypedIn);

		System.out.println("Numer rejestarcyjny: " + carParams.get(0));
		System.out.println("Nazwa pojazdu:       " + carParams.get(1));
		System.out.println("Przebieg początkowy: " + carParams.get(2));
		System.out.println("Spalanie średnie:    " + carParams.get(3));
		if (carParams.size() > 3) {
			System.out.println("Tankowania         ");
			System.out
					.println("Data:          Przebieg: 		Ilość paliwa:		Wartość:");
			for (Integer i = 4; i < carParams.size(); i++) {
				System.out.println(carParams.get(i));
			}
		}

		System.out.println("Czy chcesz edytować ten pojazd [T/N]?");
		String wantEdit = scanner.nextLine();
		if (wantEdit.toUpperCase().contains("T")) {
			// User want to edit
			System.out.println("Podaj nową nazwę pojazdu [" + carParams.get(1)
					+ "] lub wpisz '-' aby pozostawić ?");
			String userCarName = scanner.nextLine();
			System.out.println("Podaj nowy przebieg pojazdu ["
					+ carParams.get(2)
					+ "] lub wciśnij Enter- aby pozostawić ?");
			String userDistance = scanner.nextLine();
			System.out.println("Podaj średnie spalanie: [" + carParams.get(3)
					+ "] lub wciśnij Enter aby pozostawić ?");
			String userConsumpiton = scanner.nextLine();
			carController.editCar(carParams.get(0), userCarName, userDistance,
					userConsumpiton);
		}

	}

	/**
	 * View method presnt all Cars
	 */
	public void listCars() {
		Helpers.clearScren();
		List<String> carList;
		carList = carController.getCarStringList();
		System.out.println("Numer rej.   Nazwa   Przebieg   Id kierowcy   ");
		for (String carString : carList) {
			System.out.println(carString);
		}
	}

	/**
	 * A helper method to call save method from controller.
	 */
	public void save() {
		carController.save(fileNameCarList);
		userController.save(fileNameUserList);

	}

	/**
	 * @param fileName
	 *            the fileName to set
	 * @throws IOException
	 */
	public void setFileNameCarList(String fileName) throws IOException {
		this.fileNameCarList = fileName;
		carController = new CarController(fileName);
	}

	/*
	 * Here starts part for Admin - Kierowca
	 */

	/**
	 * Set a fileName of User file.
	 * 
	 * @param fileName
	 * @throws IOException
	 */
	public void setFileNameUserList(String fileName) throws IOException {
		this.fileNameUserList = fileName;
		userController = new UserController(fileName);
	}

	/**
	 * A View method to add a User.
	 * 
	 * @throws ElementAlredyExists
	 */
	public void addUser() throws ElementAlredyExists {
		Helpers.clearScren();

		System.out.println("Podaj nazwę użytkownika : ?");
		String userName = scanner.nextLine();
		System.out.println("Podaj hasło użytkownika : ?");
		String userPassword = scanner.nextLine();
		System.out.println("Podaj pełną nazwę użytkownika : ?");
		String userFullName = scanner.nextLine();

		userController.addUser(userName, userPassword, userFullName);

	}

	/**
	 * View method that list all users.
	 */
	public void listUsers() {
		Helpers.clearScren();
		List<String> userList;
		userList = userController.getUserStringList();
		System.out.println("Id Nazwa użytkownika Pełna Nazwa");
		for (String userString : userList) {
			System.out.println(userString);
		}

	}

	/**
	 * View method that edit a User.
	 * 
	 * @throws ElementNotFound
	 */
	public void editUser() throws ElementNotFound {
		Helpers.clearScren();
		System.out
				.println("Podaj id użytkownika, którego hasło chcesz zmienić: ?");
		String userId = scanner.nextLine();
		System.out.println("Podaj nowe hasło dla użytkownika: ?");
		String userPassword = scanner.nextLine();
		userController.editUser(userId, userPassword);
	}

	/**
	 * View method that deletes a User
	 * 
	 * @throws ElementNotFound
	 */
	public void deleteUser() throws ElementNotFound {
		Helpers.clearScren();
		System.out.println("Podaj id użytkownika, którego chcesz usunąć: ?");
		String userId = scanner.nextLine();
		userController.deleteUser(userId);
	}

	/**
	 * View method that checks is the user is valid. Calls the controller
	 * method.
	 * 
	 * @param userName
	 * @param userPassword
	 * @return
	 */
	public Integer checkUser(String userName, String userPassword) {
		return userController.checkUser(userName, userPassword);
	}

	/**
	 * View method that checks if user is admin. Calls the controller method.
	 * 
	 * @param userId
	 * @return
	 */
	public Boolean userIsAdmin(Integer userId) {
		return userController.isAdmin(userId);
	}

}
