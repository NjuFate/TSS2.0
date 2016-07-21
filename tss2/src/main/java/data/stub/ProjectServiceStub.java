package data.stub;

import java.util.ArrayList;
import java.util.List;

import data.service.ProjectService;
import model.Project;

public class ProjectServiceStub implements ProjectService{

	public List<Project> projectList(String account) {
		// TODO Auto-generated method stub
		ArrayList<Project> projectList  = new ArrayList<Project>();
		Project project  = new Project();
		project.setCoursename("SEII");
		project.setCourseno("c1879");
		project.setInstrutor("刘钦");
		project.setSemester("2016summer");
		project.setTeaching_assistants("唐大爷");
		
		projectList.add(project);
		
		Project project2  = new Project();
		project2.setCoursename("SEII");
		project2.setCourseno("c1879");
		project2.setInstrutor("刘钦");
		project2.setSemester("2016summer");
		project2.setTeaching_assistants("唐大爷");
		
		projectList.add(project2);
		return projectList;
	}

	public List<Project> projectListStudent(String account) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Project> getAllProject() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
