package pl.polsl.flota.model.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pl.polsl.flota.exceptions.ElementAlredyExists;
import pl.polsl.flota.exceptions.ElementNotFound;
import pl.polsl.flota.model.User;
import pl.polsl.flota.model.UserList;

public class TestUserList {

	@Test
	public void testAddUser() {
		//given
		UserList userList = new UserList();
		try {
			userList.addUser(new User("Name","passwd","Name Surname"));
		} catch (ElementAlredyExists e) {
			fail("Excepiton occured");
		}
		//when
		Boolean value = (!userList.getListOfUsers().isEmpty());
		//then
		assertTrue(value);
	}

	@Test
	public void testDeleteItem() {
		//given
		UserList userList = new UserList();
		User myUser = new User("Name","passwd","Name Surname");
		try {
			userList.addUser(myUser);
		} catch (ElementAlredyExists e) {
			fail("Excepiton occured");
		}
		userList.deleteItem(myUser);
		//when
		Boolean value = userList.getListOfUsers().isEmpty();
		//then
		assertTrue(value);
	}

	@Test
	@Before public void resetUserId() {
		User.setLastUserId(0);
	}
	public void testGetUserById() {
		//given
		UserList userList = new UserList();
		User myUser = new User("Name","passwd","Name Surname");
		User myUser1 = new User("Name1","passwd2","Name Surname");
		try {
			userList.addUser(myUser);
			userList.addUser(myUser1);
		} catch (ElementAlredyExists e) {
			fail("Excepiton when adding occured");
		}
		//when
		User retUser = null;
		try {
			retUser = userList.getUserById(1);
		} catch (ElementNotFound e) {
			fail("Excepiton when finding occured");
		}
		Boolean value = retUser.getUserName().equals(myUser.getUserName());
		//then
		assertTrue(value);
		userList.getListOfUsers().clear();
	}

	@Test
	public void testGetUserByUserName() {
		//given
		UserList userList = new UserList();
		User myUser = new User("Name","passwd","Name Surname");
		User myUser1 = new User("Name1","passwd2","Name Surname");
		try {
			userList.addUser(myUser);
			userList.addUser(myUser1);
		} catch (ElementAlredyExists e) {
			fail("Excepiton when adding occured");
		}
		//when
		User retUser = null;
		try {
			retUser = userList.getUserByUserName("Name");
		} catch (ElementNotFound e) {
			fail("Excepiton when finding occured");
		}
		Boolean value = retUser.getUserName().equals(myUser.getUserName()) &
						retUser.getUserId().equals(myUser.getUserId());
		//then
		assertTrue(value);
		userList.getListOfUsers().clear();
	}

	@Test
	@Before public void resetLastUserId(){
		User.setLastUserId(0);
	}
	public void testUpdateUserById() {
		//given
		UserList userList = new UserList();
		User myUser = new User("Name","passwd","Name Surname");
		User myUser1 = new User("Name1","passwd2","Name Surname");
		try {
			userList.addUser(myUser);
			userList.addUser(myUser1);
		} catch (ElementAlredyExists e) {
			fail("Excepiton when adding occured");
		}

		myUser1.setUserName("MyName");
		System.out.println(userList.getListOfUsers().size());
		try {
			userList.updateUserById(2, myUser1);
		} catch (ElementNotFound e1) {
			fail("Excepiton when editing occured");
		}
		User retUser = null;
		try {
			retUser = userList.getUserById(2);
		} catch (ElementNotFound e) {
			fail("Excepiton when finding occured");
		}
		Boolean value = retUser.getUserName().equals(myUser1.getUserName()) &
						retUser.getUserId().equals(myUser1.getUserId());
		//then
		assertTrue(value);
	}

}
