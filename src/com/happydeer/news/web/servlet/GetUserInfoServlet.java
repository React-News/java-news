package com.happydeer.news.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.happydeer.news.domain.User;
import com.happydeer.news.service.UserService;
import com.happydeer.news.service.impl.UserServiceImpl;
import com.happydeer.news.utils.JSONResponse;

/**
 * Servlet implementation class GetUserInfo
 */
@WebServlet("/getUserInfo")
public class GetUserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetUserInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int uID = Integer.parseInt(request.getParameter("uID"));
		UserService userService = new UserServiceImpl();
		User user = userService.getInfo(uID);
		JSONObject object = new JSONObject();
		try {
			object.put("uID", user.getId());
			object.put("uName", user.getName());
			object.put("uTelNum", user.getTelnum());
			object.put("uAvatar", user.getAvatar());
			object.put("uSex", user.getSex());
			object.put("uAge", user.getAge());
			object.put("uDescribe",user.getDescribe());
			object.put("uAuthority", user.getType());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		JSONResponse jsonResponse = new JSONResponse();
		jsonResponse.setStatus(JSONResponse.OK);
		jsonResponse.setMsg("获取用户信息成功");
		jsonResponse.setData(object);
		response.setContentType("text/json;charset=utf-8");
		response.getWriter().println(jsonResponse.toJSONString(JSONResponse.ONE));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
