package data.manage;


import data.service.ManageService;
import po.File;

public class FileManage extends ManageService<File>{
	
	
	public FileManage(){
		super();
		size = 100000;
		tableName = "file";
	}

}
