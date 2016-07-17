package dataservice;

import java.util.List;

import entity.InformMessage;

/**
 * Created by dyp on 2016/7/13.
 */
public interface InformMsgDataService {
    public boolean saveInformMsg(List<InformMessage> list);
    public List<InformMessage> getLocalInformMsg(int userId);
    public void deleteAllInformMsg(int userId);
    public List<InformMessage> getInformMsgBySender(int receiverID, int senderId);
    public void readMsg(List<InformMessage> list);
    public int checkIfRead(int senderId, int receiverId);
    public String getLastTime(List<InformMessage> list);
    public String getTimeDetail(InformMessage message);
    public long sendMsg(int userId, int receiverId,String content);
}
