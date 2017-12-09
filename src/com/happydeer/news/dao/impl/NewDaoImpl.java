package com.happydeer.news.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import com.happydeer.news.dao.NewDao;
import com.happydeer.news.pojo.domain.New;
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
	public List<New> queryMore() {
		// TODO Auto-generated method stub
		return null;
	}



}
