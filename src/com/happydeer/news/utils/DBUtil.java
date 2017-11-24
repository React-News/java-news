package com.happydeer.news.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {
	
	private static Properties props = null;
	
	//��ʼ��������dbconfig.properties��props��
	static {
		try {
			//���������ļ�
			InputStream in = DBUtil.class.getClassLoader()
					.getResourceAsStream("dbconfig.properties");
			props = new Properties();
			props.load(in);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		try {
			//����������
			Class.forName(props.getProperty("driver"));
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	//�������ݿ�����
	public static Connection getConnection() throws SQLException {
		
		return DriverManager.getConnection(props.getProperty("url"),
							 props.getProperty("usename"),
							 props.getProperty("password"));
	}

}
