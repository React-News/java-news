package com.happydeer.news.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.happydeer.news.service.CollectionService;
import com.happydeer.news.service.impl.CollectionServiceImpl;
import com.happydeer.news.utils.JSONResponse;
import com.happydeer.news.utils.StringUtil;

/**
 * Servlet implementation class DeleteCollectionServlet
 */
@WebServlet("/deleteCollection")
public class DeleteCollectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteCollectionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject object = null;
		int cID = 0;
		try {
			object = StringUtil.getJSON(request);
			cID = Integer.parseInt(object.getString("cID"));
		} catch (NumberFormatException | JSONException e) {
			e.printStackTrace();
		}
		CollectionService collection = new CollectionServiceImpl();
		boolean result = collection.cancel(cID);
		System.out.println(result);
		JSONResponse jsonResponse = new JSONResponse();
		if(result) {
			jsonResponse.setStatus(JSONResponse.OK);
			jsonResponse.setMsg("取消收藏成功");
		}else {
			jsonResponse.setStatus(JSONResponse.CILENTEORR);
			jsonResponse.setMsg("取消收藏失败");
		}
		JSONObject data = new JSONObject();
		try {
			data.put("cID", cID);
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
