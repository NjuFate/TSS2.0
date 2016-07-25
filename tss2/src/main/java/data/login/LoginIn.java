package data.login;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import data.manage.UserManage;
import data.manage.UserRoleManage;
import data.service.LoginService;
import general.Role;
import model.LoginUser;
import model.User;
import po.UserRole;
import data.base.UserQuery;
import data.exception.NoAccountException;

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

	UserManage userManage;
	UserRoleManage userRoleManage;
	UserQuery userQuery;


	public LoginIn() {
		// TODO Auto-generated constructor stub
		userManage = new UserManage();
		userRoleManage = new UserRoleManage();
		userQuery = new UserQuery(userManage);
	}



	public boolean testLegality(String account, String psw) {
		// TODO Auto-generated method stub


		LoginUser user;

		if((user = userQuery.getLoginMessage(account)) != null)
			if(user.getPsw().equals(psw))
				return true;

		return false;
	}

	public User roleIdentifier(String account) {
		// TODO Auto-generated method stub

		String sql = "select * from user where account = '" +account +"'or haccount = '" +account +"'";

		try {
			List<po.User> users = userManage.executeQuery(sql, new po.User());

			if(users == null || users.isEmpty())
				throw new NoAccountException();


			User user = new User(users.get(0));
			user.setRole(getRoleById(users.get(0).getId()));

			return user;


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoAccountException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return null;
	}



	public Role getRoleByAccount(String account) {
		// TODO Auto-generated method stub
		LoginUser user;
		if((user = userQuery.getLoginMessage(account)) != null){
			long id = user.getId();
			return getRoleById(id);
		}
		return null;
	}



	


	private Role getRoleById(long id){
		UserRole userRole = new UserRole();
		userRole.setUid(id);
		ArrayList<UserRole> userRoles = userRoleManage.query(userRole);

		if(userRoles == null&& userRoles.isEmpty())
			return null;

		return Role.valueOf(userRoles.get(0).getRole());
	}

	
	





}
