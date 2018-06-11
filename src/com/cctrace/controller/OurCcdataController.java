package com.cctrace.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cctrace.entity.Ccdata;
import com.cctrace.entity.Msg;
import com.cctrace.entity.OurCcdata;
import com.cctrace.service.DaoService;
import com.cctrace.utils.DateUtil;

@Controller
@RequestMapping(value="/pc/OurCcdata")

//冷藏箱返回数据表
public class OurCcdataController {
	@Autowired
	DaoService daoService;
	
	
	/**
	 * 新增冷藏箱数据
	 */
	   
	@RequestMapping(value="/addOurCcdata")
	public  String  addOurCcdata(HttpServletRequest request,HttpServletResponse response,
			OurCcdata ourccdata){
		//User user=(User)request.getSession().getAttribute("user");
		//ccdata.setCompanyId(user.getCompanyId());
//		long gpsLongTime = DateUtil.getLongFromStr(ourccdata.getGpsTime(), "yyyy-MM-dd HH:mm:ss");
		long receiveLongTime = DateUtil.getLongFromStr(ourccdata.getReceiveTime(), "yyyy-MM-dd HH:mm:ss");
//		ourccdata.setGpsLongTime(gpsLongTime);
		ourccdata.setReceiveLongTime(receiveLongTime);
		daoService.addOurOneNewCcdata(ourccdata);
		
		return  "addOurCcdata.jsp";
	}
	/**
	 * 测试地图解析的数据是否能传入
	 * 
	 */
	@RequestMapping("/getLocation")
	@ResponseBody
	public Msg getLocation(HttpServletRequest request,HttpServletResponse response,@RequestParam String containerId,@RequestParam String address){
		System.out.println(containerId);
		System.out.println(address);
		return Msg.success();
	}
	
	
	
}
