package data.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.annotation.EnableLoadTimeWeaving;

import data.manage.UserRoleManage;
import general.Role;
import po.UserRole;

public class UserRoleManageTest {
	
	UserRoleManage dao = new UserRoleManage();

	@Test
	public void testAddUserRole() {
		UserRole userRole = new UserRole();
		userRole.setRCode(Role.STUDENT.toString());
		userRole.setUid(123);
		dao.add(userRole);
		
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testQuery() {
		fail("Not yet implemented");
	}

}
