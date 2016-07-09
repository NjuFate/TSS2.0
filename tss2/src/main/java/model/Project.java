package model;

import java.util.List;

/**
 * Project Model
 * 
 * @author WangHuan
 *
 */
public class Project {
	private int pid;// 课程编号
	private int grade;// 年级
	private String startTime;// 课程开始时间
	private String endTime;// 课程结束时间
	private String pname;// 课程名称

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

}
