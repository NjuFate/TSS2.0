package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
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
	private List<String> instrutor;//教师
	private String semester;//学期
	private List<String> teaching_assistants;//助教
	
	public Project(po.Project project) {
		// TODO Auto-generated constructor stub
		this.courseno = project.getCourseno();
		this.coursename = project.getCoursename();
		if(project.getInstrutor()!=null)
		this.instrutor = Arrays.asList(project.getInstrutor().split(" "));
		this.semester = project.getSemester();
		if(project.getTeaching_assistants()!=null)
		this.teaching_assistants = Arrays.asList(project.getTeaching_assistants().split(" "));
		
	}
	
	public Project(){
		instrutor = new ArrayList<String>();
		teaching_assistants = new ArrayList<String>();
		
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
	public Iterator<String> getInstrutor() {
		return instrutor.iterator();
	}
	public void setInstrutor(String instrutor) {
		this.instrutor.add(instrutor);
	}
	public void setInstrutor(List<String> instrutor) {
		this.instrutor = instrutor;
	}
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public Iterator<String> getTeaching_assistants() {
		return teaching_assistants.iterator();
	}
	public void setTeaching_assistants(String teaching_assistants) {
		this.teaching_assistants.add(teaching_assistants);
	}
	public void setTeaching_assistants(List<String> teaching_assistants) {
		this.teaching_assistants = teaching_assistants;
	}
	
	public String toString(Iterator<String>lists){
		String result = "";
		while(lists.hasNext()){
			String string = lists.next();
			result = result + string + " ";
		}
	  return result;
	}
	

}
