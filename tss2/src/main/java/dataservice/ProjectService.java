package dataservice;

import java.util.List;

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
	 */
	public List<Project> projectList(String account);
}
