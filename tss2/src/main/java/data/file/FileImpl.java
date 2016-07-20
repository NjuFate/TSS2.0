package data.file;


import java.util.ArrayList;
import java.util.List;
import data.manage.FileManage;
import data.service.FileService;
import model.File;
import model.FileExtra;

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


	public FileExtra searchByID(long id) {
		// TODO Auto-generated method stub

		String sql = "select * from file where id=" + id;
		try {
			List<FileExtra>fileExtras = fileManage.executeQuery(sql, new FileExtra());
			if(fileExtras==null||fileExtras.isEmpty())
				throw new Exception();
			return fileExtras.get(0);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}



	public List<FileExtra> searchByName(String name) {
		// TODO Auto-generated method stub
		
		String sql = "select * from file where filename like '%"+name+"%'";
		try {
			List<FileExtra>fileExtras = fileManage.executeQuery(sql, new FileExtra());
			return fileExtras;
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}



	public boolean addFileMessage(File file) {
		// TODO Auto-generated method stub
		long id = fileManage.add(new po.File(file));
		if(id == 0)
			return false;
		
		return true;
	}	
	
	

	
}
