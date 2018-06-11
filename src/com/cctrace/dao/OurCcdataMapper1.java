package com.cctrace.dao;

import java.util.List;
import java.util.Map;

import com.cctrace.entity.DevAndTimeDiff;
import com.cctrace.entity.OurCcdata1;

public interface OurCcdataMapper1 {
	OurCcdata1 selectOurCcdataById(Integer id);

	OurCcdata1 selectOurCcdataBycontainerId(String containerId);

	List<OurCcdata1> selectOurCcdatasBycompanyId(Integer companyId);

	// 根据companyId查询到一个map，存放的是时间和deviceId
	List<Map<String, Object>> selectOurMaxGpsLongTimeAndDeviceIdByCompanyAndDeviceId(
			Integer companyId);

	List<Map<String, Object>> selectOurMaxGpsLongTimeAndDeviceIdByCompanyAndDeviceIdLikely(
			Map<String, Object> map);

	OurCcdata1 selectOurLastCcdataByGpsLongTimeAndDeviceMap(
			Map<String, Object> map);

	OurCcdata1 selectOurLastCcdataByContainerId(String containerId);

	OurCcdata1 selectOurCcdataByDeviceId(String deviceId);

	OurCcdata1 selectOurLastCcdataByCompanyIdAndContainerIdLikely(
			Map<String, Object> map);

	List<OurCcdata1> selectOurCcdatasByContainerIdBetweenTowTime(
			Map<String, Object> map);

	List<OurCcdata1> selectOurCcdatasByDeviceIdBetweenTwoTime(
			DevAndTimeDiff datf);

	int deleteOurCcdataById(Integer id);

	int deleteOurCcdatasByContainerId(String containerId);

	int insertOurCcdata(OurCcdata1 ourccdata);

	int updateOurCcdataById(OurCcdata1 ourccdata);

	OurCcdata1 selectOurLastCcdataByGpsLongTimeAndDeviceMapLikely(
			Map<String, Object> map);

	// 地图专辑
	OurCcdata1 getOurLastLocationBycontainerId(String containerId);

	List<OurCcdata1> selectOurRecordsBetweenInTwoTimeBycontainerId(Map map);

	// 地图上显示所有的设备信息
	List<OurCcdata1> getOurAllLocationBycompanyId(Integer companyId);

	// 查询看对应的设备号 的经纬度是否存在
	List<OurCcdata1> selectOurCcdatasLonAndLat(String containerId);

	public int updateOurCcdataBycontainerId(OurCcdata1 ourccdata);

	// Ccdata selectCcdataByContainerId(String containerId);

	// 查询单个的硬件信息
	public OurCcdata1 selectOurHardWareBycontainerId(String containerId);

	public List<OurCcdata1> selectOurCcdatas1BycompanyId1AndNowTimeASC(
			Integer companyId);

	public List<OurCcdata1> selectOurCcdatas1BycompanyId1AndNowTimeDESC(
			Integer companyId);

	public List<OurCcdata1> selectOurCcdatas1BycompanyId1AndGPSPowerASC(
			Integer companyId);

	public List<OurCcdata1> selectOurCcdatas1BycompanyId1AndGPSPowerDESC(
			Integer companyId);

	// 根据公司id降序查询地理位置
	public List<OurCcdata1> selectOurCcdata1DescPositionByDeviceId(
			Integer companyId);

	// 根据公司id升序查询地理位置
	public List<OurCcdata1> selectOurCcdata1AscPositionByDeviceId(
			Integer companyId);

}
