package ViewController;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import logic.file.FileProvider;
import logic.project.ProjectProvider;
import model.File;
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
	private FileProvider fileProvider = new FileProvider();
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
	/**
	 * 
	 * @param account
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params="method=fileList",method=RequestMethod.GET)
	public @ResponseBody List<File> projectList(String courseno,String coursename,String semester,String father) throws Exception {
		List<File> fileList = fileProvider.getFileByCourse(courseno, coursename, semester,father);
		return fileList;
	}
	
}
