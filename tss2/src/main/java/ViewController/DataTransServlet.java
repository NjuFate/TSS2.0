package ViewController;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import general.Role;
import logic.file.FileProvider;
import logic.login.RoleIdentifier;
import logic.project.ProjectProvider;
import model.File;
import model.Project;
import model.Student;
import model.Teacher;
import model.User;
/**
 * create to trans data from back to front
 * @author WangHuan
 *
 */
@Controller
@RequestMapping("data.do")
public class DataTransServlet {
	private ProjectProvider projectProvider = new ProjectProvider();
	private FileProvider fileProvider = new FileProvider();
	private RoleIdentifier roleIdentifier = new RoleIdentifier();
	public void setProjectProvider(ProjectProvider projectProvider){
		this.projectProvider = projectProvider;
	}
	/**
	 *provide projectList 
	 * @param account
	 * @return
	 */
	@RequestMapping(params="method=projectList",method=RequestMethod.GET)
	public @ResponseBody List<Project> projectList(String account) throws Exception {
		//account 合法性检验：
		if(account==null||account.equals("")){
			//todo;
		}
		List<Project> projectList = projectProvider.projectList(account);
		return projectList;
	}
	/*
	 * 
	 */
	/**
	 * 
	 * @param account
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params="method=fileList",method=RequestMethod.GET)
	public @ResponseBody List<File> projectList(String courseno,String father,HttpServletResponse response,HttpServletRequest request) throws Exception {
		System.out.println(courseno);
		System.out.println(father);
		
		if(courseno==null||courseno.equals("")){
			//courseno 合法性检验；
		}
		List<File> fileList = fileProvider.getFileByCourseno(courseno,father);
		//暂存选择的课程信息
		Cookie cookie;
		cookie = new Cookie("courseno",courseno );
		cookie.setPath("/");	
		response.addCookie(cookie);
		return fileList;
	}
	
	@RequestMapping(params="method=userInfo",method=RequestMethod.GET)
	public @ResponseBody User userInfo(String account) throws Exception {
		//不知道会不会正确返回子类信息。。
		User user = roleIdentifier.identifyRole(account);
		if(user.getRole() == Role.TEACHER){
			Teacher teacher = (Teacher)user;
			return teacher;
		}
		if(user.getRole() == Role.STUDENT){
			Student student = (Student)user;
			return student;
		}
		return user;
	}
	
	
	
}
