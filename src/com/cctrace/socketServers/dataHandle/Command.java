package com.cctrace.socketServers.dataHandle;

import java.util.HashMap;
import java.util.Map;

import com.cctrace.entity.CommandStore;
import com.cctrace.socketServers.carryHandle.CarryTemset;
import com.cctrace.socketServers.util.DealDateData;
import com.github.pagehelper.util.StringUtil;


/**
 * 
 * @author Administrator
 *控制指令的设定
 *
 */
public class Command {
	
	// 冷机类型  thermoking：冷王   carrier：开利
	//private String chillerType;
	public Map<String,Object> method(String data,String deviceId,String containerId,String chillerType){
		
		try {
			//控制指令
			String commandSet=null;
			String command=null;
			Map<String,Object> map=new HashMap<String, Object>();
			CommandStore cs=new CommandStore();
			if(data.equals("remoteSwiMacOn")){//开机
//				commandSet="460040094706516 04 9300A8C5";//错误指令
				if("thermoking".equals(chillerType)){
					commandSet=deviceId+" 08 93CDE0C09300CDA0";
				}else{
					commandSet = CarryTemset.getSendData(CarryTemset.startUp,deviceId);
				}
				
//				commandSet="4600400947065160893CDE0C09300CDA0";
//				commandSet="93CDE0C09300CDA0";
				cs.setValue("on");
				cs.setCommand("remoteSwiMac");
				command="remoteSwiMac";
			}else if(data.equals("remoteSwiMacOff")){//关机
//				commandSet="460040094706516 04 9300ABC2";//错误指令
				if("thermoking".equals(chillerType)){
					commandSet=deviceId+" 08 93CD00A09300CDA0";
				}else{
					commandSet = CarryTemset.getSendData(CarryTemset.shutDown,deviceId);
				}
//				commandSet="4600400947065160893CD00A09300CDA0";
//				commandSet="93CD00A09300CDA0";
				cs.setValue("off");
				cs.setCommand("remoteSwiMac");
				command="remoteSwiMac";
			}else if(data.equals("remoteXFSwiMacOn")){//开机
//				commandSet="460040094706516 04 9300A8C5";//错误指令
				if("thermoking".equals(chillerType)){
					commandSet=deviceId+" 09 93CE01504E9300CE9F";
				}else{
					commandSet = CarryTemset.getSendData(CarryTemset.kxfmStartUp,deviceId);
				}
				
//				commandSet="4600400947065160893CDE0C09300CDA0";
//				commandSet="93CDE0C09300CDA0";
				cs.setValue("on");
				cs.setCommand("remoteXFSwiMac");
				command="remoteXFSwiMac";
			}else if(data.equals("remoteXFSwiMacOff")){//关机
//				commandSet="460040094706516 04 9300ABC2";//错误指令
				if("thermoking".equals(chillerType)){
					commandSet=deviceId+" 09 93CE01603E9300CE9F";
				}else{
					commandSet = CarryTemset.getSendData(CarryTemset.kxfmShutDown,deviceId);
				}
//				commandSet="4600400947065160893CD00A09300CDA0";
//				commandSet="93CD00A09300CDA0";
				cs.setValue("off");
				cs.setCommand("remoteXFSwiMac");
				command="remoteXFSwiMac";
			}else if(data.equals("selfCheck")){//自检
//				commandSet="460040094706516 04 9300F677";//错误指令
				if("thermoking".equals(chillerType)){
					commandSet=deviceId+" 09 93CE01316D9300CE9F";
				}else{
					commandSet = CarryTemset.getSendData(CarryTemset.startSelfTest,deviceId);
				}
//				commandSet="4600400947065160993CE01316D9300CE9F";
//				commandSet="93CE01316D9300CE9F";
				cs.setCommand(data);
				command=data;
			}else if(data.equals("clearAlert")){//清除警报
//				commandSet="460040094706516 04 9300F776";//错误指令
				if("thermoking".equals(chillerType)){
					commandSet=deviceId+" 09 93CE01207E9300CE9F";
				}else{
					commandSet = CarryTemset.getSendData(CarryTemset.clearAlarm,deviceId);
				}
//				commandSet="4600400947065160993CE01207E9300CE9F";
//				commandSet="93CE01207E9300CE9F";
				cs.setCommand(data);
				command=data;
			}else if(data.equals("bootDef")){//除霜
//				commandSet="460040094706516 04 9300CF9E";//错误指令
				if("thermoking".equals(chillerType)){
					commandSet=deviceId+" 09 93CE01019D9300CE9F";
				}else{
					commandSet = CarryTemset.getSendData(CarryTemset.defrostCommand,deviceId);
				}
//				commandSet="4600400947065160993CE01019D9300CE9F";
//				commandSet="93CE01019D9300CE9F";
				cs.setCommand(data);
				command=data;
			}
			//运行模式
			if(data.equals("continuous")){//连续运转模式
//				commandSet="460040094706516 04 9300C8A5";//错误指令
				if("thermoking".equals(chillerType)){
					commandSet=deviceId+" 0A 93CE0240401D9300CE9F";
				}else{
					commandSet = CarryTemset.getSendData(CarryTemset.continuation,deviceId);
				}
//				commandSet="4600400947065160A93CE0240401D9300CE9F";
//				commandSet="93CE0240401D9300CE9F";
				cs.setCommand("refRunMode");
				cs.setValue(data);
				command="refRunMode";
			}else if(data.equals("auto")){//启停运转模式
				if("thermoking".equals(chillerType)){
					commandSet=deviceId+" 0A 93CE024080DD9300CE9F";
				}else{
					commandSet = CarryTemset.getSendData(CarryTemset.startStop,deviceId);
				}
//				commandSet="4600400947065160A93CE024080DD9300CE9F";
//				commandSet="93CE024080DD9300CE9F";
				cs.setCommand("refRunMode");
				cs.setValue(data);
				command="refRunMode";
			}
			
			if(StringUtil.isNotEmpty(commandSet)){
				map.put("commandSet", commandSet);
				
				Long currentLongTime=System.currentTimeMillis();
				DealDateData dealDateData=new DealDateData();
				String strFormatNowDate=dealDateData.getStringDate(currentLongTime);
		        
		        cs.setContent(commandSet);
		        cs.setLongTime(""+currentLongTime);
		        cs.setTime(strFormatNowDate);
		        cs.setType("1");
		        cs.setStatus("0");
		        cs.setContainerId(containerId);
		        map.put("object", cs);
		        map.put("command", command);
		        return map ;
			}
		} catch (Exception e) {
			System.out.println("控制指令拼接报错!");
			e.printStackTrace();
		}
		
		return null;
		
	}

}
