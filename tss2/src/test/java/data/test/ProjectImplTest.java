package data.test;


import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;


import data.project.ProjectImpl;
import model.Project;

public class ProjectImplTest {
	@SuppressWarnings("deprecation")
	ProjectImpl Impl = new ProjectImpl();

	@Test
	public void testProjectList() {
		List<Project>projects = Impl.projectList("141250052");
		assertEquals(2, projects.size());
		
		for(Project project: projects){
			System.out.println(project.getTeaching_assistants() + " " + project.getCourseno());
		}
		
		
	}

}
