package dataservice;

import model.User;

public interface LoginService {
	/**
	 * 测试账号密码是否对应
	 * @param account 账号
	 * @param psw 密码
	 * @return true : 正确， false 错误
	 */
	public boolean testLegality(String account,String psw);
	
	/**
	 * 根据账号返回对应角色model
	 * @param account
	 * @return
	 */
	public User roleIdentifier(String account);
}
