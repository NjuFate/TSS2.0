package logic.project;

import java.util.List;

import dataServiceStub.ProjectServiceStub;
import dataservice.ProjectService;
import model.Project;

/**
 * 返回所需课程信息
 * @author WangHuan
 *
 */
public class ProjectProvider {

	private ProjectService projectService = new ProjectServiceStub();
	
	public ProjectProvider(){
		
	}
	public ProjectProvider(ProjectService projectService){
		this.projectService = projectService;
	}
	public void setProjectService(ProjectService projectService){
		this.projectService = projectService;
	}
	
	/**
	 * 根据账号返回课程列表
	 * @param account
	 * @return
	 */
	public List<Project> projectList(String account){
		List<Project> projectList = projectService.projectList(account);
		return projectList;
		
	}
}
