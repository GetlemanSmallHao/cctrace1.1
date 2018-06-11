/**
 * 
 */
package com.cctrace.socketServers.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.net.Socket;

/**
 * @author glf
 * @time 2018-3-14 下午7:59:38
 * @fileName 日志
 */
public class Journal {
	
	public static void writeEtcFile(String etcName) {
		Long currentLongTime = System.currentTimeMillis();
		DealDateData dealDateData = new DealDateData();
		String sysTime = dealDateData.getStringDate(currentLongTime);
		File file = new File("D:\\etc\\" + "extName  " + etcName + ".txt");
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter rr = new FileWriter(file, true);
			BufferedWriter writer = new BufferedWriter(rr);
			writer.write("etcName  " + etcName + "       " + sysTime);
			writer.newLine();
			writer.flush();
			writer.close();
			rr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void writeSocket(Socket socket) {
		Long currentLongTime = System.currentTimeMillis();
		DealDateData dealDateData = new DealDateData();
		String sysTime = dealDateData.getStringDate(currentLongTime);
		File file = new File("D:\\etc\\" + "socketServer.txt");
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter rr = new FileWriter(file, true);
			BufferedWriter writer = new BufferedWriter(rr);
			writer.write(socket.toString() + "       " + sysTime);
			writer.newLine();
			writer.flush();
			writer.close();
			rr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
