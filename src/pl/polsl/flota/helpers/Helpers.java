/**
 * 
 */
package pl.polsl.flota.helpers;

import java.util.List;
import java.util.Scanner;

/**
 * @author Marcin Jabrzyk
 * 
 *         Class with common functions which should not be in M,V or C.
 */
public final class Helpers {

	/**
	 * Gets list of strings with menu options and returns the proper value typed
	 * by user.
	 * 
	 * @since 1.0.0 21/10/2011
	 * @param menuItems
	 *            Get list of string with formated menu items
	 * @return i-th number of selected option
	 */
	public static int menuToOptionId(List<String> menuItems) {
		Integer howManyItems = menuItems.size();
		Integer returnValue = -1;
		Boolean error = true;
		for (String menuItem : menuItems) {
			System.out.println(menuItem);
		}

		Scanner scanner = new Scanner(System.in);

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
