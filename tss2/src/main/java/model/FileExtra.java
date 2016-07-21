package model;

public class FileExtra {

	private long Id;
	private String Url;
	private String Title;
	private String Icon;

	public FileExtra(FileEx ex) {
		// TODO Auto-generated constructor stub
		this.Icon = ex.getIcon();
		this.Url = ex.getPath();
		this.Title = ex.getFileName();
		this.Id = ex.getId();
	
	}






	public long getId() {
		return Id;
	}
	public void setId(long id) {
		Id = id;
	}
	public String getUrl() {
		return Url;
	}
	public void setUrl(String url) {
		Url = url;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getIcon() {
		return Icon;
	}
	public void setIcon(String icon) {
		Icon = icon;
	}



}
