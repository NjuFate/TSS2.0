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
	
	

	

	public int delete(File file) {
		// TODO Auto-generated method stub
		String sql;
		try {
			sql = standardSQL.delete(file);
			return executeUpdate(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}



	public ArrayList<File> query(File file) {
		// TODO Auto-generated method stub
		String sql;
		try {
			sql = standardSQL.query(file);
			return (ArrayList<File>) executeQuery(sql, file);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public int add(File file) {
		// TODO Auto-generated method stub
	
		file.setId(implementation());
		try {
			String sql = standardSQL.add(file);
			return executeUpdate(sql);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block

		}
		return 0;
	}



	@Override
	public int update(File file) {
		// TODO Auto-generated method stub
		String sql;
		try {
			sql = standardSQL.update(file);
			return executeUpdate(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return 0;
		
	}
	
	
	
}
