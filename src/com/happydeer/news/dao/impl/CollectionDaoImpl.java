package com.happydeer.news.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.happydeer.news.dao.CollectionDao;
import com.happydeer.news.pojo.domain.Collection;
import com.happydeer.news.utils.DBUtil;

public class CollectionDaoImpl implements CollectionDao {

	@Override
	public int countByNID(int id) {
		Connection con = null;
		PreparedStatement prest = null;
		ResultSet rs = null;
		int result = 0;
		try {
			con = DBUtil.getConnection();
			String sql = "select count(*) from collection where nID=?";
			prest = con.prepareStatement(sql);
			prest.setInt(1, id);
			rs = prest.executeQuery();
			if(rs.next())result=rs.getInt(1);
		} catch (Exception e) {
			System.out.println("查询异常");
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			try {
				if(rs!=null)
					rs.close();
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
	public Collection queryCollectionByID(int id) {
		Connection con = null;
		PreparedStatement prest = null;
		ResultSet rs = null;
		Collection collection = null;
		try {
			con = DBUtil.getConnection();
			String sql = "select nID,uID from collection where cID=?";
			prest = con.prepareStatement(sql);
			prest.setInt(1, id);
			rs = prest.executeQuery();
			if(rs.next()) {
				collection = new Collection();
				collection.setId(id);
				collection.setNID(rs.getInt(2));
				collection.setUID(rs.getInt(3));
			}
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
		return collection;
	}

	@Override
	public int addCollection(int uID, int nID) {
		Connection con = null;
		PreparedStatement prest = null;
		int result = 0;
		try {
			con = DBUtil.getConnection();
			String sql = "insert into collection(uID,nID) values(?,?)";
			prest = con.prepareStatement(sql);
			prest.setInt(1, uID);
			prest.setInt(2, nID);
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
	public int removeCollection(int id) {
		Connection con = null;
		PreparedStatement prest = null;
		int result = 0;
		try {
			con = DBUtil.getConnection();
			String sql = "delete from collection where cID=?";
			prest = con.prepareStatement(sql);
			prest.setInt(1, id);
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
	public List<Collection> queryCollectionListByUID(int uID) {
		Connection con = null;
		PreparedStatement prest = null;
		ResultSet rs = null;
		List<Collection> list = new ArrayList<>();
		try {
			con = DBUtil.getConnection();
			String sql = "select * from collection where uID=?";
			prest = con.prepareStatement(sql);
			prest.setInt(1, uID);
			rs = prest.executeQuery();
			Collection collection = null;
			while(rs.next()) {
				collection = new Collection();
				collection.setId(rs.getInt(1));
				collection.setNID(rs.getInt(2));
				collection.setUID(rs.getInt(3));
				list.add(collection);
			}
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
		return list;
	}

}
