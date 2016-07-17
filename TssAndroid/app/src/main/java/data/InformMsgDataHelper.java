package data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Handler;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
        dbHelper = MydataBaseHelper.getInstance(context);

    }



    @Override
    public boolean saveInformMsg(List<InformMessage> list) {


        for(InformMessage msg:list){
            ContentValues values = new ContentValues();
            values.put("id",msg.getId());
            values.put("receiverId",msg.getReceiver());
            values.put("senderId",msg.getSender());
            values.put("title",msg.getTitle());
            values.put("content",msg.getContent());
            values.put("iconurl",msg.getIconurl());
            values.put("time",msg.getTime());
            values.put("ifread",msg.getIfread());
            values.put("type",msg.getType());
            dbHelper.insert("InformMsg",values);
        }

        return false;
    }

    @Override
    public List<InformMessage> getLocalInformMsg(int userId) {
        String selection = "receiverId=?";
        String[] selectionArgs = new String[]{userId+""};
        String groupBy = "senderId";
        String  having = "time>=max(time)";
        Cursor cursor = dbHelper.query("InformMsg",null,selection,selectionArgs,groupBy,having,null);
        Cursor cursor1 = dbHelper.query("InformMsg",null,"senderId=?",new String[]{userId+""},"receiverId","time>=max(time)",null);
        List<InformMessage> senderMsgList  = loadData(cursor);
        List<InformMessage> userMsgList = loadData(cursor1);
        List<InformMessage> tempList = new ArrayList<InformMessage>();

        for(InformMessage senderMsg:senderMsgList){
            for(InformMessage userMsg:userMsgList){
                if(senderMsg.getSender() == userMsg.getReceiver()){
                    if(senderMsg.getTime()<userMsg.getTime()){
                        senderMsgList.remove(senderMsg);
                        tempList.add(userMsg);
                    }
                }
            }
        }

        for(InformMessage msg:tempList){
            senderMsgList.add(msg);
        }


        return senderMsgList;
    }

    @Override
    public void deleteAllInformMsg(int userId) {
        dbHelper.delete("InformMsg",null,null);
    }

    @Override
    public List<InformMessage> getInformMsgBySender(int receiverID, int senderId) {
        String[] selectionArgs = new String[]{senderId+"",receiverID+"",receiverID+"",senderId+""};
        String selection = "(senderId=? and receiverId=?) OR (senderId=? and receiverId=?)";
        String orderby = "time DESC";
        Cursor cursor = dbHelper.query("InformMsg",null,selection,selectionArgs,null,null,orderby);

        return loadData(cursor);
    }

    @Override
    public void readMsg(List<InformMessage> list) {
        for(InformMessage msg:list){
            ContentValues values = new ContentValues();
            msg.setIfread(1);
            values.put("ifread",msg.getIfread());
            dbHelper.update("InformMsg",values,"id=?",new String[]{msg.getId()+""});
        }
    }

    @Override
    public int checkIfRead(int senderId, int receiverId) {
        int result=0;
        String selection = "senderId=? and receiverId=?";
        String[] selectionArgs = new String[]{senderId+"",receiverId+""};

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

    @Override
    public String getLastTime(List<InformMessage> list) {
        String result;
        long time = 0;
        for(InformMessage msg:list){
            if(time<msg.getTime())time=msg.getTime();
        }
        Calendar mCalendar= Calendar.getInstance();
        mCalendar.setTimeInMillis(time);
        Calendar nCalendar= Calendar.getInstance();
        nCalendar.setTimeInMillis(System.currentTimeMillis());

        int daydistance = nCalendar.get(Calendar.DAY_OF_YEAR)-mCalendar.get(Calendar.DAY_OF_YEAR);

        int yeardistcance = nCalendar.get(Calendar.YEAR)-mCalendar.get(Calendar.YEAR);
        if(yeardistcance!=0){
            daydistance = daydistance + 365*yeardistcance;
        }



        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format1 = new SimpleDateFormat("HH:mm");
        Date date = mCalendar.getTime();
        switch (daydistance){
            case 0:
                result = format1.format(date);
                break;
            case 1:
                result = "昨天";
                break;
            case 2:
                result = "前天";
                break;
            default:
                result = format.format(date);
                break;
        }

        return result;
    }

    @Override
    public String getTimeDetail(InformMessage message) {
        String result = "";
        long time = message.getTime();
        Calendar mCalendar= Calendar.getInstance();
        mCalendar.setTimeInMillis(time);
        Calendar nCalendar= Calendar.getInstance();
        nCalendar.setTimeInMillis(System.currentTimeMillis());

        int daydistance = nCalendar.get(Calendar.DAY_OF_YEAR)-mCalendar.get(Calendar.DAY_OF_YEAR);

        int yeardistcance = nCalendar.get(Calendar.YEAR)-mCalendar.get(Calendar.YEAR);
        if(yeardistcance!=0){
            daydistance = daydistance + 365*yeardistcance;
        }



        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat format1 = new SimpleDateFormat("HH:mm:ss");
        Date date = mCalendar.getTime();

        switch (daydistance){
            case 0:
                result = format1.format(date);
                break;
            default:
                result = format.format(date);
                break;
        }

        return result;
    }

    @Override
    public long sendMsg(int userId, int receiverId, String content) {
        InformMessage msg = new InformMessage();
        msg.setContent(content);
        msg.setTitle("oldyellow");
        msg.setIconurl("!!!");
        msg.setId(System.currentTimeMillis());
        msg.setSender(userId);
        msg.setReceiver(receiverId);
        msg.setTime(System.currentTimeMillis());
        msg.setIfread(1);
        msg.setType(0);

//        long id = networkHelper.sendInformMsg(msg);
//
//        msg.setMessageId(id);
        List<InformMessage> list = new ArrayList<InformMessage>();
        list.add(msg);
        saveInformMsg(list);
        return msg.getId();
    }

    private List<InformMessage> loadData(Cursor cursor){
        List<InformMessage> list = new ArrayList<InformMessage>();
        if(cursor.moveToFirst()){
            do{
                String title = cursor.getString(cursor.getColumnIndex("title"));
                String content = cursor.getString(cursor.getColumnIndex("content"));
                String localiconurl = cursor.getString(cursor.getColumnIndex("localiconurl"));
                String iconurl = cursor.getString(cursor.getColumnIndex("iconurl"));
                long id = cursor.getLong(cursor.getColumnIndex("id"));
                int senderId = cursor.getInt(cursor.getColumnIndex("senderId"));
                int receiverId = cursor.getInt(cursor.getColumnIndex("receiverId"));
                long time = cursor.getLong(cursor.getColumnIndex("time"));
                int ifread = cursor.getInt(cursor.getColumnIndex("ifread"));
                int type = cursor.getInt(cursor.getColumnIndex("type"));
                int messagetype = cursor.getInt(cursor.getColumnIndex("messagetype"));
                InformMessage msg = new InformMessage();
                msg.setTitle(title);
                msg.setContent(content);
                msg.setIconurl(iconurl);
                msg.setLocaliconurl(localiconurl);
                msg.setId(id);
                msg.setLocaliconurl(localiconurl);
                msg.setReceiver(receiverId);
                msg.setSender(senderId);
                msg.setTime(time);
                msg.setIfread(ifread);
                msg.setType(type);
                msg.setMessageType(messagetype);
                list.add(msg);

            }while (cursor.moveToNext());
        }
        return list;
    }


}
