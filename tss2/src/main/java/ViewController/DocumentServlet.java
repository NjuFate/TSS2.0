package ViewController;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import general.Role;
import logic.login.RoleIdentifier;
import logic.project.ProjectProvider;
import model.Project;
import model.User;

/**
 * 
 * @author WangHuan
 *
 */
@Controller
@RequestMapping("/pages")
public class DocumentServlet {
	private RoleIdentifier roleIdentifier = new RoleIdentifier();
	private ProjectProvider projectProvider = new ProjectProvider();

	public void setRoleIdentifier(RoleIdentifier roleIdentifier) {
		this.roleIdentifier = roleIdentifier;
	}
	public void setProjectProvider(ProjectProvider projectProvider){
		this.projectProvider = projectProvider;
	}

	@RequestMapping("/teacherDoc")
	public ModelAndView teacherDoc(String account) {
		User user = roleIdentifier.identifyRole(account);
		if(user.getRole() == Role.TEACHER){
			//判断身份为老师
			//返回老师参与的课程界面
			List<Project> projectList = projectProvider.projectList(account);
		}
		return null;
	}
}
