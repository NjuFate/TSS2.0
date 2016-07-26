package logic.test;

import static org.junit.Assert.*;

import org.junit.Test;

import logic.message.MessageReciver;
import model.json.Info;

public class MessageReciverTest {
	@Test
	public void testJsonObj(){
		String jsonStr = "{'account':['123','123'],'message':'hello','source':'sys'}";
		Info info = MessageReciver.getInfoFromStr(jsonStr);
		assertEquals("hello",info.getMessage());
		//return true;
	}
}
