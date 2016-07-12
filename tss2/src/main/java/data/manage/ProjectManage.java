package data.manage;

import data.service.ManageService;
import po.Project;

public class ProjectManage extends ManageService<Project>{

	public ProjectManage() {
		// TODO Auto-generated constructor stub
		super();
		size = 100;
		tableName = "project";
	}


}
