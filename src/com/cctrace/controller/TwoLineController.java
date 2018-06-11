package com.cctrace.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import com.cctrace.service.DaoService;


@Controller
@RequestMapping("twoLine")

//
public class TwoLineController {
	@Resource
	private DaoService daoService;
	
	@RequestMapping("getLonLat")
	public String getLonLat(ModelMap map, HttpServletRequest req){
		
		return "freshMap.jsp";
	}
	
	
}
