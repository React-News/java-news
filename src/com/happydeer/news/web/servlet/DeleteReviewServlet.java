package com.happydeer.news.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.happydeer.news.service.ReviewService;
import com.happydeer.news.service.impl.ReviewServiceImpl;
import com.happydeer.news.utils.JSONResponse;
import com.happydeer.news.utils.StringUtil;

/**
 * Servlet implementation class DeleteReviewServlet
 */
@WebServlet("/deleteComment")
public class DeleteReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteReviewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject object = null;
		int rID = 0;
		try {
			object = StringUtil.getJSON(request);
			rID = object.getInt("rID");
		} catch (NumberFormatException | JSONException e) {
			e.printStackTrace();
		}
		ReviewService reviewService = new ReviewServiceImpl();
		boolean result = reviewService.cancel(rID);
		System.out.println(result);
		JSONResponse jsonResponse = new JSONResponse();
		if(result) {
			jsonResponse.setStatus(JSONResponse.OK);
			jsonResponse.setMsg("删除评论成功");
		}else {
			jsonResponse.setStatus(JSONResponse.CILENTEORR);
			jsonResponse.setMsg("删除评论失败");
		}
		JSONObject data = new JSONObject();
		try {
			data.put("rID", rID);
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
