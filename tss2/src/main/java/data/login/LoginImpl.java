package data.login;



import java.awt.Point;
import java.util.ArrayList;

import org.omg.PortableServer.POA;

import data.base.JDBCHelper;
import data.manage.MailBoxManage;
import data.manage.PhoneManage;
import data.manage.UserManage;
import data.manage.UserRoleManage;
import data.service.LoginService;
import general.Role;
import javafx.beans.property.StringProperty;
import po.User;
import po.MailBox;
import po.PhoneNumber;
import po.UserRole;

public class LoginImpl implements LoginService{
	JDBCHelper jdbcHelper;
	PhoneManage phoneManage;
	MailBoxManage mailBoxManage;
	UserManage userManage;
	UserRoleManage userRoleManage;



	public LoginImpl() {
		// TODO Auto-generated constructor stub
		jdbcHelper = new JDBCHelper();
		phoneManage = new PhoneManage();
		mailBoxManage = new MailBoxManage();
		userManage = new UserManage();
		userRoleManage = new UserRoleManage();

	}

	//大堆重复代码，需要重构
	public boolean testLegality(String account, String psw) {
		// TODO Auto-generated method stub
		int uid = 0;

		if(account.indexOf('@') != -1){
			MailBox mailBox = new MailBox();
			mailBox.setMailBox(account);
			ArrayList<MailBox> mailBoxs;
			mailBoxs = mailBoxManage.query(mailBox);
			if(mailBoxs.size() == 0)
				return false;
			uid = mailBoxs.get(0).getUid();
		}else if(account.length() >= 11){
			PhoneNumber phoneNumber = new PhoneNumber();
			phoneNumber.setPhoneNumber(account);
			ArrayList<PhoneNumber> phoneNumbers;
			phoneNumbers = phoneManage.query(phoneNumber);
			if(phoneNumbers.size() == 0)
				return false;
			uid = phoneNumbers.get(0).getUid();
		}else {
			User user = new User();
			user.setAccount(account);
			ArrayList<User> users;
			users = userManage.query(user);
			if(users.size() == 0)
				return false;
			else if(users.get(0).getPsw().equals(psw))
				return true;
			else
				return false;

		}

		User user = new User();
		user.setId(uid);
		ArrayList<User> users;
		users = userManage.query(user);
		if(users.size() == 0)
			return false;
		else if(users.get(0).getPsw().equals(psw))
			return true;
		
		
		return false; 
	}

	public model.User roleIdentifier(String account) {
		// TODO Auto-generated method stub
		int uid = 0;

		if(account.indexOf('@') != -1){
			MailBox mailBox = new MailBox();
			mailBox.setMailBox(account);
			ArrayList<MailBox> mailBoxs;
			mailBoxs = mailBoxManage.query(mailBox);
			uid = mailBoxs.get(0).getUid();
		}else if(account.length() >= 11){
			PhoneNumber phoneNumber = new PhoneNumber();
			phoneNumber.setPhoneNumber(account);
			ArrayList<PhoneNumber> phoneNumbers;
			phoneNumbers = phoneManage.query(phoneNumber);
			uid = phoneNumbers.get(0).getUid();



		}else {
			User user = new User();
			user.setAccount(account);
			ArrayList<User> users;
			users = userManage.query(user);
			UserRole userRole = new UserRole();
			model.User result = new model.User(users.get(0));
			userRole.setUid(users.get(0).getId());
			ArrayList<UserRole> userRoles = userRoleManage.query(userRole);
			result.setRole(Role.valueOf(userRoles.get(0).getRCode()));
			return result;

		}

		User user = new User();
		user.setId(uid);
		ArrayList<User> users;
		users = userManage.query(user);
		UserRole userRole = new UserRole();
		userRole.setUid(uid);
		ArrayList<UserRole> userRoles = userRoleManage.query(userRole);
		model.User result = new model.User(users.get(0));
		result.setRole(Role.valueOf(userRoles.get(0).getRCode()));
		return result;
		
	}

	
	public static void main(String[]args){
		LoginImpl impl = new LoginImpl();
		System.out.println(impl.testLegality("123123", "123"));
		System.out.println(impl.testLegality("123123", "1234"));
		System.out.println(impl.testLegality("23131@333", "123"));
		System.out.println(impl.testLegality("11112121212", "123"));
		System.out.println(impl.roleIdentifier("23131@333").getRole());
		
		System.out.println(impl.roleIdentifier("123123").getRole());


		
		
	}
}
