package logic.login;

import data.service.LoginService;
import data.stub.LoginServiceStub;
import general.Role;
import model.User;

public class RoleIdentifier {
	private LoginService loginService = new LoginServiceStub();
	
	
	public boolean legality(String account,String psw){
		boolean legality = false;
		legality = loginService.testLegality(account, psw);
		return legality;
	}
	
	public User identifyRole(String account){
		User user = loginService.roleIdentifier(account);
		return user;
	}
	
	/**
	 * 根据账号返回用户角色信息
	 * @param account
	 * @return
	 */
	public Role getRoleByAccount(String account){
		return loginService.getRoleByAccount(account);
	}
}
