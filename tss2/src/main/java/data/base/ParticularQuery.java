package data.base;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import data.exception.NoAccountException;

public class ParticularQuery {

	private static final String[] tableNames = {"user", "mailbox","phonenumber"};
	private static final String[] idNames = {"id", "uid", "uid"};
	private static final String[] accounts = {"account", "mailbox", "phonenumber"};


	JDBCHelper helper;

	public ParticularQuery(JDBCHelper helper) {
		// TODO Auto-generated constructor stub
		this.helper = helper;
	}

	/**
	 * 获得用户id
	 * @param account 帐号
	 * @return Connection
	 */


	public int getUserIdByAccount(String account){
		// TODO Auto-generated method stub
		int id = 0;

		
		for(int i=0; i<tableNames.length;i++){
			id = getUserId(account,idNames[i], tableNames[i], accounts[i]);
			if(id != 0)
				return id;
		}
	
		return id;
	}
	 
	
     private int getUserId(String account, String idName, String tableName, String accountName){
    	 String sql = "select " + idName +" from "+ tableName + " where " + accountName + " = " + account;
    	 System.out.println(sql);

 		PreparedStatement preparedStatement = null;
 		Connection connection = helper.getConnection();
 		ResultSet resultSet = null;
 		int id = 0;

 		try {
 			preparedStatement = connection.prepareStatement(sql);
 			resultSet = preparedStatement.executeQuery();
 			if(resultSet.next())
 				id = resultSet.getInt(1);
 			else
 				throw new NullPointerException();


 		} catch (Exception e) {
 			// TODO: handle exception
 			return 0;
 		}finally {
 			helper.releaseConnection(resultSet, preparedStatement, connection);

 		}
 		return id;
     }
     
     

	


}
