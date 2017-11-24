package com.happydeer.news.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {
	
	private static Properties props = null;
	
	//初始化，加载dbconfig.properties到props中
	static {
		try {
			//加载配置文件
			InputStream in = DBUtil.class.getClassLoader()
					.getResourceAsStream("dbconfig.properties");
			props = new Properties();
			props.load(in);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		try {
			//加载驱动类
			Class.forName(props.getProperty("driver"));
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	//返回数据库连接
	public static Connection getConnection() throws SQLException {
		
		return DriverManager.getConnection(props.getProperty("url"),
							 props.getProperty("usename"),
							 props.getProperty("password"));
	}

}
