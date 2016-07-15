package entity;

/**
 * Created by dyp on 2016/7/13.
 */
public class InformMessage {
    private String iconurl;
    private String title;
    private String content;
    private String messageId;
    private String receiver;
    private String sender;
    private String localiconurl;
    private long time;
    private int ifread;
    private int type;


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

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

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
}
