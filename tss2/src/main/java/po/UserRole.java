package po;


public class UserRole implements BaseId{
	private Integer id;
	private Integer uid;
	private String RCode;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(int urid) {
		this.id = urid;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getRCode() {
		return RCode;
	}
	public void setRCode(String rCode) {
		RCode = rCode;
	}
	

}
