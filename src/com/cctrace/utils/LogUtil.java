package com.cctrace.utils;

import java.util.Date;

import com.cctrace.entity.Log;
import com.cctrace.entity.User;
import com.cctrace.service.DaoService;

public class LogUtil {
	
	private static DaoService daoService;
	
	public static void userLoginLog(User user){
		Log log = new Log();
		log.setOperator(user.getUsername());
		Date now  = new Date();
		String dateStr = DateUtil.getDateStr(now,"yyyy-MM-dd HH:mm:ss");
		long longDate = DateUtil.getLongFromDate(now);
	
		//daoService.addNewLog(log);
	}
	
}
