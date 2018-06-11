package com.cctrace.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 * @author HP
 * 这个类是用来处理移动端传递过来的Json格式的数据的工具类
 * 在绝大部分app接口中我们都用到了这个工具类，所以我们要很重视这个工具类
 */
public class JsonUtils {
	/**
	 * 
	 * @param request 此处是移动端传递过来的请求，一个HttpServletRequest
	 * @return	这里返回的是一个Map<String,String>类型的对象
	 * @throws IOException 可能会出现
	 */
	public static Map<String,String> getParamsFromJsonObject(HttpServletRequest request) throws IOException{
		/**
		 * 获取一个输入流
		 */
		BufferedReader reader = request.getReader();
		/**
		 * 创建一个为空的字符串
		 */
		String input = null;
		/**
		 * 创建一个变长字符串，用于追加在输入流中读到的内容
		 */
		StringBuffer requestBody = new StringBuffer();
		/**
		 * 连续读取请求带过来的请求体，并判断其是否为空
		 */
		while((input=reader.readLine())!=null){
			/**
			 * 不断地将读取到的内容追加到变长字符串中
			 */
			requestBody.append(input);
		}
		/**
		 * 将变长字符串转换成一个json字符串
		 */
		String jsonStr = requestBody.toString();
		System.out.println(jsonStr);
		/**
		 * 使用JSONObject将字符创解析并封装成一个HashMap类型的对象并返回
		 */
		JSONObject jsonObject = JSONObject.fromObject(jsonStr);
		HashMap map = (HashMap)JSONObject.toBean(jsonObject, HashMap.class);
		return map;
	}
	/**
	 * 这是对上面程序的一个基本的测试
	 */
	@Test
	public void test1(){
		JSONObject jsonObject = JSONObject.fromObject("{\"account\":\"wolf\",\"compPassword\":\"wolf123\"}");
		JSONArray fromObject = JSONArray.fromObject(jsonObject);
		HashMap map = (HashMap) JSONObject.toBean(jsonObject, HashMap.class);
		String account = (String) map.get("account");
		String compPassword = (String)map.get("compPassword");
		System.out.println(account);
		System.out.println(compPassword);
		System.out.println(map);
	}
	
}
