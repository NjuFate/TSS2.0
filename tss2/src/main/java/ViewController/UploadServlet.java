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

import general.CookieHelper;
import general.FileHelper;
import logic.file.FileSaver;
@Controller
@RequestMapping("/pages")
public class UploadServlet {
	FileSaver fileSaver = new FileSaver();
	@RequestMapping(value = "/add",method = {RequestMethod.POST})  
	//MultipartFile来自：org.springframework.web.multipart.MultipartFile;
	public ModelAndView addGoods(   HttpServletRequest request, 
									HttpSession session,
									@RequestParam("file") MultipartFile file
									 ) {  
		   String courseno = CookieHelper.getCookieByName("courseno", request);
		   String father = CookieHelper.getCookieByName("father", request);
		   
		    ModelAndView mav = new ModelAndView();  
		    if (!file.isEmpty()) {  
		        String fileName = file.getOriginalFilename();  
	
		        try {  
		            String tomcatPath = "C:/tss2/"+courseno+"/"+father; //得到保存的路径  
		            System.out.println("dir path is ："+tomcatPath);
		            FileCopyUtils.copy(file.getBytes(), new File(tomcatPath +"/" +  fileName));//FileCopyUtils来自org.springframework.util.FileCopyUtils  
		            //save info to database
		            fileSaver.saveFileInfoToDataBase(request, tomcatPath+"/"+fileName, fileName, courseno, father, false);
		            
		        } catch (IOException e) {  
		            // TODO Auto-generated catch block  
		            e.printStackTrace();  
		        }  
		  
		               }  
		  
		    mav.setViewName("teacher_upload");  
		    return mav;  
		  
		}  
	
	@RequestMapping(value = "/upload",method = {RequestMethod.GET}) 
	public ModelAndView show(){
		 ModelAndView mav = new ModelAndView();
		 mav.setViewName("upload2");
		 return mav;
	}
	
	@RequestMapping(value="/createFolder",method={RequestMethod.POST})
	public ModelAndView createFolder(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		String courseno = CookieHelper.getCookieByName("courseno", request);
		String father = CookieHelper.getCookieByName("father", request);
		String path = "C:/tss2/"+courseno+"/"+father;
		FileHelper.createDir(path);
		mav.setViewName("teacher_upload"); 
		return mav;
	}

}
