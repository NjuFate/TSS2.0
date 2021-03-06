package ViewController;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import data.file.AndroidFile;
import data.file.FileImpl;
import data.message.InformMessageImpl;
import data.service.AndroidDownLoadService;
import data.service.FileService;
import data.service.MessageService;
import logic.schedule.ScheduleProvider;
import model.AndroidProject;
import model.File;
import po.ScheduleItem;

@Controller
@RequestMapping("android.do")
public class AndroidAPI {
	private ScheduleProvider scheduleProvider = new ScheduleProvider();
	private MessageService messageService = new InformMessageImpl();
	private FileService fileService = new FileImpl();
	private AndroidDownLoadService androidDownloadService = new AndroidFile();
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
		System.out.println(account);
		System.out.println(time);
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
	
	
	
	/**
	 * for xia zhiwei
	 */
//	@RequestMapping(params="method=android_file",method=RequestMethod.GET)
//	public @ResponseBody List<File> getAllFile(){
//		return fileService.getAllFile();
//	}
	/**
	 * 
	 * 
	 */
	@RequestMapping(params="method=android_file",method=RequestMethod.GET)
	public @ResponseBody List<File> getCurrentSemesterFile(){
		return androidDownloadService.getCurrentSemesterFile();
	}
	
	@RequestMapping(params="method=android_source",method=RequestMethod.GET)
	public @ResponseBody List<AndroidProject> getCurrentSemesterCourse(){
		return androidDownloadService.getCurrentSemesterCourse();
	}
	
}
