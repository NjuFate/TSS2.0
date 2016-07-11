package data.manage;

import data.service.ManageService;
import po.PhoneNumber;

public class PhoneManage extends ManageService<PhoneNumber>{

	public PhoneManage() {
		// TODO Auto-generated constructor stub
		super();
		size = 1000;
		tableName = "phoneNumber";
		
	}

	
	
}
