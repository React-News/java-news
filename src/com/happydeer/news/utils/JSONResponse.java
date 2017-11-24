package com.happydeer.news.utils;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONResponse {
	
	/**
	 * HTTP状态码
	 */
	public static final String OK = "200";
	public static final String CILENTEORR = "400";
	public static final String NOTFOUNT = "404";
	public static final String DENIED = "401";
	public static final String REDIRECT = "200";
	public static final String SERVEREORR = "500";
	
	private String status;
	private String msg;
	private JSONObject data;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public JSONObject getData() {
		return data;
	}
	public void setData(JSONObject data) {
		this.data = data;
	}
	
	public JSONObject toJSONString() {
		JSONObject object = new JSONObject();
		try {
			object.put("status", this.getStatus());
			object.put("msg", this.getMsg());
			object.put("data", this.getData());
		} catch (JSONException e) {
			e.printStackTrace();
			System.out.println("json响应创建失败！！！");
		}
		return object;
	}
	
}
