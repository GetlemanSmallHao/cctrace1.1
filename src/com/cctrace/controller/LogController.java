package com.cctrace.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cctrace.entity.CommandStore;
import com.cctrace.entity.Log;
import com.cctrace.entity.Msg;
import com.cctrace.entity.User;
import com.cctrace.service.DaoService;
import com.cctrace.utils.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;

@Controller
// 日志，跟踪用户操作信息
public class LogController {

	@Autowired
	DaoService daoService;

	@ResponseBody
	@RequestMapping("/getLogs")
	public Msg getLogs(String startTime, String endTime,
			@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
		long startLongTime;
		long endLongTime;
		if (endTime == null) {
			endLongTime = DateUtil.getLongFromDate(new Date());
		} else {
			endLongTime = DateUtil.getLongFromStr(endTime,
					"yyyy-MM-dd HH:mm:ss");
		}
		if (startTime == null) {
			startLongTime = endLongTime - (7 * 24 * 60 * 60 * 1000);
		} else {
			startLongTime = DateUtil.getLongFromStr(startTime,
					"yyyy-MM-dd HH:mm:ss");
		}

		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("start", startLongTime);
		map1.put("end", endLongTime);
		// 在查询之前只需要调用，传入页码，以及每页的大小
		PageHelper.startPage(pn, 10);
		// startPage后面紧跟的这个查询就是一个分页查询
		List<Log> logs = daoService.getLogsByOperatorBetweenTwoTime(map1);

		// 封装了详细的分页信息,包括有我们查询出来的数据，传入连续显示的页数
		PageInfo page = new PageInfo(logs, 5);

		return Msg.success().add("page", page);

	}

	/**
	 * 
	 * @author liyi
	 * @date 创建时间：2017年11月30日 下午2:35:35
	 * @Descripton
	 */
	@ResponseBody
	@RequestMapping("/getLogsForCommand")
	public Msg getLogsForCommand(HttpServletRequest request, String startTime,
			String endTime, String containerId,
			@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
		long startLongTime;
		String containerId1 = containerId.trim();
		long endLongTime;
		List<CommandStore> commandStores = null;
		User user = (User) request.getSession().getAttribute("user");
		if (endTime == null) {
			endLongTime = DateUtil.getLongFromDate(new Date());
		} else {
			endLongTime = DateUtil.getLongFromStr(endTime,
					"yyyy-MM-dd HH:mm:ss");
		}
		if (startTime == null) {
			startLongTime = endLongTime - (7 * 24 * 60 * 60 * 1000);
		} else {
			startLongTime = DateUtil.getLongFromStr(startTime,
					"yyyy-MM-dd HH:mm:ss");
		}

		Map<String, Object> map1 = new HashMap<String, Object>();
		Map<String, Object> map2 = new HashMap<String, Object>();

		if (endTime != null && startTime != null
				&& StringUtil.isNotEmpty(containerId)) {
			map2.put("start", startLongTime);
			map2.put("end", endLongTime);
			map2.put("containerId", containerId1);
			map2.put("companyId", user.getCompanyId());
			// 在查询之前只需要调用，传入页码，以及每页的大小
			PageHelper.startPage(pn, 10);
			// startPage后面紧跟的这个查询就是一个分页查询
			commandStores = daoService
					.selectLogShowInTwoTimesWithContainerIdLike(map2);
		} else {
			map1.put("start", startLongTime);
			map1.put("end", endLongTime);
			map1.put("companyId", user.getCompanyId());
			// 在查询之前只需要调用，传入页码，以及每页的大小
			PageHelper.startPage(pn, 10);
			// startPage后面紧跟的这个查询就是一个分页查询
			commandStores = daoService.selectCommandForLogShowInTwoTimes(map1);
			// 封装了详细的分页信息,包括有我们查询出来的数据，传入连续显示的页数

		}

		// map1.put("containerId", containerId);
		/*
		 * System.out.println("" + "$$$"+startTime);
		 * System.out.println(startLongTime); System.out.println("^^"+pn);
		 */
		PageInfo page = new PageInfo(commandStores, 5);

		return Msg.success().add("page", page);

	}
	
	 /**
	 * @author lusiyuan
	 * @param request
	 * @param startTime
	 * @param endTime
	 * @param commandType
	 * @param pn
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getLogsForCommandType")
	public Msg getLogsForCommandType(HttpServletRequest request, String commandType,
			@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
		String commandType1 = commandType.trim();
		List<CommandStore> commandStores = null;
		User user = (User) request.getSession().getAttribute("user");

		Map<String, Object> map1 = new HashMap<String, Object>();
		Map<String, Object> map2 = new HashMap<String, Object>();
		
		if(commandType1.equals("设置温度")){
			commandType1="temSet";
		}else if(commandType1.equals("启动除霜")){
			commandType1="bootDef";
		}else if(commandType1.equals("清除警告")){
			commandType1="clearAlert";
		}else if(commandType1.equals("自检")){
			commandType1="selfCheck";
		}else if(commandType1.equals("设置运行模式")){
			commandType1="refRunMode";
		}else if(commandType1.equals("远程开关机")){
			commandType1="remoteSwiMac";
		}else if(commandType1.equals("新风门开关机")){
			commandType1="remoteXFSwiMac";
		}else if(commandType1.equals("cfm开机设置")){
			commandType1="cfmSet";
		}else{
			commandType1="";
		}

		if (StringUtil.isNotEmpty(commandType)) {
			map2.put("commandType", commandType1);
			map2.put("companyId", user.getCompanyId());
			// 在查询之前只需要调用，传入页码，以及每页的大小
			PageHelper.startPage(pn, 10);
			// startPage后面紧跟的这个查询就是一个分页查询
			commandStores = daoService
					.selectLogShowInTwoTimesWithCommandTypeLike(map2);
		} /*else {
			map1.put("companyId", user.getCompanyId());
			// 在查询之前只需要调用，传入页码，以及每页的大小
			PageHelper.startPage(pn, 10);
			// startPage后面紧跟的这个查询就是一个分页查询
			commandStores = daoService.selectCommandForLogShowInTwoTimes(map1);
			// 封装了详细的分页信息,包括有我们查询出来的数据，传入连续显示的页数
		}*/
		PageInfo page = new PageInfo(commandStores, 5);
		return Msg.success().add("page", page);

	}

}
