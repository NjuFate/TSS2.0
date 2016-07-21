package data.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.context.annotation.EnableLoadTimeWeaving;

import data.file.AndroidFile;
import model.AndroidProject;
import model.File;

public class AndroidFileTest {
	AndroidFile file = new AndroidFile();

	@Test
	public void testGetCurrentSemesterCourse() {
		List<AndroidProject>projects = file.getCurrentSemesterCourse();
		assertEquals(1, projects.size());
	}

	@Test
	public void testGetCurrentSemesterFile() {
		List<File>files = file.getCurrentSemesterFile();
		assertEquals(3, files.size());
 	}

}