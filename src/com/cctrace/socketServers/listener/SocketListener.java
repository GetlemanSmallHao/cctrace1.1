package com.cctrace.socketServers.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServletRequest;

public class SocketListener implements ServletContextListener{
	SocketThread socketThread;
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		if(socketThread != null){
			socketThread.closeServerSocket();
			socketThread.interrupt();
			System.out.println("socket服务关闭");
		}
	}
 
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext servletContext = sce.getServletContext();
		if(socketThread == null){
			socketThread = new SocketThread(null, servletContext);
			
			socketThread.start();
		}
	}

}
