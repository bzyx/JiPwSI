/**
 * 
 */
package pl.polsl.flota.model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import flexjson.JSONDeserializer;
import flexjson.JSONException;
import flexjson.JSONSerializer;

/**
 * @author Marcin Jabrzyk
 * @since 1.0.1
 */
public class CarList {
	/**
	 * 
	 */
	List<Car> listOfCars;

	/**
	 * @since 1.0.1 24/10/2011
	 * @return
	 */
	List<Car> get() {
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
	 * @since 1.0.1 24/10/2011
	 * @param car
	 */
	public void deleteItem(Car car) {
		// obsługa wg. numeru rej
	}

	/**
	 * @since 1.0.1 24/10/2011
	 * @param car
	 */
	public void updateItem(Car car) {

	}

	/**
	 * @since 1.0.1 24/10/2011
	 * @param car
	 */
	public void addItem(Car car) {
		listOfCars.add(car);
	}

	/**
	 * @since 1.0.1 24/10/2011
	 * @param car
	 * @return
	 */
	public Car getItem(Car car) {
		// TODO: To jest tylko zaślepka
		return this.listOfCars.get(0);
	}

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

}
