/**
 * 
 */
package pl.polsl.flota.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A model of Car in Flota.
 * 
 * @author Marcin Jabrzyk
 * @since 1.0.1
 */
public class Car {

	Integer acctualDriverId;
	Float avgConsumpion;
	Integer distance;
	List<Refuel> historyOfRefuel;
	String name;
	String regNumber;

	/**
	 * Default constructor. Only initializes the array for refuels.
	 * 
	 * @since 1.0.1 24/10/2011
	 */
	public Car() {
		super();
		this.historyOfRefuel = new ArrayList<Refuel>();
	}

	/**
	 * Full constructor with all needed parameters.
	 * 
	 * @param regNumber
	 * @param name
	 * @param distance
	 * @param avgConsumpion
	 * @since 1.0.1 24/10/2011
	 */
//	public Car(String regNumber, String name, Integer distance,
//			Float avgConsumpion) {
//		super();
//		this.regNumber = regNumber;
//		this.name = name;
//		this.distance = distance;
//		this.avgConsumpion = avgConsumpion;
//		this.historyOfRefuel = new ArrayList<Refuel>();
//	}
	public static class CarBuilder {
		// Required parameters
		private final String regNumber;
		private final String name;
		
		// Optional parameters
		private Integer distance = null;
		private Float avgConsumpion = null;
		
		public CarBuilder(String regestartionNumber, String name){
			this.regNumber = regestartionNumber;
			this.name = name;
		}
		
		public CarBuilder distance(Integer distance){
			this.distance = distance;
			return this;
		}
		
		public CarBuilder consumption(Float consumption){
			this.avgConsumpion = consumption;
			return this;
		}
		
		public Car build() {
			return new Car(this);
		}
	}
	
	Car(CarBuilder carBuilder){
		this.regNumber = carBuilder.regNumber;
		this.name = carBuilder.name;
		this.distance = carBuilder.distance;
		this.avgConsumpion = carBuilder.avgConsumpion;
		this.historyOfRefuel = new ArrayList<Refuel>();
	}
	

	/**
	 * Adds a refuel to a current car.
	 * 
	 * @param refuel
	 * @since 1.0.1 24/10/2011
	 */
	public void addRefuel(Refuel refuel) {
		this.historyOfRefuel.add(refuel);
	}

	/**
	 * @return the acctualDriverId
	 * @since 1.0.1 24/10/2011
	 */
	public Integer getAcctualDriverId() {
		return acctualDriverId;
	}

	/**
	 * @return the avgConsumpion
	 * @since 1.0.1 24/10/2011
	 */
	public Float getAvgConsumpion() {
		return avgConsumpion;
	}

	/**
	 * @return the distance
	 * @since 1.0.1 24/10/2011
	 */
	public Integer getDistance() {
		return distance;
	}

	/**
	 * Return a list of refuel's of the current car.
	 * 
	 * @return the historyOfRefuel
	 * @since 1.0.1 24/10/2011
	 */
	public List<Refuel> getHistoryOfRefuel() {
		return historyOfRefuel;
	}

	/**
	 * @return the name
	 * @since 1.0.1 24/10/2011
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the reg_numer
	 * @since 1.0.1 24/10/2011
	 */
	public String getRegNumber() {
		return regNumber;
	}

	/**
	 * @param acctualDriverId
	 *            the acctualDriverId to set
	 * @since 1.0.1 24/10/2011
	 */
	public void setAcctualDriverId(Integer acctualDriverId) {
		this.acctualDriverId = acctualDriverId;
	}

	/**
	 * @param avgConsumpion
	 *            the avgConsumpion to set
	 * @since 1.0.1 24/10/2011
	 */
	public void setAvgConsumpion(Float avgConsumpion) {
		this.avgConsumpion = avgConsumpion;
	}

	/**
	 * @param distance
	 *            the distance to set
	 * @since 1.0.1 24/10/2011
	 */
	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	/**
	 * Sets a new history o refuels to a car object.
	 * 
	 * @param historyOfRefuel
	 *            the historyOfRefuel to set
	 * @since 1.0.1 24/10/2011
	 */
	public void setHistoryOfRefuel(List<Refuel> historyOfRefuel) {
		this.historyOfRefuel = historyOfRefuel;
	}

	/**
	 * @param name
	 *            the name to set
	 * @since 1.0.1 24/10/2011
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param regNumber
	 *            the reg_numer to set
	 * @since 1.0.1 24/10/2011
	 */
	public void setRegNumber(String regNumber) {
		this.regNumber = regNumber;
	}

}
