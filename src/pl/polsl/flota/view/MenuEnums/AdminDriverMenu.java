/**
 * This is a Enum with values and descriptions of Admin->Driver menu. 
 */
package pl.polsl.flota.view.MenuEnums;


/**
 * Enum class for Admin->Driver menu in the main screen of the application.
 *
 */
public enum AdminDriverMenu implements IMenuEnum {
	AD_DODAJ_KIEROWCE(1,"Dodaj kierowcę."),
	AD_PRZEGLADAJ_KIEROWCOW(2, "Przeglądaj kierowców."),
	AD_ZMIEN_HASLO(3 ,"Zmień hasło."),
	AD_USUN_KIEROWCE(4, "Usuń kierowcę."),
	AD_COFNIJ_DO_MENU(5, "Cofnij do menu głównego");

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
