package com.cctrace.apiController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.codehaus.jackson.map.deser.ValueInstantiators.Base;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cctrace.entity.BindTable;
import com.cctrace.entity.QueryVo;
import com.cctrace.entity.User;
import com.cctrace.entity.Yard;
import com.cctrace.service.DaoService;
import com.cctrace.utils.BaseResult;
import com.cctrace.utils.ConstantCode;
import com.cctrace.utils.JsonUtils;

@Controller
@RequestMapping(value="/apiUser")
public class ApiYardController {

	@Autowired
	private DaoService daoService;

	public void setDaoService(DaoService daoService) {
		this.daoService = daoService;
	}
	
	
	/**
	 * 
	 * @param username,yardName
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/addYard")
	@ResponseBody
	public void addYard(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String yardName = request.getParameter("yardName");
		String username = request.getParameter("username");
		User user = daoService.getUserByUsername(username);
		
		String mess = "";
		int flag = ConstantCode.ERROR;
		JSONObject jo=new JSONObject();
		
		
		if (user.getRole().equals("common")){
			mess = "您的权限不够";
		}else{
			if(daoService.getYardByYardName(yardName) == null){
				Yard yard = new Yard();
				yard.setCompanyId(user.getCompanyId());
				yard.setYardName(yardName);
				int n = daoService.registNewYard(yard);
				if(n > 0){
					mess = "增加成功";
					flag = ConstantCode.SUCCESS;
					}
				else{
					mess = "增加失败";
				}
			}else{
				mess = "输入的堆场名已存在";
			}
			
			jo.put("flag", flag);
			jo.put("message", mess);
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(jo.toString());
			response.getWriter().flush();
			response.getWriter().close();
		
		}
	}
	
	
	
//	/**
//	 * 
//	 * @param username,yardName
//	 * @return
//	 * @throws IOException
//	 */
//	@RequestMapping(value="/addYard")
//	@ResponseBody
//	public Object addYard(HttpServletRequest request) throws IOException{
//		String yardName = request.getParameter("yardName");
//		String username = request.getParameter("username");
//		User user = daoService.getUserByUsername(username);
//		if (user.getRole().equals("common")){
//			return BaseResult.error();
//		}else{
//			if(daoService.getYardByYardName(yardName) == null){
//				Yard yard = new Yard();
//				yard.setCompanyId(user.getCompanyId());
//				yard.setYardName(yardName);
//				int n = daoService.registNewYard(yard);
//				if(n > 0){
//					return BaseResult.success();}
//				else{
//					return BaseResult.error();
//				}
//			}else{
//				return BaseResult.error();
//			}
//		
//		}
//	}
	
	
	/**
	 * 展示下货站信息
	 * 
	 */
	@RequestMapping(value="/showYard")
	@ResponseBody
	public List<QueryVo> showYard(HttpServletRequest request) throws IOException{
		String username = request.getParameter("username");
		User user = daoService.getUserByUsername(username);
		
		List<QueryVo> queryVos = null;
		if(daoService.showYardInformation(user.getCompanyId()) != null){
			queryVos = daoService.showYardInformation(user.getCompanyId());
		}
		return queryVos;
	}
	
	
	
	
	/**
	 * 
	 * @param username,yardName
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/deleteYard")
	@ResponseBody
	public BaseResult deleteYard(HttpServletRequest req) throws IOException{
		String yardName = req.getParameter("yardName");
		String username = req.getParameter("username");
		BaseResult result = new BaseResult();
		String mess = "";
		int flag = ConstantCode.ERROR;
//		JSONObject jo=new JSONObject();
		Yard yard1 = daoService.getYardByYardName(yardName);
		if(yard1 != null){
			int yardId = yard1.getId();
			User user = daoService.getUserByUsername(username);
			if (!user.getRole().equals("root")){
				mess = "您的权限不够，不能删除";
				result.setFlag(flag);
				result.setMessage(mess);
				return result;
			}else{
//				if(daoService.getYardByYardName(yardName) != null){
					Yard yard = new Yard();
					yard.setCompanyId(user.getCompanyId());
					yard.setYardName(yardName);
					List<BindTable> bindTables = daoService.selectBindTableByYardId(yardId);
					if (bindTables.size() == 0){
					int n = daoService.removeYardByYardNameAndCompanyId(yard);
					if(n > 0){
						flag = ConstantCode.SUCCESS;
						result.setFlag(flag);
						result.setMessage(mess);						
						return result;
						}
						else{
						  mess = "删除失败";
						  result.setFlag(flag);
						  result.setMessage(mess);
						  return result;
						}
					}else{
						int n = daoService.removeYardByYardNameAndCompanyId(yard);
						int n1 = daoService.updateBindTableByYardId(yardId);
						if(n > 0 && n1 > 0){
							mess = "删除成功";
							flag = ConstantCode.SUCCESS;
							result.setFlag(flag);
							result.setMessage(mess);
							return result;
						}
						else{
							mess = "删除失败";
							result.setFlag(flag);
							result.setMessage(mess);
							return result;
						}
					}
			}
		}else{
			mess = "您输入的堆场名不存在";
			result.setFlag(flag);
			result.setMessage(mess);
			return result;
		}
		

	}

	
	
}
