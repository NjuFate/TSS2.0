package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;



public class FileJson implements Comparable<FileJson>{
	private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
	private String fileName;
	private String uploadBy;
	private String updateTime;
	private String courseno;
	
	
	public String getCourseno() {
		return courseno;
	}
	public void setCourseno(String courseno) {
		this.courseno = courseno;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getUploadBy() {
		return uploadBy;
	}
	public void setUploadBy(String uploadBy) {
		this.uploadBy = uploadBy;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
	
	public int compareTo(FileJson o) {
		// TODO Auto-generated method stub
		Date thisDate = null;
		Date otherDate = null;
		try {
			thisDate = df.parse(this.updateTime);
			otherDate = df.parse(o.updateTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(thisDate.before(otherDate))
			return 1;
		else if(thisDate.after(otherDate))
			return -1;
		else return 0;
	}
	
	
	

}
