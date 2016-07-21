package data.project;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import data.base.JDBCHelper;
import data.base.UserQuery;
import data.manage.ProjectManage;
import data.manage.UserManage;
import data.manage.UserProjectManage;
import data.service.ProjectService;
import model.LoginUser;
import model.Project;
import po.UserProject;



/**
 * @author: xuan
 * @date: 2016/07/13
 * 
 * @mender: none
 * @date: none
 * 
 * @type: class
 * @description: 课程的数据层接口实现
 * 
 * @deprecated 效率低，需要重构
 */

public class ProjectImpl implements ProjectService{
	UserQuery query;
	UserProjectManage userProjectManage;
	ProjectManage projectManage;

	public ProjectImpl() {
		// TODO Auto-generated constructor stub
		query = new UserQuery(new UserManage());
		userProjectManage = new UserProjectManage();
		projectManage = new ProjectManage();
	}


	public List<Project> projectList(String account){
		// TODO Auto-generated method stub
		long id = 0;
		LoginUser u = query.getLoginMessage(account);
		if(u == null)
			return null;
		id = u.getId();

		UserProject t = new UserProject();
		t.setUid(id);
		List<UserProject>uplists = userProjectManage.query(t);

		List<Project> plists = new ArrayList<Project>(); 

		try{
			for(UserProject uproject : uplists){
				po.Project temp =  new po.Project();
				temp.setId(uproject.getPid());
				plists.add(new Project(projectManage.query(temp).get(0)));

			}
			return plists;
		}catch(Exception e){

		}
		return null;

	}


	/**
	 * @deprecated
	 */

	public List<Project> projectListStudent(String account) {
		// TODO Auto-generated method stub
		return projectList(account);
	}


	public List<Project> getAllProject() {
		// TODO Auto-generated method stub
		String sql = "select * from project";

		try {
			List<po.Project>list = projectManage.executeQuery(sql, new po.Project());
			List<Project> projects = new ArrayList<Project>();
			
			for(po.Project p:list){
				projects.add(new Project(p));
			}
			return projects;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}


}
