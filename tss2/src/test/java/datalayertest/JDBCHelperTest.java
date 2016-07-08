package datalayertest;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import datalayer.JDBCHelper;

public class JDBCHelperTest {
	JDBCHelper helper = JDBCHelper.create();

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
