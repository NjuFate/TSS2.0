package data.service;

import java.util.List;

import model.AndroidProject;
import model.File;

public interface AndroidDownLoadService {
	
	
	
	/**
	 * 返回本学期的课程列表
	 * @return List<courseno,coursename>
	 */
	
	public List<AndroidProject> getCurrentSemesterCourse();
	/**
	 * 返回本学期的课程的所有文件列表
	 * @return List<File>
	 */
	
	public List<File> getCurrentSemesterFile();

}
