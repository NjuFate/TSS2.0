package data.test;

import static org.junit.Assert.*;

import org.junit.Test;

import data.login.LoginIn;
import general.Role;

public class LoginInTest {
	
	
	LoginIn in = new LoginIn();

	@Test
	public void testTestLegality() {
		
		assertEquals(true,in.testLegality("141250052", "hdx1996"));
		assertEquals(false,in.testLegality("141250052", "hx1996"));
		assertEquals(false,in.testLegality("141250053", "hdx1996"));
		assertEquals(true,in.testLegality("141250053", "hh1996"));



 	}

	@Test
	public void testRoleIdentifier() {
		assertEquals(20,in.roleIdentifier("141250052").getAge());
		assertEquals(20,in.roleIdentifier("160721LTA").getAge());

	}

	@Test
	public void testGetRoleByAccount() {
		assertEquals(Role.ROOT,in.getRoleByAccount("141250053"));
		assertEquals(Role.ROOT,in.getRoleByAccount("160721LTA"));

;
	}

}
