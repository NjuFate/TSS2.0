package data.project;


import java.util.ArrayList;
import java.util.List;
import data.base.JDBCHelper;
import data.base.ParticularQuery;
import data.manage.ProjectManage;
import data.manage.UserProjectManage;
import data.service.ProjectService;
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
	ParticularQuery query;
	UserProjectManage userProjectManage;
	ProjectManage projectManage;

	public ProjectImpl() {
		// TODO Auto-generated constructor stub
		query = new ParticularQuery(new JDBCHelper());
		userProjectManage = new UserProjectManage();
		projectManage = new ProjectManage();
	}


	public List<Project> projectList(String account){
		// TODO Auto-generated method stub
		int id = 0;
		if((id = query.getUserIdByAccount(account)) == 0)
			return null;

		UserProject t = new UserProject();
		t.setUid(id);
		List<UserProject>uplists = userProjectManage.query(t);
		List<Project> plists = new ArrayList<Project>(); 


		for(UserProject uproject : uplists){
			po.Project temp =  new po.Project();
			temp.setId(uproject.getPid());
			plists.add(new Project(projectManage.query(temp).get(0)));

		}

		return plists;
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
