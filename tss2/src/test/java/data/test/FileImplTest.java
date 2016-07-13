package data.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import data.file.FileImpl;


public class FileImplTest {
	
	FileImpl impl = new FileImpl();
	
//	@Test
//	public void add(){
//		FileManage fileManage = new FileManage();
//		File file = new File();
//		String[]filename = {"0","1","2","3","4","5"};
//		file.setIsFolder(0);
//		
//		file.setFather("software");
//		file.setUpdateTime("2016-07-12");
//		file.setUploadBy("hdx");
//		file.setPath("apple/software");
//		file.setCourseno("1101");
//		file.setCoursename("software");
//		file.setSemester("2016年第二学期");
//		for(int i =0;i<filename.length;i++){
//			file.setFileName(filename[i]);
//			fileManage.add(file);
//		}
//		file.setFather("math");
//		file.setPath("apple/math");
//		file.setCourseno("1102");
//
//
//		for(int i =0;i<filename.length;i++){
//			file.setFileName(filename[i]);
//			fileManage.add(file);
//		}
//	}
	
	
	
	
	
	
	
	@Test
	public void testGetFileByCourse() {
		List<model.File>list = impl.getFileByCourse("1102", "software", "semester1", "math");
		assertEquals(6, list.size());
		assertEquals("hdx", list.get(0).getUploadBy());
	}

}
