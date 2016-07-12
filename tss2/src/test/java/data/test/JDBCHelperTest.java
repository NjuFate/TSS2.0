package data.test;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import data.base.JDBCHelper;

public class JDBCHelperTest {
	JDBCHelper helper = new JDBCHelper();

	@Test
	public void testCreate() {
     if(helper == null)
    	 fail("Not class created!");
	}

	

	@Test
	public void testGetConnection() {
		fail("Not connection return!");
	}

}
