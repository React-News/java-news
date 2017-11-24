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
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String telNum = request.getParameter("telnum");
		String passwd = request.getParameter("password");
		UserService userService = new UserServiceImpl();
		User user = userService.register(telNum, passwd);
		JSONResponse jsonResponse = new JSONResponse();
		if(user==null) {
			jsonResponse.setStatus(JSONResponse.CILENTEORR);
			jsonResponse.setMsg("该手机号已注册");
			jsonResponse.setData(null);
		}else {
			jsonResponse.setStatus(JSONResponse.OK);
			jsonResponse.setMsg("注册成功");
			JSONObject data = new JSONObject();
			System.out.println(user.toString());
			try {
				data.put("uID", user.getId());
				data.put("uTelNum", user.getTelnum());
				data.put("passwd", user.getPasswd());
			} catch (JSONException e) {
				e.printStackTrace();
			}
			jsonResponse.setData(data);
		}
		JSONObject object  = jsonResponse.toJSONString();
		response.setCharacterEncoding("utf-8");
		response.getWriter().println(object);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
