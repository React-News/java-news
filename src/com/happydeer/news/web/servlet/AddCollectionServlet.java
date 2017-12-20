package com.happydeer.news.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.happydeer.news.pojo.domain.Collection;
import com.happydeer.news.service.CollectionService;
import com.happydeer.news.service.NewService;
import com.happydeer.news.service.impl.CollectionServiceImpl;
import com.happydeer.news.service.impl.NewServiceImpl;
import com.happydeer.news.utils.JSONResponse;
import com.happydeer.news.utils.StringUtil;

/**
 * Servlet implementation class AddCollectionServlet
 */
@WebServlet("/addCollection")
public class AddCollectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCollectionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject object = null;
		int uID = 0,nID = 0;
		try {
			object = StringUtil.getJSON(request);
			uID = object.getInt("uID");
			nID = object.getInt("nID");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		CollectionService collectionService = new CollectionServiceImpl();
		boolean result = collectionService.collection(uID, nID);
		System.out.println(result);
		JSONResponse jsonResponse = new JSONResponse();
		if(result) {
			jsonResponse.setStatus(JSONResponse.OK);
			jsonResponse.setMsg("收藏添加成功");
		}else {
			jsonResponse.setStatus(JSONResponse.SERVEREORR);
			jsonResponse.setMsg("收藏添加失败");
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
