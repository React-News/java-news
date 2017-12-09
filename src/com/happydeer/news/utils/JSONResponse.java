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
	
	/**
	 * 相应数据结构，json对象或数组
	 */
	public static final String ONE = "OBJECT";
	public static final String MORE = "ARRAY";
	public static final String NONE = "ZERO";
	
	private String status;
	private String msg;
	private JSONObject data;
	private JSONObject[] datas;
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
	public JSONObject[] getDatas() {
		return datas;
	}
	public void setDatas(JSONObject[] datas) {
		this.datas = datas;
	}
	public JSONObject toJSONString(String type) {
		JSONObject object = new JSONObject();
		try {
			object.put("status", this.getStatus());
			object.put("msg", this.getMsg());
			if(ONE.equals(type))
			object.put("data", this.getData());
			else if(MORE.equals(type))
			object.put("data", this.getDatas());	
			else if(NONE.equals(type));
		} catch (JSONException e) {
			e.printStackTrace();
			System.out.println("json响应创建失败！！！");
		}
		return object;
	}
	
}
