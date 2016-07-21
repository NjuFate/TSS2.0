package data.manage;

import data.service.ManageService;
import po.InformMessage;

public class InformMessageManage extends ManageService<InformMessage>{
	
	public InformMessageManage() {
		// TODO Auto-generated constructor stub
		super();
		//暂定10万
		size = 100000;
		tableName = "informmessage";
	}

}
