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

import com.happydeer.news.pojo.datatransfer.NewListDownDto;
import com.happydeer.news.pojo.datatransfer.NewListUpDto;
import com.happydeer.news.service.NewService;
import com.happydeer.news.service.impl.NewServiceImpl;
import com.happydeer.news.utils.JSONResponse;
import com.happydeer.news.utils.StringUtil;

/**
 * Servlet implementation class NewsList
 */
@WebServlet("/newsList")
public class NewsListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewsListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//构造请求数据对象
		NewListUpDto upDto = new NewListUpDto();
		upDto.setUID(Integer.parseInt(StringUtil.getString(request.getParameter("uID"),"-1")));
		upDto.setCurrentPage(Integer.parseInt(StringUtil.getString(request.getParameter("currentPage"),"1")));
		upDto.setPageSize(Integer.parseInt(StringUtil.getString(request.getParameter("pageSize"),"-1")));
		upDto.setKeywd(StringUtil.getString(request.getParameter("keywd"),""));
		upDto.setTypes(StringUtil.getString(request.getParameter("nType"),"").split(","));
		System.out.println(upDto.toString());
		//调用服务层返回新闻列表
		NewService newService = new NewServiceImpl();
		List<NewListDownDto> list = newService.getNewList(upDto);
		//创建json响应体
		JSONResponse jsonResponse = new JSONResponse();
		jsonResponse.setStatus(JSONResponse.OK);
		jsonResponse.setMsg("获取新闻列表成功");
		JSONArray datas = new JSONArray();
		try {
			for(NewListDownDto downDto:list) {
				JSONObject object  = new JSONObject();
				object.put("nID", downDto.getNID());
				object.put("nTtile", downDto.getTitle());
				object.put("createdAt", downDto.getCreatedAt());
				JSONObject object2 = new JSONObject();
				object2.put("uID", downDto.getCreaterInfo().getUID());
				object2.put("uName", downDto.getCreaterInfo().getName());
				object2.put("uAvatar", downDto.getCreaterInfo().getAvatar());
				object.put("createrInfo", object2);
				datas.put(object);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		jsonResponse.setDatas(datas);
		response.setContentType("text/json;charset=utf-8");
		response.getWriter().println(jsonResponse.toJSONString(JSONResponse.MORE));
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
