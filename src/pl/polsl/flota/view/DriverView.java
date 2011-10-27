/**
 * 
 */
package pl.polsl.flota.view;


import java.util.List;
import java.util.Scanner;

import pl.polsl.flota.controller.CarController;
import pl.polsl.flota.controller.UserController;
import pl.polsl.flota.exceptions.ElementNotFound;
/**
 * @author Marcin Jabrzyk
 *
 */
public class DriverView {
	
	CarController carController;
	UserController userController;
	String fileNameCarList;
	String fileNameUserList;
	Scanner scanner;

	public DriverView(){
		super();
		scanner = new Scanner(System.in);
	}
	
	/**
	 * @param fileName
	 *            the fileName to set
	 */
	public void setFileNameCarList(String fileName) {
		this.fileNameCarList = fileName;
		carController = new CarController(fileName);
	}
		
	public void setFileNameUserList(String fileName) {
		this.fileNameUserList = fileName;
		userController = new UserController(fileName);
	}
	
	public void save() {
		carController.save(fileNameCarList);
		userController.save(fileNameUserList);

	}
	
	public void userChangeCar(Integer userId) throws ElementNotFound{
		System.out.println("Wybierz jeden z tych samochodów: ");
		List<String> possibleCars = carController.getCarWithNoDiverStringList();
		for (String line : possibleCars){
			System.out.println(line);
		}
		System.out.println("Podaj numer rejestracyjny pojazdu: ");
		String regNum = scanner.nextLine();
		carController.conntectUserToCar(userId, regNum);
	}
	
	public void userRefuel(Integer userId) throws NumberFormatException, ElementNotFound {
		System.out.println("Zanotuj tankowanie ");
		System.out.println("Podaj stan licznika: ");
		String distance = scanner.nextLine();
		System.out.println("Podaj ilośc paliwa: ");
		String amount = scanner.nextLine();
		System.out.println("Podaj kwotę : ");
		String value = scanner.nextLine();
		
		
		carController.refuel(userId, distance, amount, value);
	}
	
	
}
