package com.happydeer.news.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.happydeer.news.dao.NewDao;
import com.happydeer.news.dao.UserDao;
import com.happydeer.news.dao.impl.NewDaoImpl;
import com.happydeer.news.dao.impl.UserDaoImpl;
import com.happydeer.news.pojo.datatransfer.NewListDownDto;
import com.happydeer.news.pojo.datatransfer.NewListDownDto.CreaterInfo;
import com.happydeer.news.pojo.datatransfer.NewListUpDto;
import com.happydeer.news.pojo.domain.New;
import com.happydeer.news.pojo.domain.User;
import com.happydeer.news.service.NewService;

public class NewServiceImpl implements NewService{
	
	private static NewDao newDao = new NewDaoImpl();
	private static UserDao userDao = new UserDaoImpl();
	@Override
	public int addNews(New NEW) {
		return newDao.addNew(NEW);
	}
	@Override
	public List<NewListDownDto> getNewList(NewListUpDto upDto) {
		List<New> list = newDao.queryAllByDto(upDto); 
		List<NewListDownDto> result = new ArrayList<>();
		NewListDownDto downDto = null;
		User u = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss:SSS'Z'");
		for(New n:list) {
			downDto = new NewListDownDto();
			downDto.setNID(n.getId());
			downDto.setTitle(n.getTitle());
			System.out.println(n.getTime());
			downDto.setCreatedAt(sdf.format(n.getTime()));
			u = userDao.findUserById(n.getUID());
			CreaterInfo createrInfo = downDto.new CreaterInfo();
			createrInfo.setUID(u.getId());
			createrInfo.setName(u.getName());
			createrInfo.setAvatar(u.getAvatar());
			downDto.setCreaterInfo(createrInfo);
			result.add(downDto);
		}
		return result;
	}
	
	

}
