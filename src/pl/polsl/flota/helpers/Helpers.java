/** 
 * A file with some Helpers used in application. With helps doing things.
 */
package pl.polsl.flota.helpers;

import java.util.Scanner;

import pl.polsl.flota.exceptions.MenuItemNotFound;
import pl.polsl.flota.view.AdminDriverMenu;
import pl.polsl.flota.view.AdminMenu;
import pl.polsl.flota.view.DriverMenu;

/**
 * Class with common functions which should not be in M,V or C.
 * 
 */
public final class Helpers {

	/**
	 * Preset to Admin-user a list of options of menu.
	 * Gets the input from user. And returns this value as
	 * AdminMenu enum value.
	 * 
	 * @return enum value with the chosen menu option.
	 * @throws MenuItemNotFound 
	 */
	public static AdminMenu presentAdminMenuAndGetValue() throws MenuItemNotFound {
		Integer counter = 0;
		for (AdminMenu adminMenuElement : AdminMenu.values()){
			counter++;
			System.out.println(adminMenuElement);
		}
		
		Integer userAnswered = getAnswerFromUser(counter);
		
		for (AdminMenu enumVal : AdminMenu.values()){
			if (userAnswered == enumVal.value()){
				return enumVal;
			}
		}
		
		throw new MenuItemNotFound("Menu option not exist for value :"+userAnswered);

	}
	
	/**
	 * Preset to Admin-user a list of options of secondary level menu Driver.
	 * Gets the input from user. And returns this value as
	 * AdminDriverMenu enum value.
	 * 
	 * @return enum value with the chosen menu option.
	 * @throws MenuItemNotFound 
	 */
	public static AdminDriverMenu presentAdminDriverMenuAndGetValue() throws MenuItemNotFound {
		Integer counter = 0;
		for (AdminDriverMenu adminDriverMenuElement : AdminDriverMenu.values()){
			counter++;
			System.out.println(adminDriverMenuElement);
		}
		
		Integer userAnswered = getAnswerFromUser(counter);
		
		for (AdminDriverMenu enumVal : AdminDriverMenu.values()){
			if (userAnswered == enumVal.value()){
				return enumVal;
			}
		}
		
		throw new MenuItemNotFound("Menu option not exist for value :"+userAnswered);

	}
	
	/**
	 * Preset to Driver-user a list of options to that user.
	 * Gets the input from user. And returns this value as
	 * DriverMenu enum value.
	 * 
	 * @return enum value with the chosen menu option.
	 * @throws MenuItemNotFound 
	 */
	public static DriverMenu presentDriverMenuAndGetValue() throws MenuItemNotFound {
		Integer counter = 0;
		for (DriverMenu driverMenuElement : DriverMenu.values()){
			counter++;
			System.out.println(driverMenuElement);
		}
		
		Integer userAnswered = getAnswerFromUser(counter);
		
		for (DriverMenu enumVal : DriverMenu.values()){
			if (userAnswered == enumVal.value()){
				return enumVal;
			}
		}
		
		throw new MenuItemNotFound("Menu option not exist for value :"+userAnswered);

	}
	
	/**
	 * Gets the input from user. If user type an invalid value, 
	 * gets the input until user provide a valid one.
	 * 
	 * @param howManyItems the last valid value typed by user.
	 * @return Integer with the valid number.
	 */
	private static Integer getAnswerFromUser(Integer howManyItems) {
		Scanner scanner = new Scanner(System.in);

		Integer returnValue = -1;
		Boolean error = true;
		while (error != false) {

			try {
				System.out.print("Podaj wybór? ");
				String userTypedIn = scanner.nextLine();
				returnValue = Integer.parseInt(userTypedIn, 10);
				error = false;
			} catch (NumberFormatException e) {
				System.out.println("Podaj wartość musi być liczbą.");
				error = true;
			}

			if (returnValue < 1 || returnValue > howManyItems) {
				System.out.println("Podana wartość jest spoza zakresu.");
				error = true;
			}

		}
		return returnValue;
	}

	/**
	 * Clears the screen and make a delimiter from the previous menu.
	 * 
	 * @since 1.0.0 24/10/2011
	 */
	public static void clearScren() {
		System.out
				.println("--------------------------------------------------------------------------------");
		System.out.println("");
	}

}
