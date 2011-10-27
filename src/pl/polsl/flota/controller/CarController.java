/**
 * 
 */
package pl.polsl.flota.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pl.polsl.flota.exceptions.ElementAlredyExists;
import pl.polsl.flota.exceptions.ElementNotFound;
import pl.polsl.flota.model.Car;
import pl.polsl.flota.model.CarList;
import pl.polsl.flota.model.Refuel;

/**
 * @author bzyx
 *
 */
public class CarController {

	static CarList carList;
	
	/**
	 * 
	 */
	public CarController(String fileName) {
		super();
		carList = new CarList();
		if (carList.getListOfCars().isEmpty()){
			carList.load(fileName);
		}
		
	}

	public void addCar(String userRegNumber, String userCarName,
			Integer userDistanceInt, Float userConsumpitonFloat) throws ElementAlredyExists {
		carList.addItem(new Car(userRegNumber,userCarName,userDistanceInt,userConsumpitonFloat));
		
	}

	public List<String> getCarStringList() {
		//TODO: Values should be separated by a comma.
		//TODO: Think about presenting the name of the Driver
		List<String> returnList = new ArrayList<String>();
		for ( Car car: carList.getListOfCars() ){
			Integer acctualDriverId;
			if (car.getAcctualDriverId() != null) {
				acctualDriverId = car.getAcctualDriverId();
			} else {
				acctualDriverId = -1;
			}
			returnList.add(car.getRegNumber() + " " + car.getName() + " " +
							car.getDistance() + " " + acctualDriverId);
		}
		//System.out.println(returnList);
		return returnList;
	}
	
	public List<String> getCarWithNoDiverStringList() {
		//TODO: Values should be separated by a comma.
		//TODO: Think about presenting the name of the Driver
		List<String> returnList = new ArrayList<String>();
		for ( Car car: carList.getListOfCars() ){
			if (car.getAcctualDriverId() == null) {
				returnList.add( car.getRegNumber() + " " + car.getName() );
			}
		}
		//System.out.println(returnList);
		return returnList;
	}

	public List<String> findCar(String userTydpedIn) throws ElementNotFound {
		List<String> returnList = new ArrayList<String>();
		Car foundCar;
		
		try {
			foundCar = carList.getCarByRegNumber(userTydpedIn);
		} catch (ElementNotFound e) {
			//System.out.println("NIE ZNALEIZIONO PO REJESTRACJI"+ userTydpedIn);
			try {
				foundCar = carList.getCarByName(userTydpedIn).get(0);
			} catch (ElementNotFound e2) {
				//System.out.println("NIE ZNALEIZIONO PO Nazwie"+ userTydpedIn);
				throw new ElementNotFound("CarController: Cant find any car that match criteria.");
			}
		}
		
		returnList.add(foundCar.getRegNumber());
		returnList.add(foundCar.getName());
		returnList.add(foundCar.getDistance().toString());
		returnList.add(foundCar.getAvgConsumpion().toString());
		//returnList.add(foundCar.getAcctualDriverId().toString());
		
		for (Refuel refuel: foundCar.getHistoryOfRefuel()){
			returnList.add(refuel.getDate().toString() + " "+ refuel.getDistance().toString() + " "+ 
							refuel.getValue().toString()+ " "+refuel.getValue().toString() );
		}
		return returnList;
	}

	public void save(String fileName) {
		carList.save(fileName);
		
	}

	public void editCar(String regNumber, String userCarName, String userDistance,
			String userConsumpiton) throws ElementNotFound {
		Car carToEdit = carList.getCarByRegNumber(regNumber);
		
		if (!userCarName.startsWith("-")){
			carToEdit.setName(userCarName);
		}
		
		Boolean excepiton = false;
		Float consumption = (float) 0.0;
		try {
			consumption = Float.parseFloat(userConsumpiton);
		} catch (NumberFormatException e) {
			excepiton = true;
		}
		if (!excepiton){
			carToEdit.setAvgConsumpion(consumption);
		}
		
		excepiton = false;
		Integer distance = 0;
		try {
			distance =Integer.parseInt(userDistance);
		} catch (NumberFormatException e) {
			excepiton = true;
		}
		if (!excepiton){
			carToEdit.setDistance(distance);
		}
		
		
	}

	public void deleteCar(String string) {
		Car car = null;
		try {
			car = carList.getCarByRegNumber(string);
		} catch (ElementNotFound e) {
			System.out.println("CarController: deleteCar car not found");
		}
		carList.deleteItem(car);
	}
	
	public void conntectUserToCar(Integer userId, String carRegNumber) throws ElementNotFound {
		Car foundCar = carList.getCarByRegNumber(carRegNumber);
		foundCar.setAcctualDriverId(userId);
	}

	public void refuel(Integer userId, String distance, String amount,
			String value) throws ElementNotFound, NumberFormatException {
		Integer distanceInt;
	 	Float	amountFloat;
	 	Float   valueFloat;
		try{
			distanceInt = Integer.parseInt(distance);
			amountFloat = Float.parseFloat(amount);
			valueFloat = Float.parseFloat(value);
		} catch (NumberFormatException e) {
			throw new NumberFormatException();
		}
		Car foundCar = carList.getCarForUser(userId);
		foundCar.addRefuel(new Refuel(distanceInt, amountFloat, valueFloat, new Date()));

		
	}

}
