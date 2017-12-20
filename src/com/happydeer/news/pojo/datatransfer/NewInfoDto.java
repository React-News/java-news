package com.happydeer.news.pojo.datatransfer;

import java.util.Date;

public class NewInfoDto{
	private int id;
	private int CID;
	private String title;
	private String type;
	private String img;
	private String content;
	private Date time;
	private int reviewSize;
	private int collectionSize;
	private CreaterInfo createrInfo;
	
	public int getCID() {
		return CID;
	}

	public void setCID(int cID) {
		CID = cID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public int getReviewSize() {
		return reviewSize;
	}

	public void setReviewSize(int reviewSize) {
		this.reviewSize = reviewSize;
	}

	public int getCollectionSize() {
		return collectionSize;
	}

	public void setCollectionSize(int collectionSize) {
		this.collectionSize = collectionSize;
	}

	public CreaterInfo getCreaterInfo() {
		return createrInfo;
	}

	public void setCreaterInfo(CreaterInfo createrInfo) {
		this.createrInfo = createrInfo;
	}


}
