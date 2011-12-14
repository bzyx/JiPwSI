package pl.polsl.flota.model.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import pl.polsl.flota.exceptions.ElementAlredyExists;
import pl.polsl.flota.model.Car;
import pl.polsl.flota.model.CarList;

public class CarListTest {

	@Test
	public void testAddItem() {
		//given
		CarList carList = new CarList();
		try {
			carList.addItem(new Car.CarBuilder("SR10000", "MyName").build());
			carList.addItem(new Car.CarBuilder("SR10003", "MyName1").build());
		} catch (ElementAlredyExists e) {
			fail("An excepiton occured.");
		}
		//when
		Boolean value = (!carList.getListOfCars().isEmpty()) &
						(carList.getListOfCars().size() == 2);
		//then
		assertTrue(value);
	}

	@Test
	public void testDeleteItem() {
		//given
		CarList carList = new CarList();
		Car car1 = new Car.CarBuilder("SR10000", "MyName").build();
		try {
			carList.addItem(car1);
			carList.addItem(new Car.CarBuilder("SR10003", "MyName1").build());
		} catch (ElementAlredyExists e) {
			fail("An excepiton occured.");
		}
		carList.deleteItem(car1);
		//when
		Boolean value = (!carList.getListOfCars().isEmpty()) &
						(carList.getListOfCars().size() == 1);
		//then
		assertTrue(value);
	}

	@Test
	public void testGetListOfCars() {
		//given
		CarList carList = new CarList();
		try {
			carList.addItem(new Car.CarBuilder("SR10000", "MyName").build());
		} catch (ElementAlredyExists e) {
			fail("An excepiton occured.");
		}
		//when
		Integer numberOfItems = carList.getListOfCars().size();
		//then
		assertTrue(numberOfItems>0);
	}

}