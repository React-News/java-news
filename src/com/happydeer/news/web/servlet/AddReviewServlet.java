package com.happydeer.news.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.happydeer.news.pojo.domain.Review;
import com.happydeer.news.service.ReviewService;
import com.happydeer.news.service.impl.ReviewServiceImpl;
import com.happydeer.news.utils.JSONResponse;
import com.happydeer.news.utils.StringUtil;

/**
 * Servlet implementation class AddReviewServlet
 */
@WebServlet("/addComment")
public class AddReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddReviewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONResponse jsonResponse = new JSONResponse();
		JSONObject object = null;
		Review review = new Review();
		int uID = 0,nID = 0;
		try {
			object = StringUtil.getJSON(request);
			review.setNID(object.getInt("nID"));
			review.setUID(object.getInt("uID"));
			review.setContent(object.getString("rContent"));
			review.setPID(Integer.parseInt(StringUtil.getString(object.getString("prID"),"-1")));
		} catch (JSONException e) {
			jsonResponse.setStatus(JSONResponse.CILENTEORR);
			jsonResponse.setMsg("非法json");
			e.printStackTrace();
		}
		ReviewService reviewService = new ReviewServiceImpl();
		boolean result = reviewService.review(review);
		System.out.println(result);
		
		if(result) {
			jsonResponse.setStatus(JSONResponse.OK);
			jsonResponse.setMsg("评论成功");
		}else {
			jsonResponse.setStatus(JSONResponse.SERVEREORR);
			jsonResponse.setMsg("评论失败");
		}
		response.setContentType("text/json;charset=utf-8");
		response.getWriter().println(jsonResponse.toJSONString(JSONResponse.NONE));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
