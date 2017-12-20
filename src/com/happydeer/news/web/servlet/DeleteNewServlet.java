package com.happydeer.news.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.happydeer.news.service.NewService;
import com.happydeer.news.service.impl.NewServiceImpl;
import com.happydeer.news.utils.JSONResponse;
import com.happydeer.news.utils.StringUtil;

/**
 * Servlet implementation class DeleteNewServlet
 */
@WebServlet("/deleteNews")
public class DeleteNewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteNewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject object = null;
		int nID = 0;
		try {
			object = StringUtil.getJSON(request);
			nID = Integer.parseInt(object.getString("nID"));
		} catch (NumberFormatException | JSONException e) {
			e.printStackTrace();
		}
		NewService newService = new NewServiceImpl();
		int result = newService.removeNew(nID);
		System.out.println(result);
		JSONResponse jsonResponse = new JSONResponse();
		if(result>0) {
			jsonResponse.setStatus(JSONResponse.OK);
			jsonResponse.setMsg("删除新闻成功");
		}else {
			jsonResponse.setStatus(JSONResponse.CILENTEORR);
			jsonResponse.setMsg("删除新闻失败");
		}
		JSONObject data = new JSONObject();
		try {
			data.put("nID", nID);
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
