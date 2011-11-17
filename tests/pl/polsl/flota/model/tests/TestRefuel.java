package pl.polsl.flota.model.tests;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import pl.polsl.flota.model.Refuel;

public class TestRefuel {

	@Test
	public void testRefuelIntegerFloatFloatDate() {
		//given
		Date testDate = new Date();
		Refuel refuel = new Refuel(150000, 12.33f, 69.70f, testDate);
		//when
		Boolean value = refuel.getDistance().equals(150000) &
						refuel.getAmount().equals(12.33f) &
						refuel.getValue().equals( 69.70f) &
						refuel.getDate().equals(testDate);
						
		//then
		assertTrue(value);
	}

	@Test
	public void testGetAmount() {
		//given
		Refuel refuel = new Refuel(150000, 12.33f, 69.70f, new Date());
		refuel.setAmount(150.43f);
		//when
		Float testAmount = refuel.getAmount();
		//then
		assertEquals(new Float(150.43f), testAmount);
	}

	@Test
	public void testGetDate() {
		//given
		Refuel refuel = new Refuel(150000, 12.33f, 69.70f, new Date());
		Date testDate =  new Date();
		refuel.setDate(testDate);
		//when
		Date dateValue = refuel.getDate();
		//then
		assertEquals(testDate, dateValue);
	}

	@Test
	public void testGetDistance() {
		//given
		Refuel refuel = new Refuel(150000, 12.33f, 69.70f, new Date());
		refuel.setDistance(190000);
		//when
		Integer testDistance = refuel.getDistance();
		//then
		assertEquals(new Integer(190000), testDistance);
	}

	@Test
	public void testGetValue() {
		//given
		Refuel refuel = new Refuel(150000, 12.33f, 69.70f, new Date());
		refuel.setValue(150.33f);
		//when
		Float testValue = refuel.getValue();
		//then
		assertEquals(new Float(150.33), testValue);
	}

}