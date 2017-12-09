package com.happydeer.news.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.happydeer.news.dao.UserDao;
import com.happydeer.news.pojo.domain.User;
import com.happydeer.news.utils.DBUtil;

public class UserDaoImpl implements UserDao {
	
	@Override
	public void addUser(User user) {
		Connection con = null;
		PreparedStatement prest = null;
		try {
			con = DBUtil.getConnection();
			String sql = "insert into user(uName,uTelNum,uPasswd) values(?,?,?)";
			prest = con.prepareStatement(sql);
			prest.setString(1, user.getName());
			prest.setString(2, user.getTelnum());
			prest.setString(3, user.getPasswd());
			prest.executeUpdate();
		} catch (Exception e) {
			System.out.println("查询异常");
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			try {
				if (prest != null)
					prest.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.out.println("查询异常");
				e.printStackTrace();
				throw new RuntimeException();
			}
		}

	}

	@Override
	public void modifyUser(User user) {
		Connection con = null;
		PreparedStatement prest = null;
		ResultSet rs = null;
		List<User> list = null;
		try {
			con = DBUtil.getConnection();
			String sql = "update user set uName=?,uAvatar=?,uSex=?,uAge=?,uDesc=?,uTelNum=?,uType=?,uPasswd=? where uID=?";
			prest = con.prepareStatement(sql);
			prest.setString(1, user.getName());
			prest.setString(2, user.getAvatar());
			prest.setString(3, user.getSex());
			prest.setInt(4, user.getAge());
			prest.setString(5, user.getDescribe());
			prest.setString(6, user.getTelnum());
			prest.setString(7, user.getType());
			prest.setString(8, user.getPasswd());
			prest.setInt(9, user.getId());
			prest.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (prest != null)
					prest.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				throw new RuntimeException();
			}
		}

	}

	@Override
	public List<User> queryAll() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		List<User> list = null;
		try {
			con = DBUtil.getConnection();
			String sql = "select * from User where uID > 1";
			rs = st.executeQuery(sql);
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setAvatar(rs.getString(3));
				user.setSex(rs.getString(4));
				user.setAge(rs.getInt(5));
				user.setDescribe(rs.getString(6));
				user.setType(rs.getString(7));
				user.setPasswd(rs.getString(8));
				user.setTelnum(rs.getString(9));
				list.add(user);
			}
		} catch (Exception e) {
			throw new RuntimeException();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				throw new RuntimeException();
			}
		}
		return list;
	}

	@Override
	public User findUserByTelNum(String telNum) {
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

			if (rs.next()) {
				System.out.println("查到了");
				user = new User();
				user.setId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setAvatar(rs.getString(3));
				user.setSex(rs.getString(4));
				user.setAge(rs.getInt(5));
				user.setDescribe(rs.getString(6));
				user.setType(rs.getString(7));
				user.setPasswd(rs.getString(8));
				user.setTelnum(rs.getString(9));
			}
		} catch (Exception e) {
			System.out.println("查询异常");
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (prest != null)
					prest.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.out.println("sql异常");
				e.printStackTrace();
				throw new RuntimeException();
			}
		}
		return user;
	}

	@Override
	public User findUserById(int uID) {
		Connection con = null;
		PreparedStatement prest = null;
		ResultSet rs = null;
		User user = null;
		try {
			con = DBUtil.getConnection();
			String sql = "select * from User where uID = ?";
			prest = con.prepareStatement(sql);
			prest.setInt(1, uID);
			rs = prest.executeQuery();

			if (rs.next()) {
				System.out.println("查到了");
				user = new User();
				user.setId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setAvatar(rs.getString(3));
				user.setSex(rs.getString(4));
				user.setAge(rs.getInt(5));
				user.setDescribe(rs.getString(6));
				user.setType(rs.getString(7));
				user.setPasswd(rs.getString(8));
				user.setTelnum(rs.getString(9));
			}
		} catch (Exception e) {
			System.out.println("查询异常");
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (prest != null)
					prest.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.out.println("sql异常");
				e.printStackTrace();
				throw new RuntimeException();
			}
		}
		return user;
	}

	@Override
	public List<User> queryMore(int count,int total) {
		Connection con = null;
		PreparedStatement prest = null;
		ResultSet rs = null;
		List<User> list = null;
		try {
			con = DBUtil.getConnection();
			String sql = "select * from User offset=? limit=?";
			prest = con.prepareStatement(sql);
			prest.setInt(1, total);
			prest.setInt(2, count);
			rs = prest.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setAvatar(rs.getString(3));
				user.setSex(rs.getString(4));
				user.setAge(rs.getInt(5));
				user.setDescribe(rs.getString(6));
				user.setType(rs.getString(7));
				user.setPasswd(rs.getString(8));
				user.setTelnum(rs.getString(9));
				list.add(user);
			}
		} catch (Exception e) {
			throw new RuntimeException();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (prest != null)
					prest.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				throw new RuntimeException();
			}
		}
		return list;
	}

}
