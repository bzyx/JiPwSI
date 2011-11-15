/**
 * 
 */
package pl.polsl.flota.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pl.polsl.flota.exceptions.ElementAlredyExists;
import pl.polsl.flota.exceptions.ElementNotFound;
import pl.polsl.flota.model.Car;
import pl.polsl.flota.model.CarList;
import pl.polsl.flota.model.Refuel;

/**
 * @author Marcin Jabrzyk
 * 
 * 
 */
public class CarController {

	CarList carList;

	/**
	 * Main CarController which load and initializes the file.
	 * 
	 * @param fileName
	 *            to load
	 * @throws IOException
	 *             thrown from load
	 */
	public CarController(String fileName) throws IOException {
		super();
		carList = new CarList();
		if (carList.getListOfCars().isEmpty()) {
			carList.load(fileName);
		}

	}

	/**
	 * Adds the new Car. From view to model.
	 * 
	 * @param userRegNumber
	 * @param userCarName
	 * @param userDistanceInt
	 * @param userConsumpitonFloat
	 * @throws ElementAlredyExists
	 */
	public void addCar(String userRegNumber, String userCarName,
			Integer userDistanceInt, Float userConsumpitonFloat)
			throws ElementAlredyExists {
		carList.addItem(new Car(userRegNumber, userCarName, userDistanceInt,
				userConsumpitonFloat));

	}

	/**
	 * Presents to view a list of all Cars.
	 * 
	 * @return returns a list of string.
	 */
	public List<String> getCarStringList() {
		// TODO: Values should be separated by a comma.
		// TODO: Think about presenting the name of the Driver
		List<String> returnList = new ArrayList<String>();
		for (Car car : carList.getListOfCars()) {
			Integer acctualDriverId;
			if (car.getAcctualDriverId() != null) {
				acctualDriverId = car.getAcctualDriverId();
			} else {
				acctualDriverId = -1;
			}
			returnList.add(car.getRegNumber() + " " + car.getName() + " "
					+ car.getDistance() + " " + acctualDriverId);
		}
		return returnList;
	}

	/**
	 * Presents to view a list of all Cars without a Driver set.
	 * 
	 * @return returns a list of string.
	 */
	public List<String> getCarWithNoDiverStringList() {
		// TODO: Values should be separated by a comma.
		// TODO: Think about presenting the name of the Driver
		List<String> returnList = new ArrayList<String>();
		for (Car car : carList.getListOfCars()) {
			if (car.getAcctualDriverId() == null) {
				returnList.add(car.getRegNumber() + " " + car.getName());
			}
		}
		return returnList;
	}

	/**
	 * Tries a find a Car with the provided Registration Number or Car name.
	 * 
	 * @param userTydpedIn
	 * @return A list of string like getCarStringList, but only for find Car.
	 * @throws ElementNotFound
	 *             if no element is found.
	 */
	public List<String> findCar(String userTydpedIn) throws ElementNotFound {
		List<String> returnList = new ArrayList<String>();
		Car foundCar;

		try {
			foundCar = getCarByRegistrationNumber(userTydpedIn);
		} catch (ElementNotFound e) {
			try {
				foundCar = getCarByName(userTydpedIn).get(0);
			} catch (ElementNotFound e2) {
				throw new ElementNotFound(
						"CarController: Cant find any car that match criteria.");
			}
		}

		returnList.add(foundCar.getRegNumber());
		returnList.add(foundCar.getName());
		returnList.add(foundCar.getDistance().toString());
		returnList.add(foundCar.getAvgConsumpion().toString());
		// returnList.add(foundCar.getAcctualDriverId().toString());

		for (Refuel refuel : foundCar.getHistoryOfRefuel()) {
			returnList.add(refuel.getDate().toString() + " "
					+ refuel.getDistance().toString() + " "
					+ refuel.getValue().toString() + " "
					+ refuel.getValue().toString());
		}
		return returnList;
	}

	/**
	 * Saves the file. Calls the method from controller.
	 * 
	 * @param fileName
	 */
	public void save(String fileName) {
		carList.save(fileName);

	}

	/**
	 * Edits the Car with the parameters get from View. Finds the Car by
	 * Registration Number.
	 * 
	 * @param regNumber
	 * @param userCarName
	 * @param userDistance
	 * @param userConsumpiton
	 * @throws ElementNotFound
	 */
	public void editCar(String regNumber, String userCarName,
			String userDistance, String userConsumpiton) throws ElementNotFound {
		Car carToEdit = getCarByRegistrationNumber(regNumber);

		if (!userCarName.startsWith("-")) {
			carToEdit.setName(userCarName);
		}

		Boolean excepiton = false;
		Float consumption = (float) 0.0;
		try {
			consumption = Float.parseFloat(userConsumpiton);
		} catch (NumberFormatException e) {
			excepiton = true;
		}
		if (!excepiton) {
			carToEdit.setAvgConsumpion(consumption);
		}

		excepiton = false;
		Integer distance = 0;
		try {
			distance = Integer.parseInt(userDistance);
		} catch (NumberFormatException e) {
			excepiton = true;
		}
		if (!excepiton) {
			carToEdit.setDistance(distance);
		}

	}

	/**
	 * Deletes a Car find by Registration Number.
	 * 
	 * @param string
	 */
	public void deleteCar(String string) {
		Car car = null;
		try {
			car = getCarByRegistrationNumber(string);
		} catch (ElementNotFound e) {
			System.out.println("CarController: deleteCar car not found");
		}
		carList.deleteItem(car);
	}

	/**
	 * Tries to connect the Car with the carRegNumber to User with userId
	 * 
	 * @param userId
	 * @param carRegNumber
	 * @throws ElementNotFound
	 */
	public void conntectUserToCar(Integer userId, String carRegNumber)
			throws ElementNotFound {
		for (Car car : carList.getListOfCars()) {
			if (car.getAcctualDriverId() == userId) {
				car.setAcctualDriverId(null);
			}
		}
		Car foundCar = getCarByRegistrationNumber(carRegNumber);
		foundCar.setAcctualDriverId(userId);
	}

	/**
	 * Tries to ad a refuel to a Car connected to a User with userId
	 * 
	 * @param userId
	 * @param distance
	 * @param amount
	 * @param value
	 * @throws ElementNotFound
	 * @throws NumberFormatException
	 */
	public void refuel(Integer userId, String distance, String amount,
			String value) throws ElementNotFound, NumberFormatException {
		Integer distanceInt;
		Float amountFloat;
		Float valueFloat;
		try {
			distanceInt = Integer.parseInt(distance);
			amountFloat = Float.parseFloat(amount);
			valueFloat = Float.parseFloat(value);
		} catch (NumberFormatException e) {
			throw new NumberFormatException();
		}
		Car foundCar = getCarForUser(userId);
		foundCar.addRefuel(new Refuel(distanceInt, amountFloat, valueFloat,
				new Date()));

	}
	
	//New methods from Model -> starts here
	
	//TODO: Here ends the car model rest of the methods should go to Car Controller!

	/**
	 * Tries to find cars which have in the name a name parm. If don't found
	 * anything throws ElementNotFound
	 * 
	 * @since 1.0.1 24/10/2011
	 * @param name
	 *            to find
	 * @return A list of cars
	 * @throws ElementNotFound
	 */
	public List<Car> getCarByName(String name) throws ElementNotFound {
		List<Car> result = new ArrayList<Car>();
		for (Car car : carList.getListOfCars()) {
			if (car.getName().contains(name)) {
				result.add(car);
			}
		}
		if (result.size() == 0) {
			throw new ElementNotFound("Car: getCarByRegNumber " + name
					+ " - element not found");
		} else {
			return result;
		}
	}

	/**
	 * Tries to find a car by it's registration number. If not found throws
	 * ElementNotFound
	 * 
	 * @since 1.0.1 24/10/2011
	 * @param regNumber
	 *            a registration number to find
	 * @return a Car Object
	 * @throws ElementNotFound
	 */
	public Car getCarByRegistrationNumber(String regNumber) throws ElementNotFound {
		for (Car car : carList.getListOfCars()) {
			if (car.getRegNumber().contentEquals(regNumber)) {
				return car;
			}
		}
		throw new ElementNotFound("Car: getCarByRegNumber " + regNumber
				+ " - element not found");
	}

	/**
	 * Returns a Car object for a User with a userId
	 * 
	 * @param userId
	 * @return
	 * @throws ElementNotFound
	 */
	public Car getCarForUser(Integer userId) throws ElementNotFound {
		for (Car car : carList.getListOfCars()) {
			if (car.getAcctualDriverId() == userId) {
				return car;
			}
		}
		throw new ElementNotFound("Car: getCarForUser " + userId
				+ " - element not found");
	}

}
