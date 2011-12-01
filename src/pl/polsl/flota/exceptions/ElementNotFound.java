/**
 *  An class for ElementNotFound.
 */
package pl.polsl.flota.exceptions;

/**
 * Exception for element which not exists.
 * 
 * @author Marcin Jabrzyk
 * @since 1.0.2 25/10/2011
 */
public class ElementNotFound extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ElementNotFound() {

	}

	public ElementNotFound(String string) {
		super(string);

	}

}
