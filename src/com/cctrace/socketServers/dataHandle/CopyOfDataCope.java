/*package com.cctrace.socketServers.dataHandle;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cctrace.service.DaoService;
@Controller
public class CopyOfDataCope {

	@Resource(name="daoService") 
	private DaoService daoService;
	private ModeDate modeDate;
	@RequestMapping("/")
	public String dataProcess(){
		Map<String, String> mapJ;
		String data="93 C8 11 29 F2 03 59 03 52 03 02 03 96 90 9A";
		String brand=data.split("\\s+")[0];//冷机型号
		String pid=data.split("\\s+")[1];//PID值
		String a=data.split("\\s+")[2];//a的值，a必须有，判断后面b预警值信息
		String at="00000000"+Integer.toBinaryString((Integer.parseInt(a,16)));
		String a8=at.substring(at.length()-8,at.length());
		String a41=a8.substring(a8.length()-4, a8.length());
		String a87=a8.substring(0, 2);
		if(a41.equals("0000")){
			
			
		}else{
			int b=Integer.parseInt(data.split("\\s+")[3],16);//来判断预警值，根据b值对应预警信息
			int ww=Integer.parseInt(a87);
			int alarm=b+256*ww;//预警值，根据alarm来判断什么报警信息
			String c=data.split("\\s+")[4];//c值必须有，来判断解析温度，如回风、出风温度
			String ct1="00000000"+Integer.toBinaryString(Integer.parseInt(c,16));
			String ct=ct1.substring(ct1.length()-8,ct1.length());
			String c8=ct.substring(0,1);
			String c7=ct.substring(1,2);
			String c6=ct.substring(2,3);
			String c5=ct.substring(3,4);
			String c2=ct.substring(6,7);
			if(c8.equals("0")){
				
				
			}else{
				String d1=data.split("\\s+")[5];//回风温度
				String d2=data.split("\\s+")[6];//回风温度
				String d=d1+d2;//由于用8位，将d1和d2合并在一起转换成十进制
				double dtem0=Integer.parseInt(d,16);//解析完数据是华氏度
				String dtem1=(""+(dtem0/10-32)/1.8).split("\\.")[0];
				String dtem2=(""+(dtem0/10-32)/1.8).split("\\.")[1];
				String dtem=dtem1+dtem2.substring(0,1);//回风温度
				if(c7.equals("0")){
					
					
				}else{
					String e1=data.split("\\s+")[7];//出风温度
					String e2=data.split("\\s+")[8];//出风温度
					String e=e1+e2;//由于用8位，将d1和d2合并在一起转换成十进制
					double etem0=Integer.parseInt(e,16);//解析完数据是华氏度
					String etem1=(""+(etem0/10-32)/1.8).split("\\.")[0];
					String etem2=(""+(etem0/10-32)/1.8).split("\\.")[1];
					String etem=etem1+etem2.substring(0,1);//出风温度
					if(c6.equals("0")){
						
						
					}else{
						String f1=data.split("\\s+")[9];//设定温度
						String f2=data.split("\\s+")[10];//设定温度
						String f=f1+f2;//由于用8位，将d1和d2合并在一起转换成十进制
						double ftem0=Integer.parseInt(f,16);//解析完数据是华氏度
						String ftem1=(""+(ftem0/10-32)/1.8).split("\\.")[0];
						String ftem2=(""+(ftem0/10-32)/1.8).split("\\.")[1];
						String ftem=ftem1+ftem2.substring(0,1);//设定温度
						if(c2.equals("0")){
							if(c2.equals("0")){
								
							}else{
								String j=data.split("\\s+")[11];
								Map<String, String> putJ=new HashMap<String, String>();
								putJ.put("c2",c2 );
								putJ.put("j",j);
								mapJ=modeDate.modeDateHandle(putJ);
							}
							
						}else{
							String g1=data.split("\\s+")[11];//蒸发线圈温度
							String g2=data.split("\\s+")[12];//蒸发线圈温度
							String g=g1+g2;//由于用8位，将d1和d2合并在一起转换成十进制
							double gtem0=Integer.parseInt(g,16);//解析完数据是华氏度
							String gtem1=(""+(gtem0/10-32)/1.8).split("\\.")[0];
							String gtem2=(""+(gtem0/10-32)/1.8).split("\\.")[1];
							String gtem=gtem1+gtem2.substring(0,1);//蒸发线圈温度
							if(c2.equals("0")){
								
							}else{
								String j=data.split("\\s+")[13];
								Map<String, String> putJ=new HashMap<String, String>();
								putJ.put("c2",c2 );
								putJ.put("j",j);
								mapJ=modeDate.modeDateHandle(putJ);
							}
							
						}
					}
					
				}
				
			}
			
			
			
		}
		
		
		
		
		
		return null;
		
	}
	
}
*/