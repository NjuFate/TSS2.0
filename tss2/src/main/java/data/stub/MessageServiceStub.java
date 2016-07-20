package data.stub;

import java.util.List;

import data.service.MessageService;
import model.InformMessage;

public class MessageServiceStub implements MessageService{

	public List<InformMessage> getInformMsg(String account, long time) {
		// TODO Auto-generated method stub
		InformMessage informMessage = new InformMessage();
		
		
		
		return null;
	}

	public long sendMessage(InformMessage inform) {
		// TODO Auto-generated method stub
		return 0;
	}

}
