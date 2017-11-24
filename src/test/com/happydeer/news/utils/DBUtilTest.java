package test.com.happydeer.news.utils;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import com.happydeer.news.utils.DBUtil;

public class DBUtilTest {

	@Test
	public void testGetConnection() throws SQLException {
		Connection con = DBUtil.getConnection();
		System.out.println(con);
	}

}
