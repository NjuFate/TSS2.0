package data.login;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import data.manage.UserManage;
import data.manage.UserRoleManage;
import data.service.LoginService;
import general.Role;
import model.User;
import po.UserRole;
import data.base.JDBCHelper;

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




public class LoginImpl implements LoginService{
	
	JDBCHelper helper;
	UserManage userManage;
	UserRoleManage userRoleManage;
	
	
	public LoginImpl() {
		// TODO Auto-generated constructor stub
		helper = new JDBCHelper();
		userManage = new UserManage();
		userRoleManage = new UserRoleManage();
	}

	
	
	public boolean testLegality(String account, String psw) {
		// TODO Auto-generated method stub
		String sql = "select u.psw from user u, phonenumber p, mailbox m where u.id = p.uid and u.id = m.uid and "
				+ "(account = ? or  p.phonenumber = ? or m.mailbox = ?) ";
		
		PreparedStatement preparedStatement = null;
		Connection connection = helper.getConnection();
		ResultSet resultSet = null;
		String password;;
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, account);
			preparedStatement.setString(2, account);
			preparedStatement.setString(3, account);

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
		}
		return false;
	}

	public User roleIdentifier(String account) {
		// TODO Auto-generated method stub
		String sql = "select u.id from user u, phonenumber p, mailbox m where u.id = p.uid and u.id = m.uid and "
				+ "(account = ? or  p.phonenumber = ? or m.mailbox = ?) ";
		
		PreparedStatement preparedStatement = null;
		Connection connection = helper.getConnection();
		ResultSet resultSet = null;
		int id;
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, account);
			preparedStatement.setString(2, account);
			preparedStatement.setString(3, account);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next())
				id = resultSet.getInt(1);
			else
				throw new NullPointerException();
			
				
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		
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
