package data.test;

import static org.junit.Assert.*;

import org.junit.Test;

import data.message.MessageProcess;
import model.Message;

public class MessageProcessTest {
	MessageProcess Process = new MessageProcess();

	@Test
	public void testSaveMessage() {
		Message message = new Message();
		message.setAccount("141250054");
		message.setMessage("条目更新");
		message.setSource("wiki");
		Process.saveMessage(message);
		
	}

	@Test
	public void testSearchByAccount() {
		assertEquals(2, Process.searchByAccount("141250052").size());
	}

}
