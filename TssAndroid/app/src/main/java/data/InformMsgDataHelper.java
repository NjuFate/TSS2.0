package data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import dataservice.InformMsgDataService;
import entity.InformMessage;

/**
 * Created by dyp on 2016/7/13.
 */
public class InformMsgDataHelper implements InformMsgDataService {
    private static int msgNum=0;
    private MydataBaseHelper dbHelper;



    public InformMsgDataHelper(Context context){
        dbHelper = new MydataBaseHelper(context,"TssAndroid",null,1);
    }

    @Override
    public List<InformMessage> getInformMsg(String id) {
        msgNum++;
        ArrayList<InformMessage> msgList = new ArrayList<InformMessage>();
        InformMessage msg = new InformMessage();
        msg.setContent("DYP+"+msgNum);
        msg.setTitle("江泽民");
        msg.setIconurl("!!!");
        msg.setMessageId(System.currentTimeMillis()+"DYP");
        msg.setSender("0000003");
        msg.setReceiver("0000001");
        msg.setTime(System.currentTimeMillis());
        msg.setIfread(0);
        msgList.add(msg);
        saveInformMsg(msgList);
        return getLocalInformMsg(id);
    }

    @Override
    public boolean saveInformMsg(List<InformMessage> list) {


        for(InformMessage msg:list){
            ContentValues values = new ContentValues();
            values.put("id",msg.getMessageId());
            values.put("receiverId",msg.getReceiver());
            values.put("senderId",msg.getSender());
            values.put("title",msg.getTitle());
            values.put("content",msg.getContent());
            values.put("iconurl",msg.getIconurl());
            values.put("time",msg.getTime());
            values.put("ifread",msg.getIfread());
            dbHelper.insert("InformMsg",values);
        }

        return false;
    }

    @Override
    public List<InformMessage> getLocalInformMsg(String userId) {
        String selection = "receiverId=?";
        String[] selectionArgs = new String[]{userId};
        String groupBy = "senderId";
        String  having = "time>=max(time)";
        Cursor cursor = dbHelper.query("InformMsg",null,selection,selectionArgs,groupBy,having,null);


        return loadData(cursor);
    }

    @Override
    public void deleteAllInformMsg(String userId) {
        dbHelper.delete("InformMsg",null,null);
    }

    @Override
    public List<InformMessage> getInformMsgBySender(String receiverID, String senderId) {
        String[] selectionArgs = new String[]{senderId};
        String orderby = "time DESC";
        Cursor cursor = dbHelper.query("InformMsg",null,"senderId=?",selectionArgs,null,null,orderby);

        return loadData(cursor);
    }

    @Override
    public void readMsg(List<InformMessage> list) {
        for(InformMessage msg:list){
            ContentValues values = new ContentValues();
            msg.setIfread(1);
            values.put("ifread",msg.getIfread());
            dbHelper.update("InformMsg",values,"id=?",new String[]{msg.getMessageId()});
        }
    }

    @Override
    public int checkIfRead(String senderId, String receiverId) {
        int result=0;
        String selection = "senderId=? and receiverId=?";
        String[] selectionArgs = new String[]{senderId,receiverId};

        try {
            Cursor cursor = dbHelper.query("InformMsg",null,selection,selectionArgs,null,null,null);
            if(cursor.moveToFirst()) {
                do {
                    int ifread = cursor.getInt(cursor.getColumnIndex("ifread"));
                    if(ifread==0){
                        result++;
                    }
                } while (cursor.moveToNext());
            }
        }catch (NullPointerException e){

        }

        return  result;
    }

    private List<InformMessage> loadData(Cursor cursor){
        List<InformMessage> list = new ArrayList<InformMessage>();
        if(cursor.moveToFirst()){
            do{
                String title = cursor.getString(cursor.getColumnIndex("title"));
                String content = cursor.getString(cursor.getColumnIndex("content"));
                String localiconurl = cursor.getString(cursor.getColumnIndex("localiconurl"));
                String iconurl = cursor.getString(cursor.getColumnIndex("iconurl"));
                String id = cursor.getString(cursor.getColumnIndex("id"));
                String senderId = cursor.getString(cursor.getColumnIndex("senderId"));
                String receiverId = cursor.getString(cursor.getColumnIndex("receiverId"));
                long time = cursor.getLong(cursor.getColumnIndex("time"));
                int ifread = cursor.getInt(cursor.getColumnIndex("ifread"));
                InformMessage msg = new InformMessage();
                msg.setTitle(title);
                msg.setContent(content);
                msg.setIconurl(localiconurl);
                msg.setMessageId(id);
                msg.setLocaliconurl(localiconurl);
                msg.setReceiver(receiverId);
                msg.setSender(senderId);
                msg.setTime(time);
                msg.setIfread(ifread);
                list.add(msg);

            }while (cursor.moveToNext());
        }
        return list;
    }


}
