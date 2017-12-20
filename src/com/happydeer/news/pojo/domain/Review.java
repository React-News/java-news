package com.happydeer.news.pojo.domain;

import java.io.Serializable;
import java.util.Date;

public class Review implements Serializable {


	private static final long serialVersionUID = 1L;
	private int id;
	private int UID;
	private int NID;
	private String content;
	private int PID;
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
	public int getNID() {
		return NID;
	}
	public void setNID(int nID) {
		NID = nID;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getPID() {
		return PID;
	}
	public void setPID(int pID) {
		PID = pID;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	

}
