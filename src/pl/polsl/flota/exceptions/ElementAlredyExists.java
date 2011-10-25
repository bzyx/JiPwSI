/**
 * 
 */
package pl.polsl.flota.exceptions;

/**
 * Exception for element which already exists.
 * 
 * @author Marcin Jabrzyk
 * @since 1.0.2 25/10/2011
 * 
 */
public class ElementAlredyExists extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ElementAlredyExists() {

	}

	public ElementAlredyExists(String string) {
		super(string);
	}

}
