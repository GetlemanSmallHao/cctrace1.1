package com.cctrace.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	public static String getDateStr(Date date,String pattern){
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}
	
	public static Date getDateFromStr(String dateStr,String pattern) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date date = sdf.parse(dateStr);
		return date;
	}
	
	public static String getTimeDifferenceBetwennTwoDate(Date startDate,Date endDate){
		long end = endDate.getTime();
		long start = startDate.getTime();
		long hours = (end-start)/(3600*1000);
		long mins = ((end - start) - hours * (3600 * 1000)) / (1000 * 60);
		long secs = (((end - start) - hours * (3600 * 1000)) - (1000 * 60 * mins)) / 1000;
		String timeDifference = hours+"-"+mins+"-"+secs;
		return timeDifference;
	}
	

	/**
	 * 获取当前日期Date类型
	 */
	public static Date getNowDate(){
		return new Date();
	}
	
	/**
	 * 根据传入的date类型的值获取一个长整型的参数
	 * @param date
	 * @return
	 */
	public static long getLongFromDate(Date date){
		long time = 0;
		try {
			time = date.getTime();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return time;
	}
	
	/**
	 * 根据插入的日期字符串和匹配类型获取一个长整型的参数
	 * @param dateStr
	 * @param pattern
	 * @return
	 */
	public static long getLongFromStr(String dateStr,String pattern){
		Date date = null;
		try {
			date = getDateFromStr(dateStr, pattern);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long time = getLongFromDate(date);
		return time;
	}
	
	/**
	 * 根据天数和现在时刻获取Date
	 * 类型的若干天以前的日期
	 * @param now 现在
	 * @param serveral
	 * @return
	 */
	public static Date getServeralDaysBeforeNowTime(Date now , Integer serveral){
		long nowTime = now.getTime();
		long serveralDaysBeforeNowTime = nowTime - (86400000*serveral);
		Date date = new Date(serveralDaysBeforeNowTime);
		return date;
	}
	/**
	 * 根据现在的时间生成2017/11/10 00:11:23 格式的字符串
	 * @param now 现在
	 * @param serveral
	 * @return
	 */
	public static String getNowDateStringByAlert(){
		String pattern = "yyyy/MM/dd HH:mm:ss";
		return getDateStr(new Date(),pattern);
	}
}
