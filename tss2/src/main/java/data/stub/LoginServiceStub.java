package data.stub;

import data.service.LoginService;
import model.User;

public class LoginServiceStub implements LoginService{

	public boolean testLegality(String account, String psw) {
		// TODO Auto-generated method stub
		if(account!=null && account!="" && psw.equals("psw")){
			return true;
		}else{
		return false;}
	}

	public User roleIdentifier(String account) {
		// TODO Auto-generated method stub
		return null;
	}

}
