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

import com.happydeer.news.pojo.domain.Review;
import com.happydeer.news.service.ReviewService;
import com.happydeer.news.service.impl.ReviewServiceImpl;
import com.happydeer.news.utils.JSONResponse;
import com.happydeer.news.utils.StringUtil;

/**
 * Servlet implementation class MyReviewServlet
 */
@WebServlet("/myComment")
public class MyReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyReviewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int currentPage = Integer.parseInt(StringUtil.getString(request.getParameter("currentPage"),"1"));
		int pageSize = Integer.parseInt(StringUtil.getString(request.getParameter("pageSize"),"-1"));
		int uID = Integer.parseInt(StringUtil.getString(request.getParameter("uID"),"-1"));
		
		ReviewService reviewService = new ReviewServiceImpl();
		List<Review> result = reviewService.myReview(uID, currentPage, pageSize);
		int total = reviewService.getTotalSize();
		
		JSONResponse jsonResponse = new JSONResponse();
		if(null!=result) {
			jsonResponse.setStatus(JSONResponse.OK);
			jsonResponse.setMsg("获取我的评论列表成功");
			JSONObject data = new JSONObject();
			JSONArray datas = new JSONArray();
			try {
				data.put("total", total);
				data.put("currentPage",currentPage);
				data.put("pageSize", pageSize);
				for(Review r:result) {
					JSONObject review = new JSONObject();
					review.put("rID", r.getId());
					review.put("nID", r.getNID());
					review.put("rContent", r.getContent());
					review.put("rTime", r.getTime().getTime());
					datas.put(review);
				}
				data.put("list", datas);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			jsonResponse.setData(data);
		}else {
			jsonResponse.setStatus(JSONResponse.SERVEREORR);
			jsonResponse.setMsg("获取我的评论失败！");
			jsonResponse.setData(new JSONObject());
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
