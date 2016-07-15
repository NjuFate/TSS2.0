package ViewController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import general.CookieHelper;
import general.Role;
import logic.login.RoleIdentifier;
import model.Student;
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
	
	@RequestMapping(value = "/home", method = { RequestMethod.GET })
	public ModelAndView login(String account,String psw,HttpServletRequest request,HttpServletResponse response) {
		System.out.println("String = "+account+" psw = "+psw);
		boolean legality = roleIdentifier.legality(account, psw);
		if(!legality){
			//密码账号不对应
			ModelAndView mav = new ModelAndView();
			mav.setViewName("loginfalse");
			return mav;
		}else{
			//密码账号对应
			ModelAndView mav = new ModelAndView();
			mav.setViewName("index");
			CookieHelper.addNewCookie("account", account, response);
			String role = "normal";
			//判断并记录角色
			if(roleIdentifier.getRoleByAccount(account) == Role.TEACHER){
				role = "teacher";
			}
			CookieHelper.addNewCookie("role",role, response);
			return mav;
		}
		
		
		
	}
}
