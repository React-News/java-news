package com.happydeer.news.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.happydeer.news.pojo.domain.New;
import com.happydeer.news.service.NewService;
import com.happydeer.news.service.impl.NewServiceImpl;
import com.happydeer.news.utils.JSONResponse;
import com.happydeer.news.utils.StringUtil;

/**
 * Servlet implementation class AddNewsServlet
 */
@WebServlet("/addNews")
public class AddNewsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddNewsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject object = null;
		New NEW = new New();
		try {
			object = StringUtil.getJSON(request);
			NEW.setUID(object.getInt("uID"));
			NEW.setTitle(object.getString("nTitle"));
			NEW.setType(object.getString("nType"));
			NEW.setImg(object.getString("nImg"));
			NEW.setContent(object.getString("nContent"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		NewService newService = new NewServiceImpl();
		int result = newService.addNews(NEW);
		JSONResponse jsonResponse = new JSONResponse();
		if(result>0) {
			jsonResponse.setStatus(JSONResponse.OK);
			jsonResponse.setMsg("新闻添加成功");
		}else {
			jsonResponse.setStatus(JSONResponse.SERVEREORR);
			jsonResponse.setMsg("新闻添加失败");
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
