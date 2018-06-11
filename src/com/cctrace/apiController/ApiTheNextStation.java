package com.cctrace.apiController;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cctrace.entity.BindTable;
import com.cctrace.entity.QueryVo;
import com.cctrace.entity.TheNextStation;
import com.cctrace.entity.User;
import com.cctrace.service.DaoService;
import com.cctrace.utils.BaseResult;
import com.cctrace.utils.ConstantCode;
import com.cctrace.utils.JsonUtils;

@Controller
@RequestMapping(value = "/apiUser")
public class ApiTheNextStation {
	@Autowired
	private DaoService daoService;

	public void setDaoService(DaoService daoService) {
		this.daoService = daoService;
	}

	/**
	 * 展示信息
	 * 
	 */
	@RequestMapping(value = "/showTheNextStation")
	@ResponseBody
	public List<QueryVo> showTheNextStation(HttpServletRequest request)
			throws IOException {
		String username = request.getParameter("username");
		User user = daoService.getUserByUsername(username);

		List<QueryVo> queryVos = null;
		if (daoService.showStationInformation(user.getCompanyId()) != null) {
			queryVos = daoService.showStationInformation(user.getCompanyId());
//			System.out.println(queryVos.size());
		}
		return queryVos;
	}

	/**
	 * 
	 * @param 传入用户的username
	 *            ,stationName 判断权限，只有管理员能进行增操作
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/addTheNextStation")
	@ResponseBody
	public void addTheNextStation(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String username = request.getParameter("username");
		User user = daoService.getUserByUsername(username);

//		List<QueryVo> queryVos = null;
		String mess = "";
		int flag = ConstantCode.ERROR;
		JSONObject jo = new JSONObject();

		if (user.getRole().equals("common")) {
			mess = "您的权限不够";
		} else {
			String stationName = request.getParameter("stationName");
			TheNextStation nextStation = new TheNextStation();
			nextStation.setCompanyId(user.getCompanyId());
			nextStation.setStationName(stationName);
			int n = daoService.registNewTheNextStation(nextStation);
			if (n > 0) {
				mess = "增加成功";
				flag = ConstantCode.SUCCESS;
			} else {
				mess = "增加失败";
			}

//			jo.put("queryVos", queryVos);
			jo.put("flag", flag);
			jo.put("message", mess);
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(jo.toString());
			response.getWriter().flush();
			response.getWriter().close();
		}
	}

	/**
	 * @param 传入用户的username
	 *            ,stationName 判断权限，只有管理员能进行删操作
	 * @return json
	 * @throws IOException
	 */
	@RequestMapping(value = "/deleteTheNextStation")
	@ResponseBody
	public void deleteTheNextStation(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String username = request.getParameter("username");
		User user = daoService.getUserByUsername(username);
//		List<QueryVo> queryVos = null;
		String mess = "";
		int flag = ConstantCode.ERROR;
		JSONObject jo = new JSONObject();

		if (user.getRole().equals("common")) {
			mess = "您的权限不够";
		} else {
			String stationName = request.getParameter("stationName");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("stationName", stationName);
			map.put("companyId", user.getCompanyId());
			TheNextStation nextStation1 = daoService
					.getTheNextStationBycompanyIdAndStationName(map);
			if (nextStation1 != null) {
				int theNextStationId = nextStation1.getId();
				List<BindTable> bindTables = daoService
						.selectBindTableByTheNextStation(theNextStationId);
				if (bindTables.size() == 0) {
					int n = daoService
							.removeTheNextStationByCompanyIdAndStationName(nextStation1);
					if (n > 0) {
						mess = "删除成功";
						flag = ConstantCode.SUCCESS;
					} else {
						mess = "删除失败";
					}
				} else {
					int n = daoService
							.removeTheNextStationByCompanyIdAndStationName(nextStation1);
					int n1 = daoService
							.updateBindTableByTheNextStation(theNextStationId);
					if (n > 0 && n1 > 0) {
						mess = "删除成功";
						flag = ConstantCode.SUCCESS;
					} else {
						mess = "删除失败";
					}
				}
			} else {
				mess = "删除的下货站不存在";
			}

			jo.put("flag", flag);
			jo.put("message", mess);
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(jo.toString());
			response.getWriter().flush();
			response.getWriter().close();

		}
	}

}
