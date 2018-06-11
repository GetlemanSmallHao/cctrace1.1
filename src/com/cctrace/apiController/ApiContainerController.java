package com.cctrace.apiController;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.cctrace.entity.BindTable;
import com.cctrace.entity.Ccdata;
import com.cctrace.entity.Container;
import com.cctrace.entity.OurCcdata;
import com.cctrace.entity.QueryVo;
import com.cctrace.entity.User;
import com.cctrace.service.DaoService;
import com.cctrace.utils.ConstantCode;
import com.cctrace.utils.DateUtil;
import com.cctrace.utils.ValidateUtil;
import com.github.pagehelper.util.StringUtil;

@Controller
@RequestMapping("/apiUser")
public class ApiContainerController {
	
	@Resource(name="daoService") 
	private DaoService daoService;
	public void setDaoService(DaoService daoService) {
		this.daoService = daoService;
	}
	
	/**
	 * 显示冷藏箱信息
	 * @param request
	 * @param username
	 * @return
	 * @throws IOException
	 */
	
	
	
	
	@RequestMapping("/showAllContainerByUsername")
	@ResponseBody
	public List <QueryVo> showAllContainerByUsername (HttpServletRequest req,HttpServletResponse res,@RequestParam String username)
				throws IOException {
		User user = daoService.getUserByUsername(username);
		List<QueryVo> queryVos = null;
		if(user != null){
			if (daoService.showAllContainerByUsername(user.getCompanyId()) != null){
				queryVos = daoService.showAllContainerByUsername(user.getCompanyId());
			}
		}
		
		return queryVos;

	}
	
	/**
	 * 显示一个的冷藏箱信息
	 * 
	 */
	@RequestMapping("/showContainerinforBycontainerId")
	@ResponseBody
	public QueryVo showContainerinforBycontainerId (HttpServletRequest request,@RequestParam String containerId)
				throws IOException {
		QueryVo queryVo = daoService.showContainerinforBycontainerId(containerId);
		
		
//		SELECT c.containerId ,c.registTime,c.deviceId,c.modifyDeviceTime,
//		c.minRefBatVol,c.maxRefBatVol,c.minBackWindTemp,c.maxBackWindTemp,
//		c.minTankHum,c.maxTankHum,c.minOilLevel,c.maxOilLevel,c.minGpsBatVol,
//		c.maxGpsBatVol,c.minEnviTemp,c.maxEnviTemp,c.minChuWindTemp,c.maxChuWindTemp ,
//		c.minCenBoxTemp,c.maxCenBoxTemp ,c.minTailBoxTemp ,c.maxTailBoxTemp ,c.remoteSwiMac ,c.setTemp ,
//		c.clearAlert,c. selfCheck ,c.refRunMode ,c.newWindDoorMode ,c.companyId,
//		comp.companyName FROM container c 
//		JOIN company comp ON comp.id = c.companyId
//		WHERE c.containerId = #{container.containerId}
		
		if(StringUtil.isEmpty(queryVo.getCompany().getCompanyName())){
			queryVo.getCompany().setCompanyName("");
		}
		if(StringUtil.isEmpty(queryVo.getContainer().getRegistTime())){
			queryVo.getContainer().setRegistTime("");
		}
		if(StringUtil.isEmpty(queryVo.getContainer().getModifyDeviceTime())){
			queryVo.getContainer().setModifyDeviceTime("");
		}
		////////////////////////////////////
		if((Double)queryVo.getContainer().getMinRefBatVol() == null){
			queryVo.getContainer().setMinRefBatVol(0.0);
		}
		if((Double)queryVo.getContainer().getMaxRefBatVol() == null){
			queryVo.getContainer().setMaxRefBatVol(0.0);
		}
		///////////////////////////////////
		if((Double)queryVo.getContainer().getMinBackWindTemp() == null){
			queryVo.getContainer().setMinBackWindTemp(0.0);
		}
		if((Double)queryVo.getContainer().getMaxBackWindTemp() == null){
			queryVo.getContainer().setMaxBackWindTemp(0.0);
		}
		//////////////////////////////////
		if((Double)queryVo.getContainer().getMinTankHum() == null){
			queryVo.getContainer().setMinTankHum(0.0);
		}
		if((Double)queryVo.getContainer().getMaxTankHum() == null){
			queryVo.getContainer().setMaxTankHum(0.0);
		}
		//////////////////////////////////
		if((Double)queryVo.getContainer().getMinOilLevel() == null){
			queryVo.getContainer().setMinOilLevel(0.0);
		}
		if((Double)queryVo.getContainer().getMaxOilLevel() == null){
			queryVo.getContainer().setMaxOilLevel(0.0);
		}
		//////////////////////////////////
		if((Double)queryVo.getContainer().getMinGpsBatVol() == null){
			queryVo.getContainer().setMinGpsBatVol(0.0);
		}
		if((Double)queryVo.getContainer().getMaxGpsBatVol() == null){
			queryVo.getContainer().setMaxGpsBatVol(0.0);
		}
		//////////////////////////////////
		if((Double)queryVo.getContainer().getMinEnviTemp() == null){
			queryVo.getContainer().setMinEnviTemp(0.0);
		}
		if((Double)queryVo.getContainer().getMaxEnviTemp() == null){
			queryVo.getContainer().setMaxEnviTemp(0.0);
		}
		//////////////////////////////////
		if((Double)queryVo.getContainer().getMinChuWindTemp() == null){
			queryVo.getContainer().setMinChuWindTemp(0.0);
		}
		if((Double)queryVo.getContainer().getMaxChuWindTemp() == null){
			queryVo.getContainer().setMaxChuWindTemp(0.0);
		}
		//////////////////////////////////
		if((Double)queryVo.getContainer().getMinCenBoxTemp() == null){
			queryVo.getContainer().setMinCenBoxTemp(0.0);
		}
		if((Double)queryVo.getContainer().getMaxCenBoxTemp() == null){
			queryVo.getContainer().setMaxCenBoxTemp(0.0);
		}
		//////////////////////////////////
		if((Double)queryVo.getContainer().getMinTailBoxTemp() == null){
			queryVo.getContainer().setMinTailBoxTemp(0.0);
		}
		if((Double)queryVo.getContainer().getMaxTailBoxTemp() == null){
			queryVo.getContainer().setMaxTailBoxTemp(0.0);
		}
		//*******************************
		if(StringUtil.isEmpty(queryVo.getContainer().getRemoteSwiMac())){
			queryVo.getContainer().setRemoteSwiMac("");
		}
		if(StringUtil.isEmpty(queryVo.getContainer().getBootDef())){
			queryVo.getContainer().setBootDef("");
		}
		if((Double)queryVo.getContainer().getSetTemp() == null){
			queryVo.getContainer().setSetTemp(0.0);
		}
		if(StringUtil.isEmpty(queryVo.getContainer().getClearAlert())){
			queryVo.getContainer().setClearAlert("");
		}
		////////////////////////////////////////////
		if(StringUtil.isEmpty(queryVo.getContainer().getSelfCheck())){
			queryVo.getContainer().setSelfCheck("");
		}
		if(StringUtil.isEmpty(queryVo.getContainer().getRefRunMode())){
			queryVo.getContainer().setRefRunMode("");
		}
		if(StringUtil.isEmpty(queryVo.getContainer().getNewWindDoorMode())){
			queryVo.getContainer().setNewWindDoorMode("");
		}
		return queryVo;
	}	
	
	
	/**
	 * 添加冷藏箱
	 * @param username , containerId,deviceId
	 * @throws IOException 
	 */
	@ResponseBody
	@RequestMapping(value="/containerRegist")
	public  void containerRegist(HttpServletRequest request,HttpServletResponse response,@RequestParam String username,
			@RequestParam String containerId,@RequestParam String deviceId,@RequestParam  String type) throws IOException{
		User user = daoService.getUserByUsername(username);
		String mess = "";
		int flag = ConstantCode.ERROR;
		JSONObject jo=new JSONObject();
		
		if (user.getRole().equals("common")){
			mess = "您的权限不够";
		}else{
			
			String registTime = DateUtil.getDateStr(new Date(), "yyyy-MM-dd HH:mm:ss");
			long registLongTime =DateUtil.getLongFromDate(new Date());
			System.out.println(containerId);
			//1.非空
			if(!ValidateUtil.isValid(containerId)){
				mess = "输入的值不能为空";
			}
			if(!ValidateUtil.isValid(deviceId)){
				mess = "输入的值不能为空";
			}
			if(!ValidateUtil.isValid(type)){
				mess = "输入的值不能为空";
			}
			else{
				Container containerBycontarinId = daoService.getContainerBycontarinId(containerId);
				if(containerBycontarinId!=null){
					mess = "输入的冷藏箱Id已经存在";
				}else{
					Container container = new Container();
					container.setContainerId(containerId);
					container.setDeviceId(deviceId);
					container.setChillerType(type);
					container.setCompanyId(user.getCompanyId());
					container.setRegistTime(registTime);
					container.setRegistLongTime(registLongTime);

					int n = daoService.registNewContainer(container);
					if (n > 0){
						//添加ccdata
						Ccdata ccdata = new Ccdata();
						ccdata.setContainerId(container.getContainerId());
						ccdata.setDeviceId(container.getDeviceId());
						ccdata.setCompanyId(container.getCompanyId());
						ccdata.setChillerType(type);
						ccdata.setNowTime(registTime);
						ccdata.setNowLongTime(registLongTime);
						ccdata.setReceiveTime(registTime);
						ccdata.setReceiveLongTime(registLongTime);
						daoService.addOneNewCcdata(ccdata);
						
						//添加ourccdata
						OurCcdata ourccdata = new OurCcdata();
						ourccdata.setContainerId(container.getContainerId());
						ourccdata.setDeviceId(container.getDeviceId());
						ourccdata.setCompanyId(container.getCompanyId());
						ourccdata.setChillerType(type);
//						System.out.println("33"+ccdata);
						ourccdata.setNowTime(registTime);
						ourccdata.setNowLongTime(registLongTime);
						ourccdata.setReceiveTime(registTime);
						ourccdata.setReceiveLongTime(registLongTime);
						daoService.addOurOneNewCcdata(ourccdata);
						
						//添加bindtable
						BindTable bindtable = new BindTable();
						bindtable.setOperator(user.getUsername());
						bindtable.setContainerId(container.getContainerId());
						bindtable.setDeviceId(container.getDeviceId());
						bindtable.setBindTime(registTime);
						bindtable.setBindLongTime(registLongTime);
						bindtable.setCompanyId(container.getCompanyId());
						daoService.registNewBindTable(bindtable);
						mess = "添加成功";
						flag = ConstantCode.SUCCESS;
					}
					else{
						mess = "添加失败";
					}
					jo.put("flag", flag);
					jo.put("message", mess);
					response.setCharacterEncoding("UTF-8");
					response.getWriter().write(jo.toString());
					response.getWriter().flush();
					response.getWriter().close();
				}
			}
		}
		
		
		
	}
	
	
	
	/**
	 * 删除冷藏箱
	 */
	@ResponseBody
	@RequestMapping(value="/containerDelete")
	public void containerDelete(HttpServletRequest request,HttpServletResponse  response, @RequestParam String username,
			@RequestParam String containerId) throws IOException{
		String mess = "";
		int flag = ConstantCode.ERROR;
		JSONObject jo=new JSONObject();
		
		User user = daoService.getUserByUsername(username);
		if(user != null){
			if (user.getRole().equals("common")){
				mess = "您的权限不够";
			}
			else{
				//删除container
				int n = daoService.removeContainerByContainerId(containerId);
				//删除ccdata
				int n1 = daoService.removeCcdatasByContainerId(containerId);
				//删除bindtable
				int n2 = daoService.removeBindTableByContainerId(containerId);
				//删除ourccdata
				int n3 = daoService.removeOurCcdatasByContainerId(containerId);
				if (n > 0 && n1 > 0 && n2 > 0 && n3 > 0){
					mess = "删除成功";
					flag = ConstantCode.SUCCESS;
				}
				else{
					mess = "删除失败";
				}
			}	
				
		}else{
			mess = "用户不存在";
		}
			jo.put("flag", flag);
			jo.put("message", mess);
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(jo.toString());
			response.getWriter().flush();
			response.getWriter().close();
		
		
	}
	
	
	
		


}
