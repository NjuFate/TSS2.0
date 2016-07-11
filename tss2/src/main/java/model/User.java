package model;

import java.util.ArrayList;
import java.util.Iterator;

public class User {


	private int id;//编号
	private String account;//账号
	private String name; //名字
	private int age; //年龄
	private ArrayList<String> major; //主修方向

	
	
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

	public void setMajor(ArrayList<String> major) {
		this.major = major;
	}
}
