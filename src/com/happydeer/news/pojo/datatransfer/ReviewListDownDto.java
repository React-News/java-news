package com.happydeer.news.pojo.datatransfer;

import java.util.Date;
import java.util.List;

public class ReviewListDownDto {
	private int id;
	private String content;
	private Date time;
	private CreaterInfo createrInfo;
	private List<ReviewListDownDto> rReview;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public CreaterInfo getCreaterInfo() {
		return createrInfo;
	}
	public void setCreaterInfo(CreaterInfo createrInfo) {
		this.createrInfo = createrInfo;
	}
	public List<ReviewListDownDto> getrReview() {
		return rReview;
	}
	public void setrReview(List<ReviewListDownDto> rReview) {
		this.rReview = rReview;
	}
	@Override
	public String toString() {
		return "ReviewListDownDto [id=" + id + ", content=" + content + ", time=" + time + ", createrInfo="
				+ createrInfo + ", rReview=" + rReview + "]";
	}
	
}
