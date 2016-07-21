package data.message;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import data.login.LoginIn;
import data.manage.MessageManage;
import data.service.MessageProcessService;
import model.Message;

public class MessageProcess implements MessageProcessService{

	
	MessageManage manage;
	LoginIn loginIn;
	
	public MessageProcess() {
		// TODO Auto-generated constructor stub
		manage = new MessageManage();
		loginIn = new LoginIn();
	}
	
	
	
	
	public boolean saveMessage(Message message) {
		// TODO Auto-generated method stub
		//验证帐号是否存在
		
		if(loginIn.roleIdentifier(message.getAccount()) == null)
			return false;
			
		Calendar cal = Calendar.getInstance();
		long time = cal.getTimeInMillis();//获取年份
		po.Message mess = new po.Message(message);
		mess.setTime(time);
		long id = manage.add(mess);		
		if(id == 0)
			return false;
		
		return true;
	}

	public List<Message> searchByAccount(String account) {
		// TODO Auto-generated method stub
		
		String sql = "select * from message where account='"+account+"'";
		
		try {
			List<Message>messages = manage.executeQuery(sql, new Message());
			return messages;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}



}
