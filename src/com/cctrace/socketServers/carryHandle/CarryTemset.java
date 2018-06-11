package com.cctrace.socketServers.carryHandle;

import java.util.HashMap;

import java.util.Map;

import org.springframework.util.StringUtils;

import com.cctrace.entity.CommandStore;
import com.cctrace.socketServers.util.DealDateData;
import com.github.pagehelper.util.StringUtil;

/**
 * 
 * @author LiuXueBin 开利发送指令主要工具类
 * 
 * 
 * 
 */
public class CarryTemset {
	// 除霜命令
	public static String defrostCommand = "86032258AA00AD";
	// 清除告警命令
	public static String clearAlarm = "86032458AA0101B1";
	// 启动自检命令
	public static String startSelfTest = "86032358AA00AE";
	// 连续命令
	public static String continuation = "86032158AA0100AD";
	// 启停命令
	public static String startStop = "86032158AA0101AE";
	// 开机命令 --<>待确认
	public static String startUp = "86CDE033";
	// 关机命令 --<>待确认
	public static String shutDown = "86CD0053";
	// 请求操作状态
	public static String overallUnitStatus = "86030158AA008C";
	// 请求报警数据
	public static String activeAlarmQueue = "86030258AA008D";
	// 请求设置温度值
	public static String setTemValue = "86030758AA0092";
	// 请求温度传感器
	public static String realTem = "86030858AA0093";
	// 请求发动机传感器，电压，油量，门开关，远程开关1，远程开关2
	public static String engineSensors = "86030A58AA0095";
	// 标准小时计量器（发动机，备用机，开机时长）
	public static String standardHourMeters = "86030B58AA0096";
	// 开利新风门开机命令 --<>待确认
	public static String kxfmStartUp = "86032B58AA020100B9";
	// 开利新风门关机命令 --<>待确认
	public static String kxfmShutDown = "86032B58AA020000B8";

	/*
	 * cfm开机命令 --<>待确认 public static String cfmStartUp =
	 * "86 03 2B 58 AA 02 02 XX YY"; 冷王新风门开机命令 --<>待确认 public static String
	 * lxfmStartUp = "93CE01504E9300CE9F"; 冷王新风门关机命令 --<>待确认 public static
	 * String lxfmShutDown = "93CE01603E9300CE9F";
	 */
	// 开利温度设置
	public Map<String, Object> tempSet(String setTem, String deviceId,
			String containerId) {
		String key = null;
		key = CarryTemset.getTemperatureCommandString(Double
				.parseDouble(setTem));
		if (key != null) {
			key = getSendData(key, deviceId);
		}
		if (StringUtil.isNotEmpty(key)) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("key", key);
			CommandStore cs = new CommandStore();
			Long currentLongTime = System.currentTimeMillis();
			DealDateData dealDateData = new DealDateData();
			String strFormatNowDate = dealDateData
					.getStringDate(currentLongTime);
			cs.setContent(key);
			cs.setLongTime("" + currentLongTime);
			cs.setTime(strFormatNowDate);
			cs.setCommand("temSet");
			cs.setType("1");
			cs.setStatus("0");
			cs.setValue(setTem);
			cs.setContainerId(containerId);
			map.put("command", "temSet");
			map.put("object", cs);
			return map;
		}
		return null;
	}

	// 开利cfm设置
	public Map<String, Object> cfmSet(String setCfm, String deviceId,
			String containerId) {
		String key = null;
		key = CarryTemset.getCfmCommandString(Integer.valueOf(setCfm));
		if (key != null) {
			key = getSendData(key, deviceId);
		}
		if (StringUtil.isNotEmpty(key)) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("key", key);
			CommandStore cs = new CommandStore();
			Long currentLongTime = System.currentTimeMillis();
			DealDateData dealDateData = new DealDateData();
			String strFormatNowDate = dealDateData
					.getStringDate(currentLongTime);
			cs.setContent(key);
			cs.setLongTime("" + currentLongTime);
			cs.setTime(strFormatNowDate);
			cs.setCommand("cfmSet");
			cs.setType("1");
			cs.setStatus("0");
			cs.setValue(setCfm);
			cs.setContainerId(containerId);
			map.put("command", "cfmSet");
			map.put("object", cs);
			return map;
		}
		return null;
	}

	/**
	 * 发送的数据的生成
	 */
	public static String getSendData(String command, String deviceId) {
		int lengthValue = command.length() / 2;
		String lengthString = Integer.toHexString(lengthValue).length() == 1 ? ("0" + Integer
				.toHexString(lengthValue)) : Integer.toHexString(lengthValue);
		String returnVal = deviceId + " " + lengthString + " " + command;
		return returnVal.toUpperCase();
	}

	/**
	 * 温度控制指令的生成 根据摄氏温度值，获取温度设置指令字符串 参数： 温度（摄氏）
	 */
	public static String getTemperatureCommandString(double i) {
		String firstString = "86032058AA06";
		String lastString = "FFFFFFFF";
		// 这是摄氏度 华氏度(℉)=32+摄氏度(℃)×1.8 冷机有两种模式一种是设置度，一种是华式度，暂时使用摄氏度
		// Float hua = (float) (i*1.8+32); 华氏度
		Float hua = (float) i;
		Float lastHua = hua * 32;
		// 对数据格式化向下取整
		Integer ii = (int) Math.floor(lastHua);
		// 根据数据获取字符串
		String CommandTempNum = Integer.toHexString(ii);
		System.out.println(CommandTempNum.length());
		while (CommandTempNum.length() < 4) {
			CommandTempNum = "0000" + CommandTempNum;
		}
		CommandTempNum = CommandTempNum.substring(CommandTempNum.length() - 4,
				CommandTempNum.length());
		CommandTempNum = CommandTempNum.substring(2, 4)
				+ CommandTempNum.substring(0, 2);
		StringBuffer buffer = new StringBuffer();
		buffer.append(firstString).append(CommandTempNum).append(lastString);
		String checkCode = createCheckCodeString(buffer.toString());
		buffer.append(checkCode);
		return buffer.toString().toUpperCase();
	}

	/**
	 * cfm控制指令的生成 参数： cfm
	 */
	public static String getCfmCommandString(int i) {
		String firstString = "86032B58AA0202";
		Integer ii = i;
		// 根据数据获取字符串
		String CommandCfmNum = Integer.toHexString(ii);
		System.out.println(CommandCfmNum.length());
		while (CommandCfmNum.length() < 4) {
			CommandCfmNum = "0000" + CommandCfmNum;
		}
		CommandCfmNum = CommandCfmNum.substring(CommandCfmNum.length() - 4,
				CommandCfmNum.length());
		CommandCfmNum = CommandCfmNum.substring(2, 4);
		StringBuffer buffer = new StringBuffer();
		buffer.append(firstString).append(CommandCfmNum);
		String checkCode = createCheckCodeString(buffer.toString());
		buffer.append(checkCode);
		return buffer.toString().toUpperCase();
	}

	/**
	 * 生成校验码的字符串 参数 ： 校验码之前的字符串
	 */
	public static String createCheckCodeString(String s) {

		StringBuffer bs = new StringBuffer();
		char[] charArray = s.toCharArray();
		System.out.println(charArray.length);
		// 构造待计算的数组
		String[] strs = new String[charArray.length / 2];
		for (int i = 0; i < strs.length; i++) {
			bs.append(charArray[i * 2]);
			bs.append(charArray[i * 2 + 1]);
			strs[i] = bs.toString();
			bs.delete(0, bs.length());
		}
		Integer theSum = 0;
		// 计算该数组，按照一字节有符号数进行计算
		for (int i = 0; i < strs.length; i++) {
			String tstr = strs[i];
			// int i = **
			// byte ss = (byte)(i & 0xFF);
			Integer thisval = Integer.parseInt(tstr, 16);
			byte byteValue = (byte) (thisval & 0xFF);
			theSum += byteValue;
		}
		// 将和转换成16进制的字符串
		String sumString = Integer.toHexString(theSum);
		if (sumString.length() == 1) {
			sumString = "0" + sumString;
		}
		return sumString.substring(sumString.length() - 2, sumString.length())
				.toUpperCase();
	}
}