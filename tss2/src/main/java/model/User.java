package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.omg.PortableServer.POA;

import general.Role;

public class User {

	private int id;// 编号
	private String account;// 账号
	private String name; // 名字
	private int age; // 年龄
	private String iconurl;
	private String nickName;//昵称
	
	
	
	public String getPsw() {
		return psw;
	}

	public void setPsw(String psw) {
		this.psw = psw;
	}


	private String psw; // 密码
	private List<String> major; // 主修方向
	private Role role;//角色分类
	
	
	public User() {
		// TODO Auto-generated constructor stub
		major = new ArrayList<String>();
	}
	
	public User(po.User user){
		this.id = user.getId();
		this.account = user.getAccount();
		this.name = user.getName();
		this.age = user.getAge();
	    this.psw = user.getPsw();
		this.major = Arrays.asList(user.getMajor().split(" "));

		
	}
	
	

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Iterator<String> getMajor() {
		return major.iterator();
	}

	public void setMajor(String major) {
		this.major.add(major);
	}
	
	public void setMajor(List<String> major) {
		this.major = major;
	}
	
	
	public String toString(Iterator<String>lists){
		String result = "";
		while(lists.hasNext()){
			String string = lists.next();
			result = result + string + " ";
		}
	  return result;
	}

	public String getIconurl() {
		return iconurl;
	}

	public void setIconurl(String iconurl) {
		this.iconurl = iconurl;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
}
