package com.happydeer.news.service;

import java.util.List;

import com.happydeer.news.pojo.datatransfer.NewInfoDto;
import com.happydeer.news.pojo.datatransfer.NewListDownDto;
import com.happydeer.news.pojo.datatransfer.NewListUpDto;
import com.happydeer.news.pojo.domain.New;

public interface NewService {
	int addNews(New NEW);
	int removeNew(int nID);
	New getNewByID(int nID);
	NewInfoDto getNewInfo(int nID);
	List<NewListDownDto> getNewList(NewListUpDto upDto);
	int getTotalSize(NewListUpDto upDto);
}
