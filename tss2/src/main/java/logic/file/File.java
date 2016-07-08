package logic.file;

import java.util.List;

public class File {
	private File parent;
	private List<File> children;
	private String path;
	private String privilege;
	
	/**
	 * 
	 */
	public File(File parent,String path,String privilege){
		this.parent = parent;
		this.path = path;
		this.privilege = privilege;
	}
	
	/**
	 * 当有子文件指定当前文件为父文件时，为当前文件添加子文件
	 * @param child
	 */
	public void addChild(File child){
		
	}

	public File getParent() {
		return parent;
	}

	public void setParent(File parent) {
		this.parent = parent;
	}

	public List<File> getChildren() {
		return children;
	}

	public void setChildren(List<File> children) {
		this.children = children;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getPrivilege() {
		return privilege;
	}

	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}
	
	
	

}
