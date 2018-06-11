package com.cctrace.apiController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cctrace.entity.Rail;
import com.cctrace.entity.User;
import com.cctrace.service.DaoService;
import com.cctrace.utils.BaseResult;

@Controller
@RequestMapping(value = "/apiUser")
public class ApiRailController {

	@Resource(name = "daoService")
	private DaoService daoService;

	public void setRailService(DaoService daoService) {
		this.daoService = daoService;
	}

	/**
	 * Test 插入围栏
	 * 
	 * @param username
	 *            ,railName
	 * @return
	 */
	@RequestMapping("/insertRail")
	@ResponseBody
	public Object insertRail(HttpServletRequest request) {
		String username = request.getParameter("username");
		String railName = request.getParameter("railName");
		double railLat = Double.parseDouble(request.getParameter("railLat"));
		double railLon = Double.parseDouble(request.getParameter("railLon"));
		double radius = Double.parseDouble(request.getParameter("radius"));
		if (username != null && railName != null && railLat != 0 && radius != 0) {
			User user = daoService.getUserByUsername(username);
			Rail rail = new Rail();
			rail.setCompanyId(user.getCompanyId());
			rail.setRadius(radius);
			rail.setRailLon(railLon);
			rail.setRailLat(railLat);
			rail.setRailName(railName);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("railName", railName);
			map.put("companyId", user.getCompanyId());
			if (daoService.getRailByRailNameAndCompanyId(map) == null) {
				int n = daoService.addNewRail(rail);
				if (n > 0) {
					return BaseResult.success();
				} else {
					return BaseResult.error();
				}
			} else {
				return BaseResult.error();
			}
		} else {
			return BaseResult.error();
		}
	}

	/**
	 * 
	 * @param username
	 * @return
	 */
	@RequestMapping("/findAllRail")
	@ResponseBody
	public List<Rail> findAllRail(HttpServletRequest request) {
		String username = request.getParameter("username");

		User user = daoService.getUserByUsername(username);
		List<Rail> railList = null;
		if (daoService.getRailsByCompanyId(user.getCompanyId()) != null) {
			railList = daoService.getRailsByCompanyId(user.getCompanyId());
		}
		return railList;

	}

	/**
	 * 删除围栏
	 * 
	 * @param railName
	 *            ，username
	 * @return
	 */
	@RequestMapping("/deleteRail")
	@ResponseBody
	public Object deleteRail(HttpServletRequest request) {
		String username = request.getParameter("username");
		String railName = request.getParameter("railName");
		User user = daoService.getUserByUsername(username);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("railName", railName);
		map.put("companyId", user.getCompanyId());
		if (username != null && railName != null
				&& daoService.getRailByRailNameAndCompanyId(map) != null) {
			int n = daoService.removeRailByRailName(railName);
			if (n > 0) {
				System.out.println("delete rail success");
				return BaseResult.success();
			} else {
				return BaseResult.error();
			}
		} else {
			return BaseResult.error();
		}
	}

}
