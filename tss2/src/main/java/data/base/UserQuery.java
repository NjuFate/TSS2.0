package data.base;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import data.exception.NoAccountException;
import data.manage.UserManage;
import model.LoginUser;

public class UserQuery {

	
   private UserManage userManage;	


	public UserQuery(UserManage userManage) {
		// TODO Auto-generated constructor stub
		this.userManage = userManage; 
 	}

	/**
	 * 获得用户必要的帐号信息
	 * @param account 帐号
	 * @return LoginUser
	 */


	public LoginUser getLoginMessage(String account){
		String sql = "select * from user where account = '" +account +"' or haccount = '" +account +"'";

		try {
			List<LoginUser> users = userManage.executeQuery(sql, new LoginUser());

			if(users == null || users.isEmpty())
				throw new NoAccountException();

			return users.get(0);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoAccountException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return null;
	}
		
		
     
     

	


}
