package data.base;

/**
 * @author: xuan
 * @date: 2016/07/08
 * 
 * @mender: none
 * @date: none
 * 
 * @type: class
 * @description: JDBC 连接数据的helper
 */



import data.service.JDBCService;



public class JDBCHelper extends JDBCService{  
	
	public JDBCHelper() {
		// TODO Auto-generated constructor stub
		url = "jdbc:mysql://123.206.70.29:3306/tss2.0?characterEncoding=UTF-8";
	}	

}  
