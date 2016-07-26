package logic.file;

import java.util.ArrayList;
import java.util.List;

import data.file.FileImpl;
import data.service.FileService;
import model.File;

public class FileStorageHelper {
	FileService fileService = new FileImpl();
	
	public ArrayList<String> getStoragePath(String courseno,String father){
		ArrayList<String> pathString = new ArrayList<String>();
		pathString.add(father);
		if(father.equals(courseno)){
			return pathString;
		}
		
		List<File> fileList = fileService.getFileByCourseno(courseno, courseno);
		for(File file : fileList){
			if(file.getFileName().equals(father)){
				
			}
		}
		//pathString.add()
		return null;
		
	}
}
