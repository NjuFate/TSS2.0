package data;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import entity.InformMessage;

/**
 * Created by dyp on 2016/7/17.
 */
public class InformMsgJsonAnalysis {

    public static List<InformMessage> analysisJson(String jsonStr){
        List<InformMessage> list = new ArrayList<InformMessage>();


//        System.out.println(jsonStr);

        try {
            JSONArray jsonArray = new JSONArray(jsonStr);
            for(int i =0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.optJSONObject(i);
                InformMessage msg = new InformMessage();
                msg.setContent(jsonObject.getString("content"));
                msg.setReceiver(jsonObject.getInt("receiver"));
                msg.setSender(jsonObject.getInt("sender"));
                msg.setTitle(jsonObject.getString("title"));
                msg.setTime(jsonObject.getLong("time"));
                msg.setMessageType(jsonObject.getInt("messagetype"));
                msg.setId(jsonObject.getInt("id"));
                msg.setIconurl(jsonObject.getString("iconurl"));
                msg.setIfread(0);
                msg.setType(1);
                list.add(msg);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

//        Gson gson = new Gson();
//        TempInformMsg temp = gson.fromJson(jsonStr,TempInformMsg.class);
//        System.out.println(temp.getTitle());

//        msg.setContent(temp.content);
//        msg.setIconurl(temp.iconurl);
//        msg.setIfread(temp.ifread);
//        msg.setTitle(temp.title);
//        msg.setId(temp.id);
//        msg.setReceiver(temp.receiver);
//        msg.setSender(temp.sender);
//        msg.setMessageType(temp.messagetype);
//        msg.setTime(temp.time);
        return list;
    }

}

class TempInformMsg{
    private long id;
    private String iconurl;
    private String title;
    private String content;
    private int receiver;
    private int sender;
    private long time;
    private int ifread;
    private int messagetype;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public int getMessagetype() {
        return messagetype;
    }

    public void setMessagetype(int messagetype) {
        this.messagetype = messagetype;
    }
}
