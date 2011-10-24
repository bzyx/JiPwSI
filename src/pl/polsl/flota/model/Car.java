/**
 * 
 */
package pl.polsl.flota.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Marcin Jabrzyk
 * 
 *         A model of Car in Flota. All the Object will be serialized to file.
 * @since 1.0.1
 */
public class Car implements Serializable {
	/**
	 * It is required by Serializable interface.
	 */
	private static final long serialVersionUID = -5799913762582741920L;

	String reg_numer;

	/**
	 * @return the reg_numer
	 * @since 1.0.1 24/10/2011
	 */
	public String getReg_numer() {
		return reg_numer;
	}

	/**
	 * @param reg_numer
	 *            the reg_numer to set
	 * @since 1.0.1 24/10/2011
	 */
	public void setReg_numer(String reg_numer) {
		this.reg_numer = reg_numer;
	}

	String name;
	Integer distance;
	Float avgConsumpion;
	Integer acctualDriverId;
	List<Refuel> historyOfRefuel;

	/**
	 * @return the name
	 * @since 1.0.1 24/10/2011
	 */
	public String getName() {
		return name;
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
	 * @return the distance
	 * @since 1.0.1 24/10/2011
	 */
	public Integer getDistance() {
		return distance;
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
	 * @return the avgConsumpion
	 * @since 1.0.1 24/10/2011
	 */
	public Float getAvgConsumpion() {
		return avgConsumpion;
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
	 * @return the acctualDriverId
	 * @since 1.0.1 24/10/2011
	 */
	public Integer getAcctualDriverId() {
		return acctualDriverId;
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
	 * @return the historyOfRefuel
	 * @since 1.0.1 24/10/2011
	 */
	public List<Refuel> getHistoryOfRefuel() {
		return historyOfRefuel;
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
	 * @param reg_numer
	 * @param name
	 * @param distance
	 * @param avgConsumpion
	 * @since 1.0.1 24/10/2011
	 */
	public Car(String reg_numer, String name, Integer distance,
			Float avgConsumpion) {
		super();
		this.reg_numer = reg_numer;
		this.name = name;
		this.distance = distance;
		this.avgConsumpion = avgConsumpion;
		this.historyOfRefuel = new ArrayList<Refuel>();
	}

	/**
	 * @since 1.0.1 24/10/2011
	 */
	public Car() {
		super();
		this.reg_numer = reg_numer;
		this.name = name;
		this.distance = distance;
		this.avgConsumpion = avgConsumpion;
		this.historyOfRefuel = new ArrayList<Refuel>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Car [reg_numer=" + reg_numer + ", name=" + name + ", distance="
				+ distance + ", avgConsumpion=" + avgConsumpion
				+ ", acctualDriverId=" + acctualDriverId + ", historyOfRefuel="
				+ historyOfRefuel + "]";
	}

	/**
	 * @param refuel
	 * @since 1.0.1 24/10/2011
	 */
	public void addRefuel(Refuel refuel) {
		if (this.historyOfRefuel == null) {
			System.out.println("MAM NULLA W REFUEL");
		}
		this.historyOfRefuel.add(refuel);
	}

}
