package dataservice;

import java.util.List;

import entity.InformMessage;

/**
 * Created by dyp on 2016/7/13.
 */
public interface InformMsgDataService {
    public List<InformMessage> getInformMsg(String id);
    public boolean saveInformMsg(List<InformMessage> list);
    public List<InformMessage> getLocalInformMsg(String userId);
    public void deleteAllInformMsg(String userId);
    public List<InformMessage> getInformMsgBySender(String receiverID, String senderId);
    public void readMsg(List<InformMessage> list);
    public int checkIfRead(String senderId, String receiverId);
}
