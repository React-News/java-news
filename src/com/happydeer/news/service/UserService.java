package com.happydeer.news.service;

import java.util.List;

import com.happydeer.news.pojo.datatransfer.UserListDownDto;
import com.happydeer.news.pojo.datatransfer.UserListUpDto;
import com.happydeer.news.pojo.domain.User;

public interface UserService {
	public User register(String uTelNum,String uPasswd);
	public User login(String uTelNum,String uPasswd);
	public User modify(User user);
	public User getInfo(int uID);
	public List<UserListDownDto> users(UserListUpDto upDto);
	public int countByDto(UserListUpDto upDto);
}
