package entity;

public class Course {
	private String id;
	private String name;
	private String message;
	private String teachers;
	
	
	
	public Course(String id, String name, String message, String teachers) {
		super();
		this.id = id;
		this.name = name;
		this.message = message;
		this.teachers = teachers;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getTeachers() {
		return teachers;
	}
	public void setTeachers(String teachers) {
		this.teachers = teachers;
	}
	
	
	
}
