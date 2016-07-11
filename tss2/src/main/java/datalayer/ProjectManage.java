package datalayer;

import java.util.ArrayList;

import PO.Project;
import dataservice.ManageService;

public class ProjectManage extends ManageService<Project>{

	public ProjectManage() {
		// TODO Auto-generated constructor stub
		helper = new JDBCHelper();
		size = 100;
		tableName = "project";
		standardSQL = new StandardSQL();
	}



	@Override
	public int add(Project t) {
		// TODO Auto-generated method stub
		t.setId(implementation());
		return super.add(t);

	}







}
