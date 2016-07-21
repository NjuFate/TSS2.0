package logic.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import logic.message.MessageSender;
import model.json.Message;

public class MessageSenderTest {
	MessageSender messageSender = new MessageSender();
    
//	@Test
//	public void getToken(){
//		String url = "https://a1.easemob.com/njufate/njufate/token";
//		String client_id = "YXA6S74tkE4lEeaYET-XvTPfjw";
//		String client_secret = "YXA6AD8gVqnNDtelGKgvNvM4RXFFfag";
//		String token = messageSender.getToken(url, client_id, client_secret);
//		assertEquals("YWMtcUZJik8QEeatceM9qGm3UAAAAVdBPJV0JqQSGqA1J-El7_lViVu16FfU-kM", token);  
//		
//	}
	@Test
	public void sendMessage(){
		Message msg = new Message();
		msg.setMsg("hahahah");
		msg.setType("txt");
		String[] target = {"xiaohong","xiaoming","DYP"};
		String from = "admin";
		messageSender.sendMessage(msg, target, from);
	}
	

}
