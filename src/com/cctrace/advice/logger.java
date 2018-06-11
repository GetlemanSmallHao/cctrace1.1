package com.cctrace.advice;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;

import com.cctrace.entity.Log;
import com.cctrace.entity.User;
import com.cctrace.service.DaoService;
import com.cctrace.utils.DateUtil;


//日志记录仪
public class logger {
	
	
	@Autowired
	DaoService daoService;
	
	@Autowired  
    private  HttpServletRequest request2;  
	
	public Object record(ProceedingJoinPoint pjp) throws Throwable{
		Log log = new Log();
		
		//HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();  
		
		HttpSession session = request2.getSession();  
	
		//读取session中的用户  
        User user = (User) session.getAttribute("user");  
        if(user!=null){
        	log.setOperator(user.getUsername());
			log.setCompanyId(user.getCompanyId());
			String  operatorTime= DateUtil.getDateStr(new Date(), "yyyy-MM-dd HH:mm:ss");
			long operatorLongTime = DateUtil.getLongFromStr(operatorTime, "yyyy-MM-dd HH:mm:ss");
			log.setOperatorTime(operatorTime);
			log.setOperatorLongTime(operatorLongTime);
			
			
			//操作名称
			String oname =pjp.getSignature().getName();
			
			
			log.setContent(oname);
			
			//操作参数
			/*Object[] params = pjp.getArgs();
			
			if(DataUtil.arr2Str(params).length()>10){
				log.setOperatorParams(DataUtil.arr2Str(params).substring(0, 10));
			}else{
				log.setOperatorParams(DataUtil.arr2Str(params));
			}*/
			
			System.out.println("###"+log);
			
			//调用目标对象的方法
			Object proceed = pjp.proceed();
			daoService.logOneNewLog(log);
			
        }		
		
		//调用目标对象的方法
		Object proceed = pjp.proceed();
		return proceed;
	}	
	
	//前置通知
	public void before(JoinPoint joinPoint){
		Log log = new Log();	
		
		HttpSession session = request2.getSession(); 
		//读取session中的用户  
        User user = (User) session.getAttribute("user");
        if(user!=null){
        	log.setOperator(user.getUsername());
			log.setCompanyId(user.getCompanyId());
			String  operatorTime= DateUtil.getDateStr(new Date(), "yyyy-MM-dd HH:mm:ss");
			long operatorLongTime = DateUtil.getLongFromStr(operatorTime, "yyyy-MM-dd HH:mm:ss");
			log.setOperatorTime(operatorTime);
			log.setOperatorLongTime(operatorLongTime);
			
			//操作名称
			String oname =joinPoint.getSignature().getName();  
			log.setContent(oname);
			
			
			//操作参数
			List<Object> params = Arrays.asList(joinPoint.getArgs()); 
			
			String params1 =listToString(params, ',');
			log.setOperatorParams(params1);
			
			
			
			// List<Object> args = Arrays.asList(joinPoint.getArgs());  
			System.out.println("The method "+oname +" begins with "+ params1); 
	
			System.out.println("###"+log);
			
			daoService.logOneNewLog(log);
		
		
	}
		
	
	}
	public String listToString(List list, char separator) {  
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < list.size(); i++) {     
			sb.append(list.get(i));       
			if (i < list.size() - 1) {          
				sb.append(separator);     
				}   
			}
		if(sb.length()>90){
			return sb.toString().substring(0,90).concat("..");
		}
		return sb.toString();
	}
	 
	
}
