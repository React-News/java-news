package com.happydeer.news.dao;

import java.util.List;

import com.happydeer.news.pojo.domain.Collection;

public interface CollectionDao {

	//添加收藏
	public int addCollection(int uID,int nID);
	//删除收藏
	public int removeCollection(int id);
	//通过新闻id获取某一条新闻的收藏总数
	public int countByNID(int nID);
	//获取收藏信息
	public Collection queryCollectionByID(int id);
	//通过用户id获取某一用户的收藏列表
	public List<Collection> queryCollectionListByUID(int uID);
}
