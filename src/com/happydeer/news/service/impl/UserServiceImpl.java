package com.happydeer.news.service.impl;

import com.happydeer.news.dao.UserDao;
import com.happydeer.news.dao.impl.UserDaoImpl;
import com.happydeer.news.domain.User;
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
	

}
