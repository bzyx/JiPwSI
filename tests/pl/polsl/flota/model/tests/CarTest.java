package pl.polsl.flota.model.tests;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import pl.polsl.flota.model.Car;
import pl.polsl.flota.model.Refuel;

public class CarTest {

	@Test
	public void testCarCarBuilder() {
		//given
		Car testCar = new Car.CarBuilder("SR123TS", "myTestCar").distance(10).consumption(100.1f).build();
		//when
		Boolean testValue = testCar.getName().equals("myTestCar") &
							testCar.getRegNumber().equals("SR123TS") &
							testCar.getDistance().equals(10) &
							testCar.getAvgConsumpion().equals(100.1f);
		//then
		assertTrue(testValue);
	}

	@Test
	public void testAddRefuel() {
		//given
		Car testCar = new Car.CarBuilder("SR123TS", "myTestCar").distance(10).consumption(100.1f).build();
		Date testDate = new Date();
		Refuel testRefuel = new Refuel(123,25.4f,59.67f,testDate);
		testCar.addRefuel(testRefuel);
		//when
		Boolean testValue = testCar.getHistoryOfRefuel().contains(testRefuel) & 
				(testCar.getHistoryOfRefuel().size() == 1);
		//then
		assertTrue(testValue);
	}

	@Test
	public void testGetAcctualDriverId() {
		//given
		Car testCar = new Car.CarBuilder("SR123TS", "myTestCar").distance(10).consumption(100.1f).build();
		testCar.setAcctualDriverId(5);
		//when
		Integer testDriverId = testCar.getAcctualDriverId();
		//then
		assertEquals(5, testDriverId.intValue());
	}

	@Test
	public void testGetAvgConsumpion() {
		//given
		Car testCar = new Car.CarBuilder("SR123TS", "myTestCar").distance(10).consumption(100.1f).build();
		testCar.setAvgConsumpion(25.454f);
		//when
		Float testAvgConsumpiton = testCar.getAvgConsumpion();
		//then
		assertEquals(new Float(25.454f), testAvgConsumpiton);
	}

	@Test
	public void testGetDistance() {
		//given
		Car testCar = new Car.CarBuilder("SR123TS", "myTestCar").distance(10).consumption(100.1f).build();
		testCar.setDistance(1510900);
		//when
		Integer testDistance = testCar.getDistance();
		//then
		assertEquals(new Integer(1510900), testDistance);
	}

	@Test
	public void testGetHistoryOfRefuel() {
		//given
		Car testCar = new Car.CarBuilder("SR123TS", "myTestCar").distance(10).consumption(100.1f).build();
		testCar.addRefuel(new Refuel(100, 500f, 200f, new Date()));
		testCar.addRefuel(new Refuel(100, 100f, 100f, new Date()));
		testCar.addRefuel(new Refuel(40, 500f, 200f, new Date()));
		//when
		Integer testHistoryOfRefuelSize = testCar.getHistoryOfRefuel().size();
		//then
		assertEquals(new Integer(3), testHistoryOfRefuelSize);
	}

	@Test
	public void testGetName() {
		//given
		Car testCar = new Car.CarBuilder("SR123TS", "myTestCar").distance(10).consumption(100.1f).build();
		testCar.setName("Toyota Avensis");
		//when
		String name = testCar.getName();
		//then
		assertEquals(new String("Toyota Avensis"), name);
	}

	@Test
	public void testGetRegNumber() {
		//given
		Car testCar = new Car.CarBuilder("SR123TS", "myTestCar").distance(10).consumption(100.1f).build();
		testCar.setRegNumber("SRB1254A");
		//when
		String regNumber = testCar.getRegNumber();
		//then
		assertEquals(new String("SRB1254A"), regNumber);
	}

}