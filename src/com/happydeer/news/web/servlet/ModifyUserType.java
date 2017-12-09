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
 * Servlet implementation class ModifyUserType
 */
@WebServlet("/editUserType")
public class ModifyUserType extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyUserType() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject object = null;
		int uID = 0;
		String uType = "";
		try {
			object = StringUtil.getJSON(request);
			uID = object.getInt("uID");
			uType = object.getString("uType");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		UserService userService = new UserServiceImpl();
		User user = userService.getInfo(uID);
		user.setType(uType);

		User u = userService.modify(user);
		JSONResponse jsonResponse = new JSONResponse();
		if(u!=null) {
			jsonResponse.setStatus(JSONResponse.OK);
			jsonResponse.setMsg("用户信息修改成功");
		}else {
			jsonResponse.setStatus(JSONResponse.CILENTEORR);
			jsonResponse.setMsg("用户信息修改失败");
		}
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
