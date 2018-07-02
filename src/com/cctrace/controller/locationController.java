package com.cctrace.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cctrace.entity.Msg;
import com.cctrace.entity.OurCcdata1;
import com.cctrace.service.DaoService;
import com.cctrace.utils.ChangeRealLocation;

@Controller
@RequestMapping(value="/pc")

//当前定位 冷藏箱的位置信息定位
public class locationController {
	
	@Resource
	DaoService daoService;
	
	
/*	@ResponseBody
	@RequestMapping(value="/getLocation")
	public Msg getLocationByContaionId(HttpServletRequest request,String containerId,Model map){
		
		
		Ccdata lastCcdata= daoService.getLastCcdataByContainerId(containerId);
		
		Double lat = lastCcdata.getLat();
		Double lon = lastCcdata.getLon();
		String deviceId = lastCcdata.getDeviceId();
		
		System.out.println(lat+"---"+lon);
	
		map.addAttribute("lat", lat);
		map.addAttribute("lon", lon);
		map.addAttribute("deviceId", deviceId);
		map.addAttribute("containerId", containerId);
		
		
		return Msg.success().add("map", map);
	}*/
	
	@ResponseBody
	@RequestMapping(value="/getLocation")
	public Msg getLocationByContaionId(HttpServletRequest request,String containerId,Model map){
		
		
		try {
			OurCcdata1 lastourCcdata= daoService.selectOurCcdataBycontainerId1(containerId);
//										getLastCcdataByContainerId(containerId);
//		OurCcdata ourCcdata = daoService.se
			Double lat = lastourCcdata.getLat();
			Double lon = lastourCcdata.getLon();
			String deviceId = lastourCcdata.getDeviceId();
			String nowTime = lastourCcdata.getNowTime();
			
			System.out.println(lat+"---"+lon);
			map.addAttribute("lat", lat);
			map.addAttribute("lon", lon);
			try {
				List<Double> sendGET = ChangeRealLocation.SendGET(lat, lon);
				map.addAttribute("lat", sendGET.get(0));
				map.addAttribute("lon", sendGET.get(1));
			} catch (Exception e) {
				// TODO: handle exception
			}
			map.addAttribute("deviceId", deviceId);
			map.addAttribute("containerId", containerId);
			map.addAttribute("nowTime", nowTime);
			
			
			return Msg.success().add("map", map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("定位异常");
		}
		return null;
	}
	

}
