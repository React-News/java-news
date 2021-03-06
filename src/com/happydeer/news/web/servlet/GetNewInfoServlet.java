package com.happydeer.news.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.happydeer.news.pojo.datatransfer.CreaterInfo;
import com.happydeer.news.pojo.datatransfer.NewInfoDto;
import com.happydeer.news.service.NewService;
import com.happydeer.news.service.impl.NewServiceImpl;
import com.happydeer.news.utils.JSONResponse;
import com.happydeer.news.utils.StringUtil;
import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * Servlet implementation class GetNewInfoServlet
 */
@WebServlet("/newsDetail")
public class GetNewInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetNewInfoServlet() {
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
		NewInfoDto dto = newService.getNewInfo(nID);
		JSONResponse jsonResponse = new JSONResponse();
		if(null!=dto) {
			jsonResponse.setStatus(JSONResponse.OK);
			jsonResponse.setMsg("查询新闻信息成功");
			JSONObject data = new JSONObject();
			try {
				data.put("nID",dto.getId());
				data.put("nTitle", dto.getTitle());
				data.put("nType", dto.getType());
				data.put("nImg", dto.getImg());
				data.put("nContent", dto.getContent());
				data.put("nCreatedAt", dto.getTime());
				data.put("commentNum", dto.getReviewSize());
				data.put("starNum", dto.getCollectionSize());
				CreaterInfo createrInfo = dto.getCreaterInfo();
				JSONObject obj = new JSONObject();
				obj.put("uID",createrInfo.getId());
				obj.put("uName", createrInfo.getName());
				obj.put("uAvatar", createrInfo.getAvatar());
				data.put("nCreaterInfo", createrInfo);
			} catch (JSONException e) {
				e.printStackTrace();
			}
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
