package data.service;

import data.exception.NoAccountException;
import general.Role;

public interface RoleTransitionService {
	
	
	/**
	 * 改变帐号的角色
	 * @param account 账号
	 * @param role 变化的角色
	 * @return true 成功， false 失败
	 * @throws NoAccountException 
	 */
	
	public boolean roleChange(String account, Role role) throws NoAccountException;

}
