package com.cctrace.socketServers.dataHandle;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestParam;

import com.cctrace.entity.User;
import com.cctrace.utils.JsonResult;

public class ModeDate {
	
	public Map<String,String> modeDateHandle( Map<String,String> date){
		try {
			String c2=date.get("c2");
			String j=date.get("j");
			Map<String, String> map = new HashMap<String, String>();
			if(c2.equals("0")){
				String doreStatue="0";
			}else{
				String doreStatue="1";
				String jt="00000000"+Integer.toBinaryString(Integer.parseInt(j,16));
				String j8=jt.substring(jt.length()-8,jt.length());
				String j86=j8.substring(0,3);
				int num=0;
				String j5=null;
				String j4=null;
				String workMode=null;
				if(j86.equals("000")){
					workMode="冷机关闭";
				}else if(j86.equals("001")){
					workMode="冷机制冷";
				}else if(j86.equals("010")){
					workMode="冷机加热";
				}else if(j86.equals("011")){
					workMode="除霜";
				}else if(j86.equals("110")){
					workMode="睡眠";
				}else if(j86.equals("101")){
					workMode="Pretrip";
				}
				String j51=null;
				if(j86.equals("001") || j86.equals("010")){
					j51=j8.substring(3,4);//0发电机启停,1发电机连续
					if(j51.equals("0")){
						j5="auto";
					}else{
						j5="continuous";
					}
					String j41=j8.substring(4,5);//0发电机低速运行,1发电机高速运行
					if(j41.equals("0")){
						j4="发电机低速运行";
					}else{
						j4="发电机高速运行";
					}
				}
				String j3=j8.substring(5,6);//0冷藏箱门关闭，1冷藏箱门打开
				String doreStatus=null;
				if(j3.equals("1")){
					doreStatus="箱门打开";
				}else{
					doreStatus="箱门关闭";
				}
				String j2=j8.substring(6,7);//0柴油发电，1电力发动
				String power=null;
				if(j2.equals("1")){
					power="电力发动";
				}else{
					power="柴油发电";
				}
				map.put("workMode",workMode);
				map.put("j5",j5);
				map.put("j4",j4);
				map.put("j51",j51);
				
				map.put("j3",doreStatus);
				map.put("j2",power);
				
			}
			
			return map;
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			System.out.println("冷王数据运行模式解析报错");
		}
		return null;
		
	}

}
