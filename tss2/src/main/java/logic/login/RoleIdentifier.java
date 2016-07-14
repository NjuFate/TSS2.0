package logic.login;

import data.service.LoginService;
import data.stub.LoginServiceStub;
import model.User;

public class RoleIdentifier {
	private LoginService loginService;
	
	public RoleIdentifier(LoginService loginService){
		this.loginService = loginService;
	}
	
	public RoleIdentifier(){
		loginService = new LoginServiceStub();
	}
	
	public boolean legality(String account,String psw){
		boolean legality = false;
		legality = loginService.testLegality(account, psw);
		return legality;
	}
	
	public User identifyRole(String account){
		User user = loginService.roleIdentifier(account);
		return user;
	}
}
