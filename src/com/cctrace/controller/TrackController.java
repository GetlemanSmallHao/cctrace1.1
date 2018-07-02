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
import org.springframework.web.bind.annotation.ResponseBody;

import com.cctrace.entity.BindTable;
import com.cctrace.entity.Ccdata;
import com.cctrace.entity.OurCcdata;
import com.cctrace.entity.TrackData;
import com.cctrace.service.DaoService;
import com.cctrace.utils.DateUtil;

@Controller
@RequestMapping(value="/pc/track")

//首页运行轨迹
public class TrackController {
	
	@Resource
	private DaoService daoService;
	public void setDaoService(DaoService daoService) {
		this.daoService = daoService;
	}
	
	
	/**
	 * 通过冷藏箱id 开始时间 结束时间 查询冷箱子自研数据列表Json
	 * @param request
	 * @return
	 */
	
	@RequestMapping(value="/getTrackDatasByContainerIdJson")
	
	@ResponseBody
	public List<OurCcdata> getTrackDatasByContainerIdJson(HttpServletRequest request){
		try {
			String containerId = request.getParameter("containerId");
			String startTime = request.getParameter("startTime");
			String endTime = request.getParameter("endTime");
			long start = DateUtil.getLongFromStr(startTime,"yyyy-MM-dd HH:mm:ss");
			System.out.println("longStart----------------"+start);
			long end = DateUtil.getLongFromStr(endTime,"yyyy-MM-dd HH:mm:ss");
			System.out.println("longEnd------------------"+end);
			Map<String,Object>map = new HashMap<String,Object>();
			map.put("containerId",containerId);
			map.put("start",start);
			map.put("end",end);
			List<OurCcdata> ourCcdatas = daoService.selectOurCcdatasByContainerIdBetweenTowTimeASC(map);
			
			if(ourCcdatas==null||ourCcdatas.size()==0){
				return null;
			}
			return ourCcdatas;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("异常");
		}
		return null;
	}
	
	
	/**
	 * 通过冷藏箱id 开始时间 结束时间 查询冷箱子自研数据列表页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getTrackDatasByContainerIdPage")
	public String getTrackDatasByContainerIdPage(HttpServletRequest request,ModelMap mmp){
		try {
			String containerId = request.getParameter("containerId");
			String startTime = request.getParameter("startTime");
			String endTime = request.getParameter("endTime");
			long start = DateUtil.getLongFromStr(startTime,"yyyy-MM-dd HH:mm:ss");
			System.out.println("longStart----------------"+start);
			long end = DateUtil.getLongFromStr(endTime,"yyyy-MM-dd HH:mm:ss");
			System.out.println("longEnd------------------"+end);
			Map<String,Object>map = new HashMap<String,Object>();
			map.put("containerId",containerId);
			map.put("start",start);
			map.put("end",end);
			List<OurCcdata> ourCcdatas = daoService.selectOurCcdatasByContainerIdBetweenTowTimeASC(map);
			mmp.put("ourCcdatas", ourCcdatas);
			return "showContainsLocationByIdAndPartTime.jsp";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("异常");
		}
		return null;
	}
	
	
	@RequestMapping(value="/getTrackDatasByContainerId")
	@ResponseBody
	public List<TrackData> getTrackDatasByContainerId(HttpServletRequest request){
		try {
			String containerId = request.getParameter("containerId");
			String startTime = request.getParameter("startTime");
			String endTime = request.getParameter("endTime");
			long start = DateUtil.getLongFromStr(startTime,"yyyy-MM-dd HH:mm:ss");
			System.out.println("longStart----------------"+start);
			long end = DateUtil.getLongFromStr(endTime,"yyyy-MM-dd HH:mm:ss");
			System.out.println("longEnd------------------"+end);
			Map<String,Object>map = new HashMap<String,Object>();
			map.put("containerId",containerId);
			map.put("start",start);
			map.put("end",end);
			List<OurCcdata> ourCcdatas = daoService.selectOurCcdatasByContainerIdBetweenTowTimeASC(map);
			if(ourCcdatas==null||ourCcdatas.size()==0){
				return new ArrayList<TrackData>();
			}
			System.out.println(ourCcdatas);
			List<TrackData> trackDatas = new ArrayList<TrackData>();
			OurCcdata temp = ourCcdatas.get(0);
//		Ccdata temp = ccdatas.get(0);
			BindTable bindTable = daoService.getBindTableByContainerId(temp.getContainerId());
			String trainId = bindTable.getTrainId();
			for (int i = 0; i < ourCcdatas.size(); i++) {
				if(ourCcdatas.get(i).getLat() != null && ourCcdatas.get(i).getLon() != null){
					TrackData trackData = new TrackData();
					trackData.setLat(ourCcdatas.get(i).getLat());
					trackData.setLon(ourCcdatas.get(i).getLon());
					trackData.setGpsTime(ourCcdatas.get(i).getNowTime());
					trackData.setContainerId(ourCcdatas.get(i).getContainerId());
					trackData.setTrainId(trainId);
					trackDatas.add(trackData);
				}else{
					i++;
				}
			}
			return trackDatas;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("异常");
		}
		return null;
	}
	
	
	
	
	
	
	
}
