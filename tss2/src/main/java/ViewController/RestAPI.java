package ViewController;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import data.file.FileImpl;
import data.service.FileService;
import model.FileExtra;


@Controller
@RequestMapping("/api")
public class RestAPI {
	FileService fileService = new FileImpl();
	@RequestMapping("/ppt")
	public @ResponseBody FileExtra ppt(String id) throws Exception {
		long longid = Long.valueOf(id);
		//account 合法性检验：		
		return fileService.searchByID(longid);
	}
	
	@RequestMapping("/ppt/search")
	public @ResponseBody List<FileExtra> pptSearch(String key) throws Exception {
		//long longid = Long.valueOf(id);
		//account 合法性检验：		
		return fileService.searchByName(key);
	}
}
