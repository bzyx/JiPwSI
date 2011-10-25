/**
 * 
 */
package pl.polsl.flota.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Marcin Jabrzyk
 * 
 *         A model of Car in Flota. All the Object will be serialized to file.
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
	 * @since 1.0.1 24/10/2011
	 */
	public Car() {
		super();
		this.historyOfRefuel = new ArrayList<Refuel>();
	}
	/**
	 * @param regNumber
	 * @param name
	 * @param distance
	 * @param avgConsumpion
	 * @since 1.0.1 24/10/2011
	 */
	public Car(String regNumber, String name, Integer distance,
			Float avgConsumpion) {
		super();
		this.regNumber = regNumber;
		this.name = name;
		this.distance = distance;
		this.avgConsumpion = avgConsumpion;
		this.historyOfRefuel = new ArrayList<Refuel>();
	}

	/**
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
