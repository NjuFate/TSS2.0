package dataservice;



/**
 * @author: xuan
 * @date: 2016/07/10
 * 
 * @mender: none
 * @date: none
 * 
 * @type: interface
 * @description: 通用的增删改查接口
 */

public interface ManageService {
	
	
	
	
	/**
	 * 添加
	 * @return true : 成功， false 失败
	 */
	public boolean add(Object object);
	/**
	 * 删除
	 * @return true : 成功， false 失败
	 */
	
	public boolean delete(Object object);
	/**
	 * 更新

	 * @return true : 成功， false 失败
	 */
	public boolean update(Object object);
	/**
	 * 查看
	 * @return true : 成功， false 失败
	 */
	public boolean query(Object object);
	
}
