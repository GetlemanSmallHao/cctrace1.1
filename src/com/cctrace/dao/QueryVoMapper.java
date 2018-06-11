package com.cctrace.dao;

import java.util.List;
import java.util.Map;

import com.cctrace.entity.Ccdata;
import com.cctrace.entity.DevAndTimeDiff;
import com.cctrace.entity.QueryVo;

public interface QueryVoMapper {
	 
	public QueryVo getInforBycontainerId(String containerId);
	
	public List<QueryVo> showYardInformation(Integer companyId);
	
	public List<QueryVo> showStationInformation(Integer companyId);

	public List<QueryVo> showAllContainerByUsername(Integer companyId);
	
	public List<QueryVo> showAllBanLieByCompanyId(Integer companyId);
	
	//班列详情
	public QueryVo showBanLieInfo(String containerId);
	//冷藏箱详情
	public QueryVo showContainerinforBycontainerId(String containerId);
	//没有预警信息班列详情
	public QueryVo showBanLieInfoNoAlert(String containerId);
	//展示传感器详情表（在两段时间内的信息）
	public List<QueryVo> showSensorBetweenTwoTimeInfo(Map<String,Object> map);
	//显示传感器的设置信息
	public QueryVo showSensorSetting(String containerId);
	//展示我的信息
	public QueryVo showMyInfo(String username);
	
}
