package ViewController;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import data.message.MessageImpl;
import data.service.MessageService;
import logic.schedule.ScheduleProvider;
import po.ScheduleItem;

@Controller
@RequestMapping("android.do")
public class AndroidAPI {
	private ScheduleProvider scheduleProvider = new ScheduleProvider();
	private MessageService messageService = new MessageImpl();
	
	/**
	 * for DaYe Tang
	 * @param userName
	 * @param password
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params="method=andoird_projectTable",method=RequestMethod.GET)
	public @ResponseBody List<ScheduleItem> projectTable(String userName,String password) throws Exception {
		//account 合法性检验：
		List<ScheduleItem> projectList = scheduleProvider.getAllCourses(userName, password);
		return projectList;
	}
	
	/**
	 * for Deng YI Peng
	 * @param account
	 * @param time
	 * @return
	 */
	@RequestMapping(params="method=android_messageGet",method=RequestMethod.GET)
	public @ResponseBody List<model.InformMessage> getMessage(String account, long time){
		return messageService.getInformMsg(account, time);
	}
	//public long sendMessage(model.InformMessage inform);
	
	/**
	 * to do send Message
	 * @return
	 */
	@RequestMapping(params="method=android_messageSend",method=RequestMethod.GET)
	public @ResponseBody long sendMessage(){
		return 0;
	}
}
