/**
 * 
 */
package pl.polsl.flota.model;

import java.util.Date;

/**
 * @author Marcin Jabrzyk
 * @since 1.0.1
 */
public class Refuel {
	Float amount;
	Date date;
	Integer distance;
	Float value;

	/**
	 * @since 1.0.1 24/10/2011
	 */
	public Refuel() {
		super();
	}

	/**
	 * @param distance
	 * @param amount
	 * @param value
	 * @param date
	 * @since 1.0.1 24/10/2011
	 */
	public Refuel(Integer distance, Float amount, Float value, Date date) {
		super();
		this.distance = distance;
		this.amount = amount;
		this.value = value;
		this.date = date;
	}

	/**
	 * @return the amount
	 * @since 1.0.1 24/10/2011
	 */
	public Float getAmount() {
		return amount;
	}

	/**
	 * @return the date
	 * @since 1.0.1 24/10/2011
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @return the distance
	 * @since 1.0.1 24/10/2011
	 */
	public Integer getDistance() {
		return distance;
	}

	/**
	 * @return the value
	 * @since 1.0.1 24/10/2011
	 */
	public Float getValue() {
		return value;
	}

	/**
	 * @param amount
	 *            the amount to set
	 * @since 1.0.1 24/10/2011
	 */
	public void setAmount(Float amount) {
		this.amount = amount;
	}

	/**
	 * @param date
	 *            the date to set
	 * @since 1.0.1 24/10/2011
	 */
	public void setDate(Date date) {
		this.date = date;
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
	 * @param vale
	 *            the vale to set
	 * @since 1.0.1 24/10/2011
	 */
	public void setValue(Float value) {
		this.value = value;
	}

}
