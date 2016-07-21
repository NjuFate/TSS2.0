package data.role;

import data.base.UserQuery;
import data.exception.NoAccountException;
import data.manage.UserManage;
import data.manage.UserRoleManage;
import data.service.RoleTransitionService;
import general.Role;
import model.LoginUser;
import po.UserRole;

public class RoleTransition implements RoleTransitionService{
	
	UserQuery query;
	UserRoleManage userRoleManage;
	
	
	public RoleTransition() {
		// TODO Auto-generated constructor stub
		query = new UserQuery(new UserManage());
		userRoleManage = new UserRoleManage();
	}

	public boolean roleChange(String account, Role role) throws NoAccountException {
		// TODO Auto-generated method stub
		long id = 0;
		LoginUser u = query.getLoginMessage(account);
		if(u == null)
			throw new NoAccountException();
		id = u.getId();
		
		UserRole u1 = new UserRole();
		u1.setUid(id);
		UserRole u2 = new UserRole();
		u2.setRole(role.toString());
		
		if(userRoleManage.update(u1, u2)==0)
		return false;
		
		
		return true;
	}


}
