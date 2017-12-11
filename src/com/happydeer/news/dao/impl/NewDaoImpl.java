package com.happydeer.news.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.happydeer.news.dao.NewDao;
import com.happydeer.news.pojo.datatransfer.NewListUpDto;
import com.happydeer.news.pojo.domain.New;
import com.happydeer.news.pojo.domain.User;
import com.happydeer.news.utils.DBUtil;

public class NewDaoImpl implements NewDao {

	@Override
	public int addNew(New NEW) {
		Connection con = null;
		PreparedStatement prest = null;
		int result = 0;
		try {
			con = DBUtil.getConnection();
			String sql = "insert into new(uID,nTitle,nType,nImg,nContent,nTime) values(?,?,?,?,?,?)";
			prest = con.prepareStatement(sql);
			prest.setInt(1, NEW.getUID());
			prest.setString(2, NEW.getTitle());
			prest.setString(3, NEW.getType());
			prest.setString(4, NEW.getImg());
			prest.setString(5, NEW.getContent());
			prest.setTimestamp(6, new Timestamp(NEW.getTime().getTime()));
			result = prest.executeUpdate();
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
		return result;
	}


	@Override
	public List<New> queryAll() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		List<New> list = new ArrayList<>();
		try {
			con = DBUtil.getConnection();
			st = con.createStatement();
			String sql = "select * from New";
			rs = st.executeQuery(sql);
			while (rs.next()) {
				New NEW = new New();
				NEW.setId(rs.getInt(1));
				NEW.setTitle(rs.getString(2));
				NEW.setType(rs.getString(3));
				NEW.setImg(rs.getString(4));
				NEW.setContent(rs.getString(5));
				NEW.setTime(rs.getTimestamp(6));
				NEW.setUID(rs.getInt(7));
				list.add(NEW);
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
	public List<New> queryAllByDto(NewListUpDto upDto) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		List<New> list = new ArrayList<>();
		try {
			con = DBUtil.getConnection();
			st = con.createStatement();
			String sql = "select * from new where nID>0";
			if(upDto.getUID()!=-1)sql=sql+" and uID="+upDto.getUID();
			if(!"".equals(upDto.getKeywd()))sql=sql+" and nTitle like '%"+upDto.getKeywd()+"%'";
			if(upDto.getPageSize()>0) {
				int offset = (upDto.getCurrentPage()-1)*upDto.getPageSize();
				int limit = upDto.getPageSize();
				sql=sql+" limit "+limit+" offset "+offset;
			}
			System.out.println(sql);
			rs = st.executeQuery(sql);
			while (rs.next()) {
				New NEW = new New();
				NEW.setId(rs.getInt(1));
				NEW.setTitle(rs.getString(2));
				NEW.setType(rs.getString(3));
				NEW.setImg(rs.getString(4));
				NEW.setContent(rs.getString(5));
				NEW.setTime(rs.getTimestamp(6));
				NEW.setUID(rs.getInt(7));
				list.add(NEW);
			}
		} catch (Exception e) {
			e.printStackTrace();
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
	public New queryNewByID(int id) {
		Connection con = null;
		PreparedStatement prest = null;
		ResultSet rs = null;
		New NEW = null;
		try {
			con = DBUtil.getConnection();
			String sql = "select * from new where nID = ?";
			prest = con.prepareStatement(sql);
			prest.setInt(1, id);
			rs = prest.executeQuery();

			if (rs.next()) {
				System.out.println("查到了");
				NEW = new New();
				NEW.setId(rs.getInt(1));
				NEW.setTitle(rs.getString(2));
				NEW.setType(rs.getString(3));
				NEW.setImg(rs.getString(4));
				NEW.setContent(rs.getString(5));
				NEW.setTime(rs.getTimestamp(6));
				NEW.setUID(rs.getInt(7));
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
		return NEW;
	}



}
