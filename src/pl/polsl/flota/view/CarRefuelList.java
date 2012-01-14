package pl.polsl.flota.view;

import java.util.List;
import javax.swing.DefaultListModel;
import pl.polsl.flota.controller.CarController;
import pl.polsl.flota.model.Car;
import pl.polsl.flota.model.Refuel;

/**
 * The Class CarRefuelList.
 */
public class CarRefuelList extends DefaultListModel<String> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The car controller. */
	CarController carController;
	
	/** The list of cars. */
	List<Car> listOfCars;
	
	/** The current car object. */
	Car currentCar;
	
	/** The list of refuel. */
	List<Refuel> listOfRefuel;
	
	/** The last num of elements. */
	int lastNumOfElements;

	/**
	 * Instantiates a new car refuel list an istance of JList.
	 *
	 * @param carController the car controller
	 */
	public CarRefuelList(CarController carController) {
		this.carController = carController;
		listOfCars = carController.getRawList();
		listOfRefuel = null;
	}

	/**
	 * Sets the current element to show/
	 *
	 * @param index the new current element
	 */
	public void setCurrentElement(int index) {
		try {
			currentCar = listOfCars.get(index);
		} catch (ArrayIndexOutOfBoundsException e) {
			//We want to get an element which not exits.
			return;
		}
				
		if (currentCar.getHistoryOfRefuel() != null) {
			listOfRefuel = currentCar.getHistoryOfRefuel();
			lastNumOfElements = listOfRefuel.size();
		} else {
			lastNumOfElements = 0;
			listOfRefuel = null;
		}

		this.fireIntervalRemoved(this, 0, lastNumOfElements);
		this.clear();
		this.fireContentsChanged(this, 0, lastNumOfElements);
		this.fireIntervalAdded(this, 0, lastNumOfElements);
	}


	/**
	 * Reders a value of a index row i JList.
	 * 
	 * @param index the index number
	 */
	public String getElementAt(int index) {
		if (listOfRefuel != null) {
			Refuel tempRefuel = listOfRefuel.get(index);
			return String.format("%td.%tm.%ty %.2f - %.2f , %d",
					tempRefuel.getDate(), tempRefuel.getDate(),
					tempRefuel.getDate(), tempRefuel.getValue(),
					tempRefuel.getAmount(), tempRefuel.getDistance());
		} else
			return null;
	}

	/**
	 * Returns a number of items in a JList.
	 * 
	 */
	public int getSize() {
		return lastNumOfElements;
	}
}
