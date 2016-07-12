package data.test;


import java.util.ArrayList;
import org.junit.Test;
import data.base.StandardSQL;
import data.manage.ProjectManage;
import po.Project;

public class ProjectManageTest {
	ProjectManage dao = new ProjectManage();


	
	@Test
	public void testCreateProject() {
		StandardSQL standardSQL = new StandardSQL();
		String sql = null;
		try {
			sql = standardSQL.create(new Project());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dao.executeUpdate(sql);
	}
	
	
	
	@Test
	public void testAddProject() {
		Project project = new  Project();
		project.setCoursename("coursename");
		project.setCourseno("courseno");
		project.setTeaching_assistants("ta");
		project.setInstrutor("instructor");
		project.setSemester("semester1");
		dao.add(project);
		project.setSemester("semester2");
		dao.add(project);
	}

	@Test
	public void testDelete() {
		Project project = new Project();
		project.setId(16071102);
		dao.delete(project);
	}

	@Test
	public void testUpdate() {
		Project project = new  Project();
		project.setId(16071103);
		project.setCoursename("coursename");
		project.setCourseno("courseno");
		project.setTeaching_assistants("ta");
		project.setInstrutor("instructor");
		project.setSemester("3");
		dao.update(project);
	}

	@Test
	public void testQuery() {
		Project project = new Project();
		project.setId(16071103);
		ArrayList<Project>files = dao.query(project);
		for(Project f : files){
			System.out.println(f.getId() + " " + f.getSemester());
		}
	
	}

}
