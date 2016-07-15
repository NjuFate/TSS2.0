package data.service;

import general.Role;
import model.User;

public interface LoginService {
	/**
	 * 测试账号密码是否对应
	 * @param account 账号
	 * @param psw 密码
	 * @return true 正确， false 错误
	 */
	public boolean testLegality(String account,String psw);
	
	/**
	 * 根据账号返回对应角色model
	 * @param account
	 * @return
	 */
	public User roleIdentifier(String account);
	
	/**
	 * 根据账号返回 role 角色分类不需要多余信息
	 * @param account
	 * @return
	 */
	public Role getRoleByAccount(String account);
}
