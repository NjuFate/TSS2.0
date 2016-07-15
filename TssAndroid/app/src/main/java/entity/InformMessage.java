package entity;

/**
 * Created by dyp on 2016/7/13.
 */
public class InformMessage {
    public static final int MESSAGE_FROM_OTHER = 1;
    public static final int MESSAGE_FROM_USER = 0;
    public static final int MESSAGE_READ = 1;
    public static final int MESSAGE_UNREAD = 0;
    public static final int MESSAGE_TYPE_USER = 1;
    public static final int MESSAGE_TYPE_SYSTEM = 0;

    private String iconurl;
    private String title;
    private String content;
    private long messageId;
    private int receiver;
    private int sender;
    private String localiconurl;
    private long time;
    private int ifread;
    private int type;
    private int messageType;





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

    public String getLocaliconurl() {
        return localiconurl;
    }

    public void setLocaliconurl(String localiconurl) {
        this.localiconurl = localiconurl;
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

    public long getMessageId() {
        return messageId;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }

    public int getReceiver() {
        return receiver;
    }

    public void setReceiver(int receiver) {
        this.receiver = receiver;
    }

    public int getSender() {
        return sender;
    }

    public void setSender(int sender) {
        this.sender = sender;
    }

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }
}
