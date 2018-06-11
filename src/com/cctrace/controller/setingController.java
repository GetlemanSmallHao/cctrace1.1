package com.cctrace.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.omg.DynamicAny.DynAnyOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cctrace.entity.BindTable;
import com.cctrace.entity.Ccdata;
import com.cctrace.entity.Ccdata1;
import com.cctrace.entity.CommandStore;
import com.cctrace.entity.Container;
import com.cctrace.entity.Msg;
import com.cctrace.entity.OfflineOrder;
import com.cctrace.entity.OurCcdata;
import com.cctrace.entity.OurCcdata1;
import com.cctrace.entity.User;
import com.cctrace.service.DaoService;
import com.cctrace.socketServers.delData.ProcessSocketData;
import com.cctrace.utils.ConstantCode;
import com.cctrace.utils.JsonResult;
import com.github.pagehelper.util.StringUtil;
import com.google.gson.Gson;

@Controller
@RequestMapping("pc/seting")
// 首页 冷藏箱信息设置
public class setingController {

	@Autowired
	DaoService daoService;
	ProcessSocketData psd = new ProcessSocketData(null);
	@Autowired
	RedisTemplate<String, Object> redisTemplate;

	@ResponseBody
	@RequestMapping("getOneBindtable")
	public Msg getOneBindtable(HttpServletRequest request, String containerId) {

		Map<String, Object> map = new HashMap<String, Object>();

		if (containerId != null) {
			Container container = daoService
					.getContainerBycontarinId(containerId);
			BindTable bindTable = daoService
					.getBindTableByContainerId(containerId);
			Ccdata1 ccdata = daoService.selectCcdataByContainerId1(containerId);
			OurCcdata1 ourCcdata = daoService
					.selectOurCcdataBycontainerId1(containerId);
			String routeType;
			String trainId;
			String carGoType;
			String stationName = "";
			String yardName = "";
			String nowTime;
			Double backWindTemp;
			Double oilLevel;
			Double setTemp;
			if (null != bindTable.getRouteType()) {
				routeType = bindTable.getRouteType();
			} else {
				routeType = "";
			}
			if (null != bindTable.getTrainId()) {
				trainId = bindTable.getTrainId();
			} else {
				trainId = "";
			}
			if (null != bindTable.getCarGoType()) {
				carGoType = bindTable.getCarGoType();
			} else {
				carGoType = "";
			}
			// 下货站名称
			if (null != bindTable.getTheNextStationId()) {
				if (daoService.getTheNextStationById(bindTable
						.getTheNextStationId()) != null)
					stationName = daoService.getTheNextStationById(
							bindTable.getTheNextStationId()).getStationName();

			} else {
				stationName = "";
			}
			// 堆场名称
			if (null != bindTable.getYardId()) {
				System.out.println("+++" + bindTable.getYardId());
				System.out.println("+++"
						+ daoService.getYardbyId(bindTable.getYardId()));
				if (daoService.getYardbyId(bindTable.getYardId()) != null) {
					yardName = daoService.getYardbyId(bindTable.getYardId())
							.getYardName();
				}
			} else {
				yardName = "";
			}
			if (null != ccdata.getNowTime()) {

				nowTime = ccdata.getNowTime();

			} else {
				nowTime = "";
			}

			map.put("ccdata", ccdata);
			map.put("trainId", trainId);
			map.put("carGoType", carGoType);
			map.put("stationName", stationName);
			map.put("yardName", yardName);
			map.put("nowTime", nowTime);
			map.put("routeType", routeType);
			map.put("backWindTemp", ccdata.getBackWindTemp());
			map.put("oilLevel", ourCcdata.getOilLevel());
			map.put("setTemp", ccdata.getTempSet());
		}

		return Msg.success().add("map", map);
	}

	/**
	 * 设置温度
	 */
	@ResponseBody
	@RequestMapping(value = "/setTemp")
	public JsonResult<Container> setTemp(HttpServletRequest request,
			String setTemp, String containerId, String deviceId,
			String chillerType) {

		String mess = "";
		int flag = ConstantCode.ERROR;

		User user = (User) request.getSession().getAttribute("user");
		String userName = null;
		if (user != null) {
			userName = user.getUsername();
		} else {
			mess = "用户信息有误，请重新登录！";
			return new JsonResult<Container>(flag, mess, null);
		}

		Container container = daoService.getContainerBycontarinId(containerId);
		Ccdata1 ccdata1 = daoService.selectCcdataByDeviceId1(container
				.getDeviceId());
		Map<String, Object> valMap = new HashMap<String, Object>();
		String command = "temSet";
		valMap.put("command", command);
		valMap.put("containerId", containerId);
		CommandStore conS = daoService.selectCommandStoreCommandDescOne(valMap);
		String display = null;
		if (conS != null) {
			display = conS.getDisplay();
		}

		if (container == null) {
			mess = "该设备不存在，请核对！";
			return new JsonResult<Container>(flag, mess, container);
		}

		if (StringUtil.isEmpty(setTemp)) {
			mess = "温度不能为空！";
			return new JsonResult<Container>(flag, mess, container);
		}
		if (Double.parseDouble(setTemp) > 29) {
			mess = "温度设定的值不合法，需在±29之内";
			return new JsonResult<Container>(flag, mess, container);
		}
		if (Double.parseDouble(setTemp) < 0) {
			if ((Double.parseDouble(setTemp.split("-")[1])) > 29) {
				mess = "温度设定的值不合法，需在±29之内";
				return new JsonResult<Container>(flag, mess, container);
			}
		}
		if (ccdata1.getRefSwiState().equals("off")
				|| StringUtil.isEmpty(ccdata1.getRefSwiState())) {
			mess = "请先开机";
			return new JsonResult<Container>(flag, mess, container);
		} else {
			Map<String, String> mapSet = new HashMap<String, String>();
			mapSet.put("data", setTemp);
			mapSet.put("type", "temp");
			mapSet.put("dev", deviceId);
			mapSet.put("num", containerId);
			// 冷机类型 thermoking：冷王 carrier：开利
			mapSet.put("chillerType", chillerType);
			Map<String, Object> getMap = psd.dataResponse(mapSet);
			mess = (String) getMap.get("msg");
			String status = (String) getMap.get("flag");
			CommandStore cs = (CommandStore) getMap.get("object");
			if (cs != null) {
				cs.setDisplay(display);
				cs.setUserName(userName);
				daoService.insertCommandStore(cs);
			}
			if (status.equals("0")) {
				container.setSetTemp(Double.valueOf(setTemp));
				daoService.modifyContainerById(container);
				CommandStore cs1 = (CommandStore) getMap.get("object");
				mess = "温度设置发送成功！";
				flag = ConstantCode.SUCCESS;
			} else {
				// 缓存该命令
				Gson gson = new Gson();
				OfflineOrder offlineOrder = new OfflineOrder();
				offlineOrder.setContainerId(containerId);
				offlineOrder.setDeviceId(deviceId);
				offlineOrder.setChillerType(chillerType);
				offlineOrder.setUserName(userName);
				offlineOrder.setReturnVal((String) getMap.get("returnVal"));
				offlineOrder.setData(setTemp);
				offlineOrder.setType("temp");
				redisTemplate.opsForHash().put("offlineorder",
						offlineOrder.getDeviceId(), gson.toJson(offlineOrder));
				mess = mess + " : 该命令已经缓存";
			}
		}
		return new JsonResult<Container>(flag, mess, container);
	}

	/**
	 * 除霜设置
	 */
	@ResponseBody
	@RequestMapping(value = "/bootDef")
	public JsonResult<Container> bootDef(HttpServletRequest request,
			String bootDef, String containerId, String deviceId,
			String chillerType) {

		String mess = "";
		int flag = ConstantCode.ERROR;

		User user = (User) request.getSession().getAttribute("user");
		String userName = null;
		if (user != null) {
			userName = user.getUsername();
		} else {
			mess = "用户信息有误，请重新登录！";
			return new JsonResult<Container>(flag, mess, null);
		}

		Container container = daoService.getContainerBycontarinId(containerId);
		Ccdata1 ccdata1 = daoService.selectCcdataByDeviceId1(container
				.getDeviceId());
		if (container == null || StringUtil.isEmpty(bootDef)) {
			mess = "操作指令失败！";
			return new JsonResult<Container>(flag, mess, container);
		}
		if (ccdata1.getRefSwiState().equals("off")
				|| StringUtil.isEmpty(ccdata1.getRefSwiState())) {
			mess = "请先开机";
			return new JsonResult<Container>(flag, mess, container);
		}
		String data = null;
		if (bootDef.equals("on")) {
			data = "bootDef";
			Map<String, String> mapSet = new HashMap<String, String>();
			mapSet.put("data", data);
			mapSet.put("type", "command");
			mapSet.put("dev", deviceId);
			mapSet.put("num", containerId);
			mapSet.put("chillerType", chillerType);
			Map<String, Object> getMap = psd.dataResponse(mapSet);
			mess = (String) getMap.get("msg");
			String status = (String) getMap.get("flag");
			CommandStore cs = (CommandStore) getMap.get("object");
			if (cs != null) {
				cs.setUserName(userName);
				daoService.insertCommandStore(cs);
			}
			if (status.equals("0")) {
				container.setBootDef(bootDef);
				daoService.modifyContainerById(container);
				mess = "发送成功";
				flag = ConstantCode.SUCCESS;
			} else {
				// 缓存该命令
				Gson gson = new Gson();
				OfflineOrder offlineOrder = new OfflineOrder();
				offlineOrder.setContainerId(containerId);
				offlineOrder.setDeviceId(deviceId);
				offlineOrder.setChillerType(chillerType);
				offlineOrder.setUserName(userName);
				offlineOrder.setReturnVal((String) getMap.get("returnVal"));
				offlineOrder.setData(data);
				offlineOrder.setType("command");
				redisTemplate.opsForHash().put("offlineorder",
						offlineOrder.getDeviceId(), gson.toJson(offlineOrder));
				mess = mess + " : 该命令已经缓存";
			}

		} else {
			mess = "指令不能为空！";
			flag = ConstantCode.ERROR;
		}

		return new JsonResult<Container>(flag, mess, container);
	}

	/**
	 * 清除告警设置
	 */
	@ResponseBody
	@RequestMapping(value = "/clearAlert")
	public JsonResult<Container> clearAlert(HttpServletRequest request,
			String clearAlert, String containerId, String deviceId,
			String chillerType) {

		String mess = "";
		int flag = ConstantCode.ERROR;

		User user = (User) request.getSession().getAttribute("user");
		String userName = null;
		if (user != null) {
			userName = user.getUsername();
		} else {
			mess = "用户信息有误，请重新登录！";
			return new JsonResult<Container>(flag, mess, null);
		}

		Container container = daoService.getContainerBycontarinId(containerId);
		// Ccdata1 ccdata1 =
		// daoService.selectCcdataByDeviceId1(container.getDeviceId());
		if (container == null || StringUtil.isEmpty(clearAlert)) {
			mess = "操作指令失败！";
			return new JsonResult<Container>(flag, mess, container);
		}
		/*
		 * if(ccdata1.getRefSwiState().equals("off") ||
		 * StringUtil.isEmpty(ccdata1.getRefSwiState())){ mess = "请先开机"; return
		 * new JsonResult<Container>(flag, mess, container); }
		 */
		String data = null;
		if (clearAlert.equals("on")) {
			data = "clearAlert";
			Map<String, String> mapSet = new HashMap<String, String>();
			mapSet.put("data", data);
			mapSet.put("type", "command");
			mapSet.put("dev", deviceId);
			mapSet.put("num", containerId);
			mapSet.put("chillerType", chillerType);
			Map<String, Object> getMap = psd.dataResponse(mapSet);
			mess = (String) getMap.get("msg");
			String status = (String) getMap.get("flag");
			CommandStore cs = (CommandStore) getMap.get("object");
			if (cs != null) {
				cs.setUserName(userName);
				daoService.insertCommandStore(cs);
			}
			if (status.equals("0")) {
				container.setClearAlert(clearAlert);
				daoService.modifyContainerById(container);
				mess = "告警设置发送成功";
				flag = ConstantCode.SUCCESS;
			} else {
				// 缓存该命令
				Gson gson = new Gson();
				OfflineOrder offlineOrder = new OfflineOrder();
				offlineOrder.setContainerId(containerId);
				offlineOrder.setDeviceId(deviceId);
				offlineOrder.setChillerType(chillerType);
				offlineOrder.setUserName(userName);
				offlineOrder.setReturnVal((String) getMap.get("returnVal"));
				offlineOrder.setData(data);
				offlineOrder.setType("command");
				redisTemplate.opsForHash().put("offlineorder",
						offlineOrder.getDeviceId(), gson.toJson(offlineOrder));
				mess = mess + " : 该命令已经缓存";
			}
		} else {
			mess = "指令不能为空！";
		}
		return new JsonResult<Container>(flag, mess, container);
	}

	/**
	 * 自检设置
	 */
	@ResponseBody
	@RequestMapping(value = "/selfCheck")
	public JsonResult<Container> selfCheck(HttpServletRequest request,
			String selfCheck, String containerId, String deviceId,
			String chillerType) {

		String mess = "";
		int flag = ConstantCode.ERROR;

		User user = (User) request.getSession().getAttribute("user");
		String userName = null;
		if (user != null) {
			userName = user.getUsername();
		} else {
			mess = "用户信息有误，请重新登录！";
			return new JsonResult<Container>(flag, mess, null);
		}

		Container container = daoService.getContainerBycontarinId(containerId);
		Ccdata1 ccdata1 = daoService.selectCcdataByDeviceId1(container
				.getDeviceId());
		String data = null;
		if (container == null || StringUtil.isEmpty(selfCheck)) {
			mess = "操作指令失败！";
			return new JsonResult<Container>(flag, mess, container);
		}
		if (ccdata1.getRefSwiState().equals("off")
				|| StringUtil.isEmpty(ccdata1.getRefSwiState())) {
			mess = "请先开机";
			return new JsonResult<Container>(flag, mess, container);
		}
		if (selfCheck.equals("on")) {
			data = "selfCheck";
			Map<String, String> mapSet = new HashMap<String, String>();
			mapSet.put("data", data);
			mapSet.put("type", "command");
			mapSet.put("dev", deviceId);
			mapSet.put("num", containerId);
			mapSet.put("chillerType", chillerType);
			Map<String, Object> getMap = psd.dataResponse(mapSet);
			mess = (String) getMap.get("msg");
			String status = (String) getMap.get("flag");
			CommandStore cs = (CommandStore) getMap.get("object");
			if (cs != null) {
				cs.setUserName(userName);
				daoService.insertCommandStore(cs);
			}
			if (status.equals("0")) {
				container.setSelfCheck(selfCheck);
				daoService.modifyContainerById(container);
				mess = "自检设置发送成功";
				flag = ConstantCode.SUCCESS;
			} else {
				// 缓存该命令
				Gson gson = new Gson();
				OfflineOrder offlineOrder = new OfflineOrder();
				offlineOrder.setContainerId(containerId);
				offlineOrder.setDeviceId(deviceId);
				offlineOrder.setChillerType(chillerType);
				offlineOrder.setUserName(userName);
				offlineOrder.setReturnVal((String) getMap.get("returnVal"));
				offlineOrder.setData(data);
				offlineOrder.setType("command");
				redisTemplate.opsForHash().put("offlineorder",
						offlineOrder.getDeviceId(), gson.toJson(offlineOrder));
				mess = mess + " : 该命令已经缓存";
			}
		} else {
			mess = "指令不能为空！";
		}

		return new JsonResult<Container>(flag, mess, container);
	}

	/**
	 * 冷机运行模式设置
	 */
	@ResponseBody
	@RequestMapping(value = "/refRunMode")
	public JsonResult<Container> refRunMode(HttpServletRequest request,
			String refRunMode, String containerId, String deviceId,
			String chillerType) {

		String mess = "";
		int flag = ConstantCode.ERROR;

		User user = (User) request.getSession().getAttribute("user");
		String userName = null;
		if (user != null) {
			userName = user.getUsername();
		} else {
			mess = "用户信息有误，请重新登录！";
			return new JsonResult<Container>(flag, mess, null);
		}

		Container container = daoService.getContainerBycontarinId(containerId);
		Ccdata1 ccdata1 = daoService.selectCcdataByDeviceId1(container
				.getDeviceId());
		Map<String, Object> valMap = new HashMap<String, Object>();
		String command = "refRunMode";
		valMap.put("command", command);
		valMap.put("containerId", containerId);
		CommandStore conS = daoService.selectCommandStoreCommandDescOne(valMap);
		String display = null;
		if (conS != null) {
			display = conS.getDisplay();
		}

		String data = null;
		if (container == null || StringUtil.isEmpty(refRunMode)) {
			mess = "操作指令失败！";
			return new JsonResult<Container>(flag, mess, container);
		}
		if (ccdata1.getRefSwiState().equals("off")
				|| StringUtil.isEmpty(ccdata1.getRefSwiState())) {
			mess = "请先开机";
			return new JsonResult<Container>(flag, mess, container);
		}
		if (refRunMode.equals("continuous")) {
			data = "continuous";
			Map<String, String> mapSet = new HashMap<String, String>();
			mapSet.put("data", data);
			mapSet.put("type", "command");
			mapSet.put("dev", deviceId);
			mapSet.put("num", containerId);
			mapSet.put("chillerType", chillerType);
			Map<String, Object> getMap = psd.dataResponse(mapSet);
			mess = (String) getMap.get("msg");
			String status = (String) getMap.get("flag");
			CommandStore cs = (CommandStore) getMap.get("object");
			if (cs != null) {
				cs.setDisplay(display);
				cs.setUserName(userName);
				daoService.insertCommandStore(cs);
			}
			if (status.equals("0")) {
				container.setRefRunMode(refRunMode);
				daoService.modifyContainerById(container);
				mess = "设定连续运转模式发送成功！";
				flag = ConstantCode.SUCCESS;
			} else {
				// 缓存该命令
				Gson gson = new Gson();
				OfflineOrder offlineOrder = new OfflineOrder();
				offlineOrder.setContainerId(containerId);
				offlineOrder.setDeviceId(deviceId);
				offlineOrder.setChillerType(chillerType);
				offlineOrder.setUserName(userName);
				offlineOrder.setReturnVal((String) getMap.get("returnVal"));
				offlineOrder.setData(data);
				offlineOrder.setType("command");
				redisTemplate.opsForHash().put("offlineorder",
						offlineOrder.getDeviceId(), gson.toJson(offlineOrder));
				mess = mess + " : 该命令已经缓存";
			}
		} else if (refRunMode.equals("auto")) {
			data = "auto";
			Map<String, String> mapSet = new HashMap<String, String>();
			mapSet.put("data", data);
			mapSet.put("type", "command");
			mapSet.put("dev", deviceId);
			mapSet.put("num", containerId);
			mapSet.put("chillerType", chillerType);
			Map<String, Object> getMap = psd.dataResponse(mapSet);
			mess = (String) getMap.get("msg");
			String status = (String) getMap.get("flag");
			CommandStore cs = (CommandStore) getMap.get("object");
			if (cs != null) {
				cs.setDisplay(display);
				cs.setUserName(userName);
				daoService.insertCommandStore(cs);
			}
			if (status.equals("0")) {
				container.setRefRunMode(refRunMode);
				daoService.modifyContainerById(container);
				mess = "启停运转模式发送成功！";
				flag = ConstantCode.SUCCESS;
			} else {
				// 缓存该命令
				Gson gson = new Gson();
				OfflineOrder offlineOrder = new OfflineOrder();
				offlineOrder.setContainerId(containerId);
				offlineOrder.setDeviceId(deviceId);
				offlineOrder.setChillerType(chillerType);
				offlineOrder.setUserName(userName);
				offlineOrder.setReturnVal((String) getMap.get("returnVal"));
				offlineOrder.setData(data);
				offlineOrder.setType("command");
				redisTemplate.opsForHash().put("offlineorder",
						offlineOrder.getDeviceId(), gson.toJson(offlineOrder));
				mess = mess + " : 该命令已经缓存";
			}
		}

		return new JsonResult<Container>(flag, mess, container);
	}

	/**
	 * 开关机设置
	 */
	@ResponseBody
	@RequestMapping(value = "/remoteSwiMac")
	public JsonResult<Container> remoteSwiMac(HttpServletRequest request,
			String remoteSwiMac, String containerId, String deviceId,
			String chillerType) {

		String mess = "";
		int flag = ConstantCode.ERROR;

		User user = (User) request.getSession().getAttribute("user");
		String userName = null;
		if (user != null) {
			userName = user.getUsername();
		} else {
			mess = "用户信息有误，请重新登录！";
			return new JsonResult<Container>(flag, mess, null);
		}

		Container container = daoService.getContainerBycontarinId(containerId);
		Map<String, Object> valMap = new HashMap<String, Object>();
		String command = "remoteSwiMac";
		valMap.put("command", command);
		valMap.put("containerId", containerId);
		CommandStore conS = daoService.selectCommandStoreCommandDescOne(valMap);
		String display = null;
		if (conS != null) {
			display = conS.getDisplay();
		}

		if (container == null || StringUtil.isEmpty(remoteSwiMac)) {
			mess = "操作指令失败！";
			return new JsonResult<Container>(flag, mess, container);
		}
		String data = null;
		if (remoteSwiMac.equals("on")) {
			data = "remoteSwiMacOn";
			Map<String, String> mapSet = new HashMap<String, String>();
			mapSet.put("data", data);
			mapSet.put("type", "command");
			mapSet.put("dev", deviceId);
			mapSet.put("num", containerId);
			mapSet.put("chillerType", chillerType);
			Map<String, Object> getMap = psd.dataResponse(mapSet);
			mess = (String) getMap.get("msg");
			String status = (String) getMap.get("flag");
			CommandStore cs = (CommandStore) getMap.get("object");
			if (cs != null) {
				cs.setDisplay(display);
				cs.setUserName(userName);
				daoService.insertCommandStore(cs);
			}
			if (status.equals("0")) {
				container.setRemoteSwiMac(remoteSwiMac);
				daoService.modifyContainerById(container);
				mess = "设置开机成功！";
				flag = ConstantCode.SUCCESS;
			} else {
				// 缓存该命令
				Gson gson = new Gson();
				OfflineOrder offlineOrder = new OfflineOrder();
				offlineOrder.setContainerId(containerId);
				offlineOrder.setDeviceId(deviceId);
				offlineOrder.setChillerType(chillerType);
				offlineOrder.setUserName(userName);
				offlineOrder.setReturnVal((String) getMap.get("returnVal"));
				offlineOrder.setData(data);
				offlineOrder.setType("command");
				redisTemplate.opsForHash().put("offlineorder",
						offlineOrder.getDeviceId(), gson.toJson(offlineOrder));
				mess = mess + " : 该命令已经缓存";
			}
		} else if (remoteSwiMac.equals("off")) {
			data = "remoteSwiMacOff";
			Map<String, String> mapSet = new HashMap<String, String>();
			mapSet.put("data", data);
			mapSet.put("type", "command");
			mapSet.put("dev", deviceId);
			mapSet.put("num", containerId);
			mapSet.put("chillerType", chillerType);
			Map<String, Object> getMap = psd.dataResponse(mapSet);
			mess = (String) getMap.get("msg");
			String status = (String) getMap.get("flag");
			CommandStore cs = (CommandStore) getMap.get("object");
			if (cs != null) {
				cs.setDisplay(display);
				cs.setUserName(userName);
				daoService.insertCommandStore(cs);
			}
			if (status.equals("0")) {
				container.setRemoteSwiMac(remoteSwiMac);
				daoService.modifyContainerById(container);
				mess = "设置关机成功！";
				flag = ConstantCode.SUCCESS;
			} else {
				// 缓存该命令
				Gson gson = new Gson();
				OfflineOrder offlineOrder = new OfflineOrder();
				offlineOrder.setContainerId(containerId);
				offlineOrder.setDeviceId(deviceId);
				offlineOrder.setChillerType(chillerType);
				offlineOrder.setUserName(userName);
				offlineOrder.setReturnVal((String) getMap.get("returnVal"));
				offlineOrder.setData(data);
				offlineOrder.setType("command");
				redisTemplate.opsForHash().put("offlineorder",
						offlineOrder.getDeviceId(), gson.toJson(offlineOrder));
				mess = mess + " : 该命令已经缓存";
			}
		}

		return new JsonResult<Container>(flag, mess, container);
	}

	/**
	 * 新风门开关机设置
	 */
	@ResponseBody
	@RequestMapping(value = "/remoteXFSwiMac")
	public JsonResult<Container> remoteXFSwiMac(HttpServletRequest request,
			String remoteXFSwiMac, String setCfm, String containerId,
			String deviceId, String chillerType) {

		String mess = "";
		int flag = ConstantCode.ERROR;

		User user = (User) request.getSession().getAttribute("user");
		String userName = null;
		if (user != null) {
			userName = user.getUsername();
		} else {
			mess = "用户信息有误，请重新登录！";
			return new JsonResult<Container>(flag, mess, null);
		}

		Container container = daoService.getContainerBycontarinId(containerId);
		Map<String, Object> valMap = new HashMap<String, Object>();
		String command = "remoteXFSwiMac";
		valMap.put("command", command);
		valMap.put("containerId", containerId);
		CommandStore conS = daoService.selectCommandStoreCommandDescOne(valMap);
		String display = null;
		if (conS != null) {
			display = conS.getDisplay();
		}

		if (container == null || StringUtil.isEmpty(remoteXFSwiMac)) {
			mess = "操作指令失败！";
			return new JsonResult<Container>(flag, mess, container);
		}
		String data = null;
		if (remoteXFSwiMac.equals("on")) {
			data = "remoteXFSwiMacOn";
			Map<String, String> mapSet = new HashMap<String, String>();
			mapSet.put("data", data);
			mapSet.put("type", "command");
			mapSet.put("dev", deviceId);
			mapSet.put("num", containerId);
			mapSet.put("chillerType", chillerType);
			Map<String, Object> getMap = psd.dataResponse(mapSet);
			mess = (String) getMap.get("msg");
			String status = (String) getMap.get("flag");
			CommandStore cs = (CommandStore) getMap.get("object");
			if (cs != null) {
				cs.setDisplay(display);
				cs.setUserName(userName);
				daoService.insertCommandStore(cs);
			}
			if (status.equals("0")) {
				container.setRemoteXFSwiMac(remoteXFSwiMac);
				daoService.modifyContainerById(container);
				mess = "设置开机成功！";
				flag = ConstantCode.SUCCESS;
			} else {
				// 缓存该命令
				Gson gson = new Gson();
				OfflineOrder offlineOrder = new OfflineOrder();
				offlineOrder.setContainerId(containerId);
				offlineOrder.setDeviceId(deviceId);
				offlineOrder.setChillerType(chillerType);
				offlineOrder.setUserName(userName);
				offlineOrder.setReturnVal((String) getMap.get("returnVal"));
				offlineOrder.setData(data);
				offlineOrder.setType("command");
				redisTemplate.opsForHash().put("offlineorder",
						offlineOrder.getDeviceId(), gson.toJson(offlineOrder));
				mess = mess + " : 该命令已经缓存";
			}
		} else if (remoteXFSwiMac.equals("off")) {
			data = "remoteXFSwiMacOff";
			Map<String, String> mapSet = new HashMap<String, String>();
			mapSet.put("data", data);
			mapSet.put("type", "command");
			mapSet.put("dev", deviceId);
			mapSet.put("num", containerId);
			mapSet.put("chillerType", chillerType);
			Map<String, Object> getMap = psd.dataResponse(mapSet);
			mess = (String) getMap.get("msg");
			String status = (String) getMap.get("flag");
			CommandStore cs = (CommandStore) getMap.get("object");
			if (cs != null) {
				cs.setDisplay(display);
				cs.setUserName(userName);
				daoService.insertCommandStore(cs);
			}
			if (status.equals("0")) {
				container.setRemoteXFSwiMac(remoteXFSwiMac);
				daoService.modifyContainerById(container);
				mess = "设置关机成功！";
				flag = ConstantCode.SUCCESS;
			} else {
				// 缓存该命令
				Gson gson = new Gson();
				OfflineOrder offlineOrder = new OfflineOrder();
				offlineOrder.setContainerId(containerId);
				offlineOrder.setDeviceId(deviceId);
				offlineOrder.setChillerType(chillerType);
				offlineOrder.setUserName(userName);
				offlineOrder.setReturnVal((String) getMap.get("returnVal"));
				offlineOrder.setData(data);
				offlineOrder.setType("command");
				redisTemplate.opsForHash().put("offlineorder",
						offlineOrder.getDeviceId(), gson.toJson(offlineOrder));
				mess = mess + " : 该命令已经缓存";
			}
		} else if (remoteXFSwiMac.equals("cfmOn")) {
			Map<String, String> mapSet = new HashMap<String, String>();
			mapSet.put("data", setCfm);
			mapSet.put("type", "cfm");
			mapSet.put("dev", deviceId);
			mapSet.put("num", containerId);
			// 冷机类型 thermoking：冷王 carrier：开利
			mapSet.put("chillerType", chillerType);
			Map<String, Object> getMap = psd.dataResponse(mapSet);
			mess = (String) getMap.get("msg");
			String status = (String) getMap.get("flag");
			CommandStore cs = (CommandStore) getMap.get("object");
			if (cs != null) {
				cs.setDisplay(display);
				cs.setUserName(userName);
				daoService.insertCommandStore(cs);
			}
			if (status.equals("0")) {
				container.setSetCfm(setCfm);
				daoService.modifyContainerById(container);
				CommandStore cs1 = (CommandStore) getMap.get("object");
				mess = "cfm设置发送成功！";
				flag = ConstantCode.SUCCESS;
			} else {
				// 缓存该命令
				Gson gson = new Gson();
				OfflineOrder offlineOrder = new OfflineOrder();
				offlineOrder.setContainerId(containerId);
				offlineOrder.setDeviceId(deviceId);
				offlineOrder.setChillerType(chillerType);
				offlineOrder.setUserName(userName);
				offlineOrder.setReturnVal((String) getMap.get("returnVal"));
				offlineOrder.setData(setCfm);
				offlineOrder.setType("cfm");
				redisTemplate.opsForHash().put("offlineorder",
						offlineOrder.getDeviceId(), gson.toJson(offlineOrder));
				mess = mess + " : 该命令已经缓存";
			}
		}

		return new JsonResult<Container>(flag, mess, container);
	}

	/**
	 * 展示冷藏箱的编号
	 * 
	 * @param request
	 * @param containerId
	 * @return
	 */

	@RequestMapping("/showContainer")
	public String showContainer(HttpServletRequest request, String username,
			Model model) {
		User user = daoService.getUserByUsername(username);
		Map<String, Object> map = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		List<Container> containers = daoService.getContainersByCompanyId(user
				.getCompanyId());
		for (int i = 0; i < containers.size(); i++) {
			String containerId = containers.get(i).getContainerId();
			list.add(i, containerId);
		}
		map.put("containerId", list);
		model.addAttribute("containerId", list);
		return "seting.jsp";
	}

	@RequestMapping("/showContainerchillerType")
	@ResponseBody
	public JSONObject showContainerchillerType(HttpServletRequest request,
			String containerId, HttpServletResponse response) {
		System.out.println(containerId);
		Container container = daoService.getContainerBycontarinId(containerId);
		String chillerType = container.getChillerType();
		System.out.println("chillerType" + chillerType);
		// Map<String, Object> map = new HashMap<String, Object>();
		// map.put("chillerType", chillerType);
		JSONObject jo = new JSONObject();
		jo.put("chillerType", chillerType);
		return jo;

	}

	@RequestMapping("/showCommandSetValue")
	@ResponseBody
	public String showCommandSetValue(HttpServletRequest request,

	@RequestParam String containerId, @RequestParam String commandType) {
		Ccdata1 ccdata1 = daoService.selectCcdataByContainerId1(containerId);
		String value = "";
		switch (commandType) {
		case "temSet":
			if ((Double) ccdata1.getTempSet() != null) {
				value = "" + ccdata1.getTempSet();
			}
			break;
		case "refRunMode":
			if (StringUtil.isNotEmpty(ccdata1.getRefRunMode())) {
				value = ccdata1.getRefRunMode();
			}
			break;
		case "remoteSwiMac":
			if (StringUtil.isNotEmpty(ccdata1.getRefSwiState())) {
				value = ccdata1.getRefSwiState();
			}
			break;
		case "remoteXFSwiMac":
			if (StringUtil.isNotEmpty(ccdata1.getRefXFSwiState())) {
				value = ccdata1.getRefXFSwiState();
			}
			break;
		}
		return value;
	}

}
