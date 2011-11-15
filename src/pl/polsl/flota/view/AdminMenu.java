///**
// * 
// */
package pl.polsl.flota.view;

import pl.polsl.flota.exceptions.MenuItemNotFound;


	public enum AdminMenu implements IMenuEnum<AdminMenu> { 
		AM_DODAJ_POJAZD(1, "Dodaj pojazd."), 
		AM_PRZEGLADAJ_POJAZDY(2, "Przeglądaj pojazdy."), 
		AM_EDYTUJ_POJAZD(3, "Edytuj/Przeglądaj pojazd."), 
		AM_USUN_POJAZD(4, "Usuń pojazd."), 
		AM_KIEROWCA(5, "Kierowca."), 
		AM_WYLOGUJ(6, "Wyloguj."),
		AM_WYJSCIE(7, "Wyjście z programu.");
		
		private int value;
		private String description;
		
		AdminMenu(int val, String desc){
			value = val;
			description = desc;
		}
		
		public int value(){
			return value;
		}
		
		public String toString(){
			String s = new String();
			s = String.format(" %5d) %s ", value,description );
			return s;//new String(value + ") "+ description);
		}
		
		public AdminMenu enumForInt(int enumNumber) throws MenuItemNotFound{
			for (AdminMenu enumVal : AdminMenu.values()){
				if (enumNumber == enumVal.value()){
					return enumVal;
				}
			}
			throw new MenuItemNotFound("AdminMenu for not found for val "+ enumNumber); 
		}
		
	}

///**
// * @author bzyx
// *
// */
//public enum AdmimMenuEnum implements IMenuEnum {
//	AM_DODAJ_POJAZD(1, "Dodaj pojazd."), 
//	AM_PRZEGLADAJ_POJAZDY(2, "Przeglądaj pojazdy."), 
//	AM_EDYTUJ_POJAZD(3, "Edytuj/Przeglądaj pojazd."), 
//	AM_USUN_POJAZD(4, "Usuń pojazd."), 
//	AM_KIEROWCA(5, "Kierowca."), 
//	AM_WYLOGUJ(6, " Wyloguj."),
//	AM_WYJSCIE(7, "Wyjście z programu.");
//	
//    public final int enumValue;
//    private final String txt;
//    
//    AdmimMenuEnum(int value, String menuDescription){
//    	enumValue = value;
//    	txt = menuDescription;
//    }
//    
//    public int value(){
//    	return enumValue;
//    }
//    
//	@Override
//    public String toString() {
//		return new String(enumValue + ") "+txt );
//	}
//	
//	public AdmimMenuEnum enumFromInt(int enumVal){
//		
//		for (AdmimMenuEnum val : AdmimMenuEnum.values()){
//			if (val.value() == enumVal)
//					return val;
//		}
//		
//		return AdmimMenuEnum.AM_DODAJ_POJAZD;
//	}
//
//}
