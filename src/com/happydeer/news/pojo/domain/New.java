package com.happydeer.news.pojo.domain;

import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;

public class New implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private int UID;
	private String title;
	private String type;
	private String img;
	private String content;
	private Date time;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUID() {
		return UID;
	}
	public void setUID(int uID) {
		UID = uID;
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
		return new Date();
	}
	public void setTime(Date time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "New [id=" + id + ", UID=" + UID + ", title=" + title + ", type=" + type + ", img=" + img + ", content="
				+ content + ", time=" + time + "]";
	}

	
	
}
