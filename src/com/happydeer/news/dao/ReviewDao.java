package com.happydeer.news.dao;

import java.util.List;

import com.happydeer.news.pojo.domain.Review;

public interface ReviewDao {
	//添加评论
	public int addReview(Review review);
	//删除评论
	public int removeReview(int id);
	public int removeReviewByNID(int nID);
	public int removeReviewByUID(int uID);
	//得到某一条新闻的评论数
	public int countByNID(int nID);
	//获取某一条新闻一级评论列表
	public List<Review> queryReviewListByNID(int nID);
	//二级评论
	public List<Review> queryPReviewListByRID(int rID);
	public List<Review> queryMyReview(int uID);
	
}
