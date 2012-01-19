/**
 * A a class for a CarList model witch is a List of Cars.
 */
package pl.polsl.flota.model;

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
     * Read the Car and Refuel records and creates java objects.
     *
     * @param fileName name of databae file
     * @since 1.0.1 24/10/2011
     */
    public void load(String fileName) {
        Connection connection = null;
        try {
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
                System.out.println(rs.getInt("actualDriverId"));
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
     * Takes the all Car objects and saves them to database.
     *
     * @param fileName - name of database file
     * @since 1.0.1 24/10/2011
     */
    public void save(String fileName) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(fileName);

            Statement statement = connection.createStatement();
            statement.execute("DELETE FROM Cars");
            statement.execute("DELETE FROM Refuels");

            PreparedStatement addCarStmt = connection.prepareStatement("INSERT INTO Cars (regNumber, name, avgConsumption, actualDriverId, distance) VALUES(?,?,?,?,?) ");
            PreparedStatement addRefuelStmt = connection.prepareStatement("INSERT INTO Refuels (carId, amount, date, distance, value) VALUES(?,?,?,?,?) ");

            for (Car car : this.listOfCars) {
                addCarStmt.setString(1, car.getRegNumber());
                addCarStmt.setString(2, car.getName());
                addCarStmt.setFloat(3, car.getAvgConsumpion());
                //if car has no driver there is a null value so we send -1 to databes as an ivalid id
                addCarStmt.setInt(4, ((car.getAcctualDriverId() == null) ? -1 : car.getAcctualDriverId()));
                addCarStmt.setInt(5, car.getDistance());

                addCarStmt.execute();
                for (Refuel refuel : car.getHistoryOfRefuel()) {
                    addRefuelStmt.setString(1, car.getRegNumber());
                    addRefuelStmt.setFloat(2, refuel.getAmount());
                    //Here we have 2 diffrent DataTime types we must convert before save
                    java.sql.Date sqlDate = new java.sql.Date(refuel.getDate().getTime());
                    addRefuelStmt.setDate(3, sqlDate);
                    addRefuelStmt.setInt(4, refuel.getDistance());
                    addRefuelStmt.setFloat(5, refuel.getValue());

                    addRefuelStmt.execute();
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(CarList.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserList.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
