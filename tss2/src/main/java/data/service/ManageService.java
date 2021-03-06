package data.service;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import data.base.JDBCHelper;
import data.base.StandardSQL;
import po.BaseId;



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

public abstract class ManageService <T>{
	protected int size;
	protected JDBCHelper helper;
	protected String tableName;
	protected StandardSQL standardSQL;
	
	
    public ManageService(){
    	helper = new JDBCHelper();
    	standardSQL = new StandardSQL();
    }

    




	/**
	 * 添加
	 * @param t
	 * @return 返回自增长主键的id
	 * @throws Exception
	 */
	public long add(Object t){
		String sql;
		((BaseId) t).setId(implementation());
		
		try {
			sql = standardSQL.add(t);
			 if(executeUpdate(sql) != 0)
			return ((BaseId) t).getId();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	};
	/**
	 * 删除,可匹配非主键
	 * @param t
	 * @return
	 * @throws Exception
	 */

	public int delete(T t){
		String sql;
		try {
			sql = standardSQL.delete(t);
			return executeUpdate(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	};
	/**
	 * 更新，只能通过主键进行匹配, 更新除主键外的所有数据，最好和query一同使用
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public int update(T t){
		String sql;
		try {
			sql = standardSQL.update(t);
			return executeUpdate(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return 0;
	};
	
	
	
	/**
	 * 更新
	 * @param t1 匹配条件
	 * @param t2 更新内容
	 * @return
	 * @throws Exception
	 */
	public int update(T t1, T t2){
		String sql;
		try {
			executeUpdate(standardSQL.safe(0));
			sql = standardSQL.update(t1,t2);
			int i = executeUpdate(sql);
			executeUpdate(standardSQL.safe(1));

			return i;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return 0;
	};
	/**
	 * 查看，可匹配非主键
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public ArrayList<T> query(T t){
		String sql;
		try {
			sql = standardSQL.query(t);
			return (ArrayList<T>) executeQuery(sql, t);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	};
	
	

	/**
	 * 根据ResultSet返回结果得到Object
	 * @param rs
	 * @param o
	 * @return
	 * @throws Exception
	 */

	public long  implementation(){
		String date = new SimpleDateFormat("yyMMdd").format(new Date());
		String sql = "select max(id) from " + tableName;
		System.out.println(sql);
		ResultSet resultSet = null;
		long id = 0;
		PreparedStatement preparedStatement = null;
		Connection connection = helper.getConnection();

		try {

			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();

			if(resultSet == null){
				throw new NullPointerException("resultSet is null!");
			}



			if(resultSet.next()){
				id = resultSet.getLong(1);
				if(id < (Long.valueOf(date)*size))
					throw new NullPointerException();
				id++;
			}			

		} catch (Exception e) {
			// TODO: handle exception		
			id = Long.valueOf(date)*size;	
		}finally{
			helper.releaseConnection(resultSet, preparedStatement, connection);
		}
		return id;

	}
	


	/**
	 * 根据SQL语句执行修改数据库方面的操作
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public int executeUpdate(String sql){
		System.out.println("[Execeute SQL] "+sql);
		int set = 0;
		Connection conn = null;
		Statement stat = null;
		try{
			conn = helper.getConnection();
			stat = conn.createStatement();
			set = stat.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			helper.releaseConnection(null, stat, conn);
		}
		return set;
	}

	/**
	 * 根据SQL语句执行查询数据库方面的操作
	 * @param sql
	 * @return List
	 * @throws SQLException 
	 * @throws Exception
	 */
	public List executeQuery(String sql, Object t) throws SQLException{
		System.out.println("[Execeute SQL] "+sql);
		List list = new ArrayList();
		Connection conn = null;
		Statement stat = null;
		try{
			conn = helper.getConnection();
			stat = conn.createStatement();
			stat.executeQuery(sql);
			ResultSet rs = stat.getResultSet();
			int total = rs.getMetaData().getColumnCount();
			while(rs.next()){
				Object obj = this.getObj(rs, t);
				list.add(obj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			helper.releaseConnection(null, stat, conn);
		}
		return list;
	}

	/**
	 * 根据id从数据库中取得实例
	 * @param sql
	 * @return Object
	 * @throws Exception
	 */
	public Object getObject(Object t, String id){
		Object bean = null;
		Connection conn = null;
		Statement stat = null;
		try{
			conn = helper.getConnection();
			Object newObj = t.getClass().newInstance();
			String sql = standardSQL.query(newObj);
			sql += " where id ='"+id+"'";
			stat = conn.createStatement();
			stat.executeQuery(sql);
			ResultSet rs = stat.getResultSet();
			while(rs.next()){
				bean = getObj(rs,t);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		}
		return bean;
	}


	/**
	 * 根据ResultSet返回结果得到Object
	 * @param rs
	 * @param t
	 * @return
	 * @throws Exception
	 */
	private Object getObj(ResultSet rs,Object t) throws Exception{
		if(t==null){
			return null;
		}
		Class clazz = t.getClass();
		Object newObject =clazz.getConstructor(new Class[]{}).newInstance(new Object[]{});
		Method[] methods = clazz.getMethods();
		for(int i=0;i<methods.length;i++){
			Method method = methods[i];
			String methodName = method.getName();
			String methodType = methodName.substring(0, 3);
			if("set".equals(methodType)&&method.getParameterTypes().length==1){
				String fieldName = methodName.substring(3, 4).toLowerCase()+methodName.substring(4);
				Class classType = method.getParameterTypes()[0];
				Object value = rs.getObject(fieldName);
				method.invoke(newObject, new Object[] { value });
			}
		}
		return newObject;
	}
	
	
	

}

