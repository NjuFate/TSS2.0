package ViewController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import general.CookieHelper;

@Controller
@RequestMapping("/pages")
public class FrontContorller {
	@RequestMapping("/login")
	public ModelAndView test() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}
	
	@RequestMapping("/index")
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index");
		return modelAndView;
	}
	
	@RequestMapping("/documents")
	public ModelAndView document() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("documents");
		return modelAndView;
	}
	
	@RequestMapping("/userprofile")
	public ModelAndView userProfile(HttpServletRequest request,HttpServletResponse response){
		ModelAndView userProfile = new ModelAndView();
		String account = CookieHelper.getCookieByName("account", request);
		if(account.equals("")||account==null){
			userProfile.setViewName("loginfalse");
			return userProfile;
		}
		userProfile.setViewName("userprofile");
		return userProfile;
	}
	
	@RequestMapping("/upanddown")
	public ModelAndView upanddown(HttpServletRequest request){
		ModelAndView upanddown = new ModelAndView();
		String role = CookieHelper.getCookieByName("role", request);
		System.out.println("role= "+ role);
		if(role.equals("teacher")){
			upanddown.setViewName("teacher_upload");
		}else{
			upanddown.setViewName("student_download");
		}
		return upanddown;
	}
	
	@RequestMapping("/info")
	public ModelAndView info(){
		ModelAndView info = new ModelAndView();
		info.setViewName("InfoCenter");
		return info;
	}
	@RequestMapping("/assigment")
	public ModelAndView assigment(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("homework_release");
		return mav;
	}
	@RequestMapping("/ppt_notification")
	public ModelAndView ppt_notification(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("ppt_notification");
		return mav;
	}
	@RequestMapping("/ppt_info")
	public ModelAndView ppt_info(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("link");
		return mav;
	}
	
}
