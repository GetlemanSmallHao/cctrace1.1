package com.cctrace.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cctrace.entity.BindTable;
import com.cctrace.entity.Ccdata;
import com.cctrace.entity.Ccdata1;
import com.cctrace.entity.Container;
import com.cctrace.entity.Msg;
import com.cctrace.entity.OurCcdata;
import com.cctrace.entity.OurCcdata1;
import com.cctrace.entity.User;
import com.cctrace.service.DaoService;
import com.cctrace.utils.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 传感器信息
 * 
 * @author wang-pc
 * 
 */
@Controller
@RequestMapping(value = "pc/sensor")
// 传感器 控制
public class SensorController {

	@Resource(name = "daoService")
	private DaoService daoService;

	/**
	 * 导入jackson包。
	 * 
	 * @param pn
	 * @return
	 */
	@RequestMapping("/findIndexSensorWithJson")
	@ResponseBody
	public Msg findIndexSensorWithJson(HttpServletRequest request,
			String containerId) {
		try {
			System.out.println(containerId);
			User user = (User) request.getSession().getAttribute("user");
			if (user.getRole().equalsIgnoreCase("common")) {

			}
			Ccdata1 lastCcdata = daoService.selectCcdataByContainerId1(containerId);
			List<Ccdata1> ccdatas = new ArrayList<Ccdata1>();
			ccdatas.add(lastCcdata);
/*		Ccdata lastCcdata = daoService.getLastCcdataByContainerId(containerId);
			List<Ccdata> ccdatas = new ArrayList<Ccdata>();
			ccdatas.add(lastCcdata);
*/
			BindTable bindTable = null;
			Container container = null;
			bindTable = daoService.getBindTableByContainerId(containerId);
			container = daoService.getContainerBycontarinId(containerId);
			List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
			Map<String, Object> map = new HashMap<String, Object>();
			for (int i = 0; i < ccdatas.size(); i++) {
				System.out.println(ccdatas.size());

				String routeType;
				String trainId;
				String carGoType;
				int theNextStationId;
				int yardId;
				Double backWindTemp;
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
				OurCcdata1 ourccdata = null;
				ourccdata = daoService.selectOurCcdataBycontainerId1(ccdatas.get(i).getContainerId());
				map.put("ccdata", ccdatas.get(i));
				map.put("ourccdata", ourccdata);
				map.put("trainId", trainId);
				map.put("theNextStationId", bindTable.getTheNextStationId());
				map.put("carGoType", carGoType);
				map.put("routeType", routeType);
				map.put("backWindTemp", ccdatas.get(i).getBackWindTemp());
				map.put("setTemp", ccdatas.get(i).getTempSet());
				map.put("refSwiState", ccdatas.get(i).getRefSwiState());
				maps.add(map);
			}

			return Msg.success().add("maps", maps);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("异常");
		}
		return null;

	}

	// /**
	// * 导入jackson包。
	// * @param pn
	// * @return
	// */
	// @RequestMapping("/findIndexSensorWithJson")
	// @ResponseBody
	// public Msg findIndexSensorWithJson(HttpServletRequest request,String
	// containerId) {
	//
	//
	// List<OurCcdata> ourccdatas = new ArrayList<OurCcdata>();
	// OurCcdata lastourCcdata=
	// daoService.getOurLastCcdataByContainerId(containerId);
	// ourccdatas.add(lastourCcdata);
	//
	// BindTable bindTable = null;
	// Container container =null;
	// bindTable = daoService.getBindTableByContainerId(containerId);
	// container = daoService.getContainerBycontarinId(containerId);
	// List<Map<String,Object>> maps = new ArrayList<Map<String,Object>>();
	// for(OurCcdata ourccdata:ourccdatas){
	// String routeType;
	// String trainId;
	// String carGoType;
	// int theNextStationId;
	// int yardId;
	// Double backWindTemp;
	// Double oilLevel;
	// Double setTemp;
	// Double oilTemp;
	// Double boxTemp;
	// Double tailBoxTemp;
	// Double gpsPower;
	// String receiveTime;
	// String nowTime;
	//
	//
	// if(null!=bindTable.getRouteType()){
	// routeType = bindTable.getRouteType();
	// }else{
	// routeType = "";
	// }
	// if(null!=bindTable.getTrainId()){
	// trainId = bindTable.getTrainId();
	// }else{
	// trainId = "";
	// }
	// if(null!=bindTable.getCarGoType()){
	// carGoType = bindTable.getCarGoType();
	// }else{
	// carGoType = "";
	// }
	// //
	// if(null!=bindTable.getTheNextStationId()){
	//
	// theNextStationId = bindTable.getTheNextStationId();
	//
	// }else{
	// theNextStationId = 0;
	// }
	// if(null!=ourccdata.getOilLevel()){
	//
	// oilLevel = ourccdata.getOilLevel();
	//
	// }else{
	// oilLevel = 0.00;
	// }
	//
	// if(null!=ourccdata.getBoxTemp()){
	//
	// boxTemp = ourccdata.getBoxTemp();
	//
	// }else{
	// boxTemp = 0.00;
	// }
	//
	// if(null!=ourccdata.getGpsPower()){
	//
	// gpsPower = ourccdata.getGpsPower();
	//
	// }else{
	// gpsPower = 0.00;
	// }
	//
	// if(null!=ourccdata.getOilTemp()){
	//
	// oilTemp = ourccdata.getOilTemp();
	//
	// }else{
	// oilTemp = 0.00;
	// }
	//
	// if(null!=ourccdata.getTailBoxTemp()){
	//
	// tailBoxTemp = ourccdata.getTailBoxTemp();
	//
	// }else{
	// tailBoxTemp = 0.00;
	// }
	//
	// if(null!=ourccdata.getReceiveTime()){
	//
	// receiveTime = ourccdata.getReceiveTime();
	//
	// }else{
	// receiveTime = "";
	// }
	//
	// if(null!=ourccdata.getNowTime()){
	//
	// nowTime = ourccdata.getNowTime();
	//
	// }else{
	// nowTime = "";
	// }
	// if(null!=bindTable.getYardId()){
	//
	// yardId = bindTable.getYardId();
	//
	// }else{
	// yardId = 0;
	// }
	//
	// if(null!=container.getSetTemp()){
	//
	// setTemp = container.getSetTemp();
	//
	// }else{
	// setTemp = 0.00;
	// }
	//
	// Map<String,Object> map = new HashMap<String,Object>();
	// map.put("ccdata", ourccdata);
	// map.put("trainId", trainId);
	// map.put("theNextStationId", theNextStationId);
	// map.put("carGoType", carGoType);
	// map.put("routeType", routeType);
	// // map.put("backWindTemp", backWindTemp);
	// map.put("setTemp", setTemp);
	//
	// maps.add(map);
	// }
	// System.out.println(maps);
	// return Msg.success().add("maps",maps);
	// }
	//

	/*	*//**
	 * 导入jackson包。
	 * 
	 * @param pn
	 * @return
	 */
	/*
	 * @RequestMapping("/findSensorWithJson")
	 * 
	 * @ResponseBody public Msg findSensorWithJson(HttpServletRequest
	 * request,String containerId,String startTime,String endTime) {
	 * 
	 * 
	 * long startLongTime =
	 * DateUtil.getLongFromStr(startTime,"yyyy-MM-dd HH:mm:ss"); long
	 * endLongTime = DateUtil.getLongFromStr(endTime,"yyyy-MM-dd HH:mm:ss");
	 * 
	 * Map<String,Object> mapp = new HashMap<String,Object>();
	 * mapp.put("containerId", containerId); mapp.put("start", startLongTime);
	 * mapp.put("end", endLongTime); List<Ccdata> ccdatas =
	 * daoService.getCcdatasByContainerIdBetweenTowTime(mapp);
	 * 
	 * BindTable bindTable = null; Container container =null; bindTable =
	 * daoService.getBindTableByContainerId(containerId); container =
	 * daoService.getContainerBycontarinId(containerId);
	 * List<Map<String,Object>> maps = new ArrayList<Map<String,Object>>();
	 * for(Ccdata ccdata:ccdatas){
	 * System.out.println("1111"+ccdata.getTailBoxTemp()); String routeType;
	 * String trainId; String carGoType; int theNextStationId; int yardId;
	 * Double backWindTemp; Double oilLevel; Double setTemp;
	 * 
	 * 
	 * if(null!=bindTable.getRouteType()){ routeType = bindTable.getRouteType();
	 * }else{ routeType = ""; } if(null!=bindTable.getTrainId()){ trainId =
	 * bindTable.getTrainId(); }else{ trainId = ""; }
	 * if(null!=bindTable.getCarGoType()){ carGoType = bindTable.getCarGoType();
	 * }else{ carGoType = ""; } // if(null!=bindTable.getTheNextStationId()){
	 * 
	 * theNextStationId = bindTable.getTheNextStationId();
	 * 
	 * }else{ theNextStationId = 0; } if(null!=bindTable.getYardId()){
	 * 
	 * yardId = bindTable.getYardId();
	 * 
	 * }else{ yardId = 0; }
	 * 
	 * if(null!=container.getSetTemp()){
	 * 
	 * setTemp = container.getSetTemp();
	 * 
	 * }else{ setTemp = 0.00; }
	 * 
	 * Map<String,Object> map = new HashMap<String,Object>(); map.put("ccdata",
	 * ccdata); map.put("trainId", trainId); map.put("theNextStationId",
	 * theNextStationId); map.put("carGoType", carGoType); map.put("routeType",
	 * routeType); map.put("setTemp", setTemp);
	 * 
	 * maps.add(map); } System.out.println(maps); return
	 * Msg.success().add("maps",maps); }
	 */

	/*	*//**
	 * 导入jackson包。
	 * 
	 * @param pn
	 * @return
	 */
	/*
	 * @RequestMapping("/findSensorWithJson")
	 * 
	 * @ResponseBody public Msg findSensorWithJson(HttpServletRequest
	 * request,String containerId,String startTime,String endTime) {
	 * 
	 * 
	 * long startLongTime =
	 * DateUtil.getLongFromStr(startTime,"yyyy-MM-dd HH:mm:ss"); long
	 * endLongTime = DateUtil.getLongFromStr(endTime,"yyyy-MM-dd HH:mm:ss");
	 * 
	 * Map<String,Object> mapp = new HashMap<String,Object>();
	 * mapp.put("containerId", containerId); mapp.put("start", startLongTime);
	 * mapp.put("end", endLongTime); List<Ccdata> ccdatas =
	 * daoService.getCcdatasByContainerIdBetweenTowTime(mapp);
	 * 
	 * BindTable bindTable = null; Container container =null; bindTable =
	 * daoService.getBindTableByContainerId(containerId); container =
	 * daoService.getContainerBycontarinId(containerId);
	 * List<Map<String,Object>> maps = new ArrayList<Map<String,Object>>();
	 * for(Ccdata ccdata:ccdatas){
	 * System.out.println("1111"+ccdata.getTailBoxTemp()); String routeType;
	 * String trainId; String carGoType; int theNextStationId; int yardId;
	 * Double backWindTemp; Double oilLevel; Double setTemp;
	 * 
	 * 
	 * if(null!=bindTable.getRouteType()){ routeType = bindTable.getRouteType();
	 * }else{ routeType = ""; } if(null!=bindTable.getTrainId()){ trainId =
	 * bindTable.getTrainId(); }else{ trainId = ""; }
	 * if(null!=bindTable.getCarGoType()){ carGoType = bindTable.getCarGoType();
	 * }else{ carGoType = ""; } // if(null!=bindTable.getTheNextStationId()){
	 * 
	 * theNextStationId = bindTable.getTheNextStationId();
	 * 
	 * }else{ theNextStationId = 0; } if(null!=bindTable.getYardId()){
	 * 
	 * yardId = bindTable.getYardId();
	 * 
	 * }else{ yardId = 0; }
	 * 
	 * if(null!=container.getSetTemp()){
	 * 
	 * setTemp = container.getSetTemp();
	 * 
	 * }else{ setTemp = 0.00; }
	 * 
	 * Map<String,Object> map = new HashMap<String,Object>(); map.put("ccdata",
	 * ccdata); map.put("trainId", trainId); map.put("theNextStationId",
	 * theNextStationId); map.put("carGoType", carGoType); map.put("routeType",
	 * routeType); map.put("setTemp", setTemp);
	 * 
	 * maps.add(map); } System.out.println(maps); return
	 * Msg.success().add("maps",maps); }
	 */

	/**
	 * 导入jackson包。
	 * 
	 * @param pn
	 * @return
	 */
	@RequestMapping("/findSensorWithJson")
	@ResponseBody
	public Msg findSensorWithJson(HttpServletRequest request,
			String containerId, String startTime, String endTime,
			@RequestParam(value = "pn", defaultValue = "1") Integer pn) {

		try {
			long startLongTime = DateUtil.getLongFromStr(startTime,
					"yyyy-MM-dd HH:mm:ss");
			long endLongTime = DateUtil.getLongFromStr(endTime,
					"yyyy-MM-dd HH:mm:ss");

			Map<String, Object> mapp = new HashMap<String, Object>();
			mapp.put("containerId", containerId);
			mapp.put("start", startLongTime);
			mapp.put("end", endLongTime);
			/*List<Ccdata> ccdatas1 = daoService
					.getCcdatasByContainerIdBetweenTowTime(mapp);*/
//		System.out.println(ccdatas1.size());
//		System.out.println(ccdatas1.size() % 25);
/*	Integer totalPage = null;
			if(ccdatas1.size() > 0){
				totalPage = ccdatas1.size() % 25 == 0 ? ccdatas1.size() / 25
							: ccdatas1.size() / 25 + 1;
			}else{
				totalPage = 1;
			}
				*/	

			// 在查询之前只需要调用，传入页码，以及每页的大小
			PageHelper.startPage(pn, 39);
//		List<Ccdata> ccdatas = ccdatas1;

			List<Ccdata> ccdatas = daoService
					.getCcdatasByContainerIdBetweenTowTime(mapp);
			// 封装了详细的分页信息,包括有我们查询出来的数据，传入连续显示的页数
			PageInfo pageCcdata = new PageInfo(ccdatas, 5);
//		BindTable bindTable = null;
//		Container container = null;
//		bindTable = daoService.getBindTableByContainerId(containerId);
//		container = daoService.getContainerBycontarinId(containerId);
			//List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
			/*Map<String, Object> page = new HashMap<>();
			page.put("pageNow", pn);
			page.put("totalPage", totalPage);
*/
			/*Map<String, Object> map = new HashMap<String, Object>();
			for (int i = 0; i < ccdatas.size(); i++) {
				System.out.println("1111" + ccdatas.get(i).getTailBoxTemp());
				String routeType;
				String trainId;
				String carGoType;
				int theNextStationId;
				int yardId;
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
				map = new HashMap<String, Object>();
				map.clear();
				map.put("ccdata", pageCcdata.getList().get(i));
//			map.put("ccdata", ccdatas.get(i));
//			map.put("oilLevel", ccdatas.get(i).getOilLevel());
//			map.put("trainId", trainId);
//			map.put("theNextStationId", bindTable.getTheNextStationId());
//			map.put("carGoType", carGoType);
//			map.put("routeType", routeType);
				map.put("setTemp", ccdatas.get(i).getTempSet());
				maps.add(map);
			}*/

			return Msg.success().add("page", pageCcdata);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("异常");
		}
		return null;
	}

	/**
	 * 导入jackson包。ourccdata展示
	 * 
	 * @param pn
	 * @return
	 */
	@RequestMapping("/findSensorWithJson1")
	@ResponseBody
	public Msg findSensorWithJson1(HttpServletRequest request,
			String containerId, String startTime, String endTime,
			@RequestParam(value = "pn", defaultValue = "1") Integer pn) {

		try {
			long startLongTime = DateUtil.getLongFromStr(startTime,
					"yyyy-MM-dd HH:mm:ss");
			long endLongTime = DateUtil.getLongFromStr(endTime,
					"yyyy-MM-dd HH:mm:ss");

			Map<String, Object> mapp = new HashMap<String, Object>();
			mapp.put("containerId", containerId);
			mapp.put("start", startLongTime);
			mapp.put("end", endLongTime);
/*		List<OurCcdata> ourccdatas1 = daoService
					.getOurCcdatasByContainerIdBetweenTowTime(mapp);
			Integer totalPage = null;
			if(ourccdatas1.size() > 0){
				totalPage = ourccdatas1.size() % 25 == 0 ? ourccdatas1.size() / 25
						    : ourccdatas1.size() / 25 + 1;
			}else{
				totalPage = 1;
			}
			Map<String, Object> page = new HashMap<>();
			page.put("pageNow", pn);
			page.put("totalPage", totalPage);*/
			// 在查询之前只需要调用，传入页码，以及每页的大小
			PageHelper.startPage(pn, 39);
//		List<OurCcdata> ourccdatas = ourccdatas1;
			List<OurCcdata> ourccdatas = daoService
					.getOurCcdatasByContainerIdBetweenTowTime(mapp);
			PageInfo pageOurCcdata = new PageInfo(ourccdatas, 5);

			/*List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
			Map<String, Object> map = new HashMap<String, Object>();

			for (int i = 0; i < ourccdatas.size(); i++) {
				map = new HashMap<String, Object>();
				map.clear();
				map.put("ourccdata", pageOurCcdata.getList().get(i));
				// map.put("ourccdata", ourccdatas.get(i));
				maps.add(map);
			}
*/
//		for (int i = 0; i < maps.size(); i++) {
//			System.out.println(maps.get(i));
//		}
			return Msg.success().add("page", pageOurCcdata);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("异常");
		}
		return null;
	}

	/**
	 * 、 备份一下
	 */

	// @RequestMapping("/findSensorWithJson3")
	// @ResponseBody
	// public Msg findSensorWithJson3(HttpServletRequest request,
	// String containerId, String startTime, String endTime) {
	//
	// long startLongTime = DateUtil.getLongFromStr(startTime,
	// "yyyy-MM-dd HH:mm:ss");
	// long endLongTime = DateUtil.getLongFromStr(endTime,
	// "yyyy-MM-dd HH:mm:ss");
	//
	// Map<String, Object> mapp = new HashMap<String, Object>();
	// mapp.put("containerId", containerId);
	// mapp.put("start", startLongTime);
	// mapp.put("end", endLongTime);
	//
	// // 在查询之前只需要调用，传入页码，以及每页的大小 // PageHelper.startPage(pn, 10);
	//
	// List<Ccdata> ccdatas = daoService
	// .getCcdatasByContainerIdBetweenTowTime(mapp);
	//
	// // // 封装了详细的分页信息,包括有我们查询出来的数据，传入连续显示的页数 PageInfo pageCcdata = new
	// // PageInfo(ccdatas, 5);
	//
	// // 在查询之前只需要调用，传入页码，以及每页的大小 PageHelper.startPage(pn, 15);
	//
	// List<OurCcdata> ourccdatas = daoService
	// .getOurCcdatasByContainerIdBetweenTowTime(mapp); // PageInfo
	// // pageOurCcdata = new PageInfo(ourccdatas, 5);
	//
	// BindTable bindTable = null;
	// Container container = null;
	// bindTable = daoService.getBindTableByContainerId(containerId);
	// container = daoService.getContainerBycontarinId(containerId);
	// List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
	// List<Map<String, Object>> maps1 = new ArrayList<Map<String, Object>>();
	// Map<String, Object> map = new HashMap<String, Object>();
	// Map<String, Object> mapocc = new HashMap<String, Object>();
	// for (int i = 0; i < ccdatas.size(); i++) {
	// System.out.println("1111" + ccdatas.get(i).getTailBoxTemp());
	// String routeType;
	// String trainId;
	// String carGoType;
	// int theNextStationId;
	// int yardId;
	// Double backWindTemp;
	// Double oilLevel;
	// Double setTemp;
	//
	// if (null != bindTable.getRouteType()) {
	// routeType = bindTable.getRouteType();
	// } else {
	// routeType = "";
	// }
	// if (null != bindTable.getTrainId()) {
	// trainId = bindTable.getTrainId();
	// } else {
	// trainId = "";
	// }
	// if (null != bindTable.getCarGoType()) {
	// carGoType = bindTable.getCarGoType();
	// } else {
	// carGoType = "";
	// }
	// map = new HashMap<String, Object>();
	// map.clear();
	// // map.put("ccdata", pageCcdata.getList().get(i));
	// map.put("ccdata", ccdatas.get(i));
	// map.put("oilLevel", ccdatas.get(i).getOilLevel());
	// map.put("trainId", trainId);
	// map.put("theNextStationId", bindTable.getTheNextStationId());
	// map.put("carGoType", carGoType);
	// map.put("routeType", routeType);
	// map.put("setTemp", container.getSetTemp());
	// maps.add(map);
	// }
	//
	// for (int i = 0; i < ourccdatas.size(); i++) {
	// mapocc = new HashMap<String, Object>();
	// mapocc.clear();
	// // mapocc.put("ourccdata", pageOurCcdata.getList().get(i));
	// mapocc.put("ourccdata", ourccdatas.get(i));
	// maps1.add(mapocc);
	// }
	//
	// for (int i = 0; i < maps.size(); i++) {
	// System.out.println(maps.get(i));
	// }
	// for (int i = 0; i < maps1.size(); i++) {
	// System.out.println(maps1.get(i));
	// }
	// return Msg.success().add("maps", maps).add("maps1", maps1);
	// }

	/**
	 * 模糊查询冷藏箱列表在传感器报告
	 * 
	 */
	@RequestMapping("/selectContainersLikeyInPCSersor")
	public String selectContainersLikeyInPCSersor(HttpServletRequest request,
			String containerId, ModelMap mmp) {
		try {
			User user = (User) request.getSession().getAttribute("user");
			Map<String, Object> map = new HashMap<>();
			map.put("companyId", user.getCompanyId());
			map.put("containerId", containerId);
			List<Ccdata1> ccdatas = daoService.selectIndexContainerIdLikey(map);
			mmp.addAttribute("ccdatas", ccdatas);
			return "showContainersInPCSensor.jsp";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("模糊查询报告异常");
		}
		return null;
	}
	/**
	 * 导入jackson包。
	 * 
	 * @param pn
	 * @return
	 */
	/*
	 * @RequestMapping("/findOurCcdataWithJson")
	 * 
	 * @ResponseBody public Msg findOurCcdataWithJson(HttpServletRequest
	 * request, String containerId, String startTime, String endTime) {
	 * 
	 * long startLongTime = DateUtil.getLongFromStr(startTime,
	 * "yyyy-MM-dd HH:mm:ss"); long endLongTime =
	 * DateUtil.getLongFromStr(endTime, "yyyy-MM-dd HH:mm:ss");
	 * 
	 * Map<String, Object> mapp = new HashMap<String, Object>();
	 * mapp.put("containerId", containerId); mapp.put("start", startLongTime);
	 * mapp.put("end", endLongTime); List<OurCcdata> ourccdatas = daoService
	 * .getOurCcdatasByContainerIdBetweenTowTime(mapp); List<Map<String,
	 * Object>> maps = new ArrayList<Map<String, Object>>(); Map<String, Object>
	 * mapocc = new HashMap<String, Object>();
	 * 
	 * for (int i = 0; i < ourccdatas.size(); i++) { mapocc.put("ourccdata",
	 * ourccdatas.get(i)); maps.add(mapocc); }
	 * System.out.println(ourccdatas.size()); return Msg.success().add("maps",
	 * maps); }
	 */

	/**
	 * 备份
	 * 
	 */
	// /**
	// * 导入jackson包。
	// * @param pn
	// * @return
	// */
	// @RequestMapping("/findSensorWithJson")
	// @ResponseBody
	// public Msg findSensorWithJson(HttpServletRequest request,String
	// containerId,String startTime,String endTime) {
	//
	//
	// long startLongTime =
	// DateUtil.getLongFromStr(startTime,"yyyy-MM-dd HH:mm:ss");
	// long endLongTime =
	// DateUtil.getLongFromStr(endTime,"yyyy-MM-dd HH:mm:ss");
	//
	// Map<String,Object> mapp = new HashMap<String,Object>();
	// mapp.put("containerId", containerId);
	// mapp.put("start", startLongTime);
	// mapp.put("end", endLongTime);
	// List<Ccdata> ccdatas =
	// daoService.getCcdatasByContainerIdBetweenTowTime(mapp);
	//
	// BindTable bindTable = null;
	// Container container =null;
	// bindTable = daoService.getBindTableByContainerId(containerId);
	// container = daoService.getContainerBycontarinId(containerId);
	// List<Map<String,Object>> maps = new ArrayList<Map<String,Object>>();
	// for(Ccdata ccdata:ccdatas){
	// System.out.println("1111"+ccdata.getTailBoxTemp());
	// String routeType;
	// String trainId;
	// String carGoType;
	// int theNextStationId;
	// int yardId;
	// Double backWindTemp;
	// Double oilLevel;
	// Double setTemp;
	//
	//
	// if(null!=bindTable.getRouteType()){
	// routeType = bindTable.getRouteType();
	// }else{
	// routeType = "";
	// }
	// if(null!=bindTable.getTrainId()){
	// trainId = bindTable.getTrainId();
	// }else{
	// trainId = "";
	// }
	// if(null!=bindTable.getCarGoType()){
	// carGoType = bindTable.getCarGoType();
	// }else{
	// carGoType = "";
	// }
	// //
	// if(null!=bindTable.getTheNextStationId()){
	//
	// theNextStationId = bindTable.getTheNextStationId();
	//
	// }else{
	// theNextStationId = 0;
	// }
	// if(null!=bindTable.getYardId()){
	//
	// yardId = bindTable.getYardId();
	//
	// }else{
	// yardId = 0;
	// }
	//
	// if(null!=container.getSetTemp()){
	//
	// setTemp = container.getSetTemp();
	//
	// }else{
	// setTemp = 0.00;
	// }
	//
	// Map<String,Object> map = new HashMap<String,Object>();
	// map.put("ccdata", ccdata);
	// map.put("trainId", trainId);
	// map.put("theNextStationId", theNextStationId);
	// map.put("carGoType", carGoType);
	// map.put("routeType", routeType);
	// map.put("setTemp", setTemp);
	//
	// maps.add(map);
	// }
	// System.out.println(maps);
	// return Msg.success().add("maps",maps);
	// }

}
