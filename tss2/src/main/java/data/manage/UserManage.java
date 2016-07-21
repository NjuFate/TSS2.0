package data.manage;

import data.service.ManageService;
import po.User;

public class UserManage extends ManageService<User>{
	
	
	public UserManage() {
		// TODO Auto-generated constructor stub
		super();
		size = 100000;
		tableName = "user";
		
		
	}
	


}
