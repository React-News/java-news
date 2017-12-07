package com.happydeer.news.service;

import com.happydeer.news.domain.User;

public interface UserService {
	public User register(String uTelNum,String uPasswd);
	public User login(String uTelNum,String uPasswd);
	public User modify(User user);
	public User getInfo(int uID);
}
