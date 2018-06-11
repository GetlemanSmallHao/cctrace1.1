/*package svm.SocketServers.delData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import svm.db.dao.DaoService;
import svm.db.entity.CarMessage;
import svm.db.entity.CarMsgLog;
import svm.web.utils.DealDateData;

public class ProcessSocketData implements Runnable{
	private Socket socket;
	private  static  DaoService daoService;
	private   InputStream in = null;
	
	String sevNum=null;
	 
	
	static HashMap<String,Socket> map=new HashMap<String,Socket>();
	public ProcessSocketData(Socket client) {
		this.socket = client;
	}
	
	private  DaoService getDao() {
		if(daoService == null){
			daoService = SpringFactory.getBean("daoService");//获取注入方法
		}
		return daoService;
	}
	public void run(){
		
		try {
			
			OutputStream os = socket.getOutputStream();
		  	in = socket.getInputStream();
			byte[] buf = new byte[512];//如果改成102 当信息长度超过102时一条数据会分多次接收
			int num=0;
			int NumForCircle=0;//记录通信次数然后做删除操作
			//String msg=null;
			
            if((num=in.read(buf))!=-1){
		        
				System.out.println("socket:"+socket);
				DealTXData dealTXdata1 = new DealTXData(getDao());
				String dataString1 = new String(buf, 0, num,"utf-8");
				StringBuffer sb1 = new StringBuffer();
				System.out.println(dataString1);
				sb1.append(dataString1);
				String content1 = sb1.toString();
				sevNum=content1.split("\\,")[0];
				System.out.println("sevNum:"+sevNum+" ,socket:"+socket);
				
				
				if(dataString1.length()!=102||dataString1.split("\\,")[0].length()!=15){
					try{
				        //buf=new byte[512];
						System.out.println("socket1"+socket);
						System.out.println("close this socket"+socket);
						socket.close();
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			   if(dataString1.split("\\,")[0].length()==15){
				   synchronized(map){
					   System.out.println("socket已放入map");
				   map.put(sevNum, socket);
				   }
			   }
			
				}
			
             
			while((num=in.read(buf))!=-1){
				
				DealTXData dealTXdata = new DealTXData(getDao());
				String dataString = new String(buf, 0, num,"utf-8");
			
				//String dataString=msg;
				System.out.println(dataString);
				System.out.println(dataString.length());
			    String[] length = dataString.split("\\,");
				
				if(length.length<11 || length.length>13){
					os.flush();
				}
				StringBuffer sb = new StringBuffer();
				sb.append(dataString);
				String content = sb.toString();
				String sevNum=content.split("\\,")[0];  //获取车牌	
				  //在这里报的异常捕捉不会捕捉超时异常
			    NumForCircle=NumForCircle+1;
				//System.out.println("NFC="+NumForCircle);
				if(NumForCircle>=99){
				int count=daoService.CarMsgLogselectConuntBysevNumber(sevNum);
				System.out.println("count="+count);
				NumForCircle=0;
				if(count>300){
			    Integer sevId=daoService.CarMsgLogselectByNumber100SevId(sevNum);
				int i=daoService.CarMsgLogdeletelast100data(sevId);				
				}
				}
				DealDateData ddd=new DealDateData();
				CarMsgLog cm1=new CarMsgLog();
				cm1.setSevNumber(content.split("\\,")[0]);//设置车编号	
			    cm1.setInfo(content);
                daoService.CarMsgLoginsertSelective(cm1);//插入程序
                
	
				String lockStatue=content.split("\\,")[9];//
				
				if(sevNum.length()!=15){
					os.flush();
					continue;
				}
				if(dataString.length()!=102){
					os.flush();
					continue;
				}
				
				else{
					 sevNum=content.split("\\,")[0];
					 lockStatue=content.split("\\,")[9];
					CarMessage cm=daoService.selectCarBySevNum(sevNum);
					if(cm==null || cm.equals("")){
						System.out.println("end");
						System.out.println("end1");
						os.flush();
					}
				String	 lockSta=cm.getLockStatue();
					String sevFlag=cm.getSevFlag();
					if(sevFlag==null || sevFlag.equals("") ){
						os.flush();
					}else if(sevFlag.equals("1") && lockStatue.equals("1")){//发出开锁指令     锁的状态 关
						dealTXdata.dealTXData(content);
					//	os.write("&fgh&hhj&1&".getBytes());//发出开锁指令
					//	System.out.println("发送开锁指令成功");
						os.flush();
				
					}else if(sevFlag.equals("3") && lockSta.equals("2")){//发出判断给开锁指令后锁是否打开的指令
						
						dealTXdata.dealTXData(content);	
						
						os.flush();
					
					}else if(sevFlag.equals("0") && lockStatue.equals("1")){//锁手动关闭   关锁
						dealTXdata.dealTXData(content);
						os.flush();		
						
					}
					else if(sevFlag.equals("2") && lockStatue.equals("1")){//骑行中  关锁
						dealTXdata.dealTXData(content);
						os.flush();
						
					}else if(sevFlag.equals("0") && lockStatue.equals("1")){ //锁手动关闭加关锁
						dealTXdata.dealTXData(content);						
						os.flush();
					}else if(sevFlag.equals("2") && lockStatue.equals("0")){//骑行中  关锁
						
						dealTXdata.dealTXData(content);
						//System.out.println("timeend"+System.currentTimeMillis());
						os.flush();
						
					}else if(sevFlag.equals("1")&&lockStatue.equals("0")){//开锁指令加锁开了（此种是主动开锁可能的情况）
						dealTXdata.dealTXData(content);
						os.flush();
						
					}
					
				}
               System.out.println("end");
				os.flush();    //输出流阻塞  
				}
			
			
			
		}
      
		catch (SocketTimeoutException e) {
			
			System.out.println("连接超时，关闭连接");
			
			try {
				socket.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		catch (IOException ex) {
			try {
				System.out.println("IO异常");
				socket.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		}//捕获所有基本异常
		  catch (Exception e) {
				e.printStackTrace();
				System.out.println("数据异常，关闭连接");
				
				try {
					socket.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
	}
	
	public synchronized static int dataResponse(String num) {
		System.out.println("num");
		Socket socket1=map.get(num);
		if(socket1!=null){
			try {
				OutputStream os = socket1.getOutputStream();
				//in = socket1.getInputStream();
				os.write("&fgh&hhj&1&".getBytes());
				System.out.println("socket1开锁="+socket1);
				os.flush();
				return 1;
			}
			catch (SocketTimeoutException e) {
				System.out.println("连接超时，关闭连接");
				
					e.printStackTrace();
					return 0;
			}
			catch (IOException ex) {
				ex.printStackTrace();
				return 0;
			}
			
		}
		return 0;
	
	}

	
}




*/