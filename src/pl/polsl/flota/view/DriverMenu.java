/**
 * This is a Enum with values and descriptions of Driver menu. 
 */
package pl.polsl.flota.view;

/**
 * Enum class for Driver menu in the main screen of the application for User.
 *
 */
public enum DriverMenu implements IMenuEnum {
	D_ZANOTUJ_TANKOWANIE(1,"Zanotuj tankowanie."),
	D_ZMIEN_POJAZD(2,"Zmień pojazd."),
	D_WYLOGUJ(3,"Wyloguj."),
	D_WYJSCIE(4,"Wyjście.")
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
