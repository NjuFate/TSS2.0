package PO;

import java.util.ArrayList;

public class User{
	private int uid;//编号
	private String uname;
	private String bithYearAndMonth;//出生年月
    private ArrayList<String> major;
	public int getUid() {
		return uid;
	}
	public void setUid(int id) {
		this.uid = id;
	}
	public String getUName() {
		return uname;
	}
	public void setUName(String name) {
		this.uname = name;
	}
	public String getBithYearAndMonth() {
		return bithYearAndMonth;
	}
	public void setBithYearAndMonth(String bithYearAndMonth) {
		this.bithYearAndMonth = bithYearAndMonth;
	}
	public ArrayList<String> getMajor() {
		return major;
	}
	public void setMajor(ArrayList<String> major) {
		this.major = major;
	}
	
	
	
	
	
	
	
	
	
}
