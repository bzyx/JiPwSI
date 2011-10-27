/**
 * 
 */
package pl.polsl.flota.controller;

import java.util.ArrayList;
import java.util.List;

import pl.polsl.flota.exceptions.ElementAlredyExists;
import pl.polsl.flota.exceptions.ElementNotFound;
import pl.polsl.flota.model.User;
import pl.polsl.flota.model.UserList;

/**
 * @author Marcin Jabrzyk
 * 
 */
public class UserController {

	static UserList userList;

	public UserController(String fileName) {
		super();
		userList = new UserList();
		if (userList.getListOfUsers().isEmpty()) {
			userList.load(fileName);
		}

	}

	public void addUser(String userName, String userPassword,
			String userFullName) throws ElementAlredyExists {
		userList.addUser(new User(userName,userPassword,userFullName));
		
	}

	public void save(String fileNameUserList) {
		userList.save(fileNameUserList);
		
	}
	
	public List<String> getUserStringList() {
		//TODO: Values should be separated by a comma.
		//TODO: Think about presenting the name of the Driver
		List<String> returnList = new ArrayList<String>();
		for ( User user: userList.getListOfUsers() ){
			returnList.add(user.getUserId()+" "+user.getUserName() + " " + user.getFullName() );
		}
		//System.out.println(returnList);
		return returnList;
	}
	
	public void editUser(String userId, String newPassword) throws ElementNotFound {
		Boolean isError = true;
		Integer id = -1;
		try {
			id = Integer.parseInt(userId);
		} catch (NumberFormatException e) {
			throw new ElementNotFound("UserController: editUser wrong Integer");
		}
		for (User user: userList.getListOfUsers()){
			if(user.getUserId() == id){
				user.setPassword(newPassword);
				isError = false;
			}
		}
		if (isError){
			throw new ElementNotFound("UserController: editUser User Not Found");
		}
	}
	
	public void deleteUser(String userId) throws ElementNotFound {
		Boolean isError = true;
		Integer id = -1;
		try {
			id = Integer.parseInt(userId);
		} catch (NumberFormatException e) {
			throw new ElementNotFound("UserController: deleteUser wrong Integer");
		}
		for (User user: userList.getListOfUsers()){
			if(user.getUserId() == id){
				userList.deleteItem(user);
				isError = false;
			}
		}
		if (isError){
			throw new ElementNotFound("UserController: deleteUser User Not Found");
		}
		
	}

	public Integer checkUser(String userName , String userPassword) {
		for (User user: userList.getListOfUsers()){
			if(user.getUserName().equals(userName)){
				if(user.getPassword().equals(userPassword))
					return user.getUserId();
			}
		}
		return -1;
	}

	public Boolean isAdmin(Integer userId) {
		for (User user: userList.getListOfUsers()){
			if(user.getUserId() == userId){
				return user.getIsAdmin();
			}
		}
		return false;
	}

}
