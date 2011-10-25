/**
 * 
 */
package pl.polsl.flota.model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import pl.polsl.flota.exceptions.ElementAlredyExists;
import pl.polsl.flota.exceptions.ElementNotFound;

import flexjson.JSONDeserializer;
import flexjson.JSONException;
import flexjson.JSONSerializer;

/**
 * This class handles a list of car.
 * 
 * @author Marcin Jabrzyk
 * @since 1.0.1
 */
public class CarList {
	/**
	 * A inside object wihch is the list of cars.
	 */
	List<Car> listOfCars;

	/**
	 * Default constructor. Used to prepare listOfCars member, to prevent
	 * operating on null value.
	 * 
	 * @since 1.0.1 24/10/2011
	 */
	public CarList() {
		super();
		this.listOfCars = new LinkedList<Car>();
	}

	/**
	 * Tries to add a new car to list. Throws ElementAlredyExists exception when
	 * the car already exist or the registration number is the same as an other
	 * car in the list.
	 * 
	 * @since 1.0.1 24/10/2011
	 * @param car
	 *            a Car object to add
	 * @throws ElementAlredyExists
	 */
	public void addItem(Car car) throws ElementAlredyExists {
		for (Car car_ : this.listOfCars) {
			if (car_.equals(car)
					|| car_.getRegNumber().contentEquals(car.getRegNumber())) {
				throw new ElementAlredyExists(
						"Car: addItem - element already exists");
			}
		}
		listOfCars.add(car);
	}

	/**
	 * Removes a item from list.
	 * 
	 * @since 1.0.1 24/10/2011
	 * @param car
	 */
	public void deleteItem(Car car) {
		listOfCars.remove(car);
	}

	/**
	 * Tries to get a car by Object if not found throws ElementNotFound
	 * 
	 * @since 1.0.1 24/10/2011
	 * @param car
	 * @return a Car object
	 * @throws ElementNotFound
	 */
	// TODO: Propably to remove
	public Car getCar(Car car) throws ElementNotFound {
		for (Car car_ : this.listOfCars) {
			if (car_.equals(car)) {
				return car_;
			}
		}
		throw new ElementNotFound("Car: getCar - element not found");
	}

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
		for (Car car : this.listOfCars) {
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
	public Car getCarByRegNumber(String regNumber) throws ElementNotFound {
		for (Car car : this.listOfCars) {
			if (car.getRegNumber().contentEquals(regNumber)) {
				return car;
			}
		}
		throw new ElementNotFound("Car: getCarByRegNumber " + regNumber
				+ " - element not found");
	}

	/**
	 * Returns a inside representation.
	 * 
	 * @since 1.0.1 24/10/2011
	 * @return
	 */
	public List<Car> getListOfCars() {
		return listOfCars;
	}

	/**
	 * Reads the file encoded with JSON. All the read Objects are added to
	 * listOfCars member.
	 * 
	 * @param fileName
	 *            name of file to read
	 * @since 1.0.1 24/10/2011
	 */
	// TODO: Wyłączyć println, zamiast tego throws ErrorLoadingFile - z własnych
	// wyjątków
	public void load(String fileName) {
		try {
			FileReader fileReader = new FileReader(fileName);
			this.listOfCars = new JSONDeserializer<List<Car>>()
					.deserialize(fileReader);
			fileReader.close();
		} catch (JSONException e) {
			System.out.println("Błąd wczytywania pliku.");
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			System.out.println("Błędna nazwa pliku lub plik nie istnieje.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Bład we/wy.");
			e.printStackTrace();
		}

	}

	/**
	 * Gets the list of Objects and save it encoded in JSON in file.
	 * 
	 * @param fileName
	 *            - name of file to save
	 * @since 1.0.1 24/10/2011
	 */
	// TODO: Wyłączyć println, zamiast tego throws ErrorLoadingFile - z własnych
	// wyjątków
	public void save(String fileName) {
		try {
			JSONSerializer serializer = new JSONSerializer();
			FileWriter fileWriter = new FileWriter(fileName);
			serializer.include("historyOfRefuel").serialize(this.listOfCars,
					fileWriter);
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error przy zapisie");
		}
		System.out.println("Powinno być zapisane.");
	}

	/**
	 * Updates a car object which has a provided registration number. If can't
	 * find throws ElementNotFound
	 * 
	 * @since 1.0.1 24/10/2011
	 * @param regNumber
	 *            a registration number.
	 * @param car
	 *            a Car object.
	 * @throws ElementNotFound
	 */
	public void updateItemByRegNumber(String regNumber, Car car)
			throws ElementNotFound {
		Boolean isError = true;
		for (Car car_ : this.listOfCars) {
			if (car_.getRegNumber().contentEquals(car.getRegNumber())) {
				car_ = car;
				isError = false;
			}
		}
		if (isError) {
			throw new ElementNotFound("Car: updateItemByRegNumber " + regNumber
					+ " - element not found");
		}
	}

}
