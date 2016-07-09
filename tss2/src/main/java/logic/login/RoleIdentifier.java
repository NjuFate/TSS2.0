package logic.login;

import dataservice.LoginService;

public class RoleIdentifier {
	private LoginService loginService;
	
	public RoleIdentifier(LoginService loginService){
		this.loginService = loginService;
	}
	
	public RoleIdentifier(){
		
	}
	
	public boolean legality(String account,String psw){
		boolean legality = false;
		legality = loginService.testLegality(account, psw);
		return legality;
	}
}
