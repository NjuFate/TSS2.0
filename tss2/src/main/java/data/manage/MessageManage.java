package data.manage;

import data.service.ManageService;
import po.Message;

public class MessageManage extends ManageService<Message>{

	
	
	
	public MessageManage() {
		// TODO Auto-generated constructor stub
		super();
		//暂定10万
		size = 100000;
		tableName = "message";
	}
}
