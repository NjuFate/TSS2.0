package data.test;



import java.util.ArrayList;

import org.junit.Test;

import data.manage.FileManage;
import po.File;

/**
 * 单元测试类
 * @author Administrator
 *
 */
public class TestFileManage {
	FileManage dao = new FileManage();



	/**测试插入操作
	 * @throws Exception
	 */
	@Test
	public void testadd() throws Exception{
		File file = new File();
		
		dao.add(file);
	}

	/**
	 * 测试修改操作
	 * @throws Exception
	 */
	@Test
	public void testupdate() throws Exception{
//		File file = new File();
//		file.setId(160711003);
//		file.setCourseID("courseid");
//		file.setPath("path");
//		file.setFileName("filename");
//		file.setFatherFile("fatherfile");
//		file.setUploadUser("uploaduser");
//		file.setDownNum(22222);
//		dao.update(file);
	}

	/**测试查询操作
	 * @throws Exception
	 */
	@Test
	public void testquery() throws Exception{
		File file = new File();
		file.setId(160711000);
		ArrayList<File>files = dao.query(file);
		for(File f : files){
//			System.out.println(f.getId() + " " + f.getDownNum());
		}
	
	}

	/**
	 * 测试删除操作
	 * @throws Exception
	 */
	@Test
	public void testdel() throws Exception{
		File file =  new File();
		file.setId(160711002);
		dao.delete(file);
	}

}
