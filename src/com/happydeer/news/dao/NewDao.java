package com.happydeer.news.dao;

import java.util.List;

import com.happydeer.news.pojo.datatransfer.NewListUpDto;
import com.happydeer.news.pojo.domain.New;

public interface NewDao {
	
	int addNew(New NEW);
	
	int removeNew(int nID);
	
	List<New> queryAll();
	
	List<New> queryAllByDto(NewListUpDto upDto);
	int countByDto(NewListUpDto upDto);
	
	New queryNewByID(int id);
}
