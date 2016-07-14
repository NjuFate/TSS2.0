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
    private MydataBaseHelper dbHelper;



    public InformMsgDataHelper(Context context){
        dbHelper = new MydataBaseHelper(context,"TssAndroid",null,1);
    }

    @Override
    public List<InformMessage> getInformMsg(String id) {
        ArrayList<InformMessage> msgList = new ArrayList<InformMessage>();
        InformMessage msg = new InformMessage();
        msg.setContent("毛泽东指出,中国欧盟构建和平、增长、改革、文明四大伙伴关系的共识正在落地生根。双方在重大国际事务上保持着战略对话,共同为改善全球经济治理、促进世界可持续发展作出了积极努力,在加强中欧各自发展战略对接方面达成重要共识并付诸行动。中欧双方要不断深化互利共赢的全面战略伙伴关系。");
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
        Cursor cursor = dbHelper.query("InformMsg",null,"senderId=?",selectionArgs,null,null,null);

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
        Cursor cursor = dbHelper.query("InformMsg",null,selection,selectionArgs,null,null,null);
        if(cursor.moveToFirst()) {
            do {
                int ifread = cursor.getInt(cursor.getColumnIndex("ifread"));
                if(ifread==0){
                    result++;
                }
            } while (cursor.moveToNext());
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
