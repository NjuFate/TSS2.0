package data.test;

import static org.junit.Assert.*;


import org.junit.Test;

import data.creataccount.CreatAccount;
import data.exception.AddAccountException;
import data.exception.AddMailBoxException;
import data.exception.AddUserRoleException;
import data.exception.NoAccountException;
import data.service.CreateAccountService;
import general.Role;
import model.User;

public class CreatAccountTest {
	
	CreateAccountService service = new CreatAccount();

//	@Test
//	public void testAddAccount() {
//		User user = new User();
//		user.setAccount("141250052");
//		user.setAge(20);
//		user.setRole(Role.ROOT);
//		user.setMajor("software");
//		user.setPsw("123456");
//		user.setName("hdx");
//		try {
//			service.addAccount(user);
//		} catch (AddAccountException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (AddUserRoleException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}

	@Test
	public void testAddPhoneNumber() {
		
		
		
	}

	@Test
	public void testAddMailBox() {
		try {
			service.addMailBox("141250052", "1533704796@qq.com");
		} catch (NoAccountException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AddMailBoxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			service.addMailBox("141250052", "1425296454@qq.com");
		} catch (NoAccountException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AddMailBoxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			service.addMailBox("141350052", "1425296454@qq.com");
		} catch (NoAccountException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AddMailBoxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
