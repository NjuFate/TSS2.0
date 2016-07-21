package data.message;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import data.base.UserQuery;
import data.manage.InformMessageManage;
import data.manage.UserManage;
import data.service.MessageService;
import model.LoginUser;
import po.User;
import po.InformMessage;

public class InformMessageImpl implements MessageService{
	
	InformMessageManage messageManage;
	UserManage userManage;
	UserQuery query;
	
	public InformMessageImpl() {
		// TODO Auto-generated constructor stub
		messageManage = new InformMessageManage();
		userManage = new UserManage();
		query = new UserQuery(userManage);
	}
	

	/**
	 * 提供一个用户的id和毫秒时间，传回所有receiver是这个用户的、晚于等于这个时间的信息
	 * @param account
	 * @param time
	 * @return List<InformMessage>
	 * @deprecated
	 */

	public List<model.InformMessage> getInformMsg(String account, long time) {
		// TODO Auto-generated method stub
		LoginUser u = query.getLoginMessage(account);
		if(u == null)
			return null;
		
		long id = u.getId();
		
		String sql  = "select * from informMessage where  receiver = " + id + " and time >=" +time;
		 try {
			List<InformMessage> msgs = messageManage.executeQuery(sql, new InformMessage());
			List<model.InformMessage> mmsgs  =  new ArrayList<model.InformMessage>();
			User user = new User();
			user.setId(msgs.get(0).getSender());
			ArrayList<User>users = userManage.query(user);
		    user = users.get(0);
		    String iconurl = user.getIconurl();
		    String title = user.getNickName();
		    
		    for(InformMessage m:msgs){
		    	model.InformMessage i = new model.InformMessage(m);
		    	i.setIconurl(iconurl);
		    	i.setTitle(title);
		    	//暂时有问题
		    	
		    	i.setReceiver(Integer.valueOf(account));
		    	mmsgs.add(i);
		    }
			return mmsgs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		 
		return null;
	}

	public long sendMessage(model.InformMessage inform) {
		// TODO Auto-generated method stub
		InformMessage message = new InformMessage(inform);
		
	   return messageManage.add(message);
		
	}
	
	
	
	
}
