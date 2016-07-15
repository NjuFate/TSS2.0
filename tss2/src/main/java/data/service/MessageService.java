package data.service;

import java.util.List;


public interface MessageService {
	
	
	/**
	 * 提供一个用户的id和毫秒时间，传回所有receiver是这个用户的、晚于等于这个时间的信息
	 * @param account
	 * @param time
	 * @return List<InformMessage>
	 */
	public List<model.InformMessage> getInformMsg(String account,long time);

	/**
	 * 发送一个信息，你返回是否发送成功
	 * @param inform
	 * @return Long
	 */
	public long sendMessage(model.InformMessage inform);


}
