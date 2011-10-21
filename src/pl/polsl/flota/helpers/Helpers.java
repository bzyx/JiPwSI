/**
 * 
 */
package pl.polsl.flota.helpers;

import java.util.List;
import java.util.Scanner;

/**
 * @author Marcin Jabrzyk
 * 
 */
public final class Helpers {

	/**
	 * Gets list of strings with menu options and retuns the proper value typed
	 * by user.
	 * 
	 * @since 21/10/2011
	 * @param menuItems
	 *            Get list of string with formated menu items
	 * @return i-th number of selected option
	 */
	public static int menuToOptionId(List<String> menuItems) {
		Integer howManyItems = menuItems.size();
		Integer returnValue = -1;
		Boolean error = true;
		System.out.println(howManyItems);
		for (String menuItem : menuItems) {
			System.out.println(menuItem);
		}

		Scanner scanner = new Scanner(System.in);

		while (error != false) {

			try {
				System.out.print("Podaj wybór? ");
				String userTypedIn = scanner.nextLine();
				System.out.println("");
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

}
