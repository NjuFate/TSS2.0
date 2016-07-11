package datalayer;


import java.util.ArrayList;

import PO.File;
import dataservice.ManageService;

public class FileManage extends ManageService<File>{
	
	
	public FileManage(){
		helper = new JDBCHelper();
		size = 1000;
		tableName = "file";
		standardSQL = new StandardSQL();
	}
	
	

	@Override
	public int add(File file) {
		// TODO Auto-generated method stub
	
		file.setId(implementation());
		
		return super.add(file);
	}




	
	
	
}
