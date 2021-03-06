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
		int currentPage = Integer.parseInt(StringUtil.getString(request.getParameter("currentPage"),"1"));
		int pageSize = Integer.parseInt(StringUtil.getString(request.getParameter("pageSize"),"-1"));
		NewListUpDto upDto = new NewListUpDto();
		upDto.setUID(Integer.parseInt(StringUtil.getString(request.getParameter("uID"),"-1")));
		upDto.setCurrentPage(currentPage);
		upDto.setPageSize(pageSize);
		upDto.setKeywd(StringUtil.getString(request.getParameter("keywd"),""));
		upDto.setTypes(StringUtil.split(request.getParameter("nType")));
		System.out.println(upDto.toString());
		//调用服务层返回新闻列表
		NewService newService = new NewServiceImpl();
		List<NewListDownDto> list = newService.getNewList(upDto);
		int total = newService.getTotalSize(upDto);
		//创建json响应体
		JSONResponse jsonResponse = new JSONResponse();
		jsonResponse.setStatus(JSONResponse.OK);
		jsonResponse.setMsg("获取新闻列表成功");
		JSONObject data = new JSONObject();
		JSONArray datas = new JSONArray();
		try {
			data.put("total", total);
			data.put("currentPage",currentPage);
			data.put("pageSize", pageSize);
			for(NewListDownDto downDto:list) {
				JSONObject object1  = new JSONObject();
				object1.put("nID", downDto.getNID());
				object1.put("nTitle", downDto.getTitle());
				object1.put("nType", downDto.getType());
				object1.put("nImg", downDto.getImg());
				object1.put("nCreatedAt", downDto.getCreatedAt());
				JSONObject object2 = new JSONObject();
				object2.put("uID", downDto.getCreaterInfo().getId());
				object2.put("uName", downDto.getCreaterInfo().getName());
				object2.put("uAvatar", downDto.getCreaterInfo().getAvatar());
				object1.put("createrInfo", object2);
				datas.put(object1);
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
