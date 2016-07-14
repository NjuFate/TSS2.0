package logic.file;

import java.util.List;

import data.service.FileService;
import data.stub.FileServiceStub;
import model.File;

public class FileProvider {
	private FileService fileService = new FileServiceStub();
	public FileProvider(){
		//实例化 fileService
	}
	public FileProvider(FileService fileService){
		this.fileService = fileService;
	}
	
	public List<File> getFileByCourse(String courseno,String coursename,String semester,String father){
		List<File> fileList = fileService.getFileByCourse(courseno, coursename, semester, father);
		return fileList;
	}
	
	public List<File> getFileByCourseno(String courseno,String father){
		List<File> fileList = fileService.getFileByCourseno(courseno, father);
		return fileList;
	}
}
