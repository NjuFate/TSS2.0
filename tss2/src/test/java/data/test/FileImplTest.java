package data.test;

import static org.junit.Assert.*;

import java.awt.Image;
import java.util.List;

import org.junit.Test;


import data.file.FileImpl;
import model.File;
import model.FileExtra;

public class FileImplTest {
	FileImpl impl  = new FileImpl();

	@Test
	public void testGetFileByCourse() {
         List<File>files = impl.getFileByCourse("c1094", "SE1", "2016Summer", "c1094");		
         assertEquals(2, files.size());
	}

	@Test
	public void testGetFileByCourseno() {
		List<File>files = impl.getFileByCourseno("c1094","c1094");		
        assertEquals(2, files.size());
	}

	@Test
	public void testSearchByID() {
		FileExtra file = impl.searchByID(Long.valueOf("16072000000"));		
        assertEquals("first.ppt", file.getFileName());
	}

	@Test
	public void testSearchByName() {
		List<FileExtra> file = impl.searchByName("first");		
        assertEquals(1, file.size());	
        }

	@Test
	public void testAddFileMessage() {
	}

}
