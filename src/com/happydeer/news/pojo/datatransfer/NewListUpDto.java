package com.happydeer.news.pojo.datatransfer;

import java.util.Arrays;

public class NewListUpDto {
	private int UID;
	private int currentPage;
	private int pageSize;
	private String keywd;
	private String[] types;

	public int getUID() {
		return UID;
	}
	public void setUID(int uID) {
		UID = uID;
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
	public String getKeywd() {
		return keywd;
	}
	public void setKeywd(String keywd) {
		this.keywd = keywd;
	}
	public String[] getTypes() {
		return types;
	}
	public void setTypes(String[] types) {
		this.types = types;
	}
	@Override
	public String toString() {
		return "NewListUpDto [UID=" + UID + ", currentPage=" + currentPage + ", pageSize=" + pageSize + ", keywd="
				+ keywd + ", types=" + Arrays.toString(types) + "]";
	}

	
}
