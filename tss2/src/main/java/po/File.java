package po;


public class File implements BaseId{
	private Long id;// 数据库id
	private Integer isFolder;
	private String father;//上级目录，第一级目录下的文件的父目录为课程名
	private String fileName;//文件名
	private String updateTime;//跟新时间
	private String uploadBy;//上传者 帐号
	private String path;//存储路径
	
	private String courseno;//课程编号（这里要确保课程编号的唯一性）
	//若课程编号无法保证唯一性可以考虑使用课程名+学期
	private String coursename;
	private String semester;
	private String icon = "heheda";

	
	public File(model.File file){
		if(file.getIsFolder())
			isFolder = 1; 
		else
			isFolder = 0;
		this.father = file.getFather();
		this.fileName = file.getFileName();
		this.updateTime = file.getUpdateTime();
		this.uploadBy = file.getUploadBy();
		this.path = file.getPath();
		this.courseno = file.getCourseno();
		this.coursename = file.getCoursename();
		this.semester = file.getSemester();
		
	}
	
	public File() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	public Integer getIsFolder() {
		return isFolder;
	}
	public void setIsFolder(int isFolder) {
		this.isFolder = isFolder;
	}
	public String getFather() {
		return father;
	}
	public void setFather(String father) {
		this.father = father;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getUploadBy() {
		return uploadBy;
	}
	public void setUploadBy(String uploadBy) {
		this.uploadBy = uploadBy;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
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
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public void setId(long id) {
		// TODO Auto-generated method stub
		this.id = id;

	}
	public Long getId() {
		// TODO Auto-generated method stub
		return id;
	}
	
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	
}