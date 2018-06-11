package com.cctrace.apiController;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.deser.ValueInstantiators.Base;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.cctrace.entity.Alert;
import com.cctrace.entity.BindTable;
import com.cctrace.entity.Ccdata;
import com.cctrace.entity.Container;
import com.cctrace.entity.QueryVo;
import com.cctrace.entity.Rail;
import com.cctrace.entity.User;
import com.cctrace.service.DaoService;
import com.cctrace.utils.BaseResult;
import com.cctrace.utils.ConstantCode;
import com.cctrace.utils.DataUtil;
import com.cctrace.utils.DateUtil;
import com.cctrace.utils.JsonResult;
import com.cctrace.utils.JsonUtils;
import com.cctrace.utils.ValidateUtil;
import com.github.pagehelper.util.StringUtil;

@Controller
@RequestMapping("/apiUser")
public class ApiBanLieController {
	@Resource(name="daoService") 
	private DaoService daoService;
	public void setDaoService(DaoService daoService) {
		this.daoService = daoService;
	}
	
	/**
	 * 显示班列信息
	 * @param request
	 * @param username
	 * @return
	 * @throws IOException
	 */
	
	@RequestMapping("/showAllBanLieByCompanyId")
	@ResponseBody
	public List<BindTable> showAllBanLieByCompanyId (HttpServletRequest req,HttpServletResponse res,@RequestParam String username)
				throws IOException {
		User user = daoService.getUserByUsername(username);
		List<BindTable> bindTables = daoService.getBindTablesByCompanyId(user.getCompanyId());
		for(int i = 0; i<bindTables.size(); i++){
			if(StringUtil.isEmpty(bindTables.get(i).getTrainId())){
				bindTables.get(i).setTrainId("");
			}
			if((Integer)bindTables.get(i).getYardId() == null){
				bindTables.get(i).setYardId(0);
			}
			if((Integer)bindTables.get(i).getTheNextStationId() == null){
				bindTables.get(i).setTheNextStationId(0);
			}
			if(StringUtil.isEmpty(bindTables.get(i).getCarGoType())){
				bindTables.get(i).setCarGoType("");
			}
		}
		return bindTables;
				
	}
		
	/**
	 * 详细班列信息
	 * 
	 */
	@RequestMapping("/showBanLieInfo")
	@ResponseBody
	public QueryVo showBanLieInfo (HttpServletRequest request, @RequestParam String containerId)
				throws IOException {
		List<Alert> alerts = daoService.selectIfAlertBytrainId(containerId);
		if(alerts.size() == 0){
			System.out.println("没有预警信息");
//			b.containerId ,b.deviceId ,b.carGoType,
//			b.routeType,
//			b.trainId, b.yardId ,b.theNextStationId ,
//			c.oilLevel,c.backWindTemp,
//			c.lat , c.lon ,MAX(c.receiveTime)receiveTime,
//			con.setTemp
			QueryVo queryVo1 = daoService.showBanLieInfoNoAlert(containerId);
			if(StringUtil.isEmpty(queryVo1.getBindTable().getCarGoType())){
				queryVo1.getBindTable().setCarGoType("");
			}
			if(StringUtil.isEmpty(queryVo1.getBindTable().getRouteType())){
				queryVo1.getBindTable().setRouteType("");
			}
			if(StringUtil.isEmpty(queryVo1.getBindTable().getTrainId())){
				queryVo1.getBindTable().setTrainId("");
			}
			if((Integer)queryVo1.getBindTable().getYardId()==null){
				queryVo1.getBindTable().setYardId(0);
			}
			if((Integer)queryVo1.getBindTable().getTheNextStationId()==null){
				queryVo1.getBindTable().setTheNextStationId(0);;
			}
			if((Double)queryVo1.getOurccdata().getOilLevel()==null){
				queryVo1.getOurccdata().setOilLevel(0.0);
			}
			if((Double)queryVo1.getCcdata().getBackWindTemp()==null){
				queryVo1.getCcdata().setBackWindTemp(0.0);
			}
			
			if((Double)queryVo1.getOurccdata().getLat()==null){
				queryVo1.getOurccdata().setLat(34.741047);
			}
			if((Double)queryVo1.getOurccdata().getLon()==null){
				queryVo1.getOurccdata().setLon(113.790688);
			}
			if(StringUtil.isEmpty(queryVo1.getCcdata().getReceiveTime())){
				queryVo1.getCcdata().setReceiveTime("");
			}
			if((Double)queryVo1.getContainer().getSetTemp()==null){
				queryVo1.getContainer().setSetTemp(0.0);
			}
			if((Double)queryVo1.getCcdata().getEngRunTime() == null){
				queryVo1.getCcdata().setEngRunTime(0.0);
			}
			if((Double)queryVo1.getCcdata().getVecRunTime() == null){
				queryVo1.getCcdata().setVecRunTime(0.0);
			}
			queryVo1.getCcdata().setOilLevel(0.0);
			return queryVo1;
		}else{
			QueryVo queryVo = daoService.showBanLieInfo(containerId);
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
			if((Double)queryVo.getOurccdata().getOilLevel()==null){
				queryVo.getOurccdata().setOilLevel(0.0);
			}
			if((Double)queryVo.getCcdata().getBackWindTemp()==null){
				queryVo.getCcdata().setBackWindTemp(0.0);
			}
			
			if((Double)queryVo.getOurccdata().getLat()==null){
				queryVo.getOurccdata().setLat(34.741047);
			}
			if((Double)queryVo.getOurccdata().getLon()==null){
				queryVo.getOurccdata().setLon(113.790688);
			}
			if(StringUtil.isEmpty(queryVo.getCcdata().getReceiveTime())){
				queryVo.getCcdata().setReceiveTime("");
			}
			if((Double)queryVo.getContainer().getSetTemp()==null){
				queryVo.getContainer().setSetTemp(0.0);
			}
			if(StringUtil.isEmpty(queryVo.getAlert().getAlertType())){
				queryVo.getAlert().setAlertType("");
			}
			if(StringUtil.isEmpty(queryVo.getAlert().getAlertTime())){
				queryVo.getAlert().setAlertTime("");
			}
			if((Double)queryVo.getCcdata().getEngRunTime() == null){
				queryVo.getCcdata().setEngRunTime(0.0);
			}
			if((Double)queryVo.getCcdata().getVecRunTime() == null){
				queryVo.getCcdata().setVecRunTime(0.0);
			}
			return queryVo;
		}		

	}
	
	
	/**
	 * 
	 * 修改班列信息
	 */
	@RequestMapping("/updateBanLie")
	@ResponseBody
	public Object updateBanLie(HttpServletRequest request,@RequestParam String username,
			@RequestParam String containerId,@RequestParam String trainId,
			@RequestParam String carGoType, @RequestParam String yardName,
			@RequestParam String theNextStationName)
				throws IOException {
		User user = daoService.getUserByUsername(username);
		if(user.getRole().equals("common")){
			return BaseResult.error();
		}else{
			BindTable bindTable = new BindTable();
			bindTable.setCarGoType(carGoType);
			Map<String,Object> map = new HashMap<>();
			map.put("companyId", user.getCompanyId());
			map.put("stationName", theNextStationName);
			bindTable.setTheNextStationId(daoService.getTheNextStationBycompanyIdAndStationName(map).getId());
			bindTable.setTrainId(trainId);
			bindTable.setYardId(daoService.getYardByYardName(yardName).getId());
			bindTable.setContainerId(containerId);
			int n = daoService.modifyBindTableById(bindTable);
			if(n > 0){
				return BaseResult.success();
			}else{
				return BaseResult.error();
			}
		}
		
	}
	
	
	/**
	 * 删除冷藏箱
	 */
	@ResponseBody
	@RequestMapping(value="/banlieDelete")
	public Object banlieDelete(HttpServletRequest request,@RequestParam String username,
			@RequestParam String containerId) throws IOException{
		
		User user = daoService.getUserByUsername(username);
		if (user.getRole().equals("common")){
			return BaseResult.error();
		}
		else{
			//删除container
			int n = daoService.removeContainerByContainerId(containerId);
			//删除ccdata
			int n1 = daoService.removeCcdatasByContainerId(containerId);
			//删除bindtable
			int n2 = daoService.removeBindTableByContainerId(containerId);
			if (n > 0 && n1 > 0 && n2 > 0){
				return BaseResult.success();
			}
			else{
				return BaseResult.error();
			}
		}
		
	}
	
	/**
	 * 模糊查询冷藏箱号
	 * 返回冷藏箱号，设备号，班列Id
	 */
	@RequestMapping("/selectContainerLikey")
	@ResponseBody
	public List<BindTable> selectContainerLikey(HttpServletRequest request, @RequestParam String containerId){
		List<BindTable> bindTables = null;
		if(daoService.selectContainersLikey(containerId) != null){
			bindTables = daoService.selectContainersLikey(containerId);
		}
		return bindTables;
	}


}
