package com.cctrace.utils;

import org.springframework.data.redis.core.RedisTemplate;

import com.cctrace.entity.OfflineOrder;
import com.google.gson.Gson;

public class OfflineOrderUtil {
	private static RedisTemplate<String, Object> redisTemplate;

	// 根据设备号返回命令执行字符串
	public static String getCommandStringByDevId(String devId) {
		String result = null;
		Object object = redisTemplate.opsForHash().get("offlineorder", devId);
		if (object != null) {
			Gson gson = new Gson();
			OfflineOrder offlineOrder = gson.fromJson((String) object,
					OfflineOrder.class);
			if (offlineOrder != null) {
				result = offlineOrder.getReturnVal();
			}
		}
		return result;
	}

	// 根据设备号码删除该离线缓存
	public static void deleteCommandStringByDevId(String devId) {
		redisTemplate.opsForHash().delete("offlineorder", devId);
	}

	// 根据设备号获取离线命令实体
	public static OfflineOrder getOffLineOrderByDev(String dev) {
		OfflineOrder result = null;
		Object object = redisTemplate.opsForHash().get("offlineorder", dev);
		if (object != null) {
			Gson gson = new Gson();
			OfflineOrder offlineOrder = gson.fromJson((String) object,
					OfflineOrder.class);
			if (offlineOrder != null) {
				result = offlineOrder;
			}
		}
		return result;
	}

	public RedisTemplate<String, Object> getRedisTemplate() {
		return redisTemplate;
	}

	public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
		OfflineOrderUtil.redisTemplate = redisTemplate;
	}
}
