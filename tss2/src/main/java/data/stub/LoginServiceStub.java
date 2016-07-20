package data.stub;

import data.service.LoginService;
import general.Role;
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
		if(account.equals("www@123.com")){
			User u = new User();
			u.setAccount("www@123.com");
			u.setEducational_ID("141250134");
			u.setEducational_Psw("248502");
			u.setEmail("1194672376@qq.com");
			u.setGrade("14");
			u.setMajor("SE");
			u.setNickName("DAD");
			u.setTel("17768102809");
			u.setRole(Role.TEACHER);
			return u;
		}
		else{
			User u = new User();
			u.setAccount("www@qq.com");
			u.setEducational_ID("141250134");
			u.setEducational_Psw("248502");
			u.setEmail("1194672376@qq.com");
			u.setGrade("14");
			u.setMajor("SE");
			u.setNickName("DAD");
			u.setTel("17768102809");
		    u.setRole(Role.STUDENT);
		    return u;
		}
	}

	public Role getRoleByAccount(String account) {
		// TODO Auto-generated method stub
		if(account.equals("www@123.com")){
			return Role.TEACHER;
		}else{
			return Role.STUDENT;
		}
		
	}

}
