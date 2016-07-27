package data.file;

import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import data.manage.FileManage;
import data.manage.ProjectManage;
import data.service.AndroidDownLoadService;
import model.AndroidProject;
import model.File;
import po.Project;

public class AndroidFile implements AndroidDownLoadService{
	private static final String[] semester = {"Winter", "Spring", "Summer", "Fall"};
	FileManage fileManage;
	ProjectManage projectManage;

	public AndroidFile() {
		// TODO Auto-generated constructor stub
		fileManage = new FileManage();
		projectManage = new ProjectManage();

	}

	public List<AndroidProject> getCurrentSemesterCourse() {
		// TODO Auto-generated method stub
		String sql = "select * from project where semester='" + getCurrentSemester()+"'";
		try {
			List<AndroidProject>projects = projectManage.executeQuery(sql, new AndroidProject());
			
			return projects;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}



	
	public List<File> getCurrentSemesterFile(){
		List<AndroidProject> projects = getCurrentSemesterCourse();
		
		List<File> files = new ArrayList<File>();
		for(AndroidProject project: projects){
			files.addAll(getFileByCourseno(project.getCourseno()));
		}
		return files;
	}


	private List<File> getFileByCourseno(String courseno) {
		
		// TODO Auto-generated method stub
		String sql = "select * from file where courseno='" +courseno +"'";
		try {
			List<po.File>files = fileManage.executeQuery(sql, new po.File());
			List<File> rFiles = new ArrayList<File>();

			for(po.File f : files){
				rFiles.add(new File(f));
			}
			return rFiles;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}


	private String getCurrentSemester(){

		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);//获取年份
		int month=cal.get(Calendar.MONTH) + 1;//获取月份
		return year + semester[(month/3)%4];

	}



}
