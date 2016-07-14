package data.manage;

import data.service.ManageService;
import po.UserProject;

public class UserProjectManage extends ManageService<UserProject>{

	
	public UserProjectManage() {
		// TODO Auto-generated constructor stub

		super();
		size = 10000;
		tableName = "userproject";
			
	}
}
