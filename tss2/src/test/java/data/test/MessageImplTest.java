package data.test;


import java.util.List;

import org.junit.Test;

import data.message.MessageImpl;

public class MessageImplTest {
	MessageImpl model = new MessageImpl();

	@Test
	public void test() {
	List<model.InformMessage> list = model.getInformMsg("141250052", 1);
	System.out.println(list.get(0).toString());
	}

}
