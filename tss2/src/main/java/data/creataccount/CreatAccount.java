package data.creataccount;

import data.base.JDBCHelper;
import data.base.ParticularQuery;
import data.exception.AddAccountException;
import data.exception.AddMailBoxException;
import data.exception.AddPhoneNumberException;
import data.exception.AddUserRoleException;
import data.exception.NoAccountException;
import data.manage.MailBoxManage;
import data.manage.PhoneManage;
import data.manage.UserManage;
import data.manage.UserRoleManage;
import data.service.CreateAccountService;
import model.User;
import po.MailBox;
import po.PhoneNumber;
import po.UserRole;

public class CreatAccount implements CreateAccountService{
	UserManage userManage;
	UserRoleManage userRoleManage;
	PhoneManage phoneManage;
	JDBCHelper helper;
	MailBoxManage mailBoxManage;
	ParticularQuery query;
	
	public CreatAccount() {
		// TODO Auto-generated constructor stub
		helper = new JDBCHelper();
		userManage = new UserManage();
		phoneManage = new PhoneManage();
		userRoleManage = new UserRoleManage();
		mailBoxManage = new MailBoxManage();
		query = new ParticularQuery(helper);
	}
	
	
	

	public boolean addAccount(User user) throws AddAccountException, AddUserRoleException {
		// TODO Auto-generated method stub
		po.User addUser = new po.User(user);
		int id = userManage.add(addUser);
		
		//添加失败
		if(id == 0)
			
           throw new AddAccountException();	
		
		UserRole role = new UserRole();
		role.setRCode(user.getRole().toString());
		role.setUid(id);
		
		if(userRoleManage.add(role) == 0)
			
			throw new AddUserRoleException();
		
		
		
		
		
		return  true;
	}

	public boolean addPhoneNumber(String account, String phoneNumber) throws NoAccountException, AddPhoneNumberException {
		// TODO Auto-generated method stub
		int id = query.getUserIdByAccount(account);
		
	
		if(id == 0)
			throw new NoAccountException();
		
		PhoneNumber pNumber = new PhoneNumber();
		pNumber.setUid(id);
		pNumber.setPhoneNumber(phoneNumber);
		
		
		if(phoneManage.add(pNumber) == 0)
			throw new AddPhoneNumberException();
		
		return true;
	}

	public boolean addMailBox(String account, String mailBox) throws NoAccountException, AddMailBoxException {
		// TODO Auto-generated method stub
		int id = query.getUserIdByAccount(account);
		
		
		if(id == 0)
			throw new NoAccountException();
		
		MailBox mBox = new MailBox();
		mBox.setUid(id);
		mBox.setMailBox(mailBox);
		
		
		if(mailBoxManage.add(mBox) == 0)
			throw new AddMailBoxException();
		
		return true;
	}

}
