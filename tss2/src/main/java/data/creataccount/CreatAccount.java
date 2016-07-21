package data.creataccount;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import data.exception.AddAccountException;
import data.exception.AddUserRoleException;
import data.exception.NoAccountException;
import data.manage.UserManage;
import data.manage.UserRoleManage;
import data.service.CreateAccountService;
import model.User;
import po.UserRole;

public class CreatAccount implements CreateAccountService{
	private static 
	UserManage userManage;
	UserRoleManage userRoleManage;
	
	public CreatAccount() {
		// TODO Auto-generated constructor stub
		userManage = new UserManage();
		userRoleManage = new UserRoleManage();
	}
	
	
	

	public String addAccount(User user) throws AddAccountException, AddUserRoleException {
		// TODO Auto-generated method stub
		po.User addUser = new po.User(user);
		String hAccount = createHAccount();
		addUser.sethAccount(hAccount);
		long id = userManage.add(addUser);
		
		
		
		//添加失败
		if(id == 0)
           throw new AddAccountException();
		
		UserRole role = new UserRole();
		role.setRole(user.getRole().toString());
		role.setUid(id);
		
		if(userRoleManage.add(role) == 0)	
			throw new AddUserRoleException();
		
		return hAccount;
	}
	
	
	
	
	/**
	 * 
	 * @deprecated
	 * @return string
	 */
	private String createHAccount(){
		String date = new SimpleDateFormat("yyMMdd").format(new Date());
		

		Calendar cal = Calendar.getInstance();
		char hour = (char) (cal.get(Calendar.HOUR) + 'A');
		char minute = (char) ((cal.get(Calendar.MINUTE)% 26) + 'A');
		char second = (char) ((cal.get(Calendar.SECOND)% 26) + 'A');	
		return date + hour + minute + second;
	}
	
}
