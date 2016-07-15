package po;

public class Message {
	private String iconurl;    //发送者头像地址
	private String title;		  //发送者昵称
	private String content;		//信息内容
	private String messageId;	//每个信息唯一的ID
	private String receiver;		//接收者的ID
	private String sender;		//发送者的ID
	private long time;			//信息发送的时间，用毫秒
	private int ifread;			//信息是否阅读过，0为否，1为是，默认为0
	private int type;			//信息类型，0是官方通知，1是用户之间
	public String getIconurl() {
		return iconurl;
	}
	public void setIconurl(String iconurl) {
		this.iconurl = iconurl;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public int getIfread() {
		return ifread;
	}
	public void setIfread(int ifread) {
		this.ifread = ifread;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	
	

}
