package com.cctrace.socketServers.listener;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.cctrace.socketServers.delData.ProcessSocketData;



public class SocketThread extends Thread{
	private ServerSocket serverSocket;
	private Socket socket;
	public SocketThread(ServerSocket serverSocket, ServletContext servletContext) {
		this.serverSocket = serverSocket;
		
		String port = "8085";
		
		if(serverSocket == null){
			try {
				this.serverSocket = new ServerSocket(Integer.parseInt(port));
			} catch (IOException e) {
				try {
					serverSocket.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	@Override
	public void run() {
		while(!this.isInterrupted()){
			
			try {
				socket = this.serverSocket.accept();
				
				System.out.println("接收数据");
				socket.setSoTimeout(700000);
				if(socket != null){
					ProcessSocketData a = new ProcessSocketData(socket);
					Thread thread = new Thread(a);
					thread.start();
				}
			} catch (IOException e) {
				System.out.println("socket服务器出错！");
				try {
					socket.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	
	public void closeServerSocket(){
		try {
			if(serverSocket != null && !serverSocket.isClosed()){
				serverSocket.close();
			}
		} catch (IOException e) {
			try {
				socket.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}
