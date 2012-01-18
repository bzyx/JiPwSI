/**
 * A a class for a CarList model witch is a List of Cars.
 */
package pl.polsl.flota.model;

import flexjson.JSONDeserializer;
import flexjson.JSONException;
import flexjson.JSONSerializer;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pl.polsl.flota.exceptions.ElementAlredyExists;

/**
 * This class handles a list of car.
 *
 * @author Marcin Jabrzyk
 * @since 1.0.1
 */
public class CarList {

    /**
     * A inside object which is the list of cars.
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
     * @param car a Car object to add
     * @throws ElementAlredyExists
     */
    public void addItem(Car car) throws ElementAlredyExists {
        if (listOfCars.size() > 0) {
            for (Car car_ : this.listOfCars) {
                if (car_.equals(car)
                        || car_.getRegNumber().contentEquals(car.getRegNumber())) {
                    throw new ElementAlredyExists(
                            "Car: addItem - element already exists");
                }
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
     * @param fileName name of file to read
     * @throws IOException
     * @since 1.0.1 24/10/2011
     */
    public void load(String fileName) throws IOException {
        Connection connection = null;
        try {
            /*
             * try { FileReader fileReader = new FileReader(fileName);
             * this.listOfCars = new JSONDeserializer<List<Car>>()
             * .deserialize(fileReader); fileReader.close(); } catch
             * (JSONException e) { System.out.println("Błąd wczytywania
             * pliku."); throw new IOException(); // e.printStackTrace(); }
             * catch (FileNotFoundException e) { System.out.println("Błędna
             * nazwa pliku lub plik nie istnieje."); throw new IOException(); //
             * e.printStackTrace(); } catch (IOException e) {
             * System.out.println("Bład we/wy."); throw new IOException(); //
             * e.printStackTrace(); }
             */
            
            
            
            connection = DriverManager.getConnection(fileName);
            
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Cars");
            
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM REFUELS WHERE carId = ?");
            
            while (rs.next()) {
                Car carFromDb = new Car();
                
                carFromDb.setRegNumber(rs.getString("regNumber"));
                carFromDb.setName(rs.getString("name"));
                carFromDb.setAvgConsumpion(rs.getFloat("avgConsumption"));
                carFromDb.setAcctualDriverId(rs.getInt("actualDriverId"));
                carFromDb.setDistance(rs.getInt("distance"));
                
                preparedStatement.setString(1, carFromDb.getRegNumber());
                ResultSet carRefuelrs = preparedStatement.executeQuery();
                while (carRefuelrs.next()) {
                    Refuel refuel = new Refuel();
                    refuel.setAmount(carRefuelrs.getFloat("amount"));
                    refuel.setDate(carRefuelrs.getDate("date"));
                    refuel.setDistance(carRefuelrs.getInt("distance"));
                    refuel.setValue(carRefuelrs.getFloat("value"));
                    
                    carFromDb.addRefuel(refuel);
                }
                this.listOfCars.add(carFromDb);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CarList.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(CarList.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

    /**
     * Gets the list of Objects and save it encoded in JSON in file.
     *
     * @param fileName - name of file to save
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
    }
}
