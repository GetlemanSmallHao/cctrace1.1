package com.cctrace.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cctrace.entity.Alert;
import com.cctrace.entity.User;
import com.cctrace.service.DaoService;
import com.cctrace.utils.ConstantCode;
import com.cctrace.utils.DateUtil;
import com.cctrace.utils.ListJsonUtils;

@Controller
@RequestMapping("/pc/alert")
// 告警控制
public class AlertController {

	@Resource(name = "daoService")
	private DaoService daoService;

	@RequestMapping("/query")
	public String registNewUser(HttpServletRequest req,
			HttpServletResponse res, ModelMap mmp) {
		List<Alert> listA = daoService.selectAllAlerts();
		mmp.addAttribute("listA", listA);
		for (Alert alert : listA) {
			System.out.println(alert);
		}
		return "warning.jsp";

	}

	// /**
	// * 修改成查询1天内的信息
	// * 2017-10-13 9:57
	// * @param request
	// * @param mm
	// * @return
	// */
	// @RequestMapping(value = "/getRecentFourDaysAlerts")
	// public String getRecentFourDaysAlerts(HttpServletRequest request,
	// ModelMap mm) {
	// User user = (User) request.getSession().getAttribute("user");
	// List<String> list = null;
	// CommandStore commandStore1 = null;
	// CommandStore commandStore2 = null;
	// CommandStore commandStore3 = null;
	// Map<String,Object> map1 = new HashedMap();
	// List<Alert> alertss = new ArrayList<Alert>();
	// long remoteSwiMacTime = 0;
	// long selfCheakTime = 0 ;
	// long clearAlertTime = 0 ;
	// long nowTime;
	// // 获得当前长整形时间
	// Date data = new Date();
	// nowTime = DateUtil.getLongFromDate(data);
	// String startTime = DateUtil.getDateStr(data, "yyyy-MM-dd 00:00:00");
	// long startTime1 = DateUtil.getLongFromStr(startTime,
	// "yyyy-MM-dd HH:mm:ss");
	//
	// list = daoService.selectAlertWithContainer(user.getCompanyId());
	// for (String containerId : list) {
	// map1.put("commandType", "remoteSwiMac");
	// map1.put("containerId", containerId);
	// commandStore1 = daoService.selectSetCommand(map1);
	// if(commandStore1 != null &&
	// StringUtil.isNotEmpty(commandStore1.getLongTime())){
	// // 获得远程开关机时间
	// remoteSwiMacTime = Long.parseLong(commandStore1.getLongTime());
	// }
	// map1.clear();
	// map1.put("commandType", "clearAlert");
	// map1.put("containerId", containerId);
	// commandStore2 = daoService.selectSetCommand(map1);
	// if(commandStore2 != null &&
	// StringUtil.isNotEmpty(commandStore2.getLongTime())){
	// // 获得清除警告时间
	// clearAlertTime = Long.parseLong(commandStore2.getLongTime());
	// }
	// map1.clear();
	// map1.put("commandType", "selfCheak");
	// map1.put("containerId", containerId);
	// commandStore3 = daoService.selectSetCommand(map1);
	// if(commandStore3 != null &&
	// StringUtil.isNotEmpty(commandStore3.getLongTime())){
	// // 获得自检时间
	// selfCheakTime = Long.parseLong(commandStore3.getLongTime());
	// }
	// long max = Math.max(selfCheakTime, (Math.max(remoteSwiMacTime,
	// clearAlertTime)));
	// List<Alert> alerts1 = null;
	// Map<String, Object> map2 = new HashedMap();
	// map2.put("containerId", containerId);
	// map2.put("longTime", max);
	// if(daoService.selectAlertAfterCommand(map2).size() > 0){
	// // 显示命令之后的警告信息
	// alerts1 = daoService.selectAlertAfterCommand(map2);
	// }else{
	// map2.clear();
	// map2.put("containerId", containerId);
	// map2.put("start", startTime1);
	// map2.put("end", nowTime);
	// alerts1 = daoService.selectAlertGroupInOneDay(map2);
	// }
	// if(alerts1.size() > 0){
	// for (int i = 0; i < alerts1.size(); i++) {
	// Alert alert = alerts1.get(i);
	// System.out.println(alert);
	// alertss.add(alert);
	//
	// }
	// }
	// Map<String,Object> map = new HashedMap();
	// map.put("alert", alerts1);
	// maps.add(map);
	// }
	// mm.addAttribute("alertss", alertss);
	// return "warning.jsp";
	// }

	/**
	 * 
	 * 备份2017-10-24 ：09：02
	 */

	/**
	 * 修改成查询1天内的信息 2017-10-13 9:57
	 * 
	 * @param request
	 * @param mm
	 * @return
	 */
	@RequestMapping(value = "/getRecentFourDaysAlerts")
	public String getRecentFourDaysAlerts(HttpServletRequest request,
			ModelMap mm) {
		Integer companyId = getCompanyIdOfCurrentUser(request);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("companyId", companyId);
		Date now = new Date();
		long longNow = DateUtil.getLongFromDate(now);
		long beginTime = longNow - (1 * 24 * 60 * 60 * 1000);
		map.put("beginTime", beginTime);
		List<Alert> alerts = daoService.getAlertsByCompanyIdInFourDays(map);
		mm.addAttribute("alerts", alerts);
		System.out.println(mm);
		return "warning.jsp";
	}

	@RequestMapping(value = "/getLastAlerts")
	public String getLastAlerts(HttpServletRequest request, ModelMap mm) {
		Integer companyId = getCompanyIdOfCurrentUser(request);
		List<Alert> alerts = null;
		if (daoService.selectLastAlertsTimeByCompanyId(companyId) != null) {
			alerts = daoService.selectLastAlertsTimeByCompanyId(companyId);
		}
		mm.addAttribute("alerts", alerts);
		return "warning.jsp";
	}

	@RequestMapping(value = "/getAlertsByCompanyIdAndContainerIdLikely")
	public String getAlertsLikely(HttpServletRequest request, ModelMap mm) {
		Integer companyId = getCompanyIdOfCurrentUser(request);
		String containerId = request.getParameter("containerId");
		System.out.println(containerId);
		Date now = new Date();
		long longNow = DateUtil.getLongFromDate(now);
		long beginTime = longNow - (4 * 24 * 60 * 60 * 1000);
		Map<String, Object> map = new HashedMap();
		map.put("containerId", containerId);
		map.put("companyId", companyId);
		map.put("beginTime", beginTime);
		List<Alert> alerts = daoService
				.getAlertsByCompanyIdAndContainerIdLikely(map);
		mm.addAttribute("alerts", alerts);
		return "warning.jsp";
	}

	@RequestMapping(value = "/getAlertsByContainerIdAndAlertTypeLikely")
	public String getAlertsByCompanyIdAndContainerIdAndAlertTypeLikely(
			HttpServletRequest request, ModelMap mm) {
		Integer companyId = getCompanyIdOfCurrentUser(request);
		String containerId = request.getParameter("containerId");
		String alertType = request.getParameter("alertType");
		Date now = new Date();
		long longNow = DateUtil.getLongFromDate(now);
		long beginTime = longNow - (4 * 24 * 60 * 60 * 1000);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("companyId", companyId);
		map.put("alertType", alertType);
		map.put("containerId", containerId);
		map.put("beginTime", beginTime);
		List<Alert> alerts = daoService
				.getAlertsByCompanyIdAndContainerIdAndAlertTypeLikely(map);
		mm.addAttribute("alerts", alerts);
		return "warning.jsp";
	}

	// 根据告警类型，公司id，查询四天内的所有告警信息
	@RequestMapping(value = "/getAlertsByAlertTypeLikely")
	public String getAlertsByAlertTypeLikely(HttpServletRequest request,
			ModelMap mm) {
		Date now = new Date();
		long longNow = DateUtil.getLongFromDate(now);
		long beginTime = longNow - (4 * 24 * 60 * 60 * 1000);

		String alertType = request.getParameter("alertType");
		Integer companyId = getCompanyIdOfCurrentUser(request);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("companyId", companyId);
		map.put("beginTime", beginTime);
		map.put("alertType", alertType);
		List<Alert> alerts = daoService
				.getAlertsByCompanyIdAndAlertTypeLikely(map);
		mm.addAttribute("alerts", alerts);
		return "warning.jsp";
	}

	@RequestMapping(value = "/getMaxAlertIdByCompanyId")
	@ResponseBody
	public Integer getMaxAlertIdByCompanyId(HttpServletRequest request) {
		// 这是本类的私有方法
		Integer companyId = getCompanyIdOfCurrentUser(request);
		Integer maxAlertId = daoService.getMaxAlertIdByCompanyId(companyId);
		return maxAlertId;
	}

	@RequestMapping(value = "/getCountOfNoReadedALertsByCompanyId")
	@ResponseBody
	public Integer getCountOfNoReadedAlertsByCompanyId(
			HttpServletRequest request) {
		Integer companyId = getCompanyIdOfCurrentUser(request);

		Date now = new Date();
		long longNow = DateUtil.getLongFromDate(now);
		long beginTime = longNow - (4 * 24 * 60 * 60 * 1000);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("companyId", companyId);
		map.put("beginTime", beginTime);

		Integer count = daoService
				.getCountOfNoReadedAlertsByCompanyIdAndbeginTime(map);
		// Integer count =
		// daoService.getCountOfNoReadedAlertsByCompanyId(companyId);
		return count;
	}

	/**
	 * 上面写的方法不可行，重写获得最新三天的展示的警告的未读信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getCountOfNoReadedALertsBeforeDays")
	@ResponseBody
	public Integer getCountOfNoReadedALertsBeforeDays(HttpServletRequest request) {
		Integer companyId = getCompanyIdOfCurrentUser(request);
		Date now = new Date();
		long longNow = DateUtil.getLongFromDate(now);
		long beginTime = longNow - (1 * 24 * 60 * 60 * 1000);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("companyId", companyId);
		map.put("beginTime", beginTime);
		Integer count = null;
		if (daoService.selectCountNoReadedBeforeSomeDays(map) != null) {
			count = daoService.selectCountNoReadedBeforeSomeDays(map);
		}
		return count;
	}

	/**
	 * ajax异步请求时传递过来alertId,根据传递过来的alertId对该条记录的readed进行修改
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/modifyAlertReadStateByAlertId")
	@ResponseBody
	public Alert modifyAlertReadStateByAlertId(HttpServletRequest request) {
		String alertIdStr = request.getParameter("alertId");
		int alertId = Integer.parseInt(alertIdStr);
		Integer modify = daoService.modifyAlertReadStateToYesByAlertId(alertId);
		Alert alert = daoService.getAlertById(alertId);
		if (modify == 1) {
			System.out.println("更新成功。。。");
		} else {
			System.out.println("更新失败。。。");
		}
		return alert;
	}

	/**
	 * 如果之前没有查询过，显示往前24小时内的 如果之前查询过，显示两段时间内的新警告信息
	 * 
	 * @param request
	 * @param username
	 * @param selectTime
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/showAlertsInTwoTime")
	@ResponseBody
	public ListJsonUtils showAlertsInTwoTime(HttpServletRequest request,
			@RequestParam String username, @RequestParam String selectTime)
			throws ParseException {
		Map<String, Object> map = new HashedMap();
		String mess = "没有警告信息";
		int flag = ConstantCode.ERROR;
		User user = daoService.getUserByUsername(username);
		List<Alert> alerts = null;
		// 前台传入的查询时间
		Date selectTime1 = DateUtil.getDateFromStr(selectTime,
				"yyyy-MM-dd HH:mm:ss");
		Date nowDate = DateUtil.getNowDate();
		long start = DateUtil.getLongFromDate(nowDate);
		long end = start - 24 * 60 * 60 * 1000;

		/*
		 * Calendar calendar = new GregorianCalendar();
		 * calendar.setTime(selectTime1); calendar.add(calendar.DATE, -1);
		 * selectTime1 = calendar.getTime(); // 开始时间，---- 查询时间往前24小时 String
		 * timeBefore24hour = DateUtil.getDateStr(selectTime1,
		 * "yyyy-MM-dd HH:mm:ss");
		 */
		map.put("companyId", user.getCompanyId());
		map.put("startTime", start);
		map.put("endTime", end);
		alerts = daoService.selectNewAlertInTwoTime(map);
		if (alerts.size() > 0) {
			mess = "警告！出现警告信息！";
			flag = ConstantCode.SUCCESS;
		}

		return new ListJsonUtils(flag, mess, alerts);
	}

	/*
	 * @RequestMapping("/showAlertsInTwoTimeAndContainerId")
	 * 
	 * @ResponseBody public ListJsonUtils
	 * showAlertsInTwoTimeAndContainerId(HttpServletRequest
	 * request,@RequestParam String startTime,@RequestParam String
	 * endTime,@RequestParam String containerId) { List<Alert> alerts = null;
	 * String mess = ""; int flag = ConstantCode.ERROR;
	 * if(StringUtil.isEmpty(startTime) || StringUtil.isEmpty(endTime) ||
	 * StringUtil.isEmpty(containerId)){ mess = "输入条件不符合要求"; return new
	 * ListJsonUtils(flag, mess, null); } Map<String , Object> map = new
	 * HashMap<String, Object>(); long longStart =
	 * DateUtil.getLongFromStr(startTime, "yyyy-MM-dd HH:mm:ss"); long longEnd =
	 * DateUtil.getLongFromStr(endTime, "yyyy-MM-dd HH:mm:ss");
	 * map.put("containerId", containerId); map.put("startTime", longStart);
	 * map.put("endTime", longEnd);
	 * if(daoService.selectShowAlertInTwoTime(map).size() > 0){ alerts =
	 * daoService.selectShowAlertInTwoTime(map); mess = "查询到警告信息"; flag =
	 * ConstantCode.SUCCESS; } return new ListJsonUtils(flag, mess, alerts); }
	 */

	@RequestMapping(value = "/showAlertsInTwoTimeAndContainerId")
	public String showAlertsInTwoTimeAndContainerId(HttpServletRequest request,
			@RequestParam String startTime, @RequestParam String endTime,
			@RequestParam String containerId, ModelMap mm) {
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
		return "showSelectWarning.jsp";
	}
	
	/**
	 * 
	 * 新增模块，筛查已未读、部门、告警代码
	 */
	@RequestMapping(value = "/showThreeKindOfWarning")
	public String showThreeKindOfWarning(HttpServletRequest request,
			@RequestParam String searchAlert, ModelMap mm) {
		String readed;
		String buMenM;
		Integer alarm_num;
		String mess = "";
		Integer flag = ConstantCode.ERROR;
		List<Alert> alerts = null;
		Map<String, Object> map = new HashMap<String, Object>();
		if(searchAlert.equals("已读") || searchAlert.equals("未读")){
			readed=searchAlert;
			map.put("readed", readed);
			if (daoService.selectShowReaded(map).size() > 0) {
				alerts = daoService.selectShowReaded(map);
				mess = "查询到警告信息";
				flag = ConstantCode.SUCCESS;
			}
		}else if(searchAlert.equals("班列") || searchAlert.equals("冷链")){
			buMenM=searchAlert;
			map.put("buMenM", buMenM);
			if (daoService.selectShowBuMenM(map).size() > 0) {
				alerts = daoService.selectShowBuMenM(map);
				mess = "查询到警告信息";
				flag = ConstantCode.SUCCESS;
			}
		}else{
			alarm_num=Integer.valueOf(searchAlert);
			map.put("alarm_num", alarm_num);
			if (daoService.selectShowAlarmNum(map).size() > 0) {
				alerts = daoService.selectShowAlarmNum(map);
				mess = "查询到警告信息";
				flag = ConstantCode.SUCCESS;
			}
		}
		mm.addAttribute("alerts", alerts);
		return "showSelectWarning.jsp";
	}
	
	/*@RequestMapping(value = "/showBuMenM")
	public String showBuMenM(HttpServletRequest request,
			@RequestParam String buMenM, ModelMap mm) {
		String mess = "";
		Integer flag = ConstantCode.ERROR;
		List<Alert> alerts = null;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("buMenM", buMenM);
		if (daoService.selectShowBuMenM(map).size() > 0) {
			alerts = daoService.selectShowBuMenM(map);
			mess = "查询到警告信息";
			flag = ConstantCode.SUCCESS;
		}
		mm.addAttribute("alerts", alerts);
		return "showSelectWarning.jsp";
	}
	
	@RequestMapping(value = "/showAlarmNum")
	public String showAlarmNum(HttpServletRequest request,
			@RequestParam Integer alarm_num, ModelMap mm) {
		String mess = "";
		Integer flag = ConstantCode.ERROR;
		List<Alert> alerts = null;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("alarm_num", alarm_num);
		if (daoService.selectShowAlarmNum(map).size() > 0) {
			alerts = daoService.selectShowAlarmNum(map);
			mess = "查询到警告信息";
			flag = ConstantCode.SUCCESS;
		}
		mm.addAttribute("alerts", alerts);
		return "showSelectWarning.jsp";
	}*/

	private User getCurrentUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		return user;
	}

	private Integer getCompanyIdOfCurrentUser(HttpServletRequest request) {
		User user = getCurrentUser(request);
		Integer companyId = user.getCompanyId();
		return companyId;
	}

}
