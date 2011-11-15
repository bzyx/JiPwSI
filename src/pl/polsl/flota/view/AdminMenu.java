/**
 * This is a Enum with values and descriptions of Admin menu. 
 */
package pl.polsl.flota.view;

/**
 * Enum class for Admin menu in the main screen of the application.
 * 
 */
public enum AdminMenu implements IMenuEnum {
	AM_DODAJ_POJAZD(1, "Dodaj pojazd."), 
	AM_PRZEGLADAJ_POJAZDY(2,"Przeglądaj pojazdy."), 
	AM_EDYTUJ_POJAZD(3,"Edytuj/Przeglądaj pojazd."), 
	AM_USUN_POJAZD(4, "Usuń pojazd."), 
	AM_KIEROWCA(5, "Kierowca."), 
	AM_WYLOGUJ(6, "Wyloguj."), 
	AM_WYJSCIE(7,"Wyjście z programu.");

	private int value;
	private String description;

	/**
	 * Constructor of this enum.
	 * 
	 */
	AdminMenu(int val, String desc) {
		value = val;
		description = desc;
	}

	/**
	 * Returns the number value of the current item.
	 * @return int Intger value of current item.
	 */
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