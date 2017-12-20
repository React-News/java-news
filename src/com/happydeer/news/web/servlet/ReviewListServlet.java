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

import com.happydeer.news.pojo.datatransfer.ReviewListDownDto;
import com.happydeer.news.pojo.datatransfer.ReviewListUpDto;
import com.happydeer.news.service.ReviewService;
import com.happydeer.news.service.impl.ReviewServiceImpl;
import com.happydeer.news.utils.JSONResponse;
import com.happydeer.news.utils.StringUtil;

/**
 * Servlet implementation class ReviewListServlet
 */
@WebServlet("/commentList")
public class ReviewListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int currentPage = Integer.parseInt(StringUtil.getString(request.getParameter("currentPage"),"1"));
		int pageSize = Integer.parseInt(StringUtil.getString(request.getParameter("pageSize"),"-1"));
		ReviewListUpDto upDto = new ReviewListUpDto();
		upDto.setNID(Integer.parseInt(StringUtil.getString(request.getParameter("nID"),"-1")));
		upDto.setCurrentPage(currentPage);
		upDto.setPageSize(pageSize);
		System.out.println("servlet:"+request.getParameter("nID"));
		ReviewService reviewService = new ReviewServiceImpl();
		List<ReviewListDownDto> list = reviewService.allReview(upDto);
		int total = reviewService.getTotalSize(upDto);
		//创建json响应体
		JSONResponse jsonResponse = new JSONResponse();
		jsonResponse.setStatus(JSONResponse.OK);
		jsonResponse.setMsg("获取新闻评论列表成功");
		JSONObject data = new JSONObject();
		JSONArray datas = new JSONArray();
		try {
			data.put("total", total);
			data.put("currentPage",currentPage);
			data.put("pageSize", pageSize);
			for(ReviewListDownDto downDto:list) {
				System.out.println(downDto.toString());
				JSONObject object1  = new JSONObject();
				object1.put("rID", downDto.getId());
				object1.put("rContent", downDto.getContent());
				object1.put("rTime", downDto.getTime().getTime());
				JSONObject object2 = new JSONObject();
				object2.put("uID", downDto.getCreaterInfo().getId());
				object2.put("uName", downDto.getCreaterInfo().getName());
				object2.put("uAvatar", downDto.getCreaterInfo().getAvatar());
				object1.put("createrInfo", object2);
				List<ReviewListDownDto> plist = downDto.getrReview();
				JSONArray pdatas = new JSONArray();
				System.out.println(plist.size());
				for(ReviewListDownDto pdownDto:plist) {
					System.out.println(pdownDto.toString());
					JSONObject pobject1  = new JSONObject();
					pobject1.put("rID", pdownDto.getId());
					pobject1.put("rContent", pdownDto.getContent());
					pobject1.put("rTime", pdownDto.getTime().getTime());
					JSONObject pobject2 = new JSONObject();
					pobject2.put("uID", pdownDto.getCreaterInfo().getId());
					pobject2.put("uName", pdownDto.getCreaterInfo().getName());
					pobject2.put("uAvatar", pdownDto.getCreaterInfo().getAvatar());
					pobject1.put("createrInfo", pobject2);
					pdatas.put(pobject1);
				}
				object1.put("pList", pdatas);
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
