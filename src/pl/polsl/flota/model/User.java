/**
 * A User class.
 */
package pl.polsl.flota.model;

/**
 * A class which handle User object.
 * @author Marcin Jabrzyk
 */
public class User {

	String fullName;
	Boolean isAdmin;
	String password;
	Integer userId;
	String userName;

	{
		lastUserId++;
	}
	/**
	 * A variable to get the next userId
	 */
	static Integer lastUserId = 0;

	/**
	 * @return the lastUserId
	 */
	public static Integer getLastUserId() {
		return lastUserId;
	}

	/**
	 * Sets the lastUserId after eg. reading form a file
	 * 
	 * @param lastUserId
	 *            the lastUserId to set
	 */
	public static void setLastUserId(Integer lastUserId) {
		User.lastUserId = lastUserId;
	}

	/**
	 * A constructor with all needed parms. Default user is not a admin.
	 * 
	 * @param username
	 * @param password
	 * @param fullName
	 */
	public User(String username, String password, String fullName) {
		super();
		this.userId = lastUserId;
		this.userName = username;
		this.password = password;
		this.fullName = fullName;
		this.isAdmin = false;
	}

	/**
	 * A defalut constructor
	 */
	public User() {
		super();
		this.userId = lastUserId;
		this.isAdmin = false;
	}

	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @return the isAdmin
	 */
	public Boolean getIsAdmin() {
		return isAdmin;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @return the username
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param fullName
	 *            the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * @param isAdmin
	 *            the isAdmin to set
	 */
	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

}
