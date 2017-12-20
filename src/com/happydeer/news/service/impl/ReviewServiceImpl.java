package com.happydeer.news.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.happydeer.news.dao.ReviewDao;
import com.happydeer.news.dao.UserDao;
import com.happydeer.news.dao.impl.ReviewDaoImpl;
import com.happydeer.news.dao.impl.UserDaoImpl;
import com.happydeer.news.pojo.datatransfer.CreaterInfo;
import com.happydeer.news.pojo.datatransfer.ReviewListDownDto;
import com.happydeer.news.pojo.datatransfer.ReviewListUpDto;
import com.happydeer.news.pojo.domain.Review;
import com.happydeer.news.pojo.domain.User;
import com.happydeer.news.service.ReviewService;

public class ReviewServiceImpl implements ReviewService {
	private static ReviewDao reviewDao = new ReviewDaoImpl();
	private static UserDao userDao = new UserDaoImpl();
	private int totalSize=0;
	@Override
	public boolean review(Review r) {
		int result = reviewDao.addReview(r);
		if(result>0)return true;
		return false;
	}

	@Override
	public boolean cancel(int id) {
		int result = reviewDao.removeReview(id);
		if(result>0)return true;
		return false;
	}

	@Override
	public List<ReviewListDownDto> allReview(ReviewListUpDto upDto) {
		List<Review> list = reviewDao.queryReviewListByNID(upDto.getNID());
		List<Review> plist;
		List<ReviewListDownDto> result = new ArrayList<>();
		List<ReviewListDownDto> presult; 
		for(Review r:list) {
			ReviewListDownDto downDto = new ReviewListDownDto();
			downDto.setId(r.getId());
			downDto.setContent(r.getContent());
			downDto.setTime(r.getTime());
			User u = userDao.findUserById(r.getUID());
			CreaterInfo createrInfo = new CreaterInfo();
			createrInfo.setId(u.getId());
			createrInfo.setAvatar(u.getAvatar());
			createrInfo.setName(u.getName());
			downDto.setCreaterInfo(createrInfo);
			plist = reviewDao.queryPReviewListByRID(r.getId());
			presult = new ArrayList<>();
			for(Review pr:plist) {
				ReviewListDownDto pdownDto = new ReviewListDownDto();
				pdownDto.setId(pr.getId());
				pdownDto.setContent(pr.getContent());
				pdownDto.setTime(pr.getTime());
				User pu = userDao.findUserById(pr.getUID());
				CreaterInfo pcreaterInfo = new CreaterInfo();
				pcreaterInfo.setId(pu.getId());
				pcreaterInfo.setAvatar(pu.getAvatar());
				pcreaterInfo.setName(pu.getName());
				pdownDto.setCreaterInfo(pcreaterInfo);
				presult.add(pdownDto);
			}
			downDto.setrReview(presult);
			result.add(downDto);
		}
		return result;
	}

	@Override
	public int getTotalSize(ReviewListUpDto upDto) {
		return reviewDao.countByNID(upDto.getNID());
	}

	@Override
	public List<Review> myReview(int uID, int curr, int size) {
		List<Review> result = reviewDao.queryMyReview(uID);
		List<Review> last = new ArrayList<>();
		System.out.println(curr+" "+size);
		int from = (curr-1)*size;
		int to = curr*size;
		int len = result.size();
		totalSize = result.size();
		try {
			last = result.subList(from, to);
		} catch (IndexOutOfBoundsException e) {
			if(from>=len) {}
			else if(from<len)last=result.subList(from, len);
			e.printStackTrace();
		}
		System.out.println("from:"+from+" to:"+to+last.size());
		return last;
	}
	@Override
	public int getTotalSize() {
		return totalSize;
	}
}
