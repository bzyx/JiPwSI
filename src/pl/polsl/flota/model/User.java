/**
 * 
 */
package pl.polsl.flota.model;

/**
 * @author Marcin Jabrzyk
 * @since 1.0.2 25/10/2011
 *
 */
public class User {
	static Integer lastUserId = 0;
	/**
	 * @return the lastUserId
	 */
	public static Integer getLastUserId() {
		return lastUserId;
	}
	
	/**
	 * @param lastUserId the lastUserId to set
	 */
	public static void setLastUserId(Integer lastUserId) {
		User.lastUserId = lastUserId;
	}
	String fullName;
	Boolean isAdmin;
	String password;
	Integer userId;
	String userName;
	{
		lastUserId++;
	}
	/**
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
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	/**
	 * @param isAdmin the isAdmin to set
	 */
	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	/**
	 * @param username the username to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	

}
