package po;


public class UserRole implements BaseId{
	private Long id;
	private Long uid;
	private String role;
	
	
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	

}
