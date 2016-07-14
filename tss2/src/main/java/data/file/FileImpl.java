package data.file;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;


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
		file.setCoursename(coursename);
		file.setSemester(semester);
		file.setFather(father);
		
		List<po.File>files = fileManage.query(file);
		if(files == null || files.size() == 0)
			return null;
		
		List<File> rFiles = new ArrayList<File>();
		
		for(po.File f : files){
			rFiles.add(new File(f));
		}
		return rFiles;

	}

	
	
	
}
