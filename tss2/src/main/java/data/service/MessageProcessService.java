package data.service;

import java.util.List;

import model.Message;

public interface MessageProcessService {

	/**
	 * 保存来自其他端口消息
	 * @param message
	 * 
	 * @return true or false
	 */
	
	public boolean saveMessage(Message message);
	
	
	/**
	 * 
	 * 根据帐号进行消息查找
	 * @param account
	 * @return
	 */
	
	public List<Message> searchByAccount(String account);
}
