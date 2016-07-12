package data.test;

import static org.junit.Assert.*;

import org.junit.Test;

import data.login.LoginIn;
import data.login.LoginIn;
import general.Role;

public class LoginImplTest {
	LoginIn impl = new LoginIn();

	@Test
	public void testTestLegality() {
			assertEquals(false,impl.testLegality("123123", "123"));
			assertEquals(false,impl.testLegality("woqu", "huang"));
			assertEquals(true,impl.testLegality("1533704796@qq.com", "huang"));
			assertEquals(true,impl.testLegality("18362916726", "huang"));
			assertEquals(false,impl.testLegality("141250052", "xx"));


			
		}	

	@Test
	public void testRoleIdentifier() {
		assertEquals("141250052",impl.roleIdentifier("1533704796@qq.com").getAccount());
		assertEquals(20,impl.roleIdentifier("18362916726").getAge());
		assertEquals(20,impl.roleIdentifier("141250052").getAge());
//		System.err.println(impl.roleIdentifier("141250052").getMajor());
		assertEquals(Role.TEACHER,impl.roleIdentifier("141250052").getRole());



		
	}

}
