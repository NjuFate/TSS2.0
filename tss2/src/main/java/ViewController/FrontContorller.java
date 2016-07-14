package ViewController;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/pages")
public class FrontContorller {
	@RequestMapping("/login")
	public ModelAndView test(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		String value = "哈哈哈";
		
		Cookie cookie;
		try {
			cookie = new Cookie("test", URLEncoder.encode(value, "UTF-8"));
			cookie.setPath("/");	
			response.addCookie(cookie);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return modelAndView;
	}

	@RequestMapping("/index")
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index");
		return modelAndView;
	}
	
	@RequestMapping("/unlogin")
	public ModelAndView unlogin() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("unlogin");
		return modelAndView;
	}
	
	@RequestMapping("/documents")
	public ModelAndView document() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("documents");
		return modelAndView;
	}

	
}
