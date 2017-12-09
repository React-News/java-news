package com.happydeer.news.dao;

import java.util.List;

import com.happydeer.news.pojo.domain.User;

public interface UserDao {
	void addUser(User user);
//	void delUser();
	void modifyUser(User user);
	List<User> queryAll();
	List<User> queryMore(int count,int total);
	User findUserByTelNum(String telNum);
	User findUserById(int uID);
}
