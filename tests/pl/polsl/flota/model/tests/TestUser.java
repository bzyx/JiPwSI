package pl.polsl.flota.model.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pl.polsl.flota.model.User;

public class TestUser {

	@Test
	public void testGetLastUserId() {
		//given
		new User();
		//when
		Integer userId = User.getLastUserId();
		//then
		assertEquals(new Integer(1), userId);
	}

	@Test
	public void testSetLastUserId() {
		//given
		User.setLastUserId(5);
		new User();
		//when
		Integer userId = User.getLastUserId();
		//then
		assertEquals(new Integer(6), userId);
	}
	@After() public void setUserId() {
		User.setLastUserId(0);
	}

	@Test
	public void testGetIsAdmin() {
		//given
		User user = new User();
		user.setIsAdmin(true);
		//when
		Boolean userIsAdmin = user.getIsAdmin();
		//then
		assertTrue(userIsAdmin);
	}

	@Before public void setUserIdTo0() {
		User.setLastUserId(0);
	}
	
	@Test
	public void testGetUserId() {
		//given
		User user = new User();
		//when
		Integer userId = user.getUserId();
		//then
		assertEquals(new Integer(1), userId);
	}

}