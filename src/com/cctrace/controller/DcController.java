package com.cctrace.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;










import com.cctrace.entity.User;
import com.cctrace.entity.Yard;
import com.cctrace.service.DaoService;
import com.cctrace.utils.ConstantCode;
import com.cctrace.utils.JsonResult;
import com.cctrace.utils.ValidateUtil;

@Controller
@RequestMapping("/pc")

//堆场操作
public class DcController {
	
	@Resource
	DaoService daoService;
	
	@RequestMapping("/getDcInfo")
	public String getDcInfo(HttpServletRequest request,Model map){
		
		try {
			User user = (User) request.getSession().getAttribute("user");	
			
			List<Yard> yards = daoService.getAllYarsByCompanyId(user.getCompanyId());
			map.addAttribute("yards", yards);
			return "dcManage.jsp";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("异常");
		}
		return null;
	}
	
	//添加堆场
	@ResponseBody
	@RequestMapping("/dcRegist")
	public  JsonResult<Yard> dcRegist(HttpServletRequest request,Yard yard){
		
		try {
			String mess = "";
			int flag = ConstantCode.ERROR;
			//1.非空
			if(!ValidateUtil.isValid(yard.getYardName())){
				mess="名称不能为空";
				return new JsonResult<Yard>(flag, mess,yard);
			}

			User user = (User) request.getSession().getAttribute("user");
			int companyId = user.getCompanyId();
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("companyId",companyId);
			map.put("yardName",yard.getYardName());
			
			Yard yard1 =daoService.getYardByYardNameAndCompanyId(map);
			
			//2.堆场是否重复
			if(yard1!=null){
				mess = "堆场已存在";
			}else{
				yard.setCompanyId(user.getCompanyId());
				daoService.registNewYard(yard);
				mess = "注册成功";
				flag = ConstantCode.SUCCESS;
			}
			return new JsonResult<Yard>(flag, mess,yard);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("添加堆场异常");
		}
		return null;
	}
	
	/**
	 * 更新冷场箱数据
	 */
	@ResponseBody
	@RequestMapping(value="/dcUpdate")
	public JsonResult<Yard> dcUpdate(HttpServletRequest request,String yardName,String yardId){
		
		try {
			String mess = "";
			int flag = ConstantCode.ERROR;
			Yard yard = null;
			//1.非空
			if(!ValidateUtil.isValid(yardName)){
				mess="名称不能为空";
				return new JsonResult<Yard>(flag, mess,yard);
			}

			User user = (User) request.getSession().getAttribute("user");
			int companyId = user.getCompanyId();
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("companyId", companyId);
			map.put("yardName",yardName);
			
			yard = daoService.getYardByYardNameAndCompanyId(map);
			//2.下货站是否重复
			if(yard!=null){
				mess = "堆场已存在";
				
			}else{
				Yard yard1 = new Yard();
				
				yard1.setCompanyId(user.getCompanyId());
				yard1.setId(Integer.parseInt(yardId));
				yard1.setYardName(yardName);
				daoService.modifyYardById(yard1);
			    mess = "修改成功";
				flag = ConstantCode.SUCCESS;
				return new JsonResult<Yard>(flag, mess,yard1);
			}
			return new JsonResult<Yard>(flag, mess,yard);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			System.out.println("更新冷藏箱数据异常");
		}
		return null;
	}
	
	
	
	/**
	 * 删除堆场
	 */
	@ResponseBody
	@RequestMapping(value="/dcDelete")
	public JsonResult<Yard> dcDelete(HttpServletRequest request,Yard yard){
		
		try {
			String mess = "删除成功";
			int flag = ConstantCode.SUCCESS;
			User user = (User) request.getSession().getAttribute("user");
			yard.setCompanyId(user.getCompanyId());
			//删除堆场
			daoService.removeYardByYardNameAndCompanyId(yard);

			return new JsonResult<Yard>(flag, mess,yard);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("删除堆场异常");
		}
		return null;
	}

	
}
