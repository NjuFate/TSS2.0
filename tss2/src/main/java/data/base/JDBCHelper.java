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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class JDBCHelper {  
	public static String url = "jdbc:mysql://localhost:3306/tss2.0?characterEncoding=UTF-8&useOldAliasMetadataBehavior=true";  
	public static final String driver = "com.mysql.jdbc.Driver";  
	public static final String user = "root";  
	public static final String password = "xuan";  
	public static final String characterEncoding = "utf-8";



	/**
	 * 取得数据库链接
	 * @return Connection
	 */
	public Connection getConnection(){
		Connection conn =null;
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 释放数据库链接
	 */
	public void releaseConnection(ResultSet rs,Statement stat,Connection conn){

		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (stat != null) {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}	



}  
