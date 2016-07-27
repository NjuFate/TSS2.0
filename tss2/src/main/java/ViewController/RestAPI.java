package ViewController;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import data.file.FileImpl;
import data.service.FileService;
import logic.message.MessageReciver;
import logic.message.MessageSender;
import logic.project.AssociateSource;
import model.FileExtra;
import model.json.Info;
import model.json.Message;


@Controller
@RequestMapping("/api")
public class RestAPI {
	FileService fileService = new FileImpl();
	MessageSender messageSender = new MessageSender(); 
	AssociateSource associateSource = new AssociateSource();
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
	
	@RequestMapping(value="/notification",method={RequestMethod.POST})
	public void transferMessage(HttpServletRequest request){
		InputStream is = null;
		String contentStr = "";
		try{
			is = request.getInputStream();
			contentStr = IOUtils.toString(is,"utf-8");
		}catch (IOException e){
			e.printStackTrace();
		}
		;
		
		Info info = MessageReciver.getInfoFromStr(contentStr);
		Message message  = new Message();
		message.setMsg(info.getMessage());
		message.setType("txt");
		String[] account = {"xiaohong","160721LUU"};
		messageSender.sendMessage(message, account, info.getSource());
		//System.out.println(info.getMessage());
		
	}
	@RequestMapping(value="/relation",method={RequestMethod.GET})
	public @ResponseBody String getRelatedID(String pptId){
		return associateSource.getAssociatedId(pptId);
	}
	@RequestMapping(value="/entry",method={RequestMethod.GET})
	public @ResponseBody String getWikiInfo(String wikiId){
		return associateSource.getWikiInfo(wikiId);
	}
	
}
