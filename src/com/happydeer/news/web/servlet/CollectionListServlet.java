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

import com.happydeer.news.pojo.datatransfer.NewInfoDto;
import com.happydeer.news.pojo.datatransfer.NewListUpDto;
import com.happydeer.news.service.CollectionService;
import com.happydeer.news.service.impl.CollectionServiceImpl;
import com.happydeer.news.utils.JSONResponse;
import com.happydeer.news.utils.StringUtil;

/**
 * Servlet implementation class CollectionListServlet
 */
@WebServlet("/collectionList")
public class CollectionListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CollectionListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int currentPage = Integer.parseInt(StringUtil.getString(request.getParameter("currentPage"),"1"));
		int pageSize = Integer.parseInt(StringUtil.getString(request.getParameter("pageSize"),"-1"));
		NewListUpDto upDto = new NewListUpDto();
		upDto.setUID(Integer.parseInt(StringUtil.getString(request.getParameter("uID"),"-1")));
		upDto.setCurrentPage(currentPage);
		upDto.setPageSize(pageSize);
		upDto.setKeywd(StringUtil.getString(request.getParameter("keywd"),""));
		upDto.setTypes(StringUtil.split(request.getParameter("nType")));
		System.out.println(upDto.toString());
		CollectionService collectionService = new CollectionServiceImpl();
		List<NewInfoDto> result = collectionService.queryMore(upDto);
		int total = collectionService.getTotalSize();
		JSONResponse jsonResponse = new JSONResponse();
		if(null!=result) {
			jsonResponse.setStatus(JSONResponse.OK);
			jsonResponse.setMsg("获取用户收藏列表成功");
			JSONObject data = new JSONObject();
			JSONArray datas = new JSONArray();
			try {
				data.put("total", total);
				data.put("currentPage",currentPage);
				data.put("pageSize", pageSize);
				for(NewInfoDto dto:result) {
					JSONObject collection  = new JSONObject();
					collection.put("cID", dto.getCID());
					JSONObject newInfo  = new JSONObject();
					newInfo.put("nID", dto.getId());
					newInfo.put("nTitle", dto.getTitle());
					newInfo.put("nType", dto.getType());
					newInfo.put("nImg", dto.getImg());
					newInfo.put("nCreatedAt", dto.getTime().getTime());
					collection.put("newsInfo", newInfo);
					JSONObject createrInfo = new JSONObject();
					createrInfo.put("uID", dto.getCreaterInfo().getId());
					createrInfo.put("uName", dto.getCreaterInfo().getName());
					createrInfo.put("uAvatar", dto.getCreaterInfo().getAvatar());
					collection.put("createrInfo", createrInfo);
					collection.put("commentNum", dto.getReviewSize());
					collection.put("starNum", dto.getCollectionSize());
					datas.put(collection);
				}
				data.put("list", datas);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			jsonResponse.setData(data);
		}else {
			jsonResponse.setStatus(JSONResponse.SERVEREORR);
			jsonResponse.setMsg("获取收藏列表失败！");
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
