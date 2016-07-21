package data.test;

import static org.junit.Assert.*;

import org.junit.Test;

import data.creataccount.CreatAccount;
import data.exception.AddAccountException;
import data.exception.AddUserRoleException;
import general.Role;
import model.User;

public class CreatAccountTest {
	CreatAccount dao = new CreatAccount();

	@Test
	public void testAddAccount() {



		User user = new User();
		user.setAccount("141250057");
		user.setPsw("1996");
		user.setName("老黄");
		user.setAge(20);
		user.setNickName("oldYellow");
		
		user.setMajor("software");
		user.setRole(Role.ROOT);
		
		try {
			dao.addAccount(user);
		} catch (AddAccountException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AddUserRoleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
