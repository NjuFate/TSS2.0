package data.role;

import data.base.JDBCHelper;
import data.base.ParticularQuery;
import data.exception.NoAccountException;
import data.manage.UserRoleManage;
import data.service.RoleTransitionService;
import general.Role;
import po.UserRole;

public class RoleTransition implements RoleTransitionService{
	
	ParticularQuery query;
	UserRoleManage userRoleManage;
	
	
	public RoleTransition() {
		// TODO Auto-generated constructor stub
		query = new ParticularQuery(new JDBCHelper());
		userRoleManage = new UserRoleManage();
	}

	public boolean roleChange(String account, Role role) throws NoAccountException {
		// TODO Auto-generated method stub
		int id = query.getUserIdByAccount(account);
		if(id == 0)
			throw new NoAccountException();
		
		UserRole u1 = new UserRole();
		u1.setUid(id);
		UserRole u2 = new UserRole();
		u2.setRole(role.toString());
		
		if(userRoleManage.update(u1, u2)==0)
		return false;
		
		
		return true;
	}


}
