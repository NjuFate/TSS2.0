package logic.login;

import javax.servlet.http.HttpServletResponse;

import data.login.LoginIn;
import data.service.LoginService;
import general.CookieHelper;
import model.User;

public class Association {
	LoginService loginService = new LoginIn();
	
	public boolean loginAssociation(String account,HttpServletResponse response){
		User user = loginService.roleIdentifier(account);
		String haccount =user.gethAccount();
		String psw = user.getPsw();
		
		CookieHelper.addNewCookie("haccount", haccount, response);
		CookieHelper.addNewCookie("psw", psw, response);
		
		return false;
	}
}
