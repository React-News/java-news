package com.happydeer.news.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.happydeer.news.dao.UserDao;
import com.happydeer.news.dao.impl.UserDaoImpl;
import com.happydeer.news.pojo.datatransfer.UserListDownDto;
import com.happydeer.news.pojo.datatransfer.UserListUpDto;
import com.happydeer.news.pojo.domain.User;
import com.happydeer.news.service.UserService;

public class UserServiceImpl implements UserService {
	private static UserDao userDao = new UserDaoImpl();
	@Override
	public User register(String telNum ,String passwd) {
		User u = userDao.findUserByTelNum(telNum);
		if(u!=null) {
			System.out.println("userSerice"+u.toString());
			return null;
		}else {
			User user = new User();
			user.setTelnum(telNum);
			user.setPasswd(passwd);
			userDao.addUser(user);
			return userDao.findUserByTelNum(telNum);
		}
		
	}

	@Override
	public User login(String uTelNum, String uPasswd) {
		User u = userDao.findUserByTelNum(uTelNum);
		if(u!=null) {
			if(uPasswd.equals(u.getPasswd()))
				return u;
			else
				return null;
		}else {
			return null;
		}
	}

	@Override
	public User modify(User user) {
		userDao.modifyUser(user);
		return userDao.findUserById(user.getId());
		
	}

	@Override
	public User getInfo(int uID) {
		return userDao.findUserById(uID);
	}

	@Override
	public List<UserListDownDto> users(UserListUpDto upDto) {
		List<User> list = userDao.queryAllByDto(upDto);
		List<UserListDownDto> result = new ArrayList<>();
		for(User u:list) {
			UserListDownDto dto = new UserListDownDto();
			dto.setId(u.getId());
			dto.setName(u.getName());
			dto.setAge(u.getAge());
			dto.setAvatar(u.getAvatar());
			dto.setSex(u.getSex());
			dto.setTelNum(u.getTelnum());
			dto.setType(u.getType());
			result.add(dto);
		}
		return result;
	}

	@Override
	public int countByDto(UserListUpDto upDto) {
		return userDao.countByDto(upDto);
	}
	

}
