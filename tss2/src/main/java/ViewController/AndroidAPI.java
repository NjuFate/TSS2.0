package ViewController;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import logic.schedule.ScheduleProvider;
import po.ScheduleItem;

@Controller
@RequestMapping("android.do")
public class AndroidAPI {
	private ScheduleProvider scheduleProvider = new ScheduleProvider();
	
	@RequestMapping(params="method=andoird_projectTable",method=RequestMethod.GET)
	public @ResponseBody List<ScheduleItem> projectTable(String userName,String password) throws Exception {
		//account 合法性检验：
		List<ScheduleItem> projectList = scheduleProvider.getAllCourses(userName, password);
		return projectList;
	}
}
