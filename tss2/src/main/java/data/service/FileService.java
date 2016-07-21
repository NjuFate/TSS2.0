package data.service;

import java.util.List;

import model.File;
import model.FileExtra;

public interface FileService {
	/**
	 * 根据课程返回课程下的文件列表
	 * 返回的文件list只需要包括该课程文件下的第一层文件的目录
	 * @param courseno 课程编号
	 * @param coursename 课程名
	 * @param semester 学期
	 * @param father 父文件夹名 （由课程+ 父文件夹 唯一定位防止重名）
	 * @return
	 * 不一定要将三个参数全部作为条件，只要保证条件可以满足唯一性要求即可
	 * 比如仅判断coursename 和semester 是否相等
	 */
	public List<File> getFileByCourse(String courseno,String coursename,String semester,String father);
	
	public List<File> getFileByCourseno(String courseno,String father);
	
	
	/**
	 * 返回文件信息通过id查询
	 * @return String[path,filename,icon]
	 */
	public FileExtra searchByID(long id);
	
	/**
	 * 返回文件信息通过名字查询
	 * @return List[id,filename,icon]
	 */
	public List<FileExtra> searchByName(String name);
	
	/**
	 * 添加文件目录项
	 * @param file
	 * @return true or false
	 */
	
	public boolean addFileMessage(File file);
	

	
	
}
