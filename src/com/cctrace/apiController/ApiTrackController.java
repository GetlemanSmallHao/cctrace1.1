package com.cctrace.apiController;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cctrace.entity.Ccdata;
import com.cctrace.entity.OurCcdata;
import com.cctrace.entity.QueryVo;
import com.cctrace.entity.User;
import com.cctrace.service.DaoService;
import com.cctrace.utils.DateUtil;
import com.github.pagehelper.util.StringUtil;


@Controller
@RequestMapping(value="/apiUser")
public class ApiTrackController {
	
	@Resource
	private DaoService daoService;
	public void setDaoService(DaoService daoService) {
		this.daoService = daoService;
	}
	
/*	*//**
	 * @param containerId
	 * @return
	 * @throws IOException
	 * 
	 * 显示位置
	 *//*
	@RequestMapping(value="/getLastLocationBycontainerId")
	@ResponseBody
	public Ccdata getLastLocationBycontainerId(HttpServletRequest request, @RequestParam String containerId) throws IOException{
//		String imei = request.getParameter("imei");
		List<Ccdata> ccdatas = daoService.selectCcdatasLonAndLat(containerId);
		Ccdata ccdata = new Ccdata();
		if(ccdatas.size() > 0){
			ccdata = daoService.getLastLocationBycontainerId(containerId);
		}
		
		return ccdata;
	}*/
	
	/**
	 * @param containerId
	 * @return
	 * @throws IOException
	 * 
	 * 显示位置
	 */
	@RequestMapping(value="/getLastLocationBycontainerId")
	@ResponseBody
	public OurCcdata getLastLocationBycontainerId(HttpServletRequest request, @RequestParam String containerId) throws IOException{
//		String imei = request.getParameter("imei");
		List<OurCcdata> ourccdatas = daoService.selectOurCcdatasLonAndLat(containerId);
		OurCcdata ourccdata = new OurCcdata();
		if(ourccdatas.size() > 0){
			ourccdata = daoService.getOurLastLocationBycontainerId(containerId);
		}
		
		return ourccdata;
	}
	
	/**
	 * 地图上显示所有设备的信息
	 * 
	 */
	@RequestMapping(value="/getAllLocationBycompanyId")
	@ResponseBody
	public List<OurCcdata> getAllLocationBycompanyId(HttpServletRequest request, @RequestParam String username) throws IOException{
//		String imei = request.getParameter("imei");
		User user = daoService.getUserByUsername(username);
		List<OurCcdata> ourccdatas = daoService.getOurAllLocationBycompanyId(user.getCompanyId());
		for(int i = 0; i<ourccdatas.size(); i++){
			if((Double)ourccdatas.get(i).getLat() == null){
				ourccdatas.get(i).setLat(34.741047);
			}
			if((Double)ourccdatas.get(i).getLon() == null){
				ourccdatas.get(i).setLon(113.790688);
			}
			if(StringUtil.isEmpty(ourccdatas.get(i).getNowTime())){
				ourccdatas.get(i).setNowTime("");
			}
		}
		return ourccdatas;
		
		
		
	}
	
	/**
	 * @param containerId
	 * @return
	 * @throws IOException
	 * 
	 * 显示设备的具体信息
	 */
	@RequestMapping(value="/getInforBycontainerId")
	@ResponseBody
	public QueryVo getInforBycontainerId(HttpServletRequest request, @RequestParam String containerId) throws IOException{
		
		QueryVo queryVo = null;
		if(daoService.getInforBycontainerId(containerId) != null){
			queryVo = daoService.getInforBycontainerId(containerId);
			if((Double)queryVo.getOurccdata().getLat() == null){
				queryVo.getOurccdata().setLat(34.741047);
			}
			if((Double)queryVo.getOurccdata().getLon() == null){
				queryVo.getOurccdata().setLon(113.790688);
			}
			if(StringUtil.isEmpty(queryVo.getBindTable().getCarGoType())){
				queryVo.getBindTable().setCarGoType("");
			}
			if(StringUtil.isEmpty(queryVo.getBindTable().getRouteType())){
				queryVo.getBindTable().setRouteType("");
			}
			if(StringUtil.isEmpty(queryVo.getBindTable().getTrainId())){
				queryVo.getBindTable().setTrainId("");
			}
			if((Integer)queryVo.getBindTable().getYardId()==null){
				queryVo.getBindTable().setYardId(0);
			}
			if((Integer)queryVo.getBindTable().getTheNextStationId()==null){
				queryVo.getBindTable().setTheNextStationId(0);;
			}
			if(StringUtil.isEmpty(queryVo.getOurccdata().getNowTime())){
				queryVo.getOurccdata().setNowTime("");
			}
		}
		
		return queryVo;
	}
	
		
	/**
	 * 自定义时间查询
	 * @param String containerId,String startTime,String endTime
	 * @return
	 */
	@RequestMapping("/findTrackTwoTime")
	@ResponseBody
	public List<OurCcdata> findTrackTwoTime(HttpServletRequest request, @RequestParam String containerId,
			@RequestParam String startTime, @RequestParam String endTime) {
		
		long startLongTime = DateUtil.getLongFromStr(startTime,"yyyy-MM-dd HH:mm:ss");
		long endLongTime = DateUtil.getLongFromStr(endTime,"yyyy-MM-dd HH:mm:ss");
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("containerId", containerId);
		map.put("start", startLongTime);
		map.put("end", endLongTime);
		List<OurCcdata> ourccdatas = null;
		if(daoService.getOurCcdatasByContainerIdBetweenTowTime(map) != null){
			ourccdatas = daoService.getOurCcdatasByContainerIdBetweenTowTime(map);
		}
		return ourccdatas;
		
	}
//	/**
//	 * 显示今天的轨迹信息
//	 * @throws ParseException 
//	 * 
//	 */
//	@RequestMapping("/findTrackToday")
//	@ResponseBody
//	public List<Ccdata> findTrackToday(HttpServletRequest request, @RequestParam String containerId) throws ParseException {
//			
//		String startTime = DateUtil.getDateStr(DateUtil.getNowDate(), "yyyy-MM-dd 00:00:00");
//		String endTime = DateUtil.getDateStr(DateUtil.getNowDate(), "yyyy-MM-dd HH:mm:ss");
//		
//		long startLongTime = DateUtil.getLongFromStr(startTime,"yyyy-MM-dd HH:mm:ss");
//		long endLongTime = DateUtil.getLongFromStr(endTime,"yyyy-MM-dd HH:mm:ss");
//		
//		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("containerId", containerId);
//		map.put("start", startLongTime);
//		map.put("end", endLongTime);
//		List<Ccdata> ccdatas = daoService.getCcdatasByContainerIdBetweenTowTime(map);
//		return ccdatas;
//	}
	
	
	
	/**
	 * 显示今天的轨迹信息
	 * @throws ParseException 
	 * 
	 */
	@RequestMapping("/findTrackToday")
	@ResponseBody
	public List<OurCcdata> findTrackToday(HttpServletRequest request, @RequestParam String containerId) throws ParseException {
			
		Date dateStr = DateUtil.getDateFromStr(DateUtil.getDateStr(DateUtil.getNowDate(), "yyyy-MM-dd 00:00:00"), "yyyy-MM-dd HH:mm:ss");
		
		Date dateEnd = DateUtil.getDateFromStr(DateUtil.getDateStr(DateUtil.getNowDate(), "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss");
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("startDate", dateStr );
		map.put("endDate", dateEnd);
		map.put("containerId", containerId);
		List<OurCcdata> ourccdatas = null;
		if(daoService.selectOurRecordsBetweenInTwoTimeBycontainerId(map) != null){
			ourccdatas = daoService.selectOurRecordsBetweenInTwoTimeBycontainerId(map);
		}
		return ourccdatas;
	}
	
	
	/**
	 * 显示one month的轨迹信息
	 * @throws ParseException 
	 * 
	 */
	@RequestMapping("/findTrackOneMonth")
	@ResponseBody
	public List<OurCcdata> findTrackOneMonth(HttpServletRequest request, @RequestParam String containerId) throws ParseException {
			
		Date dateStr = DateUtil.getDateFromStr(DateUtil.getDateStr(DateUtil.getNowDate(), "yyyy-MM-01 00:00:00"), "yyyy-MM-dd HH:mm:ss");
		
		Date dateEnd = DateUtil.getDateFromStr(DateUtil.getDateStr(DateUtil.getNowDate(), "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss");
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("startDate", dateStr );
		map.put("endDate", dateEnd);
		map.put("containerId", containerId);
		List<OurCcdata> ourccdatas = null;
		if(daoService.selectOurRecordsBetweenInTwoTimeBycontainerId(map) != null){
			ourccdatas = daoService.selectOurRecordsBetweenInTwoTimeBycontainerId(map);
		}
		return ourccdatas;
	}
	
	
	
	
	/**一周的轨迹查询
	 * 根据containerId,int days
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getRecentDaysLocationRecordsByContainerId")
	@ResponseBody
	public List<OurCcdata> getRecentDaysLocationRecordsByContainerId(HttpServletRequest request){
		String containerId = request.getParameter("containerId");
//		int serveral = Integer.parseInt(request.getParameter("days"));
		int serveral = 7;
		Map<String, Object> map = new HashMap<>();
		Date now = DateUtil.getNowDate();
		map.put("startDate", DateUtil.getServeralDaysBeforeNowTime(now, serveral));
		map.put("endDate", now);
		map.put("containerId", containerId);
		List<OurCcdata> records = null;
		if(daoService.selectOurRecordsBetweenInTwoTimeBycontainerId(map) != null){
			records = daoService.selectOurRecordsBetweenInTwoTimeBycontainerId(map);
		}
		return records;
	}
	
	
}
