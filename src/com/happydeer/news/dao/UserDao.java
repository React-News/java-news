package com.happydeer.news.dao;

import java.util.List;

import com.happydeer.news.domain.User;

public interface UserDao {
	public void addUser(User user);
//	public void delUser();
	public void modifyUser(User user);
	public List<User> queryAll();
	public User findUserByTelNum(String telNum);
}
