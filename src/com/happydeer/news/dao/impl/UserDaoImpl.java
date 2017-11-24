package com.happydeer.news.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.happydeer.news.dao.UserDao;
import com.happydeer.news.domain.User;
import com.happydeer.news.utils.DBUtil;

public class UserDaoImpl implements UserDao{

	@Override
	public void addUser(User user) {
		Connection con = null;
		PreparedStatement prest = null;
		try {
			con = DBUtil.getConnection();
			String sql = "insert into user(uName,uTelNum,uPasswd) values(?,?,?)";
			prest = con.prepareStatement(sql);
			prest.setString(1, "网友");
			prest.setString(2, user.getTelnum());
			prest.setString(3, user.getPasswd());
			prest.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException();
		} finally {
			try {
				if(prest!=null) prest.close();
				if(con!=null) con.close();
			} catch (SQLException e) {
				throw new RuntimeException();
			}
		}
		
	}

	@Override
	public void modifyUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<User> queryAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findUserByTelNum(String telNum) {
		System.out.println("查询。。。");
		Connection con = null;
		PreparedStatement prest = null;
		ResultSet rs = null;
		User user = null;
		try {
			con = DBUtil.getConnection();
			String sql = "select * from User where uTelNum = ?";
			prest = con.prepareStatement(sql);
			prest.setString(1, telNum);
			rs = prest.executeQuery();

			if(rs.next()) {
				System.out.println("查到了");
				user = new User();
				user.setId(rs.getInt(1));
				user.setTelnum(rs.getString(9));
				user.setPasswd(rs.getString(8));
			}
		} catch (Exception e) {
			throw new RuntimeException();
		} finally {
			try {
				if(rs!=null)rs.close();
				if(prest!=null) prest.close();
				if(con!=null) con.close();
			} catch (SQLException e) {
				throw new RuntimeException();
			}
		}
		return user;
	}

}
