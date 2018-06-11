package com.cctrace.dao;

import java.util.List;
import java.util.Map;

import com.cctrace.entity.Ccdata;
import com.cctrace.entity.DevAndTimeDiff;
public interface CcdataMapper {
	Ccdata selectCcdataById(Integer id);
	
	Ccdata selectCcdataBycontainerId(String containerId);

	List<Ccdata> selectCcdatasBycompanyId(Integer companyId);

	// 根据companyId查询到一个map，存放的是时间和deviceId
	List<Map<String, Object>> selectMaxGpsLongTimeAndDeviceIdByCompanyAndDeviceId(Integer companyId);

	List<Map<String, Object>> selectMaxGpsLongTimeAndDeviceIdByCompanyAndDeviceIdLikely(
			Map<String, Object> map);
	
	Ccdata selectLastCcdataByGpsLongTimeAndDeviceMap(Map<String, Object> map);
	
	Ccdata selectLastCcdataByContainerId(String containerId);
	
	Ccdata selectCcdataByDeviceId(String deviceId);
	
	Ccdata selectLastCcdataByCompanyIdAndContainerIdLikely(Map<String,Object> map);
	
	List<Ccdata> selectCcdatasByContainerIdBetweenTowTime(Map<String,Object> map);
	
	List<Ccdata> selectCcdatasByContainerIdBetweenTowTimeASC(Map<String,Object> map);
	
	List<Ccdata> selectCcdatasByDeviceIdBetweenTwoTime(DevAndTimeDiff datf);
	
	int deleteCcdataById(Integer id);
	
	int deleteCcdatasByContainerId(String containerId);

	int insertCcdata(Ccdata ccdata);
	
	int updateCcdataById(Ccdata ccdata);

	Ccdata selectLastCcdataByGpsLongTimeAndDeviceMapLikely(
			Map<String, Object> map);
	 
	
	public List<Ccdata> getLastCcdatasByGpsLongTimeAndDeviceMap(List<Map<String,Object>> maps);
	//地图专辑
	 Ccdata getLastLocationBycontainerId(String containerId);
	 
	 List<Ccdata> selectRecordsBetweenInTwoTimeBycontainerId(Map map);
	 //地图上显示所有的设备信息
	 List<Ccdata> getAllLocationBycompanyId(Integer companyId);
	 //查询看对应的设备号 的经纬度是否存在
	 List<Ccdata> selectCcdatasLonAndLat(String containerId);
	 
	 public int updateCcdataBycontainerId(Ccdata ccdata);
	 
//	 Ccdata selectCcdataByContainerId(String containerId);
	 
	 //查询单个的硬件信息
	 public Ccdata selectHardWareBycontainerId(String containerId);
	 //模糊查询单个传感器号
	 public List<Ccdata> selectSensorLikeyByContainerId(String containerId);
	 //主页展示优化版
	 public List<Ccdata> selectMaxNowLongTimeIndex(Integer companyId);

}
