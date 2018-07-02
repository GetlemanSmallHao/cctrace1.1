package com.cctrace.controller;

import java.util.Date;
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

import com.cctrace.entity.Alert;
import com.cctrace.entity.Geomessage;
import com.cctrace.entity.User;
import com.cctrace.service.DaoService;
import com.cctrace.utils.ConstantCode;
import com.cctrace.utils.DateUtil;

@Controller
@RequestMapping("/pc/alert")
public class GeomessageController {
	@Resource(name = "daoService")
	private DaoService daoService;

	/**
	 * 首页获取地理围栏通知的数量 Geomessage
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getCountOfNoReadedGeomessageBeforeDays")
	@ResponseBody
	public Integer getCountOfNoReadedGeomessageBeforeDays(
			HttpServletRequest request) {
		try {
			Integer companyId = getCompanyIdOfCurrentUser(request);
			Date now = new Date();
			long longNow = DateUtil.getLongFromDate(now);
			long beginTime = longNow - (1 * 24 * 60 * 60 * 1000);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("companyId", companyId);
			map.put("beginTime", beginTime);
			Integer count = null;
			if (daoService.getCountOfNoReadedGeomessageBeforeDays(map) != null) {
				count = daoService.getCountOfNoReadedGeomessageBeforeDays(map);
			}
			return count;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("异常");
		}
		return null;
	}

	/**
	 * 点击查看明细页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getRecentOneDaysGeoAlerts")
	public String getRecentOneDaysAlerts(HttpServletRequest request, ModelMap mm) {
		try {
			Integer companyId = getCompanyIdOfCurrentUser(request);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("companyId", companyId);
			Date now = new Date();
			long longNow = DateUtil.getLongFromDate(now);
			long beginTime = longNow - (1 * 24 * 60 * 60 * 1000);
			map.put("beginTime", beginTime);
			List<Geomessage> geomessages = daoService
					.getAlertsByCompanyIdInOneDays(map);

			mm.addAttribute("geomessages", geomessages);
			System.out.println(mm);
			return "warningGeo.jsp";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("查看明细页面异常");
		}
		return null;
	}

	/**
	 * 查询两段时间的围栏通知
	 * 
	 * @param request
	 * @param startTime
	 * @param endTime
	 * @param containerId
	 * @param mm
	 * @return
	 */
	@RequestMapping(value = "/selectShowGeomessageInTwoTime")
	public String selectShowGeomessageInTwoTime(HttpServletRequest request,
			@RequestParam String startTime, @RequestParam String endTime,
			@RequestParam String containerId, ModelMap mm) {
		try {
			String mess = "";
			Integer flag = ConstantCode.ERROR;
			List<Alert> alerts = null;
			Map<String, Object> map = new HashMap<String, Object>();
			long longStart = DateUtil.getLongFromStr(startTime,
					"yyyy-MM-dd HH:mm:ss");
			long longEnd = DateUtil.getLongFromStr(endTime, "yyyy-MM-dd HH:mm:ss");
			map.put("containerId", containerId);
			map.put("startTime", longStart);
			map.put("endTime", longEnd);
			if (daoService.selectShowAlertInTwoTime(map).size() > 0) {
				alerts = daoService.selectShowAlertInTwoTime(map);
				mess = "查询到警告信息";
				flag = ConstantCode.SUCCESS;
			}
			mm.addAttribute("alerts", alerts);
			return "showGeomessageSelectWarning.jsp";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("查询围栏通知异常");
		}
		return null;
	}

	/**
	 * 地理围栏通知，通过冷箱号和时间范围查询冷藏箱
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/showGeomessagetsInTwoTimeAndContainerId")
	public String showGeomessagetsInTwoTimeAndContainerId(
			HttpServletRequest request, @RequestParam String startTime,
			@RequestParam String endTime, @RequestParam String containerId,
			ModelMap mm) {
		try {
			String mess = "";
			Integer flag = ConstantCode.ERROR;
			List<Geomessage> messages = null;
			Map<String, Object> map = new HashMap<String, Object>();
			long longStart = 0L;
			if (null != startTime && !"".equals(startTime)) {
				longStart = DateUtil.getLongFromStr(startTime,
						"yyyy-MM-dd HH:mm:ss");
			}
			long longEnd = 0L;
			if (null != endTime && !"".equals(endTime)) {
				longEnd = DateUtil.getLongFromStr(endTime, "yyyy-MM-dd HH:mm:ss");
			}

			map.put("containerId", containerId);
			if (longStart == 0) {
				map.put("startTime", null);
			} else {
				map.put("startTime", longStart);
			}
			if (longEnd == 0) {
				map.put("endTime", null);
			} else {
				map.put("endTime", longEnd);
			}

			if (daoService.showGeomessagetsInTwoTimeAndContainerId(map).size() > 0) {
				messages = daoService.showGeomessagetsInTwoTimeAndContainerId(map);
				mess = "查询到警告信息";
				flag = ConstantCode.SUCCESS;
			}
			mm.addAttribute("messages", messages);
			return "showSelectGeomessage.jsp";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("查询冷藏箱异常");
		}
		return null;
	}

	/**
	 * 更新消息是否已读
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/modifyAlertReadStateByGeomessagetId")
	@ResponseBody
	public Geomessage modifyAlertReadStateByGeomessagetId(
			HttpServletRequest request) {
		try {
			String alertIdStr = request.getParameter("alertId");
			int alertId = Integer.parseInt(alertIdStr);
			Integer modify = daoService
					.modifyAlertReadStateByGeomessagetId(alertId);
			Geomessage alert = daoService.getGeoMessageById(alertId);
			if (modify == 1) {
				System.out.println("更新成功。。。");
			} else {
				System.out.println("更新失败。。。");
			}
			return alert;
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			System.out.println("更新消息异常");
		}
		return null;
	}

	private User getCurrentUser(HttpServletRequest request) {
		try {
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			return user;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("异常");
		}
		return null;
	}

	private Integer getCompanyIdOfCurrentUser(HttpServletRequest request) {
		try {
			User user = getCurrentUser(request);
			Integer companyId = user.getCompanyId();
			return companyId;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("异常");
		}
		return null;
	}
}
