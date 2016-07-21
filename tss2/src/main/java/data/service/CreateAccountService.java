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
	 * @return 返回环信帐号
	 * @throws AddAccountException 
	 * @throws AddUserRoleException 
	 */
	
	public String addAccount(model.User user) throws AddAccountException, AddUserRoleException;
	

}
