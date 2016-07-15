package data.test;


import org.junit.Test;
import data.exception.NoAccountException;
import data.role.RoleTransition;
import data.service.RoleTransitionService;
import general.Role;

public class RoleTransitionTest {

	@Test
	public void testRoleChange() {
		RoleTransitionService roleTransitionService = new RoleTransition();
		try {
			roleTransitionService.roleChange("141250052", Role.ROOT);
		} catch (NoAccountException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
