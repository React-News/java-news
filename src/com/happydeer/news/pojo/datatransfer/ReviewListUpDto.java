package com.happydeer.news.pojo.datatransfer;

public class ReviewListUpDto {
	private int NID;
	private int currentPage;
	private int pageSize;

	public int getNID() {
		return NID;
	}
	public void setNID(int nID) {
		NID = nID;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
}
