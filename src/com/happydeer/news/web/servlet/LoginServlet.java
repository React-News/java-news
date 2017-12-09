package com.happydeer.news.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.happydeer.news.pojo.domain.User;
import com.happydeer.news.service.UserService;
import com.happydeer.news.service.impl.UserServiceImpl;
import com.happydeer.news.utils.JSONResponse;
import com.happydeer.news.utils.StringUtil;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		JSONObject object = null;
		String uTelNum = "";
		String uPasswd = "";
		try {
			object = StringUtil.getJSON(request);
			uTelNum = object.getString("uTelNum");
			uPasswd = object.getString("uPasswd");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		UserService userService = new UserServiceImpl();

		JSONResponse jsonResponse = new JSONResponse();
		jsonResponse.setStatus(JSONResponse.OK);
		if ("".equals(uPasswd) && "".equals(uTelNum)) {
			jsonResponse.setMsg("无效数据");
		} else {
			User user = userService.login(uTelNum, uPasswd);
			if (null != user) {
				jsonResponse.setMsg("登录成功");
				JSONObject obj = new JSONObject();
				try {
					obj.put("uID", user.getId());
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				jsonResponse.setData(obj);
			} else {
				jsonResponse.setMsg("电话或密码错误");
			}
		}
		response.setContentType("text/json;charset=utf-8");
		response.getWriter().println(jsonResponse.toJSONString(JSONResponse.ONE));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
