package com.example.tss.message.helper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import com.example.tss.message.entity.InformMessage;

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

