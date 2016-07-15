package po;

public class InformMessage implements BaseLongId{
	private Long id;// 数据库id
	private String content;		//信息内容
	private Integer receiver;	//接收者的ID
	private Integer sender;		//发送者的ID
	private Long time;			//信息发送的时间，用毫秒
	private Integer ifread;		//信息是否阅读过，0为否，1为是，默认为0
	private Integer messagetype;//信息类型，0是官方通知，1是用户之间
	
	
	
	public InformMessage(model.InformMessage informMessage) {
		// TODO Auto-generated constructor stub
//		this.id = informMessage.getId();
		this.content = informMessage.getContent();
		this.receiver = informMessage.getReceiver();
		this.sender = informMessage.getSender();
		this.time = informMessage.getTime();
		this.ifread = informMessage.getIfread();
		this.messagetype = informMessage.getMessagetype();
		
	}
	public InformMessage() {
		// TODO Auto-generated constructor stub
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public Integer getReceiver() {
		return receiver;
	}
	public void setReceiver(Integer receiver) {
		this.receiver = receiver;
	}
	public Integer getSender() {
		return sender;
	}
	public void setSender(Integer sender) {
		this.sender = sender;
	}
	public Long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public Integer getIfread() {
		return ifread;
	}
	public void setIfread(int ifread) {
		this.ifread = ifread;
	}
	public Long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Integer getMessagetype() {
		return messagetype;
	}
	public void setMessagetype(Integer messagetype) {
		this.messagetype = messagetype;
	}

	
	
	

}
