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
import com.happydeer.news.utils.StringUtil;

import javafx.print.JobSettings;

/**
 * Servlet implementation class ModifyUserInfoServlet
 */
@WebServlet("/editUserInfo")
public class ModifyUserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyUserInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject object = null;
		int uID = 0;
		String uName = "";
		String uSex = "";
		int uAge = 0;
		String uAvatar = "";
		String uDescribe = "";
		try {
			object = StringUtil.getJSON(request);
			uID = object.getInt("uID");
			uName = object.getString("uName");
			uSex = object.getString("uSex");
			uAge = object.getInt("uAge");
			uAvatar = object.getString("uAvatar");
			uDescribe = object.getString("uDescribe");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		UserService userService = new UserServiceImpl();
		User user = userService.getInfo(uID);
		user.setName(uName);
		user.setSex(uSex);
		user.setAge(uAge);
		user.setAvatar(uAvatar);
		user.setDescribe(uDescribe);
		User u = userService.modify(user);
		JSONResponse jsonResponse = new JSONResponse();
		if(u!=null) {
			jsonResponse.setStatus(JSONResponse.OK);
			jsonResponse.setMsg("用户信息修改成功");
			JSONObject data = new JSONObject();
			try {
				data.put("uID", u.getId());
				data.put("uName", u.getName());
				data.put("uTelNum", u.getTelnum());
				data.put("uAvatar", u.getAvatar());
				data.put("uSex", u.getSex());
				data.put("uAge", u.getAge());
				data.put("uDescribe", u.getDescribe());
				data.put("uAuthority", u.getType());
			} catch (JSONException e) {
				e.printStackTrace();
			}
			jsonResponse.setData(data);
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
