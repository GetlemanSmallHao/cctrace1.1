package com.cctrace.dao;

import java.util.List;
import java.util.Map;

import com.cctrace.entity.Ccdata;
import com.cctrace.entity.DevAndTimeDiff;
import com.cctrace.entity.OurCcdata;
public interface OurCcdataMapper {
	OurCcdata selectOurCcdataById(Integer id);
	
	OurCcdata selectOurCcdataBycontainerId(String containerId);

	List<OurCcdata> selectOurCcdatasBycompanyId(Integer companyId);

	// 根据companyId查询到一个map，存放的是时间和deviceId
	List<Map<String, Object>> selectOurMaxGpsLongTimeAndDeviceIdByCompanyAndDeviceId(Integer companyId);

	List<Map<String, Object>> selectOurMaxGpsLongTimeAndDeviceIdByCompanyAndDeviceIdLikely(
			Map<String, Object> map);
	
	OurCcdata selectOurLastCcdataByGpsLongTimeAndDeviceMap(Map<String, Object> map);
	
	OurCcdata selectOurLastCcdataByContainerId(String containerId);
	
	OurCcdata selectOurCcdataByDeviceId(String deviceId);
	
	OurCcdata selectOurLastCcdataByCompanyIdAndContainerIdLikely(Map<String,Object> map);
	
	List<OurCcdata> selectOurCcdatasByContainerIdBetweenTowTime(Map<String,Object> map);
	
	List<OurCcdata> selectOurCcdatasByContainerIdBetweenTowTimeASC(Map<String,Object> map);
	
	List<OurCcdata> selectOurCcdatasByDeviceIdBetweenTwoTime(DevAndTimeDiff datf);
	
	int deleteOurCcdataById(Integer id);
	
	int deleteOurCcdatasByContainerId(String containerId);

	int insertOurCcdata(OurCcdata ourccdata);
	
	int updateOurCcdataById(OurCcdata ourccdata);

	OurCcdata selectOurLastCcdataByGpsLongTimeAndDeviceMapLikely(
			Map<String, Object> map);
	   
	//地图专辑
	OurCcdata getOurLastLocationBycontainerId(String containerId);
	 
	 List<OurCcdata> selectOurRecordsBetweenInTwoTimeBycontainerId(Map map);
	 //地图上显示所有的设备信息
	 List<OurCcdata> getOurAllLocationBycompanyId(Integer companyId);
	 //查询看对应的设备号 的经纬度是否存在
	 List<OurCcdata> selectOurCcdatasLonAndLat(String containerId);
	 
	 public int updateOurCcdataBycontainerId(OurCcdata ourccdata);
	 
//	 Ccdata selectCcdataByContainerId(String containerId);
	 
	 //查询单个的硬件信息
	 public OurCcdata selectOurHardWareBycontainerId(String containerId);


}
