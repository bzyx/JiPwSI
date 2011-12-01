/**
 * This is a Enum with values and descriptions of Admin menu. 
 */
package pl.polsl.flota.view.MenuEnums;


/**
 * Enum class for Admin menu in the main screen of the application.
 * 
 */
public enum AdminMenu implements IMenuEnum {
	ADMIN_MENU_ADD_CAR(1, "Dodaj pojazd."), 
	ADMIN_MENU_VIEW_CARS(2,"Przeglądaj pojazdy."), 
	ADMIN_MENU_EDIT_OR_VIEW_CAR(3,"Edytuj/Przeglądaj pojazd."), 
	ADMIN_MENU_REMOVE_CAR(4, "Usuń pojazd."), 
	ADMIN_MENU_DRIVER(5, "Kierowca."), 
	ADMIN_MENU_LOGOUT(6, "Wyloguj."), 
	ADMIN_MENU_EXIT(7,"Wyjście z programu.");

	private int value;
	private String description;

	/**
	 * Constructor of this enum.
	 */
	AdminMenu(int val, String desc) {
		value = val;
		description = desc;
	}

	/**
	 * Returns the number value of the current item.
	 * @return int Intger value of current item.
	 */
	@Override
	public int value() {
		return value;
	}

	/**
	 * Returns an enum as a formated string.
	 * @return String A formated string with it's number.
	 */
	public String toString() {
		String s = new String();
		s = String.format(" %5d) %s ", value, description);
		return s;
	}
}