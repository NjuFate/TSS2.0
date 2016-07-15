package data.message;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import data.base.JDBCHelper;
import data.base.ParticularQuery;
import data.manage.MessageManage;
import data.manage.UserManage;
import data.service.MessageService;
import po.User;
import po.InformMessage;

public class MessageImpl implements MessageService{
	
	MessageManage messageManage;
	UserManage userManage;
	ParticularQuery query;
	
	public MessageImpl() {
		// TODO Auto-generated constructor stub
		messageManage = new MessageManage();
		userManage = new UserManage();
		query = new ParticularQuery(new JDBCHelper());
	}
	
	

	public List<model.InformMessage> getInformMsg(String account, long time) {
		// TODO Auto-generated method stub
		int id = query.getUserIdByAccount(account);
		String sql  = "select * from informMessage where  receiver = " + id + " and time >=" +time;
		 try {
			List<InformMessage> msgs = messageManage.executeQuery(sql, new InformMessage());
			List<model.InformMessage> mmsgs  =  new ArrayList<model.InformMessage>();
			User user = new User();
			user.setId(msgs.get(0).getSender());
		    user = userManage.query(user).get(0);
		    String iconurl = user.getIconurl();
		    String title = user.getNickName();
		    
		    for(InformMessage m:msgs){
		    	model.InformMessage i = new model.InformMessage(m);
		    	i.setIconurl(iconurl);
		    	i.setTitle(title);
		    	mmsgs.add(i);
		    }
			return mmsgs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 
		 
		 
		return null;
	}

	public long sendMessage(model.InformMessage inform) {
		// TODO Auto-generated method stub
		InformMessage message = new InformMessage(inform);
		
	   return messageManage.addByLong(message);
		
	}
	
	
	
	
}
