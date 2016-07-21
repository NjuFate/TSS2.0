package po;


public class Message implements BaseId{
	
	private Long id; 
	private String message;
	private String account;
	private String source;
	private Long time;
	private Integer ifread;
	
	
	
	public Message() {
		// TODO Auto-generated constructor stub
	}
	
	public Message(model.Message message){
		this.message = message.getMessage();
		this.account = message.getAccount();
		this.source = message.getSource();
	}
	
	
	
	
	
	public Long getTime() {
		return time;
	}
	public void setTime(Long time) {
		this.time = time;
	}
	public Integer getIfread() {
		return ifread;
	}
	public void setIfread(Integer ifread) {
		this.ifread = ifread;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public Long getId() {
		return id;
	}

	public void setId(long id) {
		// TODO Auto-generated method stub
		this.id = id;
	}
	
	

}
