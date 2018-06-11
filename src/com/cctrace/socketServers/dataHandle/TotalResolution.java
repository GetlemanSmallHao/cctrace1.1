package com.cctrace.socketServers.dataHandle;

import java.util.HashMap;
import java.util.Map;

import com.cctrace.entity.Ccdata1;
import com.cctrace.entity.Container;
import com.cctrace.service.DaoService;
import com.github.pagehelper.util.StringUtil;


public class TotalResolution {
	
	public DaoService daoService;
	public TotalResolution(DaoService daoService) {
		this.daoService = daoService;
	}
	
	public Map<String , String> totalData( String sevNum,String content){
		
		try {
			Ccdata1 cc1=daoService.selectCcdataByDeviceId1(sevNum);
			if(cc1==null){
				return null;
			}
			String chillerType=cc1.getChillerType();
			if(chillerType==null){
				return null;
			}else if(chillerType.equals("carrier")){//开利数据解析
				String nums[]=content.split("\\s+");
				Integer lengs=nums.length;
				String putVal=null;
				if(lengs>8){
					String data2=nums[1];
					if(data2.equals("ZY")){
						Map<String , String> map=new HashMap<String , String>();
						putVal="ourData";
						map.put("putVal", putVal);
						map.put("dataAll", content);
						map.put("chillerType", "carrier");
						return map;
					}
					
				}else if(lengs ==2){
					String data=nums[1];
					if(data.equals("on") || data.equals("off")){
						Map<String , String> map=new HashMap<String , String>();
						putVal="cs";
						map.put("putVal", putVal);
						map.put("dataAll", data);
						map.put("chillerType", "thermoking");
						return map;
					}
					
				
					Integer leng86=data.indexOf("86");
					data=data.substring(leng86,data.length());
					String type=data.substring(0,2);
					int a=0;
					if(!type.equals("86")){
						for(a=0;a<data.length();a++){
							type=data.substring(a,a+2);
							if(type.equals("86")){
								break;
							}
						}
					}
					if(type.equals("86")){
					data=data.substring(a,data.length());
					Integer dl=data.length();
					if(dl<7){
					   return null;
					}
					String pId=data.substring(4,6);
					if(pId.equals("A0")|| pId.equals("A1") || pId.equals("A2") || pId.equals("A3") || pId.equals("A4") || pId.equals("AB")){
						Integer length=data.length();
						Map<String , String> map=new HashMap<String , String>();
						
						int length2=length/2;
						String dataAll = "";
						for(int i=0;i<length2;i++){
							String datas=data.substring(2*i, 2*(i+1));
							if(i==0){
								dataAll=datas;
							}else{
								dataAll=dataAll+" "+datas;
							}
						}
						putVal="cs";
						map.put("putVal", putVal);
						map.put("dataAll", dataAll);
						map.put("chillerType", "carrier");
						return map;
					}else if(pId.equals("81")|| pId.equals("82") || pId.equals("87") || pId.equals("88") || pId.equals("8A") || pId.equals("8B")){
						Integer length=data.length();
						Map<String , String> map=new HashMap<String , String>();
						
						int length2=length/2;
						String dataAll = "";
						for(int i=0;i<length2;i++){
							String datas=data.substring(2*i, 2*(i+1));
							if(i==0){
								dataAll=datas;
							}else{
								dataAll=dataAll+" "+datas;
							}
						}
						putVal="dataHandle";
						map.put("putVal", putVal);
						map.put("dataAll", dataAll);
						map.put("chillerType", "carrier");
						return map;
						
					}
					
					return null;
				}
			
					/*else if(data.indexOf("83")!=-1){
					Integer leng83=data.indexOf("83");
					data=data.substring(leng83,data.length());
					String type=data.substring(0,2);
					int a=0;
					if(!type.equals("83")){
						for(a=0;a<data.length();a++){
							type=data.substring(a,a+2);
							if(type.equals("83")){
								break;
							}
						}
					}
					data=data.substring(a,data.length());
					Integer dl=data.length();
					if(dl<7){
					   return null;
					}
					String pId=data.substring(4,6);
					if(pId.equals("AB")){
						Integer length=data.length();
						Map<String , String> map=new HashMap<String , String>();
						
						int length2=length/2;
						String dataAll = "";
						for(int i=0;i<length2;i++){
							String datas=data.substring(2*i, 2*(i+1));
							if(i==0){
								dataAll=datas;
							}else{
								dataAll=dataAll+" "+datas;
							}
						}
						putVal="cs";
						map.put("putVal", putVal);
						map.put("dataAll", dataAll);
						map.put("chillerType", "carrier");
						return map;
					}
				
					return null;
					
				}
					*/
				}
				
			}else{//冷王数据解析
				String nums[]=content.split("\\s+");
				Integer lengs=nums.length;
				String putVal=null;
				if(lengs>8){
					String data2=nums[1];
					if(data2.equals("ZY")){
						Map<String , String> map=new HashMap<String , String>();
						putVal="ourData";
						map.put("putVal", putVal);
						map.put("dataAll", content);
						map.put("chillerType", "thermoking");
						return map;
					}
					
				}else if(lengs ==2){
					String data=nums[1];
					Integer leng93 = data.indexOf("93");
					if (leng93 == -1) {
						return null;
					}
					data = data.substring(leng93, data.length());
					String type=data.substring(0,2);
					int a=0;
					if(!type.equals("93")){
						for(a=0;a<data.length();a++){
							type=data.substring(a,a+2);
							if(type.equals("93")){
								break;
							}
						}
					}
					data=data.substring(a,data.length());
					Integer dl=data.length();
					if(dl<5){
					   return null;
					}
					String pId=data.substring(2,4);
					if(pId.equals("C8")){
						Integer length=data.length();
						Map<String , String> map=new HashMap<String , String>();
						
						int length2=length/2;
						String dataAll = "";
						for(int i=0;i<length2;i++){
							String datas=data.substring(2*i, 2*(i+1));
							if(i==0){
								dataAll=datas;
							}else{
								dataAll=dataAll+" "+datas;
							}
						}
						putVal="dataAnalysis";
						map.put("putVal", putVal);
						map.put("dataAll", dataAll);
						map.put("chillerType", "thermoking");
						return map;
						
					}else if(pId.equals("A8") || pId.equals("AB") || pId.equals("F6")||pId.equals("F7")||pId.equals("CF")){
						
						Integer length=data.length();
						int length2=length/2;
						String dataAll="";
						for(int i=0;i<length2;i++){
							String datas=data.substring(2*i, 2*(i+1));
							if(i==0){
								dataAll=datas;
							}else{
								dataAll=dataAll+" "+datas;
							}
							
						}
						Map<String , String> map=new HashMap<String , String>();
						putVal="dataHandle";
						map.put("putVal", putVal);
						map.put("dataAll", dataAll);
						map.put("chillerType", "thermoking");
						return map;
						
					}else if(pId.equals("CD") || pId.equals("CE")){
						Map<String , String> map=new HashMap<String , String>();
						Integer dataL=data.length();
						if(dataL==8){
						
						putVal="cs";
						map.put("putVal", putVal);
						map.put("dataAll", data);
						map.put("chillerType", "thermoking");
						return map;
						}else if(dataL>10){
							String data1=data.substring(0,8);
							String data2=data.substring(8,10);
							String data3=data.substring(dataL-8,dataL);
							String data4=data.substring(dataL-8,dataL-6);
							if(data2.equals("93")){
								putVal="cs";
								map.put("putVal", putVal);
								map.put("dataAll", data1);
								map.put("chillerType", "thermoking");
								return map;
							}else if(data4.equals("93")){
								putVal="cs";
								map.put("putVal", putVal);
								map.put("dataAll", data3);
								map.put("chillerType", "thermoking");
								return map;
							}
							
						}else{
							return null;
						}
					}
				}else{
					return null;
				}
			}
			
		} catch (Exception e) {
			System.out.println("分析数据有误！");
			e.printStackTrace();
		}
		
		return null;
		
	}
		
}
