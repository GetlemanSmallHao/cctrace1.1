package com.cctrace.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSON;
import net.sf.json.JSONObject;

public class ChangeRealLocation {

	/* *//**
	 * @param args
	 */
	/*
	 * public static void main(String[] args) { //天气（根据拼音） String tqMcParam =
	 * "lat=40.43&lng=74.0"; List<Double> sendGET = SendGET(40.43,74.0);
	 * System.out.println(sendGET.get(0)); }
	 */

	public static List<Double> SendGET(Double lat1, Double lon1) {
		String param = "lat=" + lat1 + "&lng=" + lon1;
		String url = "http://api.zdoz.net/transgps.aspx";
		String result = "";// 访问返回结果
		BufferedReader read = null;// 读取访问结果
		try {
			// 创建url
			URL realurl = new URL(url + "?" + param);
			// 打开连接
			URLConnection connection = realurl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立连接
			connection.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段，获取到cookies等
			for (String key : map.keySet()) {
			}
			// 定义 BufferedReader输入流来读取URL的响应
			read = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), "UTF-8"));
			String line;// 循环读取
			while ((line = read.readLine()) != null) {
				result += line;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (read != null) {// 关闭流
				try {
					read.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		JSON tqMcJson = JSONObject.fromObject(result);
		String json = tqMcJson.toString();
		String replace1 = json.replace(",", ":");
		String replace = replace1.replace("}", "");
		String[] split = replace.split(":");
		Double lon = Double.parseDouble(split[1]);
		Double lat = Double.parseDouble(split[3]);
		List<Double> deal = new ArrayList<>();
		deal.add(lat);
		deal.add(lon);
		return deal;
	}
}