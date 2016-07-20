package po;

public class MailBox implements BaseId{
	private Long id;
	private Long uid;
	private String mailBox;
	public Long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	public String getMailBox() {
		return mailBox;
	}
	public void setMailBox(String mailBox) {
		this.mailBox = mailBox;
	}
	
	
	
	

}
