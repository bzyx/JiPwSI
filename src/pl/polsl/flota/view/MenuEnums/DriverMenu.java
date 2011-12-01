/**
 * This is a Enum with values and descriptions of Driver menu. 
 */
package pl.polsl.flota.view.MenuEnums;


/**
 * Enum class for Driver menu in the main screen of the application for User.
 *
 */
public enum DriverMenu implements IMenuEnum {
	DRIVER_ADD_REFUEL(1,"Zanotuj tankowanie."),
	DRIVER_CHANGE_CAR(2,"Zmień pojazd."),
	DRIVER_LOGOUT(3,"Wyloguj."),
	DRIVER_EXIT(4,"Wyjście.")
	;
	
	private int value;
	private String description;
	
	/**
	 * Constructor of this enum.
	 */
	DriverMenu(int val, String desc){
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
