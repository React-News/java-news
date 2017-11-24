package com.happydeer.news.service.impl;

import com.happydeer.news.dao.UserDao;
import com.happydeer.news.dao.impl.UserDaoImpl;
import com.happydeer.news.domain.User;
import com.happydeer.news.service.UserService;

public class UserServiceImpl implements UserService {

	@Override
	public User register(String telNum ,String passwd) {
		UserDao userDao = new UserDaoImpl();
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

}
