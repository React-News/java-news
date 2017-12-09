package com.happydeer.news.service.impl;

import com.happydeer.news.dao.NewDao;
import com.happydeer.news.dao.impl.NewDaoImpl;
import com.happydeer.news.pojo.domain.New;
import com.happydeer.news.service.NewService;

public class NewServiceImpl implements NewService{
	
	private static NewDao newDao = new NewDaoImpl();
	@Override
	public int addNews(New NEW) {
		return newDao.addNew(NEW);
	}
	
	

}
