package ViewController;

import java.io.File;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
@Controller
@RequestMapping("/pages")
public class UploadServlet {
	@RequestMapping(value = "/add",method = {RequestMethod.POST})  
	//MultipartFile来自：org.springframework.web.multipart.MultipartFile;
	public ModelAndView addGoods( HttpServletRequest request, HttpSession session,@RequestParam("file") MultipartFile file) {  
		    ModelAndView mav = new ModelAndView();  
		    if (!file.isEmpty()) {  
		  
		        String path = request.getContextPath() + "/jsp/";  
		        String fileName = file.getOriginalFilename();  
		  
		        try {  
		            String tomcatPath = "E:/GitProject"; //得到保存的路径  
		            FileCopyUtils.copy(file.getBytes(), new File(tomcatPath +"/" +  fileName));//FileCopyUtils来自org.springframework.util.FileCopyUtils  
		  
		        } catch (IOException e) {  
		            // TODO Auto-generated catch block  
		            e.printStackTrace();  
		        }  
		  
		               }  
		  
		    mav.setViewName("upload2");  
		    return mav;  
		  
		}  
	
	@RequestMapping(value = "/upload",method = {RequestMethod.GET}) 
	public ModelAndView show(){
		 ModelAndView mav = new ModelAndView();
		 mav.setViewName("upload2");
		 return mav;
	}

}