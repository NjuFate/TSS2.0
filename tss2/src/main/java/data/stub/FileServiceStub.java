package data.stub;

import java.util.ArrayList;
import java.util.List;

import data.service.FileService;
import model.File;

public class FileServiceStub implements FileService {

	public List<File> getFileByCourse(String courseno, String coursename, String semester,String father) {
		// TODO Auto-generated method stub
		File file1 = new File();
		File file2 = new File();
		File file3 = new File();
		
		file1.setFolder(true);
		file1.setFather(courseno);
        file1.setFileName("base1");
        file1.setPath("kkk");
        file1.setUpdateTime("2016/07/12");
        file1.setUploadBy("唐大爷");
        
        file2.setFolder(false);
        file2.setFather(courseno);
        file2.setFileName("test.doc");
        file2.setPath("kkk");
        file2.setUpdateTime("2016/07/12");
        file2.setUploadBy("唐大爷");
        
        ArrayList<File> fileList = new ArrayList<File>();
        fileList.add(file1);
        fileList.add(file2);
        
		return fileList;
	}

}