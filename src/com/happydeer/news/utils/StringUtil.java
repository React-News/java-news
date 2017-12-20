package com.happydeer.news.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class StringUtil {
	/**
	 * 从request中提取json对象
	 * 
	 * @param request 类型为 HttpServletRequest
	 * @return json对象
	 * @throws JSONException IOException
	 */
	public static JSONObject getJSON(HttpServletRequest request) throws IOException, JSONException {
		String json = null;
		BufferedReader br = new BufferedReader(new InputStreamReader((ServletInputStream) request.getInputStream(), "utf-8"));
		StringBuffer sb = new StringBuffer("");
		String temp;
		while ((temp = br.readLine()) != null) { 
		  sb.append(temp);
		}
		br.close();
		json = sb.toString();
		JSONObject object = new JSONObject(json);
		return object;
	}
	/**
	 * 获取字符串，如不符合条件，赋默认值
	 */
	public static String getString(String s1,String s2) {
		if(null==s1||s1.equals(""))
			return s2;
		return s1;
	}
	
	/**
	 * 分割字符串，为了实现 “将空串忽视并返回一个长度为0的数组”
	 */
	
	public static String[] split(String s) {
		String str = getString(s,"");
		if("".equals(str)) {
			String[] types = {};
			return types;
		}
		return str.split(",");
	}
	
	/**
	 * 合并字符串，组装sql查询条件
	 */
	public static String merge(String[] arr) {
		int len = arr.length;
		String areas = "(";
		for(int i=0;i<len-1;i++)
			areas = areas+"'"+arr[i]+"',";
		areas = areas+"'"+arr[len-1]+"')";
		return areas;
	}
	
}
