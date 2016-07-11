package PO;

public class File{
	
	private Integer id;
	private String fileName;
	private String path;
	private String uploadUser;
	private Integer downNum;
	private String fatherFile;
	private String courseID;
	public Integer getId() {
		return id;
	}
	public void setId(int fileID) {
		this.id = fileID;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getUploadUser() {
		return uploadUser;
	}
	public void setUploadUser(String uploadUser) {
		this.uploadUser = uploadUser;
	}
	public Integer getDownNum() {
		return downNum;
	}
	public void setDownNum(int downNum) {
		this.downNum = downNum;
	}
	public String getFatherFile() {
		return fatherFile;
	}
	public void setFatherFile(String fatherFile) {
		this.fatherFile = fatherFile;
	}
	public String getCourseID() {
		return courseID;
	}
	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}
	
	
	

}
