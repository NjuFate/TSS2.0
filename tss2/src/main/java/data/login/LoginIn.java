package data.login;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.omg.PortableServer.ServantActivator;
import org.springframework.context.support.StaticApplicationContext;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import data.manage.UserManage;
import data.manage.UserRoleManage;
import data.service.LoginService;
import general.Role;
import model.User;
import po.UserRole;
import data.base.JDBCHelper;
import data.base.ParticularQuery;

/**
 * @author: xuan
 * @date: 2016/07/11
 * 
 * @mender: xuan
 * @date: 2016/07/12
 * 
 * @type: class
 * @description: login数据层重构类
 */




public class LoginIn implements LoginService{
	
	JDBCHelper helper;
	UserManage userManage;
	UserRoleManage userRoleManage;
	ParticularQuery query;
	
	
	public LoginIn() {
		// TODO Auto-generated constructor stub
		helper = new JDBCHelper();
		userManage = new UserManage();
		userRoleManage = new UserRoleManage();
		query = new ParticularQuery(helper);
	}

	
	
	public boolean testLegality(String account, String psw) {
		// TODO Auto-generated method stub
		
		
		String sql = "select psw from user where id = ? ";
		
	    int id = query.getUserIdByAccount(account);
	    if(id == 0)
	    	return false;
		
		PreparedStatement preparedStatement = null;
		Connection connection = helper.getConnection();
		ResultSet resultSet = null;
		String password;;
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);

			resultSet = preparedStatement.executeQuery();
			if(resultSet.next())
				password = resultSet.getString(1);
			else
				throw new NullPointerException(account + " account not exist!");
			
			if(psw.equals(password))
			return true;
				
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}finally {
			helper.releaseConnection(resultSet, preparedStatement, connection);
		}
		return false;
	}

	public User roleIdentifier(String account) {
		// TODO Auto-generated method stub
		int id = query.getUserIdByAccount(account);
		if(id == 0)
			return null;
		
		po.User user = new po.User();
		user.setId(id);
		ArrayList<po.User> users = userManage.query(user);
		UserRole userRole = new UserRole();
		userRole.setUid(id);
		ArrayList<UserRole> userRoles = userRoleManage.query(userRole);
		model.User result = new model.User(users.get(0));
		result.setRole(Role.valueOf(userRoles.get(0).getRCode()));
		return result;
	}
	
	
	
	
	

}
