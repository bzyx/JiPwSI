/**
 * 
 */
package pl.polsl.flota.view;

import pl.polsl.flota.exceptions.MenuItemNotFound;

/**
 * @author bzyx
 *
 */
public interface IMenuEnum<E> {
	public int value();
	public String toString();
	public E enumForInt(int enumNumber) throws MenuItemNotFound;
}
