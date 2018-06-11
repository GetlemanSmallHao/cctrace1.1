package com.cctrace.socketServers.util;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

///处理时间
public class DealDateData {
	
	public long toDate(String getDate) {//把返回的时间字符串转成Date类型的毫秒值
		//想要转换的格式
		SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd" ); 	
	    Date date = null;
	    long time=0;
		try {
			//parse把传入的字符串日期转为date类型日期
			date = format.parse(getDate);
			//获得该日期所对应的时间戳
			time=date.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}  
	    return time; //返回自 1970 年 1 月 1 日 00:00:00 GMT 以来此 Date 对象表示的毫秒数。
}
	//传入时间字符串转为时间戳
	public long getDateTime(String dateTime){
		long time=0;
		SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			time=format.parse(dateTime).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		  }		
		return time;		
	}
	//传入时间字符串转为时间戳
	public long getDateTime1(String dateTime){
		long time=0;
		SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.0");
		try {
			time=format.parse(dateTime).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		  }		
		return time;		
	}
	
	public long getDateTime2(String dateTime){
		long time=0;
		SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		try {
			time=format.parse(dateTime).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		  }		
		return time;		
	}
	//传入时间字符串转为时间戳
	public long getDateTime3(String dateTime){
		long time=0;
		SimpleDateFormat format =  new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		try {
			time=format.parse(dateTime).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		  }		
		return time;		
	}
	
	//传入时间戳转为时间字符串
	public String GetNomalDate(long timeStamp){
		SimpleDateFormat format1 =  new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.000"); //2016-08-17T12:00:00.000Z     
	    String d = format1.format(timeStamp);  
	    //Date date1=format1.parse(d);  
	    //System.out.println(""+d);  
	    //System.out.println("Format To Date:"+date1); 
	    return d;
	}
	//传入时间戳转为时间字符串
		public String GetNomalDate1(long timeStamp){
			SimpleDateFormat format1 =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //2016-08-17T12:00:00.000Z     
		    String d = format1.format(timeStamp);  
		    //Date date1=format1.parse(d);  
		    //System.out.println(""+d);  
		    //System.out.println("Format To Date:"+date1); 
		    return d;
		}
	//传入时间戳转为时间字符串
	public String getStringDate(long timeStamp){
		SimpleDateFormat format1 =  new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); //2016-08-17T12:00:00.000Z     
	    String d = format1.format(timeStamp);  
	    //Date date1=format1.parse(d);  
	    //System.out.println(""+d);  
	    //System.out.println("Format To Date:"+date1); 
	    return d;
	}
//测试使用	
	public static void main(String[] args) {
		DealDateData ddd=new DealDateData();
		System.out.println(ddd.getStringDate(1477618880000L));
		System.out.println(ddd.getDateTime1("2017-04-01 00:00:00.000"));
	/*	long time=0;
		String dateTime="2016-08-23 03:11:13";
		time=ddd.getDateTime(dateTime);
		System.out.println(time);*/
	}
	
}
