/**
 * A model class for Car type.
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

	/** The acctual driver id. */
	Integer acctualDriverId;
	
	/** The avg consumpion. */
	Float avgConsumpion;
	
	/** The distance. */
	Integer distance;
	
	/** The history of refuel. */
	List<Refuel> historyOfRefuel;
	
	/** The name. */
	String name;
	
	/** The reg number. */
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
	 * The Class CarBuilder.
	 * A Builder pattern class to simplyfy creating the objects.
	 */
	public static class CarBuilder {
		// Required parameters
		/** The reg number. */
		private final String regNumber;
		
		/** The name. */
		private final String name;
		
		// Optional parameters
		/** The distance. */
		private Integer distance = null;
		
		/** The avg consumpion. */
		private Float avgConsumpion = null;
		
		/**
		 * Instantiates a new car builder.
		 *
		 * @param regestartionNumber the regestartion number
		 * @param name the name
		 */
		public CarBuilder(String regestartionNumber, String name){
			this.regNumber = regestartionNumber;
			this.name = name;
		}
		
		/**
		 * Distance.
		 *
		 * @param distance the distance
		 * @return the car builder
		 */
		public CarBuilder distance(Integer distance){
			this.distance = distance;
			return this;
		}
		
		/**
		 * Consumption.
		 *
		 * @param consumption the consumption
		 * @return the car builder
		 */
		public CarBuilder consumption(Float consumption){
			this.avgConsumpion = consumption;
			return this;
		}
		
		/**
		 * Builds the.
		 *
		 * @return the car
		 */
		public Car build() {
			return new Car(this);
		}
	}
	
	/**
	 * Instantiates a new car.
	 *
	 * @param carBuilder the car builder
	 */
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
	 * @param refuel the refuel
	 * @since 1.0.1 24/10/2011
	 */
	public void addRefuel(Refuel refuel) {
		this.historyOfRefuel.add(refuel);
	}

	/**
	 * Gets the acctual driver id.
	 *
	 * @return the acctualDriverId
	 * @since 1.0.1 24/10/2011
	 */
	public Integer getAcctualDriverId() {
		return acctualDriverId;
	}

	/**
	 * Gets the avg consumpion.
	 *
	 * @return the avgConsumpion
	 * @since 1.0.1 24/10/2011
	 */
	public Float getAvgConsumpion() {
		return avgConsumpion;
	}

	/**
	 * Gets the distance.
	 *
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
	 * Gets the name.
	 *
	 * @return the name
	 * @since 1.0.1 24/10/2011
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the reg number.
	 *
	 * @return the reg_numer
	 * @since 1.0.1 24/10/2011
	 */
	public String getRegNumber() {
		return regNumber;
	}

	/**
	 * Sets the acctual driver id.
	 *
	 * @param acctualDriverId the acctualDriverId to set
	 * @since 1.0.1 24/10/2011
	 */
	public void setAcctualDriverId(Integer acctualDriverId) {
		this.acctualDriverId = acctualDriverId;
	}

	/**
	 * Sets the avg consumpion.
	 *
	 * @param avgConsumpion the avgConsumpion to set
	 * @since 1.0.1 24/10/2011
	 */
	public void setAvgConsumpion(Float avgConsumpion) {
		this.avgConsumpion = avgConsumpion;
	}

	/**
	 * Sets the distance.
	 *
	 * @param distance the distance to set
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
	 * Sets the name.
	 *
	 * @param name the name to set
	 * @since 1.0.1 24/10/2011
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the reg number.
	 *
	 * @param regNumber the reg_numer to set
	 * @since 1.0.1 24/10/2011
	 */
	public void setRegNumber(String regNumber) {
		this.regNumber = regNumber;
	}

}
