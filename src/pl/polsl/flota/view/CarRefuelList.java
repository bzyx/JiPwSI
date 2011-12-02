package pl.polsl.flota.view;

import java.util.List;

import javax.swing.DefaultListModel;

import pl.polsl.flota.controller.CarController;
import pl.polsl.flota.model.Car;
import pl.polsl.flota.model.Refuel;

public class CarRefuelList extends DefaultListModel<String> {

	private static final long serialVersionUID = 1L;
	CarController carController;
	List<Car> listOfCars;
	Car currentCar;
	List<Refuel> listOfRefuel;
	int lastNumOfElements;
	
	public CarRefuelList(CarController carController){
		this.carController = carController;
		listOfCars = carController.getRawList();
		listOfRefuel = null;
	}
	
	public void setCurrentElement(int index){
		currentCar = listOfCars.get(index);
		if (currentCar.getHistoryOfRefuel() != null){
			listOfRefuel = currentCar.getHistoryOfRefuel();
			lastNumOfElements = listOfRefuel.size();
		}
		else {
			lastNumOfElements = 0;
			listOfRefuel = null;
		}
		
		this.fireIntervalRemoved(this, 0, lastNumOfElements);
		this.clear();
		this.fireContentsChanged(this, 0, lastNumOfElements);
		this.fireIntervalAdded(this, 0, lastNumOfElements);
	}
	
    public String getElementAt(int index) {
    	if (listOfRefuel!=null){
    		Refuel tempRefuel = listOfRefuel.get(index);
    		return String.format("%td.%tm.%ty %.2f - %.2f , %d",tempRefuel.getDate(),
																tempRefuel.getDate(),
																tempRefuel.getDate(),
																tempRefuel.getValue(), 
																tempRefuel.getAmount(), 
																tempRefuel.getDistance());
    	} else
    		return null;
      }

      public int getSize() {
    	  return lastNumOfElements;
      }      
}
