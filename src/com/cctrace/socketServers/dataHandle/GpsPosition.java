package com.cctrace.socketServers.dataHandle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class GpsPosition {

	/**
	 * 调用一个地址，返回String数据
	 * 
	 * @throws Exception
	 */
	public static String giveGet(String str) throws Exception {
		try {
			String result = "";
			BufferedReader in = null;
			String urlNameString = str;
			System.out.println(urlNameString);
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 建立实际的连接
			connection.connect();
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), "utf-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
			return result;
		} catch (Exception e) {
			System.out.println("地理位置反解析！");
			e.printStackTrace();
		}
		return null;
	}

	public String getLocationMsg(String lat, String lon) throws Exception {
		List<String> list = new ArrayList<String>();
		list.add("eDf0reUD6QTmKrzXkacHXBgpfLemM1pg");
		list.add("DD279b2a90afdf0ae7a3796787a0742e");
		list.add("kDt9QNwfTMqHu4BlEKjwwiL33kLefz0r");

		for (String string : list) {
			String url = "http://api.map.baidu.com/geocoder/v2/?callback=renderReverse&location="
					+ lat + "," + lon + "&output=json&pois=1&ak=" + string;
			String result = giveGet(url);
			if (result.toString() == null) {

			} else if (result.contains("renderReverse&&renderReverse")) {
				result = result.substring(29, result.length() - 1);
				System.out.println(result);
				// 将结果转成gson对象
				JsonObject returnData = new JsonParser().parse(result)
						.getAsJsonObject();
				JsonElement rree = returnData.get("status");
				if ("0".equals(rree.toString())) {
					// boo = true;
					JsonElement jsonElement = returnData.get("result");
					String add = jsonElement.getAsJsonObject()
							.get("formatted_address").toString();
					add = add.replaceAll("\"", "");// 去掉双引号
					return add;
				}
			}
		}
		return null;

	}

}