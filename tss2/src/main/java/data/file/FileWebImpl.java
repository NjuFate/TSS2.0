package data.file;

import java.util.Collections;
import java.util.List;

import data.manage.FileManage;
import data.service.FileWebService;
import model.FileEx;
import model.FileExtra;
import model.FileJson;

public class FileWebImpl implements FileWebService{
	FileManage fileManage;
	
	public FileWebImpl() {
		// TODO Auto-generated constructor stub
		fileManage = new FileManage();
	}

	public List<FileJson> getAllFile() {
		// TODO Auto-generated method stub

		String sql = "select * from file where isFolder = 0";
		try {
			List<FileJson>fileJsons = fileManage.executeQuery(sql, new FileJson());
			if(fileJsons ==null||fileJsons.isEmpty())
				throw new Exception();
			
		   Collections.sort(fileJsons);
		   
		   return fileJsons;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	
	
	public static void main(String[]args){
		FileWebService service = new FileWebImpl();
		List<FileJson> fileJsons = service.getAllFile();
		for(FileJson fileJson : fileJsons){
			System.out.println(fileJson.getUpdateTime());
		}
		
	}
}
