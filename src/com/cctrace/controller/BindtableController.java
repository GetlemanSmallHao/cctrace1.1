package com.cctrace.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cctrace.dao.AlertMapper;
import com.cctrace.dao.CcdataMapper1;
import com.cctrace.dao.OurCcdataMapper1;
import com.cctrace.entity.Alert;
import com.cctrace.entity.BindTable;
import com.cctrace.entity.Ccdata;
import com.cctrace.entity.Ccdata1;
import com.cctrace.entity.Container;
import com.cctrace.entity.Msg;
import com.cctrace.entity.OurCcdata1;
import com.cctrace.entity.TheNextStation;
import com.cctrace.entity.User;
import com.cctrace.entity.Yard;
import com.cctrace.service.DaoService;
import com.cctrace.utils.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;

@Controller
@RequestMapping(value = "pc/bindtable")
// 班列绑定信息
public class BindtableController {

	@Resource(name = "daoService")
	private DaoService daoService;
	@Autowired
	private AlertMapper alertMapper;
	@Autowired
	private OurCcdataMapper1 ourCcdataMapper1;
	@Autowired
	private CcdataMapper1 ccdataMapper1;

	/**
	 * 绑定信息查询 pc/bindtable/findBindtable.do
	 * 
	 * @return
	 */
	@RequestMapping("/findBindtable")
	public String findBindtable(HttpServletRequest request, ModelMap map) {

		BindTable bindTable = null;
		User user = (User) request.getSession().getAttribute("user");

		List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
		List<Ccdata> ccdatas = daoService
				.getLastCcdatasByGpsLongTimeAndDeviceMap(user.getCompanyId());
		// Collections.sort(ccdatas, new MapComparator());
		// System.out.println(ccdatas);
		for (Ccdata ccdata : ccdatas) {
			// System.out.println(ccdata);
			String containerId = ccdata.getContainerId();
			bindTable = daoService.getBindTableByContainerId(containerId);
			String routeType = bindTable.getRouteType();
			Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put("routeType", routeType);
			maps.add(map1);
		}
		map.addAttribute("ccdatas", ccdatas);
		map.addAttribute("maps", maps);
		// System.out.println(map);
		return "index.jsp";
	}

	/**
	 * 导入jackson包。
	 * 
	 * @param pn
	 * @return
	 */
	@RequestMapping("/findBindtableWithJson")
	@ResponseBody
	public Msg findBindtableWithJson(HttpServletRequest request,
			@RequestParam(value = "pn", defaultValue = "1") Integer pn,
			@RequestParam(defaultValue = "CIdASC") String order, ModelMap map) {
		if (StringUtil.isNotEmpty((String) request.getSession().getAttribute(
				"order"))) {
			String order1 = (String) request.getSession().getAttribute("order");
			if (!order1.equals(order)) {
				request.getSession().removeAttribute("order");
				request.getSession().setAttribute("order", order);
			}
		} else {
			request.getSession().setAttribute("order", order);
		}
		List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
		Integer totalPage = null;
		if ("coldAlertDESC".equals(order)) {
			User user = (User) request.getSession().getAttribute("user");
			Map<String, Object> map0 = new HashMap<String, Object>();
			map0.put("companyId", user.getCompanyId());
			Date now = new Date();
			long longNow = DateUtil.getLongFromDate(now);
			long beginTime = longNow - (1 * 24 * 60 * 60 * 1000);
			map0.put("start", beginTime);
			map0.put("stop", longNow);
			List<Alert> listA = alertMapper.selectAllAlertsByCompanyId(map0);

			if (listA.size() > 0) {
				totalPage = listA.size() % 12 == 0 ? listA.size() / 12 : listA
						.size() / 12 + 1;
			} else {
				totalPage = 1;
			}

			Integer count = null;

			if (listA.size() > 0) {
				for (Alert alert : listA) {
					Ccdata1 ccdata = daoService
							.selectCcdataByContainerId1(alert.getContainerId());
					BindTable bindTable = daoService
							.getBindTableByContainerId(alert.getContainerId());
					// Container container =
					// daoService.getContainerBycontarinId(ccdata
					// .getContainerId());
					OurCcdata1 ourCcdata = daoService
							.selectOurCcdataBycontainerId1(alert
									.getContainerId());
					Map<String, Object> mapAlert = new HashMap<String, Object>();
					Date nowDate2 = DateUtil.getNowDate();
					long start = DateUtil.getLongFromDate(nowDate2);
					long end = start - 24 * 60 * 60 * 1000;
					mapAlert.put("containerId", alert.getContainerId());
					mapAlert.put("startTime", end);
					mapAlert.put("endTime", start);
					String routeType;
					String buMenM;
					String trainId;
					String carGoType;
					String stationName = "";
					String yardName = "";
					int theNextStationId;
					int yardId;
					String nowTime;
					Double backWindTemp;
					Double oilLevel;
					Double setTemp;
					String refSwiState;
					String communicationState;
					String ifAlert = "have";

					if (null != bindTable.getRouteType()) {
						routeType = bindTable.getRouteType();
					} else {
						routeType = "";
					}
					if (null != bindTable.getBuMenM()) {
						buMenM = bindTable.getBuMenM();
					} else {
						buMenM = "";
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
									bindTable.getTheNextStationId())
									.getStationName();

					} else {
						stationName = "";
					}
					// 堆场名称
					if (null != bindTable.getYardId()) {
						if (daoService.getYardbyId(bindTable.getYardId()) != null) {
							yardName = daoService.getYardbyId(
									bindTable.getYardId()).getYardName();
						}
					} else {
						yardName = "";
					}
					// 系统时间
					if (null != ccdata.getNowTime()) {

						nowTime = ccdata.getNowTime();

					} else {
						nowTime = "";
					}
					// 开关机状态
					if (StringUtil.isNotEmpty(ccdata.getRefSwiState())) {

						refSwiState = ccdata.getRefSwiState();
						// 显示冷机是否报警
						if ("on".equals(refSwiState)) {
							// 显示通讯状态
							if (ccdata.getReceiveLongTime() != null) {
								long longTimeGet = ccdata.getReceiveLongTime();
								Date nowDate = DateUtil.getNowDate();
								long longTimeNow = DateUtil
										.getLongFromDate(nowDate);
								if ((longTimeNow - longTimeGet) > (2 * 60000)) {
									communicationState = "断开";
								} else {
									communicationState = "连接";
								}
							} else {
								communicationState = "";
							}

							Map<String, Object> map1 = new HashMap<String, Object>();
							map1.put("ccdata", ccdata);
							map1.put("ourccdata", ourCcdata);
							map1.put("bindtable",bindTable);
							map1.put("trainId", trainId);
							map1.put("carGoType", carGoType);
							map1.put("stationName", stationName);
							map1.put("yardName", yardName);
							map1.put("nowTime", nowTime);
							map1.put("routeType", routeType);
							map1.put("backWindTemp", ccdata.getBackWindTemp());
							map1.put("oilLevel", ourCcdata.getOilLevel());
							map1.put("setTemp", ccdata.getTempSet());
							map1.put("refSwiState", refSwiState);
							map1.put("communicationState", communicationState);
							map1.put("ifAlert", ifAlert);
							map1.put("remark", bindTable.getRemark());
							map1.put("buMenM", bindTable.getBuMenM());
							maps.add(map1);
						}
					}
				}
			}

			map.addAttribute("maps", maps);

		} else if ("positionDESC".equals(order) || "positionASC".equals(order)) {
			User user = (User) request.getSession().getAttribute("user");
			List<OurCcdata1> ocd = null;
			List<OurCcdata1> ocd1 = null;
			Integer count = null;
			switch (order) {
			case "positionDESC":
				PageHelper.startPage(pn, 12);
				ocd = ourCcdataMapper1
						.selectOurCcdata1DescPositionByDeviceId(user
								.getCompanyId());
				ocd1 = ocd;
				break;
			case "positionASC":
				PageHelper.startPage(pn, 12);
				ocd = ourCcdataMapper1
						.selectOurCcdata1AscPositionByDeviceId(user
								.getCompanyId());

				ocd1 = ocd;
				break;
			}
			PageInfo pageCcdata = new PageInfo(ocd, 5);
			totalPage = pageCcdata.getPages();
			for (OurCcdata1 ourccdata : ocd1) {
				BindTable bindTable = daoService
						.getBindTableByContainerId(ourccdata.getContainerId());
				// Container container =
				// daoService.getContainerBycontarinId(ourccdata
				// .getContainerId());
				Ccdata1 ccdata1 = daoService
						.selectCcdataByContainerId1(ourccdata.getContainerId());
				Map<String, Object> mapAlert = new HashMap<String, Object>();
				Date nowDate2 = DateUtil.getNowDate();
				long start = DateUtil.getLongFromDate(nowDate2);
				long end = start - 24 * 60 * 60 * 1000;
				mapAlert.put("containerId", ourccdata.getContainerId());
				mapAlert.put("startTime", end);
				mapAlert.put("endTime", start);
				count = daoService.selectAlertInBeforeSeconds(mapAlert);
				String routeType;
				String buMenM;
				String trainId;
				String carGoType;
				String stationName = "";
				String yardName = "";
				int theNextStationId;
				int yardId;
				String nowTime;
				Double backWindTemp;
				Double oilLevel;
				Double setTemp;
				String refSwiState;
				String communicationState;
				String ifAlert = "";

				if (null != bindTable.getRouteType()) {
					routeType = bindTable.getRouteType();
				} else {
					routeType = "";
				}
				if (null != bindTable.getBuMenM()) {
					buMenM = bindTable.getBuMenM();
				} else {
					buMenM = "";
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
								bindTable.getTheNextStationId())
								.getStationName();

				} else {
					stationName = "";
				}
				// 堆场名称
				if (null != bindTable.getYardId()) {
					if (daoService.getYardbyId(bindTable.getYardId()) != null) {
						yardName = daoService
								.getYardbyId(bindTable.getYardId())
								.getYardName();
					}
				} else {
					yardName = "";
				}
				// 系统时间
				if (null != ccdata1.getNowTime()) {

					nowTime = ccdata1.getNowTime();

				} else {
					nowTime = "";
				}
				// 开关机状态
				if (StringUtil.isNotEmpty(ccdata1.getRefSwiState())) {

					refSwiState = ccdata1.getRefSwiState();
					// 显示冷机是否报警
					if (refSwiState.equals("on") && count > 0) {
						ifAlert = "have";
					} else if (refSwiState.equals("on") && (count <= 0)) {
						ifAlert = "no";
					} else if (refSwiState.equals("off")) {
						ifAlert = "";
					}
				} else {
					refSwiState = "";
				}
				// 显示通讯状态
				if (ccdata1.getReceiveLongTime() != null) {
					long longTimeGet = ccdata1.getReceiveLongTime();
					Date nowDate = DateUtil.getNowDate();
					long longTimeNow = DateUtil.getLongFromDate(nowDate);
					if ((longTimeNow - longTimeGet) > (2 * 60000)) {
						communicationState = "断开";
					} else {
						communicationState = "连接";
					}
				} else {
					communicationState = "";
				}

				Map<String, Object> map1 = new HashMap<String, Object>();
				map1.put("ccdata", ccdata1);
				map1.put("ourccdata", ourccdata);
				map1.put("bindtable",bindTable);
				map1.put("trainId", trainId);
				map1.put("carGoType", carGoType);
				map1.put("stationName", stationName);
				map1.put("yardName", yardName);
				map1.put("nowTime", nowTime);
				map1.put("routeType", routeType);
				map1.put("backWindTemp", ccdata1.getBackWindTemp());
				map1.put("oilLevel", ourccdata.getOilLevel());
				map1.put("setTemp", ccdata1.getTempSet());
				map1.put("refSwiState", refSwiState);
				map1.put("communicationState", communicationState);
				map1.put("ifAlert", ifAlert);
				map1.put("remark", bindTable.getRemark());
				map1.put("buMenM", bindTable.getBuMenM());
				maps.add(map1);
			}

		} else if ("lcxModelDESC".equals(order) || "lcxModelASC".equals(order)) {
			User user = (User) request.getSession().getAttribute("user");
			List<Ccdata1> cc1 = null;
			Integer count = null;
			switch (order) {
			case "lcxModelDESC":
				PageHelper.startPage(pn, 12);
				cc1 = ccdataMapper1.selectCcdataLcxModelDescBycompanyId1(user
						.getCompanyId());
				break;
			case "lcxModelASC":
				PageHelper.startPage(pn, 12);
				cc1 = ccdataMapper1.selectCcdataLcxModelAscBycompanyId1(user
						.getCompanyId());
				break;
			}
			PageInfo pageCcdata = new PageInfo(cc1, 5);
			totalPage = pageCcdata.getPages();
			for (Ccdata1 ourccdata : cc1) {
				BindTable bindTable = daoService
						.getBindTableByContainerId(ourccdata.getContainerId());
				// Container container =
				// daoService.getContainerBycontarinId(ourccdata
				// .getContainerId());
				Ccdata1 ccdata1 = daoService
						.selectCcdataByContainerId1(ourccdata.getContainerId());
				Map<String, Object> mapAlert = new HashMap<String, Object>();
				Date nowDate2 = DateUtil.getNowDate();
				long start = DateUtil.getLongFromDate(nowDate2);
				long end = start - 24 * 60 * 60 * 1000;
				mapAlert.put("containerId", ourccdata.getContainerId());
				mapAlert.put("startTime", end);
				mapAlert.put("endTime", start);
				count = daoService.selectAlertInBeforeSeconds(mapAlert);
				String routeType;
				String buMenM;
				String trainId;
				String carGoType;
				String stationName = "";
				String yardName = "";
				int theNextStationId;
				int yardId;
				String nowTime;
				Double backWindTemp;
				Double oilLevel;
				Double setTemp;
				String refSwiState;
				String communicationState;
				String ifAlert = "";

				if (null != bindTable.getRouteType()) {
					routeType = bindTable.getRouteType();
				} else {
					routeType = "";
				}
				if (null != bindTable.getBuMenM()) {
					buMenM = bindTable.getBuMenM();
				} else {
					buMenM = "";
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
								bindTable.getTheNextStationId())
								.getStationName();

				} else {
					stationName = "";
				}
				// 堆场名称
				if (null != bindTable.getYardId()) {
					if (daoService.getYardbyId(bindTable.getYardId()) != null) {
						yardName = daoService
								.getYardbyId(bindTable.getYardId())
								.getYardName();
					}
				} else {
					yardName = "";
				}
				// 系统时间
				if (null != ccdata1.getNowTime()) {

					nowTime = ccdata1.getNowTime();

				} else {
					nowTime = "";
				}
				// 开关机状态
				if (StringUtil.isNotEmpty(ccdata1.getRefSwiState())) {

					refSwiState = ccdata1.getRefSwiState();
					// 显示冷机是否报警
					if (refSwiState.equals("on") && count > 0) {
						ifAlert = "have";
					} else if (refSwiState.equals("on") && (count <= 0)) {
						ifAlert = "no";
					} else if (refSwiState.equals("off")) {
						ifAlert = "";
					}
				} else {
					refSwiState = "";
				}
				// 显示通讯状态
				if (ccdata1.getReceiveLongTime() != null) {
					long longTimeGet = ccdata1.getReceiveLongTime();
					Date nowDate = DateUtil.getNowDate();
					long longTimeNow = DateUtil.getLongFromDate(nowDate);
					if ((longTimeNow - longTimeGet) > (2 * 60000)) {
						communicationState = "断开";
					} else {
						communicationState = "连接";
					}
				} else {
					communicationState = "";
				}

				Map<String, Object> map1 = new HashMap<String, Object>();
				map1.put("ccdata", ccdata1);
				map1.put("ourccdata", ourccdata);
				map1.put("bindtable",bindTable);
				map1.put("trainId", trainId);
				map1.put("carGoType", carGoType);
				map1.put("stationName", stationName);
				map1.put("yardName", yardName);
				map1.put("nowTime", nowTime);
				map1.put("routeType", routeType);
				map1.put("backWindTemp", ccdata1.getBackWindTemp());
				map1.put("oilLevel", ourccdata.getOilLevel());
				map1.put("setTemp", ccdata1.getTempSet());
				map1.put("refSwiState", refSwiState);
				map1.put("communicationState", communicationState);
				map1.put("ifAlert", ifAlert);
				map1.put("remark", bindTable.getRemark());
				map1.put("buMenM", bindTable.getBuMenM());
				maps.add(map1);
			}

		} else {

			String commandType = "";
			String value = "";
			Integer count = null;
			User user = (User) request.getSession().getAttribute("user");
			/*
			 * List<Ccdata1> cc2 = daoService.selectCcdatasBycompanyId1(user
			 * .getCompanyId()); if (cc2.size() > 0) { totalPage = cc2.size() %
			 * 12 == 0 ? cc2.size() / 12 : cc2.size() / 12 + 1; } else {
			 * totalPage = 1; }
			 */
			List<Ccdata1> cc1 = null;
			List<OurCcdata1> occ1 = null;
			List<BindTable> bindTables = null;
			switch (order) {
			case "CIdDESC":
				PageHelper.startPage(pn, 12);
				cc1 = daoService.selectCcdatasBycompanyId1AndCIdDESC(user
						.getCompanyId());
				break;
			case "CIdASC":
				PageHelper.startPage(pn, 12);
				cc1 = daoService.selectCcdatasBycompanyId1AndCIdASC(user
						.getCompanyId());
				break;
			case "NowTimeASC":
				PageHelper.startPage(pn, 12);
				cc1 = daoService.selectCcdatasBycompanyId1AndNowTimeASC(user
						.getCompanyId());
				break;
			case "NowTimeDESC":
				PageHelper.startPage(pn, 12);
				cc1 = daoService.selectCcdatasBycompanyId1AndNowTimeDESC(user
						.getCompanyId());
				break;
			case "TempSetASC":
				PageHelper.startPage(pn, 12);
				cc1 = daoService.selectCcdatasBycompanyId1AndSetTempASC(user
						.getCompanyId());
				break;
			case "TempSetDESC":
				PageHelper.startPage(pn, 12);
				cc1 = daoService.selectCcdatasBycompanyId1AndSetTempDESC(user
						.getCompanyId());
				break;
			case "BackWindTempASC":
				PageHelper.startPage(pn, 12);
				cc1 = daoService.selectCcdatasBycompanyId1AndBackWindASC(user
						.getCompanyId());
				break;
			case "BackWindTempDESC":
				PageHelper.startPage(pn, 12);
				cc1 = daoService.selectCcdatasBycompanyId1AndBackWindDESC(user
						.getCompanyId());
				break;
			case "RefBatVolASC":
				PageHelper.startPage(pn, 12);
				cc1 = daoService.selectCcdatasBycompanyId1AndRefBatVolASC(user
						.getCompanyId());
				break;
			case "RefBatVolDESC":
				PageHelper.startPage(pn, 12);
				cc1 = daoService.selectCcdatasBycompanyId1AndRefBatVolDESC(user
						.getCompanyId());
				break;
			case "OurCCNowTimeASC":
				PageHelper.startPage(pn, 12);
				occ1 = daoService
						.selectOurCcdatas1BycompanyId1AndNowTimeASC(user
								.getCompanyId());
				break;
			case "OurCCNowTimeDESC":
				PageHelper.startPage(pn, 12);
				occ1 = daoService
						.selectOurCcdatas1BycompanyId1AndNowTimeDESC(user
								.getCompanyId());
				break;
			case "GPSPowerDESC":
				PageHelper.startPage(pn, 12);
				occ1 = daoService
						.selectOurCcdatas1BycompanyId1AndGPSPowerDESC(user
								.getCompanyId());
				break;
			case "GPSPowerASC":
				PageHelper.startPage(pn, 12);
				occ1 = daoService
						.selectOurCcdatas1BycompanyId1AndGPSPowerASC(user
								.getCompanyId());
			case "RemarkDESC":
				PageHelper.startPage(pn, 12);
				bindTables = daoService
						.selectBindTableByCompanyAndRemarkDESC(user
								.getCompanyId());
				break;
			case "RemarkASC":
				PageHelper.startPage(pn, 12);
				bindTables = daoService
						.selectBindTableByCompanyAndRemarkASC(user
								.getCompanyId());
				break;

			}
			if (order.equals("GPSPowerASC") || order.equals("GPSPowerDESC")
					|| order.equals("OurCCNowTimeDESC")
					|| order.equals("OurCCNowTimeASC")) {
				PageInfo pageCcdata = new PageInfo(occ1, 5);
				totalPage = pageCcdata.getPages();
				for (OurCcdata1 ourccdata : occ1) {
					BindTable bindTable = daoService
							.getBindTableByContainerId(ourccdata
									.getContainerId());
					// Container container =
					// daoService.getContainerBycontarinId(ourccdata
					// .getContainerId());
					Ccdata1 ccdata1 = daoService
							.selectCcdataByContainerId1(ourccdata
									.getContainerId());
					Map<String, Object> mapAlert = new HashMap<String, Object>();
					Date nowDate2 = DateUtil.getNowDate();
					long start = DateUtil.getLongFromDate(nowDate2);
					long end = start - 24 * 60 * 60 * 1000;
					mapAlert.put("containerId", ourccdata.getContainerId());
					mapAlert.put("startTime", end);
					mapAlert.put("endTime", start);
					count = daoService.selectAlertInBeforeSeconds(mapAlert);
					String routeType;
					String buMenM;
					String trainId;
					String carGoType;
					String stationName = "";
					String yardName = "";
					int theNextStationId;
					int yardId;
					String nowTime;
					Double backWindTemp;
					Double oilLevel;
					Double setTemp;
					String refSwiState;
					String communicationState;
					String ifAlert = "";

					if (null != bindTable.getRouteType()) {
						routeType = bindTable.getRouteType();
					} else {
						routeType = "";
					}
					if (null != bindTable.getBuMenM()) {
						buMenM = bindTable.getBuMenM();
					} else {
						buMenM = "";
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
									bindTable.getTheNextStationId())
									.getStationName();

					} else {
						stationName = "";
					}
					// 堆场名称
					if (null != bindTable.getYardId()) {
						if (daoService.getYardbyId(bindTable.getYardId()) != null) {
							yardName = daoService.getYardbyId(
									bindTable.getYardId()).getYardName();
						}
					} else {
						yardName = "";
					}
					// 系统时间
					if (null != ccdata1.getNowTime()) {

						nowTime = ccdata1.getNowTime();

					} else {
						nowTime = "";
					}
					// 开关机状态
					if (StringUtil.isNotEmpty(ccdata1.getRefSwiState())) {

						refSwiState = ccdata1.getRefSwiState();
						// 显示冷机是否报警
						if (refSwiState.equals("on") && count > 0) {
							ifAlert = "have";
						} else if (refSwiState.equals("on") && (count <= 0)) {
							ifAlert = "no";
						} else if (refSwiState.equals("off")) {
							ifAlert = "";
						}
					} else {
						refSwiState = "";
					}
					// 显示通讯状态
					if (ccdata1.getReceiveLongTime() != null) {
						long longTimeGet = ccdata1.getReceiveLongTime();
						Date nowDate = DateUtil.getNowDate();
						long longTimeNow = DateUtil.getLongFromDate(nowDate);
						if ((longTimeNow - longTimeGet) > (2 * 60000)) {
							communicationState = "断开";
						} else {
							communicationState = "连接";
						}
					} else {
						communicationState = "";
					}

					Map<String, Object> map1 = new HashMap<String, Object>();
					map1.put("ccdata", ccdata1);
					map1.put("ourccdata", ourccdata);
					map1.put("bindtable",bindTable);
					map1.put("trainId", trainId);
					map1.put("carGoType", carGoType);
					map1.put("stationName", stationName);
					map1.put("yardName", yardName);
					map1.put("nowTime", nowTime);
					map1.put("routeType", routeType);
					map1.put("backWindTemp", ccdata1.getBackWindTemp());
					map1.put("oilLevel", ourccdata.getOilLevel());
					map1.put("setTemp", ccdata1.getTempSet());
					map1.put("refSwiState", refSwiState);
					map1.put("communicationState", communicationState);
					map1.put("ifAlert", ifAlert);
					map1.put("remark", bindTable.getRemark());
					map1.put("buMenM", bindTable.getBuMenM());
					maps.add(map1);
				}
			} else if (order.equals("RemarkASC") || order.equals("RemarkDESC")) {
				PageInfo pageCcdata = new PageInfo(bindTables, 5);
				totalPage = pageCcdata.getPages();
				for (BindTable bindTable : bindTables) {
					Ccdata1 ccdata1 = daoService
							.selectCcdataByContainerId1(bindTable
									.getContainerId());
					// Container container =
					// daoService.getContainerBycontarinId(bindTable.getContainerId());
					OurCcdata1 ourCcdata = daoService
							.selectOurCcdataBycontainerId1(bindTable
									.getContainerId());
					Map<String, Object> mapAlert = new HashMap<String, Object>();
					Date nowDate2 = DateUtil.getNowDate();
					long start = DateUtil.getLongFromDate(nowDate2);
					long end = start - 24 * 60 * 60 * 1000;
					mapAlert.put("containerId", ccdata1.getContainerId());
					mapAlert.put("startTime", end);
					mapAlert.put("endTime", start);
					count = daoService.selectAlertInBeforeSeconds(mapAlert);
					String routeType;
					String buMenM;
					String trainId;
					String carGoType;
					String stationName = "";
					String yardName = "";
					int theNextStationId;
					int yardId;
					String nowTime;
					Double backWindTemp;
					Double oilLevel;
					Double setTemp;
					String refSwiState;
					String communicationState;
					String ifAlert = "";

					if (null != bindTable.getRouteType()) {
						routeType = bindTable.getRouteType();
					} else {
						routeType = "";
					}
					if (null != bindTable.getBuMenM()) {
						buMenM = bindTable.getBuMenM();
					} else {
						buMenM = "";
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
									bindTable.getTheNextStationId())
									.getStationName();

					} else {
						stationName = "";
					}
					// 堆场名称
					if (null != bindTable.getYardId()) {
						if (daoService.getYardbyId(bindTable.getYardId()) != null) {
							yardName = daoService.getYardbyId(
									bindTable.getYardId()).getYardName();
						}
					} else {
						yardName = "";
					}
					// 系统时间
					if (null != ccdata1.getNowTime()) {

						nowTime = ccdata1.getNowTime();

					} else {
						nowTime = "";
					}
					// 开关机状态
					if (StringUtil.isNotEmpty(ccdata1.getRefSwiState())) {

						refSwiState = ccdata1.getRefSwiState();
						// 显示冷机是否报警
						if (refSwiState.equals("on") && count > 0) {
							ifAlert = "have";
						} else if (refSwiState.equals("on") && (count <= 0)) {
							ifAlert = "no";
						} else if (refSwiState.equals("off")) {
							ifAlert = "";
						}
					} else {
						refSwiState = "";
					}
					// 显示通讯状态
					if (ccdata1.getReceiveLongTime() != null) {
						long longTimeGet = ccdata1.getReceiveLongTime();
						Date nowDate = DateUtil.getNowDate();
						long longTimeNow = DateUtil.getLongFromDate(nowDate);
						if ((longTimeNow - longTimeGet) > (2 * 60000)) {
							communicationState = "断开";
						} else {
							communicationState = "连接";
						}
					} else {
						communicationState = "";
					}

					Map<String, Object> map1 = new HashMap<String, Object>();
					map1.put("ccdata", ccdata1);
					map1.put("ourccdata", ourCcdata);
					map1.put("bindtable",bindTable);
					map1.put("trainId", trainId);
					map1.put("carGoType", carGoType);
					map1.put("stationName", stationName);
					map1.put("yardName", yardName);
					map1.put("nowTime", nowTime);
					map1.put("routeType", routeType);
					map1.put("backWindTemp", ccdata1.getBackWindTemp());
					map1.put("oilLevel", ourCcdata.getOilLevel());
					map1.put("setTemp", ccdata1.getTempSet());
					map1.put("refSwiState", refSwiState);
					map1.put("communicationState", communicationState);
					map1.put("ifAlert", ifAlert);
					map1.put("remark", bindTable.getRemark());
					map1.put("buMenM", bindTable.getBuMenM());
					maps.add(map1);
				}
			} else {
				PageInfo pageCcdata = new PageInfo(cc1, 5);
				totalPage = pageCcdata.getPages();
				for (Ccdata1 ccdata : cc1) {
					BindTable bindTable = daoService
							.getBindTableByContainerId(ccdata.getContainerId());
					// Container container =
					// daoService.getContainerBycontarinId(ccdata
					// .getContainerId());
					OurCcdata1 ourCcdata = daoService
							.selectOurCcdataBycontainerId1(ccdata
									.getContainerId());
					Map<String, Object> mapAlert = new HashMap<String, Object>();
					Date nowDate2 = DateUtil.getNowDate();
					long start = DateUtil.getLongFromDate(nowDate2);
					long end = start - 24 * 60 * 60 * 1000;
					mapAlert.put("containerId", ccdata.getContainerId());
					mapAlert.put("startTime", end);
					mapAlert.put("endTime", start);
					count = daoService.selectAlertInBeforeSeconds(mapAlert);
					String routeType;
					String buMenM;
					String trainId;
					String carGoType;
					String stationName = "";
					String yardName = "";
					int theNextStationId;
					int yardId;
					String nowTime;
					Double backWindTemp;
					Double oilLevel;
					Double setTemp;
					String refSwiState;
					String communicationState;
					String ifAlert = "";

					if (null != bindTable.getRouteType()) {
						routeType = bindTable.getRouteType();
					} else {
						routeType = "";
					}
					if (null != bindTable.getBuMenM()) {
						buMenM = bindTable.getBuMenM();
					} else {
						buMenM = "";
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
									bindTable.getTheNextStationId())
									.getStationName();

					} else {
						stationName = "";
					}
					// 堆场名称
					if (null != bindTable.getYardId()) {
						if (daoService.getYardbyId(bindTable.getYardId()) != null) {
							yardName = daoService.getYardbyId(
									bindTable.getYardId()).getYardName();
						}
					} else {
						yardName = "";
					}
					// 系统时间
					if (null != ccdata.getNowTime()) {

						nowTime = ccdata.getNowTime();

					} else {
						nowTime = "";
					}
					// 开关机状态
					if (StringUtil.isNotEmpty(ccdata.getRefSwiState())) {

						refSwiState = ccdata.getRefSwiState();
						// 显示冷机是否报警
						if (refSwiState.equals("on") && count > 0) {
							ifAlert = "have";
						} else if (refSwiState.equals("on") && (count <= 0)) {
							ifAlert = "no";
						} else if (refSwiState.equals("off")) {
							ifAlert = "";
						}
					} else {
						refSwiState = "";
					}
					// 显示通讯状态
					if (ccdata.getReceiveLongTime() != null) {
						long longTimeGet = ccdata.getReceiveLongTime();
						Date nowDate = DateUtil.getNowDate();
						long longTimeNow = DateUtil.getLongFromDate(nowDate);
						if ((longTimeNow - longTimeGet) > (2 * 60000)) {
							communicationState = "断开";
						} else {
							communicationState = "连接";
						}
					} else {
						communicationState = "";
					}

					Map<String, Object> map1 = new HashMap<String, Object>();
					map1.put("ccdata", ccdata);
					map1.put("ourccdata", ourCcdata);
					map1.put("bindtable",bindTable);
					map1.put("trainId", trainId);
					map1.put("carGoType", carGoType);
					map1.put("stationName", stationName);
					map1.put("yardName", yardName);
					map1.put("nowTime", nowTime);
					map1.put("routeType", routeType);
					map1.put("backWindTemp", ccdata.getBackWindTemp());
					map1.put("oilLevel", ourCcdata.getOilLevel());
					map1.put("setTemp", ccdata.getTempSet());
					map1.put("refSwiState", refSwiState);
					map1.put("communicationState", communicationState);
					map1.put("ifAlert", ifAlert);
					map1.put("remark", bindTable.getRemark());
					map1.put("buMenM", bindTable.getBuMenM());
					maps.add(map1);
				}
			}
			map.addAttribute("maps", maps);

		}
		return Msg.success().add("maps", maps).add("totalSize", totalPage);
	}

	/**
	 * 冷藏箱预警信息展示排序
	 */

	public Msg findBindtableColdAlarm(HttpServletRequest request, ModelMap mol) {

		User user = (User) request.getSession().getAttribute("user");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("companyId", user.getCompanyId());
		Date now = new Date();
		long longNow = DateUtil.getLongFromDate(now);
		long beginTime = longNow - (1 * 24 * 60 * 60 * 1000);
		map.put("start", beginTime);
		map.put("stop", longNow);
		List<Alert> listA = alertMapper.selectAllAlertsByCompanyId(map);
		Integer totalPage = null;
		if (listA.size() > 0) {
			totalPage = listA.size() % 12 == 0 ? listA.size() / 12 : listA
					.size() / 12 + 1;
		} else {
			totalPage = 1;
		}

		Integer count = null;
		List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
		if (listA.size() > 0) {
			for (Alert alert : listA) {
				Ccdata1 ccdata = daoService.selectCcdataByContainerId1(alert
						.getContainerId());
				BindTable bindTable = daoService
						.getBindTableByContainerId(alert.getContainerId());
				// Container container =
				// daoService.getContainerBycontarinId(ccdata
				// .getContainerId());
				OurCcdata1 ourCcdata = daoService
						.selectOurCcdataBycontainerId1(alert.getContainerId());
				Map<String, Object> mapAlert = new HashMap<String, Object>();
				Date nowDate2 = DateUtil.getNowDate();
				long start = DateUtil.getLongFromDate(nowDate2);
				long end = start - 24 * 60 * 60 * 1000;
				mapAlert.put("containerId", alert.getContainerId());
				mapAlert.put("startTime", end);
				mapAlert.put("endTime", start);
				count = daoService.selectAlertInBeforeSeconds(mapAlert);
				String routeType;
				String buMenM;
				String trainId;
				String carGoType;
				String stationName = "";
				String yardName = "";
				int theNextStationId;
				int yardId;
				String nowTime;
				Double backWindTemp;
				Double oilLevel;
				Double setTemp;
				String refSwiState;
				String communicationState;
				String ifAlert = "";

				if (null != bindTable.getRouteType()) {
					routeType = bindTable.getRouteType();
				} else {
					routeType = "";
				}
				if (null != bindTable.getBuMenM()) {
					buMenM = bindTable.getBuMenM();
				} else {
					buMenM = "";
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
								bindTable.getTheNextStationId())
								.getStationName();

				} else {
					stationName = "";
				}
				// 堆场名称
				if (null != bindTable.getYardId()) {
					if (daoService.getYardbyId(bindTable.getYardId()) != null) {
						yardName = daoService
								.getYardbyId(bindTable.getYardId())
								.getYardName();
					}
				} else {
					yardName = "";
				}
				// 系统时间
				if (null != ccdata.getNowTime()) {

					nowTime = ccdata.getNowTime();

				} else {
					nowTime = "";
				}
				// 开关机状态
				if (StringUtil.isNotEmpty(ccdata.getRefSwiState())) {

					refSwiState = ccdata.getRefSwiState();
					// 显示冷机是否报警
					if (refSwiState.equals("on") && count > 0) {
						ifAlert = "have";
					} else if (refSwiState.equals("on") && (count <= 0)) {
						ifAlert = "no";
					} else if (refSwiState.equals("off")) {
						ifAlert = "";
					}
				} else {
					refSwiState = "";
				}
				// 显示通讯状态
				if (ccdata.getReceiveLongTime() != null) {
					long longTimeGet = ccdata.getReceiveLongTime();
					Date nowDate = DateUtil.getNowDate();
					long longTimeNow = DateUtil.getLongFromDate(nowDate);
					if ((longTimeNow - longTimeGet) > (2 * 60000)) {
						communicationState = "断开";
					} else {
						communicationState = "连接";
					}
				} else {
					communicationState = "";
				}

				Map<String, Object> map1 = new HashMap<String, Object>();
				map1.put("ccdata", ccdata);
				map1.put("ourccdata", ourCcdata);
				map1.put("bindtable",bindTable);
				map1.put("trainId", trainId);
				map1.put("carGoType", carGoType);
				map1.put("stationName", stationName);
				map1.put("yardName", yardName);
				map1.put("nowTime", nowTime);
				map1.put("routeType", routeType);
				map1.put("backWindTemp", ccdata.getBackWindTemp());
				map1.put("oilLevel", ourCcdata.getOilLevel());
				map1.put("setTemp", ccdata.getTempSet());
				map1.put("refSwiState", refSwiState);
				map1.put("communicationState", communicationState);
				map1.put("ifAlert", ifAlert);
				map1.put("remark", bindTable.getRemark());
				map1.put("buMenM", bindTable.getBuMenM());
				maps.add(map1);
			}
		}

		mol.addAttribute("maps", maps);

		return Msg.success().add("maps", maps).add("totalSize", totalPage);

	}

	/**
	 * 显示传感器的箱子
	 * 
	 * 
	 */
	@RequestMapping("/showSensorContainers")
	@ResponseBody
	public Msg showSensorContainers(HttpServletRequest request, ModelMap map) {
		User user = (User) request.getSession().getAttribute("user");
		List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
		List<Container> containers = null;
		if (daoService.getContainersByCompanyId(user.getCompanyId()).size() > 0) {
			containers = daoService.getContainersByCompanyId(user
					.getCompanyId());
		}
		for (Container container : containers) {
			Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put("container", container);
			maps.add(map1);
		}
		map.addAttribute("maps", maps);
		return Msg.success().add("maps", maps);
	}

	// 模糊查询
	@ResponseBody
	@RequestMapping("findBindtableBycompanyIdAndcontainerIdLikely")
	public Msg findBindtableBycompanyIdAndcontainerIdLikely(
			HttpServletRequest request, String containerId) {
		User user = (User) request.getSession().getAttribute("user");
		List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
		BindTable bindTable = null;
		Container container = null;
		OurCcdata1 ourCcdata = null;
		Map<String, Object> map = new HashMap<>();
		map.put("companyId", user.getCompanyId());
		map.put("containerId", containerId);
		Integer count = null;
		List<Ccdata1> ccdatas = daoService.selectIndexContainerIdLikey(map);
		// System.out.println("cCCCC" + ccdatas);

		for (Ccdata1 ccdata : ccdatas) {
			bindTable = daoService.getBindTableByContainerId(ccdata
					.getContainerId());
			container = daoService.getContainerBycontarinId(ccdata
					.getContainerId());
			ourCcdata = daoService.selectOurCcdataBycontainerId1(ccdata
					.getContainerId());

			Map<String, Object> mapAlert = new HashMap<String, Object>();
			Date nowDate2 = DateUtil.getNowDate();
			long start = DateUtil.getLongFromDate(nowDate2);
			long end = start - 24 * 60 * 60 * 1000;
			mapAlert.put("containerId", ccdata.getContainerId());
			mapAlert.put("startTime", end);
			mapAlert.put("endTime", start);
			count = daoService.selectAlertInBeforeSeconds(mapAlert);
			String routeType;
			String buMenM;
			String trainId;
			String carGoType;
			String stationName;
			String yardName;
			int theNextStationId;
			int yardId;
			String nowTime;
			Double backWindTemp;
			Double oilLevel;
			Double setTemp;
			String communicationState;
			String ifAlert = "";
			String refSwiState;

			if (null != bindTable.getRouteType()) {
				routeType = bindTable.getRouteType();
			} else {
				routeType = "";
			}
			if (null != bindTable.getBuMenM()) {
				buMenM = bindTable.getBuMenM();
			} else {
				buMenM = "";
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
				stationName = daoService.getTheNextStationById(
						bindTable.getTheNextStationId()).getStationName();
				// 此处error
			} else {
				stationName = "";
			}
			// 堆场名称
			if (null != bindTable.getYardId()) {
				// System.out.println("+++" + bindTable.getYardId());
				// System.out.println("+++"
				// + daoService.getYardbyId(bindTable.getYardId()));
				yardName = daoService.getYardbyId(bindTable.getYardId())
						.getYardName();

			} else {
				yardName = "";
			}
			if (null != ccdata.getNowTime()) {

				nowTime = ccdata.getNowTime();

			} else {
				nowTime = "";
			}
			// 显示通讯状态
			if (ccdata.getReceiveLongTime() != null) {
				long longTimeGet = ccdata.getReceiveLongTime();
				Date nowDate = DateUtil.getNowDate();
				long longTimeNow = DateUtil.getLongFromDate(nowDate);
				if ((longTimeNow - longTimeGet) > (2 * 60000)) {
					communicationState = "断开";
				} else {
					communicationState = "连接";
				}
			} else {
				communicationState = "";
			}

			// 开关机状态
			if (StringUtil.isNotEmpty(ccdata.getRefSwiState())) {

				refSwiState = ccdata.getRefSwiState();
				// 显示冷机是否报警
				if (refSwiState.equals("on") && count > 0) {
					ifAlert = "have";
				} else if (refSwiState.equals("on") && (count <= 0)) {
					ifAlert = "no";
				} else if (refSwiState.equals("off")) {
					ifAlert = "";
				}
			} else {
				refSwiState = "";
			}

			Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put("ccdata", ccdata);
			map1.put("ourccdata", ourCcdata);
			map1.put("bindtable",bindTable);
			map1.put("chillerType", container.getChillerType());
			map1.put("trainId", trainId);
			map1.put("carGoType", carGoType);
			map1.put("stationName", stationName);
			map1.put("yardName", yardName);
			map1.put("nowTime", nowTime);
			map1.put("routeType", routeType);
			map1.put("backWindTemp", ccdata.getBackWindTemp());
			map1.put("oilLevel", ourCcdata.getOilLevel());
			map1.put("setTemp", ccdata.getTempSet());
			map1.put("refSwiState", refSwiState);
			map1.put("ifAlert", ifAlert);
			map1.put("communicationState", communicationState);
			map1.put("remark", bindTable.getRemark());
			map1.put("buMenM", bindTable.getBuMenM());
			maps.add(map1);
		}
		// map.addAttribute("maps", maps);
		// System.out.println(map);

		return Msg.success().add("maps", maps);

	}

	// 单点定位（获取所有的设备）
	@RequestMapping("/findAllDevices")
	public String findAllDevices(HttpServletRequest request, ModelMap map) {
		User user = (User) request.getSession().getAttribute("user");
		List<BindTable> bindTables = daoService.getBindTablesByCompanyId(user
				.getCompanyId());
		System.out.println("11111" + bindTables);
		map.addAttribute("bindTables", bindTables);
		return "location.jsp";
	}

	// 单点定位的模糊查询冷藏箱
	@RequestMapping("/findDevicesLikeyInLocation")
	public String findDevicesLikeyInLocation(HttpServletRequest request,
			String containerId, ModelMap mmp) {
		List<BindTable> bindTables = null;
		bindTables = daoService.selectContainersLikey(containerId);
		mmp.addAttribute("bindTables", bindTables);
		return "showContainsInLocation.jsp";
	}

	// 运行轨迹
	@RequestMapping("/findAllDevicesForTrack")
	public String findAllDevicesForTrack(HttpSession session, ModelMap map) {
		User user = (User) session.getAttribute("user");
		List<BindTable> bindTables = daoService.getBindTablesByCompanyId(user
				.getCompanyId());
		map.addAttribute("bindTables", bindTables);
		return "track.jsp";
	}

	// 获取下货站下拉列表
	@ResponseBody
	@RequestMapping("findAllXhz")
	public Msg findAllXhz(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		List<TheNextStation> allTheNextStations = daoService
				.getAllTheNextStationsByCompanyId(user.getCompanyId());
		return Msg.success().add("allTheNextStations", allTheNextStations);

	}

	// 获取堆场下拉列表
	@ResponseBody
	@RequestMapping("findAllDc")
	public Msg findAllDc(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		List<Yard> allYars = daoService.getAllYarsByCompanyId(user
				.getCompanyId());
		return Msg.success().add("allYars", allYars);
	}

	// 修改绑定表信息
	@ResponseBody
	@RequestMapping("updateBindtableInfo")
	public Msg updateBindtableInfo(HttpServletRequest request,
			String containerId, String carGoType, String trainId,
			String stationName, String yardName, String remark, String lcxModel) {
		BindTable bindTable = daoService.getBindTableByContainerId(containerId);
		Ccdata1 cc1 = daoService.selectCcdataByContainerId1(containerId);
		Integer companyId = bindTable.getCompanyId();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("companyId", companyId);
		map.put("stationName", stationName);
		bindTable.setTrainId(trainId);
		Date date = new Date();
		String bindTime = DateUtil.getDateStr(date, "yyyy-MM-dd HH:mm:ss");
		long bindLongTime = DateUtil.getLongFromStr(bindTime,
				"yyyy-MM-dd HH:mm:ss");
		bindTable.setBindTime(bindTime);
		bindTable.setBindLongTime(bindLongTime);
		bindTable.setRemark(remark);
		if (StringUtil.isNotEmpty(lcxModel) && cc1 != null) {
			cc1.setLcxModel(lcxModel);
			daoService.updateCcdataById1(cc1);
		}
		if (stationName != null && stationName != "--请选择下货站--") {
			if (daoService.getTheNextStationBycompanyIdAndStationName(map) != null) {
				bindTable.setTheNextStationId(daoService
						.getTheNextStationBycompanyIdAndStationName(map)
						.getId());
			}
		}
		if (yardName != null && yardName != "--请选择堆场--") {
			if (daoService.getYardByYardName(yardName) != null) {
				bindTable.setYardId(daoService.getYardByYardName(yardName)
						.getId());
			}
		}
		// 设置货物类型
		bindTable.setCarGoType(carGoType);

		daoService.modifyBindTableById(bindTable);

		return Msg.success();
	}
}

class MapComparator implements Comparator<Ccdata1> {

	public int compare(Ccdata1 cc1, Ccdata1 cc2) {
		// Collections.sort(cc1, new MapComparator());
		return cc1.getContainerId().compareTo(cc2.getContainerId());
	}

}
