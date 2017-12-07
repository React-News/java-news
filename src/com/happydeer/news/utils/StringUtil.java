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
	
}
