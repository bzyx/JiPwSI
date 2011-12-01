/**
 * This is file contains UserController class.
 */
package pl.polsl.flota.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pl.polsl.flota.exceptions.ElementAlredyExists;
import pl.polsl.flota.exceptions.ElementNotFound;
import pl.polsl.flota.model.User;
import pl.polsl.flota.model.UserList;

/**
 * UserController class.
 * 
 * @author Marcin Jabrzyk
 * @since 1.1.1 28.10.2011
 */
public class UserController {

	/** A simple but not so beautiful hack to be sure 
	 * that we have only one list (one model instance). */
	static UserList userList = null;

	/**
	 * Constructor of UserController
	 * 
	 * @param fileName
	 * @throws IOException
	 */
	public UserController(String fileName) throws IOException {
		super();
		if (userList == null){
			userList = new UserList();
		}
		
		if (userList.getListOfUsers().isEmpty()) {
			userList.load(fileName);
		}

	}

	/**
	 * Tries to add User, if that user already exist throws ElementAlredyExists
	 * 
	 * @param userName
	 * @param userPassword
	 * @param userFullName
	 * @throws ElementAlredyExists
	 */
	public void addUser(String userName, String userPassword,
			String userFullName) throws ElementAlredyExists {
		userList.addUser(new User(userName, userPassword, userFullName));

	}

	/**
	 * Calls the method from Model and saves the file.
	 * 
	 * @param fileNameUserList
	 */
	public void save(String fileNameUserList) {
		userList.save(fileNameUserList);

	}

	/**
	 * Return a List of String with all users.
	 * 
	 * @return a list of String.
	 */
	public List<String> getUserStringList() {
		// TODO: Values should be separated by a comma.
		// TODO: Think about presenting the name of the Driver
		List<String> returnList = new ArrayList<String>();
		for (User user : userList.getListOfUsers()) {
			returnList.add(user.getUserId() + " " + user.getUserName() + " "
					+ user.getFullName());
		}
		// System.out.println(returnList);
		return returnList;
	}

	/**
	 * Tries to to update a user password for for the User with the userId
	 * 
	 * @param userId
	 * @param newPassword
	 * @throws ElementNotFound
	 */
	public void editUser(String userId, String newPassword)
			throws ElementNotFound {
		Boolean isError = true;
		Integer id = -1;
		try {
			id = Integer.parseInt(userId);
		} catch (NumberFormatException e) {
			throw new ElementNotFound("UserController: editUser wrong Integer");
		}
		for (User user : userList.getListOfUsers()) {
			if (user.getUserId() == id) {
				user.setPassword(newPassword);
				isError = false;
			}
		}
		if (isError) {
			throw new ElementNotFound("UserController: editUser User Not Found");
		}
	}

	/**
	 * Tries to delete user with the userId
	 * 
	 * @param userId
	 * @throws ElementNotFound
	 */
	public void deleteUser(String userId) throws ElementNotFound {
		Boolean isError = true;
		Integer id = -1;
		try {
			id = Integer.parseInt(userId);
		} catch (NumberFormatException e) {
			throw new ElementNotFound(
					"UserController: deleteUser wrong Integer");
		}
		for (User user : userList.getListOfUsers()) {
			if (user.getUserId() == id) {
				userList.deleteItem(user);
				isError = false;
			}
		}
		if (isError) {
			throw new ElementNotFound(
					"UserController: deleteUser User Not Found");
		}

	}

	/**
	 * Looks for user with the provieden username and password. If user exists
	 * returns his id.
	 * 
	 * @param userName
	 * @param userPassword
	 * @return a userId
	 */
	public Integer checkUser(String userName, String userPassword) {
		for (User user : userList.getListOfUsers()) {
			if (user.getUserName().equals(userName)) {
				if (user.getPassword().equals(userPassword))
					return user.getUserId();
			}
		}
		return -1;
	}

	/**
	 * Checks if the User with userId is Admin.
	 * 
	 * @param userId
	 * @return true if user is admin
	 */
	public Boolean isAdmin(Integer userId) {
		for (User user : userList.getListOfUsers()) {
			if (user.getUserId() == userId) {
				return user.getIsAdmin();
			}
		}
		return false;
	}

}
