package datalayer;

/*
 * @author: xuan
 * @date: 2016/07/08
 * 
 * @mender: none
 * @date: none
 * 
 * @type: class
 * @description: JDBC 连接数据的helper
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class JDBCHelper {  
	public static String url = "jdbc:mysql://localhost:3306/tss2.0?characterEncoding=UTF-8";  
	public static final String driver = "com.mysql.jdbc.Driver";  
	public static final String user = "root";  
	public static final String password = "xuan";  
	public static final String characterEncoding = "utf-8";
	private static JDBCHelper helper ;
	private Connection conn = null;


	private JDBCHelper() {  
		try {  

			Class.forName(driver);

			conn = DriverManager.getConnection(url, user, password);

			System.out.println("数据库连接成功！！！");

		} catch (Exception e) {  
			e.printStackTrace();  
		}  
	}  

	public static JDBCHelper create(){
		if(helper == null){
			synchronized(JDBCHelper.class){

				if(helper == null)
					helper = new JDBCHelper();
			}
		}

		return helper;
	}


	public void close() {  
		try {  
			conn.close();  
			System.out.println("数据库连接关闭！！！");
		} catch (SQLException e) {  
			e.printStackTrace();  
		}  
	}


	public Connection getConnection(){
		return conn;
	}


}  
