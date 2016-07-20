package data.service;

import data.exception.AddAccountException;
import data.exception.AddMailBoxException;
import data.exception.AddPhoneNumberException;
import data.exception.AddUserRoleException;
import data.exception.NoAccountException;

/**
 * @author: xuan
 * @date: 2016/07/12
 * 
 * @mender: none
 * @date: none
 * 
 * @type: class
 * @description: 注册需要的数据层接口
 */



public interface CreateAccountService {
	
	
	
	/**
	 * 添加帐号
	 * @param user model
	 * @return true 添加成功， false 添加失败
	 * @throws AddAccountException 
	 * @throws AddUserRoleException 
	 */
	
	public boolean addAccount(model.User user) throws AddAccountException, AddUserRoleException;
	

	
	/**
	 * 添加绑定邮箱号
	 * @param account 账号
	 * @param mailBox 绑定邮箱号码
	 * @return true 成功， false 失败
	 * @throws NoAccountException 
	 * @throws AddMailBoxException 
	 */
	public boolean addMailBox(String account, String mailBox) throws NoAccountException, AddMailBoxException;

}
