package com.happydeer.news.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

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
import com.happydeer.news.pojo.datatransfer.NewListUpDto;
import com.happydeer.news.pojo.domain.Collection;
import com.happydeer.news.pojo.domain.New;
import com.happydeer.news.pojo.domain.User;
import com.happydeer.news.service.CollectionService;

public class CollectionServiceImpl implements CollectionService{
    private static NewDao newDao = new NewDaoImpl();
    private static UserDao userDao = new UserDaoImpl();
    private static ReviewDao reviewDao = new ReviewDaoImpl();
    private static CollectionDao collectionDao = new CollectionDaoImpl();
    private int totalSize=0;
	@Override
	public boolean collection(int uID,int nID) {
		int result = collectionDao.addCollection(uID, nID);
		if(result>0)return true;
		return false;
	}

	@Override
	public boolean cancel(int id) {
		int result = collectionDao.removeCollection(id);
		if(result>0)return true;
		return false;
	}

	@Override
	public List<NewInfoDto> queryMore(NewListUpDto cDto) {
		List<Collection> clist =collectionDao.queryCollectionListByUID(cDto.getUID());
		List<NewInfoDto> result = new ArrayList<>();
		List<NewInfoDto> last = null;
		int pageSize = cDto.getPageSize();
		int currentPage = cDto.getCurrentPage();
		int from = (currentPage-1)*pageSize;
		int to = currentPage*pageSize;
		String keywd = cDto.getKeywd();
		String[] types = cDto.getTypes();
		for(Collection c:clist) {
			New n = newDao.queryNewByID(c.getNID());
			String pattern = ".*"+keywd+".*";
			boolean isMatch = Pattern.matches(pattern, n.getTitle());
			boolean inTypes = false;
			if(types.length>0) {
				for(String s:types) {
					if(s.equals(n.getType())) {
						inTypes = true;
						break;
					}
				}
			}else {
				inTypes = true;
			}
			if(isMatch&&inTypes) {
				NewInfoDto dto = new NewInfoDto();
				dto.setId(c.getNID());
				dto.setCID(c.getId());
				dto.setTitle(n.getTitle());
				dto.setType(n.getType());
				dto.setTime(n.getTime());
				dto.setReviewSize(reviewDao.countByNID(c.getNID()));
				dto.setCollectionSize(collectionDao.countByNID(c.getNID()));
				CreaterInfo createrInfo = new CreaterInfo();
				User u = userDao.findUserById(n.getUID());
				createrInfo.setId(u.getId());
				createrInfo.setName(u.getName());
				createrInfo.setAvatar(u.getAvatar());
				dto.setCreaterInfo(createrInfo);
				result.add(dto);
			}else {
				continue;
			}
			
		}
		totalSize = result.size();
		try {
			last = result.subList(from, to);
		} catch (IndexOutOfBoundsException e) {
			int len = result.size();
			if(from>=len) {}
			else if(from<len)last=result.subList(from, len);
			e.printStackTrace();
		}
		return last;
	}

	@Override
	public int getTotalSize() {
		return totalSize;
	}
	
	
}
