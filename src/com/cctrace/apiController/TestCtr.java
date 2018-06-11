package com.cctrace.apiController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class TestCtr {
	public static void main(String[] args) throws InterruptedException {
		testTT();
	}
	
	
	/**
	 * 测试百度地图稳定性质2秒一次
	 * @throws InterruptedException 
	 */
	/*
	116.422666,39.907408
	117.172356,39.087826
	113.341119,40.137262
	113.649274,34.815169
	16.980677,21.396956
	2.226045,48.846895
	8.665099,50.188326
	 */
	public static void testTT() throws InterruptedException{
		int a = 0;
		Map<String,String> map = new HashMap<String,String>();
		map.put("116.422666","39.907408");
		map.put("117.172356","39.087826");
		map.put("113.341119","40.137262");
		map.put("113.649274","34.815169");
		map.put("16.980677","21.396956");
		map.put("2.226045","48.846895");
		map.put("8.665099","50.188326");
		
		while(true){
			Set<String> keySet = map.keySet();
			for (String string : keySet) {
				Thread.sleep(1000);
				System.out.println("请求百度逆地址解析API的次数:"+a);
				a++;
				String sendGet = sendGet(map.get(string),string);
				System.out.println("解析到的地址为"+sendGet);
			}
		}
	}
	
	 /**
     * 向指定百度API发送逆地址解析GET方法的请求
     * 
     * @param url
     *            发送请求的URL
     * @param param
     *            lat 维度 lng经度
     * @return 格式化地址描述
     */
    public static String sendGet(String lat,String lng) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = "http://api.map.baidu.com/geocoder/v2/?callback=renderReverse&location="+lat+","+lng+"&output=json&pois=1&ak=kDt9QNwfTMqHu4BlEKjwwiL33kLefz0r";
            System.out.println(urlNameString);
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(),"utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
           System.out.println(result);
           //去掉无用的字符串整合正josn
           result = result.substring(29,result.length()-1);
           System.out.println(result);
           //将结果转成gson对象
           JsonObject returnData = new JsonParser().parse(result).getAsJsonObject();
           JsonElement rree = returnData.get("result");
           JsonElement jsonElement = rree.getAsJsonObject().get("formatted_address");
           return jsonElement.toString();
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
		return null;
        
    }
	
	
	
	
	
}
