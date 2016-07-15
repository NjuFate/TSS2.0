package data;

import java.util.ArrayList;
import java.util.List;

import entity.InformMessage;

/**
 * Created by dyp on 2016/7/15.
 */
public class InformMsgNetworkHelper {

    public long sendInformMsg(InformMessage msg){
        return 0;
    }

    public List<InformMessage> getInformMsg(int userId){
        ArrayList<InformMessage> msgList = new ArrayList<InformMessage>();
        InformMessage msg = new InformMessage();
        msg.setContent("DYP");
        msg.setTitle("江泽民");
        msg.setIconurl("!!!");
        msg.setMessageId(System.currentTimeMillis());
        msg.setSender(2);
        msg.setReceiver(1);
        msg.setTime(System.currentTimeMillis());
        msg.setIfread(0);
        msg.setType(1);
        msgList.add(msg);

        return msgList;
    }
}
