package data.service;

import java.util.List;

import data.exception.NoAccountException;
import model.Project;

/**
 * 返回用户所需课程信息
 * @author WangHuan
 *
 */
public interface ProjectService {

	/**
	 * 根据账号，返回用户参与的课程
	 *  例： 输入老师账号
	 *      返回老师所参与的课程 list
	 * @param account
	 * @return
	 * @throws NoAccountException 
	 */
	public List<Project> projectList(String account);
	
	/**
	 * 根据账号，返回用户参与的课程（特指学生）；
	 * 
	 * @param account
	 * @return
	 */
	public List<Project> projectListStudent(String account);
	
	
	public  List<Project> getAllProject();
}
