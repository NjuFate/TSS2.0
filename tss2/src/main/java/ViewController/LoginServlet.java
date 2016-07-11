package ViewController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import general.Role;
import logic.login.RoleIdentifier;
import model.Teacher;
import model.User;

/**
 * 控制界面跳转 
 * @author WangHuan
 *
 */
@Controller
@RequestMapping("/pages")
public class LoginServlet {
	private RoleIdentifier roleIdentifier = new RoleIdentifier();
	
	public void setRoleIdentifier(RoleIdentifier roleIdentifier){
		this.roleIdentifier = roleIdentifier;
	}
	
	@RequestMapping(value = "/login", method = { RequestMethod.GET })
	public ModelAndView login(String account,String psw) {
		boolean legality = roleIdentifier.legality(account, psw);
		if(!legality){
			//密码账号不对应
			ModelAndView mav = new ModelAndView();
			mav.setViewName("loginfalse");
			return mav;
		}else{
			//密码账号对应
			//判断角色
			User user = roleIdentifier.identifyRole(account);
			if(user.getRole() == Role.TEACHER){
				//判断为老师
				Teacher teacher = (Teacher)user;
				
				
				//根据老师信息返回页面
				
			}
			if(user.getRole() == Role.STUDENT){
				//判断为学生
			}
			
		}
		
		
		return null;
	}
}
