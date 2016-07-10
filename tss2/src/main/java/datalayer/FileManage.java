package datalayer;

import PO.File;
import dataservice.ManageService;

public class FileManage implements ManageService{
	
	
	
	
	

	public boolean add(Object object) {
		// TODO Auto-generated method stub
		File file = (File) object;
		String sql = "insert ";
		
		
		return false;
	}

	public boolean delete(Object object) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean update(Object object) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean query(Object object) {
		// TODO Auto-generated method stub
		return false;
	}

}
