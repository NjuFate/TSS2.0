package model;

import java.util.List;

/**
 * Project Model
 * 
 * @author WangHuan
 *
 *
 *例：
 *	courseno c1092(教务网的课程编号)
 *  coursename SEI 
 *  instrutor 刘钦
 *  semester 2016fall
 *	teaching_assistants 无
 *
 *
 */
public class Project {
	private String courseno ;//课程编号
	private String coursename;//课程名称
	private String instrutor;//教师
	private String semester;//学期
	private String teaching_assistants;//助教
	
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
	public String getInstrutor() {
		return instrutor;
	}
	public void setInstrutor(String instrutor) {
		this.instrutor = instrutor;
	}
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public String getTeaching_assistants() {
		return teaching_assistants;
	}
	public void setTeaching_assistants(String teaching_assistants) {
		this.teaching_assistants = teaching_assistants;
	}
	

}
