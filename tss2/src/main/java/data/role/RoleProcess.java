package data.role;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import data.base.JDBCHelper;
import general.Role;
import po.Authority;
import po.ModuleAuthority;
import po.RoleAuthority;

public class RoleProcess {
	JDBCHelper helper;
	
	
	public RoleProcess() {
		// TODO Auto-generated constructor stub
		helper = new JDBCHelper();
	}
	
	
	

	public RoleAuthority getRolePrivilege(Role role){
		RoleAuthority roleAuthority = new RoleAuthority();
		roleAuthority.setRCode(role.toString());

		return getRoleModule(role.toString(), roleAuthority);
	}

	private RoleAuthority getRoleModule(String role, RoleAuthority roleAuthority){
		String sql1 = "select FatherRCode from  Role where RCode = ?";

		String sql2 = "select MCode from RoleModule where RCode = ?";

		ResultSet resultSet = null;
		Connection connection = helper.getConnection();
		PreparedStatement pStatement = null;
		try {
			pStatement = connection.prepareStatement(sql2);
			pStatement.setString(1, role);

			resultSet = pStatement.executeQuery();
			if(resultSet == null)
				throw new NullPointerException();			

			while(resultSet.next()){
				roleAuthority.setModule(resultSet.getString("MCode"), getModulePrivilege(resultSet.getString("MCode")));

			}

			pStatement = connection.prepareStatement(sql1);
			pStatement.setString(1, role);

			resultSet = pStatement.executeQuery();
			if(resultSet == null)
				throw new NullPointerException();	

			if(resultSet.next()){
				return getRoleModule(resultSet.getString(1), roleAuthority);

			}



		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			helper.releaseConnection(resultSet, pStatement , null );
		}



		return roleAuthority;
	}



	public ModuleAuthority getModulePrivilege(String MCode){
		String sql1 = "select * from Module where MCode = ?";
		String sql2 = "select ACode from ModuleAuthority where MCode = ?";
		ResultSet resultSet = null;
		PreparedStatement pStatement = null;
		Connection connection = helper.getConnection();
		try {
			pStatement = connection.prepareStatement(sql1);
			pStatement.setString(1, MCode);

			resultSet = pStatement.executeQuery();

			//是否考虑抛出空白异常
			if(resultSet == null){
				throw new NullPointerException();
			}
			ModuleAuthority moduleAuthority  = new ModuleAuthority();
			if(resultSet.next()){
				moduleAuthority.setMID(resultSet.getInt("MID"));
				moduleAuthority.setFatherMID(resultSet.getInt("FatherMID"));
				moduleAuthority.setMName(resultSet.getString("MName"));
				moduleAuthority.setMCode(MCode);
				moduleAuthority.setOther(resultSet.getString("other"));
			}

			pStatement = helper.getConnection().prepareStatement(sql2);
			pStatement.setString(1, MCode);
			resultSet = pStatement.executeQuery();

			if(resultSet == null){
				throw new NullPointerException();
			}

			while (resultSet.next()) {
				moduleAuthority.setPrivilege(resultSet.getString("ACode"));				
			}



			return moduleAuthority;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			helper.releaseConnection(resultSet, pStatement, connection);
		}


		throw new NullPointerException();
	}
	
	
	public ArrayList<Authority> getAuthorityList(){
		String sql = "select * from  Authority";
		PreparedStatement preparedStatement = null;
		ArrayList<Authority>list;
		ResultSet resultSet  = null;
		Connection connection = helper.getConnection();
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			
		   resultSet = preparedStatement.executeQuery();
			if(resultSet == null)
				throw new NullPointerException();
			list = new ArrayList<Authority>();
			while(resultSet.next()){
				Authority authority = new Authority();
				authority.setACode(resultSet.getString("ACode"));
				authority.setAID(resultSet.getInt("AID"));
				authority.setAName(resultSet.getString("AName"));
				list.add(authority);
			}
			
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			helper.releaseConnection(resultSet, preparedStatement, connection);
		}	
		return null;
	}
	
	
	

}
