package ViewController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import logic.login.RoleIdentifier;

@Controller
@RequestMapping("/pages")
public class LoginServlet {
	private RoleIdentifier roleIdentifier = new RoleIdentifier();
	public void setRoleIdentifier(RoleIdentifier roleIdentifier){
		this.roleIdentifier = roleIdentifier;
	}
	
	@RequestMapping(value = "/upload", method = { RequestMethod.GET })
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
			
		}
		
		
		return null;
	}
}
