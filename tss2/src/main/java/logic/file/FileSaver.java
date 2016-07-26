package logic.file;

import javax.servlet.http.HttpServletRequest;

import data.file.FileImpl;
import data.service.FileService;
import general.CookieHelper;
import model.File;

public class FileSaver {
	FileService fileService = new FileImpl();
	
	public boolean saveFileInfoToDataBase(HttpServletRequest request,String path,String fileName,String courseno,String father,boolean isFolder){
		File file = new File();
		file.setFileName(fileName);
		file.setFather(father);
		file.setCourseno(courseno);
		file.setIsFolder(isFolder);
		//file.setSemester(semester);
		//file.setUpdateTime(updateTime);
		String account = CookieHelper.getCookieByName("account", request);
		file.setUploadBy(account);
		fileService.addFileMessage(file);
		
		return true;
	}
}
