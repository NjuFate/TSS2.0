package ViewController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/pages")
public class FrontContorller {
	@RequestMapping("/test")
    public ModelAndView index(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("message", "Hello World!");
		modelAndView.setViewName("index");
        return modelAndView;
    }
	@RequestMapping("/hello2")
    public String index3(){	
        return "login";
    }
	
	@RequestMapping(value="/index", method = {RequestMethod.GET})
    public ModelAndView index2(){
        
        ModelAndView modelAndView = new ModelAndView();  
        modelAndView.addObject("message", "Hello World!");  
        modelAndView.setViewName("index");  
        return modelAndView;
    }
}
