package data.test;


import java.util.List;

import org.junit.Test;

import data.message.InformMessageImpl;

public class MessageImplTest {
	InformMessageImpl model = new InformMessageImpl();

	@Test
	public void test() {
	List<model.InformMessage> list = model.getInformMsg("141250052", 1);
	System.out.println(list.get(0).toString());
	}

}
