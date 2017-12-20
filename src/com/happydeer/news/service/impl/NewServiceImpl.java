package com.happydeer.news.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.happydeer.news.dao.CollectionDao;
import com.happydeer.news.dao.NewDao;
import com.happydeer.news.dao.ReviewDao;
import com.happydeer.news.dao.UserDao;
import com.happydeer.news.dao.impl.CollectionDaoImpl;
import com.happydeer.news.dao.impl.NewDaoImpl;
import com.happydeer.news.dao.impl.ReviewDaoImpl;
import com.happydeer.news.dao.impl.UserDaoImpl;
import com.happydeer.news.pojo.datatransfer.CreaterInfo;
import com.happydeer.news.pojo.datatransfer.NewInfoDto;
import com.happydeer.news.pojo.datatransfer.NewListDownDto;
import com.happydeer.news.pojo.datatransfer.NewListUpDto;
import com.happydeer.news.pojo.domain.New;
import com.happydeer.news.pojo.domain.User;
import com.happydeer.news.service.NewService;

public class NewServiceImpl implements NewService{
	
	private static NewDao newDao = new NewDaoImpl();
	private static UserDao userDao = new UserDaoImpl();
	private static ReviewDao reviewDao = new ReviewDaoImpl();
	private static CollectionDao collectionDao = new CollectionDaoImpl();
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
			downDto.setType(n.getType());
			downDto.setImg(n.getImg());
			downDto.setCreatedAt(n.getTime().getTime());
			u = userDao.findUserById(n.getUID());
			CreaterInfo createrInfo = new CreaterInfo();
			createrInfo.setId(u.getId());
			createrInfo.setName(u.getName());
			createrInfo.setAvatar(u.getAvatar());
			downDto.setCreaterInfo(createrInfo);
			result.add(downDto);
		}
		return result;
	}
	@Override
	public int getTotalSize(NewListUpDto upDto) {
		return newDao.countByDto(upDto);
	}
	@Override
	public int removeNew(int nID) {
		
		int result = newDao.removeNew(nID);
		//删除新闻的时候，级联删除该新闻的所有评论，以及所有用户对该新闻的收藏
		if(result>0) {
			reviewDao.removeReview(nID);        
			collectionDao.removeCollection(nID);
		}
		return result;
	}
	@Override
	public New getNewByID(int nID) {
		return newDao.queryNewByID(nID);
	}
	/* (non-Javadoc)
	 * @see com.happydeer.news.service.NewService#getNewInfo(int)
	 */
	@Override
	public NewInfoDto getNewInfo(int nID) {
		NewInfoDto dto = new NewInfoDto();
		New NEW = getNewByID(nID);
		User user = userDao.findUserById(NEW.getUID());
		dto.setId(nID);
		dto.setTitle(NEW.getTitle());
		dto.setType(NEW.getType());
		dto.setContent(NEW.getContent());
		dto.setTime(NEW.getTime());
		dto.setReviewSize(reviewDao.countByNID(nID));
		dto.setCollectionSize(collectionDao.countByNID(nID));
		CreaterInfo createrInfo = new CreaterInfo();
		createrInfo.setId(user.getId());
		createrInfo.setName(user.getName());
		createrInfo.setAvatar(user.getAvatar());
		dto.setCreaterInfo(createrInfo);
		return dto;
	}
	
	

}
