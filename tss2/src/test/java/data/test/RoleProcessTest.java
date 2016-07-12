package data.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Test;

import data.role.RoleProcess;
import general.Role;
import po.Authority;
import po.ModuleAuthority;
import po.RoleAuthority;

public class RoleProcessTest {

	private RoleProcess process;

	public RoleProcessTest() {
		// TODO Auto-generated constructor stub
		process = new RoleProcess();
	}

	@Test
	public void testGetRolePrivilege() {

		ModuleAuthority moduleAuthority1 =  process.getModulePrivilege("COURSE_MANAGE");
		ModuleAuthority moduleAuthority2 = process.getModulePrivilege("USER_MANAGE");
		assertEquals("MName:课程管理 COURSE_MANAGE_ACS ADD DELETE UPDATE QUERY", moduleAuthority1.toString());
		assertEquals("MName:用户管理 USER_MANAGE_ACS ADD DELETE UPDATE QUERY", moduleAuthority2.toString());

	}

	@Test
	public void testGetModulePrivilege() {
		 RoleAuthority roleAuthority = process.getRolePrivilege(Role.GUEST);		
		 assertEquals("RCode: GUEST CHECK_COURSE_MESSAGE", roleAuthority.toString());

		   roleAuthority = process.getRolePrivilege(Role.STUDENT);
			 assertEquals("RCode: STUDENT CHECK_HOMEWORK DOWNLOAD_COURSEWARE JOIN_CLASS MESSAGE_MANAGE "
			 		+ "UPLOAD_HOMEWORK CHECK_COURSE_MESSAGE", roleAuthority.toString());


		   roleAuthority = process.getRolePrivilege(Role.TEACHER);
			 assertEquals("RCode: TEACHER UPLOAD_COURSEWARE DOWNLOAD_COURSEWARE DOWNLOAD_HOMEWORK DELETE_COURSEWARE "
			 		+ "MESSAGE_MANAGE DELETE_HOMEWORK ASSIGN_HOMEWORK CHECK_COURSE_MESSAGE", roleAuthority.toString());

		   

		   roleAuthority = process.getRolePrivilege(Role.ROOT);
			 assertEquals("RCode: ROOT UPLOAD_COURSEWARE CLASS_MANAGE DOWNLOAD_COURSEWARE DELETE_COURSEWARE "
			 		+ "USER_MANAGE COURSE_MANAGE DELETE_HOMEWORK ASSIGN_HOMEWORK CHECK_COURSE_MESSAGE USER_ROLE_SET DOWNLOAD_HOMEWORK "
			 		+ "MESSAGE_MANAGE MODULE_AUTHORITY_SET", roleAuthority.toString());

		 
		  
	}
	@Test
	public void testGetAuthorityList() {
		ArrayList<Authority>arrayList = process.getAuthorityList();
		assertEquals(20, arrayList.size());		
		
	}

}
