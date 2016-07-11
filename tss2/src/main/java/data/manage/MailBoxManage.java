package data.manage;

import data.service.ManageService;
import po.MailBox;

public class MailBoxManage extends ManageService<MailBox> {
	
	public MailBoxManage(){
		super();
		size = 1000;
		tableName = "mailbox";
		
		
	}


}
