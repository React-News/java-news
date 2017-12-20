package com.happydeer.news.service;

import java.util.List;

import com.happydeer.news.pojo.datatransfer.NewInfoDto;
import com.happydeer.news.pojo.datatransfer.NewListUpDto;

public interface CollectionService {
	public boolean collection(int uID,int nID);
	public boolean cancel(int id);
	public List<NewInfoDto> queryMore(NewListUpDto dto);
	public int getTotalSize();
}
