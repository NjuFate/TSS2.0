package data.test;

import static org.junit.Assert.*;
import org.junit.Test;

import data.manage.MailBoxManage;
import po.MailBox;

public class MailBoxManageTest {
	MailBoxManage dao = new MailBoxManage();

//	@Test
//	public void testAdd() {
//		MailBox box = new MailBox();
//		box.setMailBox("1533704796@qq.com");
//		box.setUid(1607121000);
//		dao.add(box);
//		box.setUid(1607121002);
//		dao.add(box);
//		box.setMailBox("1425296454@qq.com");
//		dao.add(box);
//		
//	}

//	@Test
//	public void testDelete() {
//		MailBox box = new MailBox();
//		box.setMailBox("1533704796@qq.com");
//		box.setUid(1607121000);
//		dao.delete(box);
//		
//	}

	@Test
	public void testUpdate() {

		MailBox box = new MailBox();
		box.setUid(1607121001);
		box = dao.query(box).get(0);
	    assertEquals("1425296454@qq.com", box.getMailBox());
	    assertEquals("160712001", box.getId().toString());
		
		box.setMailBox("1533704796@qq.com");
		dao.update(box);
		
	}

	@Test
	public void testQuery() {
		fail("Not yet implemented");
	}

}
