package com.happydeer.news.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.happydeer.news.pojo.datatransfer.UserListDownDto;
import com.happydeer.news.pojo.datatransfer.UserListUpDto;
import com.happydeer.news.service.UserService;
import com.happydeer.news.service.impl.UserServiceImpl;
import com.happydeer.news.utils.JSONResponse;
import com.happydeer.news.utils.StringUtil;

/**
 * Servlet implementation class UserListServlet
 */
@WebServlet("/users")
public class UserListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("begin");
		int total = 0;
		int currentPage = Integer.parseInt(StringUtil.getString(request.getParameter("currentPage"),"1"));
		int pageSize = Integer.parseInt(StringUtil.getString(request.getParameter("pageSize"),"-1"));
		//构造查询对象
		UserListUpDto upDto = new UserListUpDto();
		upDto.setKeywd(StringUtil.getString(request.getParameter("keywd"),""));
		upDto.setSex(StringUtil.split(request.getParameter("uSex")));
		upDto.setTypes(StringUtil.split(request.getParameter("uType")));
		upDto.setCurrentPage(currentPage);
		upDto.setPageSize(pageSize);
		System.out.println(upDto.toString());
		//调用服务
		UserService userService = new UserServiceImpl();
		List<UserListDownDto> list = userService.users(upDto);
		total = userService.countByDto(upDto);
		
		//组装 json响应数据
		JSONResponse jsonResponse = new JSONResponse();
		jsonResponse.setStatus(JSONResponse.OK);
		jsonResponse.setMsg("获取用户列表成功");
		JSONObject data = new JSONObject();
		JSONArray datas = new JSONArray();
		try {
			data.put("total", total);
			data.put("currentPage",currentPage);
			data.put("pageSize", pageSize);
			for(UserListDownDto downDto:list) {
				JSONObject object  = new JSONObject();
				object.put("uID", downDto.getId());
				object.put("uName", downDto.getName());
				object.put("uAvatar", downDto.getAvatar());
				object.put("uSex", downDto.getSex());
				object.put("uAge", downDto.getAge());
				object.put("uTelNum", downDto.getTelNum());
				object.put("uType", downDto.getType());
				datas.put(object);
			}
			data.put("list", datas);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		jsonResponse.setData(data);
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
