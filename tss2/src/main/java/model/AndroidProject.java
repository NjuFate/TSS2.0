package model;
import po.Project;;

public class AndroidProject {
	
	private String courseno;
	private String coursename;
	private String semester;
	
	public AndroidProject() {
		// TODO Auto-generated constructor stub
	}
	
	public AndroidProject(Project project){
		this.coursename = project.getCoursename();
		this.courseno = project.getCourseno();
		this.semester = project.getSemester();
	
	}
	
	
	public String getCourseno() {
		return courseno;
	}
	public void setCourseno(String courseno) {
		this.courseno = courseno;
	}
	public String getCoursename() {
		return coursename;
	}
	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	
	

}
