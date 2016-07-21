package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import general.Role;

public class User {

	private long id;// 编号
	private String account;// 账号
	private String hAccount;//环信帐号
	private String psw; // 密码
	private String name; // 名字
	private int age; // 年龄

	private String iconurl;
	private String nickName;//昵称
	
	private List<String> major; // 主修方向
	private Role role;//角色分类
	
	//new add 非必需
	private String email;//邮箱
	private String qq;//qq
	private String grade;//年级
	private String tel;//电话
	
	private String educational_ID;//教务网账号
	private String educational_Psw;//教务网密码
	
	
	
	public String getPsw() {
		return psw;
	}

	public void setPsw(String psw) {
		this.psw = psw;
	}



	
	
	public User() {
		// TODO Auto-generated constructor stub
		major = new ArrayList<String>();
	}
	
	public User(po.User user){
	
		this.major = Arrays.asList(user.getMajor().split(" "));
		this.id = user.getId();
		this.account = user.getAccount();
		this.hAccount = user.gethAccount();
		this.name = user.getName();
		this.age = user.getAge();
		this.psw = user.getPsw();
		this.email = user.getEmail();
		this.qq = user.getQq();
		this.tel = user.getTel();
		this.educational_ID = user.getEducational_ID();
		this.educational_Psw = user.getEducational_Psw();
		this.grade = user.getGrade();
        this.iconurl = user.getIconurl();
        this.nickName = user.getNickName();
		
	}
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEducational_ID() {
		return educational_ID;
	}
	public void setEducational_ID(String educational_ID) {
		this.educational_ID = educational_ID;
	}
	public String getEducational_Psw() {
		return educational_Psw;
	}
	public void setEducational_Psw(String educational_Psw) {
		this.educational_Psw = educational_Psw;
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public String gethAccount() {
		return hAccount;
	}

	public void sethAccount(String hAccount) {
		this.hAccount = hAccount;
	}
}
