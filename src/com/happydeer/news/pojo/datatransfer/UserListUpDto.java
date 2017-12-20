package com.happydeer.news.pojo.datatransfer;

import java.util.Arrays;

public class UserListUpDto {
	private String keywd;
	private String[] sex;
	private String[] types;
	private int pageSize;
	private int currentPage;
	
	public String getKeywd() {
		return keywd;
	}
	public void setKeywd(String keywd) {
		this.keywd = keywd;
	}
	public String[] getSex() {
		return sex;
	}
	public void setSex(String[] sex) {
		this.sex = sex;
	}
	public String[] getTypes() {
		return types;
	}
	public void setTypes(String[] types) {
		this.types = types;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	@Override
	public String toString() {
		return "UserListUpDto [keywd=" + keywd + ", sex=" + Arrays.toString(sex) + ", types=" + Arrays.toString(types)
				+ ", pageSize=" + pageSize + ", currentPage=" + currentPage + "]";
	}
	
}
