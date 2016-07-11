package ViewController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import logic.project.ProjectProvider;
import model.Project;
/**
 * create to trans data from back to front
 * @author WangHuan
 *
 */
@Controller
@RequestMapping("data.do")
public class DataTransServlet {
	private ProjectProvider projectProvider = new ProjectProvider();
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
		List<Project> projectList = projectProvider.projectList(account);
		return projectList;
	}
	/*
	 * 
	 */
	
}
