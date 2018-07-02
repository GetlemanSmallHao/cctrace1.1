package com.cctrace.socketServers.dataHandle;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




import org.aspectj.org.eclipse.jdt.internal.compiler.ast.ArrayAllocationExpression;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cctrace.entity.CommandStore;
import com.cctrace.service.DaoService;
import com.cctrace.socketServers.util.DealDateData;
import com.github.pagehelper.util.StringUtil;


public class TempSet {
	
	public Map<String,Object> tempSet(String setTem, String deviceId, String containerId){
		try {
			String key=null;
			Map<String,Object> map=new HashMap<String, Object>();
			if(!StringUtils.isEmpty(setTem)){
				double a=Double.parseDouble(setTem);
				String tem = null;
				if(a<-17.777){
					int b=(int)((a*1.8+32)*10-0.5);
					String t=""+Integer.toHexString(b);
					tem=t.substring(t.length()-4, t.length());
						
				}else{
					int b=(int)((a*1.8+32)*10);
					String t=""+Integer.toHexString(b);
					if(t.length()<1){
						tem="0000";
					}else if(t.length()==1){
						tem="000"+t;
					}else if(t.length()==2){
						tem="00"+t;
					}else if(t.length()==3){
						tem="0"+t;
					}else if(t.length()==4){
						tem=t;
					}else{
						
					}
				
				}
				if(!StringUtils.isEmpty(tem)){
					String t1=tem.substring(0,2);
					String t2=tem.substring(2,4);
					short a1=Short.parseShort("93",16);
					short a2=Short.parseShort("CE",16);
					short a3=Short.parseShort("03",16);
					short a4=Short.parseShort("11",16);
					short a5=Short.parseShort(t1,16);
					short a6=Short.parseShort(t2,16);
					
					short a7=(short) (a1+a2+a3+a4+a5+a6);
					
					String two=Integer.toBinaryString(a7).toString();
					if(two.length()<8){
						two="00000000"+two;
					}
					String two8=two.substring(two.length()-8, two.length()).toString();
					StringBuffer ts=new StringBuffer();
					for(int c=0;c<two8.length();c++){
						String d=two8.substring(c,c+1).toString();
						if(d.equals("1")){
							ts.append(""+0);
						}else{
							ts.append(""+1);
						}
					};
					
					Integer e=Integer.valueOf(ts.toString(),2)+1;
					String lt=""+Integer.toHexString(e);
					if(lt.length()<2){
						lt="00"+lt;
					}
					String lat=lt.substring(lt.length()-2,lt.length());
					StringBuffer sb = new StringBuffer();
			        for(int i=0;i<lat.length();i++){
			        	char f = lat.charAt(i);
			            if(Character.isLowerCase(f)){
			            	sb.append(Character.toUpperCase(f)); 
			            }else{
			            	sb.append(f);
			            }
			        }
			        StringBuffer t3 = new StringBuffer();
			        for(int i=0;i<t1.length();i++){
			        	char g = t1.charAt(i);
			            if(Character.isLowerCase(g)){
			            	t3.append(Character.toUpperCase(g)); 
			            }else{
			            	t3.append(g);
			            }
			        }
			        StringBuffer t4 = new StringBuffer();
			        for(int i=0;i<t1.length();i++){
			        	char h = t2.charAt(i);
			            if(Character.isLowerCase(h)){
			            	t4.append(Character.toUpperCase(h)); 
			            }else{
			            	t4.append(h);
			            }
			        }
			        t1=t3.toString();
			        t2=t4.toString();
//		        key="460040094706516 0B 93CF0311"+t1+t2+sb.toString()+"9300CE9F";//错误指令
			        key=deviceId+" 0B 93CE0311"+t1+t2+sb.toString()+"9300CE9F";
//		        key="4600400947065160B93CE0311"+t1+t2+sb.toString()+"9300CE9F";
//		        key="93CE0311"+t1+t2+sb.toString()+"9300CE9F";
			        
				}
			}
			
			if(StringUtil.isNotEmpty(key)){
				map.put("key", key);
				CommandStore cs=new CommandStore();
				Long currentLongTime=System.currentTimeMillis();
				DealDateData dealDateData=new DealDateData();
				String strFormatNowDate=dealDateData.getStringDate(currentLongTime);
			    cs.setContent(key);
			    cs.setLongTime(""+currentLongTime);
			    cs.setTime(strFormatNowDate);
			    cs.setCommand("temSet");
			    cs.setType("1");
			    cs.setStatus("0");
			    cs.setValue(setTem);
			    cs.setContainerId(containerId);
			    map.put("command", "temSet");
			    map.put("object", cs);
			    return map ;
			   
			}
			return null;
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			System.out.println("设置温度异常");
		}
		return null;
		
	}
}
