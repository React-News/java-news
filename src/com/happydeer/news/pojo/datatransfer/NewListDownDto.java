package com.happydeer.news.pojo.datatransfer;

import java.util.Date;

import com.happydeer.news.pojo.domain.User;

public class NewListDownDto {
	private int NID;
	private String title;
	private String createdAt;
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

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public CreaterInfo getCreaterInfo() {
		return createrInfo;
	}

	public void setCreaterInfo(CreaterInfo createrInfo) {
		this.createrInfo = createrInfo;
	}

	public class CreaterInfo{
		private int UID;
		private String name;
		private String avatar;
		public int getUID() {
			return UID;
		}
		public void setUID(int uID) {
			UID = uID;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getAvatar() {
			return avatar;
		}
		public void setAvatar(String avatar) {
			this.avatar = avatar;
		}
		
	}
}
