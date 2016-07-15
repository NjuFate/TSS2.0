package data.file;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.methods.multipart.StringPart;

import com.sun.javafx.runtime.VersionInfo;

import data.manage.FileManage;
import data.service.FileService;
import model.File;

public class FileImpl implements FileService{
	FileManage fileManage;
	
	
	public FileImpl() {
		// TODO Auto-generated constructor stub
		fileManage = new FileManage();
	}
	
	

	public List<File> getFileByCourse(String courseno, String coursename, String semester, String father) {
		// TODO Auto-generated method stub
		po.File file = new po.File();
		file.setCourseno(courseno);
		
		file.setFather(father);
		
		if(coursename!=null)
		file.setCoursename(coursename);
		if(semester!=null)
		file.setSemester(semester);
		
		List<po.File>files = fileManage.query(file);
		if(files == null || files.size() == 0)
			return null;
		
		List<File> rFiles = new ArrayList<File>();
		
		for(po.File f : files){
			rFiles.add(new File(f));
		}
		return rFiles;

	}



	public List<File> getFileByCourseno(String courseno, String father) {
		// TODO Auto-generated method stub
         return getFileByCourse(courseno, null, null, father);
	}



	public List<File> getAllFile() {
		// TODO Auto-generated method stub
		String sql = "select * from file";
		try {
			List<po.File>files = fileManage.executeQuery(sql, new po.File());
			List<File> rFiles = new ArrayList<File>();
			
			for(po.File f : files){
				rFiles.add(new File(f));
			}
			return rFiles;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}	
	
}
