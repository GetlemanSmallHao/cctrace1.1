/*package com.cctrace.socketServers.dataHandle;

import java.util.TimerTask;  

import javax.servlet.ServletContext;

import com.cctrace.socketServers.delData.ProcessSocketData;
public class DelFileTask extends TimerTask{

private static boolean isRunning = false;    
    
    private ServletContext context = null;    
    
    public DelFileTask() {  
        super();  
    }  
      
    public DelFileTask(ServletContext context) {    
        this.context = context;    
    }    
    @Override  
    public void run() {  
          
        if (!isRunning) {  
               
            context.log("开始执行指定任务");   
            *//** 
             * 自己的业务代码 
             *//*  
              
            isRunning = false;    
//            context.log("指定任务执行结束");  
            
            
            ProcessSocketData processSocketData=new ProcessSocketData(null);
            
            processSocketData.requestData();
            
            
            
        } else {    
            context.log("上一次任务执行还未结束");    
     }  
    }  
}
*/