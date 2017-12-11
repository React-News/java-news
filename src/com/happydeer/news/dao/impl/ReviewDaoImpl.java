package com.happydeer.news.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.happydeer.news.dao.ReviewDao;
import com.happydeer.news.pojo.domain.Review;
import com.happydeer.news.utils.DBUtil;

public class ReviewDaoImpl implements ReviewDao{

	@Override
	public int addReview(Review review) {
		Connection con = null;
		PreparedStatement prest = null;
		int result = 0;
		try {
			con = DBUtil.getConnection();
			String sql = "";
			if(review.getPID()!=-1) {
				sql = "insert into review(uID,nID,rContent,rTime,prID) values(?,?,?,?,?)";
				prest = con.prepareStatement(sql);
				prest.setInt(5, review.getPID());
			}else {
				sql = "insert into review(uID,nID,rContent,rTime) values(?,?,?,?)";
				prest = con.prepareStatement(sql);
			}
			prest.setInt(1, review.getUID());
			prest.setInt(2, review.getNID());
			prest.setString(3, review.getContent());
			prest.setTimestamp(4,  new Timestamp(review.getTime().getTime()));
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
	public int removeReview(int id) {
		Connection con = null;
		PreparedStatement prest = null;
		int result = 0;
		try {
			con = DBUtil.getConnection();
			String sql = "delete from review where rID=?";
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
	public int removeReviewByNID(int nID) {
		Connection con = null;
		PreparedStatement prest = null;
		int result = 0;
		try {
			con = DBUtil.getConnection();
			String sql = "delete from review where nID=?";
			prest = con.prepareStatement(sql);
			prest.setInt(1, nID);
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

	/**
	 * 
	 * @param uID
	 * @return
	 */
	@Override
	public int removeReviewByUID(int uID) {
		Connection con = null;
		PreparedStatement prest = null;
		int result = 0;
		try {
			con = DBUtil.getConnection();
			String sql = "delete from review where uID=?";
			prest = con.prepareStatement(sql);
			prest.setInt(1, uID);
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
	public int countByNID(int nID) {
		Connection con = null;
		PreparedStatement prest = null;
		int result = 0;
		try {
			con = DBUtil.getConnection();
			String sql = "select count(*) from review where nID=?";
			prest = con.prepareStatement(sql);
			prest.setInt(1, nID);
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
	public List<Review> queryReviewListByNID(int nID) {
		Connection con = null;
		PreparedStatement prest = null;
		ResultSet rs = null;
		List<Review> list = new ArrayList<>();
		try {
			con = DBUtil.getConnection();
			String sql = "select rID,uID,nID,rContent,rTime from review where prID is null and nID=?";
			prest = con.prepareStatement(sql);
			prest.setInt(1, nID);
			rs = prest.executeQuery(sql);
			Review review = new Review();
			while (rs.next()) {
				review.setId(rs.getInt(1));
				review.setUID(rs.getInt(2));
				review.setNID(rs.getInt(3));
				review.setContent(rs.getString(4));
				review.setTime(rs.getTimestamp(5));
				list.add(review);
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

	@Override
	public List<Review> queryPReviewListByRID(int rID) {
		Connection con = null;
		PreparedStatement prest = null;
		ResultSet rs = null;
		List<Review> list = new ArrayList<>();
		try {
			con = DBUtil.getConnection();
			String sql = "select * from review where prID=?";
			prest = con.prepareStatement(sql);
			prest.setInt(1, rID);
			rs = prest.executeQuery(sql);
			Review review = new Review();
			while (rs.next()) {
				review.setId(rs.getInt(1));
				review.setUID(rs.getInt(2));
				review.setNID(rs.getInt(3));
				review.setContent(rs.getString(4));
				review.setPID(rs.getInt(5));
				review.setTime(rs.getTimestamp(6));
				list.add(review);
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
