package data.stub;

import java.util.ArrayList;
import java.util.List;

import data.service.FileService;
import model.File;
import model.FileExtra;

public class FileServiceStub implements FileService {

	public List<File> getFileByCourse(String courseno, String coursename, String semester,String father) {
		// TODO Auto-generated method stub
		File file1 = new File();
		File file2 = new File();
		File file3 = new File();
		
		file1.setIsFolder(true);
		file1.setFather(courseno);
        file1.setFileName("base1");
        file1.setPath("kkk");
        file1.setUpdateTime("2016/07/12");
        file1.setUploadBy("唐大爷");
        
        file2.setIsFolder(false);
        file2.setFather(courseno);
        file2.setFileName("test.doc");
        file2.setPath("E:/GitProject/hello.txt");
        file2.setUpdateTime("2016/07/12");
        file2.setUploadBy("唐大爷");
        
        ArrayList<File> fileList = new ArrayList<File>();
        fileList.add(file1);
        fileList.add(file2);
        
		return fileList;
	}

	public List<File> getFileByCourseno(String courseno, String father) {
		// TODO Auto-generated method stub
		File file1 = new File();
		File file2 = new File();
		File file3 = new File();
		
		file1.setIsFolder(true);
		file1.setFather(courseno);
        file1.setFileName("base1");
        file1.setPath("E:/GitProject/hello.txt");
        file1.setUpdateTime("2016/07/12");
        file1.setUploadBy("唐大爷");
        
        file2.setIsFolder(false);
        file2.setFather(courseno);
        file2.setFileName("test.doc");
        file2.setPath("E:/GitProject/hello.txt");
        file2.setUpdateTime("2016/07/12");
        file2.setUploadBy("唐大爷");
        
        ArrayList<File> fileList = new ArrayList<File>();
        fileList.add(file1);
        fileList.add(file2);
        
		return fileList;
	}

	public List<File> getAllFile() {
		// TODO Auto-generated method stub
		return null;
	}

	public FileExtra searchByID(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<FileExtra> searchByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean addFileMessage(File file) {
		// TODO Auto-generated method stub
		return false;
	}

}
