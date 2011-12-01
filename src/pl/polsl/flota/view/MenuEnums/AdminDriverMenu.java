/**
 * This is a Enum with values and descriptions of Admin->Driver menu. 
 */
package pl.polsl.flota.view.MenuEnums;


/**
 * Enum class for Admin->Driver menu in the main screen of the application.
 *
 */
public enum AdminDriverMenu implements IMenuEnum {
	ADMIN_DRIVER_ADD_DRIVER(1,"Dodaj kierowcę."),
	ADMIN_DRIVER_VIEW_DRIVERS(2, "Przeglądaj kierowców."),
	ADMIN_DRIVER_CHANGE_PASSWORD(3 ,"Zmień hasło."),
	ADMIN_DRIVER_RMOVE_DRIVER(4, "Usuń kierowcę."),
	ADMIN_DRIVER_BACK(5, "Cofnij do menu głównego");

	private int value;
	private String description;
	
	/**
	 * Constructor of this enum.
	 */
	AdminDriverMenu(int val, String desc){
		value=val;
		description=desc;
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
