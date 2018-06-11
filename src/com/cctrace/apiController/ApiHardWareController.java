package com.cctrace.apiController;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;







import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;




import com.cctrace.socketServers.delData.ProcessSocketData;



import com.cctrace.entity.Alert;
import com.cctrace.entity.Ccdata;
import com.cctrace.entity.Container;
import com.cctrace.entity.User;
import com.cctrace.service.DaoService;
import com.cctrace.utils.BaseResult;
import com.cctrace.utils.ConstantCode;
import com.cctrace.utils.JsonResult;
import com.github.pagehelper.util.StringUtil;
import com.sun.xml.internal.ws.util.StringUtils;


@Controller
@RequestMapping("/apiUser")
public class ApiHardWareController {
	
	@Resource(name="daoService") 
	private DaoService daoService;
	ProcessSocketData psd=new ProcessSocketData(null);
	public void setDaoService(DaoService daoService) {
		this.daoService = daoService;
	}
	/**
	 * username
	 * 
	 */
	
	/**
	 * 设置温度
	 */
	@ResponseBody
	@RequestMapping(value = "/setTemp")
	public BaseResult setTemp(HttpServletRequest request,
			String username,String setTemp, String containerId) {

		String mess = "";
		int flag = ConstantCode.ERROR;
		BaseResult result = new BaseResult();
		User user=daoService.getUserByUsername(username);
		if(user==null){
			mess="该用户不存在,请稍后重试！";
			result.setMessage(mess);
			result.setFlag(flag);
			return result;
		}
		String  role=user.getRole();
		if(role.equals("common") || StringUtil.isEmpty(role)){
			mess="该用户权限较低,不能进行设置！";
			result.setMessage(mess);
			result.setFlag(flag);
			return result;
		}
		
		Container container = daoService.getContainerBycontarinId(containerId);
		
		if (StringUtil.isEmpty(setTemp)) {
			mess = "温度不能为空！";
			result.setMessage(mess);
			result.setFlag(flag);
			return result;
		} else if (container == null) {
			mess = "该设备不存在，请核对！";
			result.setMessage(mess);
			result.setFlag(flag);
			return result;
		} else {
			Map<String, String> mapSet = new HashMap<String, String>();
			mapSet.put("data", setTemp);
			mapSet.put("type", "temp");
			mapSet.put("dev", container.getDeviceId());
			mapSet.put("num", containerId);
			Map<String, Object> getMap = psd.dataResponse(mapSet);
			mess = (String) getMap.get("msg");
			String status = (String) getMap.get("flag");
			if (status.equals("0")) {
				container.setSetTemp(Double.valueOf(setTemp));
				daoService.modifyContainerById(container);
				mess = "温度设定成功！";
				flag = ConstantCode.SUCCESS;
			}
		}
			result.setMessage(mess);
			result.setFlag(flag);
			return result;
	}

	/**
	 * 除霜设置
	 */
	@ResponseBody
	@RequestMapping(value = "/bootDef")
	public BaseResult bootDef(HttpServletRequest request,
			String username,String bootDef, String containerId) {

		String mess = "";
		int flag = ConstantCode.ERROR;
		BaseResult result = new BaseResult();
		User user=daoService.getUserByUsername(username);
		if(user==null){
			mess="该用户不存在,请稍后重试！";
			result.setMessage(mess);
			result.setFlag(flag);
			return result;
		}
		String  role=user.getRole();
		if(role.equals("common") || StringUtil.isEmpty(role)){
			mess="该用户权限较低,不能进行设置！";
			result.setMessage(mess);
			result.setFlag(flag);
			return result;
		}
		
		Container container = daoService.getContainerBycontarinId(containerId);
		if (container == null || StringUtil.isEmpty(bootDef)) {
			mess = "操作指令失败！";
			result.setMessage(mess);
			result.setFlag(flag);
			return result;
		}
		String data = null;
		if (bootDef.equals("on")) {
			data = "bootDef";
			Map<String, String> mapSet = new HashMap<String, String>();
			mapSet.put("data", data);
			mapSet.put("type", "command");
			mapSet.put("dev", container.getDeviceId());
			mapSet.put("num", containerId);
			Map<String, Object> getMap = psd.dataResponse(mapSet);
			mess = (String) getMap.get("msg");
			String status = (String) getMap.get("flag");
			if (status.equals("0")) {
				container.setBootDef(bootDef);
				daoService.modifyContainerById(container);
				mess = "修改成功";
				flag = ConstantCode.SUCCESS;
			}

		} else {
			mess = "指令不能为空！";
			flag = ConstantCode.ERROR;
		}

			result.setMessage(mess);
			result.setFlag(flag);
			return result;
	}

	/**
	 * 告警设置
	 */
	@ResponseBody
	@RequestMapping(value = "/clearAlert")
	public BaseResult clearAlert(HttpServletRequest request,
			String username,String clearAlert, String containerId) {

		String mess = "";
		int flag = ConstantCode.ERROR;
		BaseResult result = new BaseResult();
		
		User user=daoService.getUserByUsername(username);
		if(user==null){
			mess="该用户不存在,请稍后重试！";
			result.setMessage(mess);
			result.setFlag(flag);
			return result;
		}
		String  role=user.getRole();
		if(role.equals("common") || StringUtil.isEmpty(role)){
			mess="该用户权限较低,不能进行设置！";
			result.setMessage(mess);
			result.setFlag(flag);
			return result;
		}
		
		Container container = daoService.getContainerBycontarinId(containerId);
		if (container == null || StringUtil.isEmpty(clearAlert)) {
			mess = "操作指令失败！";
			result.setMessage(mess);
			result.setFlag(flag);
			return result;
		}
		String data = null;
		if (clearAlert.equals("on")) {
			data = "clearAlert";
			Map<String, String> mapSet = new HashMap<String, String>();
			mapSet.put("data", data);
			mapSet.put("type", "command");
			mapSet.put("dev", container.getDeviceId());
			mapSet.put("num", containerId);
			Map<String, Object> getMap = psd.dataResponse(mapSet);
			mess = (String) getMap.get("msg");
			String status = (String) getMap.get("flag");
			if (status.equals("0")) {
				container.setClearAlert(clearAlert);
				daoService.modifyContainerById(container);
				mess = "告警设置成功";
				flag = ConstantCode.SUCCESS;
			}
		} else {
			mess = "指令不能为空！";
		}
			result.setMessage(mess);
			result.setFlag(flag);
			return result;
	}

	/**
	 * 自检设置
	 */
	@ResponseBody
	@RequestMapping(value = "/selfCheck")
	public BaseResult selfCheck(HttpServletRequest request,
			String username,String selfCheck, String containerId) {

		String mess = "";
		int flag = ConstantCode.ERROR;
		BaseResult result = new BaseResult();
		
		User user=daoService.getUserByUsername(username);
		if(user==null){
			mess="该用户不存在,请稍后重试！";
			result.setMessage(mess);
			result.setFlag(flag);
			return result;
		}
		String  role=user.getRole();
		if(role.equals("common") || StringUtil.isEmpty(role)){
			mess="该用户权限较低,不能进行设置！";
			result.setMessage(mess);
			result.setFlag(flag);
			return result;
		}
		
		Container container = daoService.getContainerBycontarinId(containerId);
		String data = null;
		if (container == null || StringUtil.isEmpty(selfCheck)) {
			mess = "操作指令失败！";
			result.setMessage(mess);
			result.setFlag(flag);
			return result;
		}
		if (selfCheck.equals("on")) {
			data = "selfCheck";
			Map<String, String> mapSet = new HashMap<String, String>();
			mapSet.put("data", data);
			mapSet.put("type", "command");
			mapSet.put("dev", container.getDeviceId());
			mapSet.put("num", containerId);
			Map<String, Object> getMap = psd.dataResponse(mapSet);
			mess = (String) getMap.get("msg");
			String status = (String) getMap.get("flag");
			if (status.equals("0")) {
				container.setSelfCheck(selfCheck);
				daoService.modifyContainerById(container);
				mess = "自检设置成功";
				flag = ConstantCode.SUCCESS;
			}
		} else {
			mess = "指令不能为空！";
		}

			result.setMessage(mess);
			result.setFlag(flag);
			return result;
	}

	/**
	 * 冷机运行模式设置
	 */
	@ResponseBody
	@RequestMapping(value = "/refRunMode")
	public BaseResult refRunMode(HttpServletRequest request,
			String username,String refRunMode, String containerId) {

		String mess = "";
		int flag = ConstantCode.ERROR;
		BaseResult result = new BaseResult();
		
		User user=daoService.getUserByUsername(username);
		if(user==null){
			mess="该用户不存在,请稍后重试！";
			result.setMessage(mess);
			result.setFlag(flag);
			return result;
		}
		String  role=user.getRole();
		if(role.equals("common") || StringUtil.isEmpty(role)){
			mess="该用户权限较低,不能进行设置！";
			result.setMessage(mess);
			result.setFlag(flag);
			return result;
		}
		
		Container container = daoService.getContainerBycontarinId(containerId);
		String data = null;
		if (container == null || StringUtil.isEmpty(refRunMode)) {
			mess = "操作指令失败！";
			result.setMessage(mess);
			result.setFlag(flag);
			return result;
		}
		if (refRunMode.equals("continuous")) {
			data = "refRunMode";
			Map<String, String> mapSet = new HashMap<String, String>();
			mapSet.put("data", data);
			mapSet.put("type", "command");
			mapSet.put("dev", container.getDeviceId());
			mapSet.put("num", containerId);
			Map<String, Object> getMap = psd.dataResponse(mapSet);
			mess = (String) getMap.get("msg");
			String status = (String) getMap.get("flag");
			if (status.equals("0")) {
				container.setRefRunMode(refRunMode);
				daoService.modifyContainerById(container);
				mess = "设定连续运转模式成功！";
				flag = ConstantCode.SUCCESS;
			}
		} else if (refRunMode.equals("auto")) {
			data = "auto";
			Map<String, String> mapSet = new HashMap<String, String>();
			mapSet.put("data", data);
			mapSet.put("type", "command");
			mapSet.put("dev", container.getDeviceId());
			mapSet.put("num", containerId);
			Map<String, Object> getMap = psd.dataResponse(mapSet);
			mess = (String) getMap.get("msg");
			String status = (String) getMap.get("flag");
			if (status.equals("0")) {
				container.setRefRunMode(refRunMode);
				daoService.modifyContainerById(container);
				mess = "启停运转模式成功！";
				flag = ConstantCode.SUCCESS;
			}
		}

			result.setMessage(mess);
			result.setFlag(flag);
			return result;
	}

	/**
	 * 开关机设置
	 */
	@ResponseBody
	@RequestMapping(value = "/remoteSwiMac")
	public BaseResult remoteSwiMac(HttpServletRequest request,
			String username,String remoteSwiMac, String containerId) {

		String mess = "";
		int flag = ConstantCode.ERROR;
		BaseResult result = new BaseResult();
		
		User user=daoService.getUserByUsername(username);
		if(user==null){
			mess="该用户不存在,请稍后重试！";
			result.setMessage(mess);
			result.setFlag(flag);
			return result;
		}
		String  role=user.getRole();
		if(role.equals("common") || StringUtil.isEmpty(role)){
			mess="该用户权限较低,不能进行设置！";
			result.setMessage(mess);
			result.setFlag(flag);
			return result;
		}
		
		Container container = daoService.getContainerBycontarinId(containerId);
		if (container == null || StringUtil.isEmpty(remoteSwiMac)) {
			mess = "操作指令失败！";
			result.setMessage(mess);
			result.setFlag(flag);
			return result;
		}
		String data = null;
		if (remoteSwiMac.equals("on")) {
			data = "remoteSwiMacOn";
			Map<String, String> mapSet = new HashMap<String, String>();
			mapSet.put("data", data);
			mapSet.put("type", "command");
			mapSet.put("dev", container.getDeviceId());
			mapSet.put("num", containerId);
			Map<String, Object> getMap = psd.dataResponse(mapSet);
			mess = (String) getMap.get("msg");
			String status = (String) getMap.get("flag");
			if (status.equals("0")) {
				container.setRemoteSwiMac(remoteSwiMac);
				daoService.modifyContainerById(container);
				mess = "设置开机成功！";
				flag = ConstantCode.SUCCESS;
			}
		} else if (remoteSwiMac.equals("off")) {
			data = "remoteSwiMacOff";
			Map<String, String> mapSet = new HashMap<String, String>();
			mapSet.put("data", data);
			mapSet.put("type", "command");
			mapSet.put("dev", container.getDeviceId());
			mapSet.put("num", containerId);
			Map<String, Object> getMap = psd.dataResponse(mapSet);
			mess = (String) getMap.get("msg");
			String status = (String) getMap.get("flag");
			if (status.equals("0")) {
				container.setRemoteSwiMac(remoteSwiMac);
				daoService.modifyContainerById(container);
				mess = "设置关机成功！";
				flag = ConstantCode.SUCCESS;
			}
		}

			result.setMessage(mess);
			result.setFlag(flag);
			return result;
	}
	
	
	/*
	@RequestMapping("/setHardwareInfo")
	@ResponseBody
	public BaseResult setHardwareInfo(HttpServletRequest request,@RequestParam String username,
			@RequestParam String containerId, @RequestParam String typeCount, @RequestParam String dataCount) 
		throws IOException{
		BaseResult result = new BaseResult();
		int flag = ConstantCode.ERROR;
		String mess = "";
		String deviceId = daoService.selectCcdataBycontainerId(containerId).getDeviceId();
		Container container = daoService.getContainerBycontarinId(containerId);
		User user = daoService.getUserByUsername(username);
		if (user != null){
			String role = user.getRole();
			if (role.equals("common")){
				mess = "您的权限不够";
				result.setFlag(flag);
				result.setMessage(mess);
				return result;
			}else{
				Map<String, String> map = new HashMap<>();
				map.put("num", deviceId);
				map.put("type", typeCount);
				map.put("data", dataCount);
				Map<String, Object> resultMap = new HashMap<>();
				resultMap = ProcessSocketData.dataResponse(map);
				int flagresult = (int) resultMap.get("flag");
				if(flagresult == 0){
					//此处设置指令
					if(typeCount.equals("common")){
						if(dataCount.equals("开机")){
							container.setRemoteSwiMac("开机");
							int n = daoService.updateContainerByContainerId(container);
							if(n > 0){
								mess = "指令已生效";
								flag = ConstantCode.SUCCESS;
							}else{
								mess = "指令未生效";
							}
						}else if(dataCount.equals("关机")){
							container.setRemoteSwiMac("关机");
							int n = daoService.updateContainerByContainerId(container);
							if(n > 0){
								mess = "指令已生效";
								flag = ConstantCode.SUCCESS;
							}else{
								mess = "指令未生效";
							}
						}else if(dataCount.equals("自检")){
							container.setSelfCheck("自检");
							int n = daoService.updateContainerByContainerId(container);
							if(n > 0){
								mess = "指令已生效";
								flag = ConstantCode.SUCCESS;
							}else{
								mess = "指令未生效";
							}
						}else if(dataCount.equals("清除警报")){
							container.setClearAlert("清除警报");
							int n = daoService.updateContainerByContainerId(container);
							if(n > 0){
								mess = "指令已生效";
								flag = ConstantCode.SUCCESS;
							}else{
								mess = "指令未生效";
							}
						}else if(dataCount.equals("除霜")){
							container.setBootDef("启动除霜");
							int n = daoService.updateContainerByContainerId(container);
							if(n > 0){
								mess = "指令已生效";
								flag = ConstantCode.SUCCESS;
							}else{
								mess = "指令未生效";
							}
						}
						//运行模式
						if(dataCount.equals("连续")){
							container.setRefRunMode("连续");
							int n = daoService.updateContainerByContainerId(container);
							if(n > 0){
								mess = "指令已生效";
								flag = ConstantCode.SUCCESS;
							}else{
								mess = "指令未生效";
							}
						}else if(dataCount.equals("启停")){
							container.setRefRunMode("启停");
							int n = daoService.updateContainerByContainerId(container);
							if(n > 0){
								mess = "指令已生效";
								flag = ConstantCode.SUCCESS;
							}else{
								mess = "指令未生效";
							}
						}
					}else{
						//此处设置温度
						double temp = Double.parseDouble(dataCount);
						container.setSetTemp(temp);
						int n = daoService.updateContainerByContainerId(container);
						if(n > 0){
							mess = "温度设置成功";
							flag = ConstantCode.SUCCESS;
						}else{
							mess = "温度设置失败";
						}
					
					}
					
				}else{
					mess = "操作失败";
				}
						
				
			}
		}else{
			mess = "此用户不存在";
			
		}
		result.setMessage(mess);
		result.setFlag(flag);
		return result;
	}	

	*/
	/**
	 * 查询
	 */
	
	@RequestMapping("selectHardWareBycontainerId")
	@ResponseBody
	public JsonResult<Container> selectHardWareBycontainerId(HttpServletRequest request,@RequestParam String containerId)
	 throws IOException{
		BaseResult result = new BaseResult();
		String mess = "没有此硬件信息";
		int flag = ConstantCode.ERROR;
		Container container = null;
		if(daoService.getContainerBycontarinId(containerId) !=null){
			container = daoService.getContainerBycontarinId(containerId);
			if(StringUtil.isEmpty(container.getChillerType())){
				container.setChillerType("");
			}
			if(StringUtil.isEmpty(container.getRemoteSwiMac())){
				container.setRemoteSwiMac("");
			}
			if((Double)container.getSetTemp() == null){
				container.setSetTemp(0.0);
			}
			if(StringUtil.isEmpty(container.getRefRunMode())){
				container.setRefRunMode("");
			}
			mess  = "此硬件存在";
			flag = ConstantCode.SUCCESS;
			
		}
		
		return new JsonResult<Container>(flag, mess, container);
	}
	
	/**
	 * 模糊查询冷藏箱编号
	 * 
	 * 
	 */
	@RequestMapping("/selectContainersLikeyInHard")
	@ResponseBody
	public List<Container> selectContainersLikeyInHard(HttpServletRequest request, @RequestParam String containerId){
		List<Container> containers = null;
		if(daoService.selectContainersLikeyInHard(containerId) != null){
			containers = daoService.selectContainersLikeyInHard(containerId);
		}
		return containers;
	}
	
}
