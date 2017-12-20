package com.happydeer.news.pojo.datatransfer;

import java.util.Date;

import com.happydeer.news.pojo.domain.User;

public class NewListDownDto {
	private int NID;
	private String title;
	private String type;
	private String img;
	private long createdAt;
	private CreaterInfo createrInfo; 
	
	public int getNID() {
		return NID;
	}

	public void setNID(int nID) {
		NID = nID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public void setCreatedAt(long createdAt) {
		this.createdAt = createdAt;
	}

	public long getCreatedAt() {
		return createdAt;
	}

	public CreaterInfo getCreaterInfo() {
		return createrInfo;
	}

	public void setCreaterInfo(CreaterInfo createrInfo) {
		this.createrInfo = createrInfo;
	}

}
