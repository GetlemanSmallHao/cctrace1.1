package com.cctrace.controller.test;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cctrace.service.DaoService;

@Controller
@RequestMapping(value="/lunxun")
public class LunXunTestController {

	@Resource
	private DaoService daoService;
	public void setDaoService(DaoService daoService) {
		this.daoService = daoService;
	}
	
	@RequestMapping(value="/dingShiQiTest")
	@ResponseBody
	public String dingShiQiTest(){
		Integer maxAlertId = daoService.getMaxAlertId();
		System.out.println("maxAlertId : "+maxAlertId);
		return "{\"message\":\"ok\"}";
	}
	
	
	
}
