package datalayer;

import java.sql.PreparedStatement;
import java.sql.SQLException;



public class TableCreation {
	
	JDBCHelper helper;
	public TableCreation() {
		// TODO Auto-generated constructor stub
		helper = new JDBCHelper();
	}
	
	
	
	public void createProjectTable(){
		String sql = "create table Project(pid INT(11), grade INT(11), startTime date, endTime date, pname varchar(30), primary key(pid))";
		try {
			PreparedStatement preparedStatement = helper.getConnection().prepareStatement(sql);
		
			if(preparedStatement.executeUpdate() == 0){
				System.out.println("DONE!!!");
				
			}else {
				System.out.println("FAIL!!!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	public void createFileTable(){
		String sql = "create table File(id INT(11), fileName varchar(30), path varchar(50), "
				+ "uploadUser char(20), downNum INT(11),fatherFile varchar(50), courseID char(20), primary key(id))";
		try {
			PreparedStatement preparedStatement = helper.getConnection().prepareStatement(sql);
		
			if(preparedStatement.executeUpdate() == 0){
				System.out.println("DONE!!!");
				
			}else {
				System.out.println("FAIL!!!");
			}
			preparedStatement.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
		}
		
		
		
	}
	
	public void createUserTable(){
		String sql = "create table User(uid char(20), uname varchar(30), age INT(11), primary key(uid))";
		try {
			PreparedStatement preparedStatement = helper.getConnection().prepareStatement(sql);
		
			if(preparedStatement.executeUpdate() == 0){
				System.out.println("DONE!!!");
				
			}else {
				System.out.println("FAIL!!!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	
	
	
	public static void main(String [] args){
		TableCreation creation = new TableCreation();
		creation.createFileTable();
		
	}
	
	
	
	

}
