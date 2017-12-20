package com.happydeer.news.service;

import java.util.List;

import com.happydeer.news.pojo.datatransfer.ReviewListDownDto;
import com.happydeer.news.pojo.datatransfer.ReviewListUpDto;
import com.happydeer.news.pojo.domain.Review;

public interface ReviewService {
	
	public boolean review(Review r);
	public boolean cancel(int id);
	public List<ReviewListDownDto> allReview(ReviewListUpDto upDto);
	public int getTotalSize(ReviewListUpDto upDto);
	
	public List<Review> myReview(int uID,int curr,int size);
	public int getTotalSize();
}
