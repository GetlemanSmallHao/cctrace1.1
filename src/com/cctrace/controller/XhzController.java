package com.cctrace.controller;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cctrace.entity.Container;
import com.cctrace.entity.TheNextStation;
import com.cctrace.entity.User;
import com.cctrace.service.DaoService;
import com.cctrace.utils.ConstantCode;
import com.cctrace.utils.JsonResult;
import com.cctrace.utils.ValidateUtil;


@Controller
@RequestMapping("/pc")

//下货站管理
public class XhzController {
	
	@Resource
	DaoService daoService;
	
	//查询下货站
	@RequestMapping("/getXhzInfo")
	public String getXhzInfo(HttpServletRequest request,Model map){
		
		try {
			User user = (User) request.getSession().getAttribute("user");
			List<TheNextStation> allTheNextStations = daoService.getAllTheNextStationsByCompanyId(user.getCompanyId());
			map.addAttribute("allTheNextStations", allTheNextStations);
			
			return "xhzManage.jsp";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("下货站管理异常");
		}
		return null;
		
	}
	//添加下货站
	@ResponseBody
	@RequestMapping("/xhzRegist")
	public  JsonResult<TheNextStation> xhzRegist(HttpServletRequest request,TheNextStation theNextStation){
		
		try {
			String mess = "";
			int flag = ConstantCode.ERROR;
			//1.非空
			if(!ValidateUtil.isValid(theNextStation.getStationName())){
				mess="名称不能为空";
				return new JsonResult<TheNextStation>(flag, mess,theNextStation);
			}

			User user = (User) request.getSession().getAttribute("user");
			int companyId = user.getCompanyId();
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("companyId", companyId);
			map.put("theNextStationName",theNextStation.getStationName());
			
			TheNextStation theNextStation1 = daoService.getTheNextStationBycompanyIdAndStationName(map);
			//2.下货站是否重复
			if(theNextStation1!=null){
				mess = "下货站已存在";
			}else{
				theNextStation.setCompanyId(user.getCompanyId());
				daoService.registNewTheNextStation(theNextStation);
				mess = "注册成功";
				flag = ConstantCode.SUCCESS;
			}
			return new JsonResult<TheNextStation>(flag, mess,theNextStation);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("添加下货站异常");
		}
		return null;
	}
	
	/**
	 * 更新冷场箱数据
	 */
	@ResponseBody
	@RequestMapping(value="/xhzUpdate")
	public JsonResult<TheNextStation> xhzUpdate(HttpServletRequest request,String stationName, String xhzId){
		
		try {
			String mess = "";
			int flag = ConstantCode.ERROR;
			TheNextStation theNextStation = null;
			//1.非空
			if(!ValidateUtil.isValid(stationName)){
				mess="名称不能为空";
				return new JsonResult<TheNextStation>(flag, mess,theNextStation);
			}

			User user = (User) request.getSession().getAttribute("user");
			int companyId = user.getCompanyId();
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("companyId", companyId);
			map.put("stationName",stationName);
			
			theNextStation = daoService.getTheNextStationBycompanyIdAndStationName(map);
			//2.下货站是否重复
			if(theNextStation!=null){
				mess = "下货站已存在";
				
			}else{
				TheNextStation theNextStation1 = new TheNextStation();
				theNextStation1.setCompanyId(user.getCompanyId());
				theNextStation1.setId(Integer.parseInt(xhzId));
				theNextStation1.setStationName(stationName);
				daoService.modifyTheNextStationById(theNextStation1);
			    mess = "修改成功";
				flag = ConstantCode.SUCCESS;
				return new JsonResult<TheNextStation>(flag, mess,theNextStation1);
			}
			return new JsonResult<TheNextStation>(flag, mess,theNextStation);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			System.out.println("更新冷藏箱数据异常");
		}
		return null;
	}

	/**
	 * 删除下货站
	 */
	@ResponseBody
	@RequestMapping(value="/xhzDelete")
	public JsonResult<TheNextStation> xhzDelete(HttpServletRequest request,TheNextStation theNextStation){
		
		try {
			String mess = "删除成功";
			int flag = ConstantCode.SUCCESS;
			User user = (User) request.getSession().getAttribute("user");
			theNextStation.setCompanyId(user.getCompanyId());
			//删除container
			daoService.removeTheNextStationByCompanyIdAndStationName(theNextStation);
			return new JsonResult<TheNextStation>(flag, mess,theNextStation);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("删除下货站异常");
		}
		return null;
	}
}
